package com.simran.services;

import com.simran.models.Booking;
import com.simran.models.Vehicle;
import com.simran.models.VehicleType;
import com.simran.strategies.IPricingStrategy;
import com.simran.strategies.IVehicleMatchingStrategy;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingService
{
    Map<String, Booking> bookingMap;
    IVehicleMatchingStrategy vehicleMatchingStrategy;
    IPricingStrategy pricingStrategy;
    VehicleService vehicleService;
    VehicleInventoryService vehicleInventoryService;

    public BookingService(IVehicleMatchingStrategy vehicleMatchingStrategy, IPricingStrategy pricingStrategy,VehicleService vehicleService,VehicleInventoryService vehicleInventoryService)
    {
        this.vehicleMatchingStrategy = vehicleMatchingStrategy;
        this.pricingStrategy = pricingStrategy;
        this.vehicleService = vehicleService;
        this.vehicleInventoryService = vehicleInventoryService;
        this.bookingMap = new HashMap<>();
    }

    public Booking BookVehicle(VehicleType vehicleType, String userId, List<String> addons, LocalDateTime startDate, LocalDateTime dueDate)
    {
        List<Vehicle> vehicles = this.vehicleInventoryService.getAvailableVehicles(vehicleType,startDate,dueDate);
        Vehicle vehicle = this.vehicleMatchingStrategy.getVehicle(vehicles);
        long rentDurationInHours = Duration.between(startDate, dueDate).toHours();
        double charge = this.pricingStrategy.getPrice(vehicleType,addons,rentDurationInHours);
        Booking booking = new Booking(vehicle.getId(),userId,startDate,dueDate,addons,charge);
        bookingMap.put(booking.getId(),booking);
        this.vehicleInventoryService.UpdateVehicleInventory(vehicle.getId(),booking);
        return booking;
    }

}
