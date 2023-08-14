package com.sanchez.app.rutas.models.enums;

public enum Marcas {

    VOLVO("Volvo"),
    ALLIANCE("Alliance"),
    FORD("Ford"),
    DINA("Dina"),
    MERCEDES_BENZ("Mercedes Benz");

    //Atributos
    private String Descripcion;

    Marcas(String descripcion) {
        Descripcion = descripcion;
    }

    public static Marcas getFromString(String status){
        switch(status){
            case "Volvo":
                return VOLVO;
            case "Alliance":
                return ALLIANCE;
            case "Ford":
                return FORD;
            case "Mercedes Benz":
                return MERCEDES_BENZ;
            default:
                return null;
        }
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
