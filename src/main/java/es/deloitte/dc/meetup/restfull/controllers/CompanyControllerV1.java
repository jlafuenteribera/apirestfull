package es.deloitte.dc.meetup.restfull.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.deloitte.dc.meetup.restfull.models.Company;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController()
@RequestMapping("/v1/companies")
public class CompanyControllerV1 {

  @GetMapping(
    produces = { "application/json", "application/xml" }
  )
  public Company[] getAllCompanies(@RequestParam("isMultiNational") String isMultiNational) {
    return new Company[2];
  }

  @GetMapping(
    value = "/{id}", 
    produces = { "application/json", "application/xml" }
  )
  public Company getCompanyById(@PathVariable("id") String companyId) {
    return new Company();
  }

  @PostMapping(
    produces = { "application/json", "application/xml" },
    consumes = { "application/json", "application/xml" }
  )
  public Company newCompany(@RequestBody Company company) {
    return new Company();
  }

  @PutMapping(
    value = "/{id}", 
    produces = { "application/json", "application/xml" },
    consumes = { "application/json", "application/xml" }
  )
  public Company updateCompany(@PathVariable("id") String companyId, @RequestBody Company company) {
    return new Company();
  }

  @DeleteMapping(
    value = "/{id}"
  )
  public ResponseEntity<?> getOsiTipById(@PathVariable("id") String companyId) {
    return ResponseEntity.notFound().build();
  }
}
