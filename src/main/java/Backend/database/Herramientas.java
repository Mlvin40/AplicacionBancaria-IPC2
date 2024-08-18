package Backend.database;

public class Herramientas {

    //Metodo para formatear la fecha a formato yyyy-MM-dd compatible con MySQL
    public static String formatoFecha(String fecha) {
        fecha = fecha.trim();
        String[] fechaArray = fecha.split("/");
        String fechaFormateada = fechaArray[2] + "-" + fechaArray[1] + "-" + fechaArray[0];
        return fechaFormateada;
    }
}
