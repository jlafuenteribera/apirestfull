package es.deloitte.dc.meetup.restfull.payload;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "company")
public class CompanyV2 extends RepresentationModel<CompanyV2> {
  
  private String id;
  private String name;
  private String size;
  private Boolean isMultinational;

}
