package es.deloitte.dc.meetup.restfull.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import es.deloitte.dc.meetup.restfull.model.Company;

@Service
public class CompanyService {

  private static ArrayList<Company> companies = new ArrayList<>(List.of(
      new Company("1", "Company 1", "M", false),
      new Company("2", "Company 2", "S", true),
      new Company("3", "Company 3", "XS", false),
      new Company("4", "Company 4", "L", false),
      new Company("5", "Company 5", "XL", true),
      new Company("6", "Company 6", "XL", true),
      new Company("7", "Company 7", "S", false),
      new Company("8", "Company 8", "M", true),
      new Company("9", "Company 9", "XS", false),
      new Company("10", "Company 10", "XS", false)));

  public List<Company> getAllCompanies() {
    return companies;
  }

  public List<Company> getAllCompaniesByMulti(Boolean isMultiNational) {
    return companies.stream()
        .filter(p -> isMultiNational.equals(p.getIsMultinational())).collect(Collectors.toList());
  }

  public Boolean removeCompany(String id) {
    return companies.removeIf(p -> id.equals(p.getId()));
  }

  public Company getCompany(String id) {
    return companies.stream()
        .filter(p -> id.equals(p.getId())).findFirst().orElse(null);
  }

  public Company addCompany(Company company) {
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
