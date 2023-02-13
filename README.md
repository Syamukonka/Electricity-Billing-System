# Electricity-Billing-System
_Syamukonka @ code200_

### Quick Start Guide
To run this application, open and run the `Main.java` class. Use the console to interact with the application. You'll be presented with two applications, the **Billing Application** and **Customer Application**. You can interact with any of them. Each application will keep running until you prompt it to exit with the specified option. 


### Project details
This EBS project is a console based application that simulates the processing of electricity bills and other related services. It comprises to sub-applications namely: Customer's Application and Billing Company's Application<br>

The project has two main packages: namely, **8Entities** and **Executors**.

#### Entities
The following Classes have been used under **entities**:

1. `Consumer` is an abstract class, it defines a high-level entity that consumes electricity and considered as a customer by the billing company. This may be a person, company, organization and the like. Hence it is used as a parent class for more specific children entities. <br>
This class defines the general fields such as `name`, `address`, `meter` and a few more.<br>
    - It also contains some abstract methods that need to be implemented by its subclass. These methods include: `getVersion()`, `getVersionCode()`, `getID()`, and more.
    - It's predifined methods include `getName()`, `getAddress()`, `getMeter()`, `makePayment()` and more.
    
2. `DomesticConsumer`, `CommercialConsumer`, and `GovernmentConsumer` are subclasses of the `Consumer` abstract class. They defined there own fields such as `id`, `version`, `versionCode`, static final `BILL_RATE` and more. 

3. `Meter` class defines some specific fields such as meter `number`, meter `reading`. It contains one nested class `Log` that basically stores a single usage record for a specified time period. For example, 210Kwh in 28 days. With that said, it contains two fields, namely: `consumption` and `duration` (in days) . The `Meter` class also allows to update the meter reading by calling the `recordNewReading()` method. Calling this method also creates a new log that is stored as history in a array of Logs.

4. the interface `ScanningTools` has been create to enable safe operations while using the `Scanner` class with `System.in`. The opertations are surrounded by try and catch blocks. This will handle invalid inputs by preventing runtime errors, and giving the user another chance to enter a correct input.
5. `UsageReport` has one static method `generate()`, that generates a report on the usage of electricity for a given consumer for a specified time. Thus, it takes two arguments, `Consumer consumer` and `int months`. It prints some stats such as average consumption(monthly/daily), average cost(monthly and daily) for the specified number of months. It also displays usage for these specified number of months.

#### Executors
The following Classes have been used under **executors**:

1. `CustomerApplication` defines the customer side application, where the customer can use/manage the electricity related services. This class implements the `ScanningTools` interface. In this app you can do the following:
    >- Check pending balance
    >- Meter reading
    >- Self Payment
    >- Usage report
    >- Update Profile
    >- View Profile<br>
2. `BillManagerApplication` defines the billing side application, where the billing company manages its customers and bills. This class also implements the `ScanningTools` interface.
    >- Show customers
    >- Show customer details
    >- Process a Payment
    >- Register a new customer
    >- Update meter readings
    >- Show all customers' details
3. `Main` this class puts together the `CustomerApplication` and `BillManagerApplication` in one interface and you can run any one at a given time.

__NOTE__: the application has no permenent storage, so, all changes made while interacting with the applicaition are discarded when the execution closes.


Thanks,
Syamukonka
