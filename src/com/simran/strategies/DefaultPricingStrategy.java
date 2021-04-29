package com.simran.strategies;

import com.simran.models.VehicleType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultPricingStrategy implements IPricingStrategy
{
    Map<VehicleType,Double> vehiclePriceMap;

    public DefaultPricingStrategy()
    {
        this.vehiclePriceMap = new HashMap<>();
        vehiclePriceMap.put(VehicleType.BICYCLE,10.0);
        vehiclePriceMap.put(VehicleType.HATCHBACK,20.0);
        vehiclePriceMap.put(VehicleType.MOTORCYCLE,30.0);
        vehiclePriceMap.put(VehicleType.SUV,40.0);
        vehiclePriceMap.put(VehicleType.SEDAN,50.0);
        vehiclePriceMap.put(VehicleType.THREEWHEELER,60.0);
        vehiclePriceMap.put(VehicleType.TRUCK,70.0);
        vehiclePriceMap.put(VehicleType.VAN,80.0);
    }

    @Override
    public double getPrice(VehicleType vehicleType, List<String> addons, long rentDurationInHours) {
        double price = addons.size()*100+rentDurationInHours*100;
        return price;
    }
}
