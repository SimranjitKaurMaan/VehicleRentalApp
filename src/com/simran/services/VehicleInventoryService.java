package com.simran.services;

import com.simran.exceptions.VehicleUnAvailableException;
import com.simran.models.Booking;
import com.simran.models.Vehicle;
import com.simran.models.VehicleStatus;
import com.simran.models.VehicleType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VehicleInventoryService
{
    Map<String, List<Booking>> BookingsByVehicleIds;
    private VehicleService vehicleService;

    public VehicleInventoryService(VehicleService vehicleService)
    {
        this.vehicleService = vehicleService;
        BookingsByVehicleIds = new HashMap<>();
    }


    public List<Vehicle> getAvailableVehicles(VehicleType vehicleType, LocalDateTime startDate, LocalDateTime dueDate)
    {
        List<Vehicle> vehicles = this.vehicleService.getVehicles(vehicleType);
        List<Vehicle> availableVehicles = new ArrayList<>();
        for(Vehicle vehicle: vehicles)
        {
            if(vehicle.getVehicleStatus()==VehicleStatus.Ready)
            {
                if(BookingsByVehicleIds.containsKey(vehicle.getId()))
                {
                   List<Booking> bookings = BookingsByVehicleIds.get(vehicle.getId());
                   List<Booking> clashingBookings = bookings.stream().filter(booking ->
                           booking.getStartDate().isBefore(dueDate) ||
                           booking.getDueDate().isAfter(startDate))
                           .collect(Collectors.toList());

                    if(clashingBookings.isEmpty())
                    {
                       availableVehicles.add(this.vehicleService.getVehicle(vehicle.getId()));
                    }
                }else
                {
                    availableVehicles.add(this.vehicleService.getVehicle(vehicle.getId()));
                }
            }
        }

        if(availableVehicles.isEmpty())
            throw new VehicleUnAvailableException();

        return availableVehicles;
    }

    public void UpdateVehicleInventory(String vehicleId,Booking booking)
    {
        if(!BookingsByVehicleIds.containsKey(vehicleId))
        {
            BookingsByVehicleIds.put(vehicleId,new ArrayList<>());
        }

        BookingsByVehicleIds.get(vehicleId).add(booking);
    }
}
