package p1.EduMatrix.chatbot.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;

@Service
public class ChatbotService {

    private final WebClient webClient;

    public ChatbotService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Flux<String> getStreamedResponseFromChatbot(String prompt) {
        String url = "http://localhost:11434/api/generate";

        // Build the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "llama3.2");
        requestBody.put("prompt", prompt);
        requestBody.put("stream", true);

        // Send the request and return the streamed response
        return webClient.post()
                .uri(url)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToFlux(String.class); // Stream chunks of response as they are generated
    }
}
