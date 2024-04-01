package com.betrybe.agrix.ebytr.staff.entity;


import com.betrybe.agrix.ebytr.staff.controllers.dto.FarmDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

/**
 * Farm Model.
 */
@Entity
public class Farm {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;
  String name;
  Double size;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "farm")
  @JsonIgnore
  List<Crop> cropList;


  /**
   * Farm constructor.
   */
  public Farm() {
  }

  /**
   * Farm constructor.
   */
  public Farm(Integer id, String name, Double size, List<Crop> cropList) {
    this.id = id;
    this.name = name;
    this.size = size;
    this.cropList = cropList;
  }

  /**
   * Farm constructor.
   *
   * @param id farmId.
   * @param name farm name.
   * @param size farm size.
   */
  public Farm(Integer id, String name, Double size) {
    this.id = id;
    this.name = name;
    this.size = size;
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

  public Double getSize() {
    return size;
  }

  public void setSize(Double size) {
    this.size = size;
  }

  public FarmDto toFarmDto() {
    return new FarmDto(id, name, size);
  }
}
