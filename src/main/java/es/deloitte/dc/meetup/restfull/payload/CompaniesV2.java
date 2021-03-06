package es.deloitte.dc.meetup.restfull.payload;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.CollectionModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@XmlRootElement(name= "companies")
public class CompaniesV2 {

  private CollectionModel<CompanyV2> companies;

}
