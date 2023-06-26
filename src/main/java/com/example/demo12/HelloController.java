package com.example.demo12;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloController {
    @FXML
    private Button exit;

    @FXML
    private Button login;

    @FXML
    private TextField name;

    @FXML
    private TextField pass;
    @FXML
    private Text error;

    @FXML
    void initialize() {
        login.setOnAction(click -> {
            login.setText("Добро пожаловать");
            login.getScene().getWindow().hide();
            try {
                String url = "jdbc:mysql://localhost/session1";
                String username = "root";
                String password = "Root";
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                try (Connection conn = DriverManager.getConnection(url, username, password)) {

                    Statement statement = conn.createStatement();

                    ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
                    while (resultSet.next()) {
                        String loginDB = resultSet.getString(3);
                        String passDB = resultSet.getString(4);
                        if (passDB.equals(pass.getText().trim()) && loginDB.equals(name.getText().trim())) {
                            try {
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("amonicc.fxml"));
                                loader.load();
                                Parent root = loader.getRoot();
                                Stage stage = new Stage();
                                stage.setScene(new Scene(root));
                                stage.show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            int active = resultSet.getInt(9);
                            if (active == 1) {
                                try {
                                    FXMLLoader loader = new FXMLLoader();
                                    loader.setLocation(getClass().getResource("hiUsers.fxml"));
                                    loader.load();
                                    Parent root = loader.getRoot();
                                    Stage stage = new Stage();
                                    stage.setScene(new Scene(root));
                                    stage.show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                System.out.println("Верный пароль, аккаунт активен");
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("C:\\Users\\User\\IdeaProjects\\demo12\\src\\main\\resources\\com\\example\\demo12\\admin.fxml"));
                                loader.load();
                                Parent root = loader.getRoot();
                                Stage stage = new Stage();
                                stage.setScene(new Scene(root));
                                stage.show();

                                return;
                            } else {
                                System.out.println("Верный пароль, но вы заблокированы");
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("error.fxml"));
                                loader.load();
                                Parent root = loader.getRoot();
                                Stage stage = new Stage();
                                stage.setScene(new Scene(root));
                                stage.show();
                            }
                            return;
                        } else {
                            System.out.println("Данные введены некорректно");
                        }
                    }
                } catch (Exception ex) {
                    System.out.println("Connection failed...");
                    System.out.println(ex);
                }
            } catch (Exception ex) {
                System.out.println("Error");
            }
        });
    }

    public String getName() {
        return name.getText();
    }
}

