package com.code200.ebs.entities;

public class GovernmentConsumer extends Consumer {

    private static int COUNT = 0;
    private static final double BILL_RATE = 0.15;

    private String version = "🔵 Government Consumer";

    private final String id;
    {
        id = String.format("GOV-%03d",(++COUNT));
    }

    public GovernmentConsumer(String name, String address) {
        super(name, address);
    }

    @Override
    public double getBillRate() {
        return BILL_RATE;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public String getVersion() {
        return version;
    }
    @Override
    public String getVersionCode(){
        return "GOV";
    }
    @Override
    public String toString() {
        return "\nGovernment Consumer -------------" +
                "\n Name      :   " + getName() +
                "\n ID        :   " + id +
                "\n Address   :   " + getAddress() +
                "\n -----------------------------\n"+
                getMeter()+"\n";
    }
}
