Transportation System
Overview
Transportation System is a Java application that simulates the management of transportation routes between cities using vehicles such as trains and buses. The project calculates daily passenger counts, revenue, costs, and profits for each journey. It features a simple graphical user interface (GUI) built with Swing to interact with the system.

This project was designed to demonstrate object-oriented programming (OOP) skills in Java, as well as the integration of a graphical user interface.

Features
Add Vehicles: Create trains or buses with a name, maximum capacity, speed, and cost per kilometer.
Add Journeys: Define routes between two cities, including distance and ticket price. Cities can have or lack a train station (affects train compatibility).
Link Vehicles to Journeys: Assign vehicles to journeys, with compatibility checks (e.g., trains require stations).
Statistics: View daily passengers, revenue, costs, and profits per journey, along with a total profit figure.
Graphical Interface: A window with buttons and dialogs for user-friendly interaction.
Technologies
Language: Java
UI Library: Swing (javax.swing)
Concepts: Inheritance, abstraction, encapsulation, user input handling.

Project structure
transportation-system/
├── journey/
│   ├── City.java         # Class representing a city
│   └── Journey.java      # Class managing a journey
├── vehicle/
│   ├── Vehicle.java      # Abstract class for vehicles
│   ├── Train.java        # Implementation for trains
│   └── Bus.java          # Implementation for buses
└── TransportationGUI.java # Main class with the graphical interface

Installation
Prerequisites:
Java Development Kit (JDK) 8 or higher installed.
A terminal or an IDE (e.g., IntelliJ IDEA or Eclipse).

Clone the project with :
git clone https://github.com/AnisKasdi/Java-Transport-Project/
cd to the folder

Compile the Project:

javac Transportation.java

Run it 
java Transportation

Usage
Launch the application using the command above.
A window will open with five buttons:
Add Vehicle: Add a train or bus via a dialog.
Add Journey: Create a route between two cities.
Add Vehicle to Journey: Assign a vehicle to a journey.
Show Statistics: Display details in the text area.
Exit: Close the application.
Follow the prompts in the dialogs to input data.
Example
Add a train "TGV" (capacity: 1032, speed: 300 km/h, cost: 100/km).
Create a journey from "Paris" (with station) to "Bordeaux" (with station), 600 km, ticket price 50.
Link the TGV to the journey.
View stats to see passengers, revenue, and profits.
Limitations
No data persistence (all data is in memory and lost on exit).
Basic graphical interface (no advanced design).
Limited error handling (invalid inputs are caught, but validation is minimal).
Future Improvements
Add data persistence to a file (e.g., JSON or CSV).
Enhance the interface with tables or charts.
Implement unit tests with JUnit.
Add options like removing vehicles or journeys.
Contributing
Contributions are welcome! To suggest improvements:

Author
Kasdi Anis– Anis.kasdi2003@gmail.com

