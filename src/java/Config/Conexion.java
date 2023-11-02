/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Config;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author pc
 */
public class Conexion {
    Connection con;

    String ruta = "jdbc:mysql://localhost:3306/bd_ventas";
    
    public Connection Conexion(){
        
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(ruta, "root", "");
          
        }catch(Exception e){
            
            System.out.print("Error " + e);
        }
         return con;
 
    }
    
}
