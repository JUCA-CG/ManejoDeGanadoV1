package Paquete;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;

//funcion para realizar conexión


public class BorregaCria extends JFrame implements ActionListener {
librer cc= new librer(); 
Connection cn = cc.conexion();

    // Componentes de la ventana
    private JPanel panel;
    private JLabel labelArete, labelPeso, labelEdad, labelRaza, labelColor, labelObservaciones, labelTitulo,labelcantidad;
    private JTextField textFieldArete, textFieldPeso, textFieldEdad, textFieldRaza, textFieldColor;
    private JTextArea textAreaObservaciones;
    private JButton botonAgregar, botonBuscar, botonEliminar, botonEditar,botonVerRegistros,botonVerTodo;
    private JMenuBar menuBar;
    private JTable tabla;
    private DefaultTableModel modelo;
    private JMenu menuOpciones;
    private JMenuItem menuItemInicio, menuItemBorregaCria, menuItemBorregoSemental, menuItemBorregoEngorda;
    public BorregaCria() {
        // Configuración de la ventana
        setTitle("Registro de Borrega de cría");
        setSize(1000, 650);
        setLocationRelativeTo(null);
         setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       // Configuración del panel principal
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(226, 214, 185));
        this.getContentPane().add(panel);

        //Etiqueta de título
        labelTitulo = new JLabel("Borrega de Cria");
        labelTitulo.setBounds(300, 30, 150, 25);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        labelTitulo.setForeground(new Color(36, 47, 65));
        panel.add(labelTitulo);

        // Etiqueta de arete
        labelArete = new JLabel("Número de Arete:");
        labelArete.setBounds(50, 80, 150, 25);
        labelArete.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(labelArete);

        // Campo de texto de arete
        textFieldArete = new JTextField();
        textFieldArete.setBounds(200, 80, 100, 25);
        panel.add(textFieldArete);

        // Etiqueta de color
        labelColor = new JLabel("Color:");
        labelColor.setBounds(50, 120, 150, 25);
        labelColor.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(labelColor);

        // Campo de texto de color
        textFieldColor = new JTextField();
        textFieldColor.setBounds(200, 120, 100, 25);
        panel.add(textFieldColor);

        // Etiqueta de raza
        labelRaza = new JLabel("Raza:");
        labelRaza.setBounds(50, 160, 150, 25);
        labelRaza.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(labelRaza);

        // Campo de texto de raza
        textFieldRaza = new JTextField();
        textFieldRaza.setBounds(200, 160, 100, 25);
        panel.add(textFieldRaza);

        // Etiqueta de peso
        labelPeso = new JLabel("Peso (kg):");
        labelPeso.setBounds(50, 200, 150, 25);
        labelPeso.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(labelPeso);

        // Campo de texto de peso
        textFieldPeso = new JTextField();
        textFieldPeso.setBounds(200, 200, 100, 25);
panel.add(textFieldPeso);


    // Etiqueta de edad
    labelEdad = new JLabel("Edad (meses):");
    labelEdad.setBounds(50, 240, 150, 25);
    labelEdad.setFont(new Font("Arial", Font.BOLD, 14));
    panel.add(labelEdad);

    // Campo de texto de edad
    textFieldEdad = new JTextField();
    textFieldEdad.setBounds(200, 240, 100, 25);
    panel.add(textFieldEdad);

    // Etiqueta de observaciones
    labelObservaciones = new JLabel("Observaciones:");
    labelObservaciones.setBounds(50, 280, 150, 25);
    labelObservaciones.setFont(new Font("Arial", Font.BOLD, 14));
    panel.add(labelObservaciones);

    // Campo de texto de observaciones
    textAreaObservaciones = new JTextArea();
    textAreaObservaciones.setBounds(180, 280, 250, 100);
    textAreaObservaciones.setLineWrap(true);
    panel.add(textAreaObservaciones);

