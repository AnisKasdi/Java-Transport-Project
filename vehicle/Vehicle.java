package vehicle;

import journey.City;

public abstract class Vehicle {
    protected String name;
    protected int max_capacity;
    protected int speed;
    protected int kmcost;

    public Vehicle(String name, int max_capacity, int speed, int kmcost) {
        this.name = name;
        this.max_capacity = max_capacity;
        this.speed = speed;
        this.kmcost = kmcost;
    }

    public String getVehicleName() {
        return this.name;
    }

    public int getVehicleCapacity() {
        return this.max_capacity;
    }

    public int getVehicleSpeed() {
        return this.speed;
    }

    public int getVehicleKmcost() {
        return this.kmcost;
    }

    protected void setVehicleName(String name) {
        this.name = name;

    }
    @Override
    public String toString() {
        return getVehicleName() + " [capacity=" + max_capacity + ", speed=" + speed + ", kmcost=" + kmcost + "]";
    }
    protected void setVehicleCapacity(int capacity) {
        this.max_capacity = capacity;
    }

    protected void setVehicleSpeed(int speed) {
        this.speed = speed;
    }

    protected void setvehicleKmcost(int kmcost) {
        this.kmcost = kmcost;
    }

    public int nbPassengerperDay(int nb_trajet){
        return nb_trajet * this.max_capacity;
    }
    public int nbJourneyPerDay(int distance) {
        double totalHours = (double) distance / speed;


        if (totalHours > 24) {
            return 0;
        }

        int result = (int) (24 / totalHours);
        return result;

    }

    abstract boolean  compatibleWith(City c);



}