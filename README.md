Introduction:

  Design patterns are best practices used by software developers to solve common and recurring problems in software design. This project provides hands-on examples of popular design patterns categorized into behavioral, creational, and structural. Each pattern is implemented in a way that demonstrates its usage in real-world scenarios, offering a deeper understanding of its importance and functionality.

Behavioral Design Patterns

1. Observer Pattern
   
Use Case: Weather Monitoring System

The Observer pattern defines a dependency between objects, such that when one object (the WeatherStation) changes state, all dependent objects (like PhoneDisplay and LaptopDisplay) are notified and updated automatically.

Scenario:
The WeatherStation tracks temperature.
Multiple display devices (like PhoneDisplay and LaptopDisplay) are registered to the weather station and are automatically updated when the temperature changes.

Key Concepts:
Subject (WeatherStation): Holds the state and sends notifications to observers.
Observer (Displays): Objects that need to be updated when the subject changes.

2. Strategy Pattern
   
Use Case: Payment System

The Strategy pattern allows selecting different algorithms or behaviors at runtime. In this example, a PaymentContext allows users to select different payment methods, such as CreditCardStrategy or PayPalStrategy, at runtime.

Scenario:

A customer chooses a payment method during checkout.
The system dynamically switches between payment strategies (credit card, PayPal, etc.) based on user input.

Key Concepts:
Context (PaymentContext): Maintains a reference to the strategy and allows switching strategies dynamically.
Strategy (Payment Methods): Defines a family of algorithms (payment methods) that are interchangeable.


Creational Design Patterns

3. Singleton Pattern
Use Case: Database Connection Pool
The Singleton pattern ensures that only one instance of a particular class is created throughout the application's lifecycle. This is ideal for managing resources that are shared across the application, such as a DatabaseConnectionPool.

Scenario:

A DatabaseConnectionPool object is created, ensuring that only one instance is used throughout the system to manage database connections efficiently.
Key Concepts:
Singleton: Ensures only one instance is created and provides global access to that instance.

4. Factory Pattern
Use Case: Vehicle Factory
The Factory pattern allows for the creation of objects without specifying their exact type. This is useful when the exact type of the object may vary depending on input or context.

Scenario:

The VehicleFactory class is responsible for creating different types of vehicles (Car, Truck, Bike) based on user input.
Key Concepts:
Factory (VehicleFactory): Centralized creation logic for different types of objects (vehicles).
Product (Vehicles): The objects being created.

Structural Design Patterns

5. Adapter Pattern
Use Case: Legacy System Integration
The Adapter pattern allows incompatible interfaces to work together. In this case, a PaymentAdapter enables a legacy payment system to integrate with a new, modern interface, without modifying the existing legacy code.

Scenario:

A LegacyPaymentSystem provides payment services, but its interface is not compatible with a modern system.
The PaymentAdapter adapts the old interface to the new one, allowing smooth integration.
Key Concepts:
Adapter (PaymentAdapter): Converts one interface to another so that systems can work together without modification.
Adaptee (Legacy System): The existing interface being adapted.

6. Decorator Pattern
Use Case: Coffee Shop
The Decorator pattern allows adding responsibilities or features to objects dynamically without modifying their structure. In this example, a BasicCoffee can be "decorated" with various features (like Milk, Sugar, etc.).

Scenario:

A customer orders a basic coffee.
The coffee can be enhanced by adding extras like milk and sugar using decorators.
Key Concepts:
Component (BasicCoffee): The base object that can be decorated.
Decorators (Milk, Sugar): Dynamically add extra features to the base object.
