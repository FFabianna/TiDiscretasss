package model.objects;

import model.data_structures.TablaHash;
import java.util.ArrayList;

public class Edificios {
    private char name;
    private int pisos;
    private int num_oficinas;
    private TablaHash<Integer, Personas> oficinas;

    public Edificios(char name, int pisos, int num_oficinas) {
        this.name = name;
        this.pisos = pisos;
        this.num_oficinas = pisos * num_oficinas;
        oficinas = new TablaHash<>(this.num_oficinas);
    }

    public char getName() {
        return name;
    }

    public int getPisos() {
        return pisos;
    }

    public int getNum_oficinas() {
        return num_oficinas;
    }

    public TablaHash<Integer, Personas> getoficinas() {
        return oficinas;
    }
}
