/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DAO.EntradaDAO;
import DAO.PecasDAO;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import model.Carrinho;
import model.CarrinhoTableModel;
import model.Entrada;
import model.Orcamento;
import model.Pecas;

/**
 *
 * @author GabrielFSampaio
 */
public class ViewTelaVenda extends javax.swing.JFrame {
Double preco=0.0,subtotal=0.0,subtotal2=0.0,total=0.0,subtotal_cancelado=0.0,subtotal_cancelado2=0.0,total_cancelado=0.0,valor_finalizado=0.0,troco_finalizado=0.0;
int quantidade,id,quantidade_consulta,quantidade_cancelamento;
String nome,login2,nivel,nome2,val_unitario_cancelamento;
CarrinhoTableModel tableModel= new CarrinhoTableModel();
ViewTelaFechamento v1;
    /**
     * Creates new form ViewTelaVenda
     */
    public ViewTelaVenda() {
        initComponents();
        setIcon();
        readJTable();
        jTable2.setModel(tableModel);
        jTextField3.setVisible(false);
    } 

    public void setIcon(){
        setIconImage(new ImageIcon(getClass().getResource("/img/logo.png")).getImage());
    } 
    
    public void readJTable(){
        DefaultTableModel modelo=(DefaultTableModel) jTable1.getModel();
        modelo.setNumRows(0);
        PecasDAO pdao=new PecasDAO();
        for(Pecas p:pdao.read()){
            modelo.addRow(new Object[]{
                p.getId(),
                p.getNome(),
                p.getPreco(),
                p.getQuantidade()
                
            });
        }
    }
    public void readJTable2(){
        DefaultTableModel modelo=(DefaultTableModel) jTable1.getModel();
        modelo.setNumRows(0);
        PecasDAO pdao=new PecasDAO();
        for(Pecas p:pdao.read2(jTextField1.getText())){
            modelo.addRow(new Object[]{
                p.getId(),
                p.getNome(),
                p.getPreco(),
                p.getQuantidade()
                
            });
        }
    }
    public void gerarCupomFiscal(){
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
                com.lowagie.text.Image imagem= com.lowagie.text.Image.getInstance(getClass().getResource("/img/logo_Splash.jpeg"));
                imagem.scaleAbsolute(100f, 100f);
                imagem.setAlignment(Element.ALIGN_LEFT);
                documento.add(imagem);
            } catch (BadElementException ex) {
                Logger.getLogger(ViewRelatorioPecas.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ViewRelatorioPecas.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Paragraph titulo= new Paragraph(new Phrase(20f,"Cupom Não Fiscal",FontFactory.getFont(FontFactory.TIMES_BOLD,16f)));
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);
            Table tabelaVenda=new Table(4);
            tabelaVenda.setPadding(5);
            tabelaVenda.setSpacing(0);
            tabelaVenda.setBorder(3);
            tabelaVenda.setBorderWidth(3);
            tabelaVenda.setBorderColor(new Color(210,105,30));
            tabelaVenda.setWidth(100f);
            tabelaVenda.setWidths(new float[]{15f,15f,15f,15f});
            
            Paragraph peca1= new Paragraph(new Phrase(20f,"Peça",FontFactory.getFont(FontFactory.HELVETICA_BOLD,12f)));
            peca1.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph preco1= new Paragraph(new Phrase(20f,"Val.Unitário:",FontFactory.getFont(FontFactory.HELVETICA_BOLD,12f)));
            preco1.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph quantidade1= new Paragraph(new Phrase(20f,"Quantidade",FontFactory.getFont(FontFactory.HELVETICA_BOLD,12f)));
            quantidade1.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph subtotal1= new Paragraph(new Phrase(20f,"Subtotal",FontFactory.getFont(FontFactory.HELVETICA_BOLD,12f)));
            subtotal1.setAlignment(Element.ALIGN_CENTER);
            
            Cell celulaPeca=new Cell(peca1);
            Cell celulaPreco=new Cell(preco1);
            Cell celulaQuant=new Cell(quantidade1);
            Cell celulaSubtotal=new Cell(subtotal1);
            
            celulaPeca.setHorizontalAlignment(Element.ALIGN_CENTER);
            celulaPreco.setHorizontalAlignment(Element.ALIGN_CENTER);
            celulaQuant.setHorizontalAlignment(Element.ALIGN_CENTER);
            celulaSubtotal.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            celulaPeca.setBorderColor(new Color(210,185,30));
            celulaPreco.setBorderColor(new Color(210,185,30));
            celulaQuant.setBorderColor(new Color(210,185,30));
            celulaSubtotal.setBorderColor(new Color(210,185,30));
            
            celulaPeca.setBackgroundColor(new Color(210,149,30));
            celulaPreco.setBackgroundColor(new Color(210,149,30));
            celulaQuant.setBackgroundColor(new Color(210,149,30));
            celulaSubtotal.setBackgroundColor(new Color(210,149,30));
            
            tabelaVenda.addCell(celulaPeca);
            tabelaVenda.addCell(celulaPreco);
            tabelaVenda.addCell(celulaQuant);
            tabelaVenda.addCell(celulaSubtotal);
            
            int totalRows=jTable2.getRowCount();
            ArrayList<Carrinho> carrinhos = new ArrayList<>();
            for(int i=0;i<totalRows;i++){
                Carrinho c= new Carrinho();
                c.setNome(jTable2.getModel().getValueAt(i, 0).toString());
                c.setVal_unitario((jTable2.getModel().getValueAt(i, 1).toString()));
                c.setQuantidade(( jTable2.getModel().getValueAt(i, 2).toString()));
                c.setSubtotal((jTable2.getModel().getValueAt(i, 3).toString()));
                carrinhos.add(c);
            }
            for(Carrinho c1:carrinhos){
                tabelaVenda.addCell(c1.getNome());
                tabelaVenda.addCell(c1.getVal_unitario());
                tabelaVenda.addCell(c1.getQuantidade());
                tabelaVenda.addCell(c1.getSubtotal());
            }
            documento.add(tabelaVenda);
            Paragraph valor2= new Paragraph(new Phrase(20f,"Valor: R$ "+ valor_finalizado.toString(),FontFactory.getFont(FontFactory.TIMES_BOLD,14f)));
            titulo.setAlignment(Element.ALIGN_LEFT);
            documento.add(valor2);
            
            Paragraph total2= new Paragraph(new Phrase(20f,"Total: R$ "+ this.total.toString(),FontFactory.getFont(FontFactory.TIMES_BOLD,14f)));
            titulo.setAlignment(Element.ALIGN_LEFT);
            documento.add(total2);
            
            Paragraph troco2= new Paragraph(new Phrase(20f,"Troco: R$ "+ troco_finalizado.toString(),FontFactory.getFont(FontFactory.TIMES_BOLD,14f)));
            titulo.setAlignment(Element.ALIGN_LEFT);
            documento.add(troco2);
            documento.close();
            JOptionPane.showMessageDialog(null, "Cupom Não Fiscal Gerado com Sucesso!","Sucesso",JOptionPane.INFORMATION_MESSAGE);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Vendas");
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel1KeyPressed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Peças", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Quantidade:");

