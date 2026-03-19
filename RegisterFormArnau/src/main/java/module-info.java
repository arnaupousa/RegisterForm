module com.arnau.registerformarnau {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens com.arnau.registerformarnau.controller to javafx.fxml;
    
    exports com.arnau.registerformarnau;
}