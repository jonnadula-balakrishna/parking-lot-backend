package com.parkinglot.dao;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class AvailableSlot {
	
	String diplayMessage;
	String typeOfVehicle;
	List<String> availableSlotCodes;
	int availableSlots;
	Long TotalSlots;
	
	public String getDiplayMessage() {
		return diplayMessage;
	}
	public void setDiplayMessage(String diplayMessage) {
		this.diplayMessage = diplayMessage;
	}
	public String getTypeOfVehicle() {
		return typeOfVehicle;
	}
	public void setTypeOfVehicle(String typeOfVehicle) {
		this.typeOfVehicle = typeOfVehicle;
	}
	public List<String> getAvailableSlotCodes() {
		return availableSlotCodes;
	}
	public void setAvailableSlotCodes(List<String> availableSlotCodes) {
		this.availableSlotCodes = availableSlotCodes;
	}
	public int getAvailableSlots() {
		return availableSlots;
	}
	public void setAvailableSlots(int availableSlots) {
		this.availableSlots = availableSlots;
	}
	public Long getTotalSlots() {
		return TotalSlots;
	}
	public void setTotalSlots(Long totalSlots) {
		TotalSlots = totalSlots;
	}
	
	
	

}
