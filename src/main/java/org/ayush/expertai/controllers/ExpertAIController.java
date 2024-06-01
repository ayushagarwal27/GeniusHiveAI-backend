package org.ayush.expertai.controllers;


import org.ayush.expertai.dtos.ExpertRequestDto;
import org.ayush.expertai.dtos.ExpertResponseDto;
import org.ayush.expertai.services.ExpertService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/expert")
public class ExpertAIController {
    private final ExpertService expertService;




    public ExpertAIController(ExpertService expertService) {
        this.expertService = expertService;
    }


    @CrossOrigin(origins = "${ALLOWED_URL_EXPERT}")
    @PostMapping
    public ResponseEntity<ExpertResponseDto> expertOpinion(@RequestBody ExpertRequestDto expertRequestDto) {
        String reply = expertService.expertOpinion(expertRequestDto.getQuery(), expertRequestDto.getExpert());
        ExpertResponseDto expertResponseDto = new ExpertResponseDto();
        expertResponseDto.setReply(reply);
        return ResponseEntity.ok().body(expertResponseDto);
    }
}
