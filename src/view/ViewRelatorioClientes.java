/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DAO.ClienteDAO;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.Cliente;
import model.Pecas;

/**
 *
 * @author GabrielFSampaio
 */
public class ViewRelatorioClientes extends javax.swing.JFrame {

    /**
     * Creates new form ViewRelatorioClientes
     */
    public ViewRelatorioClientes() {
        initComponents();
        setIcon();
    }
     public void setIcon(){
        setIconImage(new ImageIcon(getClass().getResource("/img/logo.png")).getImage());
    }  
    public void gerarRelatorio(){
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
            
            Paragraph titulo= new Paragraph(new Phrase(20f,"Relatório De Clientes",FontFactory.getFont(FontFactory.TIMES_BOLD,16f)));
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);
            Table tabelaClientes=new Table(4);
            tabelaClientes.setPadding(5);
            tabelaClientes.setSpacing(0);
            tabelaClientes.setBorder(3);
            tabelaClientes.setBorderWidth(3);
            tabelaClientes.setBorderColor(new Color(210,105,30));
            tabelaClientes.setWidth(100f);
            tabelaClientes.setWidths(new float[]{10f,15f,10f,10f});
            
            Paragraph id= new Paragraph(new Phrase(20f,"ID",FontFactory.getFont(FontFactory.HELVETICA_BOLD,12f)));
            id.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph nome= new Paragraph(new Phrase(20f,"Nome",FontFactory.getFont(FontFactory.HELVETICA_BOLD,12f)));
            nome.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph email= new Paragraph(new Phrase(20f,"E-mail",FontFactory.getFont(FontFactory.HELVETICA_BOLD,12f)));
            email.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph telefone= new Paragraph(new Phrase(20f,"Telefone",FontFactory.getFont(FontFactory.HELVETICA_BOLD,12f)));
            telefone.setAlignment(Element.ALIGN_CENTER);
            
            Cell celulaID=new Cell(id);
            Cell celulaNome=new Cell(nome);
            Cell celulaEmail=new Cell(email);
            Cell celulaTelefone=new Cell(telefone);
            
            celulaID.setHorizontalAlignment(Element.ALIGN_CENTER);
            celulaNome.setHorizontalAlignment(Element.ALIGN_CENTER);
            celulaEmail.setHorizontalAlignment(Element.ALIGN_CENTER);
            celulaTelefone.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            celulaID.setBorderColor(new Color(210,185,30));
            celulaNome.setBorderColor(new Color(210,185,30));
            celulaEmail.setBorderColor(new Color(210,185,30));
            celulaTelefone.setBorderColor(new Color(210,185,30));
            
            celulaID.setBackgroundColor(new Color(210,149,30));
            celulaNome.setBackgroundColor(new Color(210,149,30));
            celulaEmail.setBackgroundColor(new Color(210,149,30));
            celulaTelefone.setBackgroundColor(new Color(210,149,30));
            
            celulaID.setHeader(true);
            tabelaClientes.addCell(celulaID);
            tabelaClientes.addCell(celulaNome);
            tabelaClientes.addCell(celulaEmail);
            tabelaClientes.addCell(celulaTelefone);
            
            ClienteDAO cdao= new ClienteDAO();
            for(Cliente c:cdao.read()){
                tabelaClientes.addCell(Integer.toString(c.getId()));
                tabelaClientes.addCell(c.getNome());
                tabelaClientes.addCell(c.getEmail());
                tabelaClientes.addCell(c.getTelefone());
            }
            documento.add(tabelaClientes);
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
        setTitle("Relatório Clientes");
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
                .addGap(134, 134, 134)
                .addComponent(jButton1)
                .addContainerGap(155, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(216, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(ViewRelatorioClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewRelatorioClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewRelatorioClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewRelatorioClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewRelatorioClientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
