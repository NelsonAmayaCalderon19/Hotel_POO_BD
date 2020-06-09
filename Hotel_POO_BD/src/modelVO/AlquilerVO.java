/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelVO;

import java.util.Date;
import java.util.logging.Logger;

/**
 *
 * @author Hp
 */
public class AlquilerVO {
    private Integer id;
    private String idUsuario;
    private Integer idHabitacion;
    private Integer cantidad;
    private Date fecha;

    public AlquilerVO() {
    }

    public AlquilerVO(Integer id, String idUsuario, Integer idHabitacion, Integer cantidad, Date fecha) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idHabitacion = idHabitacion;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(Integer idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

   
    
}
