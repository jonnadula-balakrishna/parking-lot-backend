package com.parkinglot.entitys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.parkinglot.enums.VehicleTypes;
import jakarta.persistence.*;
import lombok.*;

import java.util.Calendar;
import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "VEHICLE_PARKING")
@JsonIgnoreProperties(value = {"fuelType","attribute","attribute1","attribute2","attribute3","attribute4","attribute5"})
public  class ParkingVehicles {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parking_reg_id")
    @SequenceGenerator(name = "parking_reg_id", sequenceName = "parking_vehicles_seq", allocationSize = 1)
    private Long parkingRegId;
    private String vehicleOwner;
    private String vehicleNumber;

    @Enumerated(value = EnumType.STRING)
    private VehicleTypes vehicleTypes;
    private String fuelType;
    private String slotNumber;
    private Date vehicleIn;
    private Date vehicleOut;
    private String slotStatus;

    public Long getParkingRegId() {
        return parkingRegId;
    }

    public void setParkingRegId(Long parkingRegId) {
        this.parkingRegId = parkingRegId;
    }

    public String getVehicleOwner() {
        return vehicleOwner;
    }

    public void setVehicleOwner(String vehicleOwner) {
        this.vehicleOwner = vehicleOwner;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public VehicleTypes getVehicleTypes() {
        return vehicleTypes;
    }

    public void setVehicleTypes(VehicleTypes vehicleTypes) {
        this.vehicleTypes = vehicleTypes;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(String slotNumber) {
        this.slotNumber = slotNumber;
    }

    public Date getVehicleIn() {
        return vehicleIn;
    }

    public void setVehicleIn(Date calendar) {
        this.vehicleIn = calendar;
    }

    public Date getVehicleOut() {
        return vehicleOut;
    }

    public void setVehicleOut(Date vehicleOut) {
        this.vehicleOut = vehicleOut;
    }

    public String getSlotStatus() {
        return slotStatus;
    }

    public void setSlotStatus(String slotStatus) {
        this.slotStatus = slotStatus;
    }
}
