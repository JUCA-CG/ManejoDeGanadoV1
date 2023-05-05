package Paquete;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearU extends JFrame {

    private JPanel panel;
    private JLabel labelTitulo, labelUsuario, labelContrasena, labelValidarContrasena, labelCodigoValidacion;
    private JTextField textFieldUsuario, textFieldCodigoValidacion;
    private JPasswordField passwordFieldContrasena, passwordFieldValidarContrasena;
    private JButton botonAceptar,botonRegresar;


    public CrearU() {
        initComponents();
    }

    private void initComponents() {
        // Configuración de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sistema de Administración Ganadera - Registro de Usuario");
        setSize(600, 400);
        setResizable(false);
        setLocationRelativeTo(null);

        // Configuración del panel principal
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(226, 214, 185));
        this.getContentPane().add(panel);

        // Etiqueta de título
        labelTitulo = new JLabel("Registro de usuario");
        labelTitulo.setBounds(200, 30, 350, 25);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        labelTitulo.setForeground(new Color(36, 47, 65));
        panel.add(labelTitulo);

        // Etiqueta de usuario
        labelUsuario = new JLabel("Nombre de Usuario:");
        labelUsuario.setBounds(50, 80, 150, 25);
        labelUsuario.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(labelUsuario);

        // Campo de texto de usuario
        textFieldUsuario = new JTextField();
        textFieldUsuario.setBounds(250, 80, 200, 25);
        panel.add(textFieldUsuario);

        // Etiqueta de contraseña
        labelContrasena = new JLabel("Contraseña:");
        labelContrasena.setBounds(50, 120, 200, 25);
        labelContrasena.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(labelContrasena);

        // Campo de texto de contraseña
        passwordFieldContrasena = new JPasswordField();
        passwordFieldContrasena.setBounds(250, 120, 200, 25);
        panel.add(passwordFieldContrasena);

        // Etiqueta de validar contraseña
        labelValidarContrasena = new JLabel("Validar Contraseña:");
        labelValidarContrasena.setBounds(50, 160, 200, 25);
        labelValidarContrasena.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(labelValidarContrasena);

        // Campo de texto de validar contraseña
        passwordFieldValidarContrasena = new JPasswordField();
        passwordFieldValidarContrasena.setBounds(250, 160, 200, 25);
        panel.add(passwordFieldValidarContrasena);

        // Etiqueta de código de validación
        labelCodigoValidacion = new JLabel("Código de Validación:");
        labelCodigoValidacion.setBounds(50, 200, 200, 25);
        labelCodigoValidacion.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(labelCodigoValidacion);

        // Campo de texto de código de validación
        textFieldCodigoValidacion = new JPasswordField();
        textFieldCodigoValidacion.setBounds(250, 200, 200,25);
panel.add(textFieldCodigoValidacion);
 // Botón de aceptar
    botonAceptar = new JButton("Aceptar");
    botonAceptar.setBounds(250, 270, 100, 25);
    panel.add(botonAceptar);
    
// Botón de regresar
    botonRegresar = new JButton("Regresar");
    botonRegresar.setBounds(250, 310, 100, 25);
    panel.add(botonRegresar);
 
    
    //btn registrar
botonRegresar.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      Login login = new Login();
        login.setVisible(true);
        dispose(); // Si deseas cerrar la ventana actual (Login) al abrir la siguiente (CrearU)
    }
});


botonAceptar.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        String usuario = textFieldUsuario.getText();
        String contrasena = new String(passwordFieldContrasena.getPassword());
        String validarContrasena = new String(passwordFieldValidarContrasena.getPassword());
        String codigoValidacion = textFieldCodigoValidacion.getText();

        // Verificar que las contraseñas coincidan
        if (!contrasena.equals(validarContrasena)) {
            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar el código de validación
        if (!codigoValidacion.equals("210900")) {
            JOptionPane.showMessageDialog(null, "Código de validación incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Insertar el usuario en la base de datos
        ConsultasMySQL conexion = new ConsultasMySQL();
        if (conexion.insertarUsuario(usuario, contrasena)) {
            JOptionPane.showMessageDialog(null, "Usuario creado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            Login login = new Login();
            login.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Error al crear usuario", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
});


}
    
    

public static void main(String[] args) {
    CrearU crearU = new CrearU();
    crearU.setVisible(true);
}
}