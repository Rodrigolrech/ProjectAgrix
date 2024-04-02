package com.betrybe.agrix.ebytr.staff.controllers;


import com.betrybe.agrix.ebytr.staff.controllers.dto.FarmDto;
import com.betrybe.agrix.ebytr.staff.entity.Farm;
import com.betrybe.agrix.ebytr.staff.security.Role;
import com.betrybe.agrix.ebytr.staff.service.FarmService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Farm Controller.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {

  private final FarmService farmService;

  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  @PostMapping()
  public ResponseEntity<FarmDto> createNewFarm(@RequestBody FarmDto farmDto) {
    Farm farmCreated = farmService.insertNewFarm(farmDto.toFarm());
    return ResponseEntity.status(HttpStatus.CREATED).body(farmCreated.toFarmDto());
  }

  /**
   * Method to get all farms from db.
   *
   * @return List of farms.
   */
  @GetMapping
  @Secured({Role.ROLE_ADMIN, Role.ROLE_USER, Role.ROLE_MANAGER})
  public ResponseEntity<List<FarmDto>> getAllFarms() {
    List<Farm> farmList = farmService.getAllFarms();
    List<FarmDto> farmDtoList = new ArrayList<>();
    farmList.forEach(farm -> {
      FarmDto farmDto = farm.toFarmDto();
      farmDtoList.add(farmDto);
    });
    return ResponseEntity.ok(farmDtoList);
  }

  /**
   * Method to Get Farm By ID.
   *
   * @param id farm Id.
   * @return farmDto or not found.
   */
  @GetMapping("/{id}")
  public ResponseEntity<Object> getFarmById(@PathVariable Integer id) {
    Optional<Farm> farmOptional = farmService.getFarmById(id);

    if (farmOptional.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!");
    }

    return ResponseEntity.ok(farmOptional.get().toFarmDto());
  }

}
