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

import es.deloitte.dc.meetup.restfull.models.CompanyV1;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController()
@RequestMapping("/v1/companies")
@Tag(name = "Company V1", description = "Company V1 Controller")
public class CompanyControllerV1 {

  @GetMapping(
    produces = { "application/json", "application/xml" }
  )
  public CompanyV1[] getAllCompanies(@RequestParam("isMultiNational") String isMultiNational) {
    return new CompanyV1[2];
  }

  @GetMapping(
    value = "/{id}", 
    produces = { "application/json", "application/xml" }
  )
  public CompanyV1 getCompanyById(@PathVariable("id") String companyId) {
    return new CompanyV1();
  }

  @PostMapping(
    produces = { "application/json", "application/xml" },
    consumes = { "application/json", "application/xml" }
  )
  public CompanyV1 newCompany(@RequestBody CompanyV1 company) {
    return new CompanyV1();
  }

  @PutMapping(
    value = "/{id}", 
    produces = { "application/json", "application/xml" },
    consumes = { "application/json", "application/xml" }
  )
  public CompanyV1 updateCompany(@PathVariable("id") String companyId, @RequestBody CompanyV1 company) {
    return new CompanyV1();
  }

  @DeleteMapping(
    value = "/{id}"
  )
  public ResponseEntity<?> getOsiTipById(@PathVariable("id") String companyId) {
    return ResponseEntity.notFound().build();
  }

}
