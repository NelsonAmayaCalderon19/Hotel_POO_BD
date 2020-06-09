/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import modelVO.UsuarioVO;
import servicios.Conexion;

/**
 *
 * @author NELSON
 */
public class UsuarioDAO {
    
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL="";
    public boolean insertar(UsuarioVO dts){
    
        sSQL ="INSERT INTO USUARIO (id,identificacion,nombre,edad)"+ 
                "VALUES (?,?,?,?)";
        
      try{ 
          PreparedStatement pst = cn.prepareStatement(sSQL);
          pst.setString(1, dts.getId());
          pst.setString(2, dts.getIdentificacion());
          pst.setString(3, dts.getNombre());
          pst.setInt(4, dts.getEdad());

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
