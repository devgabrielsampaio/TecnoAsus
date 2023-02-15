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
public class OrcamentoTableModel extends AbstractTableModel {

    private List<Orcamento> dados=new ArrayList<>();
    private String[] colunas= {"Peça","Preço","Quantidade"};
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
               return dados.get(linha).getPeca();
           case 1:
               return dados.get(linha).getPreco();
           case 2:
               return dados.get(linha).getQuantidade();
       }
       return null;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    public void addRow(Orcamento p){
        this.dados.add(p);
        this.fireTableDataChanged();
    }
    public void removeRow(int linha){
        this.dados.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }
    
    
}
