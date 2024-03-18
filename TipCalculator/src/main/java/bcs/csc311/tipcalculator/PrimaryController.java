

package bcs.csc311.tipcalculator;

import java.io.IOException;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class PrimaryController {

    @FXML
    private TextField amountTF;
    @FXML
    private TextField tipTF;
    @FXML
    private TextField totalTF;
    @FXML
    private RadioButton rb1;
    @FXML
    private RadioButton rb2;
    @FXML
    private RadioButton rb3;
    @FXML
    private ToggleGroup tipPercentageGroup;

    private DoubleProperty tipPercentage = new SimpleDoubleProperty(0);

    public void initialize() {
        rb1.setUserData(15.0);
        rb2.setUserData(20.0);
        rb3.setUserData(25.0);

     
        tipTF.textProperty().bind(tipPercentage.asString("%.2f"));

     
        amountTF.textProperty().addListener((obs, oldValue, newValue) -> calculateTip());
        tipPercentage.addListener((obs, oldValue, newValue) -> calculateTip());
    }

    @FXML
    private void getSelectedTipPercentage() {
        tipPercentage.set((double) tipPercentageGroup.getSelectedToggle().getUserData());
    }

    private void calculateTip() {
        
        try {
            String amt = amountTF.getText();
            double amount = Double.parseDouble(amt);
            double tip = amount * tipPercentage.get() / 100;
            
            double total = amount + tip;
            
//            tipTF.setText(String.format("%.2f", tip));
            totalTF.setText(String.format("%.2f", total));

        } catch (NumberFormatException e) {
            
            System.out.println("inside catch");
            tipTF.setText("");
//            totalTF.setText("");
        }
    }
}
