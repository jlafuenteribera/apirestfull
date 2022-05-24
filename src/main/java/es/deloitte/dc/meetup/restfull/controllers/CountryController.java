package es.deloitte.dc.meetup.restfull.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.deloitte.dc.meetup.restfull.model.Country;
import es.deloitte.dc.meetup.restfull.payload.Countries;
import es.deloitte.dc.meetup.restfull.payload.CountryV1;
import es.deloitte.dc.meetup.restfull.services.CountryService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController()
@RequestMapping("/v1/countries")
@Tag(name = "Country V1", description = "Country V1 Controller")
public class CountryController {

  @Autowired
  private CountryService countryService;

  @GetMapping(produces = { "application/json", "application/xml" })
  @ApiResponse(responseCode = "200")
  public ResponseEntity<Countries> getAllCompanies() {
    List<Country> countries = countryService.findAll();
    List<CountryV1> countryV1s = new ArrayList<>();

    for (Country c : countries) {
      CountryV1 c1 = new CountryV1();
      BeanUtils.copyProperties(c, c1);
      Link selfLink = linkTo(CountryController.class).slash(c1.getId()).withSelfRel();
      c1.add(selfLink);

      countryV1s.add(c1);
    }

    Link link = linkTo(CountryController.class).withSelfRel();
    CollectionModel<CountryV1> result = CollectionModel.of(countryV1s, link);

    return ResponseEntity.ok(new Countries(result));
  }

  @GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
  @ApiResponse(responseCode = "200")
  @ApiResponse(responseCode = "404")
  public ResponseEntity<CountryV1> getCountryById(@PathVariable String id) {
    Country c = countryService.findById(id);

    if (c == null) {
      return ResponseEntity.notFound().build();
    }

    CountryV1 c1 = new CountryV1();
    BeanUtils.copyProperties(c, c1);

    Link selfLink = linkTo(CountryController.class).slash(c1.getId()).withSelfRel();
    c1.add(selfLink);

    return ResponseEntity.ok(c1);
  }
}
