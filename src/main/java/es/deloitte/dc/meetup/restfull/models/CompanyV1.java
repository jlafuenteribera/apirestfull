package es.deloitte.dc.meetup.restfull.models;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class CompanyV1 {
  
  private String id;
  private String name;
  private String size;
  private String isMultinational;

}
