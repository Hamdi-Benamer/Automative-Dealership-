public abstract class CommercialVehicle extends Vehicle{
    private String carries;

    public CommercialVehicle(String make, String model, int year,
                             String carries,double price, int quantity){
        super(make,model,year,price,quantity);
        this.carries = carries;
    }

    public String getCarries(){return carries;}

    public String toString() {
        return getYear() + " " + getMake() + ", " + getModel() + ", carries " + getCarries() +
                ", price $" + String.format("%,.2f",getPrice()) + " each (" +getInvQuantity()+ " in stock, " + getSoldQuantity() + " sold).";
    }
}
