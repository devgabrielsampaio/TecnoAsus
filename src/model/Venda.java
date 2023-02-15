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
public class Venda {
    private int id;
    private String metodo;
    private String total;
    private String troco;
    private String valor;
    private String data;
    private String hora;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTroco() {
        return troco;
    }

    public void setTroco(String troco) {
        this.troco = troco;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Venda() {
    }

    public Venda(int id, String metodo, String total, String troco, String valor, String data, String hora) {
        this.id = id;
        this.metodo = metodo;
        this.total = total;
        this.troco = troco;
        this.valor = valor;
        this.data = data;
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "Venda{" + "id=" + id + ", metodo=" + metodo + ", total=" + total + ", troco=" + troco + ", valor=" + valor + ", data=" + data + ", hora=" + hora + '}';
    }
    
}
