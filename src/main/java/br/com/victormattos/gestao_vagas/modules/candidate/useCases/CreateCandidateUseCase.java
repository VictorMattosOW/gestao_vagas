package br.com.victormattos.gestao_vagas.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.victormattos.gestao_vagas.exceptions.UserFoundException;
import br.com.victormattos.gestao_vagas.modules.candidate.Entity.CandidateEntity;
import br.com.victormattos.gestao_vagas.modules.candidate.Repository.CandidateRepository;

@Service
public class CreateCandidateUseCase {

  @Autowired
  private CandidateRepository candidateRepository;

  public CandidateEntity execute(CandidateEntity candidate) {
    this.candidateRepository.findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
        .ifPresent((user) -> {
          throw new UserFoundException();
        });
    return this.candidateRepository.save(candidate);
  }
}
