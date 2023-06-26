package com.example.demo12;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;

public class Admin {
    @FXML
    private Button add;

    @FXML
    private TableColumn<User, Integer> birthdate;

    @FXML
    private Button changerole;

    @FXML
    private TableColumn<User, String> email;

    @FXML
    private Button enablelogin;

    @FXML
    private Button exit;

    @FXML
    private TableColumn<User, String> lastname;

    @FXML
    private TableColumn<User, String> name;

    @FXML
    private SplitMenuButton office;

    @FXML
    private TableColumn<User, Integer> roleID;

    @FXML
    private TableView<User> table;

    @FXML
    private TableColumn<User, Integer> title;


    Connection connection;



    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        DBConnector connector = new DBConnector();
        connection = connector.getConnection();
        name.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastname.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        birthdate.setCellValueFactory(new PropertyValueFactory<>("Birthdate"));

        table.setItems(connector.getUser("SELECT users.Email, users.FirstName, users.LastName,users.Birthdate, offices.Title, roles.Title FROM users JOIN offices ON users.OfficeID = offices.ID JOIN roles ON users.RoleID = roles.ID"));
        title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        roleID.setCellValueFactory(new PropertyValueFactory<>("RoleID"));

        add.setOnAction(click -> {
            add.setText("Добро пожаловать");
            add.getScene().getWindow().hide();
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("adduser.fxml"));
                loader.load();
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        changerole.setOnAction(click -> {
            changerole.setText("Добро пожаловать");
            changerole.getScene().getWindow().hide();
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("change.fxml"));
                loader.load();
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}