package com.betrybe.agrix.ebytr.staff.entity;

import com.betrybe.agrix.ebytr.staff.controllers.dto.CropDto;
import com.betrybe.agrix.ebytr.staff.controllers.dto.CropResponseDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.List;

/**
 * Crop Model.
 */
@Entity
public class Crop {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;

  @ManyToOne(fetch = FetchType.EAGER)
  Farm farm;

  String name;
  @Column(name = "planted_area")
  double plantedArea;

  LocalDate plantedDate;
  LocalDate harvestDate;

  @ManyToMany
  List<Fertilizer> fertilizers;

  public Crop() {
  }

  /**
   * Crop Constructor.
   *
   * @param id Crop Id.
   * @param farm Farm.
   * @param name crop name.
   * @param plantedArea Crop planted area.
   */
  public Crop(Integer id, Farm farm, String name, double plantedArea,
      LocalDate plantedDate, LocalDate harvestDate) {
    this.id = id;
    this.farm = farm;
    this.name = name;
    this.plantedArea = plantedArea;
    this.plantedDate =  plantedDate;
    this.harvestDate = harvestDate;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Farm getFarm() {
    return farm;
  }

  public void setFarm(Farm farm) {
    this.farm = farm;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPlantedArea() {
    return plantedArea;
  }

  public void setPlantedArea(double plantedArea) {
    this.plantedArea = plantedArea;
  }

  public LocalDate getPlantedDate() {
    return plantedDate;
  }

  public void setPlantedDate(LocalDate plantedDate) {
    this.plantedDate = plantedDate;
  }

  public LocalDate getHarvestDate() {
    return harvestDate;
  }

  public void setHarvestDate(LocalDate harvestDate) {
    this.harvestDate = harvestDate;
  }

  public List<Fertilizer> getFertilizers() {
    return fertilizers;
  }

  public void setFertilizers(List<Fertilizer> fertilizers) {
    this.fertilizers = fertilizers;
  }

  public CropDto toCropDto() {
    return new CropDto(id, farm, name, plantedArea, plantedDate, harvestDate);
  }

  public CropResponseDto toCropResponseDto() {
    return new CropResponseDto(id, farm.getId(), name, plantedArea, plantedDate, harvestDate);
  }
}
