package com.code200.ebs.executors;

import com.code200.ebs.entities.*;

public class BillManagerApplication {
    private static int index = 9;
    protected static Consumer[] consumers = new Consumer[100];
    static {
        System.arraycopy(new Consumer[]{
                new GovernmentConsumer("First National Bank", "Build 10 - Central Business Area, Lusaka, Zambia"),
                new GovernmentConsumer("Ministry of Agriculture", "Plot 21 - Copper Street, Lusaka, Zambia"),
                new CommercialConsumer("UK Consortium", "No.17 - Independence Avenue, Kolkata, India"),
                new DomesticConsumer("Martin L. King", "Sun Court 23-102 - Jay-pee Green, Greater Noida, India"),
                new DomesticConsumer("Kayla McCathy", "Moon Court 23-102 - Jay-pee Green Colony, Mumbai, India"),
                new GovernmentConsumer("Konkola Copper Mines", "Area 43 - Kitwe, CopperBelt, Zambia"),
                new CommercialConsumer("Unilever India", "Building 13 - Power Avenue, Industrial Area, Mumbai, India"),
                new CommercialConsumer("Habibi Restaurant", "Building 3 - Holy Crescent, Model Town, Jalandhar, India"),
                new DomesticConsumer("Dallas Bronn", "Room 305 - Vinayak Apartment, Law Gate Phagwara, India"),
        },0,consumers,0,9);
    }
    public static void run() {
        //run application
        boolean run = true;
        simulateUsage();
        while(run){
            homeScreen();
            switch (ScanningTools.scanInt()){
                case 1 -> {
                    //show customers
                    show();
                    System.out.println("""
                            1. Go back
                            2. Exit application
                            """);
                    if (ScanningTools.scanInt()==2){
                        run=false;
                    }
                }
                case 2 -> {
                    boolean retry = true;
                    while (retry){
                        System.out.println("Enter the customer ID");
                        Consumer target = find(ScanningTools.scanWord());
                        if(target!=null){
                            System.out.println(target);
                            Bill.generateBill(target);
                            System.out.println("""
                            1. Go back
                            2. Exit application
                            """);
                            if (ScanningTools.scanInt()==2){
                                run=false;
                            }
                            retry=false;
                        }
                        else{
                            System.out.println("""
                                    Not found
                                    1. Retry
                                    2. Go back
                                    3. Exit application
                                    """);
                            int retryOption = ScanningTools.scanInt();
                            if (retryOption==2){
                                retry=false;
                            }else if(retryOption==3){
                                run = false;
                                break;
                            }
                        }
                    }
                }
                case 3 -> {
                    //process a payment
                    boolean retry = true;
                    while (retry){
                        System.out.println("Enter the customer ID");
                        Consumer target = find(ScanningTools.scanWord());
                        if(target!=null){
                            System.out.println("Customer: "+target.getName()+" - "+target.getID());
                            double res = paymentInteraction();
                            if(res>0){
                                //payment request was valid
                                target.makePayment(res);
                                retry=false;
                            }else System.out.println("Payment failed");

                        }
                        else{
                            System.out.println("""
                                    Not found
                                    1. Retry
                                    2. Go back
                                    3. Exit application
                                    """);
                            int retryOption = ScanningTools.scanInt();
                            if (retryOption==2){
                                retry=false;
                            }else if(retryOption==3){
                                run = false;
                                break;
                            }
                        }
                    }
                }
                case 4 -> {

                    System.out.println("""
                            Choose the category of the new consumer:
                            1. Domestic
                            2. Commercial
                            3. Government
                            """);
                    int option = ScanningTools.scanInt();
                    System.out.println("Enter the name:");
                    String name = ScanningTools.scanLine();
                    System.out.println("Enter the address:");
                    String address = ScanningTools.scanLine();
                    if(name!= null && !name.isEmpty()){
                        switch (option){
                            case 1 -> {
                                consumers[index++] = new DomesticConsumer(name,address);
                                System.out.println("Added successfully");
                            }
                            case 2 -> {
                                consumers[index++] = new CommercialConsumer(name,address);
                                System.out.println("Added successfully");
                            }
                            case 3 -> {
                                consumers[index++] = new GovernmentConsumer(name,address);
                                System.out.println("Added successfully");
                            }
                        }

                    }else{
                        System.out.println("Failed to add due to invalid inputs");
                    }
                    System.out.println("""
                            1. Go back
                            2. Exit application
                            """);
                    if (ScanningTools.scanInt()==2){
                        run=false;
                    }
                }
                case 5 -> {
                    simulateUsage();
                    System.out.println("""
                            1. Go back
                            2. Exit application
                            """);
                    if (ScanningTools.scanInt()==2){
                        run=false;
                    }
                }
                case 6 -> {
                    showDetailed();
                    System.out.println("""
                            1. Go back
                            2. Exit application
                            """);
                    if (ScanningTools.scanInt()==2){
                        run=false;
                    }
                }
                case 7 -> run = false;
                default -> {
                    //ignore
                }
            }
        }
    }

