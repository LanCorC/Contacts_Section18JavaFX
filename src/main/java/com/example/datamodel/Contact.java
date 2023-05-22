package com.example.datamodel;

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

    public Contact() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNumber() {
        return number;
    }

    public String getNotes() {
        return notes;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
