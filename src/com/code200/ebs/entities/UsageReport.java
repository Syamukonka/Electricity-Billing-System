package com.code200.ebs.entities;

import java.util.Arrays;

public class UsageReport {

    /*
        The com.code200.ebs.entities.UsageReport will take a meter reading, and consumer, then generate a report
        TODO: implement static generateReport which takes a meter and consumer, and duration.
    */
    public static void generate(Consumer consumer, int months ){
        //generate a report
        //average consumption
        Meter.Log[] logs = consumer.getMeter().getLogs();
        //System.out.println("LOGS: \n"+Arrays.toString(logs));
        System.out.println("\n");
        if(logs==null || logs.length==0){
            System.out.println("No report available");
            return;
        }
        if(months > logs.length || months<=0){
            System.out.println("⚠ The value you entered is out of bounds\n-Showing all logs instead of "+months+"\n");
            months = logs.length;
        }

        double monthlyAvg = 0, dailyAvg = 0;
        int days = 1;
        for (int i = logs.length-1; i>(logs.length-months)-1; i--) {
            if(logs[i]==null)
                continue;
            days+=logs[i].getDuration();
            dailyAvg+=logs[i].getConsumption();
            monthlyAvg+=logs[i].getConsumption();
        }
        dailyAvg/=days;
        monthlyAvg/=months;

        System.out.println("⚡ "+months+" months usage report" +
                String.format("\n Monthly Average       :   %.2f KWh",monthlyAvg)+
                String.format("\n Average monthly cost  :   $%.2f",monthlyAvg*consumer.getBillRate())+
                String.format("\n Daily Average         :   %.2f KWh",dailyAvg)+
                String.format("\n Average Daily cost    :   $%.2f",dailyAvg*consumer.getBillRate())+
        "\n\n⚡ View your consumption history:");
        System.out.println(consumer.getMeter().showLogs(months)+" ---end of history---\n\n");
        Bill.generateBill(consumer);
    }


}
