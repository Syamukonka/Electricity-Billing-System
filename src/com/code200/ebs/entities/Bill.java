package com.code200.ebs.entities;

public class Bill {

    public static void generateBill(Consumer consumer, boolean silent) {
        consumer.getBillRate();
        double reading = consumer.getMeter().getReading();
        double paid = consumer.getTotalPaid();
        double overallDue = reading * consumer.getBillRate();
        double balance = overallDue - paid;
        consumer.setBalance(balance);
    }
        public static void generateBill(Consumer consumer){
        consumer.getBillRate();
        double reading = consumer.getMeter().getReading();
        double paid = consumer.getTotalPaid();
        double overallDue = reading* consumer.getBillRate();
        double balance = overallDue-paid;
        consumer.setBalance(balance);


        System.out.println("\n⚡ Electricity bill"+
                "\n Customer        :   "+ consumer.getName()+"["+ consumer.getID()+"]"+
                "\n"+ consumer.getMeter()+
                "\n-----------------------------\n"+
                String.format(" Overall cost    :   $ %.2f%n",overallDue)+
                String.format(" Cleared amount  :   $ %.2f%n",paid)+
                String.format(" Balance         :   $ %.2f%n",balance)+
                " Billing Rate    :   $ "+ consumer.getBillRate()+"/unit \n "+ consumer.getVersion()+" billing rate\n");
    }

    public static void processPayment(Consumer consumer, double amount){

        consumer.makePayment(amount);

    }



}
