/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion.grafica;

import javax.swing.ImageIcon;
import Civilizacion.*;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import simulacion.util.Ataque;

/**
 *
 * @author gaby
 */
public class frameAldea extends javax.swing.JFrame {

    //------------------------------ VARIABLES DEL JUEGO ---------------------------------
    // <editor-fold desc="Variables">
    // edificios[] y tropas[]: Son los objetos generales de edificios y tropas.
    private Edificio edificios[] = new Edificio[9];
    private Tropa tropas[] = new Tropa[4];
    // aldea: Mi aldea
    public Aldea aldea;
    public Ataque ataque;
    // hiloEstadoAldea: Actualiza el estado dela aldea (minas, recolectores, construcciones, etc)
    private Thread hiloEstadoAldea;
    private Thread Vida;

    // tiempoActual:
    private Date tiempoActual;
    private Random rand;
    //  </editor-fold>
    private final long MILISEGS_POR_DIA = 24 * 60 * 60 * 1000; //Milisegundos al día 
    // LEF: Lista de Eventos Futuros
    ArrayList<EventoFuturo> LEF;

    public frameAldea() {
        this.rand = new Random(System.currentTimeMillis());
        initComponents();
        ubicacion_labels();
        iniciarObjTropas();
        iniciarObjEdificios();

    }

    void ubicacion_labels() {

        javax.swing.border.Border border = BorderFactory.createLineBorder(Color.red);
        javax.swing.border.Border border2 = BorderFactory.createLineBorder(Color.green);

        this.e1.setBorder(border);
        this.e2.setBorder(border);
        this.e3.setBorder(border);
        this.e4.setBorder(border);
        this.timef.setBorder(border2);

        this.jLabel21.setIcon(new ImageIcon(getClass().
                getResource("Barbarian.png")));

        this.jLabel22.setIcon(new ImageIcon(getClass().
                getResource("Archer.png")));

        this.jLabel23.setIcon(new ImageIcon(getClass().
                getResource("Giant.png")));

        this.jLabel25.setIcon(new ImageIcon(getClass().
                getResource("Goblin.png")));

        this.jLabel3.setIcon(new ImageIcon(getClass().
                getResource("logo.png")));

        this.jLabel24.setIcon(new ImageIcon(getClass().
                getResource("ayuntamiento.png")));

        this.jLabel14.setIcon(new ImageIcon(getClass().
                getResource("Barbarian.png")));

        this.jLabel15.setIcon(new ImageIcon(getClass().
                getResource("Archer.png")));

        this.jLabel16.setIcon(new ImageIcon(getClass().
                getResource("Giant.png")));

        this.jLabel17.setIcon(new ImageIcon(getClass().
                getResource("Goblin.png")));

        this.jLabel5.setIcon(new ImageIcon(getClass().
                getResource("Cabaconstructor.png")));

        this.jLabel6.setIcon(new ImageIcon(getClass().
                getResource("Minaoro.png")));

        this.jLabel7.setIcon(new ImageIcon(getClass().
                getResource("RecolorectorElixir.png")));

        this.jLabel8.setIcon(new ImageIcon(getClass().
                getResource("ArmyCamp.png")));

        this.jLabel9.setIcon(new ImageIcon(getClass().
                getResource("Canon.png")));

        this.jLabel10.setIcon(new ImageIcon(getClass().
                getResource("mortero.png")));

        this.jLabel11.setIcon(new ImageIcon(getClass().
                getResource("ArcherTower.png")));

        this.jLabel12.setIcon(new ImageIcon(getClass().
                getResource("barracks.png")));
    }

    //------------------------------ INICIAR OBJETOS TROPAS ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="iniciarObjTropas">
    private void iniciarObjTropas() {

        // Barbaros
        int tipo = vg.BARBARO;
        int precio = 25;
        int peso = 1;
        int vida = 45;
        int tasaDaño = 8;
        int tiempo = 5;
        int nivelCuartel = 0;
        tropas[tipo] = new Tropa(tipo, precio, peso, vida, tasaDaño, tiempo, nivelCuartel);

        // Arqueras
        tipo = vg.ARQUERA;
        precio = 50;
        peso = 1;
        vida = 22;
        tasaDaño = 7;
        tiempo = 6;
        nivelCuartel = 1;
        tropas[tipo] = new Tropa(tipo, precio, peso, vida, tasaDaño, tiempo, nivelCuartel);

        // Gigantes
        tipo = vg.GIGANTE;
        precio = 250;
        peso = 5;
        vida = 300;
        tasaDaño = 11;
        tiempo = 10;
        nivelCuartel = 2;
        tropas[tipo] = new Tropa(tipo, precio, peso, vida, tasaDaño, tiempo, nivelCuartel);

        // Duendes
        tipo = vg.DUENDE;
        precio = 25;
        peso = 1;
        vida = 25;
        tasaDaño = 11;
        tiempo = 7;
        nivelCuartel = 3;
        tropas[tipo] = new Tropa(tipo, precio, peso, vida, tasaDaño, tiempo, nivelCuartel);
    }
    //</editor-fold>

