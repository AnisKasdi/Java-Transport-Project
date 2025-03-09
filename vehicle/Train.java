package vehicle;

import journey.City;

public class Train extends Vehicle {
    public  Train(String name, int max_capacity,int speed,int kmcost) {
        super(name,max_capacity,speed,kmcost);
    }

    @Override
    boolean compatibleWith(City c) {
    if(c.gare){
        return true;
    }
    return false;
        }
}


