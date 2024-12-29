package com.parkinglot.service.impl;

import com.parkinglot.dao.AvailableSlot;
import com.parkinglot.entitys.ParkingVehicles;
import com.parkinglot.entitys.VehicleTypesAndSlots;
import com.parkinglot.enums.VehicleTypes;
import com.parkinglot.exceptions.CustomException;
import com.parkinglot.repository.VehicleParkingRepo;
import com.parkinglot.repository.VehicleTypesAndSlotsRepo;
import com.parkinglot.service.VehicleParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class VehicleParkingServiceImpl implements VehicleParkingService {

    @Autowired
    VehicleParkingRepo vehicleParkingRepo;

    @Autowired
    VehicleTypesAndSlotsRepo vehicleTypesAndSlotsRepo;


    @Override
    public List<ParkingVehicles> getAllParkingVehicles() {
        return vehicleParkingRepo.getAllVehicles();
    }

    @Override
    public List<AvailableSlot> getAvailableSlots() {
        List<AvailableSlot> dashBoardDTO = new ArrayList<>();
        AvailableSlot availableSlot = null;
        List<ParkingVehicles> availableSlots = vehicleParkingRepo.getAvailableSlots();

        List<String> carSlots = new ArrayList<>();
        List<String> busSlots = new ArrayList<>();
        List<String> truckSlots = new ArrayList<>();
        List<String> bikeSlots = new ArrayList<>();

        for (ParkingVehicles slot : availableSlots) {
            if (VehicleTypes.CAR.equals(slot.getVehicleTypes())) {
                carSlots.add(slot.getSlotNumber());
            }
            if (VehicleTypes.BUS.equals(slot.getVehicleTypes())) {
                busSlots.add(slot.getSlotNumber());
            }
            if (VehicleTypes.TRUCK.equals(slot.getVehicleTypes())) {
                truckSlots.add(slot.getSlotNumber());
            }
            if (VehicleTypes.BIKE.equals(slot.getVehicleTypes())) {
                bikeSlots.add(slot.getSlotNumber());
            }
        }
        System.out.println();
        List<VehicleTypesAndSlots> type = null;
        if (true) {
            type = vehicleTypesAndSlotsRepo.getByVehicleTypes(VehicleTypes.CAR);
            availableSlot = slots(type, carSlots);
            dashBoardDTO.add(availableSlot);
        }
        if (true) {
            type = vehicleTypesAndSlotsRepo.getByVehicleTypes(VehicleTypes.BUS);
            availableSlot = slots(type, busSlots);
            dashBoardDTO.add(availableSlot);
        }
        if (true) {
            type = vehicleTypesAndSlotsRepo.getByVehicleTypes(VehicleTypes.TRUCK);
            availableSlot = slots(type, truckSlots);
            dashBoardDTO.add(availableSlot);
        }
        if (true) {
            type = vehicleTypesAndSlotsRepo.getByVehicleTypes(VehicleTypes.BIKE);
            availableSlot = slots(type, bikeSlots);
            dashBoardDTO.add(availableSlot);
        }

        return dashBoardDTO;
    }

    public AvailableSlot slots(List<VehicleTypesAndSlots> type, List<String> slots) {
        String[] split = type.get(0).getSlotCodes().split(",");
        List<String> uniqueToArray = new ArrayList<>(Arrays.asList(split));
        uniqueToArray.removeAll(slots);
        AvailableSlot availableSlot = new AvailableSlot();
        availableSlot.setAvailableSlotCodes(uniqueToArray);
        availableSlot.setAvailableSlots(uniqueToArray.size());
        availableSlot.setTypeOfVehicle(type.get(0).getVehicleTypes().toString());
        availableSlot.setTotalSlots(type.get(0).getTotalSlots());
        if (uniqueToArray.isEmpty()) {
            availableSlot.setDiplayMessage("No Slots Available");
        } else {
            availableSlot.setDiplayMessage("Slots Available");
        }
        return availableSlot;
    }

    @Override
    public ParkingVehicles saveVehicleReg(ParkingVehicles slot) throws Exception {

        List<VehicleTypesAndSlots> byVehicleTypes = vehicleTypesAndSlotsRepo.getByVehicleTypes(slot.getVehicleTypes());

        List<String> typeBySlots = vehicleParkingRepo.getTypeBySlots(slot.getVehicleTypes());

        String[] split = byVehicleTypes.get(0).getSlotCodes().split(",");
        List<String> uniqueToArray = new ArrayList<>(Arrays.asList(split));
        uniqueToArray.removeAll(typeBySlots);
        if (uniqueToArray.isEmpty()) {
            throw new CustomException(200, "no slots available");
        }
        Optional<ParkingVehicles> parking = vehicleParkingRepo.checkVehicleAlredyParkOrNot(slot.getVehicleNumber(), slot.getVehicleTypes(), "PARKED");
        if (parking.isPresent()) {
            throw new CustomException(200, " Vehicle Already Parked at : " + parking.get());
        }
        slot.setSlotNumber(uniqueToArray.get(0));
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        slot.setVehicleIn(date);
        slot.setSlotStatus("PARKED");
        return vehicleParkingRepo.save(slot);
    }

    @Override
    public void leaveOnParkingVehicleReg(String slotCode) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        vehicleParkingRepo.leaveOnParkingVehicleReg(slotCode, date, "UN_PARKED");
    }

    @Override
    public List<ParkingVehicles> getAllVehiclesListWithStatus() {
        List<ParkingVehicles> all = vehicleParkingRepo.findAll();
        return all;
    }

    @Override
    public Optional<ParkingVehicles> getSlotInfo(String slotCode) {
        return vehicleParkingRepo.getSlotInfo(slotCode, "PARKED");
    }
    
}
