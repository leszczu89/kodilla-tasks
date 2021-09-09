package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Builder()
@AllArgsConstructor
@Getter
public class Mail {
    private String mailTo;
    private String subject;
    private String message;
    @Builder.Default
    private String toCc = null;

}
