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
public class ExpertService {
    private final ChatClient chatClient;


    public ExpertService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Value("classpath:/prompts/expert.st")
    private Resource expertPromptStr;

    public String expertOpinion(String question, String expert){

        PromptTemplate promptTemplate = new PromptTemplate(expertPromptStr);
        Map<String, Object> map = new HashMap<>();
        map.put("expert",expert);
        map.put("question",question);
        Prompt prompt = promptTemplate.create(map);
        ChatResponse response = chatClient.call(prompt);
        return response.getResult().getOutput().getContent();
    }
}
