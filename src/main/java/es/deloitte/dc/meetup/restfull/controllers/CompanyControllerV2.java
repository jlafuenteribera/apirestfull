package es.deloitte.dc.meetup.restfull.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.deloitte.dc.meetup.restfull.models.Company;

@RestController()
@RequestMapping("/v2/companies")
public class CompanyControllerV2 {
  
  @GetMapping()
  public Company[] getAllCompanies() {
    return new Company[2];
  }
}
