/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author GabrielFSampaio
 */
public class Orcamento {
    private String peca;
    private String preco;
    private String quantidade;

    public Orcamento() {
    }

    public Orcamento(String peca, String preco, String quantidade) {
        this.peca = peca;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getPeca() {
        return peca;
    }

    public void setPeca(String peca) {
        this.peca = peca;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    
    
}
