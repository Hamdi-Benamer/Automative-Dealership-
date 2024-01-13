public class Van extends CommercialVehicle {
    private boolean covered;

    public Van(String make, String model, int year,
                 String carries, boolean covered,double price, int invQuantity) {
        super(make, model, year, carries,price, invQuantity);
        this.covered = covered;
    }

    public boolean getCovered(){return covered;}

    public String toString(){
        String result = super.toString();
        if(covered){ result = "Covered Van: " + result; }
        else{ result = "Van: " + result; }
        return result;
    }


}
