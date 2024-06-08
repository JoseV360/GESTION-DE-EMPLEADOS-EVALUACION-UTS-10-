package gestionempleados;

public class EmpleadoPorHora extends Empleado {
    private final int horasTrabajadas;

    public EmpleadoPorHora(String nombre, String apellido, String cedula, int cantidadHijos, int horasTrabajadas) {
        super(nombre, apellido, cedula, cantidadHijos);
        this.horasTrabajadas = horasTrabajadas;
    }

    @Override
    public double calcularSueldo() {
        return horasTrabajadas * 100;
    }
}
