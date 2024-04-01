package com.betrybe.agrix.ebytr.staff.controllers.dto;

import com.betrybe.agrix.ebytr.staff.entity.Crop;
import com.betrybe.agrix.ebytr.staff.entity.Farm;
import java.time.LocalDate;

/**
 * CropDTO.
 *
 * @param id Crop Id.
 * @param farm Farm.
 * @param name crop name.
 * @param plantedArea Crop planted area.
 */
public record CropDto(Integer id, Farm farm, String name, double plantedArea,
                      LocalDate plantedDate, LocalDate harvestDate) {

  public Crop toCrop() {
    return new Crop(id, farm, name, plantedArea, plantedDate, harvestDate);
  }

}
