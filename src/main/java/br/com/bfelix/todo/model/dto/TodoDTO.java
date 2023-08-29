package br.com.bfelix.todo.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class TodoDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private Date dataParaFinalizar;
    private Boolean finalizado;
}
