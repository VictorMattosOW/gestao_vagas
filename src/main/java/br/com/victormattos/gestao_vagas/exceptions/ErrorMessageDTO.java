package br.com.victormattos.gestao_vagas.exceptions;

import lombok.Data;

@Data
public class ErrorMessageDTO {

  public ErrorMessageDTO(String msg, String field) {
    this.message = msg;
    this.field = field;
  }

  private String message;
  private String field;
}
