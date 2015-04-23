package simulacion.grafica;
import Civilizacion.*;
import javax.swing.JOptionPane;

public class frameMejorarEdificios extends javax.swing.JFrame {

    public frameAldea copia;
    public itemMejorarEdificio seleccionado;
    public double oroAldea, elixirAldea;
    
    public frameMejorarEdificios(frameAldea obj) {
        initComponents();
        copia = obj;
        oroAldea = copia.aldea.oro;
        elixirAldea = copia.aldea.elixir;
        for(Edificio e: obj.aldea.edificios){
            if(e.estaHabilitado() && (e.nivel+1)<e.mejoras.length){
                switch(e.tipo){
                    case vg.AYUNTAMIENTO:
                        jComboBox1.addItem(new itemMejorarEdificio(e.id,e.nivel,e.mejoras[e.nivel+1].precio,"Ayuntamiento",e.tipoCompra));
                        break;
                    case vg.CAMPAMENTO:
                        jComboBox1.addItem(new itemMejorarEdificio(e.id,e.nivel,e.mejoras[e.nivel+1].precio,"Campamento",e.tipoCompra));
                        break;
                    case vg.CUARTEL:
                        jComboBox1.addItem(new itemMejorarEdificio(e.id,e.nivel,e.mejoras[e.nivel+1].precio,"Cuartel",e.tipoCompra));
                        break;
                    case vg.MINA:
                        jComboBox1.addItem(new itemMejorarEdificio(e.id,e.nivel,e.mejoras[e.nivel+1].precio,"Mina de Oro",e.tipoCompra));
                        break;
                    case vg.RECOLECTOR:
                        jComboBox1.addItem(new itemMejorarEdificio(e.id,e.nivel,e.mejoras[e.nivel+1].precio,"Recolector de Elixir",e.tipoCompra));
                        break;
                    case vg.TORRE:
                        jComboBox1.addItem(new itemMejorarEdificio(e.id,e.nivel,e.mejoras[e.nivel+1].precio,"Torre de Arqueras",e.tipoCompra));
                        break;
                    case vg.CAÑON:
                        jComboBox1.addItem(new itemMejorarEdificio(e.id,e.nivel,e.mejoras[e.nivel+1].precio,"Cañón",e.tipoCompra));
                        break;
                    case vg.MORTERO:
                        jComboBox1.addItem(new itemMejorarEdificio(e.id,e.nivel,e.mejoras[e.nivel+1].precio,"Mortero",e.tipoCompra));
                        break;
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldNivel = new javax.swing.JTextField();
        jTextFieldPrecio = new javax.swing.JTextField();
        jButtonMejorar = new javax.swing.JButton();
        jTextFieldNombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButtonVolver = new javax.swing.JButton();
        jTextFieldTipoCompra = new javax.swing.JTextField();

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel1.setText("Mejorar Edificio");

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Nombre");

        jLabel3.setText("Edificio");

        jLabel4.setText("Siguiente Nivel");

        jTextFieldNivel.setEnabled(false);

        jTextFieldPrecio.setEnabled(false);

        jButtonMejorar.setText("Mejorar");
        jButtonMejorar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMejorarActionPerformed(evt);
            }
        });

        jTextFieldNombre.setEnabled(false);

        jLabel5.setText("Precio");

        jButtonVolver.setText("Volver a la Aldea");
        jButtonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVolverActionPerformed(evt);
            }
        });

        jTextFieldTipoCompra.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jTextFieldNombre)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel5)
                                    .addComponent(jTextFieldPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                                    .addComponent(jTextFieldTipoCompra)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jButtonMejorar)
                        .addGap(70, 70, 70)
                        .addComponent(jButtonVolver)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldTipoCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonMejorar)
                    .addComponent(jButtonVolver))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        seleccionado = (itemMejorarEdificio)jComboBox1.getSelectedItem();
        jTextFieldNivel.setText(String.valueOf(seleccionado.nivel+2));
        jTextFieldPrecio.setText(String.valueOf(seleccionado.precio));
        jTextFieldNombre.setText(String.valueOf(seleccionado.nombre));
        jTextFieldTipoCompra.setText(String.valueOf(seleccionado.tipoCompra));
    }//GEN-LAST:event_jComboBox1ActionPerformed

private void jButtonMejorarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMejorarActionPerformed
    // Verificar que pueda mejorar
    if(seleccionado.tipoCompra.equals("oro")){
        if(seleccionado.precio > oroAldea){
            JOptionPane.showMessageDialog(this, "Necesita "+seleccionado.precio+" de oro para mejorar el edificio", "ERROR", JOptionPane.ERROR_MESSAGE);
        }else{
            copia.MejorarEdificio(copia.aldea.edificios.get(seleccionado.id));
            JOptionPane.showMessageDialog(this, "Ha empezado a mejorar: "+seleccionado.nombre, "", JOptionPane.WARNING_MESSAGE);
            this.setVisible(false);
        }
    }
    else{
        if(seleccionado.precio > elixirAldea){
            JOptionPane.showMessageDialog(this, "Necesita "+seleccionado.precio+" de elixir para mejorar el edificio", "ERROR", JOptionPane.ERROR_MESSAGE);
        }else{
            copia.MejorarEdificio(copia.aldea.edificios.get(seleccionado.id));
            JOptionPane.showMessageDialog(this, "Ha empezado a mejorar: "+seleccionado.nombre, "", JOptionPane.WARNING_MESSAGE);
            this.setVisible(false);
        }
    }
    
}//GEN-LAST:event_jButtonMejorarActionPerformed

private void jButtonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVolverActionPerformed
    this.setVisible(false);
}//GEN-LAST:event_jButtonVolverActionPerformed

    /**
     * @param args the command line arguments
     */
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonMejorar;
    private javax.swing.JButton jButtonVolver;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldNivel;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldPrecio;
    private javax.swing.JTextField jTextFieldTipoCompra;
    // End of variables declaration//GEN-END:variables
}
