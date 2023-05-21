package org.example;

import java.io.*;
import java.io.FileReader;
import java.io.IOException;

import static jdk.internal.org.jline.utils.Colors.s;

public class DealerShipFileManager {


    //
    public Dealership getDealership() {
        Dealership anotherDealership;
        try {
            String dealershipFileName = "01-DealershipVehicleList";
            BufferedReader dealershipFileReader = new BufferedReader(new FileReader(dealershipFileName));


            String dealershipInformation = dealershipFileReader.readLine();
            String[] dealershipDetails = dealershipInformation.split("\\|");
            String name = dealershipDetails[0];
            String address = dealershipDetails[1];
            String phone = dealershipDetails[2];

            anotherDealership = new Dealership(name, address, phone);

            String vehicleSpecs;
            while ((vehicleSpecs = dealershipFileReader.readLine()) != null) {
                String[] vehicleDetails = vehicleSpecs.split("\\|");
                int vin = Integer.parseInt(vehicleDetails[0]);
                int year = Integer.parseInt(vehicleDetails[1]);
                String make = vehicleDetails[2];
                String model = vehicleDetails[3];
                String vehicleType = vehicleDetails[4];
                String color = vehicleDetails[5];
                int odometer = Integer.parseInt(vehicleDetails[6]);
                double price = Double.parseDouble(vehicleDetails[7]);
                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                anotherDealership.addVehicle(vehicle);
            }

            dealershipFileReader.close();

            return anotherDealership;


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void saveDealership(Dealership anotherDealership) {
        try {
            FileWriter fileWriter = new FileWriter("DealershipVehicleList");
            for (Vehicle v : anotherDealership.getAllVehicles()) {
                String vehicle = String.format("%d|%d|%s|%s|%s|%s|%d|%.2f\n",
                        v.getVin(),
                        v.getVehicleType(),
                        v.getYear(),
                        v.getPrice(),
                        v.getModel(),
                        v.getMake(),
                        v.getOdometer(),
                        v.getColor());
                fileWriter.write(vehicle);
                fileWriter.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
