package com.betrybe.agrix.ebytr.staff.controllers.dto;

import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;

/**
 * Fertilizer Dto.
 *
 * @param id Fertilizer Id.
 * @param name Fertilizer name.
 * @param brand Fertilizer brand.
 * @param composition Fertilizer composition.
 */
public record FertilizerDto(Integer id, String name, String brand, String composition) {

  public Fertilizer toFertilizer() {
    return new Fertilizer(id, name, brand, composition);
  }
}
