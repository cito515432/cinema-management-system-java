/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejecutables;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Basedatos.*;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InterfazGUI {
    private JFrame frame;
    private JPanel panel;
    private JButton gestionarButton;
    private JButton comprarButton;
    private JButton salirButton;

    private TeatrosManager teatrosManager;
    private PeliculasManager peliculasManager;
    private ConfiteriaManager confiteriaManager;

    public InterfazGUI() {
        initialize();
        addActionListeners();
        teatrosManager = new TeatrosManager();
        peliculasManager = new PeliculasManager();
        confiteriaManager = new ConfiteriaManager();
    }

    private void initialize() {
    frame = new JFrame("Sistema de Gestión y Compra");
    frame.setSize(400, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    

    panel = new JPanel();
    panel.setLayout(new GridBagLayout());
    panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

    JLabel titleLabel = new JLabel("Prototipo Software Gestión Cine");
    GridBagConstraints titleConstraints = new GridBagConstraints();
    titleConstraints.gridx = 0;
    titleConstraints.gridy = 0;
    titleConstraints.anchor = GridBagConstraints.NORTH;
    titleConstraints.insets = new Insets(0, 0, 20, 0);
    panel.add(titleLabel, titleConstraints);

    gestionarButton = new JButton("Gestionar");
    comprarButton = new JButton("Comprar");
    salirButton = new JButton("Salir");

    GridBagConstraints constraints = new GridBagConstraints();
    constraints.gridx = 0;
    constraints.gridy = 1;
    constraints.anchor = GridBagConstraints.CENTER;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(0, 0, 20, 0);
    panel.add(gestionarButton, constraints);

    constraints.gridx = 0;
    constraints.gridy = 2;
    constraints.anchor = GridBagConstraints.CENTER;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(0, 0, 20, 0);
    panel.add(comprarButton, constraints);

    constraints.gridx = 0;
    constraints.gridy = 3;
    constraints.anchor = GridBagConstraints.CENTER;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    panel.add(salirButton, constraints);

    frame.add(panel);
    frame.setVisible(true);

    gestionarButton.setPreferredSize(new Dimension(100, 30));
    gestionarButton.setBackground(new Color(0, 128, 255));
    gestionarButton.setForeground(Color.WHITE);

    comprarButton.setPreferredSize(new Dimension(100, 30));
    comprarButton.setBackground(new Color(0, 128, 255));
    comprarButton.setForeground(Color.WHITE);

    salirButton.setPreferredSize(new Dimension(100, 30));
    salirButton.setBackground(new Color(0, 128, 255));
    salirButton.setForeground(Color.WHITE);

    panel.setBackground(new Color(245, 245, 220));

    gestionarButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (seguridad()) {
                gestionar();
            }
        }
    });
}

private boolean seguridad() {

    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(4, 1));
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    panel.setBackground(new Color(245, 245, 220));

    JLabel usuarioLabel = new JLabel("Ingrese su usuario:");
    JTextField usuarioTextField = new JTextField();
    panel.add(usuarioLabel);
    panel.add(usuarioTextField);

    JLabel contrasenaLabel = new JLabel("Ingrese su contraseña:");
    JPasswordField contrasenaTextField = new JPasswordField();
    panel.add(contrasenaLabel);
    panel.add(contrasenaTextField);

    int resultado = JOptionPane.showConfirmDialog(null, panel, "Iniciar sesión", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    if (resultado == JOptionPane.OK_OPTION) {
        String usuarioIngresado = usuarioTextField.getText();
        String contrasenaIngresada = new String(contrasenaTextField.getPassword());
        
         boolean valido = Consultas.validarUsuario(usuarioIngresado, contrasenaIngresada);
        System.out.println("Usuario válido: " + valido);

        if (valido) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    } else {
        return false;
    }
}

