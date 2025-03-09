package journey;

public  class City {
    public boolean gare;
    String name;
    public City(String name,boolean bool){
        this.name=name;
        this.gare=bool;
    }
    public void setNameOfCity(String name){
        this.name=name;
    }
    public void setGareOrNo(boolean gare){
        this.gare=gare;
    }
    public String getNameOfCity(){
        return this.name;
    }
    public boolean getGareOrNo(){
        return this.gare;
    }

}
