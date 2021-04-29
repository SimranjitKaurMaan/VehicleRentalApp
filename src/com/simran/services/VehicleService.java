package com.simran.services;

import com.simran.exceptions.VehicleNotFoundException;
import com.simran.exceptions.VehicleUnAvailableException;
import com.simran.models.Vehicle;
import com.simran.models.VehicleStatus;
import com.simran.models.VehicleType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VehicleService
{
    Map<String,Vehicle> vehicleMap;
    Map<VehicleType, List<String>> vehiclesByTypeMap;

    public VehicleService(Map<String, Vehicle> vehicleMap,Map<VehicleType,List<String>> vehiclesByTypeMap) {
        this.vehicleMap = vehicleMap;
        this.vehiclesByTypeMap = vehiclesByTypeMap;
    }

    public List<Vehicle> getVehicles(VehicleType vehicleType)
    {
        List<String> vehicleIds = vehiclesByTypeMap.get(vehicleType);
        List<Vehicle> vehicles = new ArrayList<>();
        for(String vehicleId: vehicleIds)
        {
            Vehicle vehicle = vehicleMap.get(vehicleId);
            vehicles.add(vehicle);
        }
        if(vehicles.isEmpty())
        {
            throw new VehicleUnAvailableException();
        }
        return vehicles;
    }

    public Vehicle getVehicle(String vehicleId)
    {
        if(!vehicleMap.containsKey(vehicleId))
        {
            throw new VehicleNotFoundException();
        }
        return vehicleMap.get(vehicleId);
    }

}
