package com.example.demo12;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HiUsers {

    @FXML
    private Label name;

    Connection connection;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException, IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.load(getClass().getResource("C:\\Users\\User\\IdeaProjects\\demo12\\src\\main\\resources\\com\\example\\demo12\\amonicc.fxml").openStream());
        HelloController controller = loader.getController();
        System.out.println(controller.getName());

        DBConnector connector = new DBConnector();
        connection = connector.getConnection();
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery( "SELECT users.FirstName FROM users WHERE users.Email='"  + "'");
        while (set.next()) {
            System.out.println(set.getString("users.FirstName"));
            name.setText(set.getString("users.FirstName"));
        }
    }
}

