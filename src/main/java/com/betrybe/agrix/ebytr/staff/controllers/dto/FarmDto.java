package com.betrybe.agrix.ebytr.staff.controllers.dto;

import com.betrybe.agrix.ebytr.staff.entity.Farm;

/**
 * Farm DTO.
 *
 * @param id FarmDto ID.
 * @param name FarmDto name.
 * @param size FarmDto size.
 */
public record FarmDto(Integer id, String name, Double size) {

  public Farm toFarm() {
    return new Farm(id, name, size);
  }

}
