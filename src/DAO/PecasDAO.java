/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Pecas;
import util.Conexao;

/**
 *
 * @author GabrielFSampaio
 */
public class PecasDAO {
    public List<Pecas> read(){
        List<Pecas> peca=new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/tecnoasus?useTimezone=true&serverTimezone=UTC";
		String driver = "com.mysql.cj.jdbc.Driver";
		String login = "root";
		String senha = "";
		Conexao conexao = new Conexao(url, driver, login, senha);
		
		Connection con = conexao.obterConexao();
		
		String sql = "select * from pecas order by id";
		
		try {
			Statement comando = con.createStatement();
			ResultSet rs = comando.executeQuery(sql);
			
			while(rs.next()) {
				Pecas p = new Pecas();
				p.setId(rs.getInt("id"));
				p.setNome(rs.getString("nome"));
				p.setPreco(rs.getString("preco"));
                                p.setQuantidade(rs.getString("quantidade"));
				peca.add(p);
			}
			rs.close();
			comando.close();
			con.close();
			
		} catch (SQLException e) {
			System.out.println("Erro ao buscar no Banco de Dados");
			System.out.println("Verifique sua instrução SQL");
			System.out.println("Mensagem: "+e.getMessage());
		}
                return peca;
    }
    public List<Pecas> read2(String nome){
         List<Pecas>pecas=new ArrayList<>();
                String url = "jdbc:mysql://localhost:3306/tecnoasus?useTimezone=true&serverTimezone=UTC";
		String driver = "com.mysql.cj.jdbc.Driver";
		String login = "root";
		String senha = "";
		Conexao conexao = new Conexao(url, driver, login, senha);
		
		Connection con = conexao.obterConexao();
		
		String sql = "select * from pecas where nome like ?";
                
		
		try {
                    try (PreparedStatement comando = con.prepareStatement(sql)) {
                        comando.setString(1, nome+"%");
                        ResultSet rs=comando.executeQuery();
                        while(rs.next()) {
                            Pecas p = new Pecas();
                            p.setId(rs.getInt("id"));
                            p.setNome(rs.getString("nome"));
                            p.setPreco(rs.getString("preco"));
                            p.setQuantidade(rs.getString("quantidade"));
                            pecas.add(p);
                        }
                        rs.close();
                        con.close();
                    }
			
			
		} catch (SQLException e) {
			System.out.println("Erro ao buscar no Banco de Dados");
			System.out.println("Verifique sua instrução SQL");
			System.out.println("Mensagem: "+e.getMessage());
		}
                return pecas;
    }
     public static Pecas inserir(String nome,String preco, String quantidade) {
		Pecas peca=null;
		
		String url = "jdbc:mysql://localhost:3306/tecnoasus?useTimezone=true&serverTimezone=UTC";
		String driver = "com.mysql.cj.jdbc.Driver";
		String login = "root";
		String senha = "";
		Conexao conexao = new Conexao(url, driver, login, senha);
		
		Connection con = conexao.obterConexao();
		
		String sql = "insert into pecas(nome,preco,quantidade) values(?,?,?)";
		try {
			PreparedStatement comando = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			comando.setString(1,nome);
			comando.setString(2,preco);
			comando.setString(3,quantidade);
			
			if(comando.executeUpdate()>0) {
				ResultSet rs = comando.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					peca=new Pecas(id,nome,preco,quantidade);
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
		return peca;	
    }
     public static boolean excluir(int id) {
		boolean ok = false;
		
		String url = "jdbc:mysql://localhost:3306/tecnoasus?useTimezone=true&serverTimezone=UTC";
		String driver = "com.mysql.cj.jdbc.Driver";
		String login = "root";
		String senha = "";
		Conexao conexao = new Conexao(url, driver, login, senha);
		
		Connection con = conexao.obterConexao();
		
		String sql = "delete from pecas where id=?";
		
		try {
			PreparedStatement comando = con.prepareStatement(sql);
			comando.setInt(1, id);
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
      public static boolean atualizar(int id, String novoNome, String novoPreco, String novoQuantidade) {
		boolean ok = false;
		
		String url = "jdbc:mysql://localhost:3306/tecnoasus?useTimezone=true&serverTimezone=UTC";
		String driver = "com.mysql.cj.jdbc.Driver";
		String login = "root";
		String senha = "";
		Conexao conexao = new Conexao(url, driver, login, senha);
		
		Connection con = conexao.obterConexao();
		
		String sql = "update pecas set nome= ?, preco= ?,quantidade= ? where id = ?";
		
		try {
			PreparedStatement comando = con.prepareStatement(sql);
			comando.setString(1, novoNome);
                        comando.setString(2, novoPreco);
                        comando.setString(3, novoQuantidade);
                        comando.setInt(4, id);
			ok = comando.executeUpdate() > 0;
			comando.close();
			con.close();
			
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar mensagem no Banco de Dados");
			System.out.println("Verifique sua instrução SQL");
			System.out.println("Mensagem: "+e.getMessage());
		}
		return ok;
	}
      public List<Pecas> consulta(int id){
        List<Pecas>peca=new ArrayList<>();
        String url="jdbc:mysql://localhost:3306/tecnoasus?useTimezone=true&serverTimezone=UTC";
        String driver = "com.mysql.cj.jdbc.Driver";
	String login = "root";
	String senha = "";
        Conexao conexao = new Conexao(url, driver, login, senha);
        Connection con = conexao.obterConexao();
		
		String sql = "select `quantidade` from `pecas` where `id`= ?";
		
		try {
			PreparedStatement comando = con.prepareStatement(sql);
			comando.setInt(1, id);
			ResultSet rs = comando.executeQuery();
			while(rs.next()) {
				Pecas p= new Pecas();
                                p.setQuantidade(rs.getString("quantidade"));
                                peca.add(p);
			}
			rs.close();
			comando.close();
			con.close();
		} catch(SQLException error) {
			System.out.println("Erro ao Entrar No Sistema");
			System.out.println("Verifique sua instrução SQL");
			System.out.println("Mensagem: "+error.getMessage());
		}
                return peca; 
    }
      public static boolean atualizar_estoque1(String novaQuantidade,int id) {
		boolean ok = false;
		
		String url = "jdbc:mysql://localhost:3306/tecnoasus?useTimezone=true&serverTimezone=UTC";
		String driver = "com.mysql.cj.jdbc.Driver";
		String login = "root";
		String senha = "";
		Conexao conexao = new Conexao(url, driver, login, senha);
		
		Connection con = conexao.obterConexao();
		
		String sql = "update pecas set `quantidade`=`quantidade`- ? where id = ?";
		
		try {
			PreparedStatement comando = con.prepareStatement(sql);
			comando.setString(1, novaQuantidade);                 
                        comando.setInt(2, id);
			ok = comando.executeUpdate() > 0;
			comando.close();
			con.close();
			
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar mensagem no Banco de Dados");
			System.out.println("Verifique sua instrução SQL");
			System.out.println("Mensagem: "+e.getMessage());
		}
		return ok;
	}
      public static boolean atualizar_estoque2(String novaQuantidade,String nome) {
		boolean ok = false;
		
		String url = "jdbc:mysql://localhost:3306/tecnoasus?useTimezone=true&serverTimezone=UTC";
		String driver = "com.mysql.cj.jdbc.Driver";
		String login = "root";
		String senha = "";
		Conexao conexao = new Conexao(url, driver, login, senha);
		
		Connection con = conexao.obterConexao();
		
		String sql = "update pecas set `quantidade`=`quantidade`+ ? where nome like ?";
		
		try {
			PreparedStatement comando = con.prepareStatement(sql);
			comando.setString(1, novaQuantidade);                 
                        comando.setString(2, nome+"%");
			ok = comando.executeUpdate() > 0;
			comando.close();
			con.close();
			
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar mensagem no Banco de Dados");
			System.out.println("Verifique sua instrução SQL");
			System.out.println("Mensagem: "+e.getMessage());
		}
		return ok;
	}
      public List<Pecas> read3(String nome){
         List<Pecas>pecas=new ArrayList<>();
                String url = "jdbc:mysql://localhost:3306/tecnoasus?useTimezone=true&serverTimezone=UTC";
		String driver = "com.mysql.cj.jdbc.Driver";
		String login = "root";
		String senha = "";
		Conexao conexao = new Conexao(url, driver, login, senha);
		
		Connection con = conexao.obterConexao();
		
		String sql = "select `Preco` from `pecas` where nome like ?";
                
		
		try {
                    try (PreparedStatement comando = con.prepareStatement(sql)) {
                        comando.setString(1, nome+"%");
                        ResultSet rs=comando.executeQuery();
                        while(rs.next()) {
                            Pecas p = new Pecas();
                            p.setPreco(rs.getString("preco"));
                            pecas.add(p);
                        }
                        rs.close();
                        con.close();
                    }
			
			
		} catch (SQLException e) {
			System.out.println("Erro ao buscar no Banco de Dados");
			System.out.println("Verifique sua instrução SQL");
			System.out.println("Mensagem: "+e.getMessage());
		}
                return pecas;
    }
     
}
