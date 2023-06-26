package com.example.demo12;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DBConnector {
    private Connection connection;

    DBConnector() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost/session1", "root", "Root");
    }

    public ObservableList<User> getUser(String selectRequest) throws SQLException {
        ObservableList<User> res = FXCollections.observableArrayList();
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery(selectRequest);

        String FirstName, LastName, Email, Birthdate, title, roleID;

        while (set.next()) {
            FirstName = set.getString("users.FirstName");
            LastName = set.getString("users.LastName");
            Email = set.getString("users.Email");
            Birthdate = set.getString("users.Birthdate");
            roleID = set.getString("roles.Title");
            title = set.getString("offices.Title");
            res.add(new User(FirstName, LastName, Birthdate, roleID, Email, title ));
        }
        return res;

    }
    public ObservableList<String> getOffice(String selectRequest) throws SQLException {
        ObservableList<String> ress = FXCollections.observableArrayList();
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery(selectRequest);

        String nameOffice;

        while (set.next()) {
            nameOffice = set.getString("offices.Title");
            ress.add(nameOffice);
        }
        return ress;
    }
    public Connection getConnection() {
        return connection;
    }
}