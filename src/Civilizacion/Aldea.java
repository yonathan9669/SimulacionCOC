package Civilizacion;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Aldea extends Thread {

    //------------------------------ VARIABLES ---------------------------------
    // <editor-fold desc="Variables">
    private int[][] posiciones;
    // oro: Cantidad de oro
    // elixir: Cantidad de elixir
    // poblacion: Cantidad de tropas actual en la aldea
    // idTropas: Contador para asignarle a cada tropa un id distinto
    public double oro, elixir;
    public int poblacion;
    private int idTropas;
    //
    public ArrayList<Edificio> edificios;
    public ArrayList<Tropa> tropas;
    public ArrayList<Tropa> tropasEnemigas;
    //
    public boolean atacando;
    public long endTime;
    private Random rand;
    //  </editor-fold>

    //------------------------------ CONSTRUCTOR ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public Aldea(double or, double el) {
        oro = or;
        elixir = el;
        poblacion = 0;
        idTropas = 0;
        edificios = new ArrayList<>();
        tropas = new ArrayList<>();

        this.posiciones = new int[vg.ALTO][vg.ANCHO];
        this.rand = new Random(System.currentTimeMillis());
    }
    //  </editor-fold>

    //------------------------------ OBTENER ID DE PROXIMA TROPA ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="aumentarIdTropas">
    public void aumentarIdTropas() {
        idTropas++;
    }
    //  </editor-fold>

    //------------------------------ GET ID TROPAS ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="getIdTropas">
    public int getIdTropas() {
        return idTropas;
    }
    //  </editor-fold>

    //------------------------------ OBTENER CAPACIDAD ORO ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="capacidadOro">
    public int capacidadOro() {
        int capacidad = 0;
        for (Edificio e : edificios) {
            if (e.tipo == vg.AYUNTAMIENTO) {
                return e.mejoras[e.nivel].capacidad;
            }
        }
        return capacidad;
    }
    //  </editor-fold>

    //------------------------------ OBTENER CAPACIDAD ELIXIR ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="capacidadElixir">
    public int capacidadElixir() {
        int capacidad = 0;
        for (Edificio e : edificios) {
            if (e.tipo == vg.AYUNTAMIENTO) {
                return e.mejoras[e.nivel].capacidad;
            }
        }
        return capacidad;
    }
    //  </editor-fold>

    //------------------------------ ALMACENAR ORO ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="almacenarOro">
    public void almacenarOro(double almacenado) {
        oro = almacenado;
    }
    //  </editor-fold>

    //------------------------------ ALMACENAR ELIXIR ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="almacenarElixir">
    public void almacenarElixir(double almacenado) {
        elixir = almacenado;
    }
    //  </editor-fold>

    //------------------------------ GASTAR ORO ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="gastarOro">
    public void gastarOro(double gasto) {
        oro -= gasto;
    }
    //  </editor-fold>

    //------------------------------ GASTAR ELIXIR ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="gastarElixir">
    public void gastarElixir(double gasto) {
        elixir -= gasto;
    }
    //  </editor-fold>

    //------------------------------ OBTENER CONSTRUCTORES LIBRES ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="constructoresLibres">
    public int constructoresLibres() {
        int libres = 0;
        for (Edificio e : edificios) {
            if (e.tipo == vg.CHOZA && e.constructorLibre()) {
                libres++;
            }
        }
        return libres;
    }
    //  </editor-fold>

    //------------------------------ LIBERAR CONSTRUCTOR ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="liberarConstructor">
    public void liberarConstructor() {
        for (Edificio e : edificios) {
            if (e.tipo == vg.CHOZA && !e.constructorLibre()) {
                e.liberarConstructor();
                break;
            }
        }
    }
    //  </editor-fold>

    //------------------------------ BLOQUEAR CONSTRUCTOR ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="bloquearConstructor">
    public void bloquearConstructor() {
        for (Edificio e : edificios) {
            if (e.tipo == vg.CHOZA && e.constructorLibre()) {
                e.bloquearConstructor();
                break;
            }
        }
    }
    //  </editor-fold>

    //------------------------------ CREAR EDIFICIO ---------------------------------
    // Crear edificio es al iniciar la aldea. No genera un evento futuro y es habilitado de una vez.
    // <editor-fold defaultstate="collapsed" desc="crearEdificio">
    public void crearEdificio(Edificio ed) {
        Point posicion = this.getPosicion();
        // Crear objeto nuevo edificio
        Edificio edif = new Edificio(ed.tipo, ed.tipoCompra, ed.mejoras, posicion);

        //Liberar constructor si este edificio es una choza
        if (edif.tipo == vg.CHOZA) {
            edif.liberarConstructor();
        }

        // Establecer Id
        edif.setId(edificios.size());

        // Agregarlo a los edificios de esta aldea
        edificios.add(edif);

        // Habilitar
        edif.habilitar();
    }
    //  </editor-fold>

    //------------------------------ CONSTRUIR EDIFICIO ---------------------------------
    // Construir edificio es para luego de iniciar la aldea. Genera un evento futuro y 
    // empieza deshabilitado. El evento futuro es cuando culmina la construccion y ahi se habilita.
    // <editor-fold defaultstate="collapsed" desc="construirEdificio">
    public EventoFuturo construirEdificio(Date tiempo, Edificio ed) {
        Point posicion = this.getPosicion();
        // Crear objeto nuevo edificio
        Edificio edif = new Edificio(ed.tipo, ed.tipoCompra, ed.mejoras, posicion);

        //Aumentar nivel
        //edif.aumentarNivel();
        // Establecer Id
        edif.setId(edificios.size());

        // Agregarlo a los edificios de esta aldea
        edificios.add(edif);

        // Calcular tiempo de finalizado de construccion
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tiempo); // Configurar la fecha
        calendar.add(Calendar.SECOND, edif.mejoras[edif.nivel].tiempo);  // Sumar los segundos de tiempo

        // Crear evento futuro
        EventoFuturo evento = new EventoFuturo(calendar.getTime(), vg.EV_CULMINAR_EDIF, edif.getEdifId());
        //System.out.println((calendar.getTime()));

        return evento;
    }
    //  </editor-fold>

    //------------------------------ MEJORAR EDIFICIO ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="mejorarEdificio">
    public EventoFuturo mejorarEdificio(Date tiempo, Edificio ed) {
        //Deshabilitar
        ed.deshabilitar();

        //Aumentar nivel
        ed.aumentarNivel();

        // Calcular tiempo de finalizado de mejora
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tiempo); // Configurar la fecha
        calendar.add(Calendar.SECOND, ed.mejoras[ed.nivel].tiempo);  // Sumar los minutos de tiempo

        System.out.println("Tiempo de fin de mejora: " + calendar.getTime());

        // Crear evento futuro
        EventoFuturo evento = new EventoFuturo(calendar.getTime(), vg.EV_CULMINAR_EDIF, ed.id);
        //System.out.println((calendar.getTime()));

        return evento;
    }
    //  </editor-fold>

    //------------------------------ OBTENER CAPACIDAD TROPAS ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="capacidadTropas">
    public int capacidadTropas() {
        int capacidad = 0;
        for (Edificio edificio : edificios) {
            if (edificio.tipo == vg.CAMPAMENTO) {
                capacidad += edificio.capacidadCola();
            }
        }
        return capacidad;
    }
    //  </editor-fold>

    //------------------------------ HABILITAR TROPA ---------------------------------
    // Agregar tropa a la aldea luego de que haya sido construida.
    // Esta tropa debio haberse empezado a construir en Edificio.java, luego de el evento EV_CULMINAR_TROPA, 
    // la tropa se saca del edificio y agrega a la aldea
    // <editor-fold defaultstate="collapsed" desc="habilitarTropa">
    public void habilitarTropa(Tropa tr) {
        // Habilitarla
        tr.habilitar();
        // Agregarla a las tropas de esta aldea
        tropas.add(tr);
    }
    //  </editor-fold>

    //------------------------------ USAR TROPAS ---------------------------------
    // Usar tropas para "atacar"
    // <editor-fold defaultstate="collapsed" desc="usarTropas">
    public void usarTropas(ArrayList<Integer> cantidad, ArrayList<Integer> tipos, Aldea enemigo) {

    }
    //  </editor-fold>

    //------------------------------ ¿ESTA DESTRUIDA LA ALDEA? ---------------------------------
    // Si algun edificio tiene mas de 0 de vida, la aldea NO esta destruida
    // <editor-fold defaultstate="collapsed" desc="destruida">
    public boolean destruida() {
        for (Edificio edificio : edificios) {
            if (edificio.vidaActual > 0) {
                return false;
            }
        }
        return true;
    }
    //  </editor-fold>

    //------------------------------ EDIFICIO CERCANO ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="edifCercano">
    /*public ClaseEdificio edifCercano(int posicionTropa){
        
     return ec;
     }]*/
    //  </editor-fold>
    //------------------------------ TORRE CERCANA ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="torreCercana">
    /*public ClaseEdificio torreCercana(int posicionTropa){
        
     return tc;
     }*/
    //  </editor-fold>
    //------------------------------ RECOLECTOR CERCANO ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="recolectorCercano">
    /*public ClaseEdificio recolectorCercano(int posicionTropa){
        
     return rc;
     }*/
    //  </editor-fold>
    //------------------------------ TROPA CERCANA ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="tropaCercana">
    /*public ClaseTropa tropaCercana(ClaseEdificio torre, ArrayList<ClaseTropa> tropas){
        
     return tc;
     }*/
    //  </editor-fold>
    //------------------------------ OBTENER LISTA DE TORRES ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="torres">
    public ArrayList<Edificio> torres() {
        ArrayList<Edificio> torres = new ArrayList<Edificio>();
        for (Edificio ed : edificios) {
            // Preguntar si edificio es una torre
            if (ed.tipo == vg.TORRE || ed.tipo == vg.CAÑON || ed.tipo == vg.MORTERO) {
                torres.add(ed);
            }
        }
        return torres;
    }
    //  </editor-fold>

    //------------------------------ RECIBIR ATAQUE ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="torres">
    public void RecibirAtaque(ArrayList<Tropa> tropas) {
        this.endTime = System.currentTimeMillis() + vg.TIEMPO_ATAQUE;
        this.tropasEnemigas = tropas;

        this.start();
    }
    //  </editor-fold>

    @Override
    public void run() {
        ArrayList<Edificio> torres = this.torres();
        this.atacando = this.endTime - System.currentTimeMillis() > 0;
        System.out.println("Iniciando ATAQUE " + !this.tropasEnemigas.isEmpty());

        while (atacando && !this.destruida() && !this.tropasEnemigas.isEmpty()) {
            for (Tropa tropa : this.tropasEnemigas) {
                if (!tropa.atacando()) {
                    Edificio edif = null;

                    switch (tropa.tipo) {
                        case vg.ARQUERA:
                        case vg.BARBARO:
                            edif = this.edifCercano(tropa.posicion);
                            break;
                        case vg.GIGANTE:
                            edif = this.torreCercana(tropa.posicion);
                            break;
                        case vg.DUENDE:
                            edif = this.recolectorCercano(tropa.posicion);
                            break;
                    }

                    if (edif == null) {
                        edif = this.edifCercano(tropa.posicion);
                    }

                    tropa.atacar(edif);
                }
            }

            for (Edificio torre : torres) {
                if (!torre.atacando()) {
                    Tropa tropa = this.tropaCercana(torre);

                    if (tropa != null) {
                        torre.atacar(tropa);
                    }
                }
            }

            this.atacando = this.endTime - System.currentTimeMillis() > 0;
        }

        System.out.println("ATAQUE Finalizado");
    }

    private Tropa tropaCercana(Edificio torre) {
        Tropa tropa;
        int size = this.tropasEnemigas.size();
        int i = size;

        do {
            i--;
            int pos = rand.nextInt(size);
            tropa = this.tropasEnemigas.get(pos);
        } while (i > 0);

        return tropa;
    }

    private Point getPosicion() {
        for (int i = 0; i < vg.ALTO; i++) {
            for (int j = 0; j < vg.ANCHO; j++) {
                if (posiciones[i][j] == 0) {
                    this.posiciones[i][j] = 1;
                    return new Point(i, j);
                }
            }
        }

        return null;
    }

    private Edificio edifCercano(Point posicion) {
        Edificio edif;
        int size = edificios.size();
        int i = size;

        do {
            i--;
            int pos = rand.nextInt(size);
            edif = this.edificios.get(pos);
        } while (i > 0 && edif.vidaActual <= 0);

        return edif;
    }

    private Edificio torreCercana(Point posicion) {
        Edificio torre;
        ArrayList<Edificio> torres = this.torres();
        int size = torres.size();
        int i = size;

        do {
            i--;
            int pos = rand.nextInt(size);
            torre = torres.get(pos);
        } while (i > 0 && torre.vidaActual <= 0);

        return torre;
    }

    private Edificio recolectorCercano(Point posicion) {
        Edificio recolector;
        ArrayList<Edificio> recolectores = this.recolectores();
        int size = recolectores.size();
        int i = size;

        do {
            i--;
            int pos = rand.nextInt(size);
            recolector = recolectores.get(pos);
        } while (i > 0 && recolector.vidaActual <= 0);

        return recolector;
    }

    private ArrayList<Edificio> recolectores() {
        ArrayList<Edificio> recolectores = new ArrayList<Edificio>();
        for (Edificio ed : edificios) {
            // Preguntar si edificio es un recolector
            if (ed.tipo == vg.MINA || ed.tipo == vg.RECOLECTOR) {
                recolectores.add(ed);
            }
        }
        return recolectores;
    }
}
