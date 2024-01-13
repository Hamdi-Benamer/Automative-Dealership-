public class Sedan extends PersonalVehicle {
    public Sedan(String make, String model, String color, int year, double price, int invQuantity){
        super(make,model,color,year,price,invQuantity);
    }

    public String toString(){
        return "Sedan: " + super.toString();
    }

}
