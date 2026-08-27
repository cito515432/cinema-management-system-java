
package Basedatos;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Consultas {
    
    // Método para validar el nombre de usuario y contraseña
    public static boolean validarUsuario(String nombre, String contraseña) {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            // Obtener la conexión
            conexion = Conexion.obtenerConexion();
            
            // Crear la declaración preparada
            String consulta = "SELECT * FROM cinecolombia.usuario WHERE nombre = ? AND contraseña = ?";
            statement = conexion.prepareStatement(consulta);
            statement.setString(1, nombre);
            statement.setString(2, contraseña);
            
            // Ejecutar la consulta
            resultSet = statement.executeQuery();
            
            // Si hay resultados, el usuario y la contraseña son correctos
            return resultSet.next();
            
        } catch (SQLException e) {
            System.out.println("Error al validar el usuario: " + e.getMessage());
            return false;
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
    }

    public static void consultarTeatroPorIdCine(int id_teatro) {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            // Obtener la conexión
            conexion = Conexion.obtenerConexion();
            
            // Crear la consulta
            String consulta = "SELECT * FROM teatros WHERE id_cine = ?";
            statement = conexion.prepareStatement(consulta);
            statement.setInt(1, id_teatro);
            
            // Ejecutar la consulta
            resultSet = statement.executeQuery();
            
            // Procesar los resultados
            while (resultSet.next()) {
                int idCineResultado = resultSet.getInt("id_teatro");
                String direccion = resultSet.getString("direccion");
                String ciudad = resultSet.getString("ciudad");
                int nSillasXSala = resultSet.getInt("n_sillas_x_sala");
                int nSalas = resultSet.getInt("n_salas");
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar la tabla teatros: " + e.getMessage());
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
    }
    
public static ResultSet consultarConfiteriaPorNombreCombo(String nombreCombo) {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            // Obtener la conexión
            conexion = Conexion.obtenerConexion();
            
            // Crear la consulta
            String consulta = "SELECT * FROM confiteria WHERE nombre_combo = ?";
            statement = conexion.prepareStatement(consulta);
            statement.setString(1, nombreCombo);
            
            // Ejecutar la consulta
            resultSet = statement.executeQuery();
            
            // No es necesario procesar los resultados aquí
            
        } catch (SQLException e) {
            System.out.println("Error al consultar la tabla confiteria: " + e.getMessage());
        } finally {
            // No es necesario cerrar los recursos aquí
        }
        
        // Retornar el ResultSet para que sea procesado fuera del método
        return resultSet;
    }

// Método para consultar la tabla peliculas por nombre de película
    public static ResultSet consultarPeliculaPorId(int idPelicula) {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Obtener la conexión
            conexion = Conexion.obtenerConexion();

            // Crear la consulta
            String consulta = "SELECT * FROM peliculas WHERE id_pelicula = ?";
            statement = conexion.prepareStatement(consulta);
            statement.setInt(1, idPelicula);

            // Ejecutar la consulta
            resultSet = statement.executeQuery();

            // No es necesario procesar los resultados aquí

        } catch (SQLException e) {
            System.out.println("Error al consultar la tabla peliculas: " + e.getMessage());
        } finally {
            // No es necesario cerrar los recursos aquí
        }

        // Retornar el ResultSet para que sea procesado fuera del método
        return resultSet;
    }
    
    public static ResultSet mostrarTodasLasPeliculas() {
    Connection conexion = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    
    try {
        // Obtener la conexión
        conexion = Conexion.obtenerConexion();
        
        // Crear la consulta
        String consulta = "SELECT * FROM peliculas";
        statement = conexion.prepareStatement(consulta);
        
        // Ejecutar la consulta
        resultSet = statement.executeQuery();
        
        // Retornar el ResultSet
        return resultSet;
        
    } catch (SQLException e) {
        System.out.println("Error al consultar la tabla de películas: " + e.getMessage());
        // En caso de error, retornar null
        return null;
    } finally {
        // No es necesario cerrar los recursos aquí
    }
}
    
    // Método para mostrar toda la tabla de teatros
    public static ResultSet mostrarTodosLosTeatros() {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            // Obtener la conexión
            conexion = Conexion.obtenerConexion();
            
            // Crear la consulta
            String consulta = "SELECT * FROM teatros";
            statement = conexion.prepareStatement(consulta);
            
            // Ejecutar la consulta
            resultSet = statement.executeQuery();
            
            // No es necesario procesar los resultados aquí
            
        } catch (SQLException e) {
            System.out.println("Error al consultar la tabla de teatros: " + e.getMessage());
        } finally {
            // No es necesario cerrar los recursos aquí
        }
        
        // Retornar el ResultSet para que sea procesado fuera del método
        return resultSet;
    }
    
    // Método para mostrar toda la tabla de confitería
    public static ResultSet mostrarTodaLaConfiteria() {
    Connection conexion = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    
    try {
        // Obtener la conexión
        conexion = Conexion.obtenerConexion();
        
        // Crear la consulta
        String consulta = "SELECT * FROM confiteria";
        statement = conexion.prepareStatement(consulta);
        
        // Ejecutar la consulta
        resultSet = statement.executeQuery();
        
        // No es necesario procesar los resultados aquí
        
    } catch (SQLException e) {
        System.out.println("Error al consultar la tabla de confitería: " + e.getMessage());
        // En caso de error, retornar null o un ResultSet vacío según lo que necesites en tu lógica
    } finally {
        // No cerramos los recursos aquí para permitir que el ResultSet esté disponible para su procesamiento
        // Los recursos se cerrarán en el método que llama a mostrarTodaLaConfiteria()
    }
    
    // Retornar el ResultSet para que sea procesado fuera del método
    return resultSet;
}
    
    // Método para consultar una factura por su ID
    public static void consultarFacturaPorId(int idFactura) {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            // Obtener la conexión
            conexion = Conexion.obtenerConexion();
            
            // Crear la consulta
            String consulta = "SELECT * FROM facturas WHERE id_facturas = ?";
            statement = conexion.prepareStatement(consulta);
            statement.setInt(1, idFactura);
            
            // Ejecutar la consulta
            resultSet = statement.executeQuery();
            
            // Procesar y mostrar los resultados
            if (resultSet.next()) {
                String nombreCombo = resultSet.getString("nombre_combo");
                String fechaFactura = resultSet.getString("fecha_factura");
                
                System.out.println("ID Factura: " + idFactura + ", Nombre Combo: " + nombreCombo + ", Fecha: " + fechaFactura);
            } else {
                System.out.println("No se encontró una factura con el ID especificado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar la factura: " + e.getMessage());
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
    }
    
    // Método para consultar un ticket por su ID
    public static void consultarTicketPorId(int idTicket) {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            // Obtener la conexión
            conexion = Conexion.obtenerConexion();
            
            // Crear la consulta
            String consulta = "SELECT * FROM tickets WHERE id_ticket = ?";
            statement = conexion.prepareStatement(consulta);
            statement.setInt(1, idTicket);
            
            // Ejecutar la consulta
            resultSet = statement.executeQuery();
            
            // Procesar y mostrar los resultados
            if (resultSet.next()) {
                int idPelicula = resultSet.getInt("id_pelicula");
                String fechaTicket = resultSet.getString("fecha_ticket");
                boolean requiereDocumento = resultSet.getBoolean("requiere_documento");
                
                System.out.println("ID Ticket: " + idTicket + ", ID Película: " + idPelicula + ", Fecha: " + fechaTicket + ", Requiere documento: " + requiereDocumento);
            } else {
                System.out.println("No se encontró un ticket con el ID especificado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar el ticket: " + e.getMessage());
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
    }
    
    public static ResultSet consultarUltimoIdTicket() throws SQLException {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            // Obtener la conexión
            conexion = Conexion.obtenerConexion();
            
            // Crear la consulta para obtener el último ID de ticket
            String consulta = "SELECT MAX(id_ticket) FROM tickets";
            statement = conexion.prepareStatement(consulta);
            
            // Ejecutar la consulta
            resultSet = statement.executeQuery();
            
            return resultSet;
        } finally {
            // No cerramos recursos aquí para permitir que el llamador maneje el cierre de la conexión y el statement
        }
    }
}
