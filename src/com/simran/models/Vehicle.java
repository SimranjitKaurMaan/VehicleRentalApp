package com.simran.models;

import com.simran.exceptions.VehicleUnAvailableException;

public class Vehicle
{
    String id;
    VehicleType vehicleType;
    VehicleStatus vehicleStatus;

    public VehicleStatus getVehicleStatus() {
        return vehicleStatus;
    }

    public Vehicle(String id, VehicleType vehicleType, VehicleStatus vehicleStatus) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.vehicleStatus = vehicleStatus;
    }

    public String getId() {
        return id;
    }

}
