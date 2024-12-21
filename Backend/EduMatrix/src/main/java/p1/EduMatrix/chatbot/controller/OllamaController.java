package p1.EduMatrix.chatbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import p1.EduMatrix.chatbot.services.ChatbotService;
import p1.EduMatrix.chatbot.services.PdfTextExtractorService;
import reactor.core.publisher.Flux;

import java.io.File;

@RestController
@RequestMapping("/api/chat")
public class OllamaController {

    @Autowired
    private PdfTextExtractorService pdfTextExtractorService;

    @Autowired
    private ChatbotService chatbotService;

    // In-memory context (this can be replaced with a more robust storage solution)
    private String currentContext = "";

    @PostMapping(value = "/ask", produces = MediaType.TEXT_EVENT_STREAM_VALUE) // Enable SSE for streaming
    public Flux<String> askQuestion(
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
                    : "\"" + currentContext + "\"  \"" + userQuestion + "\"";

            // Send the prompt to the chatbot and stream the response
            return chatbotService.getStreamedResponseFromChatbot(prompt);

        } catch (Exception e) {
            // In case of an error, return a single Flux with the error message
            return Flux.just("Error: " + e.getMessage());
        }
    }
}