     // Etiqueta de De cantidad de borregas totales
    int totalBorregas = obtenerTotalBorregas();
    labelcantidad = new JLabel("Borregas totales: " + totalBorregas);
    labelcantidad.setBounds(700, 450, 280, 25);
    labelcantidad.setFont(new Font("Arial", Font.BOLD, 14));
    panel.add(labelcantidad);
    
    
    // Botón de agregar
    botonAgregar = new JButton("Agregar");
    botonAgregar.setBounds(50, 500, 125, 30);
    botonAgregar.addActionListener(this);
    panel.add(botonAgregar);

    // Botón de buscar
    botonBuscar = new JButton("Buscar");
    botonBuscar.setBounds(170, 500, 125, 30);
    botonBuscar.addActionListener(this);
    panel.add(botonBuscar);

    // Botón de eliminar
    botonEliminar = new JButton("Eliminar");
    botonEliminar.setBounds(290, 500, 125, 30);
    botonEliminar.addActionListener(this);
    panel.add(botonEliminar);

    // Botón de editar
    botonEditar = new JButton("Editar");
    botonEditar.setBounds(410, 500, 125, 30);
    botonEditar.addActionListener(this);
    panel.add(botonEditar);
    
    // Botón para ver registros médicos
        botonVerRegistros = new JButton("Manejo médico");
        botonVerRegistros.setBounds(530, 500, 125, 30);
        botonVerRegistros.addActionListener(this);
        panel.add(botonVerRegistros);

// Botón para ver todos los registros
botonVerTodo = new JButton("Ver todo");
botonVerTodo.setBounds(650, 500, 125, 30);
botonVerTodo.addActionListener(this);
panel.add(botonVerTodo);

/// Tabla
    tabla = new JTable();
    modelo = new DefaultTableModel();
    tabla.setModel(modelo);
    modelo.addColumn("Arete");
    modelo.addColumn("Color");
    modelo.addColumn("Raza");
    modelo.addColumn("Edad");
    modelo.addColumn("Peso");
    modelo.addColumn("Observaciones");
    tabla.getColumnModel().getColumn(5).setPreferredWidth(150);
    /*Con esta linea se puede boquear la tabla, pero actualmente el usuario la requiere habilitada
    tabla.setEnabled(false);
    */
    JScrollPane scrollTabla = new JScrollPane(tabla);
    scrollTabla.setBounds(450, 80, 510, 300);
    
    panel.add(scrollTabla);


   // Barra de menú
    menuBar = new JMenuBar();
    menuBar.setBounds(0, 0, 1000, 25);

    // Menú de opciones
    menuOpciones = new JMenu("Opciones");

    // Opción de inicio
    menuItemInicio = new JMenuItem("Inicio");
    menuItemInicio.addActionListener(this);
    menuOpciones.add(menuItemInicio);

    // Opción de borrega de cria (seleccionada)
    menuItemBorregaCria = new JMenuItem("Borrega de Cria");
    menuItemBorregaCria.setEnabled(false);
    menuOpciones.add(menuItemBorregaCria);

    // Opción de borrego semental
    menuItemBorregoSemental = new JMenuItem("Borrego Semental");
    menuItemBorregoSemental.addActionListener(this);
    menuOpciones.add(menuItemBorregoSemental);

    // Opción de borrego de engorda
    menuItemBorregoEngorda = new JMenuItem("Borrego de Engorda");
    menuItemBorregoEngorda.addActionListener(this);
    menuOpciones.add(menuItemBorregoEngorda);

    menuBar.add(menuOpciones);
    panel.add(menuBar);

    // Agregar panel a la ventana
    add(panel);

    // Hacer visible la ventana
    setVisible(true);
    
    
     //btnRegistros
   botonVerRegistros.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
     
    }
});
   
   
}
    
    
      /**
     * Método para mostrar consulta al usuario
     */                            
