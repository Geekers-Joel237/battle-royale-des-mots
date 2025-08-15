module com.brm.brmclient {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.brm.brmclient to javafx.fxml;
    exports com.brm.brmclient;
}