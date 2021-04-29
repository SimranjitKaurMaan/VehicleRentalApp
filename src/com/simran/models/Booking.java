package com.simran.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Booking
{
    private String id;
    private String vehicleId;
    private String userId;
    private LocalDateTime startDate;
    private LocalDateTime dueDate;
    List<String> addons;
    double charge;

    public Booking(String vehicleId, String userId, LocalDateTime startDate,LocalDateTime dueDate, List<String> addons, double charge)
    {
        this.id = UUID.randomUUID().toString();
        this.vehicleId = vehicleId;
        this.userId = userId;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.addons = addons;
        this.charge = charge;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id='" + id + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", userId='" + userId + '\'' +
                ", startDate=" + startDate +
                ", dueDate=" + dueDate +
                ", addons=" + addons +
                ", charge=" + charge +
                '}';
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

}
