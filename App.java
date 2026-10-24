package com.mycompany.mavenproject1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
public class App extends Application {
    private TextArea displayArea;
    @Override
    public void start(Stage stage) {
        Label nameLabel = new Label("Freelancer Name:");
        Label projectLabel = new Label("Project Name:");
        Label amountLabel = new Label("Payment Amount:");
        TextField nameField = new TextField();
        TextField projectField = new TextField();
        TextField amountField = new TextField();
        Button addButton = new Button("Add Project");
        Button clearButton = new Button("Clear");
        displayArea = new TextArea();
        displayArea.setEditable(false);
        displayArea.setPrefHeight(200);
        addButton.setOnAction(e -> addProject(
                nameField.getText(),
                projectField.getText(),
                amountField.getText()
        ));
        clearButton.setOnAction(e -> {
            nameField.clear();
            projectField.clear();
            amountField.clear();
        });
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(projectLabel, 0, 1);
        grid.add(projectField, 1, 1);
        grid.add(amountLabel, 0, 2);
        grid.add(amountField, 1, 2);
        grid.add(addButton, 0, 3);
        grid.add(clearButton, 1, 3);
        VBox root = new VBox(10, grid, new Label("Project Records:"), displayArea);
        root.setPadding(new Insets(15));
        Scene scene = new Scene(root, 400, 400);
        stage.setTitle("Freelancer Project Management System");
        stage.setScene(scene);
        stage.show();
    }
    private void addProject(String name, String project, String amount) {
        if (name.isEmpty() || project.isEmpty() || amount.isEmpty()) {
            showAlert("Please fill all fields!");
            return;
        }
        try {
            double payment = Double.parseDouble(amount);
            String record = "Freelancer: " + name +
                    "\nProject: " + project +
                    "\nPayment: ₹" + payment +
                    "\n---------------------------\n";
            displayArea.appendText(record);
        } catch (NumberFormatException e) {
            showAlert("Enter valid amount!");
        }
    }
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static void main(String[] args) {
        launch();
    }
}
