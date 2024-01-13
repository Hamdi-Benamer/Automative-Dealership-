import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class AutoParkButtonPane extends Pane {

    Button addToCart;
    Button remove;
    Button completeSale;
    Button reset;

   public  AutoParkButtonPane(){
       //Make new buttons
       addToCart = new Button("Add to Cart");
       addToCart.relocate(86,0);
       addToCart.setPrefSize(132, 40);

       remove = new Button("Remove Item");
       remove.relocate(305,0);
       remove.setPrefSize(117, 40);

       completeSale = new Button("Complete Sale");
       completeSale.relocate(435,0);
       completeSale.setPrefSize(117, 40);

       reset = new Button("Reset Stock");
       reset.relocate(620,0);
       reset.setPrefSize(132, 40);

       //add all to the button pane
       getChildren().addAll(addToCart, remove, completeSale, reset);
   }

    public Button getAddToCart() {
        return addToCart;
    }

    public Button getCompleteSale() {
        return completeSale;
    }

    public Button getRemove() {
        return remove;
    }

    public Button getReset() {
        return reset;
    }

}
