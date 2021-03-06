/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dudente;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JTable;

/**
 *
 * @author Igor Couto
 */
public class botaoRelatorio extends javax.swing.JFrame {

    private final JTable tabelaDentistas;
    private final JTable tabelaEmpregados;

    /**
     * Creates new form botaoRelatorio
     */
    public botaoRelatorio() {
        this.tabelaDentistas = null;
        this.tabelaEmpregados = null;
        this.setResizable(false);
        initComponents();
    }

    botaoRelatorio(JTable tabelaDentistas, JTable tabelaEmpregados) {
        this.tabelaDentistas = tabelaDentistas;
        this.tabelaEmpregados = tabelaEmpregados;
        this.setResizable(false);
        this.setLocationRelativeTo(this);
        initComponents();
   
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Todos Funcionários");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Somente Dentistas");
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Somente Empregados");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
        JFileChooser jFileChooser1 = new JFileChooser(); 
        
        int retrival = jFileChooser1.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
            try {
                salvarRelatorio(jFileChooser1.getSelectedFile().getAbsolutePath(),3);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (retrival == JFileChooser.CANCEL_OPTION) {
            System.out.println("Cancelou");
        }
        this.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser jFileChooser1 = new JFileChooser(); 
        
        int retrival = jFileChooser1.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
            try {
                salvarRelatorio(jFileChooser1.getSelectedFile().getAbsolutePath(),1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (retrival == JFileChooser.CANCEL_OPTION) {
            System.out.println("Cancelou");
        }
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JFileChooser jFileChooser1 = new JFileChooser(); 
        
        int retrival = jFileChooser1.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
            try {
                salvarRelatorio(jFileChooser1.getSelectedFile().getAbsolutePath(),2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (retrival == JFileChooser.CANCEL_OPTION) {
            System.out.println("Cancelou");
        }
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(botaoRelatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(botaoRelatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(botaoRelatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(botaoRelatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new botaoRelatorio().setVisible(true);
            }
        });
    }

    private void salvarRelatorio(String caminho, int opt) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter writer;

        
        if(!caminho.endsWith(".pdf")){
            caminho = caminho + ".pdf";
        }
        
        writer = PdfWriter.getInstance(document, new FileOutputStream(caminho));


        document.open();
        PdfContentByte cb = writer.getDirectContent();

        PdfTemplate tp = cb.createTemplate(500, 500);
        Graphics2D g2;

        g2 = tp.createGraphicsShapes(500, 500);



        if (opt == 1) {
            tabelaDentistas.print(g2);
        }

        if (opt == 2) {
            tabelaEmpregados.print(g2);
        }

        if (opt == 3) {
            tabelaDentistas.print(g2);
            tabelaEmpregados.print(g2);
        }

        g2.dispose();
        cb.addTemplate(tp, 30, 300);

        document.close();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    // End of variables declaration//GEN-END:variables
}