package simulacion.grafica;

public class itemMejorarEdificio {

    public int id, nivel, precio;
    public String nombre;

    public itemMejorarEdificio(int i, int ni, int pr, String no) {
        id = i;
        nivel = ni;
        precio = pr;
        nombre = no;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}    
