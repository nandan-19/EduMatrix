package p1.EduMatrix.chatbot.controller;
import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import p1.EduMatrix.chatbot.services.ChatbotService;
import p1.EduMatrix.chatbot.services.PdfTextExtractorService;

@RestController
@RequestMapping("/api/chat")
public class OllamaController {

    @Autowired
    private PdfTextExtractorService pdfTextExtractorService;

    @Autowired
    private ChatbotService chatbotService;

    // In-memory context (this can be replaced with a more robust storage solution)
    private String currentContext = "";

    @PostMapping("/ask")
    public ResponseEntity<String> askQuestion(
        @RequestParam(value = "file", required = false) MultipartFile file,
        @RequestParam("question") String userQuestion) {

        try {
            // If a new file is uploaded, extract the context
            if (file != null) {
                File tempFile = File.createTempFile("uploaded", ".pdf");
                file.transferTo(tempFile);

                // Replace the current context with the extracted text
                currentContext = pdfTextExtractorService.extractTextFromPdf(tempFile);
                tempFile.delete();
                 
            }

            // Build the prompt for Ollama
            String prompt = currentContext.isEmpty() 
                ? userQuestion 
                : "\"" + currentContext + "\" prompt: \"" + userQuestion + "\"";
                
            // Send the prompt to the chatbot
            String response = chatbotService.getResponseFromChatbot(prompt);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }
}
