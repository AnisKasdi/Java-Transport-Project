package journey;

import vehicle.Vehicle;

public class Journey  {
    private City City1;
    private City City2;
    private int distance;
    private int passengerTicket;
    private Vehicle[] vehicle;
    private int VehicleCount;
    public static final int MAX_VEHICLE = 100;


    public Journey(City city1, City city2, int distance, int passengerTicket, Vehicle[] vehicle, int vehicleCount) {
        this.City1 = city1;
        this.City2 = city2;
        this.distance = distance;
        this.passengerTicket = passengerTicket;
        this.vehicle = new Vehicle[MAX_VEHICLE];
        this.VehicleCount = vehicleCount;// normalement 0 au début
    }

    public City getCity1() {
        return this.getCity1();

    }

    public City getCity2() {
        return this.getCity2();
    }

    public void setCity1(City city) {
        this.City1 = city;
    }

    public void setCity2(City city) {
        this.City2 = city;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return this.distance;
    }

    public int getPassengerTicket() {
        return this.passengerTicket;
    }

    public void setPassengerTicket(int passengerTicket) {
        this.passengerTicket = passengerTicket;
    }

    public boolean addVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            System.out.println("Veuillez entrer un paramètre valide");
            return false;

        }
        if (this.VehicleCount >= this.MAX_VEHICLE) {
            System.out.println("Taille maximum atteinte ! Impossible d'ajouter un nouveau véhicule");
            return false;
        } else {
            this.vehicle[this.VehicleCount] = vehicle;
            VehicleCount++;
            System.out.println("Vehicule ajouté avec succès !");
            if (this.VehicleCount == this.MAX_VEHICLE) {
                System.out.println("Attention vous avez atteint le plafond du nombre max de véhicule pouvant être ajoutée ");


            }
            return true;
        }

    }

    public int passangersperday() {
        int result = 0;

        // Parcourir tous les véhicules dans le tableau
        for (int i = 0; i < this.VehicleCount; i++) {
            Vehicle currentVehicle = this.vehicle[i]; // Récupérer le véhicule actuel
            int nb_trajet = currentVehicle.nbJourneyPerDay(getDistance()); // Nombre de trajets pour ce véhicule
            result += currentVehicle.nbPassengerperDay(nb_trajet); // Ajouter les passagers transportés
        }

        return result;
    }

    public boolean contains(Vehicle vehicle) {
        if (vehicle != null) {
            for (int i = 0; i < this.VehicleCount; i++) {
                if (this.vehicle[i].equals(vehicle)) {
                    return true;
                }
            }

        }
        return false;
    }

    public int costPerDay(){
        int result=0;
        for(int i=0;i<this.VehicleCount;i++){
            Vehicle current=this.vehicle[i];
            result+=current.getVehicleKmcost()*getDistance();

        }
        return result;

    }

    public int incomePerDay() {
        int result = 0;

        // Parcourir tous les véhicules
        for (int i = 0; i < this.VehicleCount; i++) {
            Vehicle currentVehicle = this.vehicle[i];

            // Calculer le revenu généré par ce véhicule
            int passengersPerDay = currentVehicle.nbPassengerperDay(currentVehicle.nbJourneyPerDay(distance));
            result += passengersPerDay * this.passengerTicket;
        }

        return result;
    }
    public int benefitPerDay(){
        return this.incomePerDay()-this.costPerDay();
    }
    @Override
    public String toString() {
        return "Journey from " + City1.getNameOfCity() + " to " + City2.getNameOfCity() + " (" + distance + "km)";
    }
    }
