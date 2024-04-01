package com.betrybe.agrix.ebytr.staff.controllers.dto;

import java.time.LocalDate;

/**
 * Crop Response DTO.
 *
 * @param id Crop Id.
 * @param farmId Farm Id.
 * @param name crop name.
 * @param plantedArea Crop planted area.
 */
public record CropResponseDto(Integer id, Integer farmId, String name, double plantedArea,
                              LocalDate plantedDate, LocalDate harvestDate) {

}
