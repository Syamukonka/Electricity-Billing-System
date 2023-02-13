package com.code200.ebs.entities;

import java.io.IOException;
import java.util.Scanner;

public interface ScanningTools {
        static int scanInt(){
        Scanner scanner = new Scanner(System.in);
        try {
            int val = scanner.nextInt();
            return val;
        }catch (Exception ignore){
            System.out.println("Invalid input, try again");
            return scanInt();
        }
    }
    static double scanDouble(){
        Scanner scanner = new Scanner(System.in);
        try {
            double val = scanner.nextDouble();
            return val;
        }catch (Exception ignore){
            System.out.println("Invalid input, try again");
            return scanDouble();
        }

    }
    static String scanWord(){
        Scanner scanner = new Scanner(System.in);
        try {
            String val = scanner.next();
            return val;
        }catch (Exception ignore){
            System.out.println("Invalid input, try again");
            return scanWord();
        }

    }

    static String scanLine(){
        Scanner scanner = new Scanner(System.in);
        try {
            String val = scanner.nextLine();
            return val;
        }catch (Exception ignore){
            System.out.println("Invalid input, try again");
            return scanWord();
        }

    }

    static void flush() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException e) {
            //throw new RuntimeException(e);
        } catch (IOException e) {
            //throw new RuntimeException(e);
            //ignore
        }
    }
}
