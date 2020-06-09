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
import modelVO.AlquilerVO;
import servicios.Conexion;

/**
 *
 * @author NELSON
 */
public class AlquilerDAO {
       private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL="";
    public boolean insertar(AlquilerVO dts){
    
        sSQL ="INSERT INTO alquiler (idusuario,idhabitacion,cantidad,fecha)"+ 
                "VALUES (?,?,?,?)";
        
      try{ 
          PreparedStatement pst = cn.prepareStatement(sSQL);
          pst.setString(1, dts.getIdUsuario());
          pst.setInt(2, dts.getIdHabitacion());
          pst.setInt(3, dts.getCantidad());
          pst.setDate(4, (Date) dts.getFecha());

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
