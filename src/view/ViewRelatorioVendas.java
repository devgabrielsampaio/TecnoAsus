/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DAO.PecasDAO;
import DAO.VendaDAO;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author GabrielFSampaio
 */
public class ViewRelatorioVendas extends javax.swing.JFrame {
String data_inicial_formatada;
    /**
     * Creates new form ViewRelatorioVendas
     */
    public ViewRelatorioVendas() {
        initComponents();
        setIcon();
    }
     public void setIcon(){
        setIconImage(new ImageIcon(getClass().getResource("/img/logo.png")).getImage());
    }
    public void gerarRelatorio(){
        Double rendimentos1;
        Document documento=new Document(PageSize.A4);
        try {
            JFileChooser filechooser= new JFileChooser();
            filechooser.setDialogTitle("Salvar PDF");
            filechooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Files", "pdf");
            filechooser.addChoosableFileFilter(filter);
            filechooser.showSaveDialog(null);
            PdfWriter.getInstance(documento, new FileOutputStream(filechooser.getSelectedFile().getPath()+".pdf"));
            documento.open();
            try {
                Image imagem= Image.getInstance(getClass().getResource("/img/logo_Splash.jpeg"));
                imagem.scaleAbsolute(100f, 100f);
                imagem.setAlignment(Element.ALIGN_LEFT);
                documento.add(imagem);
            } catch (BadElementException ex) {
                Logger.getLogger(ViewRelatorioPecas.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ViewRelatorioPecas.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Paragraph titulo= new Paragraph(new Phrase(20f,"Relatório De Vendas",FontFactory.getFont(FontFactory.TIMES_BOLD,16f)));
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);
            Table tabelaVendas=new Table(1);
            tabelaVendas.setPadding(5);
            tabelaVendas.setSpacing(0);
            tabelaVendas.setBorder(3);
            tabelaVendas.setBorderWidth(3);
            tabelaVendas.setBorderColor(new Color(210,105,30));
            tabelaVendas.setWidth(100f);
            tabelaVendas.setWidths(new float[]{15f});
            
            Paragraph rendimentos= new Paragraph(new Phrase(20f,"Rendimentos",FontFactory.getFont(FontFactory.HELVETICA_BOLD,12f)));
            rendimentos.setAlignment(Element.ALIGN_CENTER);
            
            Cell celulaRendimentos=new Cell(rendimentos);
            
            celulaRendimentos.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            celulaRendimentos.setBorderColor(new Color(210,185,30));
        
            celulaRendimentos.setBackgroundColor(new Color(210,149,30));
          
            tabelaVendas.addCell(celulaRendimentos);
            
            VendaDAO vdao= new VendaDAO();
            rendimentos1= vdao.retornaRendimentos();
            tabelaVendas.addCell(rendimentos1.toString());
            documento.add(tabelaVendas);
            documento.close();
            JOptionPane.showMessageDialog(null, "Relatório Gerado Com Sucesso!","Sucesso",JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Gerar Relatório"+ex);
        } catch (DocumentException ex) {
           JOptionPane.showMessageDialog(null, "Erro ao Gerar Relatório"+ex);
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

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Relatório Vendas");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        jButton1.setBackground(new java.awt.Color(0, 102, 102));
        jButton1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Gerar Relatório");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(jButton1)
                .addContainerGap(155, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(186, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        gerarRelatorio();
        
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ViewRelatorioVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewRelatorioVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewRelatorioVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewRelatorioVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewRelatorioVendas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}