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
public class Carrinho {
    private String nome;
    private String val_unitario;
    private String quantidade;
    private String subtotal;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getVal_unitario() {
        return val_unitario;
    }

    public void setVal_unitario(String val_unitario) {
        this.val_unitario = val_unitario;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public Carrinho() {
    }

    public Carrinho(String nome, String val_unitario, String quantidade, String subtotal) {
        this.nome = nome;
        this.val_unitario = val_unitario;
        this.quantidade = quantidade;
        this.subtotal = subtotal;
    }
    
}
