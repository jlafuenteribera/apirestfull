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
public class CompaniesV2 {
  private List<CompanyV2> companies;
}
