module com.example.contacts_section18javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.contacts_section18javafx to javafx.fxml;
    exports com.example.contacts_section18javafx;
}