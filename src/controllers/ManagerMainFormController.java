package controllers;

import Db.DriverDb;
import Db.InParkingDb;
import Db.OnDeliveryDb;
import Db.VehicleDb;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Driver;
import model.Vehicle;
import tm.DriverTm;
import tm.InParkingTm;
import tm.OnDeliveryTm;
import tm.VehicleTm;

import java.io.IOException;
import java.util.Optional;

public class ManagerMainFormController {
    public TableView<DriverTm> tblDriver;
    public TableColumn colDriverName;
    public TableColumn colNic;
    public TableColumn colLicense;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colOptions;
    public TableView<VehicleTm> tblVehicle;
    public TableColumn colVehicleNum;
    public TableColumn colType;
    public TableColumn colMaxWeight;
    public TableColumn colPassengers;
    public TableColumn colVehicleOptions;
    public TableView<InParkingTm> tblInParking;
    public TableColumn colParkingVehicleNum;
    public TableColumn colParkingVehicleType;
    public TableColumn colSlot;
    public TableColumn colParkedTime;
    public TableView<OnDeliveryTm> tblOnDelivery;
    public TableColumn colDeliveryVehicleNum;
    public TableColumn colDeliveryType;
    public TableColumn colDeliveryDriverName;
    public TableColumn colLeftTime;
    public AnchorPane context;
    public JFXButton btnAddVehicle;
    public JFXButton btnDriver;

    public void initialize() {
        colDriverName.setCellValueFactory(new PropertyValueFactory("driverName"));
        colNic.setCellValueFactory(new PropertyValueFactory("nic"));
        colLicense.setCellValueFactory(new PropertyValueFactory("licenseNum"));
        colAddress.setCellValueFactory(new PropertyValueFactory("address"));
        colContact.setCellValueFactory(new PropertyValueFactory("contactNum"));
        colOptions.setCellValueFactory(new PropertyValueFactory("btn"));

        colVehicleNum.setCellValueFactory(new PropertyValueFactory("vehicleNum"));
        colType.setCellValueFactory(new PropertyValueFactory("vehicleType"));
        colMaxWeight.setCellValueFactory(new PropertyValueFactory("maxWeight"));
        colPassengers.setCellValueFactory(new PropertyValueFactory("numOfPassengers"));
        colVehicleOptions.setCellValueFactory(new PropertyValueFactory("btn"));

        colParkingVehicleNum.setCellValueFactory(new PropertyValueFactory("vehicleNum"));
        colParkingVehicleType.setCellValueFactory(new PropertyValueFactory("type"));
        colSlot.setCellValueFactory(new PropertyValueFactory("slotNum"));
        colParkedTime.setCellValueFactory(new PropertyValueFactory("time"));

        colDeliveryVehicleNum.setCellValueFactory(new PropertyValueFactory("vehicleNum"));
        colDeliveryType.setCellValueFactory(new PropertyValueFactory("type"));
        colDeliveryDriverName.setCellValueFactory(new PropertyValueFactory("driverName"));
        colLeftTime.setCellValueFactory(new PropertyValueFactory("time"));

        tblVehicle.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnAddVehicle.setText("UPDATE");
            setVehicleData(newValue);
        });

        tblDriver.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDriver.setText("UPDATE");
            setDriverData(newValue);
        });

        loadAllDrivers();
        loadAllVehicles();
        loadAllInParking();
        loadAllOnDelivery();
    }

    public void setVehicleData(VehicleTm tm) {
        if (tm != null) {
            AddVehicleFormController.vehicleNum = tm.getVehicleNum();
            AddVehicleFormController.type = tm.getVehicleType().toString();
            AddVehicleFormController.weight = tm.getMaxWeight();
            AddVehicleFormController.passengers = tm.getNumOfPassengers();
        }
    }

    public void setDriverData(DriverTm tm) {
        if (tm != null) {
            AddDriverFormController.name=tm.getDriverName();
            AddDriverFormController.nic=tm.getNic();
            AddDriverFormController.address=tm.getAddress();
            AddDriverFormController.license=tm.getLicenseNum();
            AddDriverFormController.tel=tm.getContactNum();
        }
    }

    public void loadAllDrivers() {
        ObservableList obList = FXCollections.observableArrayList();
        for (Driver d :
                DriverDb.drivers) {
            Button btn = new Button("Delete");
            DriverTm tm = new DriverTm(d.getDriverName(), d.getNic(), d.getLicenseNum(), d.getAddress(), d.getContactNum(), btn);

            btn.setOnAction((e) -> {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure?", ButtonType.YES, ButtonType.NO);
                Optional<ButtonType> buttonType = alert.showAndWait();

                if (buttonType.get().equals(ButtonType.YES)) {
                    DriverDb.drivers.remove(d);
                    obList.remove(tm);
                }

            });

            obList.add(tm);
        }
        tblDriver.setItems(obList);
    }

    public void loadAllVehicles() {
        ObservableList obList = FXCollections.observableArrayList();
        for (Vehicle v :
                VehicleDb.vehicles) {
            Button btn = new Button("Delete");
            VehicleTm tm = new VehicleTm(v.getVehicleNum(), v.getVehicleType(), v.getMaxWeight(), v.getNumOfPassengers(), btn);

            btn.setOnAction((e) -> {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure?", ButtonType.YES, ButtonType.NO);
                Optional<ButtonType> buttonType = alert.showAndWait();

                if (buttonType.get().equals(ButtonType.YES)) {
                    VehicleDb.vehicles.remove(v);
                    obList.remove(tm);
                }

            });

            obList.add(tm);
        }
        tblVehicle.setItems(obList);
    }

    public void loadAllInParking() {
        ObservableList obList = FXCollections.observableArrayList();
        obList.addAll(InParkingDb.parkingData);

        tblInParking.setItems(obList);
    }

    public void loadAllOnDelivery() {
        ObservableList obList = FXCollections.observableArrayList();
        obList.addAll(OnDeliveryDb.onDeliveryData);

        tblOnDelivery.setItems(obList);
    }

    public void addVehicleOnAction(ActionEvent actionEvent) throws IOException {
        AddVehicleFormController.btnText = btnAddVehicle.getText().equals("Add Vehicle") ? "ADD" : "UPDATE";
        setUi("AddVehicleForm");
    }

    public void btnVehicleRefreshOnAction(ActionEvent actionEvent) {
        loadAllVehicles();
        btnAddVehicle.setText("Add Vehicle");
    }

    public void addDriverOnAction(ActionEvent actionEvent) throws IOException, InterruptedException {
        AddDriverFormController.btnText = btnDriver.getText().equals("Add Driver") ? "ADD" : "UPDATE";
        setUi("AddDriverForm");
    }

    public void btnDriverRefreshOnAction(ActionEvent actionEvent) throws IOException {
        loadAllDrivers();
        btnDriver.setText("Add Driver");
    }

    private void setUi(String location) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/" + location + ".fxml"))));
        stage.show();
    }
}
