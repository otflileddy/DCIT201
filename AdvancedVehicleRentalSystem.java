
import java.util.*;

abstract class Vehicle {
    private String vehicleId;
    private String model;
    private double baseRentalRate;
    private boolean isAvailable;

    public Vehicle(String vehicleId, String model, double baseRentalRate) {
        this.vehicleId = vehicleId;
        this.model = model;
        this.baseRentalRate = baseRentalRate;
        this.isAvailable = true;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getBaseRentalRate() {
        return baseRentalRate;
    }

    public void setBaseRentalRate(double baseRentalRate) {
        this.baseRentalRate = baseRentalRate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public abstract double calculateRentalCost(int days);

    public abstract boolean isAvailableForRental();
}

// Car class extending Vehicle
class Car extends Vehicle {
    private boolean hasAirConditioning;

    public Car(String vehicleId, String model, double baseRentalRate, boolean hasAirConditioning) {
        super(vehicleId, model, baseRentalRate);
        this.hasAirConditioning = hasAirConditioning;
    }

    public boolean isHasAirConditioning() {
        return hasAirConditioning;
    }

    public void setHasAirConditioning(boolean hasAirConditioning) {
        this.hasAirConditioning = hasAirConditioning;
    }

    @Override
    public double calculateRentalCost(int days) {
        return days * getBaseRentalRate() + (hasAirConditioning ? 10 : 0);
    }

    @Override
    public boolean isAvailableForRental() {
        return isAvailable();
    }
}

// Motorcycle class extending Vehicle
class Motorcycle extends Vehicle {
    private boolean hasHelmet;

    public Motorcycle(String vehicleId, String model, double baseRentalRate, boolean hasHelmet) {
        super(vehicleId, model, baseRentalRate);
        this.hasHelmet = hasHelmet;
    }

    public boolean isHasHelmet() {
        return hasHelmet;
    }

    public void setHasHelmet(boolean hasHelmet) {
        this.hasHelmet = hasHelmet;
    }

    @Override
    public double calculateRentalCost(int days) {
        return days * getBaseRentalRate();
    }

    @Override
    public boolean isAvailableForRental() {
        return isAvailable();
    }
}

// Truck class extending Vehicle
class Truck extends Vehicle {
    private double loadCapacity;

    public Truck(String vehicleId, String model, double baseRentalRate, double loadCapacity) {
        super(vehicleId, model, baseRentalRate);
        this.loadCapacity = loadCapacity;
    }

    public double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    @Override
    public double calculateRentalCost(int days) {
        return days * getBaseRentalRate() + loadCapacity * 2;
    }

    @Override
    public boolean isAvailableForRental() {
        return isAvailable();
    }
}

// Rentable interface
interface Rentable {
    void rent(Customer customer, int days);
    void returnVehicle();
}

// Customer class
class Customer {
    private String name;
    private List<String> rentalHistory;

    public Customer(String name) {
        this.name = name;
        this.rentalHistory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addRental(String vehicleId) {
        rentalHistory.add(vehicleId);
    }

    public List<String> getRentalHistory() {
        return rentalHistory;
    }
}

// RentalAgency class
class RentalAgency {
    private List<Vehicle> vehicles;
    private List<String> transactionHistory;

    public RentalAgency() {
        this.vehicles = new ArrayList<>();
        this.transactionHistory = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public Vehicle rentVehicle(String vehicleId, Customer customer, int days) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVehicleId().equals(vehicleId) && vehicle.isAvailableForRental()) {
                vehicle.setAvailable(false);
                customer.addRental(vehicleId);
                transactionHistory.add("Vehicle rented: " + vehicleId);
                return vehicle;
            }
        }
        return null;
    }

    public void returnVehicle(String vehicleId) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVehicleId().equals(vehicleId)) {
                vehicle.setAvailable(true);
                transactionHistory.add("Vehicle returned: " + vehicleId);
            }
        }
    }
}
