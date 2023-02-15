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
public class Entrada {
    private String Login;
    private String Senha;
    private String Nivel;
    private String Email;

    public String getLogin() {
        return Login;
    }

    public void setLogin(String Login) {
        this.Login = Login;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
    }

    public String getNivel() {
        return Nivel;
    }

    public void setNivel(String Nivel) {
        this.Nivel = Nivel;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public Entrada(String Login, String Senha, String Nivel, String Email) {
        this.Login = Login;
        this.Senha = Senha;
        this.Nivel = Nivel;
        this.Email = Email;
    }

    public Entrada() {
    }
    

    @Override
    public String toString() {
        return "Entrada{" + "Login=" + Login + ", Senha=" + Senha + ", Nivel=" + Nivel + ", Email=" + Email + '}';
    }
    
}
