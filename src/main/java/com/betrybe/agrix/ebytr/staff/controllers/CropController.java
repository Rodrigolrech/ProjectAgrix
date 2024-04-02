package com.betrybe.agrix.ebytr.staff.controllers;

import com.betrybe.agrix.ebytr.staff.controllers.dto.CropDto;
import com.betrybe.agrix.ebytr.staff.controllers.dto.CropResponseDto;
import com.betrybe.agrix.ebytr.staff.controllers.dto.ResponseDto;
import com.betrybe.agrix.ebytr.staff.entity.Crop;
import com.betrybe.agrix.ebytr.staff.entity.Farm;
import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;
import com.betrybe.agrix.ebytr.staff.security.Role;
import com.betrybe.agrix.ebytr.staff.service.CropService;
import com.betrybe.agrix.ebytr.staff.service.FarmService;
import com.betrybe.agrix.ebytr.staff.service.FertilizerService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Crop Controller.
 */
@RestController
@RequestMapping()
public class CropController {

  private final FarmService farmService;

  private final CropService cropService;

  private final FertilizerService fertilizerService;

  /**
   * CropController to autowired Beans.
   *
   * @param farmService Farm Service.
   * @param cropService Crop Service.
   * @param fertilizerService Fertilizer Service.
   */
  @Autowired
  public CropController(FarmService farmService, CropService cropService,
      FertilizerService fertilizerService) {
    this.farmService = farmService;
    this.cropService = cropService;
    this.fertilizerService = fertilizerService;
  }

  /**
   * Create new Crop to farm.
   *
   * @param farmId Farm Id that will be created the crop.
   * @param cropDto Crop received by request body.
   * @return created Crop.
   */
  @PostMapping("/farms/{farmId}/crops")
  public ResponseEntity<Object> createCropInFarm(@PathVariable Integer farmId,
      @RequestBody CropDto cropDto) {
    Optional<Farm> farmOptional = farmService.getFarmById(farmId);

    if (farmOptional.isEmpty()) {
      ResponseDto<Crop> responseDto = new ResponseDto<>("Fazenda não encontrada!", null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }

    Crop crop = cropDto.toCrop();
    crop.setFarm(farmOptional.get());
    Crop createdCrop = cropService.createCrop(crop);
    ResponseDto<Crop> responseDto = new ResponseDto<>("Crop criado com sucesso!", createdCrop);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdCrop.toCropResponseDto());
  }

  /**
   * Get all crops by Farm Id.
   *
   * @param farmId Farm Id.
   * @return List of Crops by Farm Id.
   */
  @GetMapping("/farms/{farmId}/crops")
  public ResponseEntity<Object> getAllCropsByFarmId(@PathVariable Integer farmId) {
    Optional<Farm> farmOptional = farmService.getFarmById(farmId);

    if (farmOptional.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!");
    }
    List<Crop> cropList = cropService.findAllCropsByFarmId(farmId);
    return ResponseEntity.ok(cropList.stream().map(crop -> new CropResponseDto(crop.getId(),
        crop.getFarm().getId(), crop.getName(), crop.getPlantedArea(), crop.getPlantedDate(),
            crop.getHarvestDate()))
        .collect(Collectors.toList()));
  }

  /**
   * Get all crops.
   *
   * @return List of all the crops.
   */
  @GetMapping("/crops")
  @Secured({Role.ROLE_ADMIN, Role.ROLE_MANAGER})
  public ResponseEntity<List<CropResponseDto>> getAllCrops() {
    List<Crop> cropList = cropService.getAllCrops();
    return ResponseEntity.ok(cropList.stream().map(crop ->
        new CropResponseDto(crop.getId(), crop.getFarm().getId(), crop.getName(),
            crop.getPlantedArea(), crop.getPlantedDate(), crop.getHarvestDate()))
        .collect(Collectors.toList()));
  }

  /**
   * Get Crop By ID.
   *
   * @param id crop Id.
   * @return Crop.
   */
  @GetMapping("/crops/{id}")
  public ResponseEntity<Object> getCropById(@PathVariable Integer id) {
    Optional<Crop> cropOptional = cropService.getCropById(id);

    if (cropOptional.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plantação não encontrada!");
    }
    return ResponseEntity.ok(cropOptional.get().toCropResponseDto());
  }

  /**
   * Get Crop By Harvest Date between Dates.
   *
   * @param startDate Start Date.
   * @param endDate End Date.
   * @return List of crops.
   */
  @GetMapping("/crops/search")
  public ResponseEntity<List<CropResponseDto>> getCropByDates(
      @RequestParam("start")LocalDate startDate,
      @RequestParam("end") LocalDate endDate) {
    List<Crop> crops = cropService.getCropByHarvestDateBetweenDates(startDate, endDate);
    return ResponseEntity.ok(crops.stream().map(crop ->
            new CropResponseDto(crop.getId(), crop.getFarm().getId(), crop.getName(),
                crop.getPlantedArea(), crop.getPlantedDate(), crop.getHarvestDate()))
        .collect(Collectors.toList()));
  }

  /**
   * PostMapping to add Fertilizer to Crop.
   *
   * @param cropId Crop Id.
   * @param fertilizerId Fertilizer Id.
   * @return return if it was or not possible to associate it.
   */
  @PostMapping("/crops/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> addFertilizerToCrop(@PathVariable Integer cropId,
      @PathVariable Integer fertilizerId) {
    Optional<Crop> cropOptional = cropService.getCropById(cropId);
    if (cropOptional.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plantação não encontrada!");
    }
    Optional<Fertilizer> fertilizerOptional = fertilizerService.getFertilizerById(fertilizerId);
    if (fertilizerOptional.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fertilizante não encontrado!");
    }
    try {
      Crop cropUpdated = cropService.associateCropWithFertilizer(cropOptional.get(),
          fertilizerOptional.get());
      return ResponseEntity.status(HttpStatus.CREATED)
          .body("Fertilizante e plantação associados com sucesso!");
    } catch (RuntimeException re) {
      return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null);
    }
  }

  /**
   * Get Fertilizers by crop id.
   *
   * @param cropId crop id.
   * @return fertilizers.
   */
  @GetMapping("/crops/{cropId}/fertilizers")
  public ResponseEntity<Object> getFertilizerByCrop(@PathVariable Integer cropId) {
    Optional<Crop> cropOptional = cropService.getCropById(cropId);
    if (cropOptional.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(new ResponseDto("Plantação não encontrada!", null));
    }
    return ResponseEntity.ok(cropOptional.get().getFertilizers().stream()
        .map(fert -> fert.toFertilizerDto()).collect(Collectors.toList()));

  }

}
