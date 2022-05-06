package controllers;

import Db.*;
import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;
import tm.OnDeliveryTm;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;

import static model.VehicleType.*;


public class MainFormController {
    public ComboBox cmbVehicleNum;
    public ComboBox cmbDriver;
    public Label lblType;
    public Label lblSlotNumber;
    public static int slotNum;
    public JFXButton btnPark;
    public JFXButton btnShift;
    public Label lblClock;
    public Label lblDay;
    public AnchorPane mainContext;

    public void initialize() {
        startClock();
        setVehicles();
        setDrivers();

        lblDay.setText(LocalDate.now().toString());

        cmbVehicleNum.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            for (int i = 0; i < SlotDb.vehicleArray.length; i++) {
                if (SlotDb.vehicleArray[i] != null && SlotDb.vehicleArray[i].getVehicleNum().equals(cmbVehicleNum.getValue().toString())) {
                    btnPark.setDisable(true);
                    btnShift.setDisable(false);
                    cmbDriver.setDisable(false);
                    break;
                } else if (SlotDb.vehicleArray.length - 1 == i) {
                    btnShift.setDisable(true);
                    btnPark.setDisable(false);
                    cmbDriver.setDisable(true);
                }
            }
            int i = 0;
            for (Vehicle v1 :
                    SlotDb.vehicleArray) {
                if (v1 != null && v1.getVehicleNum().equals(cmbVehicleNum.getValue().toString())) {
                    lblSlotNumber.setText(null);
                    lblType.setText(v1.getVehicleType().toString());
                    break;
                } else if (SlotDb.vehicleArray.length - 1 == i) {
                    for (Vehicle v :
                            VehicleDb.vehicles) {
                        if (cmbVehicleNum.getValue().toString().equals(v.getVehicleNum())) {
                            lblType.setText(v.getVehicleType().toString());
                            switch (v.getVehicleType()) {
                                case VAN:
                                    setSlot(Van.vanSlots);
                                    break;
                                case BUS:
                                    setSlot(Bus.busSlots);
                                    break;
                                case LORRY:
                                    setSlot(Lorry.lorrySlots);
                                    break;
                            }
                        }
                    }
                }
                i++;
            }
        });
    }

    private void startClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.millis(1000 - Calendar.getInstance().get(Calendar.MILLISECOND)), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Calendar cal = Calendar.getInstance();
                int second = cal.get(Calendar.SECOND);
                int minute = cal.get(Calendar.MINUTE);
                int hour = cal.get(Calendar.HOUR);
                String am_pm = (cal.get(Calendar.AM_PM) == 0) ? "AM" : "PM";
                lblClock.setText(String.format("%02d : %02d : %02d %s", hour, minute, second, am_pm));
            }
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    private void setSlot(int[] slots) {
        for (int i = 0; i < slots.length; i++) {
            if (SlotDb.vehicleArray[slots[i] - 1] == null) {
                lblSlotNumber.setText(String.valueOf(slots[i]));
                slotNum = Integer.parseInt(lblSlotNumber.getText());
                break;
            }
        }
    }

    public void setVehicles() {
        ObservableList vehicleList = FXCollections.observableArrayList();
        for (Vehicle v :
                VehicleDb.vehicles) {
            vehicleList.add(v.getVehicleNum());
        }
        cmbVehicleNum.getItems().addAll(vehicleList);

    }

    public void setDrivers() {
        ObservableList driverList = FXCollections.observableArrayList();
        for (Driver d :
                DriverDb.drivers) {
            driverList.add(d.getDriverName());
        }
        cmbDriver.getItems().addAll(driverList);
    }


    public void parkOnAction(ActionEvent actionEvent) {
        if (lblSlotNumber.getText().equals("")) {
            new Alert(Alert.AlertType.WARNING, "Vehicle Number required", ButtonType.OK).show();
            return;
        }
        OnDeliveryDb.onDeliveryData.removeIf(tm -> tm.getVehicleNum().equals(cmbVehicleNum.getValue().toString()));
        Vehicle v1;
        for (Vehicle v :
                VehicleDb.vehicles) {
            if (cmbVehicleNum.getValue().toString().equals(v.getVehicleNum())) {
                switch (VehicleType.valueOf(lblType.getText())) {
                    case VAN:
                        v1 = new Van();
                        v1.park(v.getVehicleNum());
                        break;
                    case BUS:
                        v1 = new Bus();
                        v1.park(cmbVehicleNum.getValue().toString());
                        break;
                    case LORRY:
                        v1 = new Lorry();
                        v1.park(cmbVehicleNum.getValue().toString());
                        break;
                }
                new Alert(Alert.AlertType.INFORMATION, "Done", ButtonType.OK).showAndWait();
                cmbVehicleNum.getSelectionModel().select("Vehicle Number");
                lblType.setText(null);
                lblSlotNumber.setText(null);
                break;
            }
        }
    }

    public void onDeliveryOnAction(ActionEvent actionEvent) {
        for (OnDeliveryTm tm :
                OnDeliveryDb.onDeliveryData) {
            if (cmbDriver.getValue().toString().equals(tm.getDriverName())) {
                new Alert(Alert.AlertType.WARNING, "Driver name is invalid").show();
                return;
            }
        }
        if (cmbDriver.getSelectionModel().isEmpty() || cmbDriver.getValue().toString().equals("Driver Name")) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Driver name is required", ButtonType.OK);
            alert.show();
            return;
        } else {
            Vehicle v1;
            switch (VehicleType.valueOf(lblType.getText())) {
                case VAN:
                    v1 = new Van();
                    v1.leavePark(cmbVehicleNum.getValue().toString(), cmbDriver.getValue().toString());
                    break;
                case BUS:
                    v1 = new Bus();
                    v1.leavePark(cmbVehicleNum.getValue().toString(), cmbDriver.getValue().toString());
                    break;
                case LORRY:
                    v1 = new Lorry();
                    v1.leavePark(cmbVehicleNum.getValue().toString(), cmbDriver.getValue().toString());
                    break;
            }
            InParkingDb.parkingData.removeIf(tm -> tm.getVehicleNum().equals(cmbVehicleNum.getValue().toString()));
            new Alert(Alert.AlertType.INFORMATION, "Done", ButtonType.OK).showAndWait();
            cmbVehicleNum.getSelectionModel().select("Vehicle Number");
            cmbDriver.getSelectionModel().select("Driver Name");
            lblType.setText(null);
        }
    }

    public void refresh(MouseEvent mouseEvent) throws IOException {
        mainContext.getChildren().clear();
        Stage stage = (Stage) mainContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/MainForm.fxml"))));
    }

    public void logInOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/ManagementLoginForm.fxml"))));
        stage.show();
    }
}
