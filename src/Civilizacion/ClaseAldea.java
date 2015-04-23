package Civilizacion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ClaseAldea {
    //------------------------------ VARIABLES ---------------------------------
    // <editor-fold desc="Variables">
    // oro: Cantidad de oro
    // elixir: Cantidad de elixir
    // poblacion: Cantidad de tropas actual en la aldea
    // idTropas: Contador para asignarle a cada tropa un id distinto
    public double oro, elixir; 
    public int poblacion;
    private int idTropas;
    //
    public ArrayList<ClaseEdificio> edificios;
    public ArrayList<ClaseTropa> tropas;
    public ArrayList<ClaseTropa> tropasEnemigas;
    //  </editor-fold>
    
    //------------------------------ CONSTRUCTOR ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public ClaseAldea(double or, double el){
        oro = or;
        elixir = el;
        poblacion = 0;
        idTropas = 0;
        edificios = new ArrayList<>();
        tropas = new ArrayList<>();
    }
    //  </editor-fold>
    
    //------------------------------ OBTENER ID DE PROXIMA TROPA ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="aumentarIdTropas">
    public void aumentarIdTropas(){
        idTropas++;
    }     
    //  </editor-fold>
    
    //------------------------------ OBTENER ID DE PROXIMA TROPA ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="aumentarIdTropas">
    public int getIdTropas(){
        return idTropas;
    }     
    //  </editor-fold>
    
    //------------------------------ OBTENER CAPACIDAD ORO ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="capacidadOro">
    public int capacidadOro(){
        int capacidad = 0;
        for(ClaseEdificio e: edificios){
            if(e.tipo == vg.AYUNTAMIENTO)
                return e.mejoras[e.nivel].capacidad;
        }
        return capacidad;
    }
    //  </editor-fold>
    
    //------------------------------ OBTENER CAPACIDAD ELIXIR ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="capacidadElixir">
    public int capacidadElixir(){
        int capacidad = 0;
        for(ClaseEdificio e: edificios){
            if(e.tipo == vg.AYUNTAMIENTO)
                return e.mejoras[e.nivel].capacidad;
        }
        return capacidad;
    }
    //  </editor-fold>
    
    //------------------------------ ALMACENAR ORO ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="almacenarOro">
    public void almacenarOro(double almacenado){
        oro = almacenado;
    }
    //  </editor-fold>
    
    //------------------------------ ALMACENAR ELIXIR ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="almacenarElixir">
    public void almacenarElixir(double almacenado){
        elixir = almacenado;
    }
    //  </editor-fold>
    
    //------------------------------ GASTAR ORO ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="gastarOro">
    public void gastarOro(double gasto){
        oro -= gasto;
    }
    //  </editor-fold>
    
    //------------------------------ GASTAR ELIXIR ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="gastarElixir">
    public void gastarElixir(double gasto){
        elixir -= gasto;
    }
    //  </editor-fold>
    
    //------------------------------ OBTENER CONSTRUCTORES LIBRES ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="constructoresLibres">
    public int constructoresLibres(){
        int libres = 0;
        for(ClaseEdificio e: edificios){
            if(e.tipo == vg.CHOZA && e.constructorLibre())
                libres ++;
        }
        return libres;
    }
    //  </editor-fold>

    //------------------------------ LIBERAR CONSTRUCTOR ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="liberarConstructor">
    public void liberarConstructor(){
        for(ClaseEdificio e: edificios){
            if(e.tipo == vg.CHOZA && !e.constructorLibre()){
                e.liberarConstructor();
                break;
            }
        }
    }    
    //  </editor-fold>
    
    //------------------------------ BLOQUEAR CONSTRUCTOR ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="bloquearConstructor">
    public void bloquearConstructor(){
        for(ClaseEdificio e: edificios){
            if(e.tipo == vg.CHOZA && e.constructorLibre()){
                e.bloquearConstructor();
                break;
            }
        }
    }    
    //  </editor-fold>
    
    //------------------------------ CREAR EDIFICIO ---------------------------------
    // Crear edificio es al iniciar la aldea. No genera un evento futuro y es habilitado de una vez.
    // <editor-fold defaultstate="collapsed" desc="crearEdificio">
    public void crearEdificio(ClaseEdificio ed){
        // Crear objeto nuevo edificio
        ClaseEdificio edif = new ClaseEdificio(ed.tipo, ed.tipoCompra, ed.mejoras);
        
        //Liberar constructor si este edificio es una choza
        if(edif.tipo == vg.CHOZA)
            edif.liberarConstructor();
        
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
    public EventoFuturo construirEdificio(Date tiempo, ClaseEdificio ed){
        // Crear objeto nuevo edificio
        ClaseEdificio edif = new ClaseEdificio(ed.tipo, ed.tipoCompra, ed.mejoras);
        
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
        EventoFuturo evento = new EventoFuturo(calendar.getTime(), vg.EV_CULMINAR_EDIF, edif.getId());
        //System.out.println((calendar.getTime()));
        
        return evento;
    }   
    //  </editor-fold>
    
    //------------------------------ MEJORAR EDIFICIO ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="mejorarEdificio">
    public EventoFuturo mejorarEdificio(Date tiempo, ClaseEdificio ed){
        //Deshabilitar
        ed.deshabilitar();
        
        //Aumentar nivel
        ed.aumentarNivel();
        
        // Calcular tiempo de finalizado de mejora
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tiempo); // Configurar la fecha
        calendar.add(Calendar.SECOND, ed.mejoras[ed.nivel].tiempo);  // Sumar los minutos de tiempo
        
        System.out.println("Tiempo de fin de mejora: "+calendar.getTime());
        
        // Crear evento futuro
        EventoFuturo evento = new EventoFuturo(calendar.getTime(), vg.EV_CULMINAR_EDIF, ed.id);
        //System.out.println((calendar.getTime()));
        
        return evento;
    }
    //  </editor-fold>
    
    //------------------------------ OBTENER CAPACIDAD TROPAS ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="capacidadTropas">
    public int capacidadTropas(){
        int capacidad = 0;
        for(ClaseEdificio edificio : edificios){
            if(edificio.tipo == vg.CAMPAMENTO)
                capacidad += edificio.capacidadCola();
        }
        return capacidad;
    }
    //  </editor-fold>
    
    //------------------------------ HABILITAR TROPA ---------------------------------
    // Agregar tropa a la aldea luego de que haya sido construida.
    // Esta tropa debio haberse empezado a construir en Edificio.java, luego de el evento EV_CULMINAR_TROPA, 
    // la tropa se saca del edificio y agrega a la aldea
    // <editor-fold defaultstate="collapsed" desc="habilitarTropa">
    public void habilitarTropa(ClaseTropa tr){
        // Crear objeto de nueva tropa
        ClaseTropa tropa = new ClaseTropa(tr.tipo, tr.precio, tr.peso, tr.vida, tr.tasaDaño, tr.tiempo, tr.nivelCuartel);
        // Asignar id de tropa
        tropa.setId(tr.id);
        // Habilitarla
        tropa.habilitar();
        // Agregarla a las tropas de esta aldea
        tropas.add(tropa);
    }
    //  </editor-fold>
    
    //------------------------------ USAR TROPAS ---------------------------------
    // Usar tropas para "atacar"
    // <editor-fold defaultstate="collapsed" desc="usarTropas">
    public void usarTropas(ArrayList<Integer> cantidad, ArrayList<Integer> tipos, ClaseAldea enemigo){
        
    }    
    //  </editor-fold>
    
    //------------------------------ ¿ESTA DESTRUIDA LA ALDEA? ---------------------------------
    // Si algun edificio tiene mas de 0 de vida, la aldea NO esta destruida
    // <editor-fold defaultstate="collapsed" desc="destruida">
    public boolean destruida(){
        for(ClaseEdificio edificio : edificios){
            if(edificio.vidaActual > 0)
                return false;
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
    public ArrayList<ClaseEdificio> torres(){
        ArrayList<ClaseEdificio> torres = new ArrayList<ClaseEdificio>();
        for(ClaseEdificio ed : edificios){
            // Preguntar si edificio es una torre
            if(ed.tipo == vg.TORRE || ed.tipo == vg.CAÑON || ed.tipo == vg.MORTERO)
                torres.add(ed);
        }
        return torres;
    }
    //  </editor-fold>
}
