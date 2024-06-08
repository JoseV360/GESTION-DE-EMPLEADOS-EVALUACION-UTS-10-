package gestionempleados;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class EmpleadosGUI extends JFrame {
    private final JTable table;
    private DefaultTableModel model;
    private ArrayList<Empleado> empleados;

    public EmpleadosGUI() {
        empleados = new ArrayList<>();

        setTitle("Gestión de Empleados");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Modelo de la tabla
        model = new DefaultTableModel(new String[]{"Nombre", "Apellido", "Cédula", "Cantidad de Hijos", "Tipo", "Detalle 1", "Detalle 2", "Sueldo"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        // Panel de entrada
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(8, 2, 10, 10)); // 8 filas, 2 columnas, con espacio de 10px entre componentes

        JTextField nombreField = new JTextField(10);
        JTextField apellidoField = new JTextField(10);
        JTextField cedulaField = new JTextField(10);
        JTextField cantidadHijosField = new JTextField(5);

        JComboBox<String> tipoComboBox = new JComboBox<>(new String[]{"Por Hora", "Temporal", "Planta Permanente"});
        JTextField detalleField1 = new JTextField(10); // Para horas trabajadas, fecha alta, o antigüedad
        JTextField detalleField2 = new JTextField(10); // Para fecha baja (solo para temporales)

        inputPanel.add(new JLabel("Nombre:"));
        inputPanel.add(nombreField);
        inputPanel.add(new JLabel("Apellido:"));
        inputPanel.add(apellidoField);
        inputPanel.add(new JLabel("Cédula:"));
        inputPanel.add(cedulaField);
        inputPanel.add(new JLabel("Cantidad de Hijos:"));
        inputPanel.add(cantidadHijosField);
        inputPanel.add(new JLabel("Tipo:"));
        inputPanel.add(tipoComboBox);
        inputPanel.add(new JLabel("Detalle 1:"));
        inputPanel.add(detalleField1);
        inputPanel.add(new JLabel("Detalle 2:"));
        inputPanel.add(detalleField2);

        add(inputPanel, BorderLayout.NORTH);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        JButton guardarButton = new JButton("Guardar");
        JButton limpiarButton = new JButton("Limpiar");

        buttonPanel.add(guardarButton);
        buttonPanel.add(limpiarButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Acción del botón guardar
        guardarButton.addActionListener((ActionEvent e) -> {
            // Verificar que todos los campos estén llenos
            if (nombreField.getText().isEmpty() || apellidoField.getText().isEmpty() || cedulaField.getText().isEmpty() ||
                cantidadHijosField.getText().isEmpty() || detalleField1.getText().isEmpty() ||
                (tipoComboBox.getSelectedItem().equals("Temporal") && detalleField2.getText().isEmpty())) {
                
                JOptionPane.showMessageDialog(null, "Debe llenar todos los campos para registrar un empleado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(null, "¿Desea guardar la información?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                String nombre = nombreField.getText();
                String apellido = apellidoField.getText();
                String cedula = cedulaField.getText();
                int cantidadHijos = Integer.parseInt(cantidadHijosField.getText());
                String tipo = (String) tipoComboBox.getSelectedItem();
                Empleado empleado = null;

                switch (tipo) {
                    case "Por Hora":
                        try {
                            int horasTrabajadas = Integer.parseInt(detalleField1.getText());
                            empleado = new EmpleadoPorHora(nombre, apellido, cedula, cantidadHijos, horasTrabajadas);
                            model.addRow(new Object[]{nombre, apellido, cedula, cantidadHijos, tipo, horasTrabajadas, "", empleado.calcularSueldo()});
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "El detalle para 'Por Hora' debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        break;
                    case "Temporal":
                        String fechaAlta = detalleField1.getText();
                        String fechaBaja = detalleField2.getText();
                        empleado = new EmpleadoTemporal(nombre, apellido, cedula, cantidadHijos, fechaAlta, fechaBaja);
                        model.addRow(new Object[]{nombre, apellido, cedula, cantidadHijos, tipo, fechaAlta, fechaBaja, empleado.calcularSueldo()});
                        break;
                    case "Planta Permanente":
                        try {
                            int antiguedad = Integer.parseInt(detalleField1.getText());
                            empleado = new EmpleadoPlantaPermanente(nombre, apellido, cedula, cantidadHijos, antiguedad);
                            model.addRow(new Object[]{nombre, apellido, cedula, cantidadHijos, tipo, antiguedad, "", empleado.calcularSueldo()});
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "El detalle para 'Planta Permanente' debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        break;
                    default:
                        break;
                }

                if (empleado != null) {
                    empleados.add(empleado);
                    // Refrescar tabla
                    table.repaint();
                }

                nombreField.setText("");
                apellidoField.setText("");
                cedulaField.setText("");
                cantidadHijosField.setText("");
                detalleField1.setText("");
                detalleField2.setText("");

                JOptionPane.showMessageDialog(null, "Datos almacenados temporalmente.");
            }
        });

        // Acción del botón limpiar
        limpiarButton.addActionListener((ActionEvent e) -> {
            int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea limpiar todos los parámetros?", "Advertencia", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                nombreField.setText("");
                apellidoField.setText("");
                cedulaField.setText("");
                cantidadHijosField.setText("");
                detalleField1.setText("");
                detalleField2.setText("");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EmpleadosGUI().setVisible(true);
        });
    }
}
