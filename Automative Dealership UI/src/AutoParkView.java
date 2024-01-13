import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class AutoParkView extends Pane {
    AutoPark model;

    private ListView<String> inventoryList;
    private ListView<String> cartList;
    private ListView<String> popularList;
    private TextField salesField;
    private TextField revenueField;
    private TextField averageSaleField;
    private AutoParkButtonPane buttonPane;
    private double currentCartTotal;
    private Label label2;


    public AutoParkView(AutoPark initModel) {
        model = initModel;

        //making labels
        Label label1 = new Label("Park Inventory: ");
        label1.relocate(20, 30);
        label1.setPrefSize(265, 10);

        //making the Listviews
        inventoryList = new ListView<>();
        inventoryList.relocate(20, 60);
        inventoryList.setPrefSize(265, 270);

        //making labels
        label2 = new Label("Current Cart ($" + currentCartTotal + "):" );
        label2.relocate(295, 30);
        label2.setPrefSize(265, 10);

        //making the Listviews
        cartList = new ListView<>();
        cartList.relocate(295, 60);
        cartList.setPrefSize(265, 270);

        //making labels
        Label label3 = new Label("Park Summary: ");
        label3.relocate(570, 30);
        label3.setPrefSize(230, 10);

        //making the Listviews
        popularList = new ListView<>();
        popularList.relocate(570, 210);
        popularList.setPrefSize(210, 120);


        //making labels
        Label label4 = new Label("# Sales: ");
        label4.relocate(580, 60);
        label4.setPrefSize(50, 30);

        //Making TextField
        salesField = new TextField();
        salesField.relocate(665, 60);
        salesField.setPrefSize(115, 30);
        salesField.setText("0");


        //making labels

        Label label5 = new Label("Revenue: ");
        label5.relocate(580, 95);
        label5.setPrefSize(60, 40);

        revenueField = new TextField();
        revenueField.relocate(665, 100);
        revenueField.setPrefSize(115, 30);
        revenueField.setText("0.00");


        //making labels

        Label label6 = new Label("$ / Sale: ");
        label6.relocate(580, 140);
        label6.setPrefSize(50, 30);

        //Making TextField
        averageSaleField = new TextField();
        averageSaleField.relocate(665, 140);
        averageSaleField.setPrefSize(115, 30);
        averageSaleField.setText("N/A");


        //making labels
        Label label7 = new Label("Most Popular Items: ");
        label7.relocate(570, 180);
        label7.setPrefSize(230, 10);

        buttonPane = new AutoParkButtonPane();
        buttonPane.relocate(0, 340);
        buttonPane.setPrefSize(400, 50);

        currentCartTotal = 0;

        getChildren().addAll(label1, label2, label3, label4, label5, label6, label7, inventoryList, cartList, popularList, salesField, revenueField, averageSaleField, buttonPane);
        update(model);
    }


    public Label getLabel2() {
        return label2;
    }

    public ListView<String> getCartList() {
        return cartList;
    }

    public ListView<String> getInventoryList() {
        return inventoryList;
    }

    public ListView<String> getPopularList() {
        return popularList;
    }

    public TextField getAverageSaleField() {
        return averageSaleField;
    }

    public TextField getRevenueField() {
        return revenueField;
    }

    public TextField getSalesField() {
        return salesField;
    }


    public AutoParkButtonPane getButtonPane() {
        return buttonPane;
    }

    public double getCurrentCartTotal() {
        return currentCartTotal;
    }

    public void setCurrentCartTotal(double currentCartTotal) {
        this.currentCartTotal = currentCartTotal;
    }

    public void update(AutoPark model) {
        Item[] item = model.getItems().toArray(new Item[0]);
        String[] name = new String[model.getTotalItem()];
        for (int i = 0; i < model.getTotalItem(); i++) {
            name[i] = String.valueOf((item[i]));
        }
        ObservableList<String> list = FXCollections.observableArrayList(name);
        inventoryList.setItems(list);
        popularList.setItems(model.getXPopularItems(3));


        Item[] item2 = model.getCurrentCart().toArray(new Item[0]);
        String[] name2 = new String[model.getTotalCartItem()];
        for (int i = 0; i < model.getTotalCartItem(); i++) {
            name2[i] = ((item2[i].getAmountOfItemPurchased() +" "+(item2[i])));
        }
        ObservableList<String> list2 = FXCollections.observableArrayList(name2);
        cartList.setItems(list2);


        int index = inventoryList.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            buttonPane.getAddToCart().setDisable(false);
        } else {
            buttonPane.getAddToCart().setDisable(true);
        }

        if(model.getCurrentCart().isEmpty()){
            buttonPane.getCompleteSale().setDisable(true);
        }else{
            buttonPane.getCompleteSale().setDisable(false);
        }

        label2.setText("Current Cart ($" + currentCartTotal + "):" );


        int index2 = cartList.getSelectionModel().getSelectedIndex();
        if (index2 >= 0) {
            buttonPane.getRemove().setDisable(false);
        } else {
            buttonPane.getRemove().setDisable(true);
        }


    }
}




