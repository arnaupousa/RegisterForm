package ficheros;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ClassFichero {
    
    private static BufferedWriter writer;

    // Crea o abre el archivo
    public static void createFile(String fileName) throws IOException {
        // El 'true' es para que añada texto al final y no borre los usuarios anteriores
        writer = new BufferedWriter(new FileWriter(fileName, true));
    }

    // Escribe una línea en el archivo
    public static void writeFile(String line) throws IOException {
        if (writer != null) {
            writer.write(line);
            writer.flush(); // Asegura que se guarde al instante
        }
    }

    // Cierra el archivo cuando cierras la aplicación
    public static void closeFile() throws IOException {
        if (writer != null) {
            writer.close();
        }
    }
}