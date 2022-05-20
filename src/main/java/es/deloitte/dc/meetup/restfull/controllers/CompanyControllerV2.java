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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.deloitte.dc.meetup.restfull.model.Company;
import es.deloitte.dc.meetup.restfull.payload.CompaniesV2;
import es.deloitte.dc.meetup.restfull.payload.CompanyV2;
import es.deloitte.dc.meetup.restfull.services.CompanyService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController()
@RequestMapping("/v2/companies")
@Tag(name = "Company V2", description = "Company V2 Controller")
public class CompanyControllerV2 {
  
  @Autowired
  private CompanyService companyService;

  @GetMapping(
    produces = { "application/json", "application/xml" }
  )
  @ApiResponse(responseCode = "200")
  public ResponseEntity<CompaniesV2> getAllCompanies(@RequestParam("isMultiNational") Boolean isMultiNational) {
    List<Company> list;
    if (isMultiNational == null) {
      list = companyService.getAllCompanies();
    } else {
      list = companyService.getAllCompaniesByMulti(isMultiNational);
    }

    List<CompanyV2> vCompanyV2s = new ArrayList<>();

    for (Company c: list) {
      CompanyV2 c1 = new CompanyV2();
      BeanUtils.copyProperties(c, c1);
      vCompanyV2s.add(c1);
    }

    return ResponseEntity.ok(new CompaniesV2(vCompanyV2s));
  }

  @GetMapping(
    value = "/{id}", 
    produces = { "application/json", "application/xml" }
  )
  @ApiResponse(responseCode = "200")
  @ApiResponse(responseCode = "404")
  public ResponseEntity<CompanyV2> getCompanyById(@PathVariable("id") String companyId) {
    Company c = companyService.getCompany(companyId);

    if (c == null) {
      return ResponseEntity.notFound().build();
    }

    CompanyV2 c1 = new CompanyV2();
    BeanUtils.copyProperties(c, c1);

    return ResponseEntity.ok(c1);
  }

  @PostMapping(
    produces = { "application/json", "application/xml" },
    consumes = { "application/json", "application/xml" }
  )
  @ApiResponse(responseCode = "200")
  public ResponseEntity<CompanyV2> newCompany(@RequestBody CompanyV2 company) {
    Company c = new Company();
    BeanUtils.copyProperties(company, c);

    c = companyService.addCompany(c);

    CompanyV2 c1 = new CompanyV2();
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
  public ResponseEntity<Boolean> updateCompany(@PathVariable("id") String companyId, @RequestBody CompanyV2 company) {
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
