/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import config.Config;
import dao.IDao;
import entities.Machine;
import entities.Salle;
import java.awt.Color;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author houss
 */
public class SalleMachineForm extends javax.swing.JInternalFrame {
    IDao<Machine> dao = null;
    IDao<Salle> dao1 =null;
    private DefaultTableModel model;
    /**
     * Creates new form SalleMachine
     */
    public SalleMachineForm() {
        initComponents();
        customizeUI();
        try {
            dao = (IDao<Machine>) Naming.lookup("rmi://"+Config.ip+":"+Config.port+"/dao");
            dao1 = (IDao<Salle>) Naming.lookup("rmi://"+Config.ip+":"+Config.port+"/dao1");
        } catch (NotBoundException ex) {
            Logger.getLogger(SalleMachineForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(SalleMachineForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(SalleMachineForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        model = (DefaultTableModel) listeMachinesBySalle.getModel();
        load();
        loadSalle();
    }
    
    private void customizeUI() {
        // Set a more visually appealing background color for the main panel
        jPanel1.setBackground(new Color(240, 240, 240));

        // Set a more descriptive title for the internal frame
        setTitle("Machines by Salle");

        // Enhance the look of the table
        listeMachinesBySalle.getTableHeader().setBackground(new Color(51, 153, 255)); // Light blue header background
        listeMachinesBySalle.getTableHeader().setForeground(Color.black);
        listeMachinesBySalle.setSelectionBackground(new Color(51, 153, 255));
    }
    public void load (){
        model.setRowCount(0); // Clear the table rows

        try {
            Salle selectedSalle = (Salle) comboSalle.getSelectedItem();
            List<Machine> machines = dao.findMachinesBySalle(selectedSalle);

            machines.forEach(m -> model.addRow(new Object[] {
                m.getId(),
                m.getRef(),
                m.getMarque(),
                m.getPrix()
            }));
        } catch (RemoteException ex) {
            Logger.getLogger(SalleMachineForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void loadSalle(){
        try {
            for(Salle s :dao1.findAll()){
                comboSalle.addItem(s);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(SalleMachineForm.class.getName()).log(Level.SEVERE, null, ex);
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
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        comboSalle = new javax.swing.JComboBox<Salle>();
        jScrollPane2 = new javax.swing.JScrollPane();
        listeMachinesBySalle = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("MByS");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Informations"));

        jLabel1.setText("Salle");

        comboSalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSalleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(jLabel1)
                .addGap(56, 56, 56)
                .addComponent(comboSalle, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboSalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(138, Short.MAX_VALUE))
        );

        listeMachinesBySalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Réference", "Marque", "Prix"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(listeMachinesBySalle);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboSalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSalleActionPerformed
        load();        // TODO add your handling code here:
    }//GEN-LAST:event_comboSalleActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Salle> comboSalle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable listeMachinesBySalle;
    // End of variables declaration//GEN-END:variables
}
