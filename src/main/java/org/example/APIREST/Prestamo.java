package org.example.APIREST;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Prestamo {

    @JsonProperty("id")
    private int id;
    @JsonProperty("id_user")
    private int id_user;
    @JsonProperty("id_equipo")
    private int id_equipo;
    @JsonProperty("fecha_inicio")
    private String fecha_inicio;
    @JsonProperty("fecha_fin")
    private String fecha_fin;
    @JsonProperty("fecha_real")
    private String fecha_real;
    @JsonProperty("comentario")
    private String comentario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(int id_equipo) {
        this.id_equipo = id_equipo;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getFecha_real() {
        return fecha_real;
    }

    public void setFecha_real(String fecha_real) {
        this.fecha_real = fecha_real;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Prestamo() {
    }

    public Prestamo(int id, int id_user, int id_equipo, String fecha_inicio, String fecha_fin, String fecha_real,String comentario) {
        this.id = id;
        this.id_user = id_user;
        this.id_equipo = id_equipo;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.fecha_real = fecha_real;
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "id_user='" + id_user + '\'' +
                ", id_equipo='" + id_equipo + '\'' +
                ", fecha inicio='" + fecha_inicio + '\'' +
                ", fecha fin='" + fecha_fin + '\'' +
                ", fecha real='" + fecha_real + '\'' +
                ", comentario='" + comentario+
                "}";
    }

    public void EditarPrestamo(Prestamo prestEditado){
        this.setId_user(prestEditado.getId_user());
        this.setId_equipo(prestEditado.getId_equipo());
        this.setFecha_inicio(prestEditado.getFecha_inicio());
        this.setFecha_fin(prestEditado.getFecha_fin());
        this.setFecha_real(prestEditado.getFecha_real());
        this.setComentario(prestEditado.getComentario());
    }
}
