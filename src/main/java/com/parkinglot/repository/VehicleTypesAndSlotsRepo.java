package com.parkinglot.repository;

import com.parkinglot.entitys.VehicleTypesAndSlots;
import com.parkinglot.enums.VehicleTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleTypesAndSlotsRepo extends JpaRepository<VehicleTypesAndSlots,Long> {

    List<VehicleTypesAndSlots>  getByVehicleTypes(VehicleTypes vehicleTypes);

    @Query( " select a from VehicleTypesAndSlots a order by vehicleTypeId  desc limit 1")
    List<VehicleTypesAndSlots> getByVehicleTypeIdOrderByDesc();
	
}
