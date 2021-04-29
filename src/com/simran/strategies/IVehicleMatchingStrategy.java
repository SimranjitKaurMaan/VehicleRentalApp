package com.simran.strategies;

import com.simran.models.Vehicle;

import java.util.List;

public interface IVehicleMatchingStrategy
{
    Vehicle getVehicle(List<Vehicle> vehicles);
}
