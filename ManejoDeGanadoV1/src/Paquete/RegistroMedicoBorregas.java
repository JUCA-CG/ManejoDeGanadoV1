package Paquete;
import com.toedter.calendar.JCalendar;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

public class RegistroMedicoBorregas extends JFrame implements ActionListener {
librer cc= new librer(); 
Connection cn = cc.conexion();
    private JPanel panel;
    private JLabel labelFecha;
    private JLabel labelVacunas;
    private JLabel labelObservaciones,labelIdChequeo;
    private JTextArea textVacunas,textIdChequeo;
    private JTextArea textObservaciones,fechaTextArea;
    private JButton botonAgregar;
    private JButton botonBuscar,botonVerTodo;
    private JButton botonEliminar;
    private JButton botonEditar;
    private JButton botonCalendario;
    private JCalendar calendar;
    private JTable tabla;
    private DefaultTableModel modelo;
    private JMenuBar menuBar;
    private JMenu menuOpciones;
    private JMenuItem menuItemInicio, menuItemBorregaCria, menuItemBorregoSemental, menuItemBorregoEngorda;

    public RegistroMedicoBorregas() {
        setTitle("Registro Médico de Borregos");
        setSize(1200, 700);
        setLocationRelativeTo(null);
         setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//Panel principal
        panel = new JPanel();
        panel.setLayout(null);
        add(panel);
        panel.setBackground(new Color(226, 214, 185));
        // Crear la etiqueta
        JLabel etiquetaTitulo = new JLabel("Manejo médico borregas");
        etiquetaTitulo.setBounds(230, 30, 300, 30);
        etiquetaTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        etiquetaTitulo.setForeground(new Color(36, 47, 65));
 // Etiqueta de idChequeo
        labelIdChequeo = new JLabel("Id chequeo:");
        labelIdChequeo.setBounds(50, 125, 150, 25);
        labelIdChequeo.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(labelIdChequeo);

        // Campo de texto para idChequeo
        textIdChequeo = new JTextArea();
        textIdChequeo.setBounds(200, 125, 150, 25);
        panel.add(textIdChequeo);
        
        
        // Agregar la etiqueta de fecha
        panel.add(etiquetaTitulo);
        // Etiqueta de fecha
        labelFecha = new JLabel("Fecha de Chequeo:");
        labelFecha.setBounds(50, 175, 150, 25);
        labelFecha.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(labelFecha);

       /* Botón de calendario para fecha Ya se usa una mejor opción.
        botonCalendario = new JButton();
        botonCalendario.setBounds(370, 80, 25, 25);
        botonCalendario.addActionListener(this);
        panel.add(botonCalendario);*/

        /* Campo de texto para fecha segunda opcion
        dateChooser = new JDateChooser();
        dateChooser.setBounds(200, 175, 150, 20);
        panel.add(dateChooser);*/
        
        // Crear el FechaTextArea
        fechaTextArea = new JTextArea();
        fechaTextArea.setBounds(200, 175, 150, 20);
        panel.add(fechaTextArea);
        fechaTextArea.setEnabled(false);

// Crear el botón para abrir el calendario
JButton calendarButton = new JButton("Abrir Calendario");
calendarButton.setBounds(360, 175, 150, 20);
panel.add(calendarButton);

// Agregar un ActionListener al botón para abrir el calendario
calendarButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        // Crear un objeto JCalendar y mostrarlo en un diálogo
        JCalendar calendar = new JCalendar();
        JOptionPane.showMessageDialog(panel, calendar, "Seleccione una fecha", JOptionPane.PLAIN_MESSAGE);

        // Obtener la fecha seleccionada y mostrarla en el JTextArea
        Date selectedDate = calendar.getDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
        String formattedDate = dateFormat.format(selectedDate);
        fechaTextArea.setText(formattedDate);
        
    }
});

        // Etiqueta de vacunas
        labelVacunas = new JLabel("Vacunas Aplicadas:");
        labelVacunas.setBounds(50, 250, 150, 25);
        labelVacunas.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(labelVacunas);

        // Campo de texto para vacunas
        textVacunas = new JTextArea();
        textVacunas.setBounds(200, 235, 300, 75);
        panel.add(textVacunas);

        // Etiqueta de observaciones
        labelObservaciones = new JLabel("Observaciones:");
        labelObservaciones.setBounds(50, 375, 150, 25);
        labelObservaciones.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(labelObservaciones);

        // Campo de texto para observaciones
        textObservaciones = new JTextArea();
        textObservaciones.setBounds(200, 360, 300, 75);
        panel.add(textObservaciones);

        // Botón de agregar registro médico
        botonAgregar = new JButton("Agregar");
        botonAgregar.setBounds(50, 500, 125, 30);
        botonAgregar.addActionListener(this);
        panel.add(botonAgregar);

        // Botón de buscar registro médico
        botonBuscar = new JButton("Buscar");
        botonBuscar.setBounds(170, 500, 125, 30);
        botonBuscar.addActionListener(this);
        panel.add(botonBuscar);

        // Botón de eliminar registro médico
        botonEliminar = new JButton("Eliminar");
        botonEliminar.setBounds(290, 500, 125, 30);
        botonEliminar.addActionListener(this);
        panel.add(botonEliminar);

        // Botón de editar registro médico
        botonEditar = new JButton("Editar");
        botonEditar.setBounds(410, 500, 125, 30);
        botonEditar.addActionListener(this);
        panel.add(botonEditar);
        
        // Botón para ver todos los registros
        botonVerTodo = new JButton("Ver todo");
        botonVerTodo.setBounds(530, 500, 125, 30);
        botonVerTodo.addActionListener(this);
        panel.add(botonVerTodo);

        // Tabla
    tabla = new JTable();
    modelo = new DefaultTableModel();
    tabla.setModel(modelo);
    modelo.addColumn("Id");
    modelo.addColumn("Fecha");
    modelo.addColumn("Vacunas");
    modelo.addColumn("Observaciones");
    tabla.getColumnModel().getColumn(3).setPreferredWidth(150);
    JScrollPane scrollTabla = new JScrollPane(tabla);
    scrollTabla.setBounds(620, 80, 500, 300);
    panel.add(scrollTabla);
        
        // Barra de menú
    menuBar = new JMenuBar();
    menuBar.setBounds(0, 0, 1200, 25);

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
        setVisible(true);
    }
    
     /**
     * Método para mostrar consulta al usuario
     */                            
