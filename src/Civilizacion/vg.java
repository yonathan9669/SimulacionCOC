package Civilizacion;

public class vg {

    //------------------------------ VARIABLES GLOBALES ---------------------------------
    // Tamaño de ALDEA
    public static final int ALTO = 20;
    public static final int ANCHO = 20;
    // Edificios
    public static final int AYUNTAMIENTO = 0, CHOZA = 1, CAMPAMENTO = 2, CUARTEL = 3, MINA = 4, 
                            RECOLECTOR = 5, TORRE = 6, CAÑON = 7, MORTERO = 8;
    // Tropas
    public static final int BARBARO = 0, ARQUERA = 1, GIGANTE = 2, DUENDE = 3;
    // Niveles de edificios
    public static final int NIVELES = 5;
    // Eventos futuros posibles
    public static final int EV_CULMINAR_EDIF = 0, EV_CULMINAR_TROPA = 1;
    // Constantes para Simular ATAQUE
    public static final int TIEMPO_ATAQUE = 180 * 1000;
    public static final int minOroAtaque = 500;
    public static final int maxOroAtaque = 1500;
    public static final int minElixirAtaque = 500;
    public static final int maxElixirAtaque = 1500;
    public static final int RETRASO = 1000;
}
