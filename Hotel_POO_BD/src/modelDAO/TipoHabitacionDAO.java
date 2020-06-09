/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import modelVO.*;
import servicios.Conexion;

/**
 *
 * @author NELSON
 */
public class TipoHabitacionDAO {
     private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL="";
    public boolean insertar(TipohabitacionVO dts){
    
        sSQL ="INSERT INTO tipohabitacion (descripcion,tarifa,cantidad)"+ 
                "VALUES (?,?,?)";        
      try{ 
          PreparedStatement pst = cn.prepareStatement(sSQL);
          pst.setString(1, dts.getDescripcion());
          pst.setInt(2, dts.getTarifa());
          pst.setInt(3, dts.getCantidad());
          int n=pst.executeUpdate();
          if(n!=0){
              return true;
          }else{
              return false;
          }
    }catch(Exception e){
        JOptionPane.showConfirmDialog(null, e);
        return false;
    }
} 
}
