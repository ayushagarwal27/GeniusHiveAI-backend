package org.ayush.expertai.controllers;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mood")
public class MoodAiController {

    private final ChatClient chatClient;

    public MoodAiController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Value("classpath:/prompts/mood.st")
    private Resource moodPrompt;


    @GetMapping
    public String getRecommendationBasedOnMood(@RequestParam String mood){
        PromptTemplate promptTemplate = new PromptTemplate(moodPrompt);
        Map<String, Object> map = new HashMap<>();
        map.put("mood",mood);
        Prompt prompt = promptTemplate.create(map);
        ChatResponse response = chatClient.call(prompt);
        return response.getResult().getOutput().getContent();
    }
}
