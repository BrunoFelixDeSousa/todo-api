package br.com.bfelix.todo.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TodoDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataParaFinalizar;
    private Boolean finalizado;
}
