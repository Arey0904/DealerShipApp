package org.example;

public class Contract {
    private String dateContract;
    private String customerName;
    private String email;
    private int vehicleSold;
    private double totalPrice;
    private double monthlyPayment;

    public String getDateContract() {
        return dateContract;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getEmail() {
        return email;
    }

    public int getVehicleSold() {
        return vehicleSold;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public Contract(String dateContract, String customerName, String email, int vehicleSold, double totalPrice, double monthlyPayment) {
        this.dateContract = dateContract;
        this.customerName = customerName;
        this.email = email;
        this.vehicleSold = vehicleSold;
        this.totalPrice = totalPrice;
        this.monthlyPayment = monthlyPayment;
    }
}