void mostrarDatos() throws ParseException{
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.addColumn("Id chequeo");
    modelo.addColumn("Fecha");
    modelo.addColumn("Vacunas");
    modelo.addColumn("Observaciones");

    tabla.setModel(modelo);
    String valor = textIdChequeo.getText();
    String sql = "SELECT * FROM ManejoMedicoBorregas WHERE id_chequeo='" + valor + "'";
    
   
    String datos[] = new String[6];
    Statement st;
    try{
        st = (Statement) cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        SimpleDateFormat formatoFechaActual = new SimpleDateFormat("yyyy-MM-dd");
SimpleDateFormat formatoFechaDeseada = new SimpleDateFormat("dd/MM/yyyy");
        while(rs.next()){
            
            datos[0] = rs.getString("id_chequeo");
            datos[1] = formatoFechaDeseada.format(formatoFechaActual.parse(rs.getString("fecha_chequeo")));
            datos[2] = rs.getString("vacunas_aplicadas");
            datos[3] = rs.getString("notas_generales");
            modelo.addRow(datos);
        }
        // Verificar si la consulta SQL devolvió algún resultado
        
        tabla.setModel(modelo);
    } catch (SQLException ex) {
        Logger.getLogger(RegistroMedicoBorregas.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "Intente de nuevo, fallo en la búsqueda del chequeo");
    }
}
    
