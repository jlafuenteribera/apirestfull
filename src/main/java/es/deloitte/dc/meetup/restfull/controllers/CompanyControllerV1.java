package es.deloitte.dc.meetup.restfull.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.deloitte.dc.meetup.restfull.model.Company;
import es.deloitte.dc.meetup.restfull.payload.CompaniesV1;
import es.deloitte.dc.meetup.restfull.payload.CompanyV1;
import es.deloitte.dc.meetup.restfull.services.CompanyService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController()
@RequestMapping("/v1/companies")
@Tag(name = "Company V1", description = "Company V1 Controller")
public class CompanyControllerV1 {

  @Autowired
  private CompanyService companyService;

  @GetMapping(
    produces = { "application/json", "application/xml" }
  )
  @ApiResponse(responseCode = "200")
  public ResponseEntity<CompaniesV1> getAllCompanies(@RequestParam("isMultiNational") Boolean isMultiNational) {
    List<Company> list;
    if (isMultiNational == null) {
      list = companyService.getAllCompanies();
    } else {
      list = companyService.getAllCompaniesByMulti(isMultiNational);
    }

    List<CompanyV1> vCompanyV1s = new ArrayList<>();

    for (Company c: list) {
      CompanyV1 c1 = new CompanyV1();
      BeanUtils.copyProperties(c, c1);
      vCompanyV1s.add(c1);
    }

    return ResponseEntity.ok(new CompaniesV1(vCompanyV1s));
  }

  @GetMapping(
    value = "/{id}", 
    produces = { "application/json", "application/xml" }
  )
  @ApiResponse(responseCode = "200")
  @ApiResponse(responseCode = "404")
  public ResponseEntity<CompanyV1> getCompanyById(@PathVariable("id") String companyId) {
    Company c = companyService.getCompany(companyId);

    if (c == null) {
      return ResponseEntity.notFound().build();
    }

    CompanyV1 c1 = new CompanyV1();
    BeanUtils.copyProperties(c, c1);

    return ResponseEntity.ok(c1);
  }

  @PostMapping(
    produces = { "application/json", "application/xml" },
    consumes = { "application/json", "application/xml" }
  )
  @ApiResponse(responseCode = "200")
  public ResponseEntity<CompanyV1> newCompany(@RequestBody CompanyV1 company) {
    Company c = new Company();
    BeanUtils.copyProperties(company, c);

    c = companyService.addCompany(c);

    CompanyV1 c1 = new CompanyV1();
    BeanUtils.copyProperties(c, c1);

    return ResponseEntity.ok(c1);
  }

  @PutMapping(
    value = "/{id}", 
    produces = { "application/json", "application/xml" },
    consumes = { "application/json", "application/xml" }
  )
  @ApiResponse(responseCode = "200")
  @ApiResponse(responseCode = "404")
  public ResponseEntity<Boolean> updateCompany(@PathVariable("id") String companyId, @RequestBody CompanyV1 company) {
    Company c = new Company();
    BeanUtils.copyProperties(company, c);

    Boolean updated = companyService.updateCompany(c, companyId);

    if (!updated) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok().build();
  }

  @DeleteMapping(
    value = "/{id}"
  )
  @ApiResponse(responseCode = "200")
  @ApiResponse(responseCode = "404")
  public ResponseEntity<Boolean> getOsiTipById(@PathVariable("id") String companyId) {
    Boolean deleted = companyService.removeCompany(companyId);

    if (!deleted) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok().build();
  }

}
