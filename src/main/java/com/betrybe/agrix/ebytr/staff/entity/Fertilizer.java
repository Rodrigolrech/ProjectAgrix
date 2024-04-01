package com.betrybe.agrix.ebytr.staff.entity;

import com.betrybe.agrix.ebytr.staff.controllers.dto.FertilizerDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.List;

/**
 * Fertilizer Entity.
 */
@Entity
public class Fertilizer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;

  String name;
  String composition;

  String brand;

  @ManyToMany
  List<Crop> crops;

  public Fertilizer() {
  }

  /**
   * Fetilizer Constructor.
   *
   * @param id Fetilizer id.
   * @param name Fetilizer name.
   * @param brand Fetilizer brand.
   * @param composition Fetilizer composition.
   */
  public Fertilizer(Integer id, String name, String brand, String composition) {
    this.id = id;
    this.name = name;
    this.brand = brand;
    this.composition = composition;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getComposition() {
    return composition;
  }

  public void setComposition(String composition) {
    this.composition = composition;
  }

  public FertilizerDto toFertilizerDto() {
    return new FertilizerDto(id, name, brand, composition);
  }
}
