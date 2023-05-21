package com.datamodel;

public class Contact {
    private String firstName;
    private String lastName;
    private String number;
    private String notes;

    public Contact(String firstName, String lastName, String number, String notes) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.notes = notes;
    }
}
