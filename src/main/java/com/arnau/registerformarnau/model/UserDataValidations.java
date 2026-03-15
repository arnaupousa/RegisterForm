
package com.arnau.registerformarnau.model;
import java.time.LocalDate;
import java.time.Period;

public class UserDataValidations {
    /**
     * Valida si el NIF recibido es correcto.
     * Solo se valida si el tipo de documento es 1.
     *
     * @param typeDoc Tipo de documento (1 = NIF)
     * @param id      Documento a validar
     * @return true si es válido, false en caso contrario
     */
    public static boolean checkId(int typeDoc, String id) {

        if (typeDoc != 1 || id == null || !id.matches("\\d{8}[A-Za-z]")) {
            return false;
        }

        String letters = "TRWAGMYFPDXBNJZSQVHLCKE";
        int num = Integer.parseInt(id.substring(0, 8));

        return Character.toUpperCase(id.charAt(8)) == letters.charAt(num % 23);
    }

    /**
     * Valida que el formato de la fecha sea correcto (dd/mm/aaaa)
     *
     * @param date Fecha en formato String
     * @return true si es válida, false en caso contrario
     */
    public static boolean checkFormatDate(String date) {

        if (date == null) {
            return false;
        }

        String[] parts = date.split("/");

        if (parts.length != 3 ||
                !parts[0].matches("\\d+") ||
                !parts[1].matches("\\d+") ||
                !parts[2].matches("\\d+")) {
            return false;
        }

        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        return (day >= 1 && day <= 31) &&
                (month >= 1 && month <= 12) &&
                (year > 1900 && year < 2100);
    }

    /**
     * Calcula la edad en base a la fecha de nacimiento.
     *
     * @param birthDate Fecha de nacimiento (dd/mm/aaaa)
     * @return edad calculada o -1 si la fecha no es válida
     */
    public static int calculateAge(String birthDate) {

        if (!checkFormatDate(birthDate)) {
            return -1;
        }

        String[] parts = birthDate.split("/");

        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        LocalDate birth = LocalDate.of(year, month, day);
        LocalDate now = LocalDate.now();

        if (birth.isAfter(now)) {
            return -1;
        }

        return Period.between(birth, now).getYears();
    }

    /**
     * Valida que el código postal tenga 5 dígitos numéricos.
     *
     * @param zip Código postal
     * @return true si es válido, false en caso contrario
     */
    public static boolean checkPostalCode(String zip) {

        if (zip == null || zip.length() != 5) {
            return false;
        }

        for (int i = 0; i < zip.length(); i++) {
            if (!Character.isDigit(zip.charAt(i))) {
                return false;
            }
        }

        return true;
    }


    /**
     * Valida si el contenido de un String es completamente numérico.
     *
     * @param str Cadena a validar
     * @return true si contiene solo números, false en caso contrario
     */
    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }

        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Valida si el contenido de un String contiene únicamente letras.
     *
     * @param str Cadena a validar
     * @return true si contiene solo letras, false en caso contrario
     */
    public static boolean isAlphabetic(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }

        for (int i = 0; i < str.length(); i++) {
            if (!Character.isLetter(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Valida si el formato de un correo electrónico es correcto.
     * Debe contener una '@' y terminar en .com, .es u otra extensión válida.
     *
     * @param email Correo electrónico a validar
     * @return true si el formato es correcto, false en caso contrario
     */
    public static boolean checkEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        // Debe contener una @
        if (!email.contains("@")) {
            return false;
        }

        // Debe terminar en una extensión válida básica
        if (!(email.endsWith(".com") || email.endsWith(".es") ||
                email.endsWith(".org") || email.endsWith(".net"))) {
            return false;
        }

        // No puede empezar ni terminar por @
        if (email.startsWith("@") || email.endsWith("@")) {
            return false;
        }

        return true;
    }

    /**
     * Valida que el nombre tenga una longitud lógica y que no contenga números.
     *
     * @param name Nombre a validar
     * @return true si el nombre es válido, false en caso contrario
     */
    public static boolean checkName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        // Longitud lógica (entre 2 y 50 caracteres)
        if (name.length() < 2 || name.length() > 50) {
            return false;
        }

        // No debe contener números
        for (int i = 0; i < name.length(); i++) {
            if (Character.isDigit(name.charAt(i))) {
                return false;
            }
        }

        return true;
    }
}
