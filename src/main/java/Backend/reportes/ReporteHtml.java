package Backend.reportes;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class ReporteHtml {

    private final String PATH_REPORTE;
    private String contenido;

    public ReporteHtml(String PATH_REPORTE, String contenido) {
        this.PATH_REPORTE = PATH_REPORTE;
        this.contenido = contenido;
    }

    public void exportarReporteHTML() {
        File reporte = new File(PATH_REPORTE);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH_REPORTE))) {

            //Si no existe el archivo lo crea
            if (!reporte.exists()) {
                reporte.createNewFile();  // Crea el archivo si no existe
            }

            StringBuilder builder = new StringBuilder();
            String[] lineas = contenido.split("\n");
            for (int i = 0; i < lineas.length; i++) {
                builder.append("<p>").append(lineas[i]).append("</p>").append("\n");
            }

            bw.write("<html>" + "\n"
                    + " <head>" + "\n"
                    + "     <title>DATOS TARJETA</title>" + "\n"
                    + " </head>" + "\n"
                    + "     <body>" + "\n"
                    + builder.toString()
                    + "     </body>" + "\n"
                    + "</html>");
            JOptionPane.showMessageDialog(null, "Reporte exportado exitosamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al exportar el reporte");
        }
    }
}
