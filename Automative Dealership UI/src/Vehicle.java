public abstract class Vehicle extends Item {
    private String make;
    private String model;
    private int year;

    public Vehicle(String make, String model, int year,double price, int quantity){
        super(price, quantity);
        this.make = make;
        this.model = model;
        this.year = year;
   }

   public String getMake(){return make;}
   public String getModel(){return model;}
   public int getYear(){return year;}
}
