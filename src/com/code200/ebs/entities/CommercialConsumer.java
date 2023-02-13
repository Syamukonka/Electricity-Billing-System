package com.code200.ebs.entities;

public class CommercialConsumer extends Consumer {

    private static int COUNT = 0;
    private final String version = "🟠 Commercial Consumer";
    private static final double BILL_RATE = 0.20;
    private final String id;

    {
        id = String.format("COM-%03d",(++COUNT));
    }

    public CommercialConsumer(String name, String address) {
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
        return "COM";
    }
    @Override
    public String toString() {
        return "\nCommercial Consumer -------------" +
                "\n Name      :   " + getName() +
                "\n ID        :   " + id +
                "\n Address   :   " + getAddress() +
                "\n -----------------------------\n"+
                getMeter()+"\n";
    }

}
