package com.example.datamodel;

import java.util.Objects;

public class Contact {
    private String firstName;
    private String lastName;
    private String number;
    private String notes;


    public Contact(String firstName, String lastName, String number, String notes) {
        this.firstName = firstName.strip();
        this.lastName = lastName.strip();
        this.number = number.strip();
        this.notes = notes.strip();
    }

    public Contact() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (!firstName.equals(contact.firstName)) return false;
        if (!Objects.equals(lastName, contact.lastName)) return false;
        return Objects.equals(number, contact.number);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
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
