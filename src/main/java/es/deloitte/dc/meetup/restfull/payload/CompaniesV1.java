package es.deloitte.dc.meetup.restfull.payload;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@XmlRootElement(name= "companies")
public class CompaniesV1 {
  private List<CompanyV1> companies;
}
