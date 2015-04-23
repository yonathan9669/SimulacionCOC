package simulacion.util;

import Civilizacion.Aldea;
import Civilizacion.Edificio;
import Civilizacion.Tropa;
import java.util.Random;

public class Ataque extends Aldea {

    Edificio cuartel;
    Tropa[] tiposTropas;

    public Ataque(double or, double el, Edificio cuartel, Tropa[] tipos) {
        super(or, el);
        this.cuartel = cuartel;
        this.tiposTropas = tipos;

        this.crearEdificio(cuartel);
        this.generarTropas();
    }

    private void generarTropas() {
        int id = 0;
        Random rand = new Random(System.currentTimeMillis());
        while (this.elixir >= 25) {
            int i = rand.nextInt(4);
            if (tiposTropas[i].precio <= this.elixir) {
                id++;
                this.elixir -= tiposTropas[i].precio;
                this.habilitarTropa(this.cuartel.crearTropa(tiposTropas[i], id));
            }
        }
    }

    public void Atacar(Aldea aldea) {
        System.out.println("Tropas de ATAQUE");
        
        for (Tropa tropa : tropas) {
            System.out.println(tropa.getTropaId() + ".- " + tropa.getNombre());
        }
        
        aldea.RecibirAtaque(tropas);
    }
}
