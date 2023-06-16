package com.example.contacts_section18javafx;

import com.example.datamodel.Contact;
import com.example.datamodel.ContactData;
import javafx.collections.ObservableList;
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

    //field keeps note, if any, what details to pre-populate of
    //existing contact, or what to replace when 'edit' is called
    private Contact referenceContact;

    public Contact processResults(String s) {
        //Temp: input check
        System.out.printf("%s received", s);
        Contact result = new Contact(firstName.getText(),
                secondName.getText(), phoneNumber.getText(),
                notes.getText());

        ObservableList<Contact> list =
                ContactData.getInstance().getContacts();
        switch (s) {
            case "add" -> {
                if (!list.contains(result)) {
                    ContactData.getInstance().addContact(result);
                } else {
                    return null;
                }
            }
            case "edit" -> {
                //replacement not already in e.g. to not cause duplicate, then change
                if (!list.contains(result)) {
                    list.set(list.indexOf(referenceContact), result);
                    ContactData.getInstance().editContact(referenceContact, result);
                    System.out.println("Edit attempted");
                } else {
                    System.out.println("Edit failed");
                    return null;
                }
            }
            //delete = default
            default -> ContactData.getInstance().deleteContact(referenceContact);
        }
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

    public void freezeEntry() {
        firstName.setEditable(false);
        secondName.setEditable(false);
        phoneNumber.setEditable(false);
        notes.setEditable(false);
    }

    //added resumeEntry in case this is required to revert state between more than 1 dialog calls per start()
    public void resumeEntry() {
        firstName.setEditable(true);
        secondName.setEditable(true);
        phoneNumber.setEditable(true);
        notes.setEditable(true);
    }
}
