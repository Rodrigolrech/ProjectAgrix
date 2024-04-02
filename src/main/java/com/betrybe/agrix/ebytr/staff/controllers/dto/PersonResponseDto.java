package com.betrybe.agrix.ebytr.staff.controllers.dto;

import com.betrybe.agrix.ebytr.staff.security.Role;

/**
 * PersonResponseDto.
 *
 * @param id id.
 * @param username username.
 * @param role role.
 */
public record PersonResponseDto(Long id, String username, Role role) {

}
