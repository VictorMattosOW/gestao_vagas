package br.com.victormattos.gestao_vagas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.victormattos.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.victormattos.gestao_vagas.modules.company.repositories.CompanyRepository;
import br.com.victormattos.gestao_vagas.modules.company.useCases.CreateCompanyUseCase;

public class CreateCompanyUseCaseTest {

  @Autowired
  @InjectMocks
  private CreateCompanyUseCase companyUseCase;

  @Mock
  private CompanyRepository companyRepository;

  @Mock
  private PasswordEncoder passwordEncoder;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @DisplayName("Should create company successfully when everything is OK")
  void createCompanyUseCase() throws Exception {
    CompanyEntity inputCompany = new CompanyEntity();
    inputCompany.setUsername("newUsername");
    inputCompany.setEmail("newEmail@example.com");
    inputCompany.setName("victor");
    inputCompany.setWebsite("victor");
    inputCompany.setDescription("victor");

    inputCompany.setPassword("password");
    when(companyRepository.findByUsernameOrEmail("newUsername", "newEmail@example.com")).thenReturn(Optional.empty());
    when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
    when(companyRepository.save(inputCompany)).thenReturn(inputCompany);
    CompanyEntity resultCompany = companyUseCase.execute(inputCompany);
    verify(companyRepository, times(1)).findByUsernameOrEmail("newUsername", "newEmail@example.com");
    verify(passwordEncoder, times(1)).encode("password");
    verify(companyRepository, times(1)).save(inputCompany);

    assertEquals("encodedPassword", resultCompany.getPassword());
  }

}
