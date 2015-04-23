package Civilizacion;

import java.util.Date;

public class Mejora {
    
    //------------------------------ VARIABLES ---------------------------------
    // <editor-fold desc="Variables">
    // costo: costo de compra o mejora
    // tiempo: cuanto tarda en construirse o mejorarse (en segundos)
    // vida: que tanta defensa tiene
    // capacidad: capacidad de cola en caso de ser cuartel. Capacidad de espacio en caso de ser campamento.
    // Capacidad maxima del recolector. Capacidad maxima de oro y elixir para el ayuntamiento.
    public int precio, vida, capacidad;
    public int tiempo;
    // tasa: recursos recolectados por unid de tiempo si es recolector o mina. Daño por unid de tiempo si es edif de defensa
    public double tasa;
    //  </editor-fold>
    
    //------------------------------ CONSTRUCTOR ---------------------------------
    // <editor-fold desc="Constructor">
    public Mejora(int pr, int segundos, int vi, int ca){
        precio = pr;
        tiempo = segundos;
        vida = vi;
        capacidad = ca;
    }
    //  </editor-fold>
    
    //------------------------------ CONSTRUCTOR RECOLECTORES ---------------------------------
    // <editor-fold desc="Constructor2">
    public Mejora(int pr, int segundos, int vi, int ca, double ta){
        precio = pr;
        tiempo = segundos;
        vida = vi;
        capacidad = ca;
        tasa = ta;
        //System.out.println("tasa: "+tasa);
    }
    //  </editor-fold>
    
}
