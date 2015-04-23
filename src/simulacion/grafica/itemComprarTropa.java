package simulacion.grafica;

import Civilizacion.Tropa;
import java.util.ArrayList;

public class itemComprarTropa {

    public int id, nivel;
    public ArrayList<Tropa> colaTropas;
    public String nombre;

    public itemComprarTropa(int id, int nivel, ArrayList<Tropa> colaTropas, String nombre) {
        this.id = id;
        this.nivel = nivel;
        this.colaTropas = new ArrayList<>();
        for(Tropa t: colaTropas)
            this.colaTropas.add(t);
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}    
