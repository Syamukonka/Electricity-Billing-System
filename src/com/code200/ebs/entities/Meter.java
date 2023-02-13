package com.code200.ebs.entities;

import java.util.Arrays;
import java.util.Objects;

public class Meter {

    static private int METER_COUNT = 1;
    {
        number = METER_COUNT++;
    }
    private final int number;
    private double reading = 0;

    private int index = 0;
    private Log[] logs = new Log[25];

    public Meter(){

    }

    public boolean recordNewReading(double newReading, int duration , boolean silent){
        //log  the new reading
        if( duration<=0 || newReading <= 0)
            throw new IllegalArgumentException("The new reading and duration must both be greater than 0");
        if(newReading<reading)
            throw new IllegalArgumentException("The new reading must be greater than the previous reading");
        //INFORMANT

        if(!silent)
            System.out.println(String.format("New Reading logged: %.2fKWh\nConsumption was %.2fKWh in %2d days",newReading,(newReading-reading),duration));

        logs[index++] = new Log(newReading-reading,duration);
        reading = newReading;

        return true;
    }

    public int getNumber() {
        return number;
    }

    public Log[] getLogs() {
        return Arrays.copyOf(logs,index);
    }

    public double getReading() {
        return reading;
    }

    public String showLogs(){
        StringBuilder output = new StringBuilder();
        for (Log log : logs) {
            if(log==null) break;
            output.insert(0,String.format("[%.2fKWh - %s Days]\n",log.consumption,log.duration));
        }
        return output.toString();
    }
    public String showLogs(int length){
        StringBuilder output = new StringBuilder();
        Log[] logs = getLogs();
        int duration = 1;
        for (int i = logs.length-1; i>=logs.length-length; i--) {
            if(logs[i]==null) break;
            output.append(String.format("[%4.2f KWh  -  "+(duration==1?"this month":duration==2?"previous month":"%d months ago")+"]\n",
                    logs[i].consumption,duration));
            duration++;
        }
        return output.toString();
    }

    @Override
    public String toString() {
        return String.format(" Meter number    :   C2L%03d", number)+
               String.format("\n Meter reading   :   %.2f KWh", reading);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meter meter)) return false;
        return number == meter.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    class Log{
        private final double consumption ;
        private final int duration;
        Log(double consumption, int duration){
            this.consumption = consumption;
            this.duration = duration;
        }

        public double getConsumption() {
            return consumption;
        }

        public int getDuration() {
            return duration;
        }
    }


}
