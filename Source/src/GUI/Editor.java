/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;

/**
 * Interfaz grafica principal, editor de texto
 * @author jose_
 */
public class Editor extends javax.swing.JFrame {

    private EditorManager manager = new EditorManager();
    /**
     * Creates new form Editor
     */
    public Editor() {
        initComponents();
        this.setExtendedState(this.MAXIMIZED_BOTH);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ReporteErroresEstructuraGramatica = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaTokens = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        TablaErrores = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        EliminarLenguaje = new javax.swing.JDialog();
        LenguajesComboBox = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        DialogoTablaAnalisisSintactico = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaDeAnalisisSintactico = new javax.swing.JTable();
        DialogoTablaAnalisisSintactico1 = new javax.swing.JDialog();
        jScrollPane4 = new javax.swing.JScrollPane();
        TablaDeAnalisisSintactico1 = new javax.swing.JTable();
        tabs = new javax.swing.JTabbedPane();
        informacionLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        MenuLenguajes = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        VerTabla = new javax.swing.JMenuItem();
        VerInfo = new javax.swing.JMenuItem();
        VerPila = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        ReporteErroresEstructuraGramatica.setSize(new java.awt.Dimension(1270, 600));

        TablaTokens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Token", "Lexema", "Linea", "Columna"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TablaTokens);
        if (TablaTokens.getColumnModel().getColumnCount() > 0) {
            TablaTokens.getColumnModel().getColumn(0).setPreferredWidth(200);
            TablaTokens.getColumnModel().getColumn(1).setPreferredWidth(200);
            TablaTokens.getColumnModel().getColumn(2).setPreferredWidth(80);
            TablaTokens.getColumnModel().getColumn(3).setPreferredWidth(80);
        }

        TablaErrores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo", "Valor", "Descripcion", "Linea", "Columna"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaErrores.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane3.setViewportView(TablaErrores);
        if (TablaErrores.getColumnModel().getColumnCount() > 0) {
            TablaErrores.getColumnModel().getColumn(0).setPreferredWidth(120);
            TablaErrores.getColumnModel().getColumn(1).setPreferredWidth(135);
            TablaErrores.getColumnModel().getColumn(2).setPreferredWidth(840);
        }

        jLabel1.setText("Listado de tokens");

        jLabel2.setText("Errores encontrados:");

        javax.swing.GroupLayout ReporteErroresEstructuraGramaticaLayout = new javax.swing.GroupLayout(ReporteErroresEstructuraGramatica.getContentPane());
        ReporteErroresEstructuraGramatica.getContentPane().setLayout(ReporteErroresEstructuraGramaticaLayout);
        ReporteErroresEstructuraGramaticaLayout.setHorizontalGroup(
            ReporteErroresEstructuraGramaticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReporteErroresEstructuraGramaticaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ReporteErroresEstructuraGramaticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(ReporteErroresEstructuraGramaticaLayout.createSequentialGroup()
                        .addGroup(ReporteErroresEstructuraGramaticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 649, Short.MAX_VALUE)))
                .addContainerGap())
        );
        ReporteErroresEstructuraGramaticaLayout.setVerticalGroup(
            ReporteErroresEstructuraGramaticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ReporteErroresEstructuraGramaticaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                .addContainerGap())
        );

        EliminarLenguaje.setSize(new java.awt.Dimension(270, 130));

        jButton2.setText("Eliminar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout EliminarLenguajeLayout = new javax.swing.GroupLayout(EliminarLenguaje.getContentPane());
        EliminarLenguaje.getContentPane().setLayout(EliminarLenguajeLayout);
        EliminarLenguajeLayout.setHorizontalGroup(
            EliminarLenguajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EliminarLenguajeLayout.createSequentialGroup()
                .addGroup(EliminarLenguajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EliminarLenguajeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(LenguajesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(EliminarLenguajeLayout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jButton2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        EliminarLenguajeLayout.setVerticalGroup(
            EliminarLenguajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EliminarLenguajeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LenguajesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        DialogoTablaAnalisisSintactico.setResizable(false);
        DialogoTablaAnalisisSintactico.setSize(this.getToolkit().getScreenSize());

        TablaDeAnalisisSintactico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(TablaDeAnalisisSintactico);

        javax.swing.GroupLayout DialogoTablaAnalisisSintacticoLayout = new javax.swing.GroupLayout(DialogoTablaAnalisisSintactico.getContentPane());
        DialogoTablaAnalisisSintactico.getContentPane().setLayout(DialogoTablaAnalisisSintacticoLayout);
        DialogoTablaAnalisisSintacticoLayout.setHorizontalGroup(
            DialogoTablaAnalisisSintacticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogoTablaAnalisisSintacticoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1251, Short.MAX_VALUE)
                .addContainerGap())
        );
        DialogoTablaAnalisisSintacticoLayout.setVerticalGroup(
            DialogoTablaAnalisisSintacticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogoTablaAnalisisSintacticoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                .addContainerGap())
        );

        DialogoTablaAnalisisSintactico1.setResizable(false);
        DialogoTablaAnalisisSintactico1.setSize(this.getToolkit().getScreenSize());

        TablaDeAnalisisSintactico1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(TablaDeAnalisisSintactico1);

        javax.swing.GroupLayout DialogoTablaAnalisisSintactico1Layout = new javax.swing.GroupLayout(DialogoTablaAnalisisSintactico1.getContentPane());
        DialogoTablaAnalisisSintactico1.getContentPane().setLayout(DialogoTablaAnalisisSintactico1Layout);
        DialogoTablaAnalisisSintactico1Layout.setHorizontalGroup(
            DialogoTablaAnalisisSintactico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogoTablaAnalisisSintactico1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1251, Short.MAX_VALUE)
                .addContainerGap())
        );
        DialogoTablaAnalisisSintactico1Layout.setVerticalGroup(
            DialogoTablaAnalisisSintactico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogoTablaAnalisisSintactico1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        informacionLabel.setText("Linea: 0  |  Columna: 0");

        jMenu1.setText("Archivo");

        jMenuItem1.setText("Nuevo");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Abrir");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Guardar");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("Guardar Como");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem6.setText("Cerrar pestaña activa");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuItem5.setText("Salir");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Acciones");

        jMenuItem8.setText("Compilar");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        jMenuItem7.setText("Cargar Lenguaje");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuItem9.setText("Borrar Lenguaje");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem9);

        jMenuBar1.add(jMenu3);

        MenuLenguajes.setText("Lenguajes");
        jMenuBar1.add(MenuLenguajes);

        jMenu4.setText("Ver");

        VerTabla.setText("Ver informacion del lenguaje activo");
        VerTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VerTablaActionPerformed(evt);
            }
        });
        jMenu4.add(VerTabla);

        VerInfo.setText("Ver tabla de analisis sintactico");
        VerInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VerInfoActionPerformed(evt);
            }
        });
        jMenu4.add(VerInfo);

        VerPila.setText("Ver proceso estado de pila durante analisis");
        VerPila.setEnabled(false);
        VerPila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VerPilaActionPerformed(evt);
            }
        });
        jMenu4.add(VerPila);

        jMenuBar1.add(jMenu4);

        jMenu2.setText("Acerca de...");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(informacionLabel)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(informacionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Nuevo
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        manager.nuevoTab(tabs);
        agregarEventosAlTab();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    //Abrir
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        manager.cargarTab(tabs);
        agregarEventosAlTab();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    //Guardar
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        manager.guardarTab(tabs);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    //GuardarComo
    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        Tab tab = (Tab) tabs.getSelectedComponent();
        if(tab != null){
            if(tab.getOrigen() == null){
                ArchivosManager.guardarComo(tab, true);
            }else{
                ArchivosManager.guardarComo(tab, false);
            }
        }else{
            JOptionPane.showMessageDialog(null, "No hay ninguna pestaña activa. ", "Error",JOptionPane.ERROR_MESSAGE );
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    //Salir
    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    //Acerca de
    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        JOptionPane.showMessageDialog(null, "Desarrollado por: José Carlos Soberanis Ramírez\nCarnet: 201730246\nCiclo: 2020\nCurso: Compiladores 2", "Acerca de...", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jMenu2MouseClicked

    //Cerrar pestaña
    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        manager.cerrarTab(tabs);
        if(tabs.getComponentCount() == 0) informacionLabel.setText("Linea: 0  |  Columna: 0");
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        ((DefaultTableModel) TablaTokens.getModel()).setRowCount(0);
        ((DefaultTableModel) TablaErrores.getModel()).setRowCount(0);
        if(tabs.getSelectedComponent() != null){
            boolean mostrarErrores = manager.parsearSecciones(((Tab)tabs.getSelectedComponent()).getTexto().getText(), TablaTokens, TablaErrores);
            if(!mostrarErrores){
                this.ReporteErroresEstructuraGramatica.setVisible(true);
            }else{
                cargarLenguajes();
            }
        }else{
            JOptionPane.showMessageDialog(null, "No hay una pestaña activa.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        ((DefaultTableModel) TablaErrores.getModel()).setRowCount(0);
        ((DefaultTableModel) TablaTokens.getModel()).setRowCount(0);
        if(((Tab)tabs.getSelectedComponent()) != null){
            manager.compilarTexto(this.MenuLenguajes, (Tab)tabs.getSelectedComponent(), TablaErrores);
            if(((DefaultTableModel) TablaErrores.getModel()).getRowCount() != 0){
                this.ReporteErroresEstructuraGramatica.setVisible(true);
            }else{
                this.VerPila.setEnabled(true);
            }
        }else{
            JOptionPane.showMessageDialog(null, "No hay ninguna pestaña con texto activa", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        this.LenguajesComboBox.removeAllItems();
        for (String item : ArchivosManager.cargarNombresLenguajes()) {
            this.LenguajesComboBox.addItem(item);
        }
        this.EliminarLenguaje.setVisible(true);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ArchivosManager.eliminarLenguaje((String) LenguajesComboBox.getSelectedItem());
        this.EliminarLenguaje.dispose();
        cargarLenguajes();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void VerInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VerInfoActionPerformed
        this.DialogoTablaAnalisisSintactico.setTitle("TABLA DE ANALISIS SINTACTICO");
        manager.crearTablaDeAnalisisSintactico(this.MenuLenguajes, TablaDeAnalisisSintactico);
        this.DialogoTablaAnalisisSintactico.setVisible(true);
    }//GEN-LAST:event_VerInfoActionPerformed

    private void VerTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VerTablaActionPerformed
        manager.mostrarInformacionDelLenguaje(this.MenuLenguajes);
    }//GEN-LAST:event_VerTablaActionPerformed

    private void VerPilaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VerPilaActionPerformed
        this.DialogoTablaAnalisisSintactico1.setTitle("PROCESO DE LA PILA DURANTE EL ANALISIS DE LA CADENA");
        manager.crearTablaDeAnalisisCadena(this.MenuLenguajes, TablaDeAnalisisSintactico1);
        this.DialogoTablaAnalisisSintactico1.setVisible(true);
    }//GEN-LAST:event_VerPilaActionPerformed

    /**
     * 
     * @return el menu de lenguajes cargados
     */
    public JMenu getMenuLenguajes(){ return this.MenuLenguajes; }
    
    /**
     * 
     * @return el manager del editor
     */
    public EditorManager getEditorManager(){ return this.manager; }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Editor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog DialogoTablaAnalisisSintactico;
    private javax.swing.JDialog DialogoTablaAnalisisSintactico1;
    private javax.swing.JDialog EliminarLenguaje;
    private javax.swing.JComboBox<String> LenguajesComboBox;
    private javax.swing.JMenu MenuLenguajes;
    private javax.swing.JDialog ReporteErroresEstructuraGramatica;
    private javax.swing.JTable TablaDeAnalisisSintactico;
    private javax.swing.JTable TablaDeAnalisisSintactico1;
    private javax.swing.JTable TablaErrores;
    private javax.swing.JTable TablaTokens;
    private javax.swing.JMenuItem VerInfo;
    private javax.swing.JMenuItem VerPila;
    private javax.swing.JMenuItem VerTabla;
    private javax.swing.JLabel informacionLabel;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane tabs;
    // End of variables declaration//GEN-END:variables

    private void agregarEventosAlTab() {
        Tab tab = (Tab) tabs.getSelectedComponent();
        tab.getTexto().addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent e) {
                int pos = e.getDot();
                int row = 0, col=0;
                try {
                    row = tab.getTexto().getLineOfOffset(pos) + 1;
                    col = pos - tab.getTexto().getLineStartOffset(row - 1) + 1;
                } catch (BadLocationException ex) {
                    Logger.getLogger(EditorManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                informacionLabel.setText("Línea: " + row + "  |  Columna: " + col);
            }
        });
        tab.getTexto().addKeyListener(new KeyListener(){
            @Override
            public void keyPressed(KeyEvent e) {tab.setModificado(true);}
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }

    /**
     * Metodo que carga en el menú lenguajes los lenguajes existentes en el repositorio local
     * 
     */
    public void cargarLenguajes() {
        VerPila.setEnabled(false);
        MenuLenguajes.removeAll();
        List<String> lenguajes =  ArchivosManager.cargarNombresLenguajes();
        ButtonGroup grupo = new ButtonGroup();
        for (int i = 0; i < lenguajes.size(); i++){
            JRadioButtonMenuItem item = new JRadioButtonMenuItem(lenguajes.get(i));
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    VerPila.setEnabled(false);
                }
            });
            if(i==0) item.setSelected(true);
            item.setName(lenguajes.get(i));
            grupo.add(item);
            MenuLenguajes.add(item);
        }
        if(!lenguajes.isEmpty()){
            VerTabla.setEnabled(true);
            VerInfo.setEnabled(true);
        }else{
            VerTabla.setEnabled(false);
            VerInfo.setEnabled(false);
        }
    }
}
