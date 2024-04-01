package com.betrybe.agrix.ebytr.staff.service;

import com.betrybe.agrix.ebytr.staff.entity.Crop;
import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;
import com.betrybe.agrix.ebytr.staff.repository.CropRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Crop Service.
 */
@Service
public class CropService {

  private final CropRepository cropRepository;

  public CropService(CropRepository cropRepository) {
    this.cropRepository = cropRepository;
  }

  public Crop createCrop(Crop crop) {
    return cropRepository.save(crop);
  }

  public List<Crop> findAllCropsByFarmId(Integer farmId) {
    return cropRepository.findByFarmId(farmId);
  }

  public List<Crop> getAllCrops() {
    return cropRepository.findAll();
  }

  public Optional<Crop> getCropById(Integer id) {
    return cropRepository.findById(id);
  }

  public List<Crop> getCropByHarvestDateBetweenDates(LocalDate startDate, LocalDate endDate) {
    return cropRepository.findCropByHarvestDateBetweenDates(startDate, endDate);
  }

  @Transactional
  public Crop associateCropWithFertilizer(Crop crop, Fertilizer fertilizer) {
    crop.getFertilizers().add(fertilizer);
    return cropRepository.save(crop);
  }

}
