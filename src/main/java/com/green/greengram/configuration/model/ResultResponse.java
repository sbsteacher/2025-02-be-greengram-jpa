package com.green.greengram.configuration.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResultResponse <T> {
    private String resultMessage;
    private T resultData;
}
