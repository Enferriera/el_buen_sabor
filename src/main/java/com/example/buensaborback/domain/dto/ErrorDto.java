package com.example.buensaborback.domain.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDto {
    private String errorMsg;
    private String errorClass;
}
