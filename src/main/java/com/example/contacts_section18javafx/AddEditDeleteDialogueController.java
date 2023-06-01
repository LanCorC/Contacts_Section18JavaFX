package com.example.contacts_section18javafx;

import com.example.datamodel.Contact;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddEditDeleteDialogueController {
    @FXML
    private TextField firstName;
    @FXML
    private TextField secondName;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextArea notes;
    private Contact referenceContact;

    public Contact processResults(String s) {
        System.out.printf("%s received", s);
        Contact result = new Contact(firstName.getText(),
                secondName.getText(), phoneNumber.getText(),
                notes.getText());
        //TODO add a switch - if add, check if already exists + add. if delete, check if already exists, delete.
        //TODO track: if EDIT, replace reference class from Singleton list
        return result;
    }

    public Contact prepopulate(Contact c) {
        referenceContact = c;
        firstName.setText(c.getFirstName());
        secondName.setText(c.getLastName());
        phoneNumber.setText(c.getNumber());
        notes.setText(c.getNotes());
        System.out.println(c);
        return c;
    }

    //if 'add', start empty fields

    //if 'edit', start fields pre-populated

    //if 'delete', -- simpler dialog Y/N + entry summary?

}