    //------------------------------ INICIAR OBJETOS EDIFICIOS ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="iniciarObjEdificios">
    private void iniciarObjEdificios() {

        // Ayuntamiento
        int tipo = vg.AYUNTAMIENTO;
        Point posicion = new Point(-1, -1);
        String tipoCompra = "oro";
        Mejora mejAy[] = new Mejora[vg.NIVELES];
        //precio,tiempo(segundos),vida,capacidad,tasa
        mejAy[0] = new Mejora(1000, 10, 1500, 10000);
        mejAy[1] = new Mejora(4000, 20, 1600, 20000);
        mejAy[2] = new Mejora(8000, 30, 1850, 30000);
        mejAy[3] = new Mejora(15000, 40, 2100, 40000);
        mejAy[4] = new Mejora(250000, 50, 2400, 50000);
        edificios[tipo] = new Edificio(tipo, tipoCompra, mejAy, posicion);

        // Choza de Constructor
        tipo = vg.CHOZA;
        tipoCompra = "elixir";
        Mejora mejCh[] = new Mejora[1];
        mejCh[0] = new Mejora(200, 10, 250, 0);
        edificios[tipo] = new Edificio(tipo, tipoCompra, mejCh, posicion);

        // Campamento
        tipo = vg.CAMPAMENTO;
        tipoCompra = "elixir";
        Mejora mejCa[] = new Mejora[vg.NIVELES];
        mejCa[0] = new Mejora(250, 10, 400, 20);
        mejCa[1] = new Mejora(2500, 20, 500, 30);
        mejCa[2] = new Mejora(10000, 30, 600, 35);
        mejCa[3] = new Mejora(100000, 40, 700, 40);
        mejCa[4] = new Mejora(250000, 50, 800, 45);
        edificios[tipo] = new Edificio(tipo, tipoCompra, mejCa, posicion);

        // Cuartel
        tipo = vg.CUARTEL;
        tipoCompra = "elixir";
        Mejora mejCu[] = new Mejora[vg.NIVELES];
        mejCu[0] = new Mejora(200, 10, 250, 20);
        mejCu[1] = new Mejora(1000, 20, 290, 30);
        mejCu[2] = new Mejora(2500, 30, 330, 35);
        mejCu[3] = new Mejora(5000, 40, 370, 40);
        mejCu[4] = new Mejora(1000, 50, 410, 45);
        edificios[tipo] = new Edificio(tipo, tipoCompra, mejCu, posicion);

        // Mina de Oro
        tipo = vg.MINA;
        tipoCompra = "elixir";
        Mejora mejMi[] = new Mejora[vg.NIVELES];
        mejMi[0] = new Mejora(150, 10, 400, 50, 2);
        mejMi[1] = new Mejora(300, 20, 450, 1000, 4);
        mejMi[2] = new Mejora(700, 30, 500, 1500, 8);
        mejMi[3] = new Mejora(1400, 40, 550, 2500, 16);
        mejMi[4] = new Mejora(3000, 50, 590, 10000, 32);
        edificios[tipo] = new Edificio(tipo, tipoCompra, mejMi, posicion);

        // Recolector de Elixir
        tipo = vg.RECOLECTOR;
        tipoCompra = "oro";
        Mejora mejRe[] = new Mejora[vg.NIVELES];
        mejRe[0] = new Mejora(150, 10, 400, 50, 2);
        mejRe[1] = new Mejora(300, 20, 450, 1000, 4);
        mejRe[2] = new Mejora(700, 30, 500, 1500, 8);
        mejRe[3] = new Mejora(1400, 40, 550, 2500, 16);
        mejRe[4] = new Mejora(3000, 50, 590, 10000, 32);
        edificios[tipo] = new Edificio(tipo, tipoCompra, mejRe, posicion);

        // Torre de Arqueras
        tipo = vg.TORRE;
        tipoCompra = "oro";
        Mejora mejTo[] = new Mejora[vg.NIVELES];
        mejTo[4] = new Mejora(1000, 10, 380, 0, 11);
        mejTo[1] = new Mejora(2000, 20, 420, 0, 15);
        mejTo[2] = new Mejora(5000, 30, 460, 0, 19);
        mejTo[3] = new Mejora(20000, 40, 500, 0, 25);
        mejTo[0] = new Mejora(80000, 50, 540, 0, 30);
        edificios[tipo] = new Edificio(tipo, tipoCompra, mejTo, posicion);

        // Cañon
        tipo = vg.CAÑON;
        tipoCompra = "oro";
        Mejora mejCañ[] = new Mejora[vg.NIVELES];
        mejCañ[4] = new Mejora(250, 10, 420, 0, 9);
        mejCañ[1] = new Mejora(1000, 20, 470, 0, 11);
        mejCañ[2] = new Mejora(4000, 30, 520, 0, 15);
        mejCañ[3] = new Mejora(16000, 40, 570, 0, 19);
        mejCañ[0] = new Mejora(50000, 50, 620, 0, 25);
        edificios[tipo] = new Edificio(tipo, tipoCompra, mejCañ, posicion);

        // Mortero
        tipo = vg.MORTERO;
        tipoCompra = "oro";
        Mejora mejMo[] = new Mejora[vg.NIVELES];
        mejMo[4] = new Mejora(8000, 10, 400, 0, 15);
        mejMo[1] = new Mejora(32000, 20, 450, 0, 19);
        mejMo[2] = new Mejora(120000, 30, 500, 0, 25);
        mejMo[3] = new Mejora(400000, 40, 550, 0, 30);
        mejMo[0] = new Mejora(800000, 50, 600, 0, 35);
        edificios[tipo] = new Edificio(tipo, tipoCompra, mejMo, posicion);
    }
    //  </editor-fold>

