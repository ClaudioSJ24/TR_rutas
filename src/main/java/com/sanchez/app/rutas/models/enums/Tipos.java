package com.sanchez.app.rutas.models.enums;

public enum Tipos {

    TRAILER("Trailer"),
    TORTON("Torton"),
    DOBLE_REMOLQUE("Doble Remolque"),
    VOLTEO("Volteo"),
    SEMI_REMOLQUE("Semi Remolque");

    //Atributos
    private String descripcion;

    Tipos(String descripcion) {
        this.descripcion = descripcion;
    }

    public static Tipos getFromString(String status){
        switch(status){
            case "Trailer":
                return TRAILER;
            case "Torton":
                return TORTON;
            case "Doble Remolque":
                return DOBLE_REMOLQUE;
            case "Volteo":
                return VOLTEO;
            case "Semi Remolque":
                return SEMI_REMOLQUE;
            default:
                return null;
        }
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
