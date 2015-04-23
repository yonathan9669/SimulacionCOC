
package Civilizacion;

public class ClaseTropa {
    
    //------------------------------ VARIABLES ---------------------------------
    // <editor-fold desc="Variables">
    // tipo: Cada valor entero es una tropa distinta
    // precio: Costo de la tropa en elixir
    // peso: Cuanto espacio ocupa la tropa en el campamento
    // posicion: Posicion respecto a ¿la matriz de la aldea?
    public int tipo, precio, peso, posicion;
    // vida: Vida máxima de la tropa
    // tasaDaño: Daño por segundo
    // tiempo: Cuanto tarda en construirse (en segundos)
    // vidaActual: Vida actual de la tropa
    public int vida, tasaDaño, tiempo, vidaActual;
    // habilitada: Cuando la tropa esta en construccion esta deshabilitada
    // atacando: 
    // nivelCuartel: Nivel de cuartel requerido para entrenar la tropa
    public boolean habilitada, atacando;
    public int nivelCuartel;
    // id: Identificador de la tropa. Para poderlo identificar en la LEF y cuando haya un edif atacandolo
    public int id;
    // idObjetivo: Identificador del edificio al que esta atacando
    public int idObjetivo;
    //  </editor-fold>
    
    //------------------------------ CONSTRUCTOR ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public ClaseTropa(int tip, int pr, int pe, int vi, int taDa, int segundos, int niCu){
        tipo = tip;
        precio = pr;
        peso = pe;
        vida = vi;
        vidaActual = vida;
        tasaDaño = taDa;
        tiempo = segundos;
        nivelCuartel = niCu;
        atacando = false;
        habilitada = false;
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
    // Habilitar la tropa justo despues de ser construida
    // <editor-fold defaultstate="collapsed" desc="Habilitar">
    public void habilitar(){
        habilitada = true;
    }     
    //  </editor-fold>
    
    //------------------------------ DESHABILITAR ---------------------------------
    // Habilitar la tropa justo despues de ser construida
    // <editor-fold defaultstate="collapsed" desc="Habilitar">
    public void deshabilitar(){
        habilitada = false;
    }
    //  </editor-fold>
    
    //------------------------------ ATACAR ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="Atacar">
    public void atacar(ClaseEdificio edificio){
        
    }
    //  </editor-fold>
    
    //------------------------------ ¿ESTA ATACANDO? ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="Atacando">
    public boolean atacando(){
        return atacando;
    }
    //  </editor-fold>
    
    //------------------------------ DEVOLVER NOMBRE DE TROPA ---------------------------------
    // Usado para imprimir
    // <editor-fold defaultstate="collapsed" desc="getNombre">
    public String getNombre(){
        if(tipo == vg.BARBARO)
            return "Bárbaro";
        else if(tipo == vg.ARQUERA)
            return "Arquera";
        else if(tipo == vg.GIGANTE)
            return "Gigante";
        else if(tipo == vg.DUENDE)
            return "Duende";
        return "";
    }     
    //  </editor-fold>
}