        jTextField1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Preço", "Quantidade"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Digite o Nome da Peça:");

        jTextField2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Carrinho De Compras", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "", "", ""
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
        );

        jButton1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/adicao.png"))); // NOI18N
        jButton1.setText("Adicionar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancelar.png"))); // NOI18N
        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/verificar.png"))); // NOI18N
        jButton3.setText("Encerrar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                            .addComponent(jTextField3))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            readJTable2();
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if("".equals(jTextField2.getText())){
            JOptionPane.showMessageDialog(null, "Campo Quantidade Está Vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        else{
            PecasDAO pdao= new PecasDAO();
            for(Pecas p1:pdao.consulta(id)){
                quantidade_consulta=Integer.parseInt(p1.getQuantidade());
            }
            if(Integer.parseInt(jTextField2.getText())<=quantidade_consulta){
                quantidade= Integer.parseInt(jTextField2.getText());
                subtotal= preco * quantidade;
                subtotal2=subtotal;
                total+=subtotal2;
                jTextField3.setText(total.toString());
                boolean p= PecasDAO.atualizar_estoque1(jTextField2.getText(), id);
                Carrinho c= new Carrinho();
                c.setNome(nome);
                c.setVal_unitario(preco.toString());
                c.setQuantidade(jTextField2.getText());
                c.setSubtotal(subtotal.toString());
                tableModel.addRow(c);
                readJTable();
            }
            else{
                JOptionPane.showMessageDialog(null, "Quantidade em Estoque Não Disponível", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        EntradaDAO edao=new EntradaDAO();
        for(Entrada e1:edao.read2(login2)){
            nivel=e1.getNivel();
        }
        if(nivel.equals("Atendente")){
           ImageIcon img= new ImageIcon(getClass().getResource("/img/cadeado.png"));
           String resposta1 = (String)JOptionPane.showInputDialog(null, "Informe o Código de Segurança::", "Segurança", JOptionPane.QUESTION_MESSAGE,img,null,null);
           if(resposta1.equals("tecnoasus")){
                subtotal_cancelado2=subtotal_cancelado;
                total_cancelado+=subtotal_cancelado2;
                total-=total_cancelado;
                jTextField3.setText(total.toString());
                boolean p1= PecasDAO.atualizar_estoque2(Integer.toString(quantidade_cancelamento), nome2);
                Carrinho c= new Carrinho();
                c.setNome(nome2);
                c.setVal_unitario(val_unitario_cancelamento);
                c.setQuantidade(Integer.toString(quantidade_cancelamento));
                c.setSubtotal("-"+subtotal_cancelado.toString());
                tableModel.addRow(c);
                readJTable();
           }
           else{
               JOptionPane.showMessageDialog(null, "Código de Segurança Inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(nivel.equals("Administrador")){
           subtotal_cancelado2=subtotal_cancelado;
           total_cancelado+=subtotal_cancelado2;
           total-=total_cancelado;
           jTextField3.setText(total.toString());
           boolean p1= PecasDAO.atualizar_estoque2(Integer.toString(quantidade_cancelamento), nome2);
           Carrinho c= new Carrinho();
           c.setNome(nome2);
           c.setVal_unitario(val_unitario_cancelamento);
           c.setQuantidade(Integer.toString(quantidade_cancelamento));
           c.setSubtotal("-"+subtotal_cancelado.toString());
           tableModel.addRow(c);
           readJTable();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int indicelinha=jTable1.getSelectedRow();
        id=Integer.parseInt(jTable1.getValueAt(indicelinha, 0).toString());
        nome=jTable1.getValueAt(indicelinha, 1).toString();
        preco=Double.parseDouble(jTable1.getValueAt(indicelinha, 2).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        int indicelinha=jTable2.getSelectedRow();
        nome2=jTable2.getValueAt(indicelinha, 0).toString();
        val_unitario_cancelamento=jTable2.getValueAt(indicelinha, 1).toString();
        quantidade_cancelamento=Integer.parseInt(jTable2.getValueAt(indicelinha, 2).toString());
        subtotal_cancelado=Double.parseDouble(jTable2.getValueAt(indicelinha, 3).toString());
        
    }//GEN-LAST:event_jTable2MouseClicked

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
       
    }//GEN-LAST:event_formKeyPressed

    private void jPanel1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1KeyPressed
       
    }//GEN-LAST:event_jPanel1KeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        v1= new ViewTelaFechamento();
        v1.recebe(this,Double.parseDouble(jTextField3.getText()));
        v1.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(ViewTelaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewTelaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewTelaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewTelaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewTelaVenda().setVisible(true);
            }
        });
    }
    public void recebe(String login){
        login2=login;
    }
    public void recebe2(Double valor_compra,Double troco_compra){
        valor_finalizado=valor_compra;
        troco_finalizado=troco_compra;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