void mostrarDatos(){
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.addColumn("Número_arete");
    modelo.addColumn("Color");
    modelo.addColumn("Raza");
    modelo.addColumn("Peso");
    modelo.addColumn("Edad");
    modelo.addColumn("Observaciones");
    tabla.setModel(modelo);
    String valor = textFieldArete.getText();
    String sql = "SELECT * FROM borregasCria WHERE numero_arete='" + valor + "'";
    
   
    String datos[] = new String[6];
    Statement st;
    try{
        st = (Statement) cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            datos[0] = rs.getString("numero_arete");
            datos[1] = rs.getString("color");
            datos[2] = rs.getString("raza");
            datos[3] = rs.getString("peso");
            datos[4] = rs.getString("edad");
            datos[5] = rs.getString("observaciones");
            modelo.addRow(datos);
        }
        // Verificar si la consulta SQL devolvió algún resultado
        
        tabla.setModel(modelo);
    } catch (SQLException ex) {
        Logger.getLogger(BorregaCria.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "Intente de nuevo, fallo en la búsqueda de la borrega");
    }
}
private void mostrarTodo() {
    try {
        String[] columnas = {"Arete", "Color", "Raza", "Peso", "Edad", "Observaciones"};
        String[] registro = new String[6];

        modelo = new DefaultTableModel(null, columnas);

        String sql = "SELECT * FROM borregascria";
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            registro[0] = rs.getString("numero_arete");
            registro[1] = rs.getString("color");
            registro[2] = rs.getString("raza");
            registro[3] = rs.getString("peso");
            registro[4] = rs.getString("edad");
            registro[5] = rs.getString("observaciones");
            modelo.addRow(registro);
        }

        tabla.setModel(modelo);

    } catch (SQLException ex) {
        Logger.getLogger(BorregaCria.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    // Método para manejar los eventos de los botones y menús
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == botonAgregar) {
        // Código para agregar una borrega
        
        if(textFieldArete.getText().length()>0 && textFieldColor.getText().length()>0&& textFieldRaza.getText().length()>0 && textFieldPeso.getText().length()>0&& textFieldEdad.getText().length()>0){
PreparedStatement pps;
try {
pps = cn.prepareStatement("INSERT INTO borregasCria(numero_arete,color,raza,peso,edad,observaciones)VALUES (?,?,?,?,?,?)");
pps.setString(1, textFieldArete.getText());
pps.setString(2, textFieldColor.getText());
pps.setString(3, textFieldRaza.getText());
pps.setString(4, textFieldPeso.getText());
pps.setString(5, textFieldEdad.getText());
pps.setString(6, textAreaObservaciones.getText());
pps.executeUpdate();
JOptionPane.showMessageDialog(null, "Datos guardados con éxito");
// actualiza la variable totalBorregas
  int totalBorregas = obtenerTotalBorregas();
  labelcantidad.setText("Borregas totales: " + totalBorregas);
textFieldArete.setText("");
textFieldColor.setText("");
textFieldRaza.setText("");
textFieldPeso.setText("");
textFieldEdad.setText("");
textAreaObservaciones.setText("");
}  catch (SQLException ex) {
Logger.getLogger(BorregaCria.class.getName()).log(Level.SEVERE, null, ex);
JOptionPane.showMessageDialog(null, "Intente de nuevo, fallo en el registro nuevo");
} }
else{
JOptionPane.showMessageDialog(null, "Favor de llenar todos los campos");
}
                                      

    } else if (e.getSource() == botonBuscar) {

 // Código para buscar una borrega
      
 if(textFieldArete.getText().length()>0){
        mostrarDatos ();
            textFieldArete.setText("");
           textFieldColor.setText("");
           textFieldRaza.setText("");
           textFieldPeso.setText("");
           textFieldEdad.setText("");
           textAreaObservaciones.setText("");}
        else {
            JOptionPane.showMessageDialog(null, "Favor de registrar un número de arete");
            
}

    } else if (e.getSource() == botonEliminar) {
        // Código para eliminar
        
 if(textFieldArete.getText().length()>0){
String valor= textFieldArete.getText();
try {
PreparedStatement pps = cn.prepareStatement("DELETE FROM borregasCria WHERE numero_arete='"+valor+ "'");
pps.executeUpdate();
JOptionPane.showMessageDialog(null, "Datos eliminados con éxito");
  int totalBorregas = obtenerTotalBorregas();
  labelcantidad.setText("Borregas totales: " + totalBorregas);
textFieldArete.setText("");
textFieldColor.setText("");
textFieldRaza.setText("");
textFieldPeso.setText("");
textFieldEdad.setText("");
textAreaObservaciones.setText("");
} catch (SQLException ex) {
Logger.getLogger(BorregaCria.class.getName()).log(Level.SEVERE, null, ex);
JOptionPane.showMessageDialog(null, "Intente de nuevo, fallo en la eliminación de datos");
}}
else{
JOptionPane.showMessageDialog(null, "Favor de registrar el número de arete");
}
                               
//Boton editar
} else if (e.getSource() == botonEditar) {
if(textFieldArete.getText().length()>0 && textFieldColor.getText().length()>0&& textFieldRaza.getText().length()>0 && textFieldPeso.getText().length()>0&& textFieldEdad.getText().length()>0){
String valor= textFieldArete.getText();

String c =textFieldColor.getText();
String r =textFieldRaza.getText();
String p =textFieldPeso.getText();
String ed =textFieldEdad.getText();
String o =textAreaObservaciones.getText();
try {
PreparedStatement pps = cn.prepareStatement("UPDATE borregasCria SET numero_arete = '"+valor+ "' ,color = '"+c+ "' ,raza = '"+r+ "' ,peso = '"+p+ "' ,edad = '"+ed+ "' ,observaciones = '"+o+ "'WHERE numero_arete='"+valor+ "'");
pps.executeUpdate();
JOptionPane.showMessageDialog(null, "Datos actualizados con éxito");
textFieldArete.setText("");
textFieldColor.setText("");
textFieldRaza.setText("");
textFieldPeso.setText("");
textFieldEdad.setText("");
textAreaObservaciones.setText("");
} catch (SQLException ex) {
Logger.getLogger(BorregaCria.class.getName()).log(Level.SEVERE, null, ex);
JOptionPane.showMessageDialog(null, "Intente de nuevo, fallo en la modificación de datos");
}}
else {
JOptionPane.showMessageDialog(null, "Favor de llenar todos los campos");
}
//Boton VerTodo
} else if (e.getSource() == botonVerTodo) {

   mostrarTodo();
            

} else if (e.getSource() == menuItemInicio) {
    // Código para volver al inicio
     Home home = new Home();
        home.setVisible(true);
        dispose(); // Cierra la ventana actual de Login
    
} else if (e.getSource() == menuItemBorregoSemental) {
   // Código para cambiar a la vista de borrego Semental
    BorregoSemental borregoSemental = new BorregoSemental();
        borregoSemental.setVisible(true);
        dispose(); // Cierra la ventana actual de Login
} else if (e.getSource() == menuItemBorregoEngorda) {
    // Código para cambiar a la vista de borrego de engorda
    BorregoEngorda borregoEngorda = new BorregoEngorda();
        borregoEngorda.setVisible(true);
        dispose(); // Cierra la ventana actual de Login
}else if (e.getSource() == botonVerRegistros) {
   RegistroMedicoBorregas registroMedicoBorregas = new RegistroMedicoBorregas();
        registroMedicoBorregas.setVisible(true);
        dispose(); // Si deseas cerrar la ventana actual (Login) al abrir la siguiente (CrearU) 
    
}}
public int obtenerTotalBorregas() {
    String consulta = "SELECT COUNT(*) FROM borregasCria";
    Statement st = null;
    ResultSet rs = null;
    int totalBorregas = 0;
    
    try {
        st = cn.createStatement();
        rs = st.executeQuery(consulta);
        rs.next();
        totalBorregas = rs.getInt(1);
    } catch (SQLException e) {
        // Manejar la excepción
        e.printStackTrace();
    } finally {
        // Cerrar los objetos ResultSet y Statement
        try {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    return totalBorregas;
}

public static void main(String[] args) {
    new BorregaCria();
}
}