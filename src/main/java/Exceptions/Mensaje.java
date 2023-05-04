package Exceptions;

import java.time.LocalDateTime;

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