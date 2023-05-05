/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Paquete;

import java.sql.DriverManager;
import java.sql.Connection;


	
public class librer {
Connection conectar = null;
public Connection conexion(){
try{
Class.forName ( "com.mysql.cj.jdbc.Driver" );
conectar = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/AdminGanadera","root","1234");
}                                                       
catch(Exception e){                                     
System.out.print(e.getMessage());
}
return conectar;
}}
