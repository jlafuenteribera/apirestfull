package es.deloitte.dc.meetup.restfull.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.deloitte.dc.meetup.restfull.repositories.CompanyRepository;
import es.deloitte.dc.meetup.restfull.repositories.CountryRepository;

@Configuration
public class CustomBeansConfig {
    
  @Bean
  public CompanyRepository createCompanyRepository() {
    return new CompanyRepository();
  }

  @Bean
  public CountryRepository createCountryRepository() {
    return new CountryRepository();
  }

}
