package br.com.victormattos.gestao_vagas.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.victormattos.gestao_vagas.modules.candidate.Entity.CandidateEntity;
import br.com.victormattos.gestao_vagas.modules.candidate.Repository.CandidateRepository;
import br.com.victormattos.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;

public class ProfileCandidateUseCase {

  @Autowired
  private CandidateRepository candidateRepository;

  public ProfileCandidateResponseDTO execute(UUID idCandidate) {
    var candidate = this.candidateRepository.findById(idCandidate).orElseThrow(
        () -> {
          throw new UsernameNotFoundException("User not found.");
        });
    var candidateDTO = new ProfileCandidateResponseDTO(candidate.getName(), candidate.getEmail(), candidate.getUsername(),
        candidate.getCurriculum(), candidate.getDescription());
    return candidateDTO;
  }

}
