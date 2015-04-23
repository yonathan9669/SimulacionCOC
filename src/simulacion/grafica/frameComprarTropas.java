package simulacion.grafica;
import Civilizacion.*;
import javax.swing.JOptionPane;

public class frameComprarTropas extends javax.swing.JFrame {

    public frameAldea copia;
    public itemComprarTropa seleccionado;
    public double elixirAldea;
    
    public frameComprarTropas(frameAldea obj) {
        initComponents();
        copia = obj;
        elixirAldea = copia.aldea.elixir;
        for(Edificio e: obj.aldea.edificios){
            if(e.estaHabilitado() && (e.tipo==vg.CUARTEL)){
                jComboBox3.addItem(new itemComprarTropa(e.id,e.nivel,e.colaTropas,"Cuartel"));
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

        jComboBox1 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButtonBarbaro = new javax.swing.JButton();
        jButtonArquera = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldBarbaros = new javax.swing.JTextField();
        jTextFieldArqueras = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jButtonGigante = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jButtonDuende = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldGigantes = new javax.swing.JTextField();
        jTextFieldDuendes = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jTextFieldNivel = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel1.setText("Comprar Tropas");

        jLabel2.setText("Bárbaro (25 ELIXIR)");

        jLabel3.setText("Arquera (50 ELIXIR)");

        jButtonBarbaro.setText("Comprar");
        jButtonBarbaro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBarbaroActionPerformed(evt);
            }
        });

        jButtonArquera.setText("Comprar");
        jButtonArquera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonArqueraActionPerformed(evt);
            }
        });

        jLabel4.setText("Barbaros en cola");

        jLabel5.setText("Arqueras en cola");

        jTextFieldBarbaros.setEnabled(false);

        jTextFieldArqueras.setEnabled(false);

        jButton3.setText("volver a la Aldea");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel6.setText("Gigante (250 ELIXIR)");

        jButtonGigante.setText("Comprar");
        jButtonGigante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGiganteActionPerformed(evt);
            }
        });

        jLabel7.setText("Seleccione un Cuartel:");

        jButtonDuende.setText("Comprar");
        jButtonDuende.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDuendeActionPerformed(evt);
            }
        });

        jLabel8.setText("Duende (25 ELIXIR)");

        jTextFieldGigantes.setEnabled(false);

        jTextFieldDuendes.setEnabled(false);

        jLabel9.setText("Gigantes en cola");

        jLabel10.setText("Duendes en cola");

        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jTextFieldNivel.setEnabled(false);

        jLabel11.setText("Nivel:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(20, 20, 20)
                                .addComponent(jTextFieldDuendes, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldArqueras, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                                    .addComponent(jTextFieldBarbaros, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                                    .addComponent(jTextFieldGigantes, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel7)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldNivel, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(17, 17, 17)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonDuende)
                                    .addComponent(jButtonArquera)
                                    .addComponent(jButtonBarbaro)
                                    .addComponent(jButtonGigante)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                                .addComponent(jButton3)
                                .addGap(67, 67, 67))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(45, 45, 45))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextFieldBarbaros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextFieldArqueras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldGigantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jButton3))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldDuendes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jButtonBarbaro))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jButtonArquera))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jButtonGigante))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jButtonDuende))))
                .addGap(47, 47, 47))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       
        this.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        seleccionado = (itemComprarTropa)jComboBox3.getSelectedItem();
        
        int nivelReal = seleccionado.nivel+1;
        
        jTextFieldNivel.setText(String.valueOf(nivelReal));
        
        // Permitir/Bloquear compra de tropas segun nivel de cuartel
        if(nivelReal >= 2)
            jButtonArquera.setEnabled(true);
        else
            jButtonArquera.setEnabled(false);
        if(nivelReal >= 3)
            jButtonGigante.setEnabled(true);
        else
            jButtonGigante.setEnabled(false);
        if(nivelReal >= 4)
            jButtonDuende.setEnabled(true);
        else
            jButtonDuende.setEnabled(false);
        
        // Mostrar tropas en cola
        int bar = 0, arq = 0, gig = 0, due = 0;
        for(Tropa t: seleccionado.colaTropas){
            if(t.tipo == vg.BARBARO)
                bar++;
            if(t.tipo == vg.ARQUERA)
                arq++;
            if(t.tipo == vg.GIGANTE)
                gig++;
            if(t.tipo == vg.DUENDE)
                due++;
        }
        jTextFieldBarbaros.setText(String.valueOf(bar));
        jTextFieldArqueras.setText(String.valueOf(arq));
        jTextFieldGigantes.setText(String.valueOf(gig));
        jTextFieldDuendes.setText(String.valueOf(due));
}//GEN-LAST:event_jComboBox3ActionPerformed

private void jButtonBarbaroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBarbaroActionPerformed
    if(elixirAldea >= 25){
        copia.ComprarTropa(vg.BARBARO, copia.aldea.edificios.get(seleccionado.id));
        JOptionPane.showMessageDialog(this, "Ha empezado a entrenar un barbaro", "", JOptionPane.WARNING_MESSAGE);
        this.setVisible(false);
    }
    else{
        JOptionPane.showMessageDialog(this, "Necesita 25 de elixir para entrenar a la tropa", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}//GEN-LAST:event_jButtonBarbaroActionPerformed

private void jButtonArqueraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonArqueraActionPerformed
    if(elixirAldea >= 50){
        copia.ComprarTropa(vg.ARQUERA, copia.aldea.edificios.get(seleccionado.id));
        JOptionPane.showMessageDialog(this, "Ha empezado a entrenar una arquera", "", JOptionPane.WARNING_MESSAGE);
        this.setVisible(false);
    }
    else{
        JOptionPane.showMessageDialog(this, "Necesita 50 de elixir para entrenar a la tropa", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}//GEN-LAST:event_jButtonArqueraActionPerformed

private void jButtonGiganteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGiganteActionPerformed
    if(elixirAldea >= 250){
        copia.ComprarTropa(vg.GIGANTE, copia.aldea.edificios.get(seleccionado.id));
        JOptionPane.showMessageDialog(this, "Ha empezado a entrenar un gigante", "", JOptionPane.WARNING_MESSAGE);
        this.setVisible(false);
    }
    else{
        JOptionPane.showMessageDialog(this, "Necesita 250 de elixir para entrenar a la tropa", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}//GEN-LAST:event_jButtonGiganteActionPerformed

private void jButtonDuendeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDuendeActionPerformed
    if(elixirAldea >= 25){
        copia.ComprarTropa(vg.DUENDE, copia.aldea.edificios.get(seleccionado.id));
        JOptionPane.showMessageDialog(this, "Ha empezado a entrenar un duende", "", JOptionPane.WARNING_MESSAGE);
        this.setVisible(false);
    }
    else{
        JOptionPane.showMessageDialog(this, "Necesita 25 de elixir para entrenar a la tropa", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}//GEN-LAST:event_jButtonDuendeActionPerformed

    /**
     * @param args the command line arguments
     */
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonArquera;
    private javax.swing.JButton jButtonBarbaro;
    private javax.swing.JButton jButtonDuende;
    private javax.swing.JButton jButtonGigante;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextFieldArqueras;
    private javax.swing.JTextField jTextFieldBarbaros;
    private javax.swing.JTextField jTextFieldDuendes;
    private javax.swing.JTextField jTextFieldGigantes;
    private javax.swing.JTextField jTextFieldNivel;
    // End of variables declaration//GEN-END:variables
}
