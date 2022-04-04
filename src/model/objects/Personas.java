package model.objects;

public class Personas {
    private String nombre;
    private int posicion;
    private int oficina;

    public Personas(String nombre, int posicion, int oficina) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.oficina = oficina;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPosicion() {
        return posicion;
    }

    public int getOficina() {
        return oficina;
    }
}
