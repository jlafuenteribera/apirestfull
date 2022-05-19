package es.deloitte.dc.meetup.restfull.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company {
  
  private String id;
  private String name;
  private String size;
  private String isMultinational;

}
