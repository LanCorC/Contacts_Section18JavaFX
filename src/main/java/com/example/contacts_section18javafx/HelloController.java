package com.example.contacts_section18javafx;

import com.example.datamodel.Contact;
import com.example.datamodel.ContactData;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Optional;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TableView<Contact> contactTableView;
    @FXML
    private BorderPane mainBorderPane;

    public void initialize() {
        TableColumn<Contact, String> col1 = new TableColumn<>("First Name");
        TableColumn<Contact, String> col2 = new TableColumn<>("Last Name");
        TableColumn<Contact, String> col3 = new TableColumn<>("Contact Number");
        TableColumn<Contact, String> col4 = new TableColumn<>("Notes");

        col1.setCellValueFactory(new PropertyValueFactory<Contact, String>("firstName"));
        col2.setCellValueFactory(new PropertyValueFactory<Contact, String>("lastName"));
        col3.setCellValueFactory(new PropertyValueFactory<Contact, String>("number"));
        col4.setCellValueFactory(new PropertyValueFactory<Contact, String>("notes"));

//        col1.setCellFactory(new Callback<TableColumn<Contact, Contact>, TableCell<Contact, Contact>>() {
//            @Override
//            public TableCell<Contact, Contact> call(TableColumn<Contact, Contact> contactStringTableColumn) {
//                TableCell<Contact, Contact> cell = new TableCell<>() {
//                    @Override
//                    protected void updateItem(Contact s, boolean b) {
//                        super.updateItem(s, b);
//                        if (b) {
//                            setText(null);
//                        } else {
//                            setText(s.getFirstName());
//                        }
//                    }
//                };
//                return null;
//            }
//        });
        contactTableView.getColumns().setAll(col1, col2, col3, col4);

//        ObservableList<Contact> contacts = FXCollections.observableArrayList();
//        Contact c1 = new Contact("Lance", "Maxx", "111", "test");
//        Contact c2 = new Contact("Lear", "Minne", "222", "axe");
//        Contact c3 = new Contact("Tim", "Innes", "333", "ags");
//        Contact c4 = new Contact("Rupert", "Smalls", "132", "grint");
//        Contact c5 = new Contact("VeryLong Name IV", "Entered-Person", "321", "bluh");
//        contacts.addAll(c1, c2, c3, c4, c5);

        contactTableView.setItems(ContactData.getInstance().getContacts());
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
                dialog.setHeaderText("Adjust their information below");
                dialog.setTitle("Edit Existing Contact");
            }
            default -> {
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

    @FXML public void handleExit() {
        welcomeText.setText("Closing in a sec!");

        try {
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