    //------------------------------ ALDEAPPAL ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="aldeappal">
    void aldeappal(frameInicio obj) {
        //--------------- Crear aldea ---------------
        double orInicial = 500;
        double elInicial = 500;
        aldea = new Aldea(orInicial, elInicial);

        //--------------- Iniciar Labels ---------------
        this.jTextFieldChoza.setText(obj.choza.getText());
        this.jTextFieldMina.setText(obj.minaORO.getText());
        this.jTextFieldRecolector.setText(obj.rec_elx.getText());
        this.jTextFieldCampamento.setText(obj.campamento.getText());
        this.jTextFieldCañon.setText(obj.canon.getText());
        this.jTextFieldMortero.setText(obj.mortero.getText());
        this.jTextFieldTorre.setText(obj.torre_arq.getText());
        this.jTextFieldCuartel.setText(obj.cuartel.getText());
        this.jTextFieldAyuntamiento.setText(obj.ayunta.getText());
        this.jTextFieldBarbaro.setText("0");
        this.jTextFieldArquera.setText("0");
        this.jTextFieldGigante.setText("0");
        this.jTextFieldDuende.setText("0");
        this.jTextFieldOroMina.setText("0");
        this.jTextFieldElixRec.setText("0");
        this.jTextFieldOro.setText(String.valueOf((int) orInicial));
        this.jTextFieldElixir.setText(String.valueOf((int) elInicial));

        //--------------- Iniciar LEF ---------------
        LEF = new ArrayList<>();

        //--------------- Crear edificios ---------------
        // Crear ayuntamiento:
        for (int i = 0; i < Integer.parseInt(obj.ayunta.getText()); i++) {
            aldea.crearEdificio(edificios[vg.AYUNTAMIENTO]);
        }
        // Crear chozas de constructor:
        for (int i = 0; i < Integer.parseInt(obj.choza.getText()); i++) {
            aldea.crearEdificio(edificios[vg.CHOZA]);
        }
        // Crear campamentos:
        for (int i = 0; i < Integer.parseInt(obj.campamento.getText()); i++) {
            aldea.crearEdificio(edificios[vg.CAMPAMENTO]);
        }
        // Crear cuarteles:
        for (int i = 0; i < Integer.parseInt(obj.cuartel.getText()); i++) {
            aldea.crearEdificio(edificios[vg.CUARTEL]);
        }
        // Crear minas de oro:
        for (int i = 0; i < Integer.parseInt(obj.minaORO.getText()); i++) {
            aldea.crearEdificio(edificios[vg.MINA]);
        }
        // Crear recolectores de elixir:
        for (int i = 0; i < Integer.parseInt(obj.rec_elx.getText()); i++) {
            aldea.crearEdificio(edificios[vg.RECOLECTOR]);
        }
        // Crear torres de arqueras:
        for (int i = 0; i < Integer.parseInt(obj.torre_arq.getText()); i++) {
            aldea.crearEdificio(edificios[vg.TORRE]);
        }
        // Crear cañones:
        for (int i = 0; i < Integer.parseInt(obj.canon.getText()); i++) {
            aldea.crearEdificio(edificios[vg.CAÑON]);
        }
        // Crear morteros:
        for (int i = 0; i < Integer.parseInt(obj.mortero.getText()); i++) {
            aldea.crearEdificio(edificios[vg.MORTERO]);
        }

        tiempoActual = new Date();

        //--------------- Iniciar Hilo General del Estado de la Aldea ---------------
        hiloEstadoAldea = new Thread() {
            public void run() {
                while (true) {
                    actualizarEstado();
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        };
        hiloEstadoAldea.start();

    }
    //  </editor-fold>

    //------------------------------ ACTUALIZAR ESTADO ---------------------------------
    // Actualizar el estado de la aldea.
    // <editor-fold defaultstate="collapsed" desc="actualizarEstado">
    private void actualizarEstado() {
        //--------------- Calcular tiempo transcurrido ---------------

        Date tiempoAnterior = tiempoActual;
        tiempoActual = new Date();
        double transcurridoAux = (tiempoActual.getTime() - tiempoAnterior.getTime());
        double transcurrido = transcurridoAux / 1000.0;

        boolean puedeCrearTropas = false;
        boolean puedeRecogerRecursos = false;
        //--------------- Recorrer edificios ---------------
        for (Edificio e : aldea.edificios) {

            // Actualizar minas y recolectores
            if (e.generadorRecursos() && e.estaHabilitado()) {
                e.actualizarRecursos(transcurrido);
                puedeRecogerRecursos = true;
                if (e.tipo == vg.MINA) {
                    jTextFieldOroMina.setText(String.valueOf(e.cantidadRecurso));
                } else {
                    jTextFieldElixRec.setText(String.valueOf(e.cantidadRecurso));
                }
            }

            // Verificar si hay un cuartel habilitado para poder crear tropas
            if (e.tipo == vg.CUARTEL && e.habilitado) {
                puedeCrearTropas = true;
            }

        }

        // Habilitar/deshabilitar boton crear tropas
        jButtonCrearTropa.setEnabled(puedeCrearTropas);

        // Habilitar/deshabilitar boton recoger recursos
        jButtonRecogerRecursos.setEnabled(puedeRecogerRecursos);

        // Verificar si hay constuctores libres para permitir la creacion de tropas
        if (aldea.constructoresLibres() > 0) {
            jButtonCrearEdificio.setEnabled(true);
            jButtonMejorarEdificio.setEnabled(true);
        } else {
            jButtonCrearEdificio.setEnabled(false);
            jButtonMejorarEdificio.setEnabled(false);
        }

        //--------------- Revisar LEF ---------------
        // Recorrer en sentido inverso
        for (int i = LEF.size() - 1; i >= 0; i--) {
            Date tiempoActual = new Date();
            // Si ocurre un evento
            if (tiempoActual.after(LEF.get(i).tiempo)) {
                // Si el evento es culminar edificio
                if (LEF.get(i).tipo == vg.EV_CULMINAR_EDIF) {
                    culminarEdificio(aldea.edificios.get(LEF.get(i).id));
                    LEF.remove(i); // Remover evento futuro de la lista
                } else if (LEF.get(i).tipo == vg.EV_CULMINAR_TROPA) {
                    boolean romperCiclo = false;
                    // Recorrer edificio para buscar tropa en colaTropas
                    for (Edificio e : aldea.edificios) {
                        // Buscar tropa

                        for (Tropa t : e.colaTropas) {
                            if (t.id == LEF.get(i).id) {
                                culminarTropa(e, t); // En este evento se agrega la tropa a la aldea
                                e.colaTropas.remove(t);// Remover tropa de colaTropas en edificio
                                LEF.remove(i); // Remover evento futuro de la lista
                                romperCiclo = true;
                                break;

                            }
                        }
                        if (romperCiclo) {
                            break;
                        }
                    }
                }
            }
        }

        inicial();
        try {
            Barravida();
        } catch (InstantiationException ex) {
            Logger.getLogger(frameAldea.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(frameAldea.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(frameAldea.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frameAldea.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //  </editor-fold>

    //------------------------------ COMPRAR EDIFICIO ---------------------------------
    // Verificar si se puede comprar el edificio seleccionado y comprarlo.
    // <editor-fold defaultstate="collapsed" desc="ComprarEdificio">
    public void ComprarEdificio(int tipo) {
        if (edificios[tipo].tipoCompra.equals("oro")) {
            if (aldea.oro >= edificios[tipo].precioCompraMejora()) {
                if (aldea.constructoresLibres() > 0) {
                    // Bloquear constructor
                    aldea.bloquearConstructor();
                    // Obtener tiempo actual
                    Date tiempo = new Date();
                    // Gastar oro de edificio
                    aldea.gastarOro(edificios[tipo].precioCompraMejora());
                    // Construir edificio y obtener evento futuro de culminacion
                    // Agregar evento a la LEF
                    LEF.add(aldea.construirEdificio(tiempo, edificios[tipo]));
                    jTextFieldOro.setText(String.valueOf((int) aldea.oro));
                }
            }
        } else {
            if (aldea.elixir >= edificios[tipo].precioCompraMejora()) {
                if (aldea.constructoresLibres() > 0) {
                    // Bloquear constructor
                    aldea.bloquearConstructor();
                    // Obtener tiempo actual
                    Date tiempo = new Date();
                    // Gastar elixir de edificio
                    aldea.gastarElixir(edificios[tipo].precioCompraMejora());
                    // Construir edificio y obtener evento futuro
                    // Agregar evento a la LEF
                    LEF.add(aldea.construirEdificio(tiempo, edificios[tipo]));
                    jTextFieldElixir.setText(String.valueOf((int) aldea.elixir));
                }
            }
        }
    }
    //  </editor-fold>

    //------------------------------ MEJORAR EDIFICIOS ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="MejorarEdificio">
    public void MejorarEdificio(Edificio edificio) {
        if (edificio.tipoCompra.equals("oro")) {
            if (aldea.oro >= edificio.precioCompraMejora()) {
                if (aldea.constructoresLibres() > 0) {
                    // Bloquear constructor
                    aldea.bloquearConstructor();
                    // Obtener tiempo actual
                    Date tiempo = new Date();
                    // Gastar oro de edificio
                    aldea.gastarOro(edificio.precioCompraMejora());
                    // Mejorar edificio y obtener evento futuro de culminacion
                    // Agregar evento a la LEF
                    LEF.add(aldea.mejorarEdificio(tiempo, edificio));
                    jTextFieldOro.setText(String.valueOf((int) aldea.oro));
                }
            }
        } else {
            if (aldea.elixir >= edificio.precioCompraMejora()) {
                if (aldea.constructoresLibres() > 0) {
                    // Bloquear constructor
                    aldea.bloquearConstructor();
                    // Obtener tiempo actual
                    Date tiempo = new Date();
                    // Gastar elixir de edificio
                    aldea.gastarElixir(edificio.precioCompraMejora());
                    // Mejorar edificio y obtener evento futuro
                    // Agregar evento a la LEF
                    LEF.add(aldea.mejorarEdificio(tiempo, edificio));
                    jTextFieldElixir.setText(String.valueOf((int) aldea.elixir));
                }
            }
        }
    }
    //  </editor-fold>

    //------------------------------ COMPRAR TROPA ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="ComprarTropa">
    void ComprarTropa(int tipoTropa, Edificio edificio) {
        if (edificio.generaTropas()) {
            if (edificio.disponible(tropas[tipoTropa])) {
                if (aldea.elixir >= tropas[tipoTropa].precio) { // Este faltaba
                    if (aldea.capacidadTropas() - aldea.poblacion >= tropas[tipoTropa].peso) {
                        if (edificio.capacidadCola() >= tropas[tipoTropa].peso) {
                            // Obtener tiempo actual
                            Date tiempo = new Date();
                            // Gastar elixir de tropa
                            aldea.gastarElixir(tropas[tipoTropa].precio);
                            // Construir trpá y obtener evento futuro de culminacion
                            // Agregar evento a la LEF
                            LEF.add(edificio.construirTropa(tiempo, tropas[tipoTropa], aldea.getIdTropas()));
                            jTextFieldElixir.setText(String.valueOf((int) aldea.elixir));

                            // Aumentar tamaño de poblacion
                            aldea.poblacion += tropas[tipoTropa].peso;
                        }
                    }
                }
            }
        }
    }
    //  </editor-fold>

    //------------------------------ CULMINAR EDIFICIO ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="CulminarEdificio">
    private void culminarEdificio(Edificio edificio) {
        // Si el edificio es una choza, liberar constructor
        if (edificio.tipo == vg.CHOZA) {
            edificio.liberarConstructor();
        }
        // Habilitar edificio recien creado/modificado
        edificio.habilitar();
        // Liberar constructor que estaba creando/modificando el edificio
        aldea.liberarConstructor();
        // Verificar si el edificio es nuevo o estaba mejorando
        if (edificio.nivel == 0) {
            // Sumar edificio a su label respectivo
            sumarEdificio(edificio.tipo);
            // Mensaje de construccion finalizada
            JOptionPane.showMessageDialog(this, "Construccion de " + edificio.getNombre() + " finalizada", "", JOptionPane.WARNING_MESSAGE);
        } else {
            // Mensaje de mejora finalizada
            JOptionPane.showMessageDialog(this, "Mejora de " + edificio.getNombre() + " a nivel " + String.valueOf(edificio.nivel + 1) + " finalizada", "", JOptionPane.WARNING_MESSAGE);
        }
    }
    //  </editor-fold>

    //------------------------------ CULMINAR TROPA ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="CulminarTropa">
    private void culminarTropa(Edificio edificio, Tropa tropa) {
        System.out.println("Culminar tropa " + tropa.id);
        // Agregar tropa a la aldea
        aldea.habilitarTropa(tropa);
        // Sumar tropa al label respectivo
        sumarTropa(tropa.tipo);
    }
    //  </editor-fold>

    //------------------------------ SUMAR EDIFICIO A LABEL ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="sumarEdificio">
    private void sumarEdificio(int tipo) {
        int cantActual;
        //Buscar tipo
        switch (tipo) {
            case vg.CHOZA:
                cantActual = Integer.parseInt(jTextFieldChoza.getText());
                jTextFieldChoza.setText(String.valueOf(cantActual + 1));
                break;
            case vg.CAMPAMENTO:
                cantActual = Integer.parseInt(jTextFieldCampamento.getText());
                jTextFieldCampamento.setText(String.valueOf(cantActual + 1));
                break;
            case vg.CUARTEL:
                cantActual = Integer.parseInt(jTextFieldCuartel.getText());
                jTextFieldCuartel.setText(String.valueOf(cantActual + 1));
                break;
            case vg.MINA:
                cantActual = Integer.parseInt(jTextFieldMina.getText());
                jTextFieldMina.setText(String.valueOf(cantActual + 1));
                break;
            case vg.RECOLECTOR:
                cantActual = Integer.parseInt(jTextFieldRecolector.getText());
                jTextFieldRecolector.setText(String.valueOf(cantActual + 1));
                break;
            case vg.TORRE:
                cantActual = Integer.parseInt(jTextFieldTorre.getText());
                jTextFieldTorre.setText(String.valueOf(cantActual + 1));
                break;
            case vg.CAÑON:
                cantActual = Integer.parseInt(jTextFieldCañon.getText());
                jTextFieldCañon.setText(String.valueOf(cantActual + 1));
                break;
            case vg.MORTERO:
                cantActual = Integer.parseInt(jTextFieldMortero.getText());
                jTextFieldMortero.setText(String.valueOf(cantActual + 1));
                break;
            default:
                break;
        }
    }
    //  </editor-fold>

    //------------------------------ SUMAR TROPA A LABEL ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="sumarTropa">
    private void sumarTropa(int tipo) {
        int cantActual;
        //Buscar tipo
        switch (tipo) {
            case vg.BARBARO:
                cantActual = Integer.parseInt(jTextFieldBarbaro.getText());
                jTextFieldBarbaro.setText(String.valueOf(cantActual + 1));
                break;
            case vg.ARQUERA:
                cantActual = Integer.parseInt(jTextFieldArquera.getText());
                jTextFieldArquera.setText(String.valueOf(cantActual + 1));
                break;
            case vg.GIGANTE:
                cantActual = Integer.parseInt(jTextFieldGigante.getText());
                jTextFieldGigante.setText(String.valueOf(cantActual + 1));
                break;
            case vg.DUENDE:
                cantActual = Integer.parseInt(jTextFieldDuende.getText());
                jTextFieldDuende.setText(String.valueOf(cantActual + 1));
                break;
        }
    }
    //  </editor-fold>

    //------------------------------ RECOGER RECURSOS ---------------------------------
    // <editor-fold defaultstate="collapsed" desc="RecogerRecursos">
    private void RecogerRecursos(Edificio edificio) {
        // Si es generarod re recursos y esta habilitado
        if (edificio.generadorRecursos() && edificio.estaHabilitado()) {
            double cantidad = edificio.recogerRecursos();
            if (edificio.tipoRecurso().equals("oro")) {
                double almacenado = aldea.oro;
                almacenado += cantidad;
                almacenado = (aldea.capacidadOro() < almacenado) ? aldea.capacidadOro() : almacenado;
                //aldea.almacenar(almacenado);
                aldea.almacenarOro(almacenado);
            } else {
                double almacenado = aldea.elixir;
                almacenado += cantidad;
                almacenado = (aldea.capacidadElixir() < almacenado) ? aldea.capacidadElixir() : almacenado;
                //aldea.almacenar(almacenado);
                aldea.almacenarElixir(almacenado);
            }
        }
    }
    //  </editor-fold>

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldOro = new javax.swing.JTextField();
        jTextFieldElixir = new javax.swing.JTextField();
        jButtonCrearEdificio = new javax.swing.JButton();
        jButtonCrearTropa = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButtonMejorarEdificio = new javax.swing.JButton();
        jButtonRecogerRecursos = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldChoza = new javax.swing.JTextField();
        jTextFieldMina = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldRecolector = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldCampamento = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldCañon = new javax.swing.JTextField();
        jTextFieldMortero = new javax.swing.JTextField();
        jTextFieldTorre = new javax.swing.JTextField();
        jTextFieldCuartel = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTextFieldBarbaro = new javax.swing.JTextField();
        jTextFieldArquera = new javax.swing.JTextField();
        jTextFieldGigante = new javax.swing.JTextField();
        jTextFieldDuende = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        timef = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jTextFieldAyuntamiento = new javax.swing.JTextField();
        jTextFieldOroMina = new javax.swing.JTextField();
        jTextFieldElixRec = new javax.swing.JTextField();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel20 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        e1 = new javax.swing.JTextField();
        e2 = new javax.swing.JTextField();
        e3 = new javax.swing.JTextField();
        e4 = new javax.swing.JTextField();
        jProgressBar2 = new javax.swing.JProgressBar();
        jLabel26 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Oro");

        jLabel2.setText("Elixir");

        jTextFieldOro.setEnabled(false);

        jTextFieldElixir.setToolTipText("");
        jTextFieldElixir.setEnabled(false);

        jButtonCrearEdificio.setText("Crear Edificio");
        jButtonCrearEdificio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrearEdificioActionPerformed(evt);
            }
        });

        jButtonCrearTropa.setText("Crear Tropa");
        jButtonCrearTropa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrearTropaActionPerformed(evt);
            }
        });

        jButton3.setText("Realizar Ataque");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButtonMejorarEdificio.setText("Mejorar Edificio");
        jButtonMejorarEdificio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMejorarEdificioActionPerformed(evt);
            }
        });

        jButtonRecogerRecursos.setText("Recoger Recursos");
        jButtonRecogerRecursos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRecogerRecursosActionPerformed(evt);
            }
        });

        jButton9.setText("Recibir Ataque");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 102));
        jLabel4.setText("Mi Aldea");

        jTextFieldChoza.setEnabled(false);

        jTextFieldMina.setEnabled(false);

        jTextFieldRecolector.setEnabled(false);

        jTextFieldCampamento.setEnabled(false);

        jTextFieldCañon.setEnabled(false);

        jTextFieldMortero.setEnabled(false);

        jTextFieldTorre.setEnabled(false);

        jTextFieldCuartel.setEnabled(false);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Tropas");

        jTextFieldBarbaro.setEnabled(false);

        jTextFieldArquera.setEnabled(false);

        jTextFieldGigante.setEnabled(false);

        jTextFieldDuende.setEnabled(false);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 0, 0));
        jLabel18.setText("Tropas");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Tiempo");

        timef.setEnabled(false);

        jLabel24.setText("AYUNTAMIENTO");

        jTextFieldAyuntamiento.setEnabled(false);

        jTextFieldOroMina.setEnabled(false);
        jTextFieldOroMina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldOroMinaActionPerformed(evt);
            }
        });

        jTextFieldElixRec.setEnabled(false);

        jLabel20.setText("Vida");

        e1.setEnabled(false);

        e2.setEnabled(false);

        e3.setEnabled(false);

        e4.setEnabled(false);

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 0, 0));
        jLabel26.setText("Vida");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextFieldElixRec, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldOroMina, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextFieldRecolector, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldMina, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldChoza, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldCampamento, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(65, 65, 65)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextFieldMortero, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldCuartel)
                                    .addComponent(jTextFieldTorre, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(34, 34, 34)
                                .addComponent(jTextFieldCañon, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(73, 73, 73)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldBarbaro)
                            .addComponent(jTextFieldGigante)
                            .addComponent(jTextFieldDuende)
                            .addComponent(jTextFieldArquera, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 251, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 319, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(29, 29, 29))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(122, 122, 122)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel20)
                                        .addGap(18, 18, 18)
                                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(152, 152, 152))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel24)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextFieldAyuntamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTextFieldOro, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldElixir, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(143, 143, 143)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonCrearEdificio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonCrearTropa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonMejorarEdificio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonRecogerRecursos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel21))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(e2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(e1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(78, 78, 78)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(e4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(e3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(87, 87, 87)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(20, 20, 20)
                        .addComponent(timef, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addGap(18, 18, 18)
                        .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(62, 62, 62)
                        .addComponent(jButtonCrearEdificio)
                        .addGap(4, 4, 4)
                        .addComponent(jButtonCrearTropa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonMejorarEdificio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRecogerRecursos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(jTextFieldOro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldElixir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jLabel4)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel13))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jProgressBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel24)
                                    .addComponent(jTextFieldAyuntamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(jTextFieldChoza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(jTextFieldCañon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel14)
                                .addComponent(jTextFieldBarbaro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextFieldMina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(jTextFieldMortero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(jTextFieldArquera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldOroMina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextFieldRecolector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jTextFieldTorre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(jTextFieldGigante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldElixRec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jTextFieldCampamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(jTextFieldCuartel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(jTextFieldDuende, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(jLabel23)
                            .addComponent(e1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(e3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(jLabel25)
                            .addComponent(e2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(e4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(timef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))))
                .addContainerGap(243, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
int life1;

    void inicial() {
        int life = 0;
        for (Edificio e : aldea.edificios) {
            life = e.vidaActual + life;
        }

        life1 = life;
        jProgressBar1.setMaximum(life1);
        jProgressBar1.setStringPainted(true);
        jProgressBar1.setBackground(Color.WHITE);
        jProgressBar1.setForeground(Color.black);
        jProgressBar2.setStringPainted(true);
        jProgressBar2.setBackground(Color.WHITE);
        jProgressBar2.setForeground(Color.black);
    }

    void Barravida() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {

        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        int life = 0;

        for (Edificio e : aldea.edificios) {
            life = e.vidaActual + life;
            jProgressBar1.setValue(life);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(frameAldea.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }


    private void jButtonCrearEdificioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearEdificioActionPerformed
        frameComprarEdificios obj = new frameComprarEdificios(this);

        obj.show();


    }//GEN-LAST:event_jButtonCrearEdificioActionPerformed

    private void jButtonCrearTropaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearTropaActionPerformed

        frameComprarTropas obj = new frameComprarTropas(this);
        obj.show();

    }//GEN-LAST:event_jButtonCrearTropaActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        frameRealizarAtaque obj = new frameRealizarAtaque(this);
        obj.show();

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButtonMejorarEdificioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMejorarEdificioActionPerformed

        frameMejorarEdificios obj = new frameMejorarEdificios(this);
        obj.show();

    }//GEN-LAST:event_jButtonMejorarEdificioActionPerformed

private void jButtonRecogerRecursosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRecogerRecursosActionPerformed
    // Recoger Recursos
    for (Edificio e : aldea.edificios) {
        RecogerRecursos(e);
    }
    // Mostrar Recursos
    jTextFieldOro.setText(String.valueOf((int) aldea.oro));
    //System.out.println("Elixir: "+aldea.elixir);
    jTextFieldElixir.setText(String.valueOf((int) aldea.elixir));
}//GEN-LAST:event_jButtonRecogerRecursosActionPerformed

private void jTextFieldOroMinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldOroMinaActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jTextFieldOroMinaActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        if (ataque == null) {
            int Oro = rand.nextInt(vg.maxOroAtaque) + vg.minOroAtaque;
            int Elixir = rand.nextInt(vg.maxElixirAtaque) + vg.minElixirAtaque;

            ataque = new Ataque(Oro, Elixir, edificios[vg.CUARTEL], tropas);
            ataque.Atacar(aldea);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField e1;
    private javax.swing.JTextField e2;
    private javax.swing.JTextField e3;
    private javax.swing.JTextField e4;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButtonCrearEdificio;
    private javax.swing.JButton jButtonCrearTropa;
    private javax.swing.JButton jButtonMejorarEdificio;
    private javax.swing.JButton jButtonRecogerRecursos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextFieldArquera;
    private javax.swing.JTextField jTextFieldAyuntamiento;
    private javax.swing.JTextField jTextFieldBarbaro;
    private javax.swing.JTextField jTextFieldCampamento;
    private javax.swing.JTextField jTextFieldCañon;
    private javax.swing.JTextField jTextFieldChoza;
    private javax.swing.JTextField jTextFieldCuartel;
    private javax.swing.JTextField jTextFieldDuende;
    private javax.swing.JTextField jTextFieldElixRec;
    private javax.swing.JTextField jTextFieldElixir;
    private javax.swing.JTextField jTextFieldGigante;
    private javax.swing.JTextField jTextFieldMina;
    private javax.swing.JTextField jTextFieldMortero;
    private javax.swing.JTextField jTextFieldOro;
    private javax.swing.JTextField jTextFieldOroMina;
    private javax.swing.JTextField jTextFieldRecolector;
    private javax.swing.JTextField jTextFieldTorre;
    private javax.swing.JTextField timef;
    // End of variables declaration//GEN-END:variables
}
