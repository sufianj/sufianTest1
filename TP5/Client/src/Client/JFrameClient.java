/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;


/**
 *
 * @author Administrator
 */
public class JFrameClient extends javax.swing.JFrame implements ActionListener
{

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jButton_OK) {
            jButton_OKActionPerformed(e);
            System.out.println("in actionPerformed");
        }
    }
    
/*    @Override
    public void windowClosing(WindowEvent e)
    {
        if (e.getSource() == this)
            outSocket.println("Client quit");
        System.out.println("In windowClosing");
    }*/
    
    
    /**
     * Creates new form JFrameClient
     */
    public JFrameClient()   throws Exception{
        initComponents();
        socket = new Socket("localhost", 4444);
        //to get the ip address
        System.out.println((java.net.InetAddress.getLocalHost()).toString());

        //true: it will flush the output buffer
        outSocket = new PrintWriter(socket.getOutputStream(), true);
        inSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
        //read the list of pieces
      //  ArrayList<String> Pieces = new ArrayList<String>();
        //todo: read the number of pieces 
        int i = 0;
        Pieces = new String[10];
        server_rep = inSocket.readLine();
        while (! "end of list".equals(server_rep))
        {
          //  System.out.println(server_rep);
            Pieces[i] = new String();
            Pieces[i] = server_rep;
            i++;
            server_rep = inSocket.readLine();
        }
         jComboBox_NameTheatre.setModel(new javax.swing.DefaultComboBoxModel(Pieces));
        setVisible(true);
        
    }
    
    

  /*  private void SetCB_NameTheatre(String[] strlist)//load the items read from server to jCB_NameTheate
    {
        jComboBox_NameTheatre.setModel(new javax.swing.DefaultComboBoxModel(strlist));
    }*/
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_NameClient = new javax.swing.JLabel();
        jLabel_NumberOfPlace = new javax.swing.JLabel();
        jLabel_NameTheatre = new javax.swing.JLabel();
        jLabel_Validation = new javax.swing.JLabel();
        jButton_OK = new javax.swing.JButton();
        jTextField_NameClient = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_Result = new javax.swing.JTextArea();
        jComboBox_NameTheatre = new javax.swing.JComboBox();
        jComboBox_NumberOfPlace = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel_NameClient.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 1, 18)); // NOI18N
        jLabel_NameClient.setText("First name and Last name");

        jLabel_NumberOfPlace.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 1, 18)); // NOI18N
        jLabel_NumberOfPlace.setText("Number of places");

        jLabel_NameTheatre.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 1, 18)); // NOI18N
        jLabel_NameTheatre.setText("Name of the piece of theate");

        jLabel_Validation.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 1, 18)); // NOI18N
        jLabel_Validation.setText("Validate the reservation");

        jButton_OK.setText("OK");
        jButton_OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_OKActionPerformed(evt);
            }
        });

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setToolTipText("");

        jTextArea_Result.setColumns(20);
        jTextArea_Result.setRows(5);
        jScrollPane1.setViewportView(jTextArea_Result);

        jComboBox_NameTheatre.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jComboBox_NameTheatre.setMaximumRowCount(6);

        jComboBox_NumberOfPlace.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jComboBox_NumberOfPlace.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_NameClient, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_NumberOfPlace, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_NameTheatre, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel_Validation, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(jButton_OK))
                            .addComponent(jTextField_NameClient, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jComboBox_NameTheatre, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jComboBox_NumberOfPlace, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_NameClient)
                .addGap(27, 27, 27)
                .addComponent(jTextField_NameClient, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jLabel_NameTheatre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jComboBox_NameTheatre, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel_NumberOfPlace)
                .addGap(18, 18, 18)
                .addComponent(jComboBox_NumberOfPlace, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Validation)
                    .addComponent(jButton_OK))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_OKActionPerformed

        String str;
        try{
            str = this.jComboBox_NameTheatre.getSelectedItem().toString();
            this.outSocket.println(str);
            str = this.jComboBox_NumberOfPlace.getSelectedItem().toString();
            this.outSocket.println(str);
            str = this.jTextField_NameClient.getText();
            this.outSocket.println(str);
            str = inSocket.readLine();
            System.out.println(str);
            this.jTextArea_Result.setText(str);
        }catch (Exception e) {
                    e.printStackTrace();
        }
    }//GEN-LAST:event_jButton_OKActionPerformed


    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        outSocket.println("Client quit");
     //   System.out.println("in formWindowClosing");
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       // /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
 /*       try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrameClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
        
        //</editor-fold>

        /* Create and display the form */
        
    /*    java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameClient().setVisible(true);
            }
        });*/   
        
        try{
        JFrameClient jfc = new JFrameClient();
        }catch (Exception e) {
                    e.printStackTrace();
        }
        
   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_OK;
    private javax.swing.JComboBox jComboBox_NameTheatre;
    private javax.swing.JComboBox jComboBox_NumberOfPlace;
    private javax.swing.JLabel jLabel_NameClient;
    private javax.swing.JLabel jLabel_NameTheatre;
    private javax.swing.JLabel jLabel_NumberOfPlace;
    private javax.swing.JLabel jLabel_Validation;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea_Result;
    private javax.swing.JTextField jTextField_NameClient;
    // End of variables declaration//GEN-END:variables
    private Socket socket;
    private PrintWriter outSocket;
    private String Pieces[];
    private String server_rep;
    private BufferedReader inSocket;
}


