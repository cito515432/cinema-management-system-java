
package Ejecutables;

import Basedatos.Consultas;
import Basedatos.Introduciryeliminar;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeatrosManager {
    public void agregarTeatro(int id_teatro, String direccion, String ciudad, int n_sillas_x_sala, int n_salas) {
        Introduciryeliminar.insertarTeatro(id_teatro, direccion, ciudad, n_sillas_x_sala, n_salas);
        System.out.println("Teatro agregado con éxito. ID de teatro: " + id_teatro);
    }

    public void mostrarTeatrosDisponibles() {
        StringBuilder teatrosInfo = new StringBuilder();
        teatrosInfo.append("Teatros Disponibles:\n");
        ResultSet resultSet = Consultas.mostrarTodosLosTeatros();
        try {
            while (resultSet.next()) {
                String ID_teatro = resultSet.getString("id_cine");
                String direccion = resultSet.getString("direccion");
                String ciudad = resultSet.getString("ciudad");
                int n_sillas_x_sala = resultSet.getInt("n_sillas_x_sala");
                int n_salas = resultSet.getInt("n_salas");

                teatrosInfo.append("ID de teatro: ").append(ID_teatro).append("\n");
                teatrosInfo.append("Dirección: ").append(direccion).append("\n");
                teatrosInfo.append("Ciudad: ").append(ciudad).append("\n");
                teatrosInfo.append("Número de sillas por sala: ").append(n_sillas_x_sala).append("\n");
                teatrosInfo.append("Salas: ").append(n_salas).append("\n\n");
            }
            mostrarEnInterfazGrafica(teatrosInfo.toString());
        } catch (SQLException e) {
            System.out.println("Error al mostrar los teatros disponibles: " + e.getMessage());
        }
    }

    private void mostrarEnInterfazGrafica(String info) {
        JFrame frame = new JFrame("Teatros Disponibles");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        JTextArea textArea = new JTextArea(10, 40);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setText(info);

        panel.add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}