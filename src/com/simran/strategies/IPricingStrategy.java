package com.simran.strategies;

import com.simran.models.VehicleType;

import java.util.List;

public interface IPricingStrategy
{
    double getPrice(VehicleType vehicleType, List<String> addons, long rentDurationInHours);
}
