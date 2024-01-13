public class SUV extends PersonalVehicle {
    private boolean AWD;

    public SUV(String make, String model, String color, int year,
                 boolean AWD,double price, int invQuantity){
        super(make,model,color,year,price,invQuantity);
        this.AWD = AWD;
    }

    public String toString(){
        String result = super.toString();
        if(AWD){ result = "AWD SUV: " + result; }
        else{ result = "SUV: " + result; }
        return result;
    }
}
