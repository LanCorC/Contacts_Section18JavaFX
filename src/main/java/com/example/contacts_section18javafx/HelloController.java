package com.example.contacts_section18javafx;

import com.example.datamodel.Contact;
import com.example.datamodel.ContactData;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Optional;

public class HelloController {
    @FXML
    private TableView<Contact> contactTableView;
    @FXML
    private BorderPane mainBorderPane;

    public void initialize() {
        TableColumn<Contact, String> col1 = new TableColumn<>("First Name");
        TableColumn<Contact, String> col2 = new TableColumn<>("Last Name");
        TableColumn<Contact, String> col3 = new TableColumn<>("Contact Number");
        TableColumn<Contact, String> col4 = new TableColumn<>("Notes");

        col1.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col2.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        col3.setCellValueFactory(new PropertyValueFactory<>("number"));
        col4.setCellValueFactory(new PropertyValueFactory<>("notes"));

        contactTableView.getColumns().setAll(col1, col2, col3, col4);

        contactTableView.setItems(ContactData.getInstance().getContacts());
        contactTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }


    @FXML
    public void handleAddContact() {
        System.out.println("Menuitem \"Add Contact\" selected");
        handleContactDialog("add");
    }
    @FXML
    public void handleEditContact() {
        System.out.println("Menuitem \"Edit Contact\" selected");
        handleContactDialog("edit");
    }
    @FXML
    public void handleDeleteContact() {
        System.out.println("Menuitem \"Delete Contact\" selected");
        handleContactDialog("delete");
    }

    //add, edit, delete will trigger this?
    private void handleContactDialog(String action) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        switch(action) {
            case "add" -> {
                dialog.setHeaderText("Fill in their information below");
                dialog.setTitle("Add a New Contact");
            }
            case "edit" -> {
                if (contactTableView.getSelectionModel().getSelectedItem() == null) {
                    nullSelected();
                    return;
                }
                dialog.setHeaderText("Adjust their information below");
                dialog.setTitle("Edit Existing Contact");
            }
            default -> {
                if (contactTableView.getSelectionModel().getSelectedItem() == null) {
                    nullSelected();
                    return;
                }
                dialog.setHeaderText("You are about to delete the entry summarised below");
                dialog.setTitle("Confirm deletion of contact...");
            }
        }

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("addEditDeleteDialogue.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
            AddEditDeleteDialogueController controller = fxmlLoader.getController();
            if (action.equals("edit")) {
                controller.prepopulate(contactTableView.getSelectionModel().getSelectedItem());
            } else if (action.equals("delete")) {
                controller.prepopulate(contactTableView.getSelectionModel().getSelectedItem());
                controller.freezeEntry();
            }
        } catch(IOException e) {
            System.out.println("Couldn't find the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            AddEditDeleteDialogueController controller = fxmlLoader.getController();
            controller.processResults(action);
        }
    }

    private void nullSelected() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("No Contact Selected");
        alert.setHeaderText(null);
        alert.setContentText(
                "Please select an existing contact from the table and try again.");
        alert.showAndWait();
    }

    @FXML public void handleExit() {
        try {
            Platform.exit();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    @FXML
    public void onHandleKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.DELETE)) {
            handleDeleteContact();
        }
    }
}