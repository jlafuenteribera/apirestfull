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
@XmlRootElement(name = "country")
public class CountryV1 extends RepresentationModel<CountryV1> {
  
  private String id;
  private String name;
}
