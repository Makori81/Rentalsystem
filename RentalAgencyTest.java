package com.mycompany.rentalagencytest;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class RentalAgencyTest {
    
    public static void main(String[] args) {
        // Create a Scanner object to read input
        Scanner scanner = new Scanner(System.in);
        
        // Create a rental agency object
        RentalAgency agency = new RentalAgency();
        
        // Example logic: Displaying welcome message and options
        System.out.println("Welcome to the Car Rental System!");
        
        boolean exit = false;
        while (!exit) {
            System.out.println("Select an option:");
            System.out.println("1. Rent a car");
            System.out.println("2. Display available cars");
            System.out.println("3. Return a car");
            System.out.println("4. Exit");
            
            // Reading user's choice
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    // Logic to rent a car
                    System.out.println("Enter customer details:");
                    System.out.println("Customer ID:");
                    int customerId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.println("Customer Name:");
                    String customerName = scanner.nextLine();
                    System.out.println("Customer Email:");
                    String customerEmail = scanner.nextLine();
                    System.out.println("Customer Phone Number:");
                    String customerPhone = scanner.nextLine();
                    Customer customer = new Customer(customerId, customerName, customerEmail, customerPhone);
                    
                    // Display available cars and their registration numbers along with the model
                    System.out.println("Available Cars:");
                    List<Car> availableCars = agency.getAvailableCars();
                    for (Car car : availableCars) {
                        System.out.println("Registration Number: " + car.getRegistrationNumber() + " - " + car.getMake() + " " + car.getModel());
                    }
                    
                    // Prompt user to choose a car
                    System.out.println("Enter registration number of the car you want to rent:");
                    String regNumber = scanner.nextLine();
                    Car chosenCar = agency.findCar(regNumber);
                    
                    // Rent the chosen car
                    if (chosenCar != null) {
                        boolean success = agency.rentCar(customer, chosenCar);
                        if (success) {
                            System.out.println("Car rented successfully!");
                        } else {
                            System.out.println("Car is not available for rent.");
                        }
                    } else {
                        System.out.println("Car not found.");
                    }
                    break;
                case 2:
                    // Display available cars and their registration numbers along with the model
                    System.out.println("Available Cars:");
                    List<Car> allAvailableCars = agency.getAvailableCars();
                    for (Car car : allAvailableCars) {
                        System.out.println("Registration Number: " + car.getRegistrationNumber() + " - " + car.getMake() + " " + car.getModel());
                    }
                    break;
                case 3:
                    // Logic to return a car
                    System.out.println("Enter registration number of the car you want to return:");
                    String returnRegNumber = scanner.nextLine();
                    Car returnedCar = agency.findCar(returnRegNumber);

                    if (returnedCar != null && !returnedCar.isAvailable()) {
                        Customer rentingCustomer = null;
                        for (Customer c : agency.getCustomers()) {
                            if (c.getCustomerId() == returnedCar.getCustomerId()) {
                                rentingCustomer = c;
                                break;
                            }
                        }

                        // Display car and customer details for confirmation
                        System.out.println("Car details:");
                        System.out.println("Registration Number: " + returnedCar.getRegistrationNumber() + " - " + returnedCar.getMake() + " " + returnedCar.getModel());
                        System.out.println("Customer details:");
                        System.out.println("Customer ID: " + rentingCustomer.getCustomerId());
                        System.out.println("Customer Name: " + rentingCustomer.getName());
                        System.out.println("Customer Email: " + rentingCustomer.getEmail());
                        System.out.println("Customer Phone Number: " + rentingCustomer.getPhoneNumber());

                        // Ask for confirmation
                        System.out.println("Confirm returning this car? (yes/no)");
                        String confirmation = scanner.nextLine();
                        if (confirmation.equalsIgnoreCase("yes")) {
                            agency.returnCar(returnedCar);
                            System.out.println("Car returned successfully!");
                        } else {
                            System.out.println("Returning process cancelled.");
                        }
                    } else {
                        System.out.println("Car not found or is not rented.");
                    }
                    break;
                case 4:
                    // Exit the program
                    System.out.println("Thank you for using the Car Rental System. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
        // Close the scanner
        scanner.close();
    }
}

class Car {
    private String registrationNumber;
    private String make;
    private String model;
    private int year;
    private boolean
available;
    private int customerId; // Add customerId field

    public Car(String registrationNumber, String make, String model, int year) {
        this.registrationNumber = registrationNumber;
        this.make = make;
        this.model = model;
        this.year = year;
        this.available = true; // Initially set the car as available
        this.customerId = -1; // Initialize customerId to -1 (indicating no customer)
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getCustomerId() { // Add getter for customerId
        return customerId;
    }

    public void setCustomerId(int customerId) { // Add setter for customerId
        this.customerId = customerId;
    }
}

class Customer {
    private int customerId;
    private String name;
    private String email;
    private String phoneNumber;

    public Customer(int customerId, String name, String email, String phoneNumber) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

class RentalAgency {
    private List<Car> cars;
    private List<Customer> customers;

    public RentalAgency() {
        this.cars = new ArrayList<>();
        this.customers = new ArrayList<>(); // Initialize the list of customers
        // Adding some example cars
        cars.add(new Car("ABC234", "Toyota", "Camry", 2020));
        cars.add(new Car("XYZ456", "Honda", "Accord", 2021));
        cars.add(new Car("DEF189", "Ford", "Focus", 2019));
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void removeCar(Car car) {
        cars.remove(car);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void removeCustomer(Customer customer) {
        customers.remove(customer);
    }

    public List<Car> getAvailableCars() {
        List<Car> availableCars = new ArrayList<>();
        for (Car car : cars) {
            if (car.isAvailable()) {
                availableCars.add(car);
            }
        }
        return availableCars;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public Car findCar(String registrationNumber) {
        for (Car car : cars) {
            if (car.getRegistrationNumber().equals(registrationNumber)) {
                return car;
            }
        }
        return null;
    }

    public boolean rentCar(Customer customer, Car car) {
        if (car.isAvailable()) {
            car.setAvailable(false);
            car.setCustomerId(customer.getCustomerId());
            customers.add(customer);
            return true;
        }
        return false;
    }

    public void returnCar(Car car) {
        car.setAvailable(true);
        car.setCustomerId(-1);
        for (Customer customer : customers) {
            if (customer.getCustomerId() == car.getCustomerId()) {
                customers.remove(customer);
                break;
            }
        }
    }
}
