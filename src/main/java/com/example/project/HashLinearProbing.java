package com.example.project;

import java.util.Random;

public class HashLinearProbing {
    private int hsize; // tamano de la tabla hash
    private Persona[] buckets; // array que representa la tabla hash
    private Persona AVAILABLE;
    private int size; // cantidad de elementos en la tabla hash

    public HashLinearProbing(int hsize) {
        this.setBuckets(new Persona[hsize]);
        this.hsize = hsize;
        this.AVAILABLE = new Persona(Integer.MIN_VALUE + "", "sin nombre");
        this.size = 0;
    }

    public Persona[] getBuckets() {
        return buckets;
    }

    public void setBuckets(Persona[] buckets) {
        this.buckets = buckets;
    }

    public int hashing(int key) {
        int hash = key % hsize;
        if (hash < 0) {
            hash += hsize;
        }
        return hash;
    }

    /**
     * @param key
     */
    public void insertHash(Persona persona) {
        Integer key = Integer.parseInt(persona.DNI);
        int hash = hashing(key);

        if (isFull()) {
            System.out.println("Tabla hash esta llena!");
            return;
        }

        for (int i = 0; i < hsize; i++) {
            if ( getBuckets()[hash] == null || getBuckets()[hash] == AVAILABLE) {
                getBuckets()[hash] = persona;
                size++;
                System.out.println("clave añadida con exito");
                return;
            }

            if (hash + 1 < hsize) {
                hash++;
            } else {
                hash = 0;
            }
        }
    }

    public void deleteHash(int key) {
        Integer wrappedInt = key;
        int hash = hashing(key);

        if (isEmpty()) {
            System.out.println("Tabla hash esta vacia!");
            return;
        }

        for (int i = 0; i < hsize; i++) {
            if (getBuckets()[hash] != null && getBuckets()[hash].DNI.equals(wrappedInt+"")) {
                getBuckets()[hash] = AVAILABLE;
                size--;
                return;
            }

            if (hash + 1 < hsize) {
                hash++;
            } else {
                hash = 0;
            }
        }
        System.out.println("Clave " + key + " no encontrada");
    }

    public void displayHashtable() {
        for (int i = 0; i < hsize; i++) {
            if (getBuckets()[i] == null || getBuckets()[i] == AVAILABLE) {
                System.out.println("Celda " + i + ": Vacia");
            } else {
                System.out.println("Celda " + i + ": " + getBuckets()[i].toString());
            }
        }
    }

    public int findHash(int key) {
        Integer wrappedInt = key;
        int hash = hashing(key);

        if (isEmpty()) {
            System.out.println("Tabla hash esta vacia!");
            return -1;
        }

        for (int i = 0; i < hsize; i++) {
            try {
                if (getBuckets()[hash].DNI.equals(wrappedInt+"")) {
                    //buckets[hash] = AVAILABLE;
                    return hash;
                }
            } catch (Exception E) {
            }

            if (hash + 1 < hsize) {
                hash++;
            } else {
                hash = 0;
            }
        }
        System.out.println("Clave " + key + " no encontrada!");
        return -1;
    }        
   
    public boolean isFull() {        
        return size == hsize;
    }

    public boolean isEmpty() {
        boolean response = true;
        for (int i = 0; i < hsize; i++) {
            if (getBuckets()[i] != null) {
                response = false;
                break;
            }
        }
        return response;
    }

    /**
     * @param args
     */
    public static void main (String[] args){
        HashLinearProbing tb = new HashLinearProbing(10);

        Random rd = new Random();

        for(int i = 0; i < 5; i++){
            //tb.insertHash(rd.nextInt(100));
        }

        tb.displayHashtable();        
    }
}
