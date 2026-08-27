
package Basedatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Introduciryeliminar {
    
    // Método para insertar una nueva película
    public static void insertarPelicula(String nombrePelicula, String fechaPelicula, String horaProyeccion, int edadMinima) {
        Connection conexion = null;
        PreparedStatement statement = null;
        
        try {
            // Obtener la conexión
            conexion = Conexion.obtenerConexion();
            
            // Crear la consulta de inserción
            String consulta = "INSERT INTO peliculas (nombre_pelicula, fecha_pelicula, hora_proyeccion, edad_minima) VALUES (?, ?, ?, ?)";
            statement = conexion.prepareStatement(consulta);
            
            // Establecer los parámetros
            statement.setString(1, nombrePelicula);
            statement.setString(2, fechaPelicula);
            statement.setString(3, horaProyeccion);
            statement.setInt(4, edadMinima);
            
            // Ejecutar la consulta de inserción
            statement.executeUpdate();
            
            System.out.println("¡Pelicula insertada correctamente!");
        } catch (SQLException e) {
            System.out.println("Error al insertar película: " + e.getMessage());
        } finally {
            // Cerrar recursos
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar el Statement: " + e.getMessage());
                }
            }
            if (conexion != null) {
                Conexion.cerrarConexion(conexion);
            }
        }
    }
    
    // Método para insertar un nuevo teatro
    public static void insertarTeatro(int id_teatro, String direccion, String ciudad, int n_sillas_x_sala, int n_salas) {
        Connection conexion = null;
        PreparedStatement statement = null;
        
        try {
            // Obtener la conexión
            conexion = Conexion.obtenerConexion();
            
            // Crear la consulta de inserción
            String consulta = "INSERT INTO teatros (id_teatro, direccion, ciudad, n_sillas_x_sala, n_salas) VALUES (?, ?, ?, ?, ?)";
            statement = conexion.prepareStatement(consulta);
            
            // Establecer los parámetros
            statement.setInt(1, id_teatro);
            statement.setString(2, direccion);
            statement.setString(3, ciudad);
            statement.setInt(4, n_sillas_x_sala);
            statement.setInt(5, n_salas);
            
            // Ejecutar la consulta de inserción
            statement.executeUpdate();
            
            System.out.println("¡Teatro insertado correctamente!");
        } catch (SQLException e) {
            System.out.println("Error al insertar teatro: " + e.getMessage());
        } finally {
            // Cerrar recursos
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar el Statement: " + e.getMessage());
                }
            }
            if (conexion != null) {
                Conexion.cerrarConexion(conexion);
            }
        }
    }
    
    // Método para insertar un nuevo combo en la confitería
    public static void insertarCombo(String nombreCombo, int precio, String descripcion) {
        Connection conexion = null;
        PreparedStatement statement = null;
        
        try {
            // Obtener la conexión
            conexion = Conexion.obtenerConexion();
            
            // Crear la consulta de inserción
            String consulta = "INSERT INTO confiteria (nombre_combo, precio, descripcion) VALUES (?, ?, ?)";
            statement = conexion.prepareStatement(consulta);
            
            // Establecer los parámetros
            statement.setString(1, nombreCombo);
            statement.setDouble(2, precio);
            statement.setString(3, descripcion);
            
            // Ejecutar la consulta de inserción
            statement.executeUpdate();
            
            System.out.println("¡Combo insertado correctamente!");
        } catch (SQLException e) {
            System.out.println("Error al insertar combo: " + e.getMessage());
        } finally {
            // Cerrar recursos
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar el Statement: " + e.getMessage());
                }
            }
            if (conexion != null) {
                Conexion.cerrarConexion(conexion);
            }
        }
    }
    
    // Método para eliminar una película por su ID
    public static void eliminarPeliculaPorId(int idPelicula) {
        Connection conexion = null;
        PreparedStatement statement = null;
        
        try {
            // Obtener la conexión
            conexion = Conexion.obtenerConexion();
            
            // Crear la consulta de eliminación
            String consulta = "DELETE FROM peliculas WHERE id_pelicula = ?";
            statement = conexion.prepareStatement(consulta);
            
            // Establecer el parámetro
            statement.setInt(1, idPelicula);
            
            // Ejecutar la consulta de eliminación
            statement.executeUpdate();
            
            System.out.println("¡Pelicula eliminada correctamente!");
        } catch (SQLException e) {
            System.out.println("Error al eliminar película: " + e.getMessage());
        } finally {
            // Cerrar recursos
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar el Statement: " + e.getMessage());
                }
            }
            if (conexion != null) {
                Conexion.cerrarConexion(conexion);
            }
        }
    }
    // Método para insertar un nuevo ticket
    public static void insertarTicket(int idPelicula, String fechaTicket, String requiereDocumento) {
        Connection conexion = null;
        PreparedStatement statement = null;
        
        try {
            // Obtener la conexión
            conexion = Conexion.obtenerConexion();
            
            // Crear la consulta de inserción
            String consulta = "INSERT INTO tickets (id_pelicula, fecha_ticket, requiere_documento) VALUES (?, ?, ?)";
            statement = conexion.prepareStatement(consulta);
            
            // Establecer los parámetros
            statement.setInt(1, idPelicula);
            statement.setString(2, fechaTicket);
            statement.setString(3, requiereDocumento);
            
            // Ejecutar la consulta de inserción
            statement.executeUpdate();
            
            System.out.println("¡Ticket insertado correctamente!");
        } catch (SQLException e) {
            System.out.println("Error al insertar ticket: " + e.getMessage());
        } finally {
            // Cerrar recursos
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar el Statement: " + e.getMessage());
                }
            }
            if (conexion != null) {
                Conexion.cerrarConexion(conexion);
            }
        }
    }
    
    // Método para insertar una nueva factura
    public static int insertarFactura(String nombre, String fecha) {
    Connection conexion = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    int id_facturas = 0;

    try {
        conexion = Conexion.obtenerConexion();
        String consulta = "INSERT INTO facturas (nombre_combo, fecha_factura) VALUES (?, ?)";
        statement = conexion.prepareStatement(consulta, new String[]{"id_facturas"});
        statement.setString(1, nombre);
        statement.setString(2, fecha);
        statement.executeUpdate();

        resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) {
            id_facturas = resultSet.getInt(1);
        }
    } catch (SQLException e) {
        System.out.println("Error al insertar la factura: " + e.getMessage());
    } finally {
        // Cerrar recursos
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar el ResultSet: " + e.getMessage());
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar el Statement: " + e.getMessage());
            }
        }
        if (conexion != null) {
            Conexion.cerrarConexion(conexion);
        }
    }

    return id_facturas;
}
    
    // Método para eliminar un ticket por su ID
    public static void eliminarTicketPorId(int idTicket) {
        Connection conexion = null;
        PreparedStatement statement = null;
        
        try {
            // Obtener la conexión
            conexion = Conexion.obtenerConexion();
            
            // Crear la consulta de eliminación
            String consulta = "DELETE FROM tickets WHERE id_ticket = ?";
            statement = conexion.prepareStatement(consulta);
            
            // Establecer el parámetro
            statement.setInt(1, idTicket);
            
            // Ejecutar la consulta de eliminación
            statement.executeUpdate();
            
            System.out.println("¡Ticket eliminado correctamente!");
        } catch (SQLException e) {
            System.out.println("Error al eliminar ticket: " + e.getMessage());
        } finally {
            // Cerrar recursos
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar el Statement: " + e.getMessage());
                }
            }
            if (conexion != null) {
                Conexion.cerrarConexion(conexion);
            }
        }
    }
    
    // Método para eliminar una factura por su ID
    public static void eliminarFacturaPorId(int idFactura) {
        Connection conexion = null;
        PreparedStatement statement = null;
        
        try {
            // Obtener la conexión
            conexion = Conexion.obtenerConexion();
            
            // Crear la consulta de eliminación
            String consulta = "DELETE FROM facturas WHERE id_facturas = ?";
            statement = conexion.prepareStatement(consulta);
            
            // Establecer el parámetro
            statement.setInt(1, idFactura);
            
            // Ejecutar la consulta de eliminación
            statement.executeUpdate();
            
            System.out.println("¡Factura eliminada correctamente!");
        } catch (SQLException e) {
            System.out.println("Error al eliminar factura: " + e.getMessage());
        } finally {
            // Cerrar recursos
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar el Statement: " + e.getMessage());
                }
            }
            if (conexion != null) {
                Conexion.cerrarConexion(conexion);
            }
        }
    }
}