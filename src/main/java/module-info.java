module com.arnau.registerformarnau {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.arnau.registerformarnau.controller to javafx.fxml;
    
    exports com.arnau.registerformarnau;
}