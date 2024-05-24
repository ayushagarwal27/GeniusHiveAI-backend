package org.ayush.expertai.controllers;


import org.ayush.expertai.dtos.ExpertResponseDto;
import org.ayush.expertai.services.ExpertService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/expert")
public class ExpertAIController {
    private final ExpertService expertService;


    public ExpertAIController(ExpertService expertService) {
        this.expertService = expertService;
    }


    @GetMapping
    public ResponseEntity<ExpertResponseDto> expertOpinion(@RequestParam String question, @RequestParam String expert) {
        String reply = expertService.expertOpinion(question, expert);
        ExpertResponseDto expertResponseDto = new ExpertResponseDto();
        expertResponseDto.setReply(reply);
        return ResponseEntity.ok().body(expertResponseDto);
    }
}
