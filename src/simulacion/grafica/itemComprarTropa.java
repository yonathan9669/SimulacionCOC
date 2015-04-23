package simulacion.grafica;

import Civilizacion.ClaseTropa;
import java.util.ArrayList;

public class itemComprarTropa {

    public int id, nivel;
    public ArrayList<ClaseTropa> colaTropas;
    public String nombre;

    public itemComprarTropa(int id, int nivel, ArrayList<ClaseTropa> colaTropas, String nombre) {
        this.id = id;
        this.nivel = nivel;
        this.colaTropas = new ArrayList<>();
        for(ClaseTropa t: colaTropas)
            this.colaTropas.add(t);
        this.nombre = nombre;
    }

}    
