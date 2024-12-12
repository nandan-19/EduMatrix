package p1.EduMatrix.chatbot.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate; // For sending HTTP requests to the Ollama API
import org.springframework.http.HttpEntity;       // For building HTTP requests
import org.springframework.http.HttpHeaders;     // For specifying HTTP headers
import org.springframework.http.HttpMethod;      // For defining the HTTP method (POST)
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;  // For handling HTTP responses
import com.fasterxml.jackson.databind.ObjectMapper; // For JSON serialization/deserialization

import java.io.IOException;                      // For exception handling
import java.util.HashMap;                        // For handling dynamic data (e.g., context)
import java.util.Map;                            // For defining the structure of context storage


@Service
public class ChatbotService {

    public String getResponseFromChatbot(String prompt) {
        // Send the prompt to the Ollama API
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:11434/api/generate";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Build the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "llama3.2");
        requestBody.put("prompt", prompt);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        // Handle the response
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to get response from chatbot");
        }
    }
}
