/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author GabrielFSampaio
 */
public class CarrinhoTableModel extends AbstractTableModel {

    private List<Carrinho> dados=new ArrayList<>();
    private String[] colunas= {"Nome","Val.Unitário","Quantidade","Subtotal"};
    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
       switch(coluna){
           case 0:
               return dados.get(linha).getNome();
           case 1:
               return dados.get(linha).getVal_unitario();
           case 2:
               return dados.get(linha).getQuantidade();
           case 3:
               return dados.get(linha).getSubtotal();
       }
       return null;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    public void addRow(Carrinho c){
        this.dados.add(c);
        this.fireTableDataChanged();
    }
    
    
}
