package es.deloitte.dc.meetup.restfull.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.deloitte.dc.meetup.restfull.model.Country;
import es.deloitte.dc.meetup.restfull.repositories.CountryRepository;

@Service
public class CountryService {
  
  @Autowired
  private CountryRepository countryRepository;

  public Country findById(String id) {
    return countryRepository.findById(id).orElse(null);
  }

  public List<Country> findAll() {
    return countryRepository.findAll();
  }

}
