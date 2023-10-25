package br.com.victormattos.gestao_vagas.modules.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.victormattos.gestao_vagas.modules.company.entities.JobEntity;
import br.com.victormattos.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/job")
public class JobController {

  @Autowired
  private CreateJobUseCase createJobUseCase;

  @PostMapping("/")
  public ResponseEntity<JobEntity> create(@Valid @RequestBody JobEntity job) {
    var result = this.createJobUseCase.execute(job);
    return ResponseEntity.ok().body(result);
  }
}
