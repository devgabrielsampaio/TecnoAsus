/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Entrada;
import util.Conexao;

/**
 *
 * @author GabrielFSampaio
 */
public class EntradaDAO {
    public List<Entrada> read(String login1, String senha1){
        List<Entrada>entradas=new ArrayList<>();
        String url="jdbc:mysql://localhost:3306/tecnoasus?useTimezone=true&serverTimezone=UTC";
        String driver = "com.mysql.cj.jdbc.Driver";
	String login = "root";
	String senha = "";
        Conexao conexao = new Conexao(url, driver, login, senha);
        Connection con = conexao.obterConexao();
		
		String sql = "select `Nivel` from `entrada` where `Login` = ? and `Senha`=?";
		
		try {
			PreparedStatement comando = con.prepareStatement(sql);
			comando.setString(1, login1);
			comando.setString(2, senha1);
			ResultSet rs = comando.executeQuery();
			while(rs.next()) {
				Entrada e= new Entrada();
				e.setNivel(rs.getString("Nivel"));
                                entradas.add(e);
                                
			}
			rs.close();
			comando.close();
			con.close();
		} catch(SQLException error) {
			System.out.println("Erro ao Entrar No Sistema");
			System.out.println("Verifique sua instrução SQL");
			System.out.println("Mensagem: "+error.getMessage());
		}
                return entradas; 
    }
     public List<Entrada> read2(String login1){
        List<Entrada>entradas=new ArrayList<>();
        String url="jdbc:mysql://localhost:3306/tecnoasus?useTimezone=true&serverTimezone=UTC";
        String driver = "com.mysql.cj.jdbc.Driver";
	String login = "root";
	String senha = "";
        Conexao conexao = new Conexao(url, driver, login, senha);
        Connection con = conexao.obterConexao();
		
		String sql = "select `Nivel` from `entrada` where `Login` = ?";
		
		try {
			PreparedStatement comando = con.prepareStatement(sql);
			comando.setString(1, login1);
			ResultSet rs = comando.executeQuery();
			while(rs.next()) {
				Entrada e= new Entrada();
				e.setNivel(rs.getString("Nivel"));
                                entradas.add(e);
                                
			}
			rs.close();
			comando.close();
			con.close();
		} catch(SQLException error) {
			System.out.println("Erro ao Entrar No Sistema");
			System.out.println("Verifique sua instrução SQL");
			System.out.println("Mensagem: "+error.getMessage());
		}
                return entradas; 
    }
    public List<Entrada> read3(String senha1){
        List<Entrada>entradas=new ArrayList<>();
        String url="jdbc:mysql://localhost:3306/tecnoasus?useTimezone=true&serverTimezone=UTC";
        String driver = "com.mysql.cj.jdbc.Driver";
	String login = "root";
	String senha = "";
        Conexao conexao = new Conexao(url, driver, login, senha);
        Connection con = conexao.obterConexao();
		
		String sql = "select `Nivel` from `entrada` where `Senha` = ?";
		
		try {
			PreparedStatement comando = con.prepareStatement(sql);
			comando.setString(1, senha1);
			ResultSet rs = comando.executeQuery();
			while(rs.next()) {
				Entrada e= new Entrada();
				e.setNivel(rs.getString("Nivel"));
                                entradas.add(e);
                                
			}
			rs.close();
			comando.close();
			con.close();
		} catch(SQLException error) {
			System.out.println("Erro ao Entrar No Sistema");
			System.out.println("Verifique sua instrução SQL");
			System.out.println("Mensagem: "+error.getMessage());
		}
                return entradas; 
    }
    public List<Entrada> readConsult(){
        List<Entrada>entradas=new ArrayList<>();
        String url="jdbc:mysql://localhost:3306/tecnoasus?useTimezone=true&serverTimezone=UTC";
        String driver = "com.mysql.cj.jdbc.Driver";
	String login = "root";
	String senha = "";
        Conexao conexao = new Conexao(url, driver, login, senha);
        Connection con = conexao.obterConexao();
		
		String sql = "select login,password(senha) as senha,nivel,email from `entrada`";
		
		try {
			PreparedStatement comando = con.prepareStatement(sql);
			ResultSet rs = comando.executeQuery();
			while(rs.next()) {
				Entrada e= new Entrada();
                                e.setLogin(rs.getString("login"));
                                e.setSenha(rs.getString("senha"));
				e.setNivel(rs.getString("Nivel"));
                                e.setEmail(rs.getString("email"));
                                entradas.add(e);
                                
			}
			rs.close();
			comando.close();
			con.close();
		} catch(SQLException error) {
			System.out.println("Erro ao Entrar No Sistema");
			System.out.println("Verifique sua instrução SQL");
			System.out.println("Mensagem: "+error.getMessage());
		}
                return entradas; 
    }
    public List<Entrada> readConsultLogin(String login1){
        List<Entrada>entradas=new ArrayList<>();
        String url="jdbc:mysql://localhost:3306/tecnoasus?useTimezone=true&serverTimezone=UTC";
        String driver = "com.mysql.cj.jdbc.Driver";
	String login = "root";
	String senha = "";
        Conexao conexao = new Conexao(url, driver, login, senha);
        Connection con = conexao.obterConexao();
		
		String sql = "select login,password(senha) as senha,nivel,email from `entrada` where `Login` like ?";
		
		try {
			PreparedStatement comando = con.prepareStatement(sql);
                        comando.setString(1, login1+"%");
			ResultSet rs = comando.executeQuery();
			while(rs.next()) {
				Entrada e= new Entrada();
                                e.setLogin(rs.getString("login"));
                                e.setSenha(rs.getString("senha"));
				e.setNivel(rs.getString("Nivel"));
                                e.setEmail(rs.getString("email"));
                                entradas.add(e);
                                
			}
			rs.close();
			comando.close();
			con.close();
		} catch(SQLException error) {
			System.out.println("Erro ao Entrar No Sistema");
			System.out.println("Verifique sua instrução SQL");
			System.out.println("Mensagem: "+error.getMessage());
		}
                return entradas; 
    }
    public static Entrada inserir(String login1,String senha1, String nivel, String email) {
		Entrada entrada=null;
		
		String url = "jdbc:mysql://localhost:3306/tecnoasus?useTimezone=true&serverTimezone=UTC";
		String driver = "com.mysql.cj.jdbc.Driver";
		String login = "root";
		String senha = "";
		Conexao conexao = new Conexao(url, driver, login, senha);
		
		Connection con = conexao.obterConexao();
		
		String sql = "insert into entrada(login,senha,nivel,email) values(?,?,?,?)";
		try {
			PreparedStatement comando = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			comando.setString(1, login1);
                        comando.setString(2, senha1);
                        comando.setString(3, nivel);
                        comando.setString(4, email);
			if(comando.executeUpdate()>0) {
				ResultSet rs = comando.getGeneratedKeys();
				if(rs.next()) {
                                    entrada= new Entrada(login1, senha1, nivel, email);
				}
				rs.close();
			}
			
			comando.close();
			con.close();
                }catch(SQLException e) {
			System.out.println("Erro ao inserir no Banco de Dados.");
			System.out.println("Verifique sua instrução SQL.");
			System.out.println("Mensagem: "+e.getMessage());
		}
		return entrada;	
    }
    public static boolean excluir(String login1) {
		boolean ok = false;
		
		String url = "jdbc:mysql://localhost:3306/tecnoasus?useTimezone=true&serverTimezone=UTC";
		String driver = "com.mysql.cj.jdbc.Driver";
		String login = "root";
		String senha = "";
		Conexao conexao = new Conexao(url, driver, login, senha);
		
		Connection con = conexao.obterConexao();
		
		String sql = "delete from entrada where login=?";
		
		try {
			PreparedStatement comando = con.prepareStatement(sql);
			comando.setString(1, login1);
			ok = comando.executeUpdate() > 0;
			comando.close();
			con.close();
			
		} catch (SQLException e) {
			System.out.println("Erro ao excluir do Banco de Dados");
			System.out.println("Verifique sua instrução SQL");
			System.out.println("Mensagem: "+e.getMessage());
		}
		return ok;
	}
    
}
