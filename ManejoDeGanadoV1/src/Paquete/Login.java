package Paquete;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Image;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Login extends JFrame {

    private JPanel panel;
    private JLabel labelTitulo, labelImagen, labelUsuario, labelPassword;
    private JTextField fieldUsuario;
    private JPasswordField fieldPassword;
    private JButton botonAceptar, botonRegistrar;

    public Login() {
        initComponents();
    }
    
    private boolean validarCredenciales(String usuario, String contrasena) {
    String url = "jdbc:mysql://localhost:3306/AdminGanadera";
    String usuarioBD = "root";
    String contrasenaBD = "1234";

    try (Connection conexion = DriverManager.getConnection(url, usuarioBD, contrasenaBD);
         PreparedStatement consulta = conexion.prepareStatement(
                 "SELECT * FROM usuarios WHERE nombre_usuario = ? AND contrasena = ?");
    ) {
        consulta.setString(1, usuario);
        consulta.setString(2, contrasena);

        try (ResultSet resultado = consulta.executeQuery()) {
            return resultado.next(); // Devuelve true si encuentra una fila con el usuario y contraseña especificados
        }
    } catch (SQLException e) {
        System.out.println("Error al validar credenciales: " + e.getMessage());
        return false;
    }
}

    private void initComponents() {

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(226, 214, 185));
        this.getContentPane().add(panel);

        labelTitulo = new JLabel("Sistema de Administración Ganadera");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        labelTitulo.setBounds(80, 20, 400, 25);
        panel.add(labelTitulo);

        ImageIcon icono = new ImageIcon(getClass().getResource("/Imagenes/logo.png"));
        Image img = icono.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        labelImagen = new JLabel(new ImageIcon(img));
        labelImagen.setBounds(175, 60, 150, 150);
        panel.add(labelImagen);
        labelUsuario = new JLabel("Usuario:");
        labelUsuario.setFont(new Font("Arial", Font.BOLD, 16));
        labelUsuario.setBounds(75, 230, 150, 20);
        panel.add(labelUsuario);

        fieldUsuario = new JTextField();
        fieldUsuario.setBounds(200, 230, 200, 25);
        panel.add(fieldUsuario);

        labelPassword = new JLabel("Contraseña:");
        labelPassword.setFont(new Font("Arial", Font.BOLD, 16));
        labelPassword.setBounds(50, 280, 150, 20);
        panel.add(labelPassword);

        fieldPassword = new JPasswordField();
        fieldPassword.setBounds(200, 280, 200, 25);
        panel.add(fieldPassword);

        botonAceptar = new JButton("Iniciar Sesión");
        botonAceptar.setFont(new Font("Arial", Font.BOLD, 16));
        botonAceptar.setBounds(170, 340, 150, 30);
        botonAceptar.setBackground(new Color(0, 128, 0));
        botonAceptar.setForeground(Color.WHITE);
        panel.add(botonAceptar);

        botonRegistrar = new JButton("Registrarse");
        botonRegistrar.setFont(new Font("Arial", Font.BOLD, 16));
        botonRegistrar.setBounds(170, 390, 150, 30);
        botonRegistrar.setBackground(new Color(43, 58, 68));
        botonRegistrar.setForeground(Color.WHITE);
        panel.add(botonRegistrar);

        this.setTitle("Login");
        this.setSize(500, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
         
        //Btn aceptar
botonAceptar.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String usuario = fieldUsuario.getText();
        String contrasena = new String(fieldPassword.getPassword());

        if (validarCredenciales(usuario, contrasena)) {
            Home home = new Home();
            home.setVisible(true);
            dispose(); // Cierra la ventana actual de Login
        } else {
            // Muestra un mensaje de error si las credenciales son incorrectas
            JOptionPane.showMessageDialog(Login.this, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
        });

//btn registrar
botonRegistrar.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      CrearU crearU = new CrearU();
        crearU.setVisible(true);
        dispose(); // Si deseas cerrar la ventana actual (Login) al abrir la siguiente (CrearU)
    }
});


     }

    public static void main(String[] args) {
        Login login = new Login();
        login.setVisible(true);
    }

}
