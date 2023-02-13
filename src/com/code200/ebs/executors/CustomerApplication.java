package com.code200.ebs.executors;

import com.code200.ebs.entities.*;


public class CustomerApplication implements ScanningTools {

    private static Consumer consumer = new DomesticConsumer("Emilia Clarke","29 Dawn Street, East watch, Essos.");

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        //console interface
        //home [user chooses an action]


        boolean run = true;
        while(run){
            consumer.simulateUsage(30);
            System.out.println("\nHi, "+consumer.getName());
            System.out.println("\nPress 1 - Start session");
            System.out.println("Press 2 - Close");
            switch (ScanningTools.scanInt()){
                case 1 -> {
                    String action = "";
                    while(true){
                    if(action.equals("x"))
                    {
                        System.out.println("Thank you for using this service.\nGoodbye!!");
                        break;
                    }

                    homeScreen();
                    int option = ScanningTools.scanInt();
                        //System.out.println("OPTION: "+option);
                    switch (option) {
                        case 1 -> {
                                System.out.printf("Your balance is $%.2f \n"+(consumer.getBalance()<0?"Your payments are ahead":"Pay in good time to avoid any inconveniences%n"),consumer.getBalance());
                                System.out.println();
                                System.out.println("""
                                    \n1. Go back
                                    2. End session
                                    """);
                                if(ScanningTools.scanInt()==2)
                                    action = "x";

                            }
                            case 2 -> {
                                System.out.println(consumer.getMeter());
                                System.out.println("""
                                    \n1. Go back
                                    2. End Session                                    """);
                                if(ScanningTools.scanInt()==2)
                                    action = "x";
                            }
                            case 3 -> {
                                double res = paymentInteraction();
                                if(res>0){
                                    //payment request was valid
                                    consumer.makePayment(res);
                                }else System.out.println("Payment failed");

                            }
                            case 4 -> {
                                System.out.println("Enter the number of months to report:");
                                int months = ScanningTools.scanInt();
                                UsageReport.generate(consumer,months);
                                System.out.println("""
                                    \n1. Go back
                                    2. End Session
                                    """);
                                if(ScanningTools.scanInt()==2)
                                    action = "x";
                            }
                            case 5 -> {

                                System.out.println("Enter new name:");
                                String name = ScanningTools.scanLine();
                                System.out.println("Enter new address:");
                                String address = ScanningTools.scanLine();
                                if(name!=null && !name.isEmpty()){
                                    if(address!=null && !address.isEmpty()){
                                        consumer.setAddress(address);
                                    }
                                    consumer.setName(name);
                                    System.out.println("✅Successfully updated");
                                    System.out.println(consumer);
                                }else{
                                    System.out.println("Profile not updated");
                                }
                                System.out.println("""
                                        \n1. Go back
                                        2. End session
                                        """);
                                if(ScanningTools.scanInt()==2)
                                    action = "x";
                            }
                            case 6 ->{
                                System.out.println(consumer);
                                System.out.println("""
                                    \n1. Go back
                                    2. End Session  
                                    """);
                                if(ScanningTools.scanInt()==2)
                                    action = "x";

                            }
                            case 7 -> {
                                consumer.simulateUsage(30);
                                System.out.println("""
                                    \n✅ Meter updated
                                    1. Go back
                                    2. End Session 
                                    """);
                                if(ScanningTools.scanInt()==2)
                                    action = "x";
                            }
                            case 8 -> {
                                action = "x";
                            }
                            default -> {
                                //ignore
                            }
                        }
                    }
                }
                case 2 -> {
                    run=false;
                }
                default -> {
                    //ignore
                }
            }

        }
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
                    System.out.printf("Successfully paid $%.2f\nPress 0 - Exit payment%n%n",amount);
                    ScanningTools.scanInt();
                    return amount;

                }
            }
        }
    }
        private static void homeScreen(){
        System.out.println("\nCode200 Luminous Power Ltd.\nChoose a service:");
        System.out.println("""
                1. Check pending balance
                2. Meter reading
                3. Self Payment
                4. Usage report
                5. Update Profile
                6. View Profile
                7. Simulate 1 month usage
                8. Exit
                """);
    }


}
