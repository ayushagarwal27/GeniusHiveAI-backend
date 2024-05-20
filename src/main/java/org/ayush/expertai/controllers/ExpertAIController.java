package org.ayush.expertai.controllers;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/expert")
public class ExpertAIController
{

    public final ChatClient chatClient;

//    @Value("classpath:/prompts/expert.st")
//    private String systemExpertMessage;

    public ExpertAIController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }


    @GetMapping
    public String expertOpinion(@RequestParam String question, @RequestParam String expert){
        SystemMessage systemMessage = new SystemMessage("You are "+ expert+". Please answer questions for your area of specialization. For anything else reply you do not know.");
        UserMessage userMessage = new UserMessage(question) ;
        Prompt prompt = new Prompt(List.of(systemMessage, userMessage));
        return chatClient.call(prompt).getResult().getOutput().getContent();
    }
}
