package es.deloitte.dc.meetup.restfull.payload;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.CollectionModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "countries")
public class Countries {
  
  private CollectionModel<CountryV1> countries;

}
