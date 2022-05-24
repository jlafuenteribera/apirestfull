package es.deloitte.dc.meetup.restfull.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.deloitte.dc.meetup.restfull.model.Company;
import es.deloitte.dc.meetup.restfull.model.Country;
import es.deloitte.dc.meetup.restfull.repositories.CompanyRepository;

@Service
public class CompanyService {

  @Autowired
  private CompanyRepository companyRepository;

  public List<Company> getAllCompanies() {
    return companyRepository.findAll();
  }

  public List<Company> getAllCompaniesByMulti(Boolean isMultiNational) {
    return companyRepository.getAllCompaniesByIsMultiNational(isMultiNational);
  }

  public Boolean removeCompany(String id) {
    return companyRepository.remove(id);
  }

  public Company getCompany(String id) {
    return companyRepository.findById(id).orElse(null);
  }

  public Company addCompany(Company company) {
    return companyRepository.save(company);
  }

  public Boolean updateCompany(Company company, String id) {
    return companyRepository.updateCompany(company, id);
  }

  public List<Country> getCompanyCountries(String id) {
    return this.getCompany(id).getCountries();
  }

}
