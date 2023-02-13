package com.code200.ebs.entities;

import java.util.Random;

public abstract class Consumer {
    private String name, address;
    private final Meter meter;
    private double totalPaid = 0.0;

    private double balance = 0.0;

    Consumer(String name, String address){

        if(name==null || name.isEmpty())
            throw new IllegalArgumentException();
        if(address==null || address.isEmpty())
            throw new IllegalArgumentException();

        meter = new Meter();
        this.name = name;
        this.address = address;

    }
    public void recordConsumption(double currentReading, int duration){
        meter.recordNewReading(currentReading, duration, true);
    }
    public boolean makePayment(double amount){
        if(amount<=0) {
            throw new IllegalArgumentException("Payment can't be zero or negative");
        }
        totalPaid+=amount;
        balance-=amount;
        return true;
    }

    public abstract double getBillRate();
    public abstract String getID();

    public abstract String getVersion();
    public abstract String getVersionCode();

    public Meter getMeter(){
        return meter;
    }
    @Override
    public boolean equals(Object obj) {
        if(this==obj)
            return true;
        if(!(obj instanceof Consumer))
            return false;
        if(this.meter.equals(((Consumer) obj).meter) && this.name.equals(((Consumer) obj).name))
            return true;
        return this.name.equals(((Consumer) obj).name)&&this.address.equals(((Consumer) obj).address);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getTotalPaid() {
        return totalPaid;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void simulateUsage(int period){
        if(period<=0){
            period = 1;
        }
        Random rand = new Random();
            double reading = Math.abs(rand.nextDouble(5.0,20));
            reading*=period;
            //System.out.printf("%.2f%n",reading);
            recordConsumption(getMeter().getReading()+reading, period);

            Bill.generateBill(this,true);
    }

    @Override
    public String toString() {
        return "com.code200.ebs.entities.Customer{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", meter=" + meter +
                '}';
    }
}
