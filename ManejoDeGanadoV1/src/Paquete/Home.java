package Paquete;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements ActionListener {

    private JPanel panel;
    private JLabel labelBienvenida, labelImagen,labelSeleccion;
    private JButton botonBorregas, botonSementales, botonEngorda;
    private JMenuBar menuBar;
    private JMenu menuOpciones;
    private JMenuItem menuItemBorregas, menuItemSementales, menuItemEngorda, menuItemConfiguracion;
    public Home() {
        initComponents();
        
    }

    private void initComponents() {

        // Configuración de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sistema de Administración Ganadera");
        setSize(800, 600); // Aumentamos la altura de la ventana
        setResizable(false);
        setLocationRelativeTo(null);

        // Configuración del panel principal
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(226, 214, 185));
        this.getContentPane().add(panel);

        // Etiqueta de bienvenida
       labelBienvenida = new JLabel("Sistema de administración ganadera");
        labelBienvenida.setBounds(100, 55, 700, 50);
        labelBienvenida.setFont(new Font("Arial", Font.BOLD, 26));
        labelBienvenida.setForeground(new Color(36, 47, 65));
        labelBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
        labelBienvenida.setForeground(Color.BLACK);
        panel.add(labelBienvenida);
// Etiqueta de seleccioon de opcion
       labelSeleccion = new JLabel("Selecciona una opción");
        labelSeleccion.setBounds(50, 110, 700, 50);
        labelSeleccion.setFont(new Font("Arial", Font.BOLD, 22));
        labelSeleccion.setForeground(new Color(36, 47, 65));
        labelSeleccion.setHorizontalAlignment(SwingConstants.CENTER);
        labelSeleccion.setForeground(Color.BLACK);
        panel.add(labelSeleccion);
        // Imagen principal
        ImageIcon icono = new ImageIcon(getClass().getResource("/Imagenes/logo.png"));
        Image img = icono.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        labelImagen = new JLabel(new ImageIcon(img));
        labelImagen.setBounds(-5, 30, 180, 110);
        panel.add(labelImagen);
        
       

        // Botones principales
     //botonBorrega
      try {
    ImageIcon imagen = new ImageIcon(getClass().getResource("/Imagenes/LogoBorrego1.png"));

    botonBorregas = new JButton("", imagen);
    botonBorregas.setVerticalTextPosition(SwingConstants.BOTTOM);
    botonBorregas.setHorizontalTextPosition(SwingConstants.CENTER);
    botonBorregas.setText("Borregas de Cría");
    botonBorregas.setBounds(40, 250, 250, 200);
    botonBorregas.setFont(new Font("Arial", Font.BOLD, 17 ));
    botonBorregas.setBackground(new Color(226, 214, 185));
    botonBorregas.setForeground(Color.BLACK);
    botonBorregas.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    panel.add(botonBorregas);
} catch (Exception e) {
    System.out.println("Error al cargar la imagen: " + e.getMessage());
}
      //botonSemental
       try {
    ImageIcon imagen = new ImageIcon(getClass().getResource("/Imagenes/LogoBorrego2.png"));
    botonSementales = new JButton("", imagen);
    botonSementales.setVerticalTextPosition(SwingConstants.BOTTOM);
    botonSementales.setHorizontalTextPosition(SwingConstants.CENTER);
    botonSementales.setText("Borregos Sementales");
    botonSementales.setBounds(265, 250, 250, 200);
    botonSementales.setFont(new Font("Arial", Font.BOLD, 17));
    botonSementales.setBackground(new Color(226, 214, 185));
    botonSementales.setForeground(Color.BLACK);
    botonSementales.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    panel.add(botonSementales);
} catch (Exception e) {
    System.out.println("Error al cargar la imagen: " + e.getMessage());
}
//botonBorregoEngorda
     try {
    ImageIcon imagen = new ImageIcon(getClass().getResource("/Imagenes/LogoBorrego3.png"));
    botonEngorda = new JButton("", imagen);
    botonEngorda.setVerticalTextPosition(SwingConstants.BOTTOM);
    botonEngorda.setHorizontalTextPosition(SwingConstants.CENTER);
    botonEngorda.setText("Borregos de Engorda");
    botonEngorda.setBounds(500, 250, 250, 200);
    botonEngorda.setFont(new Font("Arial", Font.BOLD, 17));
    botonEngorda.setBackground(new Color(226, 214, 185));
    botonEngorda.setForeground(Color.BLACK);
    botonEngorda.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    panel.add(botonEngorda);
} catch (Exception e) {
    System.out.println("Error al cargar la imagen: " + e.getMessage());
}


    // Barra de menú
    menuBar = new JMenuBar();
    menuBar.setBounds(0, 0, 800, 25);
    panel.add(menuBar);

    // Menú Opciones
    menuOpciones = new JMenu("Opciones");
    menuOpciones.setFont(new Font("Arial", Font.PLAIN, 14));
    menuOpciones.setForeground(new Color(36, 47, 65));
    menuBar.add(menuOpciones);

    // Submenú Borregas de Cría
    menuItemBorregas = new JMenuItem("Borregas de Cría");
    menuItemBorregas.setFont(new Font("Arial", Font.PLAIN, 14));
    menuItemBorregas.addActionListener(e -> {
    });
    menuOpciones.add(menuItemBorregas);

    // Submenú Borregos Sementales
    menuItemSementales = new JMenuItem("Borregos Sementales");
    menuItemSementales.setFont(new Font("Arial", Font.PLAIN, 14));
    menuItemSementales.addActionListener(e -> {
    });
    
    
    menuOpciones.add(menuItemSementales);

    // Submenú Borregos de Engorda
    menuItemEngorda = new JMenuItem("Borregos de Engorda");
    menuItemEngorda.setFont(new Font("Arial", Font.PLAIN, 14));
    menuItemEngorda.addActionListener(e -> {
    });
    menuOpciones.add(menuItemEngorda);

    // Submenú   Cerrar Sesión
    menuItemConfiguracion = new JMenuItem("Cerrar Sesión");
    menuItemConfiguracion.setFont(new Font("Arial", Font.PLAIN, 14));
    menuItemConfiguracion.addActionListener(e -> {
    });
    menuOpciones.add(menuItemConfiguracion);

    botonBorregas.addActionListener(this);
    botonSementales.addActionListener(this);
    botonEngorda.addActionListener(this);
    menuItemBorregas.addActionListener(this);
    menuItemSementales.addActionListener(this);
    menuItemEngorda.addActionListener(this);
    menuItemConfiguracion.addActionListener(this);
    setVisible(true);
    
    }
    // Método para manejar los eventos de los botones y menús
    public void actionPerformed(ActionEvent e) {
    if (e.getSource() == botonBorregas) {
        //btnBorregaC
        BorregaCria borregaCria = new BorregaCria();
        borregaCria.setVisible(true);
        dispose(); // Si deseas cerrar la ventana actual (Login) al abrir la siguiente (CrearU)
    } else if (e.getSource() == botonSementales) {
       //btnBorregoS
        BorregoSemental borregoSemental = new BorregoSemental();
        borregoSemental.setVisible(true);
        dispose(); // Si deseas cerrar la ventana actual (Login) al abrir la siguiente (CrearU)
    } else if (e.getSource() == botonEngorda) {
        // btnBorregoE
        BorregoEngorda borregoengorda = new BorregoEngorda();
        borregoengorda.setVisible(true);
        dispose(); // Si deseas cerrar la ventana actual (Login) al abrir la siguiente (CrearU)

} else if (e.getSource() == menuItemConfiguracion) {
    // Código para volver al inicio
     Login login = new Login();
        login.setVisible(true);
        dispose(); // Cierra la ventana actual de Login
    
} else if (e.getSource() == menuItemBorregas) {
    // Código para cambiar a la vista de borrega Cria
      BorregaCria borregaCria = new BorregaCria();
        borregaCria.setVisible(true);
        dispose(); // Cierra la ventana actual de Login
} else if (e.getSource() == menuItemEngorda) {
    // Código para cambiar a la vista de borrego de engorda
    BorregoEngorda borregoEngorda = new BorregoEngorda();
        borregoEngorda.setVisible(true);
        dispose(); // Cierra la ventana actual de Login
}else if (e.getSource() == menuItemSementales) {
    // Código para cambiar a la vista de borrego de engorda
    BorregoSemental borregoSementales = new BorregoSemental();
        borregoSementales.setVisible(true);
        dispose(); // Cierra la ventana actual de Login
}
    
}
    
 

public static void main(String[] args) {
    new Home();
}}