//Mostrar todos los registros
private void mostrarTodo() {
    try {
        String[] columnas = {"Id", "Fecha", "Vacunas", "Notas"};
        String[] registro = new String[4];

        modelo = new DefaultTableModel(null, columnas);

        String sql = "SELECT * FROM ManejoMedicoBorregas";
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        st = (Statement) cn.createStatement();
        SimpleDateFormat formatoFechaActual = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatoFechaDeseada = new SimpleDateFormat("dd/MM/yyyy");
        while (rs.next()) {
            registro[0] = rs.getString("id_chequeo");
            try {
                registro[1] = formatoFechaDeseada.format(formatoFechaActual.parse(rs.getString("fecha_chequeo")));
            } catch (ParseException ex) {
                Logger.getLogger(RegistroMedicoBorregas.class.getName()).log(Level.SEVERE, null, ex);
            }
            registro[2] = rs.getString("vacunas_aplicadas");
            registro[3] = rs.getString("notas_generales");
            modelo.addRow(registro);
        }

        tabla.setModel(modelo);

    } catch (SQLException ex) {
        Logger.getLogger(BorregaCria.class.getName()).log(Level.SEVERE, null, ex);
    }
}
//Funcionalidad botones
    @Override
    public void actionPerformed(ActionEvent e) {
    if (e.getSource() == botonAgregar) {        
   // Lógica para agregar registro médico
        if(fechaTextArea.getText().length()>0 &&textIdChequeo.getText().length()>0 && textVacunas.getText().length()>0){
PreparedStatement pps;
try {
    //
String fechaChequeoString = fechaTextArea.getText();
SimpleDateFormat formatoEntrada = new SimpleDateFormat("dd/MM/yyyy");
Date fechaChequeoDate = formatoEntrada.parse(fechaChequeoString);
SimpleDateFormat formatoSalida = new SimpleDateFormat("yyyy-MM-dd");
String fechaChequeoFormateada = formatoSalida.format(fechaChequeoDate);
    //
pps = cn.prepareStatement("INSERT INTO ManejoMedicoBorregas(id_chequeo,fecha_chequeo,vacunas_aplicadas,notas_generales)VALUES (?,?,?,?)");
pps.setString(1, textIdChequeo.getText());
pps.setString(2, fechaChequeoFormateada);
pps.setString(3, textVacunas.getText());
pps.setString(4, textObservaciones.getText());

pps.executeUpdate();
JOptionPane.showMessageDialog(null, "Datos guardados con éxito");
textIdChequeo.setText("");
textVacunas.setText("");
textObservaciones.setText("");
fechaTextArea.setText("");

}  catch (SQLException ex) {
Logger.getLogger(BorregoEngorda.class.getName()).log(Level.SEVERE, null, ex);
JOptionPane.showMessageDialog(null, "Intente de nuevo, fallo en el registro nuevo");
}           catch (ParseException ex) {
                Logger.getLogger(RegistroMedicoBorregas.class.getName()).log(Level.SEVERE, null, ex);
            } }
else{
JOptionPane.showMessageDialog(null, "Favor de llenar todos los campos");
}
            
           } else if (e.getSource() == botonBuscar) {
    // Lógica para buscar registro médico
    
    if(textIdChequeo.getText().length()>0){
        try {
            mostrarDatos ();
              textIdChequeo.setText("");
        textVacunas.setText("");
        textObservaciones.setText("");
        fechaTextArea.setText("");
        } catch (ParseException ex) {
            Logger.getLogger(RegistroMedicoBorregas.class.getName()).log(Level.SEVERE, null, ex);
        }
}
        else {
            JOptionPane.showMessageDialog(null, "Favor de registrar un número de arete");
            
}
    
    
} else if (e.getSource() == botonEliminar) {
    // Lógica para eliminar registro médico
    if(textIdChequeo.getText().length()>0){
String valor= textIdChequeo.getText();
try {
PreparedStatement pps = cn.prepareStatement("DELETE FROM ManejoMedicoBorregas WHERE id_chequeo='"+valor+ "'");
pps.executeUpdate();
JOptionPane.showMessageDialog(null, "Datos eliminados con éxito");
textIdChequeo.setText("");
textVacunas.setText("");
textObservaciones.setText("");
fechaTextArea.setText("");
} catch (SQLException ex) {
Logger.getLogger(RegistroMedicoBorregas.class.getName()).log(Level.SEVERE, null, ex);
JOptionPane.showMessageDialog(null, "Intente de nuevo, fallo en la eliminación de datos");
}}
else{
JOptionPane.showMessageDialog(null, "Favor de registrar el número de arete");
}
    
    
} else if (e.getSource() == botonEditar) {
    // Lógica para editar registro médico
   if(fechaTextArea.getText().length()>0 &&textIdChequeo.getText().length()>0 && textVacunas.getText().length()>0){
       
try {
    
    String fechaChequeoString = fechaTextArea.getText();
    SimpleDateFormat formatoEntrada = new SimpleDateFormat("dd/MM/yyyy");
    Date fechaChequeoDate = formatoEntrada.parse(fechaChequeoString);
    SimpleDateFormat formatoSalida = new SimpleDateFormat("yyyy-MM-dd");
    String fechaChequeoFormateada = formatoSalida.format(fechaChequeoDate);
    String valor= textIdChequeo.getText();
    
    String f =fechaChequeoFormateada;
    String v =textVacunas.getText();
    String o =textObservaciones.getText();
    
    try {
        PreparedStatement pps = cn.prepareStatement("UPDATE ManejoMedicoBorregas SET id_chequeo = '"+valor+ "' ,fecha_chequeo = '"+f+ "' ,vacunas_aplicadas = '"+v+ "' ,notas_generales = '"+o+ "'WHERE id_chequeo='"+valor+ "'");
        pps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Datos actualizados con éxito");
        textIdChequeo.setText("");
        textVacunas.setText("");
        textObservaciones.setText("");
        fechaTextArea.setText("");
        
    } catch (SQLException ex) {
        Logger.getLogger(RegistroMedicoBorregas.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "Intente de nuevo, fallo en la modificación de datos"+ex);
    }} catch (ParseException ex) {
Logger.getLogger(RegistroMedicoBorregas.class.getName()).log(Level.SEVERE, null, ex);
}}
else {
JOptionPane.showMessageDialog(null, "Favor de llenar todos los campos");
}
//Boton VerTodo
} else if (e.getSource() == botonVerTodo) {

   mostrarTodo();
} else if (e.getSource() == botonCalendario) {
    calendar = new JCalendar();
    JOptionPane.showMessageDialog(null, calendar, "Seleccionar Fecha", JOptionPane.PLAIN_MESSAGE);
}else if (e.getSource() == menuItemInicio) {
    // Código para volver al inicio
     Home home = new Home();
        home.setVisible(true);
        dispose(); // Cierra la ventana actual de Login
    
} else if (e.getSource() == menuItemBorregoSemental) {
    // Código para cambiar a la vista de borrego semental
      BorregoSemental borregoSemental = new BorregoSemental();
        borregoSemental.setVisible(true);
        dispose(); // Cierra la ventana actual de Login
} else if (e.getSource() == menuItemBorregoEngorda) {
    // Código para cambiar a la vista de borrego de engorda
    BorregoEngorda borregoEngorda = new BorregoEngorda();
        borregoEngorda.setVisible(true);
        dispose(); // Cierra la ventana actual de Login
}

}

public static void main(String[] args) {
new RegistroMedicoBorregas();
}
}