package gestionempleados;

public class EmpleadoPlantaPermanente extends Empleado {
    private final int antiguedad;

    public EmpleadoPlantaPermanente(String nombre, String apellido, String cedula, int cantidadHijos, int antiguedad) {
        super(nombre, apellido, cedula, cantidadHijos);
        this.antiguedad = antiguedad;
    }

    @Override
    public double calcularSueldo() {
        return 20000 + (1000 * cantidadHijos) + (1000 * antiguedad);
    }
}
