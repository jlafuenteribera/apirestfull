package es.deloitte.dc.meetup.restfull.model;

import java.util.List;

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
  private Boolean isMultinational;
  private List<Country> countries;

}
