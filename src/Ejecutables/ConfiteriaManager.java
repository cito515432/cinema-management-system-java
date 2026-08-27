
package Ejecutables;

import Basedatos.Consultas;
import Basedatos.Introduciryeliminar;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConfiteriaManager {

    public void agregarCombo(String nombre, int precio, String descripcion) {
        Introduciryeliminar.insertarCombo(nombre, precio, descripcion);
        System.out.println("Combo agregado con éxito.");
    }

    public void mostrarCombosDisponibles() {
        System.out.println("Combos Disponibles:");
        ResultSet resultSet = Consultas.mostrarTodaLaConfiteria();
        try {
            while (resultSet.next()) {
                String nombreCombo = resultSet.getString("nombre_combo");
                int precio = resultSet.getInt("precio");
                String descripcion = resultSet.getString("descripcion");
                
                System.out.println("Nombre: " + nombreCombo +
                                   ", Precio: " + precio +
                                   ", Descripción: " + descripcion);
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar combos disponibles: " + e.getMessage());
        }
    }

    public void comprarCombo(String nombreCombo, String fechaFactura) {
        ResultSet resultSet = Consultas.consultarConfiteriaPorNombreCombo(nombreCombo);
        try {
            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre_combo");
                int precio = resultSet.getInt("precio");
                String descripcion = resultSet.getString("descripcion");
                
                // Insertar la factura
                Introduciryeliminar.insertarFactura(nombre, fechaFactura);
                
                // Mostrar el detalle de la factura y el combo seleccionado
                System.out.println("¡Compra realizada con éxito!");
                System.out.println("------------- FACTURA -------------");
                System.out.println("Fecha compra: " + fechaFactura);
                System.out.println("Combo: " + nombre);
                System.out.println("Precio: " + precio);
                System.out.println("Descripción: " + descripcion);
                System.out.println("-----------------------------------");
            } else {
                System.out.println("El combo seleccionado no está disponible.");
            }
        } catch (SQLException e) {
            System.out.println("Error al comprar combo: " + e.getMessage());
        }
    }
    public ResultSet buscarCombo(String nombreCombo) {
        return Consultas.consultarConfiteriaPorNombreCombo(nombreCombo);
    }
}