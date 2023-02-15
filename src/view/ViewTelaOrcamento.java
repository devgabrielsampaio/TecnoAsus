/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DAO.ClienteDAO;
import DAO.PecasDAO;
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
import java.awt.event.ItemEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import model.Cliente;
import model.Orcamento;
import model.OrcamentoTableModel;
import model.Pecas;

/**
 *
 * @author GabrielFSampaio
 */
public class ViewTelaOrcamento extends javax.swing.JFrame {
String preco;
Double preco_cancelado=0.0,preco_normal=0.0,subtotal=0.0,subtotal2=0.0,subtotal_cancelado=0.0,subtotal_cancelado2=0.0,total=0.0;
int quantidade_cancelado=0,quantidade_normal=0;
OrcamentoTableModel tableModel= new OrcamentoTableModel();
    /**
     * Creates new form ViewTelaOrcamento
     */
    public ViewTelaOrcamento() {
        initComponents();
        setIcon();
        readJTable();
        readJTable2();
        jTable1.setModel(tableModel);
    }
    public void setIcon(){
        setIconImage(new ImageIcon(getClass().getResource("/img/logo.png")).getImage());
    }
    public void readJTable(){
        ClienteDAO cdao=new ClienteDAO();
        for(Cliente c:cdao.read()){
            jComboBox1.addItem(c.getNome());
                
                
            
        }
    }  
    public void readJTable2(){
        PecasDAO pdao=new PecasDAO();
        for(Pecas p:pdao.read()){
            jComboBox2.addItem(p.getNome());
                
                
            
        }
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
            
            Paragraph titulo= new Paragraph(new Phrase(20f,"Orçamento TecnoAsus",FontFactory.getFont(FontFactory.TIMES_BOLD,16f)));
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);
            Table tabelaOrcamento=new Table(4);
            tabelaOrcamento.setPadding(5);
            tabelaOrcamento.setSpacing(0);
            tabelaOrcamento.setBorder(3);
            tabelaOrcamento.setBorderWidth(3);
            tabelaOrcamento.setBorderColor(new Color(210,105,30));
            tabelaOrcamento.setWidth(100f);
            tabelaOrcamento.setWidths(new float[]{15f,15f,15f,15f});
            
            Paragraph nome_cliente= new Paragraph(new Phrase(20f,"Cliente: "+jComboBox1.getSelectedItem().toString(),FontFactory.getFont(FontFactory.TIMES_BOLD,14f)));
            titulo.setAlignment(Element.ALIGN_LEFT);
            documento.add(nome_cliente);
            
            Paragraph peca= new Paragraph(new Phrase(20f,"Peça",FontFactory.getFont(FontFactory.HELVETICA_BOLD,12f)));
            peca.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph preco= new Paragraph(new Phrase(20f,"Preço:",FontFactory.getFont(FontFactory.HELVETICA_BOLD,12f)));
            preco.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph quantidade= new Paragraph(new Phrase(20f,"Quantidade",FontFactory.getFont(FontFactory.HELVETICA_BOLD,12f)));
            quantidade.setAlignment(Element.ALIGN_CENTER);
            
            Cell celulaPeca=new Cell(peca);
            Cell celulaPreco=new Cell(preco);
            Cell celulaQuant=new Cell(quantidade);
            Cell celulaObs=new Cell("OBS");
            
            celulaPeca.setHorizontalAlignment(Element.ALIGN_CENTER);
            celulaPreco.setHorizontalAlignment(Element.ALIGN_CENTER);
            celulaQuant.setHorizontalAlignment(Element.ALIGN_CENTER);
            celulaObs.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            celulaPeca.setBorderColor(new Color(210,185,30));
            celulaPreco.setBorderColor(new Color(210,185,30));
            celulaQuant.setBorderColor(new Color(210,185,30));
            celulaObs.setBorderColor(new Color(210,185,30));
            
            celulaPeca.setBackgroundColor(new Color(210,149,30));
            celulaPreco.setBackgroundColor(new Color(210,149,30));
            celulaQuant.setBackgroundColor(new Color(210,149,30));
            celulaObs.setBackgroundColor(new Color(210,149,30));
            
            tabelaOrcamento.addCell(celulaPeca);
            tabelaOrcamento.addCell(celulaPreco);
            tabelaOrcamento.addCell(celulaQuant);
            tabelaOrcamento.addCell(celulaObs);
            
            int totalRows=jTable1.getRowCount();
            ArrayList<Orcamento> orcamentos = new ArrayList<>();
            for(int i=0;i<totalRows;i++){
                Orcamento o= new Orcamento();
                o.setPeca((String) jTable1.getModel().getValueAt(i, 0));
                o.setPreco((String)jTable1.getModel().getValueAt(i, 1));
                o.setQuantidade((String) jTable1.getModel().getValueAt(i, 2));
                orcamentos.add(o);
            }
            for(Orcamento o1:orcamentos){
                tabelaOrcamento.addCell(o1.getPeca());
                tabelaOrcamento.addCell(o1.getPreco());
                tabelaOrcamento.addCell(o1.getQuantidade());
                tabelaOrcamento.addCell("Nenhuma Observação");
            }
            documento.add(tabelaOrcamento);
            Paragraph total2= new Paragraph(new Phrase(20f,"Total: R$ "+ this.total.toString(),FontFactory.getFont(FontFactory.TIMES_BOLD,14f)));
            titulo.setAlignment(Element.ALIGN_LEFT);
            documento.add(total2);
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
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Orçamento");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nome:");

        jComboBox1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Peça:");

        jComboBox2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Quant:");

        jTextField1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/adicao.png"))); // NOI18N
        jButton1.setText("Adicionar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ficheiro-pdf (1).png"))); // NOI18N
        jButton2.setText("Gerar PDF");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excluir.png"))); // NOI18N
        jButton3.setText("Remover");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(8, 8, 8)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField1)
                            .addComponent(jComboBox2, 0, 188, Short.MAX_VALUE))))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        PecasDAO pdao=new PecasDAO();
        for(Pecas p:pdao.read3(jComboBox2.getSelectedItem().toString())){
            preco=p.getPreco();   
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        preco_normal= Double.parseDouble(preco);
        quantidade_normal=Integer.parseInt(jTextField1.getText());
        subtotal= preco_normal*quantidade_normal;
        subtotal2=subtotal;
        total+=subtotal2;
        Orcamento o= new Orcamento();
        o.setPeca(jComboBox2.getSelectedItem().toString());
        o.setPreco(preco);
        o.setQuantidade(jTextField1.getText());
        tableModel.addRow(o);
        
           
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        gerarRelatorio();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(jTable1.getSelectedRow()!= -1){
            subtotal_cancelado=preco_cancelado*quantidade_cancelado;
            subtotal2=subtotal_cancelado;
            total-=subtotal2;
            tableModel.removeRow(jTable1.getSelectedRow());
            JOptionPane.showMessageDialog(null, "Item Removido com Sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
           
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int indicelinha=jTable1.getSelectedRow();
        String nome=jTable1.getValueAt(indicelinha, 0).toString();
        preco_cancelado=Double.parseDouble(jTable1.getValueAt(indicelinha, 1).toString());
        quantidade_cancelado=Integer.parseInt(jTable1.getValueAt(indicelinha, 2).toString());
    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(ViewTelaOrcamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewTelaOrcamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewTelaOrcamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewTelaOrcamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewTelaOrcamento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
