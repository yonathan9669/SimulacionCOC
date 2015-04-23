package Civilizacion;

import java.util.Date;

// Los eventos de aqui son los que ocurren al pasar un tiempo
// Son llamados por otra clase y puestos en una lista de eventos futuros (LEF)
public class EventoFuturo {
    //------------------------------ VARIABLES ---------------------------------
    // <editor-fold desc="Variables">
    // tiempo: tiempo en que ocurrira el envento
    // tipo: evento que ocurrira (culminar tropa, culminar edificio, etc)
    // id: para saber cual tropa o edificio
    public Date tiempo;
    public int tipo, id;
    
    //  </editor-fold>
    
    //------------------------------ CONSTRUCTOR ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public EventoFuturo(Date tie, int tip, int i){
        tiempo = tie;
        tipo = tip;
        id = i;
    }
    //  </editor-fold>    
}
