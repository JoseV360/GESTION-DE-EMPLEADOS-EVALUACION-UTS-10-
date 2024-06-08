package gestionempleados;

public abstract class Empleado {
    protected String nombre;
    protected String apellido;
    protected String cedula;
    protected int cantidadHijos;

    public Empleado(String nombre, String apellido, String cedula, int cantidadHijos) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.cantidadHijos = cantidadHijos;
    }

    public abstract double calcularSueldo();
}
