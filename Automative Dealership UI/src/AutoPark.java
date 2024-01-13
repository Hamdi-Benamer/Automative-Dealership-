import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class AutoPark implements Serializable {
    public final int MAX_ITEMS = 10;
    public static int totalItem;
    public static int totalCartItem;

    private double currentCartPrice;

    private String name;
    private double revenue;
    private ArrayList<Item> items;

    private ArrayList<Item> currentCart;

    private ArrayList<Item> completedSales;
    public int completedSalesInt;


    public AutoPark(String name) {
        this.name = name;
        revenue = 0;
        items = new ArrayList<>();
        totalItem = 0;
        currentCart = new ArrayList<>();
        totalCartItem = 0;
        currentCartPrice = 0;
        completedSales = new ArrayList<>();
        completedSalesInt = 0;
    }

    //getters and setters
    public ArrayList<Item> getItems() {
        return items;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public int getCompletedSalesInt() {
        return completedSalesInt;
    }

    public int getTotalItem() {
        return totalItem;
    }

    public void setCompletedSalesInt(int q) {
        completedSalesInt += q;
    }


    public String getName() {
        return name;
    }

    public ArrayList<Item> getCurrentCart() {
        return currentCart;
    }


    public static int getTotalCartItem() {
        return totalCartItem;
    }

    public ArrayList<Item> getCompletedSales() {
        return completedSales;
    }


    //Adds an item and returns true if there is space in the array
    //Returns false otherwise
    public boolean addItem(Item newItem) {
        if (totalItem < MAX_ITEMS) {
            items.add(newItem);
            //items[totalItem] = newItem;
            totalItem++;
            return true;
        }
        return false;
    }

    //updates the quantites of the inventory listview
    public boolean updateQuantitites(int index) {
        items.get(index).setSoldQuantity(items.get(index).getInvQuantity() - (items.get(index).getInvQuantity() - 1));
        items.get(index).setCartQuantity((items.get(index).getCartQuantity() + 1));
        items.get(index).setInvQuantity(items.get(index).getInvQuantity() - (items.get(index).getInvQuantity() - 1));
        items.get(index).setAmountOfItemPurchased(items.get(index).getAmountOfItemPurchased() + 1);
        return true;
    }



    //used to add from inventory to cart
    public boolean addToCurrentCart(int index) {
        if (totalCartItem < MAX_ITEMS) {
            if (items.get(index) != null) {
                //add the item to cart
                currentCart.add(items.get(index));
                //update the array size
                totalCartItem++;

                //update the quantitites
                items.get(index).setSoldQuantity(items.get(index).getInvQuantity() - (items.get(index).getInvQuantity() - 1));
                items.get(index).setCartQuantity((items.get(index).getCartQuantity() + 1));
                items.get(index).setInvQuantity(items.get(index).getInvQuantity() - (items.get(index).getInvQuantity() - 1));
                items.get(index).setAmountOfItemPurchased(items.get(index).getSoldQuantity());
                return true;
            }

            return true;

        }
        return false;
    }

    //used to remove from items listview
    public boolean removeItem(int index) {

        if (index >= 0 && index < totalItem) {
            items.remove(items.get(index));
            //update the array variable indicating variable size after
            // removing, it was already used multiple times so I did not remove
            // it
            totalItem--;
            return true;
        }
        return false;
    }

    //used to remove from items cart view
    public boolean removeItemFromCart(int index) {

        if (index >= 0 && index <= totalCartItem) {
            currentCart.remove(currentCart.get(index));
            //update the array variable indicating variable size after removing
            totalCartItem--;
            return true;
        }
        return false;
    }


    public ObservableList<String> getXPopularItems(int x) {
        ObservableList<String> sorted = FXCollections.observableArrayList();
        //if the integer inputted is zero or negative an empty array will be returned
        if (x <= 0) {
            return sorted;
        } else if (x > completedSales.size()) {
            //if the integer inoutted is larger than the size of items (array), the array will be sorted and al of it will be returned
            Collections.sort(completedSales);
            return FXCollections.observableArrayList(completedSales.toString());
        } else {
            //else sort the array and return the top "x" most popular items using a for loop
            Collections.sort(completedSales);
            for (int i = 0; i < x; i++) {
                sorted.add(String.valueOf(completedSales.get(i)));
            }
        }
        return sorted;


    }

   /* public double calculateRevenue() {
        //iterate and add the prices
       *//* for (Item i : completedSales) {
            revenue += i.getPrice();
        }
        return revenue;*//*
    }*/

    public double calculateAverage() {
        //get the average by diving revue by the number of sales
        double average = revenue / completedSalesInt;
        return average;
    }


        public static AutoPark createPark () {
            AutoPark park1 = new AutoPark("VroomVille Vehicle Haven");
            Sedan s1 = new Sedan("Hyundai", "Sonata", "Black", 2020, 35000, 2);
            Sedan s2 = new Sedan("BMW", "3 Series", "White", 2022, 42000, 10);
            park1.addItem(s1);
            park1.addItem(s2);
            SUV suv1 = new SUV("Chevy", "Trailblazer", "Red", 2021, true, 32000, 10);
            SUV suv2 = new SUV("Jeep", "Grand Cherokee", "Green", 2018, false, 21000, 10);
            park1.addItem(suv1);
            park1.addItem(suv2);
            Truck t1 = new Truck("Toyota", "Tacoma", 2019, "goods", true, 28000, 10);
            Truck t2 = new Truck("Ford", "Ranger", 2022, "equipment", false, 30000, 10);
            park1.addItem(t1);
            park1.addItem(t2);
            Van v1 = new Van("Ford", "Transit", 2020, "goods", true, 22000, 10);
            Van v2 = new Van("Ram", "ProMaster", 2019, "equipment", false, 19000, 10);
            park1.addItem(v1);
            park1.addItem(v2);
            Tire tire1 = new Tire(10, 30, true, 390, 20);
            Tire tire2 = new Tire(12, 35, false, 320, 20);
            park1.addItem(tire1);
            park1.addItem(tire2);
            return park1;
        }
    }

