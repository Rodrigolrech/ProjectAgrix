package com.betrybe.agrix.ebytr.staff.controllers.dto;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.security.Role;

/**
 * PersonDto.
 *
 * @param username username.
 * @param password password.
 * @param role role.
 */
public record PersonDto(String username, String password, Role role) {

  public Person toPerson() {
    return new Person(username, password, role);
  }
}
