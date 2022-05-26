package es.deloitte.dc.meetup.restfull.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
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
import es.deloitte.dc.meetup.restfull.model.Country;
import es.deloitte.dc.meetup.restfull.payload.CompaniesV2;
import es.deloitte.dc.meetup.restfull.payload.CompanyV2;
import es.deloitte.dc.meetup.restfull.payload.Countries;
import es.deloitte.dc.meetup.restfull.payload.CountryV1;
import es.deloitte.dc.meetup.restfull.services.CompanyService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController()
@RequestMapping("/v2/companies")
@Tag(name = "Company V2", description = "Company V2 Controller")
public class CompanyControllerV2 {

  @Autowired
  private CompanyService companyService;

  @GetMapping(produces = { "application/json", "application/xml" })
  @ApiResponse(responseCode = "200")
  public ResponseEntity<CompaniesV2> getAllCompanies(@RequestParam(required = false) Boolean isMultiNational) {
    List<Company> list;
    if (isMultiNational == null) {
      list = companyService.getAllCompanies();
    } else {
      list = companyService.getAllCompaniesByMulti(isMultiNational);
    }

    List<CompanyV2> vCompanyV2s = new ArrayList<>();

    for (Company c : list) {
      CompanyV2 c1 = new CompanyV2();
      BeanUtils.copyProperties(c, c1);
      Link selfLink = linkTo(CompanyControllerV2.class).slash(c1.getId()).withSelfRel();
      c1.add(selfLink);

      if (c.getCountries().size() > 0) {
        Link ordersLink = linkTo(methodOn(CompanyControllerV2.class)
            .getCountriesForCompany(c1.getId())).withRel("allCountries");
        c1.add(ordersLink);
      }

      vCompanyV2s.add(c1);
    }

    Link link = linkTo(CompanyControllerV2.class).withSelfRel();
    CollectionModel<CompanyV2> result = CollectionModel.of(vCompanyV2s, link);

    return ResponseEntity.ok(new CompaniesV2(result));
  }

  @GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
  @ApiResponse(responseCode = "200")
  @ApiResponse(responseCode = "404")
  public ResponseEntity<CompanyV2> getCompanyById(@PathVariable String id) {
    Company c = companyService.getCompany(id);

    if (c == null) {
      return ResponseEntity.notFound().build();
    }

    CompanyV2 c1 = new CompanyV2();
    BeanUtils.copyProperties(c, c1);

    Link selfLink = linkTo(CompanyControllerV2.class).slash(c1.getId()).withSelfRel();
    c1.add(selfLink);

    if (c.getCountries().size() > 0) {
      Link ordersLink = linkTo(methodOn(CompanyControllerV2.class)
          .getCountriesForCompany(c1.getId())).withRel("allCountries");
      c1.add(ordersLink);
    }

    return ResponseEntity.ok(c1);
  }

  @GetMapping(value = "/{id}/countries", produces = { "application/json", "application/xml" })
  @ApiResponse(responseCode = "200")
  public ResponseEntity<Countries> getCountriesForCompany(@PathVariable String id) {
    List<Country> countries = companyService.getCompanyCountries(id);
    List<CountryV1> countryV1s = new ArrayList<>();

    for (Country c : countries) {
      CountryV1 c1 = new CountryV1();
      BeanUtils.copyProperties(c, c1);
      Link selfLink = linkTo(methodOn(CountryController.class)
          .getCountryById(c.getId())).withSelfRel();
      c1.add(selfLink);

      countryV1s.add(c1);
    }

    Link link = linkTo(methodOn(CompanyControllerV2.class)
        .getCountriesForCompany(id)).withSelfRel();
    CollectionModel<CountryV1> result = CollectionModel.of(countryV1s, link);

    return ResponseEntity.ok(new Countries(result));
  }

  @PostMapping(produces = { "application/json", "application/xml" }, consumes = { "application/json",
      "application/xml" })
  @ApiResponse(responseCode = "200")
  public ResponseEntity<CompanyV2> newCompany(@RequestBody CompanyV2 company) {
    Company c = new Company();
    BeanUtils.copyProperties(company, c);

    c = companyService.addCompany(c);
    c.setCountries(new ArrayList<>());

    CompanyV2 c1 = new CompanyV2();
    BeanUtils.copyProperties(c, c1);

    Link selfLink = linkTo(CompanyControllerV2.class).slash(c1.getId()).withSelfRel();
    c1.add(selfLink);

    if (c.getCountries().size() > 0) {
      Link ordersLink = linkTo(methodOn(CompanyControllerV2.class)
          .getCountriesForCompany(c1.getId())).withRel("allCountries");
      c1.add(ordersLink);
    }

    return ResponseEntity.ok(c1);
  }

  @PutMapping(value = "/{id}", produces = { "application/json", "application/xml" }, consumes = { "application/json",
      "application/xml" })
  @ApiResponse(responseCode = "200")
  @ApiResponse(responseCode = "404")
  public ResponseEntity<Boolean> updateCompany(@PathVariable String id, @RequestBody CompanyV2 company) {
    Company c = new Company();
    BeanUtils.copyProperties(company, c);
    c.setCountries(new ArrayList<>());

    Boolean updated = companyService.updateCompany(c, id);

    if (!updated) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok().build();
  }

  @DeleteMapping(value = "/{id}")
  @ApiResponse(responseCode = "200")
  @ApiResponse(responseCode = "404")
  public ResponseEntity<Boolean> removeCompany(@PathVariable String id) {
    Boolean deleted = companyService.removeCompany(id);

    if (!deleted) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok().build();
  }

}