    private static void homeScreen(){
        System.out.println("""
                    \nCode200 Luminous Power Ltd.
                    ⚙ Management Portal
                    
                    1. Show customers
                    2. Show customer details
                    3. Process a Payment
                    4. Register a new customer
                    5. Update meter readings
                    6. Show all customers' details
                    7. Exit
                    """);
    }

    private static void show(){

        StringBuilder com = new StringBuilder();
        StringBuilder dom = new StringBuilder();
        StringBuilder gov = new StringBuilder();
        for (Consumer consumer : consumers) {
            if(consumer==null)
                break;
            if(consumer.getVersionCode().equals("DOM"))
                dom.append("["+consumer.getID()+"] "+consumer.getName()+"\n");
            else if(consumer.getVersionCode().equals("COM"))
                com.append("["+consumer.getID()+"] "+consumer.getName()+"\n");
            else if(consumer.getVersionCode().equals("GOV"))
                gov.append("["+consumer.getID()+"] "+consumer.getName()+"\n");
        }
        String res = dom.append(com).append(gov).toString();
        System.out.println(res);
    }

    private static void showDetailed(){

        for (Consumer consumer : consumers) {
            if(consumer==null)
                break;
            if(!consumer.getVersionCode().equals("DOM"))
                continue;
            System.out.println(consumer);
            Bill.generateBill(consumer);
        }
        for (Consumer consumer : consumers) {
            if(consumer==null)
                break;
            if(!consumer.getVersionCode().equals("COM"))
                continue;
            System.out.println(consumer);
            Bill.generateBill(consumer);
        }
        for (Consumer consumer : consumers) {
            if(consumer==null)
                break;
            if(!consumer.getVersionCode().equals("GOV"))
                continue;
            System.out.println(consumer);
            Bill.generateBill(consumer);
        }
    }

    private static Consumer find(String id){
        for (Consumer consumer: consumers) {
            if(consumer==null)
                break;
            if(id.toLowerCase().equals(consumer.getID().toLowerCase())){
                return consumer;
            }
        }
        return null;
    }

    private static double paymentInteraction (){

        while(true){
            System.out.println("Enter the amount:");
            double amount = ScanningTools.scanDouble();
            if(amount<=2) {
                System.out.println("""
                        Enter an amount greater than $2
                        Press 0 - to cancel
                        Press 1 - to retry""");

                if(ScanningTools.scanInt()==0)
                    return 0;

            }else {
                System.out.printf("Pay $%.2f, is that correct?%n%n",amount);
                System.out.println("""
                        1. Yes
                        2. No
                        """);
                if(ScanningTools.scanInt()==1){
                    System.out.printf("Successfully paid $%.2f\nPress 0 - to exit%n%n",amount);
                    ScanningTools.scanInt();
                    return amount;

                }
            }
        }
    }
    private static void simulateUsage(){

        for (Consumer consumer : consumers) {
            if(consumer==null){
                break;
            }
            consumer.simulateUsage(30);
        }
            System.out.println("Updated all users' meters");
    }


}
