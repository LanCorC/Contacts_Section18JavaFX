package com.example.contacts_section18javafx;

import com.datamodel.Contact;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TableView<Contact> contactTableView;

    @FXML
    public void handleAddContact() {
        welcomeText.setText("Menuitem \"Add Contact\" selected");
    }
    @FXML
    public void handleEditContact() {
        welcomeText.setText("Menuitem \"Edit Contact\" selected");
    }
    @FXML
    public void handleDeleteContact() {
        welcomeText.setText("Menuitem \"Delete Contact\" selected");
    }
    @FXML public void handleExit() {
        welcomeText.setText("Closing in a sec!");

        try {
            Thread.sleep(1000);
            Platform.exit();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}