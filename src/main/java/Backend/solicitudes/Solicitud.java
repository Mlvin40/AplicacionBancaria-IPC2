package Backend.solicitudes;

import Backend.database.Herramientas;
import Backend.enums.Estado;
import Backend.enums.TipoTarjeta;

public class Solicitud {

    private String numeroSolicitud; // para buscar una solicitud en el sistema
    private String fecha;
    private TipoTarjeta tipo; //NACIONAL, REGIONAL, INTERNACIONAL
    private String nombreSolicitante;
    private double salario;
    private String direccion;
    private String estado;

    public Solicitud(String numeroSolicitud, String fecha, TipoTarjeta tipo, String nombreSolicitante, double salario, String direccion) { //constructor
        this.numeroSolicitud = numeroSolicitud;
        this.fecha = fecha;
        this.tipo = tipo;
        this.nombreSolicitante = nombreSolicitante;
        this.salario = salario;
        this.direccion = direccion;
    }

    
    /**
     * Constructor para crear una solicitud con un estado específico, este sirve para los reportes
     * @param numeroSolicitud
     * @param fecha
     * @param tipo
     * @param nombreSolicitante
     * @param salario
     * @param direccion
     * @param estado 
     */
    public Solicitud(String numeroSolicitud, String fecha, TipoTarjeta tipo, String nombreSolicitante, double salario, String direccion, String estado) { //constructor
        this.numeroSolicitud = numeroSolicitud;
        this.fecha = fecha;
        this.tipo = tipo;
        this.nombreSolicitante = nombreSolicitante;
        this.salario = salario;
        this.direccion = direccion;
        this.estado = estado;
    }

    public String getNumeroSolicitud() {
        return numeroSolicitud;
    }

    public String getFecha() {
        return fecha;
    }

    public TipoTarjeta getTipo() {
        return tipo;
    }

    public String getNombreSolicitante() {
        return nombreSolicitante;
    }

    public double getSalario() {
        return salario;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEstado() {
        return estado;
    }
    
}
