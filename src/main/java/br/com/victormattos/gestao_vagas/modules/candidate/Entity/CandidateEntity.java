package br.com.victormattos.gestao_vagas.modules.candidate.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

@Data
@Entity(name = "candidate")
public class CandidateEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String name;

  @Pattern(regexp = "\\S+", message = "O campo [usename] não deve conter espaços")
  private String username;

  @Email(message = "O campo (E-mail) deve conter um e-mail válido!")
  private String email;

  @Length(min = 10, max = 100, message = "A senha deve conter entre 10 e 100 caracteres")
  private String password;

  private String description;
  private String curriculum;

  @CreationTimestamp
  private LocalDateTime createAt;

  public String getUsername() {
    return this.username;
  }

  public String getEmail() {
    return this.email;
  }
}