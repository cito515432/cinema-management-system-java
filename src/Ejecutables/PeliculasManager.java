
package Ejecutables;

import Basedatos.Consultas;
import Basedatos.Introduciryeliminar;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PeliculasManager {
    public PeliculasManager() {
    }
    

    public void agregarPelicula(String nombre, String fecha, String horaProyeccion, int edadMinima) {
        Introduciryeliminar.insertarPelicula(nombre, fecha, horaProyeccion, edadMinima);
        System.out.println("Película agregada con éxito.");
    }

    public void mostrarPeliculasDisponibles() {
        ResultSet resultSet = Consultas.mostrarTodasLasPeliculas();
        try {
            System.out.println("Películas Disponibles:");
            while (resultSet.next()) {
                int idPelicula = resultSet.getInt("id_pelicula");
                String nombrePelicula = resultSet.getString("nombre_pelicula");
                String fechaPelicula = resultSet.getString("fecha_pelicula");
                String horaProyeccion = resultSet.getString("hora_proyeccion");
                int edadMinima = resultSet.getInt("edad_minima");

                System.out.println("ID de película: " + idPelicula +
                        ", Nombre: " + nombrePelicula +
                        ", Fecha: " + fechaPelicula +
                        ", Hora: " + horaProyeccion);
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar las películas: " + e.getMessage());
        }
    }

    public void eliminarPelicula(int idPelicula) {
        Introduciryeliminar.eliminarPeliculaPorId(idPelicula);
        System.out.println("Película eliminada con éxito.");
    }
    
}