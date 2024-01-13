import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.time.MonthDay;
import java.util.Arrays;
import java.util.Map;

public class AutoParkApp extends Application {

    AutoPark model;
    AutoParkView view;


    public void start(Stage stage) throws Exception {
        model = AutoPark.createPark();
        view = new AutoParkView(model);


        //creating the stage
        stage.setTitle(AutoPark.createPark().getName());
        stage.setResizable(false);
        stage.setScene(new Scene(view, 800, 400)); // Set size of window
        stage.show();

        view.update(model);



        view.getButtonPane().getAddToCart().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                handleAdd();
            }
        });

        view.getInventoryList().setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                handleInventoryListSelection();
            }
        });
        view.getCartList().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                handleCartListSelection();
            }
        });

        view.getButtonPane().getCompleteSale().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                handleCompleteSale();
            }
        });

        view.getButtonPane().getRemove().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                handleRemove();
            }
        });


        view.getButtonPane().getReset().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                //create a new model and update it so that everything resets
                model = AutoPark.createPark();
                view.update(model);
                view.getSalesField().setText("");
                view.getRevenueField().setText("");
                view.getAverageSaleField().setText("");
            }
        });


    }

    public static void main(String[] args) {
        launch(args);
    }

    public void handleInventoryListSelection() {
        //call update from view
        view.update(model);
    }

    public void handleCartListSelection() {
        //call update from view
        view.update(model);
    }


    public void handleAdd() {

        int index = view.getInventoryList().getSelectionModel().getSelectedIndex();
        Item newItem = model.getItems().get(index);
        if (index >= 0) {
            //if the item is already in the cart only update the quantities
            if (model.getCurrentCart().contains(newItem)) {
                //I made it so that adding to the cart adds to the amount sold,
                // if the sale goes through the number will stay the same but if the item is
                // removed from cart i will decrease the amount sold
                model.updateQuantitites(index);
            } else {
                //if the item is not in the cart add it
                model.addToCurrentCart(index);

            }
            //disable the add button after its been used
            view.getButtonPane().getAddToCart().setDisable(false);

            //if there is no remaining inventory remove the item
            if (newItem.getInvQuantity() == 0) {
                model.removeItem(index);
            }
        }
        //add the price of item to the current cart total price
        view.setCurrentCartTotal(view.getCurrentCartTotal() + newItem.getPrice());

        //update
        view.update(model);
    }

    public void handleRemove() {

        //get the selected item
        int index2 = view.getCartList().getSelectionModel().getSelectedIndex();
        Item newitem = model.getCurrentCart().get(index2);
        //decrease amount purchased once it is added to the cart
        newitem.setAmountOfItemPurchased(newitem.getAmountOfItemPurchased() - 1);

        //if item is removed from cart reduce the cart quantity and amount sold
        model.getCurrentCart().get(index2).setCartQuantity(-1);
        model.getCurrentCart().get(index2).setSoldQuantity(-1);
        //update the cart total with every change to the cart items
        view.setCurrentCartTotal(view.getCurrentCartTotal() - newitem.getPrice());

        if (index2 >= 0) {

            //if the item is already in the inventrory only add to the inventory quantity and if the sold quantity is 0 remove it from cart
            if (model.getItems().contains(model.getCurrentCart().get(index2))) {
                newitem.updateStockQuantity(+1);
                if (newitem.getSoldQuantity() == 0) {

                    model.removeItemFromCart(model.getCurrentCart().indexOf(newitem));
                }
            } else {
                    //if the item is not in inventory list
                    if (!model.getItems().contains(newitem)) {
                        //update toa  random quantity so that I can differentiate between it and other items
                        newitem.updateStockQuantity(+60);
                        if (newitem.getInvQuantity() > 50) {
                            //add the item
                            model.addItem(newitem);
                            //change the quantity to 1 when the item is back in inventory
                            newitem.setInvQuantityTo(1);
                        }
                        //if amount of item purchased is zero remove it from the item
                        if (model.getCurrentCart().get(index2).getSoldQuantity() == 0) {
                            model.removeItemFromCart(index2);
                        }

                    }

                }
            }
        //update
        view.update(model);

    }



    public void handleCompleteSale() {
        for (int i = 0; i < model.getCurrentCart().size(); i++) {
            //if the item is not already in the completed sales array
            //add it, this is done so that we dont get multiple instances
            // of the same item in the get popular list view
            if (!model.getCompletedSales().contains(model.getCurrentCart().get(i))) {
                model.getCompletedSales().add(model.getCurrentCart().get(i));
            }
            model.removeItemFromCart(i);
        }

            for (int i = 0; i < model.getCurrentCart().size(); i++) {
                model.removeItemFromCart(i);
            }


            //update the textfields and the cart total when a sale goes through
            model.setCompletedSalesInt(+1);
            view.getSalesField().setText("" + model.getCompletedSalesInt());
            model.setRevenue(model.getRevenue() + view.getCurrentCartTotal());
            view.getRevenueField().setText("" + model.getRevenue());
            view.setCurrentCartTotal(0);
            view.getAverageSaleField().setText("" + model.calculateAverage());
            view.update(model);

        }

    }

