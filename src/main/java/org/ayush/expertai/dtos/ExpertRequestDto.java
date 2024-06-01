package org.ayush.expertai.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpertRequestDto {
    private String expert;
    private String query;
}
