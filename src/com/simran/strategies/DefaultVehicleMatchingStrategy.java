package com.simran.strategies;

import com.simran.models.Vehicle;

import java.util.List;

public class DefaultVehicleMatchingStrategy implements IVehicleMatchingStrategy{
    @Override
    public Vehicle getVehicle(List<Vehicle> vehicles) {
        return vehicles.get(0);
    }
}
