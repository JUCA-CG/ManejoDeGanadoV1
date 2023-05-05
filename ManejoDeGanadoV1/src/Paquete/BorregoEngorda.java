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
import java.text.DecimalFormat;


//funcion para realizar conexión


public class BorregoEngorda  extends JFrame implements ActionListener {
librer cc= new librer(); 
Connection cn = cc.conexion();

    // Componentes de la ventana
    private JPanel panel;
    private JLabel labelArete, labelPeso, labelEdad, labelObservaciones, labelPesoTotal, labelcantidad,labelAlimento,labelTitulo,labelAretePadre,labelAreteMadre;
    private JTextField textFieldArete, textFieldPeso, textFieldEdad,textFieldAretePadre,textFieldAreteMadre;
    private JTextArea textAreaObservaciones;
    private JButton botonAgregar, botonBuscar, botonEliminar, botonEditar,botonVerTodo;
    private JMenuBar menuBar;
    private JTable tabla;
   private DefaultTableModel modelo;
    private JMenu menuOpciones;
    private JMenuItem menuItemInicio, menuItemBorregaCria, menuItemBorregoSemental, menuItemBorregoEngorda;
    public BorregoEngorda () {
        // Configuración de la ventana
        setTitle("Registro de Borrego de engorda ");
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
        labelTitulo = new JLabel("Borrego de engorda");
        labelTitulo.setBounds(250, 30, 200, 25);
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

        // Etiqueta de AreteMadre
        labelAreteMadre = new JLabel("Arete madre:");
        labelAreteMadre.setBounds(50, 120, 150, 25);
        labelAreteMadre.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(labelAreteMadre);

        // Campo de texto de AreteMadre
        textFieldAreteMadre = new JTextField();
        textFieldAreteMadre.setBounds(200, 120, 100, 25);
        panel.add(textFieldAreteMadre);

        // Etiqueta de AretePadre
        labelAretePadre = new JLabel("Arete padre:");
        labelAretePadre.setBounds(50, 160, 150, 25);
        labelAretePadre.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(labelAretePadre);

        // Campo de texto de AretePadre
        textFieldAretePadre = new JTextField();
        textFieldAretePadre.setBounds(200, 160, 100, 25);
        panel.add(textFieldAretePadre);

        // Etiqueta de peso
        labelPeso = new JLabel("Peso (kg):");
        labelPeso.setBounds(50, 200, 150, 25);
        labelPeso.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(labelPeso);

        // Campo de texto de peso
        textFieldPeso = new JTextField();
        textFieldPeso.setBounds(200, 200, 100, 25);
panel.add(textFieldPeso);
 // Etiqueta de De cantidad de borregos engorda totales
    int totalBorregas = obtenerTotalBorregosE();
    labelcantidad = new JLabel("Borregos de engorda totales: " + totalBorregas);
    labelcantidad.setBounds(450, 380, 280, 25);
    labelcantidad.setFont(new Font("Arial", Font.BOLD, 14));
    panel.add(labelcantidad);
    
    // Etiqueta de De cantidad de kilos totales
    int kilosTotales = sumarKgBorregosDeEngorda();
    labelPesoTotal = new JLabel("Los cuales pesan un total de "+kilosTotales+ " kg");
    labelPesoTotal.setBounds(450, 400, 450, 25);
    labelPesoTotal.setFont(new Font("Arial", Font.BOLD, 14));
    panel.add(labelPesoTotal);
    
    // Etiqueta de De cantidad de alimento requerido
    Double AlimentoRequerido = (kilosTotales*.04);
    DecimalFormat formatoDecimal = new DecimalFormat("#.##");
    String AlimentoR = formatoDecimal.format(AlimentoRequerido);
    labelAlimento = new JLabel("Por lo cual requieren de : " + AlimentoR+ " kg de alimento diario");
    labelAlimento.setBounds(450, 420, 450, 25);
    labelAlimento.setFont(new Font("Arial", Font.BOLD, 14));
    panel.add(labelAlimento);


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
    
 // Botón para ver todos los registros
    botonVerTodo = new JButton("Ver todo");
    botonVerTodo.setBounds(530, 500, 125, 30);
    botonVerTodo.addActionListener(this);
    panel.add(botonVerTodo);

/// Tabla
    tabla = new JTable();
    modelo = new DefaultTableModel();
    tabla.setModel(modelo);
    modelo.addColumn("Arete");
    modelo.addColumn("Arete Madre");
    modelo.addColumn("Arete Padre");
    modelo.addColumn("Edad");
    modelo.addColumn("Peso");
    modelo.addColumn("Observaciones");
    tabla.getColumnModel().getColumn(5).setPreferredWidth(150);
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

    // Opción de BorregoEngorda  (seleccionada)
    menuItemBorregoEngorda = new JMenuItem("Borrego de Engorda");
    menuItemBorregoEngorda.setEnabled(false);
    menuOpciones.add(menuItemBorregoEngorda);

    // Opción de borrego semental
    menuItemBorregoSemental = new JMenuItem("Borrego Semental");
    menuItemBorregoSemental.addActionListener(this);
    menuOpciones.add(menuItemBorregoSemental);

    // Opción de borrega de cría
    menuItemBorregaCria = new JMenuItem("Borrega de cría");
   menuItemBorregaCria.addActionListener(this);
    menuOpciones.add(menuItemBorregaCria);

    menuBar.add(menuOpciones);
    panel.add(menuBar);

    // Agregar panel a la ventana
    add(panel);

    // Hacer visible la ventana
    setVisible(true);
    
}
    
    
      /**
     * Método para mostrar consulta al usuario
     */                            
void mostrarDatos(){
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.addColumn("Número_arete");
    modelo.addColumn("arete_madre");
    modelo.addColumn("arete_padre");
    modelo.addColumn("Peso");
    modelo.addColumn("Edad");
    modelo.addColumn("Observaciones");
    tabla.setModel(modelo);
    String valor = textFieldArete.getText();
    String sql = "SELECT * FROM borregosEngorda WHERE numero_arete='" + valor + "'";
    
   
    String datos[] = new String[6];
    Statement st;
    try{
        st = (Statement) cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            datos[0] = rs.getString("numero_arete");
            datos[1] = rs.getString("arete_madre");
            datos[2] = rs.getString("arete_padre");
            datos[3] = rs.getString("peso");
            datos[4] = rs.getString("edad");
            datos[5] = rs.getString("observaciones");
            modelo.addRow(datos);
        }
        // Verificar si la consulta SQL devolvió algún resultado
        
        tabla.setModel(modelo);
    } catch (SQLException ex) {
        Logger.getLogger(BorregoEngorda.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "Intente de nuevo, fallo en la búsqueda del borrego");
    }
}
//Mostrar todos los registros
private void mostrarTodo() {
    try {
        String[] columnas = {"Arete", "Peso", "Edad", "Arete madre", "Arete Padre", "Observaciones"};
        String[] registro = new String[6];

        modelo = new DefaultTableModel(null, columnas);

        String sql = "SELECT * FROM borregosEngorda";
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            registro[0] = rs.getString("numero_arete");
            registro[1] = rs.getString("peso");
            registro[2] = rs.getString("edad");
            registro[3] = rs.getString("arete_madre");
            registro[4] = rs.getString("arete_padre");
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
  // Código para agregar una borrego
        
 if(textFieldArete.getText().length()>0 && textFieldAreteMadre.getText().length()>0&& textFieldAretePadre.getText().length()>0 && textFieldPeso.getText().length()>0&& textFieldEdad.getText().length()>0){
PreparedStatement pps;
try {
pps = cn.prepareStatement("INSERT INTO borregosEngorda(numero_arete,arete_madre,arete_padre,peso,edad,observaciones)VALUES (?,?,?,?,?,?)");
pps.setString(1, textFieldArete.getText());
pps.setString(2, textFieldAreteMadre.getText());
pps.setString(3, textFieldAretePadre.getText());
pps.setString(4, textFieldPeso.getText());
pps.setString(5, textFieldEdad.getText());
pps.setString(6, textAreaObservaciones.getText());
pps.executeUpdate();
JOptionPane.showMessageDialog(null, "Datos guardados con éxito");
 int totalBorregos = obtenerTotalBorregosE();
 labelcantidad.setText("Borregos de engorda totales: " + totalBorregos);
 int kilosTotales = sumarKgBorregosDeEngorda();
 labelPesoTotal.setText("Los cuales pesan un total de "+kilosTotales+ " kg");
  Double AlimentoRequerido = (kilosTotales*.04);
  DecimalFormat formatoDecimal = new DecimalFormat("#.##");
  String AlimentoR = formatoDecimal.format(AlimentoRequerido);
 labelAlimento.setText("Por lo cual requieren de : " + AlimentoR+ " kg de alimento diario");
 textFieldArete.setText("");
textFieldAreteMadre.setText("");
textFieldAretePadre.setText("");
textFieldPeso.setText("");
textFieldEdad.setText("");
textAreaObservaciones.setText("");
}  catch (SQLException ex) {
Logger.getLogger(BorregoEngorda.class.getName()).log(Level.SEVERE, null, ex);
JOptionPane.showMessageDialog(null, "Intente de nuevo, fallo en el registro nuevo");
} }
else{
JOptionPane.showMessageDialog(null, "Favor de llenar todos los campos");
}
                                      

    } else if (e.getSource() == botonBuscar) {
        // Código para buscar un borrego
        if(textFieldArete.getText().length()>0){
        mostrarDatos ();
        textFieldArete.setText("");
        textFieldAreteMadre.setText("");
        textFieldAretePadre.setText("");
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
PreparedStatement pps = cn.prepareStatement("DELETE FROM borregosEngorda WHERE numero_arete='"+valor+ "'");
pps.executeUpdate();
JOptionPane.showMessageDialog(null, "Datos eliminados con éxito");
 int totalBorregos = obtenerTotalBorregosE();
 labelcantidad.setText("Borregos de engorda totales: " + totalBorregos);
 int kilosTotales = sumarKgBorregosDeEngorda();
 labelPesoTotal.setText("Los cuales pesan un total de "+kilosTotales+ " kg");
  Double AlimentoRequerido = (kilosTotales*.04);
  DecimalFormat formatoDecimal = new DecimalFormat("#.##");
  String AlimentoR = formatoDecimal.format(AlimentoRequerido);
 labelAlimento.setText("Por lo cual requieren de : " + AlimentoR+ " kg de alimento diario");
textFieldArete.setText("");
textFieldAreteMadre.setText("");
textFieldAretePadre.setText("");
textFieldPeso.setText("");
textFieldEdad.setText("");
textAreaObservaciones.setText("");
} catch (SQLException ex) {
Logger.getLogger(BorregoEngorda.class.getName()).log(Level.SEVERE, null, ex);
JOptionPane.showMessageDialog(null, "Intente de nuevo, fallo en la eliminación de datos");
}}
else{
JOptionPane.showMessageDialog(null, "Favor de registrar el número de arete");
}
                               
//Boton editar
} else if (e.getSource() == botonEditar) {
        if(textFieldArete.getText().length()>0 && textFieldAreteMadre.getText().length()>0&& textFieldAretePadre.getText().length()>0 && textFieldPeso.getText().length()>0&& textFieldEdad.getText().length()>0){
String valor= textFieldArete.getText();

String am =textFieldAreteMadre.getText();
String ap =textFieldAretePadre.getText();
String p =textFieldPeso.getText();
String ed =textFieldEdad.getText();
String o =textAreaObservaciones.getText();
try {
PreparedStatement pps = cn.prepareStatement("UPDATE borregosEngorda SET numero_arete = '"+valor+ "' ,arete_madre = '"+am+ "' ,arete_padre = '"+ap+ "' ,peso = '"+p+ "' ,edad = '"+ed+ "' ,observaciones = '"+o+ "'WHERE numero_arete='"+valor+ "'");
pps.executeUpdate();
JOptionPane.showMessageDialog(null, "Datos actualizados con éxito");
 int kilosTotales = sumarKgBorregosDeEngorda();
 labelPesoTotal.setText("Los cuales pesan un total de "+kilosTotales+ " kg");
  Double AlimentoRequerido = (kilosTotales*.04);
  DecimalFormat formatoDecimal = new DecimalFormat("#.##");
  String AlimentoR = formatoDecimal.format(AlimentoRequerido);
 labelAlimento.setText("Por lo cual requieren de : " + AlimentoR+ " kg de alimento diario");
textFieldArete.setText("");
textFieldAreteMadre.setText("");
textFieldAretePadre.setText("");
textFieldPeso.setText("");
textFieldEdad.setText("");
textAreaObservaciones.setText("");
} catch (SQLException ex) {
Logger.getLogger(BorregoEngorda.class.getName()).log(Level.SEVERE, null, ex);
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
    
} else if (e.getSource() == menuItemBorregaCria) {
    // Código para cambiar a la vista de borrega Cria
      BorregaCria borregaCria = new BorregaCria();
        borregaCria.setVisible(true);
        dispose(); // Cierra la ventana actual de Login
} else if (e.getSource() == menuItemBorregoSemental) {
    // Código para cambiar a la vista de borrego Semental
    BorregoSemental borregoSemental = new BorregoSemental();
        borregoSemental.setVisible(true);
        dispose(); // Cierra la ventana actual de Login
}
    
}
//Metodo para sumar total
public int obtenerTotalBorregosE() {
    String consulta = "SELECT COUNT(*) FROM borregosEngorda";
    Statement st = null;
    ResultSet rs = null;
    int totalBorregos = 0;
    
    try {
        st = cn.createStatement();
        rs = st.executeQuery(consulta);
        rs.next();
        totalBorregos = rs.getInt(1);
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
    
    return totalBorregos;
}
//Metodo para sumar kg
 public int sumarKgBorregosDeEngorda() {
    String consulta = "SELECT SUM(peso) FROM borregosEngorda";
    Statement st = null;
    ResultSet rs = null;
    int totalKg = 0;
    
    try {
        st = cn.createStatement();
        rs = st.executeQuery(consulta);
        rs.next();
        totalKg = rs.getInt(1);
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
    
    return totalKg;
}

public static void main(String[] args) {
    new BorregoEngorda();
}
}