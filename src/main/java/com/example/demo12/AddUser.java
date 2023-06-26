package com.example.demo12;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;

public class AddUser {

    @FXML
    private TextField birthdate;

    @FXML
    private Button cancel;

    @FXML
    private TextField email;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private ComboBox<String> office;

    @FXML
    private TextField pass;

    @FXML
    private Button save;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        DBConnector connector = new DBConnector();

        ObservableList<String> Office = connector.getOffice("SELECT offices.Title FROM offices");
        office.setValue(Office.get(0));
        for (int i = 0; i < Office.size(); i++) {
            office.getItems().add(String.valueOf(Office.get(i)));
        }
// Верх не трогать
        cancel.setOnAction(click -> {
            cancel.getScene().getWindow().hide();
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("admin.fxml"));
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

    public void add() throws SQLException {
        save.getScene().getWindow().hide();

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/session1", "root", "Root");
        Statement statement = conn.createStatement();

        String selected = office.getValue();
        ResultSet set = statement.executeQuery("SELECT offices.ID FROM offices WHERE offices.Title = '" + selected + "'");
        set.next();
        int officeId = set.getInt("offices.ID");

        String registerSql = "INSERT INTO users (users.RoleID, users.Email, users.FirstName, users.LastName, users.Password, users.Birthdate, users.OfficeID, users.Active) " +
                "VALUES (" + 2 + ", '" + email.getText() + "', '" + firstName.getText() + "', '" + lastName.getText() + "', '" + pass.getText() + "', '" + birthdate.getText() + "', " + officeId + ", " + 1 + ")";
        statement.executeUpdate(registerSql);
    }
}