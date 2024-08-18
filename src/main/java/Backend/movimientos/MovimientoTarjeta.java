package Backend.movimientos;

import Backend.database.Herramientas;
import Backend.enums.TipoMovimiento;

public class MovimientoTarjeta {

    private String numeroTarjeta;
    private String fecha;
    private TipoMovimiento tipoMovimiento; //CARGO, ABONO
    private String descripcion;
    private String establecimiento;
    private double monto;

    public MovimientoTarjeta(String numeroTarjeta, String fecha, TipoMovimiento tipoMovimiento, String descripcion, String establecimiento, double monto) {
        this.numeroTarjeta = numeroTarjeta;
        this.fecha = fecha;
        this.tipoMovimiento = tipoMovimiento;
        this.descripcion = descripcion;
        this.establecimiento = establecimiento;
        this.monto = monto;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public String getFecha() {
        return fecha;
    }

    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getEstablecimiento() {
        return establecimiento;
    }

    public double getMonto() {
        return monto;
    }
}