private void addActionListeners() {
    UIManager.put("Button.background", new Color(0, 128, 255)); // Establecer el color de fondo de los botones a azul
    UIManager.put("Button.foreground", Color.WHITE); // Establecer el color del texto de los botones a blanco
    comprarButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            comprar();
        }
    });

    salirButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            salir();
        }
    });

    gestionarButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        }
    });

    // Establecer el fondo beige en todo el cuadro de diálogo
    UIManager.put("OptionPane.background", new Color(245, 245, 220));
    UIManager.put("OptionPane.background", new Color(245, 245, 220));
    UIManager.put("Panel.background", new Color(245, 245, 220));
}  

    private void gestionar() {
    JFrame gestionFrame = new JFrame("Gestionar");
    gestionFrame.setSize(400, 300);
    gestionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JPanel gestionPanel = new JPanel();
    gestionPanel.setLayout(new GridBagLayout());
    gestionPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

    // Etiqueta para mostrar el mensaje de bienvenida
    JLabel welcomeLabel = new JLabel("Bienvenido Administrador");
    GridBagConstraints welcomeConstraints = new GridBagConstraints();
    welcomeConstraints.gridx = 0;
    welcomeConstraints.gridy = 0;
    welcomeConstraints.anchor = GridBagConstraints.CENTER;
    welcomeConstraints.insets = new Insets(0, 0, 20, 0);
    gestionPanel.add(welcomeLabel, welcomeConstraints);

    JButton agregarPeliculaButton = new JButton("Agregar Película");
    JButton agregarTeatroButton = new JButton("Agregar Teatro");
    JButton agregarComboButton = new JButton("Agregar Combo");
    JButton eliminarPeliculaButton = new JButton("Eliminar Película"); // Nuevo botón

    GridBagConstraints constraints = new GridBagConstraints();
    constraints.gridx = 0;
    constraints.gridy = 1;
    constraints.anchor = GridBagConstraints.CENTER;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(0, 0, 20, 0);
    gestionPanel.add(agregarPeliculaButton, constraints);

    constraints.gridx = 0;
    constraints.gridy = 2;
    constraints.anchor = GridBagConstraints.CENTER;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(0, 0, 20, 0);
    gestionPanel.add(agregarTeatroButton, constraints);

    constraints.gridx = 0;
    constraints.gridy = 3;
    constraints.anchor = GridBagConstraints.CENTER;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(0, 0, 20, 0);
    gestionPanel.add(agregarComboButton, constraints);
    
    constraints.gridx = 0;
    constraints.gridy = 4;
    constraints.anchor = GridBagConstraints.CENTER;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(0, 0, 20, 0);
    gestionPanel.add(eliminarPeliculaButton, constraints); 

    gestionFrame.add(gestionPanel);
    gestionFrame.setVisible(true);

    agregarPeliculaButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            agregarPelicula();
        }
    });

    agregarTeatroButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            agregarTeatro();
        }
    });

    agregarComboButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            agregarCombo();
        }
    });
    
    eliminarPeliculaButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            mostrarPeliculasDisponibles();
            eliminarPelicula(); 
        }
    });

    // Establecer el fondo beige en el panel
    gestionPanel.setBackground(new Color(245, 245, 220));

    // Establecer propiedades de los botones
    agregarPeliculaButton.setPreferredSize(new Dimension(150, 30));
    agregarPeliculaButton.setBackground(new Color(0, 128, 255));
    agregarPeliculaButton.setForeground(Color.WHITE);

    agregarTeatroButton.setPreferredSize(new Dimension(150, 30));
    agregarTeatroButton.setBackground(new Color(0, 128, 255));
    agregarTeatroButton.setForeground(Color.WHITE);

    agregarComboButton.setPreferredSize(new Dimension(150, 30));
    agregarComboButton.setBackground(new Color(0, 128, 255));
    agregarComboButton.setForeground(Color.WHITE);
    
    eliminarPeliculaButton.setPreferredSize(new Dimension(150, 30)); // Establecer el tamaño del botón eliminar
    eliminarPeliculaButton.setBackground(new Color(0, 128, 255)); // Establecer el color de fondo
    eliminarPeliculaButton.setForeground(Color.WHITE); // Establecer el color del texto
}

    private void comprar() {
    JFrame comprarFrame = new JFrame("Comprar");
    comprarFrame.setSize(400, 300);
    comprarFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JPanel comprarPanel = new JPanel();
    comprarPanel.setLayout(new GridBagLayout());
    comprarPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

    // Etiqueta para mostrar el mensaje de bienvenida
    JLabel welcomeLabel = new JLabel("Bienvenido Cliente");
    GridBagConstraints welcomeConstraints = new GridBagConstraints();
    welcomeConstraints.gridx = 0;
    welcomeConstraints.gridy = 0;
    welcomeConstraints.anchor = GridBagConstraints.CENTER;
    welcomeConstraints.insets = new Insets(0, 0, 20, 0);
    comprarPanel.add(welcomeLabel, welcomeConstraints);

    JButton comprarTicketButton = new JButton("Comprar Ticket");
    JButton comprarComboButton = new JButton("Comprar Combo");
    JButton verTeatrosButton = new JButton("Ver Teatros Disponibles");

    GridBagConstraints constraints = new GridBagConstraints();
    constraints.gridx = 0;
    constraints.gridy = 1;
    constraints.anchor = GridBagConstraints.CENTER;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(0, 0, 20, 0);
    comprarPanel.add(comprarTicketButton, constraints);

    constraints.gridx = 0;
    constraints.gridy = 2;
    constraints.anchor = GridBagConstraints.CENTER;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(0, 0, 20, 0);
    comprarPanel.add(comprarComboButton, constraints);

    constraints.gridx = 0;
    constraints.gridy = 3;
    constraints.anchor = GridBagConstraints.CENTER;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    comprarPanel.add(verTeatrosButton, constraints);

    comprarFrame.add(comprarPanel);
    comprarFrame.setVisible(true);

    comprarTicketButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            mostrarPeliculasDisponibles();
            comprarTicket();
        }
    });

    comprarComboButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            mostrarCombosDisponibles();
            comprarCombo();
        }
    });

    verTeatrosButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
           VentanaTeatros ventanaTeatros = new VentanaTeatros();
           
        }
    });

    comprarPanel.setBackground(new Color(245, 245, 220));
    comprarTicketButton.setPreferredSize(new Dimension(150, 30));
    comprarTicketButton.setBackground(new Color(0, 128, 255));
    comprarTicketButton.setForeground(Color.WHITE);

    comprarComboButton.setPreferredSize(new Dimension(150, 30));
    comprarComboButton.setBackground(new Color(0, 128, 255));
    comprarComboButton.setForeground(Color.WHITE);

    verTeatrosButton.setPreferredSize(new Dimension(150, 30));
    verTeatrosButton.setBackground(new Color(0, 128, 255));
    verTeatrosButton.setForeground(Color.WHITE);
}
private void mostrarPeliculasDisponibles() {
        JFrame frame = new JFrame("Peliculas Disponibles");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 245, 220));

        JTextArea textArea = new JTextArea(10, 40);
        textArea.setEditable(false);
        textArea.setBackground(new Color(245, 245, 220));
        textArea.setForeground(new Color(0, 128, 255));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBackground(new Color(245, 245, 220));

        ResultSet resultSet = Consultas.mostrarTodasLasPeliculas();
        if (resultSet != null) {
            try {
                StringBuilder peliculasInfo = new StringBuilder();
                peliculasInfo.append("Peliculas Disponibles:\n");
                while (resultSet.next()) {
                    peliculasInfo.append("ID: ").append(resultSet.getInt("id_pelicula")).append("\n");
                    peliculasInfo.append("Nombre: ").append(resultSet.getString("nombre_pelicula")).append("\n");
                    peliculasInfo.append("Fecha: ").append(resultSet.getString("fecha_pelicula")).append("\n");
                    peliculasInfo.append("Hora de proyección: ").append(resultSet.getString("hora_proyeccion")).append("\n");
                    peliculasInfo.append("Edad mínima: ").append(resultSet.getInt("edad_minima")).append("\n\n");
                }
                textArea.setText(peliculasInfo.toString());
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al mostrar las películas: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo obtener la lista de películas.");
        }

        panel.add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
    private void agregarPelicula() {
    JFrame agregarPeliculaFrame = new JFrame("Agregar Película");
    agregarPeliculaFrame.setSize(400, 300);
    agregarPeliculaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JPanel agregarPeliculaPanel = new JPanel();
    agregarPeliculaPanel.setLayout(new GridBagLayout());
    agregarPeliculaPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
    agregarPeliculaPanel.setBackground(new Color(245, 245, 220));

    JLabel nombreLabel = new JLabel("Nombre de la Película:");
    nombreLabel.setForeground(new Color(0, 128, 255));
    JTextField nombreTextField = new JTextField();
    JLabel fechaLabel = new JLabel("Fecha:");
    fechaLabel.setForeground(new Color(0, 128, 255));
    JTextField fechaTextField = new JTextField();
    JLabel horaLabel = new JLabel("Hora de proyección (24h):");
    horaLabel.setForeground(new Color(0, 128, 255));
    JTextField horaTextField = new JTextField();
    JLabel edadLabel = new JLabel("Edad mínima:");
    edadLabel.setForeground(new Color(0, 128, 255));
    JTextField edadTextField = new JTextField();

    JButton agregarButton = new JButton("Agregar");
    agregarButton.setBackground(new Color(0, 128, 255));
    agregarButton.setForeground(Color.WHITE);

    GridBagConstraints constraints =new GridBagConstraints();
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.anchor = GridBagConstraints.CENTER;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(0, 0, 20, 0);
    agregarPeliculaPanel.add(nombreLabel, constraints);

    constraints.gridx = 1;
    constraints.gridy = 0;
    constraints.anchor = GridBagConstraints.CENTER;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(0, 0, 20, 0);
    agregarPeliculaPanel.add(nombreTextField, constraints);

    constraints.gridx = 0;
    constraints.gridy = 1;
    constraints.anchor = GridBagConstraints.CENTER;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(0, 0, 20, 0);
    agregarPeliculaPanel.add(fechaLabel, constraints);

    constraints.gridx = 1;
    constraints.gridy = 1;
    constraints.anchor = GridBagConstraints.CENTER;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(0, 0, 20, 0);
    agregarPeliculaPanel.add(fechaTextField, constraints);

    constraints.gridx = 0;
    constraints.gridy = 2;
    constraints.anchor = GridBagConstraints.CENTER;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(0, 0, 20, 0);
    agregarPeliculaPanel.add(horaLabel, constraints);

    constraints.gridx = 1;
    constraints.gridy = 2;
    constraints.anchor = GridBagConstraints.CENTER;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(0, 0, 20, 0);
    agregarPeliculaPanel.add(horaTextField, constraints);

    constraints.gridx = 0;
    constraints.gridy = 3;
    constraints.anchor = GridBagConstraints.CENTER;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(0, 0, 20, 0);
    agregarPeliculaPanel.add(edadLabel, constraints);

    constraints.gridx = 1;
    constraints.gridy = 3;
    constraints.anchor = GridBagConstraints.CENTER;
    constraints.fill= GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(0, 0, 20, 0);
    agregarPeliculaPanel.add(edadTextField, constraints);

    constraints.gridx = 1;
    constraints.gridy = 4;
    constraints.anchor = GridBagConstraints.CENTER;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(0, 0, 0, 0);
    agregarPeliculaPanel.add(agregarButton, constraints);

    agregarPeliculaFrame.add(agregarPeliculaPanel);
    agregarPeliculaFrame.setVisible(true);

    agregarButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            // Obtener los valores ingresados por el usuario
            String nombrePelicula = nombreTextField.getText();
            String fecha = fechaTextField.getText();
            String horaProyeccion = horaTextField.getText();
            int edadMinima = Integer.parseInt(edadTextField.getText());

            // Agregar la película utilizando el PeliculasManager
            peliculasManager.agregarPelicula(nombrePelicula, fecha, horaProyeccion, edadMinima);

            // Cerrar la ventana de agregar película después de agregarla
            agregarPeliculaFrame.dispose();
        }
    });
}

    private void agregarTeatro() {
    JFrame agregarTeatroFrame = new JFrame("Agregar Teatro");
    agregarTeatroFrame.setSize(450, 300);
    agregarTeatroFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JPanel agregarTeatroPanel = new JPanel();
    agregarTeatroPanel.setLayout(new GridLayout(7, 2));
    agregarTeatroPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
    agregarTeatroPanel.setBackground(new Color(245, 245, 220));

    JLabel idLabel = new JLabel("ID del Teatro:");
    idLabel.setForeground(new Color(0, 128, 255));
    JTextField idTextField = new JTextField();
    JLabel direccionLabel = new JLabel("Dirección:");
    direccionLabel.setForeground(new Color(0, 128, 255));
    JTextField direccionTextField = new JTextField();
    JLabel ciudadLabel = new JLabel("Ciudad:");
    ciudadLabel.setForeground(new Color(0, 128, 255));
    JTextField ciudadTextField = new JTextField();
    JLabel sillasLabel = new JLabel("Número de sillas por sala:");
    sillasLabel.setForeground(new Color(0, 128, 255));
    JTextField sillasTextField = new JTextField();
    JLabel salasLabel = new JLabel("Número de Salas:");
    salasLabel.setForeground(new Color(0, 128, 255));
    JTextField salasTextField = new JTextField();

    JButton agregarButton = new JButton("Agregar");
    agregarButton.setBackground(new Color(0, 128, 255));
    agregarButton.setForeground(Color.WHITE);

    agregarTeatroPanel.add(idLabel);
    agregarTeatroPanel.add(idTextField);
    agregarTeatroPanel.add(direccionLabel);
    agregarTeatroPanel.add(direccionTextField);
    agregarTeatroPanel.add(ciudadLabel);
    agregarTeatroPanel.add(ciudadTextField);
    agregarTeatroPanel.add(sillasLabel);
    agregarTeatroPanel.add(sillasTextField);
    agregarTeatroPanel.add(salasLabel);
    agregarTeatroPanel.add(salasTextField);
    agregarTeatroPanel.add(new JSeparator());
    agregarTeatroPanel.add(agregarButton);

    agregarTeatroFrame.add(agregarTeatroPanel);
    agregarTeatroFrame.setVisible(true);

    agregarButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            // Obtener los valores ingresados por el usuario
            int idTeatro = Integer.parseInt(idTextField.getText());
            String direccion = direccionTextField.getText();
            String ciudad = ciudadTextField.getText();
            int sillasPorSala = Integer.parseInt(sillasTextField.getText());
            int numSalas = Integer.parseInt(salasTextField.getText());

            // Agregar el teatro utilizando el TeatrosManager
            teatrosManager.agregarTeatro(idTeatro, direccion, ciudad, sillasPorSala, numSalas);

            // Cerrar la ventana de agregar teatro después de agregarlo
            agregarTeatroFrame.dispose();
        }
    });
}

    private void agregarCombo() {
    JFrame agregarComboFrame = new JFrame("Agregar Combo");
    agregarComboFrame.setSize(400, 300);
    agregarComboFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JPanel agregarComboPanel = new JPanel();
    agregarComboPanel.setLayout(new GridLayout(4, 2));
    agregarComboPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
    agregarComboPanel.setBackground(new Color(245, 245, 220));

    JLabel nombreLabel = new JLabel("Nombre del Combo:");
    nombreLabel.setForeground(new Color(0, 128, 255));
    JTextField nombreTextField = new JTextField();
    JLabel precioLabel = new JLabel("Precio:");
    precioLabel.setForeground(new Color(0, 128, 255));
    JTextField precioTextField = new JTextField();
    JLabel descripcionLabel = new JLabel("Descripción:");
    descripcionLabel.setForeground(new Color(0, 128, 255));
    JTextField descripcionTextField = new JTextField();

    JButton agregarButton = new JButton("Agregar");
    agregarButton.setBackground(new Color(0, 128, 255));
    agregarButton.setForeground(Color.WHITE);

    agregarComboPanel.add(nombreLabel);
    agregarComboPanel.add(nombreTextField);
    agregarComboPanel.add(precioLabel);
    agregarComboPanel.add(precioTextField);
    agregarComboPanel.add(descripcionLabel);
    agregarComboPanel.add(descripcionTextField);
    agregarComboPanel.add(agregarButton);

    agregarComboFrame.add(agregarComboPanel);
    agregarComboFrame.setVisible(true);

    agregarButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            // Obtener los valores ingresados por el usuario
            String nombre = nombreTextField.getText();
            int precio = Integer.parseInt(precioTextField.getText());
            String descripcion = descripcionTextField.getText();

            // Agregar el combo utilizando el ConfiteriaManager
            confiteriaManager.agregarCombo(nombre, precio, descripcion);

            // Cerrar la ventana de agregar combo después de agregarlo
            agregarComboFrame.dispose();
        }
    });
}
    private void mostrarCombosDisponibles() {
    JFrame frame = new JFrame("Combos Disponibles");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JPanel panel = new JPanel(new BorderLayout());
    panel.setBackground(new Color(245, 245, 220));

    JTextArea textArea = new JTextArea(10, 40);
    textArea.setEditable(false);
    textArea.setBackground(new Color(245, 245, 220));
    textArea.setForeground(new Color(0, 128, 255));

    JScrollPane scrollPane = new JScrollPane(textArea);
    scrollPane.setBackground(new Color(245, 245, 220));

    StringBuilder combosInfo = new StringBuilder();
    combosInfo.append("Combos Disponibles:\n");
    
    // Aquí obtenemos el ResultSet con los combos desde ConfiteriaManager
    ResultSet resultSet = Consultas.mostrarTodaLaConfiteria();
    try {
        while (resultSet.next()) {
            String nombreCombo = resultSet.getString("nombre_combo");
            int precio = resultSet.getInt("precio");
            String descripcion = resultSet.getString("descripcion");
            
            // Agregamos la información de cada combo al StringBuilder
            combosInfo.append("Nombre del Combo: ").append(nombreCombo).append("\n");
            combosInfo.append("Precio: ").append(precio).append("\n");
            combosInfo.append("Descripción: ").append(descripcion).append("\n\n");
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener combos disponibles: " + e.getMessage());
    }
    textArea.setText(combosInfo.toString());

    panel.add(scrollPane, BorderLayout.CENTER);
    frame.getContentPane().add(panel);
    frame.pack();
    frame.setVisible(true);
}
private void mostrarTicket(ResultSet peliculaResultSet, String fechaTicket, int idTicket) {
        // Mostrar la información del ticket en una ventana
        JFrame ticketFrame = new JFrame("Ticket Comprado");
        ticketFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel ticketPanel = new JPanel(new GridBagLayout());
        
        

        JTextArea ticketTextArea = new JTextArea(10, 40);
        ticketTextArea.setEditable(false);
        

        try {
            ticketTextArea.append("¡Compra realizada con éxito!\n");
            ticketTextArea.append("------------- TICKET -------------\n");
            ticketTextArea.append("ID de ticket: " + idTicket + "\n");
            ticketTextArea.append("Nombre: " + peliculaResultSet.getString("nombre_pelicula") + "\n");
            ticketTextArea.append("Fecha: " + fechaTicket + "\n");
            ticketTextArea.append("Hora de proyección: " + peliculaResultSet.getString("hora_proyeccion") + "\n");
            ticketTextArea.append("Edad mínima: " + peliculaResultSet.getInt("edad_minima") + "\n");
            ticketTextArea.append("Requiere documento: " + (peliculaResultSet.getInt("edad_minima") > 18 ? "Sí" : "No") + "\n");
            ticketTextArea.append("----------------------------------");

            ticketPanel.add(ticketTextArea);
            ticketFrame.getContentPane().add(ticketPanel);
            ticketFrame.pack();
            ticketFrame.setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al mostrar el ticket: " + ex.getMessage());
        }
    }
    public void comprarTicket() {
        // Ventana para pedir el ID de la película y la cantidad
        JFrame inputFrame = new JFrame("Información de Compra");
        inputFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        inputPanel.setBackground(new Color(245, 245, 220)); // Conservar el color beige

        JLabel idLabel = new JLabel("ID de la Película:");
        JTextField idField = new JTextField(10);
        JLabel cantidadLabel = new JLabel("Cantidad:");
        JTextField cantidadField = new JTextField(5);

        JButton comprarButton = new JButton("Comprar");
        comprarButton.setBackground(new Color(0, 128, 255)); // Conservar el color azul
        comprarButton.setForeground(Color.WHITE);

        comprarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int idPelicula = Integer.parseInt(idField.getText());
                    int cantidadTickets = Integer.parseInt(cantidadField.getText());

                    // Comprar la cantidad de tickets con los datos ingresados
                    for (int i = 0; i < cantidadTickets; i++) {
                        ResultSet peliculaResultSet = Consultas.consultarPeliculaPorId(idPelicula);
                        if (peliculaResultSet.next()) {
                            String fechaTicket = obtenerFechaActual();
                            String requiereDocumento = peliculaResultSet.getInt("edad_minima") > 18 ? "Sí" : "No";
                            Introduciryeliminar.insertarTicket(idPelicula, fechaTicket, requiereDocumento);
                            ResultSet ticketResultSet = Consultas.consultarUltimoIdTicket();
                            int idTicket = 0;
                            if (ticketResultSet.next()) {
                                idTicket = ticketResultSet.getInt(1);
                            }
                            mostrarTicket(peliculaResultSet, fechaTicket, idTicket);
                        } else {
                            JOptionPane.showMessageDialog(null, "El ID de película ingresado no es válido.");
                        }
                    }
                    inputFrame.dispose(); // Cerrar la ventana de entrada después de comprar
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un ID y una cantidad válidos.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al comprar el ticket: " + ex.getMessage());
                }
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        inputPanel.add(idLabel, gbc);

        gbc.gridx = 1;
        inputPanel.add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(cantidadLabel, gbc);

        gbc.gridx = 1;
        inputPanel.add(cantidadField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        inputPanel.add(comprarButton, gbc);

        inputFrame.getContentPane().add(inputPanel);
        inputFrame.pack();
        inputFrame.setVisible(true);
    }

    private void comprarCombo() {
    // Ventana para pedir el nombre del combo y la cantidad
    JFrame inputFrame = new JFrame("Información de Compra");
    inputFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    JPanel inputPanel = new JPanel(new GridBagLayout());
    inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    inputPanel.setBackground(new Color(245, 245, 220)); // Conservar el color beige

    JLabel nombreLabel = new JLabel("Nombre del combo:");
    JTextField nombreField = new JTextField(15);
    JLabel cantidadLabel = new JLabel("Cantidad:");
    JTextField cantidadField = new JTextField(5);

    JButton comprarButton = new JButton("Comprar");
    comprarButton.setBackground(new Color(0, 128, 255)); // Conservar el color azul
    comprarButton.setForeground(Color.WHITE);

    comprarButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String nombreCombo = nombreField.getText();
            int cantidadCombos = 0;
            try {
                cantidadCombos = Integer.parseInt(cantidadField.getText());
                // Consultar el combo seleccionado por el nombre
                ResultSet comboResultSet = Consultas.consultarConfiteriaPorNombreCombo(nombreCombo);
                if (comboResultSet.next()) {
                    // Insertar la factura
                    String fechaFactura = obtenerFechaActual();
                    int idFactura = Introduciryeliminar.insertarFactura(nombreCombo, fechaFactura);

                    // Mostrar la factura generada
                    mostrarFactura(comboResultSet, cantidadCombos, idFactura);

                    inputFrame.dispose(); // Cerrar la ventana de entrada después de comprar
                } else {
                    JOptionPane.showMessageDialog(null, "El combo seleccionado no está disponible.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ingrese una cantidad válida.");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al comprar el combo: " + ex.getMessage());
            }
        }
    });

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(5, 5, 5, 5);
    inputPanel.add(nombreLabel, gbc);

    gbc.gridx = 1;
    inputPanel.add(nombreField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    inputPanel.add(cantidadLabel, gbc);

    gbc.gridx = 1;
    inputPanel.add(cantidadField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.CENTER;
    inputPanel.add(comprarButton, gbc);

    inputFrame.getContentPane().add(inputPanel);
    inputFrame.pack();
    inputFrame.setVisible(true);
}

private String obtenerFechaActual() {
    // Obtener la fecha actual
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate date = LocalDate.now();
    return date.format(formatter);
}

private void mostrarFactura(ResultSet comboResultSet, int cantidadCombos, int idFactura) throws SQLException {
    JFrame frame = new JFrame("Factura de Compra");
    frame.setSize(320, 200); // Ajustar el tamaño de la ventana
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
    JPanel panel = new JPanel(new BorderLayout());
    JTextArea textArea = new JTextArea();
    textArea.setEditable(false);
    
    String factura = "¡Compra realizada con éxito!\n" +
                     "------------- FACTURA -------------\n";
    do {
        String nombreCombo = comboResultSet.getString("nombre_combo");
        int precio = comboResultSet.getInt("precio");
        String descripcion = comboResultSet.getString("descripcion");
        factura += "ID Factura: " + idFactura + "\n" +
                   "Combo: " + nombreCombo + "\n" +
                   "Precio: " + precio + "\n" +
                   "Descripción: " + descripcion + "\n" +
                   "Cantidad: " + cantidadCombos + "\n" + // Mostrar la cantidad de combos
                   "Fecha compra: " + obtenerFechaActual() + "\n" + // Mostrar la fecha actual
                   "-------------------------------------------------\n";
    } while (comboResultSet.next());

    textArea.setText(factura);
    panel.add(textArea, BorderLayout.CENTER);
    
    frame.getContentPane().add(panel);
    frame.setVisible(true);
}

    public class VentanaTeatros extends JFrame {
    private Consultas consultas;

    public VentanaTeatros() {
        this.consultas = new Consultas();
        mostrarTeatrosDisponibles();
    }

    public void mostrarTeatrosDisponibles() {
        JFrame frame = new JFrame("Teatros Disponibles");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 245, 220));

        JTextArea textArea = new JTextArea(10, 40);
        textArea.setEditable(false);
        textArea.setBackground(new Color(245, 245, 220));
        textArea.setForeground(new Color(0, 128, 255));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBackground(new Color(245, 245, 220));

        StringBuilder teatrosInfo = new StringBuilder();
        teatrosInfo.append("Teatros Disponibles:\n");

        // Consultar la base de datos para obtener la información de los teatros
        ResultSet resultSet = consultas.mostrarTodosLosTeatros();
        try {
            while (resultSet.next()) {
                int id_teatro = resultSet.getInt("id_teatro");
                String direccion = resultSet.getString("direccion");
                String ciudad = resultSet.getString("ciudad");
                int numSillasPorSala = resultSet.getInt("n_sillas_x_sala");
                String salas = resultSet.getString("n_salas");

                teatrosInfo.append("ID de teatro: ").append(id_teatro).append("\n");
                teatrosInfo.append("Dirección: ").append(direccion).append("\n");
                teatrosInfo.append("Ciudad: ").append(ciudad).append("\n");
                teatrosInfo.append("Número de sillas por sala: ").append(numSillasPorSala).append("\n");
                teatrosInfo.append("Salas: ").append(salas).append("\n\n");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar teatros: " + e.getMessage());
        } finally {
            // Cerrar el ResultSet después de usarlo
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        textArea.setText(teatrosInfo.toString());

        panel.add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
   
   private void eliminarPelicula() {
    JFrame eliminarPeliculaFrame = new JFrame("Eliminar Película");
    eliminarPeliculaFrame.setSize(400, 300);
    eliminarPeliculaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JPanel eliminarPeliculaPanel = new JPanel();
    eliminarPeliculaPanel.setLayout(new GridBagLayout());
    eliminarPeliculaPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
    eliminarPeliculaPanel.setBackground(new Color(245, 245, 220));

    JLabel idLabel = new JLabel("ID de la Película:");
    idLabel.setForeground(new Color(0, 128, 255));
    JTextField idTextField = new JTextField();

    JButton eliminarButton = new JButton("Eliminar");
    eliminarButton.setBackground(new Color(0, 128, 255));
    eliminarButton.setForeground(Color.WHITE);

    GridBagConstraints constraints = new GridBagConstraints();
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.anchor = GridBagConstraints.CENTER;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(0, 0, 20, 0);
    eliminarPeliculaPanel.add(idLabel, constraints);

    constraints.gridx = 1;
    constraints.gridy = 0;
    constraints.anchor = GridBagConstraints.CENTER;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(0, 0, 20, 0);
    eliminarPeliculaPanel.add(idTextField, constraints);

    constraints.gridx = 1;
    constraints.gridy = 1;
    constraints.anchor = GridBagConstraints.CENTER;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(0, 0, 0, 0);
    eliminarPeliculaPanel.add(eliminarButton, constraints);

    eliminarPeliculaFrame.add(eliminarPeliculaPanel);
    eliminarPeliculaFrame.setVisible(true);

    eliminarButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            // Obtener el ID ingresado por el usuario
            int idPelicula = Integer.parseInt(idTextField.getText());

            // Eliminar la película utilizando el PeliculasManager
            peliculasManager.eliminarPelicula(idPelicula);

            // Cerrar la ventana de eliminar película después de eliminarla
            eliminarPeliculaFrame.dispose();
        }
    });
}

    private void salir() {
        JOptionPane.showMessageDialog(frame, "¡Gracias por usar el sistema. Hasta luego!");
        System.exit(0);
    }
}
