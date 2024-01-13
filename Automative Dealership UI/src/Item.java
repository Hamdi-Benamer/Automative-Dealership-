import java.io.Serializable;

public abstract class Item implements Serializable, Comparable<Item> {
    private double price;
    private int invQuantity;
    private int soldQuantity;
    private int cartQuantity;
    private int amountOfItemPurchased;


    public Item(double price, int invQuantity){
        this.price = price;
        this.invQuantity = invQuantity;
        this.soldQuantity = 0;
        this.cartQuantity = 0;
    }

    public double sellUnits(int amount){
        if( amount > 0 && invQuantity >= amount){
            invQuantity -= amount;
            soldQuantity += amount;
            System.out.println(amount*price);
            return amount*price;
        }
        return 0;
    }

    public double getPrice(){return price;}
    public int getInvQuantity(){return invQuantity;}
    public int getSoldQuantity(){return soldQuantity;}
    public int getCartQuantity() {return cartQuantity;}
    public void setInvQuantity(int q){invQuantity -= q;}
    public void setSoldQuantity(int q){soldQuantity += q;}
    public void setCartQuantity(int q){cartQuantity += q;}
    public void setInvQuantityTo(int q){invQuantity = q;}


    public int getAmountOfItemPurchased() {
        return amountOfItemPurchased;
    }

    public void setAmountOfItemPurchased(int amountOfItemPurchased) {
        this.amountOfItemPurchased = amountOfItemPurchased;
    }



    public abstract String toString();
    public void updateStockQuantity(int amount){invQuantity += amount;}

    public int compareTo(Item newItem){
        return newItem.soldQuantity - this.soldQuantity;
    }
}
