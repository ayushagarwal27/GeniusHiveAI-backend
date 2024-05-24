package org.ayush.expertai.controllers;

import org.ayush.expertai.dtos.MoodResponseDto;
import org.ayush.expertai.services.MoodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/mood")
public class MoodAiController {

    private final MoodService moodService;

    public MoodAiController(MoodService moodService) {
        this.moodService = moodService;
    }

    @GetMapping
    public ResponseEntity<MoodResponseDto> getRecommendationBasedOnMood(@RequestParam String mood){
        String moodRecommendations = moodService.getRecommendationBasedOnMood(mood);
        MoodResponseDto moodResponseDto = new MoodResponseDto();
        moodResponseDto.setMoodRecommendations(moodRecommendations);
        return ResponseEntity.ok().body(moodResponseDto);
    }
}
