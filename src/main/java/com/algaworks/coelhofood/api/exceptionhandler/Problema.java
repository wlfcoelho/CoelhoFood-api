package com.algaworks.coelhofood.api.exceptionhandler;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class Problema {

    private LocalDateTime dataHora;
    private String mensagem;
}
