package com.main;

import data.Admin;
import data.Student;
import exception.custom.IllegalAdminAccess;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LibrarySystem extends Application {

    public static String NIM;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Admin adminObj = new Admin();
        Student studentObj = new Student();

        primaryStage.setTitle("UMM Library");

        // Load the logo image
        Image logoImage = new Image("file:C:\\Users\\alfar\\OneDrive\\Gambar\\BAHAN PAMERAN\\logo umm monocrom putim (1).png");
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitHeight(150); // set the desired height
        logoImageView.setPreserveRatio(true); // maintain the aspect ratio

        //Label
        Label sceneTitle = new Label("Perpustakaan UMM");
        Label usernameLabel = new Label("Username");
        Label passwordLabel = new Label("Password");

        //Notification label
        Label errorLoginMessage = new Label("Pengguna tidak ditemukan");

        //Field
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();

        //Font Style
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        usernameLabel.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));
        passwordLabel.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));
        errorLoginMessage.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 12));

        //Font Color
        sceneTitle.setStyle("-fx-text-fill: white;");
        usernameLabel.setStyle("-fx-text-fill: white;");
        passwordLabel.setStyle("-fx-text-fill: white;");
        errorLoginMessage.setStyle("-fx-text-fill: #FF1E1E;");

        //Font visible Settings
        errorLoginMessage.setVisible(false);

        //Button
        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #333333; -fx-text-fill: white;");

        //Grid Layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setStyle("-fx-background-color: black;"); // Set background color to black

        // Add logo to the grid
        grid.add(logoImageView, 0, 0, 2, 1);
        GridPane.setValignment(logoImageView, javafx.geometry.VPos.CENTER); // center vertically
        GridPane.setHalignment(logoImageView, javafx.geometry.HPos.CENTER); // center horizontally

        // Add sceneTitle below the logo
        grid.add(sceneTitle, 0, 1, 2, 1);
        GridPane.setValignment(sceneTitle, javafx.geometry.VPos.CENTER); // center vertically
        GridPane.setHalignment(sceneTitle, javafx.geometry.HPos.CENTER); // center horizontally

        grid.add(usernameLabel, 0, 2);
        grid.add(passwordLabel, 0, 3);
        grid.add(errorLoginMessage, 0, 4);

        grid.add(usernameField, 1, 2);
        grid.add(passwordField, 1, 3);

        grid.add(loginButton, 1, 4);

        grid.setVgap(10);
        grid.setHgap(5);

        //Create Window
        Scene scene = new Scene(grid, 1360, 720);
        primaryStage.setScene(scene);
        primaryStage.show();

        //Action Button
        loginButton.setOnAction(event -> {
            if (usernameField.getText().equals(Admin.adminusername) && passwordField.getText().equals(Admin.adminpassword)) {
                adminObj.menu();
                primaryStage.close();
            } else if (usernameField.getText().length() == 15 && passwordField.getText().length() < 15) {
                try {
                    if (studentObj.isStudents(usernameField)) {
                        errorLoginMessage.setVisible(false);
                        studentObj.isStudents(usernameField);
                    } else {
                        errorLoginMessage.setVisible(true);
                    }
                } catch (IllegalAdminAccess pesanError) {
                    errorLoginMessage.setText(pesanError.getMessage());
                    errorLoginMessage.setVisible(true);
                }
            } else {
                errorLoginMessage.setVisible(true);
            }
        });
    }
}
