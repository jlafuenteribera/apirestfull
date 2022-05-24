package es.deloitte.dc.meetup.restfull.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import es.deloitte.dc.meetup.restfull.model.Country;

public class CountryRepository {

  private static ArrayList<Country> countries = new ArrayList<>(List.of(
      new Country("1", "Alemania"),
      new Country("2", "Francia"),
      new Country("3", "Italia"),
      new Country("4", "Reino Unido"),
      new Country("5", "España")));

  public static Optional<Country> findById(String id) {
    return countries.stream()
        .filter(p -> id.equals(p.getId())).findFirst();
  }

  public static List<Country> findAll() {
    return countries;
  }

}
