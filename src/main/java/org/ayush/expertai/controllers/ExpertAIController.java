package org.ayush.expertai.controllers;

//import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/expert")
public class ExpertAIController
{

    private static final Logger log = LoggerFactory.getLogger(ExpertAIController.class);
    public final ChatClient chatClient;

//    @Value("classpath:/prompts/expert.st")
//    private String expertPromptStr;

    public ExpertAIController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }


    @GetMapping
    public String expertOpinion(@RequestParam String question, @RequestParam String expert){
        String expertString = """ 
                You are {expert}. Please answer questions for your area of specialization. For anything else reply you do not know.
                
                {question}
                """;
        PromptTemplate promptTemplate = new PromptTemplate(expertString);
        Map<String, Object> map = new HashMap<>();
        map.put("expert",expert);
        map.put("question",question);
        Prompt prompt = promptTemplate.create(map);
        System.out.println(prompt.getContents());
        ChatResponse response = chatClient.call(prompt);
        return response.getResult().getOutput().getContent();
    }
}
