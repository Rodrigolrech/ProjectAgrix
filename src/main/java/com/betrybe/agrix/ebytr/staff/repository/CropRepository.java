package com.betrybe.agrix.ebytr.staff.repository;

import com.betrybe.agrix.ebytr.staff.entity.Crop;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Crop Repository.
 */
@Repository
public interface CropRepository extends JpaRepository<Crop, Integer> {
  List<Crop> findByFarmId(Integer farmId);

  @Query("SELECT c FROM Crop c WHERE c.harvestDate BETWEEN :startDate AND :endDate ")
  List<Crop> findCropByHarvestDateBetweenDates(@Param("startDate")LocalDate startDate,
      @Param("endDate") LocalDate endDate);
}
