package org.ayush.expertai.services;


import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;

@Service
public class MoodService {
    private final ChatClient chatClient;

    @Value("classpath:/prompts/mood.st")
    private Resource moodPrompt;

    public MoodService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String getRecommendationBasedOnMood(String mood){
        PromptTemplate promptTemplate = new PromptTemplate(moodPrompt);
        Map<String, Object> map = new HashMap<>();
        map.put("mood",mood);
        Prompt prompt = promptTemplate.create(map);
        ChatResponse response = chatClient.call(prompt);
        return response.getResult().getOutput().getContent();
    }
}
