package Backend.solicitudes;

import Backend.enums.Estado;
import Backend.enums.TipoTarjeta;

public class Solicitud {

    private String numeroSolicitud; // para buscar una solicitud en el sistema
    private String fecha;
    private TipoTarjeta tipo; //NACIONAL, REGIONAL, INTERNACIONAL
    private String nombreSolicitante;
    private double salario;
    private String direccion;
    //private Estado estado; //enum PENDIENTE, AUTORIZADA, RECHAZADA

    public Solicitud(String numeroSolicitud, String fecha, TipoTarjeta tipo, String nombreSolicitante, double salario, String direccion) { //constructor
        this.numeroSolicitud = numeroSolicitud;
        this.fecha = fecha;
        this.tipo = tipo;
        this.nombreSolicitante = nombreSolicitante;
        this.salario = salario;
        this.direccion = direccion;
    }

    public String getNumeroSolicitud() {
        return numeroSolicitud;
    }

    public void setNumeroSolicitud(String numeroSolicitud) {
        this.numeroSolicitud = numeroSolicitud;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public TipoTarjeta getTipo() {
        return tipo;
    }

    public void setTipo(TipoTarjeta tipo) {
        this.tipo = tipo;
    }

    public String getNombreSolicitante() {
        return nombreSolicitante;
    }

    public void setNombreSolicitante(String nombreSolicitante) {
        this.nombreSolicitante = nombreSolicitante;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
