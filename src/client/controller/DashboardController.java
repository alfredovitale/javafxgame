package client.controller;

import client.StartClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class DashboardController {


    public void showProfile(ActionEvent actionEvent) {
    }

    public void startSinglegame(ActionEvent actionEvent) {
        ScreenController screenController = ScreenController.getInstance();
        screenController.activate("boardScreen");
    }

    public void startMultiplayer(ActionEvent actionEvent) {
    }
}
