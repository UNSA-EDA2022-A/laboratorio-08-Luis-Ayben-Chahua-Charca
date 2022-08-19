package com.example.project;

public class Persona {
    public String DNI, nombre;

    public Persona(String DNI, String nombre){
        this.DNI = DNI;
        this.nombre = nombre;
    }

    public String toString(){
        return nombre + " Dni: " + DNI;
    }
}
