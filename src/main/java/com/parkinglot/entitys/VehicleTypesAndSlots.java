package com.parkinglot.entitys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.parkinglot.enums.VehicleTypes;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Builder
@Table(name = "VEHICLE_TYPES_SLOTS")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"attribute","attribute1","attribute2","attribute3","attribute4","attribute5"})
public class VehicleTypesAndSlots {

    @Id
    private Long vehicleTypeId;

    @Enumerated(value = EnumType.STRING)
    private VehicleTypes vehicleTypes;

    private String slotCodes;
    private Long TotalSlots;


    public Long getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Long vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public VehicleTypes getVehicleTypes() {
        return vehicleTypes;
    }

    public void setVehicleTypes(VehicleTypes vehicleTypes) {
        this.vehicleTypes = vehicleTypes;
    }

    public String getSlotCodes() {
        return slotCodes;
    }

    public void setSlotCodes(String slotCodes) {
        this.slotCodes = slotCodes;
    }

    public Long getTotalSlots() {
        return TotalSlots;
    }

    public void setTotalSlots(Long totalSlots) {
        TotalSlots = totalSlots;
    }
}
