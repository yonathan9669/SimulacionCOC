package Civilizacion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ClaseEdificio {
    //------------------------------ VARIABLES ---------------------------------
    // <editor-fold desc="Variables">
    // tipoCompra: Si el edificio se compra con "oro" o "elixir"
    // tipo: Cada valor entero es un edificio distinto
    // nivel: Nivel actual del edificio
    // mejoras: Vector en donde cada posicion tiene un precio de mejora para cambiar al siguiente nivel
    // cantidadRecurso: Cantidad de oro o elixir en un edif recolector
    // alcance: Cuantos cuadros tiene de alcance (para los edif de defensa)
    // vidaActual:
    public String tipoCompra;
    public int tipo, nivel, alcance;
    public double cantidadRecurso;
    public Mejora mejoras[];
    public int vidaActual;
    // habilitado: Cuando el edificio esta en construccion o siendo mejorado esta deshabilitado
    public boolean habilitado;
    // id: Identificador del edificio. Para poderlo identificar en la LEF y cuando hayan tropas atacandolo
    public int id;
    // idObjetivo: Identificador de la tropa a la que esta atacando
    public int idObjetivo;
    // colaTropas: Unidades en cola en un cuartel
    public ArrayList<ClaseTropa> colaTropas;
    // constructorLibre: Valido solo para chozas porque por cada choza hay un constructor
    private boolean constructorLibre;
    //  </editor-fold>
    
    //------------------------------ CONSTRUCTOR ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public ClaseEdificio(int ti, String tiCo, Mejora me[]){
        nivel = 0;
        tipo = ti;
        tipoCompra = tiCo;
        mejoras = new Mejora[me.length];
        for(int i=0;i<me.length;i++){
            if(tipo == vg.MINA || tipo == vg.RECOLECTOR)
                mejoras[i] = new Mejora(me[i].precio, me[i].tiempo, me[i].vida, me[i].capacidad, me[i].tasa);
            else
                mejoras[i] = new Mejora(me[i].precio, me[i].tiempo, me[i].vida, me[i].capacidad);
        }
        colaTropas = new ArrayList<>();
        vidaActual = mejoras[0].vida;
        constructorLibre = false;
        habilitado = false;
        cantidadRecurso = 0;
    }
    //  </editor-fold>
    
    //------------------------------ ESTABLECER ID ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="setId">
    public void setId(int i){
        id = i;
    }     
    //  </editor-fold>
    
    //------------------------------ OBTENER ID ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="getId">
    public int getId(){
        return id;
    }     
    //  </editor-fold>
    
    //------------------------------ HABILITAR ---------------------------------
    // Habilitar el edificio justo despues de ser construido o mejorado
    // <editor-fold defaultstate="collapsed" desc="Habilitar">
    public void habilitar(){
        habilitado = true;
    }     
    //  </editor-fold>
    
    //------------------------------ DESHABILITAR ---------------------------------
    // Deshabilitar el edificio
    // <editor-fold defaultstate="collapsed" desc="Deshabilitar">
    public final void deshabilitar(){
        habilitado = false;
    }     
    //  </editor-fold>
    
    //------------------------------ ¿ESTA HABILITADO? ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="estaHabilitado">
    public boolean estaHabilitado(){
        return habilitado;
    }    
    //  </editor-fold>    
    
    //------------------------------ ¿ES GENERADOR DE RECURSOS? ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="generadorRecursos">
    public boolean generadorRecursos(){
        if ((tipo == vg.MINA) || (tipo == vg.RECOLECTOR))
            return true;
        else
            return false;
    }    
    //  </editor-fold>
    
    //------------------------------ ACTUALIZAR RECURSOS ---------------------------------
    // Recoger recursos para pasarlos al edificio
    // <editor-fold defaultstate="collapsed" desc="actualizarRecursos">
    public void actualizarRecursos(double transcurrido){
        cantidadRecurso += mejoras[nivel].tasa * transcurrido;
        if(cantidadRecurso > mejoras[nivel].capacidad)
            cantidadRecurso = mejoras[nivel].capacidad;
        //System.out.println(mejoras[nivel].tasa+" * "+transcurrido+" = "+(mejoras[nivel].tasa*transcurrido));
        //System.out.println("Cantidad de "+tipoRecurso()+": "+cantidadRecurso);
    }    
    //  </editor-fold>    
    
    //------------------------------ RECOGER RECURSOS ---------------------------------
    // Recoger los recursos del edificio para pasarlos a la aldea
    // El edificio termina con 0 recursos
    // <editor-fold defaultstate="collapsed" desc="recogerRecursos">
    public double recogerRecursos(){
        double cantidad = cantidadRecurso;
        cantidadRecurso = 0;
        return cantidad;
    }    
    //  </editor-fold>
    
    //------------------------------ OBTENER CAPACIDAD RECURSO ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="capacidadRecurso">
    public int capacidadRecurso(){
        return mejoras[nivel].capacidad;
    }     
    //  </editor-fold>
    
    //------------------------------ TIPO DE RECURSO ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="tipoRecurso">
    public String tipoRecurso(){
        if (tipo == vg.MINA)
            return "oro";
        else 
            return "elixir";
    }    
    //  </editor-fold>
    
    //------------------------------ OBTENER PRECIO DE COMPRA O MEJORA ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="precioCompraMejora">
    public int precioCompraMejora(){
        return mejoras[nivel].precio;
    }    
    //  </editor-fold>
    
    //------------------------------ ¿GENERA TROPAS? ---------------------------------
    // Saber si este edificio genera tropas
    // <editor-fold defaultstate="collapsed" desc="generaTropas">
    public boolean generaTropas(){
        if(tipo == vg.CUARTEL)
            return true;
        else
            return false;
    }    
    //  </editor-fold>
    
    //------------------------------ ¿TIPO DE TROPA DISPONIBLE? ---------------------------------
    // Saber si el tipo de tropa que se recibe por parametros esta disponible en este cuartel
    // (Si no esta disponible, el cuartel necesita subir de nivel)
    // <editor-fold defaultstate="collapsed" desc="disponible">
    public boolean disponible(ClaseTropa tropa){
        if( (tipo == vg.CUARTEL) && (nivel >= tropa.nivelCuartel) )
            return true;
        else
            return false;
    }    
    //  </editor-fold>
    
    //------------------------------ OBTENER CAPACIDAD DE COLA ---------------------------------
    // Valido solo para cuarteles y campamentos
    // <editor-fold defaultstate="collapsed" desc="capacidadCola">
    public int capacidadCola(){
        if(tipo == vg.CUARTEL || tipo == vg.CAMPAMENTO)
            return mejoras[nivel].capacidad;
        else
            return 0;
    }    
    //  </editor-fold>
    
    //------------------------------ CONSTRUIR TROPA ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="construirTropa">
    public EventoFuturo construirTropa(Date tiempo, ClaseTropa tr, int id){
        // Crear objeto de nueva tropa
        ClaseTropa tropa = new ClaseTropa(tr.tipo, tr.precio, tr.peso, tr.vida, tr.tasaDaño, tr.tiempo, tr.nivelCuartel);
        
        // Establecer id
        tropa.setId(id);
        
        // Agregar tropa a la cola de este cuartel
        colaTropas.add(tropa);
        
        // Calcular tiempo de finalizado de construccion
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tiempo); // Configurar la fecha
        calendar.add(Calendar.SECOND, tropa.tiempo);  // Sumar los segundos de tiempo
        
        // Crear evento futuro
        EventoFuturo evento = new EventoFuturo(calendar.getTime(), vg.EV_CULMINAR_TROPA, id);
        
        // Imprimir tiempo de finalizado de construccion
        //System.out.println((calendar.getTime()));
        
        return evento;
    }
    //  </editor-fold>
    
    //------------------------------ LIBERAR COLA ---------------------------------
    // Valido solo para cuarteles
    // Devolver la primera tropa encolada
    // <editor-fold defaultstate="collapsed" desc="liberarCola">
    public ClaseTropa liberarCola(){
        return colaTropas.remove(0);
    }    
    //  </editor-fold>
    
    //------------------------------ ATACAR ---------------------------------
    // Valido solo para torres
    // <editor-fold defaultstate="collapsed" desc="Atacar">
    public void atacar(ClaseTropa tropa){
        
    }
    //  </editor-fold>
    
    //------------------------------ LIBERAR CONSTRUCTOR ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="LiberarConstructor">
    public void liberarConstructor(){
        constructorLibre = true;
    }     
    //  </editor-fold>
    
    //------------------------------ BLOQUEAR CONSTRUCTOR ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="BloquearConstructor">
    public final void bloquearConstructor(){
        constructorLibre = false;
    }     
    //  </editor-fold>
    
    //------------------------------ ¿ESTA LIBRE EL CONSTRUCTOR? ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="constructorLibre">
    public boolean constructorLibre(){
        return constructorLibre;
    }    
    //  </editor-fold>
    
    //------------------------------ AUMENTAR NIVEL ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="aumentarNivel">
    public void aumentarNivel(){
        nivel++;
    }     
    //  </editor-fold>
    
    //------------------------------ DEVOLVER NOMBRE DE EDIFICIO ---------------------------------
    // Usado para imprimir
    // <editor-fold defaultstate="collapsed" desc="getNombre">
    public String getNombre(){
        if(tipo == vg.AYUNTAMIENTO)
            return "Ayuntamiento";
        else if(tipo == vg.CHOZA)
            return "Choza de Constructor";
        else if(tipo == vg.CAMPAMENTO)
            return "Campamento";
        else if(tipo == vg.CUARTEL)
            return "Cuartel";
        else if(tipo == vg.MINA)
            return "Mina de Oro";
        else if(tipo == vg.RECOLECTOR)
            return "Recolector de Elixir";
        else if(tipo == vg.TORRE)
            return "Torre de Arqueras";
        else if(tipo == vg.CAÑON)
            return "Cañón";
        else if(tipo == vg.MORTERO)
            return "Mortero";
        return "";
    }     
    //  </editor-fold>
}
