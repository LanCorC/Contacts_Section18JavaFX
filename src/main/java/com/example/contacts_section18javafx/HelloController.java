package com.example.contacts_section18javafx;

import com.example.datamodel.Contact;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TableView<Contact> contactTableView;

    public void initialize() {
        TableColumn<Contact, String> col1 = new TableColumn<>("First Name");
        TableColumn<Contact, String> col2 = new TableColumn<>("Last Name");
        TableColumn<Contact, String> col3 = new TableColumn<>("Contact Number");
        TableColumn<Contact, String> col4 = new TableColumn<>("Notes");

        col1.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col2.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        col3.setCellValueFactory(new PropertyValueFactory<>("number"));
        col4.setCellValueFactory(new PropertyValueFactory<>("notes"));

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

        contactTableView.getColumns().addAll(col1, col2, col3, col4);

        ObservableList<Contact> contacts = FXCollections.observableArrayList();
        Contact c1 = new Contact("Lance", "Maxx", "111", "test");
        Contact c2 = new Contact("Lear", "Minne", "222", "axe");
        Contact c3 = new Contact("Tim", "Innes", "333", "ags");
        Contact c4 = new Contact("Rupert", "Smalls", "132", "grint");
        Contact c5 = new Contact("Blah", "Bluh", "321", "bluh");

        contacts.addAll(c1, c2, c3, c4, c5);

        contactTableView.getItems().addAll(contacts);

    }

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