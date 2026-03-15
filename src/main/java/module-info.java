module com.arnau.registerformarnau {
    // 1. Damos permiso para usar las librerías visuales de JavaFX
    requires javafx.controls;
    requires javafx.fxml;

    // 2. Damos permiso para que JavaFX pueda "leer" tu archivo RegisterController
    // (Sin esto, las etiquetas @FXML darán error al ejecutar)
    opens com.arnau.registerformarnau.controller to javafx.fxml;
    
    // 3. Exportamos tu paquete principal para que la aplicación pueda arrancar
    exports com.arnau.registerformarnau;
}