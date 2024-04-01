package com.betrybe.agrix.ebytr.staff.controllers;


import com.betrybe.agrix.ebytr.staff.controllers.dto.FertilizerDto;
import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;
import com.betrybe.agrix.ebytr.staff.service.FertilizerService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Fertilizer Controller.
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {

  @Autowired
  FertilizerService fertilizerService;

  /**
   * Post Mapping para a criação de um novo Fertilizer.
   *
   * @param fertilizerDto FertilizerDTO.
   * @return created Fertilizer
   */
  @PostMapping()
  public ResponseEntity<FertilizerDto> createNewFertilizer(
      @RequestBody FertilizerDto fertilizerDto) {
    Fertilizer fertilizerCreated = fertilizerService
        .createNewFertilizer(fertilizerDto.toFertilizer());
    return ResponseEntity.status(HttpStatus.CREATED).body(fertilizerCreated.toFertilizerDto());
  }

  /**
   * Get Mapping to get all Fertilizers.
   *
   * @return List of all Fertilizers.
   */
  @GetMapping()
  public ResponseEntity<List<FertilizerDto>> getAllFertilizers() {
    List<Fertilizer> fertilizers = fertilizerService.getAllFertilizers();
    return ResponseEntity.ok(fertilizers.stream().map(fert -> fert.toFertilizerDto())
        .collect(Collectors.toList()));
  }

  /**
   * Get Fertilizer By Id.
   *
   * @param id Fertilizer Id.
   * @return Fertilizer.
   */
  @GetMapping("/{id}")
  public ResponseEntity<Object> getFertilizerById(@PathVariable Integer id) {
    Optional<Fertilizer> fertilizerOptional = fertilizerService.getFertilizerById(id);

    if (fertilizerOptional.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fertilizante não encontrado!");
    }
    return ResponseEntity.ok(fertilizerOptional.get().toFertilizerDto());
  }
}
