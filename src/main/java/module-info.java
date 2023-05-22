module com.example.contacts_section18javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;


    opens com.example.contacts_section18javafx to javafx.fxml;
    exports com.example.contacts_section18javafx;
    exports com.example.datamodel;
}