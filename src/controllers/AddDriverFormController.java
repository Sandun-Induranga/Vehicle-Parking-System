package controllers;

import Db.DriverDb;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Driver;

public class AddDriverFormController {
    public JFXTextField txtName;
    public JFXTextField txtNic;
    public JFXTextField txtLicenseNum;
    public JFXTextArea txtAddress;
    public JFXTextField txtTel;
    public static String name;
    public static String nic;
    public static String license;
    public static String address;
    public static String tel;
    public static String btnText;
    public JFXButton btnAdd;

    public void initialize(){

        btnAdd.setText(btnText);

        txtName.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                validate(txtName);
            }
        });

        txtNic.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                validate(txtNic);
            }
        });

        txtLicenseNum.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                validate(txtLicenseNum);
            }
        });
        txtTel.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                validate(txtTel);
            }
        });
        if (btnAdd.getText().equals("UPDATE")){
            txtName.setText(name);
            txtNic.setText(nic);
            txtTel.setText(tel);
            txtAddress.setText(address);
            txtLicenseNum.setText(license);
        }
    }

    public void validate(JFXTextField textField){
        if(textField.getText().equals("")){
            new Alert(Alert.AlertType.WARNING,"Invalid Detail",ButtonType.OK).show();
            textField.setText("");
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        if (btnAdd.getText().equals("ADD")){
            for (Driver d:
                 DriverDb.drivers) {
                if (d.getNic().equals(txtNic.getText())){
                    new Alert(Alert.AlertType.WARNING,"Already added",ButtonType.OK).show();
                    return;
                }
            }
            Driver driver = new Driver(txtName.getText(),txtNic.getText(),txtLicenseNum.getText(),txtAddress.getText(),txtTel.getText());
            DriverDb.drivers.add(driver);
            new Alert(Alert.AlertType.INFORMATION,"Added", ButtonType.OK).show();
        }else {
            for (Driver driver:
                 DriverDb.drivers) {
                if (driver.getNic().equals(txtNic.getText())){
                    driver.setNic(txtNic.getText());
                    driver.setDriverName(txtName.getText());
                    driver.setAddress(txtAddress.getText());
                    driver.setContactNum(txtTel.getText());
                    driver.setLicenseNum(txtLicenseNum.getText());
                    new Alert(Alert.AlertType.INFORMATION,"Updated", ButtonType.OK).show();
                    break;
                }
            }
        }
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
