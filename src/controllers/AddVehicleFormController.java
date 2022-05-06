package controllers;

import Db.VehicleDb;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.*;

public class AddVehicleFormController {

    public JFXTextField txtVehicleNum;
    public JFXComboBox cmbType;
    public JFXTextField txtMaxWeight;
    public JFXTextField txtPassengers;
    public static String vehicleNum;
    public static String type;
    public static int weight;
    public static int passengers;
    public static String btnText;
    public JFXButton btnAdd;

    public void initialize() {
        cmbType.getItems().addAll("VAN", "BUS", "LORRY");

        btnAdd.setText(btnText);

        txtVehicleNum.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                if (!txtVehicleNum.getText().contains("-")) {
                    new Alert(Alert.AlertType.WARNING, "Invalid Vehicle Number", ButtonType.OK).show();
                    txtVehicleNum.setText("");
                }
            }
        });

        txtMaxWeight.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                validate(txtMaxWeight);
            }
        });

        txtPassengers.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                validate(txtPassengers);
            }
        });

        if (btnAdd.getText().equals("UPDATE")) {
            txtVehicleNum.setText(vehicleNum);
            cmbType.getSelectionModel().select(type);
            txtMaxWeight.setText(String.valueOf(weight));
            txtPassengers.setText(String.valueOf(passengers));
        }
    }

    public void validate(JFXTextField textField) {
        if (!textField.getText().matches("[0-9]*")) {
            new Alert(Alert.AlertType.WARNING, "Invalid Weight", ButtonType.OK).show();
            textField.setText("");
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        if (btnAdd.getText().equals("ADD")) {
            for (Vehicle v :
                    VehicleDb.vehicles) {
                if (v.getVehicleNum().equals(txtVehicleNum.getText())) {
                    new Alert(Alert.AlertType.WARNING, "Already added", ButtonType.OK).show();
                    return;
                }
            }
            Vehicle vehicle;
            switch (VehicleType.valueOf(cmbType.getValue().toString())) {
                case VAN:
                    vehicle = new Van(txtVehicleNum.getText(), VehicleType.VAN, Integer.parseInt(txtMaxWeight.getText()), Integer.parseInt(txtPassengers.getText()));
                    break;
                case BUS:
                    vehicle = new Bus(txtVehicleNum.getText(), VehicleType.BUS, Integer.parseInt(txtMaxWeight.getText()), Integer.parseInt(txtPassengers.getText()));
                    break;
                default:
                    vehicle = new Lorry(txtVehicleNum.getText(), VehicleType.LORRY, Integer.parseInt(txtMaxWeight.getText()), Integer.parseInt(txtPassengers.getText()));
            }
            VehicleDb.vehicles.add(vehicle);
            new Alert(Alert.AlertType.INFORMATION, "Added", ButtonType.OK).show();
        } else {
            for (Vehicle vehicle :
                    VehicleDb.vehicles) {
                if (vehicle.getVehicleNum().equals(txtVehicleNum.getText())) {
                    vehicle.setVehicleNum(txtVehicleNum.getText());
                    vehicle.setVehicleType(VehicleType.valueOf(cmbType.getValue().toString()));
                    vehicle.setMaxWeight(Integer.parseInt(txtMaxWeight.getText()));
                    vehicle.setNumOfPassengers(Integer.parseInt(txtPassengers.getText()));
                    new Alert(Alert.AlertType.INFORMATION, "Updated", ButtonType.OK).show();
                    break;
                }
            }
        }

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
