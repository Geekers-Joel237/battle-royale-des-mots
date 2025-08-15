module com.brm.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.brm.domain;


    opens com.brm.client to javafx.fxml;
    exports com.brm.client;
}