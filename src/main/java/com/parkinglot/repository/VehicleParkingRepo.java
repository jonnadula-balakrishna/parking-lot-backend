package com.parkinglot.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.parkinglot.entitys.ParkingVehicles;
import com.parkinglot.enums.VehicleTypes;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface VehicleParkingRepo extends JpaRepository<ParkingVehicles, Long> {

    @Query("select a from ParkingVehicles a where a.slotStatus = 'PARKED'  order by parkingRegId desc")
    List<ParkingVehicles> getAllVehicles();

    @Query("select a from ParkingVehicles a where a.slotStatus in ('PARKED')  order by vehicleTypes asc ")
    List<ParkingVehicles> getAvailableSlots();

    @Query("select a.slotNumber from ParkingVehicles a where a.slotStatus in ('PARKED') and a.vehicleTypes =:vehicleTypes  order by slotNumber asc ")
    List<String> getTypeBySlots(VehicleTypes vehicleTypes);

    @Transactional
    @Modifying
    @Query("update ParkingVehicles a set a.vehicleOut =:date ,a.slotStatus =:unParking  where a.slotNumber=:slotNumber ")
    void leaveOnParkingVehicleReg(String slotNumber, Date date, String unParking);

    @Query(value = "select a.slotNumber from ParkingVehicles a where a.slotStatus =:parking and a.vehicleTypes =:vehicleTypes   and a.vehicleNumber =:vehicleNumber order by vehicleIn desc limit 1")
    Optional<ParkingVehicles> checkVehicleAlredyParkOrNot(String vehicleNumber, VehicleTypes vehicleTypes, String parking);

    @Query("select a from ParkingVehicles a where a.slotStatus =:status and a.slotNumber =:slotCode ")
    Optional<ParkingVehicles>  getSlotInfo( String slotCode, String status);

}