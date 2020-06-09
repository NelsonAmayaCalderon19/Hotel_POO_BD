/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import modelDAO.*;
import modelVO.AlquilerVO;
import modelVO.TipohabitacionVO;
import modelVO.UsuarioVO;
import servicios.Conexion;
import vista.*;

/**
 *
 * @author NELSON
 */
public class Controlador implements ActionListener {

    private Vista v = null;
    int idhab = 0;

    public Controlador(Vista v) {
        super();
        this.v = v;
        actionListener(this);
        v.Fecha.setDate(new Date());
    }

    private void actionListener(ActionListener controlador) {
        v.btnRegistrar.addActionListener(controlador);
        v.btnLimpiar.addActionListener(controlador);

    }
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    int cont = 0;

    public boolean obtenerIdHabitacion(AlquilerVO dts) {
        int cont = 0;

        String sSQL = "SELECT * FROM TIPOHABITACION ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                idhab = (Integer.parseInt(rs.getString("id")));
                cont++;
            }

            if (cont != 0) {
                cont = 0;
                return true;
            } else {
                cont = 0;
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            cont = 0;
            return false;
        }
    }

    void limpiar() {
        v.t1.setText("");
        v.t2.setText("");
        v.t3.setText("");
        v.t4.setText("");
        v.t5.setText("");
        v.t6.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == v.btnRegistrar) {
                if (v.t1.getText().length() == 0) {
                    JOptionPane.showConfirmDialog(null, "Debes ingresar el Nombre del Usuario");
                    v.t1.requestFocus();
                    return;
                }
                if (v.t2.getText().length() == 0) {
                    JOptionPane.showConfirmDialog(null, "Debes ingresar el  Documento del Usuario");
                    v.t2.requestFocus();
                    return;
                }

                if (v.t3.getText().length() == 0) {
                    JOptionPane.showConfirmDialog(null, "Debes ingresar la Edad del Usuario");
                    v.t3.requestFocus();
                    return;
                    
                } if (v.t4.getText().length() == 0 && v.t5.getText().length()==0 && v.t6.getText().length()==0) {
                    JOptionPane.showConfirmDialog(null, "Debes Ingresar la Cantidad de Habitaciones que desea Alquilar");
                    return;
                }
                else {

                    String identificacion = v.t2.getText();
                    String id = identificacion.substring(identificacion.length() - 4, identificacion.length());

                    int precio = 0, cantidad = 0;
                    String tipo = "";
                    UsuarioVO dts = new UsuarioVO();
                    UsuarioDAO func = new UsuarioDAO();

                    dts.setId(id);
                    dts.setIdentificacion(v.t2.getText());
                    dts.setNombre(v.t1.getText());
                    dts.setEdad(Integer.parseInt(v.t3.getText()));
                    TipohabitacionVO dts3 = new TipohabitacionVO();
                    TipoHabitacionDAO func3 = new TipoHabitacionDAO();
                    if (!v.t4.getText().equals("") && v.t5.getText().equals("") && v.t6.getText().equals("")) {
                        precio = 40000 * Integer.parseInt(v.t4.getText());
                        cantidad = Integer.parseInt(v.t4.getText());
                        tipo = "Sencilla";
                    } else if (v.t4.getText().equals("") && !v.t5.getText().equals("") && v.t6.getText().equals("")) {
                        precio = 60000 * Integer.parseInt(v.t5.getText());
                        cantidad = Integer.parseInt(v.t5.getText());
                        tipo = "Doble";
                    } else if (v.t4.getText().equals("") && v.t5.getText().equals("") && !v.t6.getText().equals("")) {
                        precio = 80000 * Integer.parseInt(v.t6.getText());
                        cantidad = Integer.parseInt(v.t6.getText());
                        tipo = "Suite";
                    }
                    dts3.setDescripcion(tipo);
                    dts3.setTarifa(precio);
                    dts3.setCantidad(cantidad);
                    AlquilerVO dts2 = new AlquilerVO();
                    AlquilerDAO func2 = new AlquilerDAO();
                    dts2.setIdUsuario(id);
                    dts2.setCantidad(cantidad);
                    Calendar cal;
                    int d, m, a;
                    cal = v.Fecha.getCalendar();
                    d = cal.get(Calendar.DAY_OF_MONTH);
                    m = cal.get(Calendar.MONTH);
                    a = cal.get(Calendar.YEAR) - 1900;
                    dts2.setFecha(new java.sql.Date(a, m, d));
                    func.insertar(dts);
                    JOptionPane.showMessageDialog(null, "El Usuario fue Registrado Exitosamente");
                    func3.insertar(dts3);
                    JOptionPane.showMessageDialog(null, "Habitación Entregada Exitosamente");
                    obtenerIdHabitacion(dts2);
                    dts2.setIdUsuario(id);
                    dts2.setIdHabitacion(idhab);
                    func2.insertar(dts2);
                    JOptionPane.showMessageDialog(null, "El Alquiler fue Registrado Exitosamente");
                }
            }
            if (e.getSource() == v.btnLimpiar) {
                limpiar();
            }
        } catch (Exception ev) {
            ev.getMessage();
        }

    }
}
