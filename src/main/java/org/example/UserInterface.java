package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private static Dealership dealership;

    public static Scanner scanner = new Scanner(System.in);

    private void init() {
        DealerShipFileManager dealerShipFileManager = new DealerShipFileManager();
    }

    private void displayVehicles(List<Vehicle> vehicle) {
        for (Vehicle i : vehicle) {
            System.out.printf("%-15s %25s %20s %d %15.2f \n", i.getVin(), i.getYear(), i.getMake(), i.getModel(), i.getOdometer(), i.getPrice(), i.getVehicleType());
        }
    }

    public void displayMenu() {
        init();

        boolean exit = false;

        while (!exit) {
            System.out.println("------ Dealership Menu ------");
            System.out.println("[1] Get vehicles by price");
            System.out.println("[2] Get vehicles by make and model");
            System.out.println("[3] Get vehicles by year");
            System.out.println("[4] Get vehicles by color");
            System.out.println("[5] Get vehicles by mileage");
            System.out.println("[6] Get vehicles by vehicle type");
            System.out.println("[7] Get all vehicles");
            System.out.println("[8] Add a vehicle");
            System.out.println("[9] Remove a vehicle");
            System.out.println("[0] Exit");
            System.out.print("Enter your choice: ");
            int choice;
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> processGetByPriceRequest();
                case 2 -> processGetByMakeModelRequest();
                case 3 -> processGetByYearRequest();
                case 4 -> processGetByColorRequest();
                case 5 -> processGetByMileageRequest();
                case 6 -> processGetByVehicleTypeRequest();
                case 7 -> processGetAllVehiclesRequest();
                case 8 -> processAddVehicleRequest();
                case 9 -> processRemoveVehicleRequest();
                case 0 -> {
                    exit = true;
                    System.out.println("Exiting Dealership Menu...");
                }
                default -> badInput();
            }
        }
    }

    private void badInput() {
        System.out.println("Please select a valid option");
    }

    public void processGetAllVehiclesRequest() {
        List<Vehicle> list = dealership.getAllVehicles();
        displayVehicles(list);
    }


    public void processGetByYearRequest() {
        System.out.println("Enter minimum Year: ");
        int minYear = scanner.nextInt();
        System.out.print("Enter maximum year: ");
        int maxYear = scanner.nextInt();

        System.out.println("Vehicles by Year:");
        ArrayList aList = (ArrayList) dealership.getVehiclesByYear(minYear, maxYear);

        displayVehicles(aList);

        System.out.println();

    }

    public void processGetByMakeModelRequest() {
        String make = "";
        String model = "";

        System.out.println("Enter Your Model: ");
        make = requestStringInput("Make: ", make);
        model = requestStringInput("Model:  ", model);
        System.out.println("\n -----------------SELECTION BY MODEL------------------");
        List<Vehicle> list = dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(list);

    }

    public void processGetByVehicleTypeRequest() {
        String vehicleType = "";
        vehicleType = requestStringInput("Enter vehicle type: ", vehicleType);
        System.out.println("\n -----------------SELECTION BY VEHICLE TYPE------------------");
        List<Vehicle> list = dealership.getVehiclesByType(vehicleType);
        displayVehicles(list);
    }

    public void processGetByColorRequest() {
        String color = "black";

        color = requestStringInput("Enter color:", color);
        System.out.println("\n -----------------SELECTION BY COLOR------------------");
        List<Vehicle> list = dealership.getVehiclesByColor(color);
        displayVehicles(list);
    }

    public void processGetByMileageRequest() {
        System.out.println("Enter minimum mileage");
        String input = scanner.nextLine();
        int min = 0;
        System.out.println("Enter maximum mileage: ");
        input = scanner.nextLine();
        int max = Integer.MAX_VALUE;
        System.out.println("\n -----------------SELECTION BY MILEAGE------------------");
        displayVehicles(dealership.getVehiclesByMileage(min, max));
    }

    public void processGetByPriceRequest() {
        double min = 0.0;
        double max = Double.MAX_VALUE;

        System.out.println("Enter Price Range: ");
        min = requestDoubleInput("Minimum: ", min);
        max = requestDoubleInput("Maximum: ", max);

        List<Vehicle> list = dealership.getVehiclesByPrice(min, max);
        displayVehicles(list);
        //System.out.println("Enter min Price Range: ");
        //double minPrice = Double.parseDouble(scanner.nextLine());
        //System.out.println("Enter max Price Range: ");
        //double maxPrice = Double.parseDouble(scanner.nextLine());

    }

    public void processAddVehicleRequest() {
        System.out.println("Enter vin of vehicle you like to add: ");
        int vin = scanner.nextInt();
        System.out.println("Enter year: ");
        int year = scanner.nextInt();
        System.out.println("Enter make: ");
        String make = String.valueOf(scanner.nextInt());
        System.out.println("Enter model: ");
        String model = String.valueOf(scanner.nextInt());
        System.out.println("Enter vehicle type: ");
        String vehicleType = String.valueOf(scanner.nextInt());
        System.out.println("Enter color: ");
        String color = String.valueOf(scanner.nextInt());
        System.out.println("Enter mileage: ");
        int odometer = scanner.nextInt();
        System.out.println("Enter price: ");
        double price = scanner.nextDouble();
        System.out.println("\nVehicle successfully added ");
        Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);

        DealerShipFileManager dealerShipFileManager = new DealerShipFileManager();
        dealerShipFileManager.saveDealership(dealership);

    }

    public void processRemoveVehicleRequest() {
        System.out.println("Enter vin of Vehicle you like to remove: ");
        int vin = requestIntegerInput("VIN: ", 0);
        Vehicle v = dealership.getVehicleByVin(vin);
        {
            if (v != null) {
                dealership.removeVehicle(v);
                DealerShipFileManager.saveDealership(dealership);
                System.out.println("Vehicle has been removed");
            }
        }
        dealership.removeVehicle(v);

        DealerShipFileManager dealerShipFileManager = new DealerShipFileManager();
        dealerShipFileManager.saveDealership(dealership);
    }

    private String requestStringInput(String prompt, String defaultValue) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        String value = input.equals("") ? defaultValue : input;
        return value;
    }

    private double requestDoubleInput(String prompt, double defaultValue) {
        System.out.print(prompt);
        String input = scanner.nextLine();

        double value = defaultValue;
        try {
            value = Double.parseDouble(input);
        } catch (NumberFormatException ignored) {
        }

        return value;
    }

    private int requestIntegerInput(String prompt, int defaultValue) {
        System.out.print(prompt);
        String input = scanner.nextLine();

        int value = defaultValue;
        try {
            value = Integer.parseInt(input);
        } catch (NumberFormatException ignored) {
        }
        return value;
    }
}


