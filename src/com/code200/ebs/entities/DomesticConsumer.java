package com.code200.ebs.entities;

import java.util.Objects;

public class DomesticConsumer extends Consumer {

    private static int COUNT = 0;
    private final String version = "🟢 Domestic Consumer";
    private static final double BILL_RATE = 0.25;
    private final String id;

    {
        id = String.format("DOM-%03d",(++COUNT));
    }

    public DomesticConsumer(String name, String address){
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
    public String toString() {
        return "\nDomestic Consumer:" +
                "\n Name      :   " + getName() +
                "\n ID        :   " + id +
                "\n Address   :   " + getAddress() +
                "\n -----------------------------\n"+
                getMeter()+"\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DomesticConsumer that)) return false;
        return id.equals(that.id);
    }

    public String getVersion() {
        return version;
    }
    @Override
    public String getVersionCode(){
        return "DOM";
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
