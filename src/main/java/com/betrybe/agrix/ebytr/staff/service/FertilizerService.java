package com.betrybe.agrix.ebytr.staff.service;


import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;
import com.betrybe.agrix.ebytr.staff.repository.FertilizerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Fertilizer Service.
 */
@Service
public class FertilizerService {

  @Autowired
  FertilizerRepository fertilizerRepository;

  public Fertilizer createNewFertilizer(Fertilizer fertilizer) {
    return fertilizerRepository.save(fertilizer);
  }

  public List<Fertilizer> getAllFertilizers() {
    return fertilizerRepository.findAll();
  }

  public Optional<Fertilizer> getFertilizerById(Integer id) {
    return fertilizerRepository.findById(id);
  }
}
