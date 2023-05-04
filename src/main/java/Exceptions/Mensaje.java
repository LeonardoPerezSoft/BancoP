package Exceptions;

import java.time.LocalDateTime;
import java.util.Objects;

public class Mensaje {
    private String error;
   // private String detalle;
    private String notificacion;

    private LocalDateTime data;



    public String getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(String notificacion) {
        this.notificacion = notificacion;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return "Mensaje{" +
                "error='" + error + '\'' +
                ", notificacion='" + notificacion + '\'' +
                ", data=" + data +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mensaje mensaje = (Mensaje) o;
        return Objects.equals(error, mensaje.error) && Objects.equals(notificacion, mensaje.notificacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(error, notificacion);
    }

    public void setError(String error) {
        this.error = error;
        LocalDateTime now = LocalDateTime.now();
        setData(now);
    }




//    public String getDetalle() {
//        return detalle;
//    }
//
//    public void setDetalle(String detalle) {
//        this.detalle = detalle;
//    }

//    public void buildMessageBdError(String mensaje){
//        String[] items = mensaje.split("Detail: ");
//        setError(items[0].split("ERROR: ")[1]);
//        setDetalle(items[1]);
//        LocalDateTime now = LocalDateTime.now();
//        setData(now);
//    }

     public void mensajeNotFound(String mensajeN){
         setError(mensajeN);
         LocalDateTime now = LocalDateTime.now();
         setData(now);
    }

    public void mensajeExitoso(String mensajeE){
        setNotificacion(mensajeE);
        LocalDateTime now = LocalDateTime.now();
        setData(now);
    }



}