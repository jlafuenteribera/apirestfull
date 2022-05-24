package es.deloitte.dc.meetup.restfull.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import es.deloitte.dc.meetup.restfull.model.Company;

@Repository
public class CompanyRepository {

  private static ArrayList<Company> companies = new ArrayList<>(List.of(
      new Company("1", "Company 1", "M", false, List.of()),
      new Company("2", "Company 2", "S", true,
          List.of(CountryRepository.findById("1").get(), CountryRepository.findById("5").get())),
      new Company("3", "Company 3", "XS", false, List.of()),
      new Company("4", "Company 4", "L", false, List.of()),
      new Company("5", "Company 5", "XL", true,
          List.of(CountryRepository.findById("2").get(), CountryRepository.findById("4").get())),
      new Company("6", "Company 6", "XL", true,
          List.of(CountryRepository.findById("3").get(), CountryRepository.findById("3").get())),
      new Company("7", "Company 7", "S", false, List.of()),
      new Company("8", "Company 8", "M", true,
          List.of(CountryRepository.findById("4").get(), CountryRepository.findById("2").get())),
      new Company("9", "Company 9", "XS", false, List.of()),
      new Company("10", "Company 10", "XS", false, List.of())));

  public Optional<Company> findById(String id) {
    return companies.stream()
        .filter(p -> id.equals(p.getId())).findFirst();
  }

  public List<Company> findAll() {
    return companies;
  }

  public Boolean remove(String id) {
    return companies.removeIf(p -> id.equals(p.getId()));
  }

  public List<Company> getAllCompaniesByIsMultiNational(Boolean isMultiNational) {
    return companies.stream()
        .filter(p -> isMultiNational.equals(p.getIsMultinational())).collect(Collectors.toList());
  }

  public Company save(Company company) {
    company.setId(UUID.randomUUID().toString());
    companies.add(company);
    return company;
  }

  public Boolean updateCompany(Company company, String id) {
    company.setId(id);
    Company old = companies.stream()
        .filter(p -> id.equals(p.getId())).findFirst().orElse(null);

    if (old != null) {
      companies.removeIf(p -> id.equals(p.getId()));
      companies.add(company);
      return true;
    }

    return false;
  }

}
