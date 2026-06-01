package study;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
public class AIController {
    
    private final OpenAiChatClient chatClient;
    
    public AIController(OpenAiChatClient chatClient) {
        this.chatClient = chatClient;
    }
    
    @GetMapping("/chat")
    public String chat(@RequestParam String message) {
        return chatClient.call(message);
    }
}
