package com.code200.ebs.executors;

import com.code200.ebs.entities.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("""
                \nChoose an app to run
                1. Bill Manager's App
                2. Customer's App
                """);
        switch (ScanningTools.scanInt()){
            case 1 -> BillManagerApplication.run();
            case 2 -> CustomerApplication.run();
        }

    }


}