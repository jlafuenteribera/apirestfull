package es.deloitte.dc.meetup.restfull.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.deloitte.dc.meetup.restfull.models.CompanyV2;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController()
@RequestMapping("/v2/companies")
@Tag(name = "Company V2", description = "Company V2 Controller")
public class CompanyControllerV2 {
  
  @GetMapping(
    produces = { "application/json", "application/xml" }
  )
  public CompanyV2[] getAllCompanies(@RequestParam("isMultiNational") String isMultiNational) {
    return new CompanyV2[2];
  }

  @GetMapping(
    value = "/{id}", 
    produces = { "application/json", "application/xml" }
  )
  public CompanyV2 getCompanyById(@PathVariable("id") String companyId) {
    return new CompanyV2();
  }

  @PostMapping(
    produces = { "application/json", "application/xml" },
    consumes = { "application/json", "application/xml" }
  )
  public CompanyV2 newCompany(@RequestBody CompanyV2 company) {
    return new CompanyV2();
  }

  @PutMapping(
    value = "/{id}", 
    produces = { "application/json", "application/xml" },
    consumes = { "application/json", "application/xml" }
  )
  public CompanyV2 updateCompany(@PathVariable("id") String companyId, @RequestBody CompanyV2 company) {
    return new CompanyV2();
  }

  @DeleteMapping(
    value = "/{id}"
  )
  public ResponseEntity<?> getOsiTipById(@PathVariable("id") String companyId) {
    return ResponseEntity.notFound().build();
  }

}
