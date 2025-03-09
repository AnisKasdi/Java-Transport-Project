package vehicle;


import journey.City;

public class Bus extends Vehicle {
    public  Bus(String name, int max_capacity,int speed,int kmcost) {
        super(name,max_capacity,speed,kmcost);
    }


    @Override
    boolean compatibleWith(City c) {
        return true;
    }
}


