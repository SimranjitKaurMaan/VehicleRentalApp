package test;

import com.simran.models.Booking;
import com.simran.models.Vehicle;
import com.simran.models.VehicleStatus;
import com.simran.models.VehicleType;
import com.simran.services.BookingService;
import com.simran.services.VehicleInventoryService;
import com.simran.services.VehicleService;
import com.simran.strategies.DefaultPricingStrategy;
import com.simran.strategies.DefaultVehicleMatchingStrategy;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingServiceTest
{
    @Test
    public void DefaultTest()
    {
        Map<String, Vehicle> vehicleMap = new HashMap<>();
        vehicleMap.put("V1",new Vehicle("V1",VehicleType.BICYCLE, VehicleStatus.Ready));
        vehicleMap.put("V2",new Vehicle("V2",VehicleType.HATCHBACK, VehicleStatus.Servicing));
        vehicleMap.put("V3",new Vehicle("V3",VehicleType.BICYCLE, VehicleStatus.Ready));
        vehicleMap.put("V4",new Vehicle("V4",VehicleType.MOTORCYCLE, VehicleStatus.Ready));
        vehicleMap.put("V5",new Vehicle("V5",VehicleType.BICYCLE, VehicleStatus.Ready));
        Map<VehicleType, List<String>> vehicleTypeMap = new HashMap<>();
        vehicleTypeMap.put(VehicleType.BICYCLE,Arrays.asList("V1","V3","V5"));
        vehicleTypeMap.put(VehicleType.MOTORCYCLE,Arrays.asList("V4"));
        vehicleTypeMap.put(VehicleType.HATCHBACK,Arrays.asList("V2"));
        VehicleService vehicleService = new VehicleService(vehicleMap,vehicleTypeMap);
        VehicleInventoryService vehicleInventoryService = new VehicleInventoryService(vehicleService);
        DefaultVehicleMatchingStrategy vehicleMatchingStrategy = new DefaultVehicleMatchingStrategy();
        DefaultPricingStrategy pricingStrategy = new DefaultPricingStrategy();
        BookingService bookingService = new BookingService(vehicleMatchingStrategy,pricingStrategy,vehicleService,vehicleInventoryService);
        Booking booking1 = bookingService.BookVehicle(VehicleType.BICYCLE, "101", Arrays.asList("A","B","C"), LocalDateTime.now(), LocalDateTime.now().plusDays(2));
        System.out.println(booking1);
        Booking booking2 = bookingService.BookVehicle(VehicleType.BICYCLE, "102", Arrays.asList("A","B"), LocalDateTime.now(), LocalDateTime.now().plusDays(4));
        System.out.println(booking2);
        Booking booking3 = bookingService.BookVehicle(VehicleType.BICYCLE, "103", Arrays.asList("A"), LocalDateTime.now(), LocalDateTime.now().plusDays(4));
        System.out.println(booking3);
        Booking booking4 = bookingService.BookVehicle(VehicleType.BICYCLE, "104", Arrays.asList("A"), LocalDateTime.now().plusDays(6), LocalDateTime.now().plusDays(10));
        System.out.println(booking4);

    }
}
