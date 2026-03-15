package com.arnau.registerformarnau.controller;

import com.arnau.registerformarnau.model.UserDataValidations;
import ficheros.ClassFichero;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class RegisterController implements Initializable {

    @FXML private Label lblDNI;
    @FXML private Label lblCP;
    @FXML private Label lblBirth;
    @FXML private Label lblMail;
    @FXML private TextField txtName;
    @FXML private TextField txtDNI;
    @FXML private TextField txtCP;
    @FXML private TextField txtBirth;
    @FXML private TextField txtMail;
    @FXML private VBox lblName;
    @FXML private Label txtInfo;
    @FXML private Button btnReg;
    @FXML private Button btnLimpiar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ClassFichero.createFile("RegisterUsers.csv");
            String line = "name" + ";" + "id" + ";" + "postalCode" + ";" + "birthDate" + ";" + "mail" + ";" + "password" + "\n";
            ClassFichero.writeFile(line);
        } catch (Exception e){
            System.out.println("Error al crear fichero");
        }
    }
    
    @FXML
    private void pressRegister() {
        boolean todoOK = true;
        
        String nombre = txtName.getText();
        String nif = txtDNI.getText();
        String cp = txtCP.getText();
        String birth = txtBirth.getText();
        String mail = txtMail.getText();

        lblDNI.setText("");
        lblCP.setText("");
        lblBirth.setText("");
        lblMail.setText("");
        txtInfo.setText("");

        if (!UserDataValidations.checkName(nombre)) {
            todoOK = false;
            txtInfo.setText("Error: Nombre no válido.");
        }

        if (!UserDataValidations.checkId(1, nif)) {
            todoOK = false;
            lblDNI.setText("NIF incorrecto");
        }

        if (!UserDataValidations.checkPostalCode(cp)) {
            todoOK = false;
            lblCP.setText("CP inválido (5 números)");
        }

        if (!UserDataValidations.checkFormatDate(birth)) {
            todoOK = false;
            lblBirth.setText("Formato: dd/mm/aaaa");
        } else {
            int edad = UserDataValidations.calculateAge(birth);
            if (edad == -1 || edad < 18) {
                todoOK = false;
                lblBirth.setText("Debes ser mayor de 18 años");
            }
        }

        if (!UserDataValidations.checkEmail(mail)) {
            todoOK = false;
            lblMail.setText("Email con formato incorrecto");
        }

        if (todoOK) {
            txtInfo.setText("¡Usuario registrado con éxito!");
            txtInfo.setStyle("-fx-text-fill: green;"); 
            try {
                String line = nombre + ";" + nif + ";" + cp + ";" + birth + ";" + mail + ";" + "sin-password" + "\n";
                ClassFichero.writeFile(line);
            } catch (Exception e) {
                txtInfo.setText("Error al guardar en el fichero CSV");
                txtInfo.setStyle("-fx-text-fill: red;");
            }
        } else {
            txtInfo.setStyle("-fx-text-fill: red;");
            if(txtInfo.getText().isEmpty()) {
                txtInfo.setText("Por favor, revisa los campos en rojo.");
            }
        }
    }
    
    @FXML
    private void limpiarCampos() {
        txtName.clear();
        txtDNI.clear();
        txtCP.clear();
        txtBirth.clear();
        txtMail.clear();
        lblDNI.setText("");
        lblCP.setText("");
        lblBirth.setText("");
        lblMail.setText("");
        txtInfo.setText("");
    }
}