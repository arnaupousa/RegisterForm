package com.arnau.registerformarnau;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import ficheros.ClassFichero;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    // 1. Método init() para preparar recursos antes de cargar la interfaz
    @Override
    public void init() throws Exception {
        // Aquí podrías inicializar/abrir el archivo si tu clase lo requiere
        // Por ejemplo: ClassFichero.openFile();
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        
        // 2. Añadimos un título a la ventana de tu aplicación
        stage.setTitle("Formulario de Registro - Arnau"); 
        
        stage.setScene(scene);
        stage.show();
    }
    
    // 3. Método stop() cierra los recursos cuando se cierra la ventana
    @Override
    public void stop() throws Exception {
        ClassFichero.closeFile();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}