package gestionempleados;

public class EmpleadoTemporal extends Empleado {
    private final String fechaAlta;
    private final String fechaBaja;

    public EmpleadoTemporal(String nombre, String apellido, String cedula, int cantidadHijos, String fechaAlta, String fechaBaja) {
        super(nombre, apellido, cedula, cantidadHijos);
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
    }

    @Override
    public double calcularSueldo() {
        return 18000 + (1000 * cantidadHijos);
    }
}
