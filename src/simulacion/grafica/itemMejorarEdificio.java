package simulacion.grafica;

public class itemMejorarEdificio {

    public int id, nivel, precio;
    public String nombre, tipoCompra;

    public itemMejorarEdificio(int i, int ni, int pr, String no, String tiCo) {
        id = i;
        nivel = ni;
        precio = pr;
        nombre = no;
        tipoCompra = tiCo;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}    
