package br.com.victormattos.gestao_vagas.modules.company.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.victormattos.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.victormattos.gestao_vagas.modules.company.entities.JobEntity;
import br.com.victormattos.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/company/job")
public class JobController {

  @Autowired
  private CreateJobUseCase createJobUseCase;

  @PostMapping("/")
  @PreAuthorize("hasRole('COMPANY')")
  public ResponseEntity<JobEntity> create(@Valid @RequestBody CreateJobDTO jobDTO, HttpServletRequest request) {
    var companyId = request.getAttribute("company_id");
    var jobEntity = JobEntity.builder()
        .companyId(UUID.fromString(companyId.toString()))
        .benefits(jobDTO.benefits())
        .description(jobDTO.description())
        .level(jobDTO.level())
        .build();

    var result = this.createJobUseCase.execute(jobEntity);
    return ResponseEntity.ok().body(result);
  }
}
