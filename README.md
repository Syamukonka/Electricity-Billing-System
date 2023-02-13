# Electricity-Billing-System

This EBS project is a console based application that simulates the processing of electricity bills and other related services. It comprises to sub-applications namely: Customer's Application and Billing Company's Application<br>

The following Classes have been used to complate the task:

1. `Consumer`: an abstract class, it defines an high-level entity that consumes electricity and considered as a customer by the billing company. This may be a person, company, organization and the like.<br>
This class defines the general fields such as `name`, `address`, `meter` and a few more.<br>
    - It also contains some abstract methods that need to be implemented by its subclass. These methods include: `getVersion()`, `getVersionCode()`, `getID()`, and more.
 
    - It's predifined methods include `getName()`, `getAddress()`, `getMeter()`, `makePayment()` and more.
    
2. `DomesticConsumer`, `CommercialConsumer`, and `GovernmentConsumer` are subclasses of the `Consumer` abstract class. They defined there own fields such as `id`, `version`, `versionCode`, static final `BILL_RATE` and more. 

3. `Meter` class defines some specific fields such as meter `number`, meter `reading`. It contains one nested class `Log` this basically stores a usage record for a specified time period. For example, 210Kwh in 28 days. With that said, it contains two fields, namely: `consumption` and `duration` (in days) . The `Meter` class also allows to update the meter reading by calling the `recordNewReading()` method. Calling this method also creates a new log for that is stored as history in a array of Logs.

__to be continued__...
