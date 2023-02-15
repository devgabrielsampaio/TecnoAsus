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
import model.Cliente;
import util.Conexao;

/**
 *
 * @author GabrielFSampaio
 */
public class ClienteDAO {
     public List<Cliente> read(){
        List<Cliente> clientes=new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/tecnoasus?useTimezone=true&serverTimezone=UTC";
		String driver = "com.mysql.cj.jdbc.Driver";
		String login = "root";
		String senha = "";
		Conexao conexao = new Conexao(url, driver, login, senha);
		
		Connection con = conexao.obterConexao();
		
		String sql = "select * from cliente order by id";
		
		try {
			Statement comando = con.createStatement();
			ResultSet rs = comando.executeQuery(sql);
			
			while(rs.next()) {
				Cliente c = new Cliente();
				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				c.setEmail(rs.getString("email"));
                                c.setTelefone(rs.getString("telefone"));
                                c.setCep(rs.getString("cep"));
                                c.setLogradouro(rs.getString("logradouro"));
                                c.setNumero(rs.getString("numero"));
                                c.setBairro(rs.getString("bairro"));
                                c.setCidade(rs.getString("cidade"));
                                c.setEstado(rs.getString("estado"));
                                c.setComplemento(rs.getString("complemento"));
                                c.setCpf(rs.getString("cpf"));
                                clientes.add(c);
                             }
			rs.close();
			comando.close();
			con.close();
			
		} catch (SQLException e) {
			System.out.println("Erro ao buscar no Banco de Dados");
			System.out.println("Verifique sua instrução SQL");
			System.out.println("Mensagem: "+e.getMessage());
		}
                return clientes;
    }
     public List<Cliente> read2(String nome){
         List<Cliente>clientes=new ArrayList<>();
                String url = "jdbc:mysql://localhost:3306/tecnoasus?useTimezone=true&serverTimezone=UTC";
		String driver = "com.mysql.cj.jdbc.Driver";
		String login = "root";
		String senha = "";
		Conexao conexao = new Conexao(url, driver, login, senha);
		
		Connection con = conexao.obterConexao();
		
		String sql = "select * from cliente where nome like ?";
                
		
		try {
                    try (PreparedStatement comando = con.prepareStatement(sql)) {
                        comando.setString(1, nome+"%");
                        ResultSet rs=comando.executeQuery();
                        while(rs.next()) {
                            Cliente c = new Cliente();
                            c.setId(rs.getInt("id"));
                            c.setNome(rs.getString("nome"));
                            c.setEmail(rs.getString("email"));
                            c.setTelefone(rs.getString("telefone"));
                            c.setCep(rs.getString("cep"));
                            c.setLogradouro(rs.getString("logradouro"));
                            c.setNumero(rs.getString("numero"));
                            c.setBairro(rs.getString("bairro"));
                            c.setCidade(rs.getString("cidade"));
                            c.setEstado(rs.getString("estado"));
                            c.setComplemento(rs.getString("complemento"));
                            c.setCpf(rs.getString("cpf"));
                            clientes.add(c);
                        }
                        rs.close();
                        con.close();
                    }
			
			
		} catch (SQLException e) {
			System.out.println("Erro ao buscar no Banco de Dados");
			System.out.println("Verifique sua instrução SQL");
			System.out.println("Mensagem: "+e.getMessage());
		}
                return clientes;
    }
     public static Cliente inserir(String nome,String email, String telefone,String cep, String logradouro, String numero, String bairro, String cidade, String estado, String cpf, String complemento) {
		Cliente cliente=null;
		
		String url = "jdbc:mysql://localhost:3306/tecnoasus?useTimezone=true&serverTimezone=UTC";
		String driver = "com.mysql.cj.jdbc.Driver";
		String login = "root";
		String senha = "";
		Conexao conexao = new Conexao(url, driver, login, senha);
		
		Connection con = conexao.obterConexao();
		
		String sql = "insert into cliente(nome,email,telefone,cep,logradouro,numero,bairro,cidade,estado,cpf,complemento) values(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement comando = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			comando.setString(1,nome);
			comando.setString(2,email);
			comando.setString(3,telefone);
			comando.setString(4,cep);
                        comando.setString(5,logradouro);
                        comando.setString(6,numero);
                        comando.setString(7,bairro);
                        comando.setString(8,cidade);
                        comando.setString(9,estado);
                        comando.setString(10,cpf);
                        comando.setString(11,complemento);
			if(comando.executeUpdate()>0) {
				ResultSet rs = comando.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					cliente= new Cliente(id, nome, email, telefone, cep, logradouro, numero, bairro, cidade, estado, cpf, complemento);
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
		return cliente;	
    }
     public static boolean excluir(int id) {
		boolean ok = false;
		
		String url = "jdbc:mysql://localhost:3306/tecnoasus?useTimezone=true&serverTimezone=UTC";
		String driver = "com.mysql.cj.jdbc.Driver";
		String login = "root";
		String senha = "";
		Conexao conexao = new Conexao(url, driver, login, senha);
		
		Connection con = conexao.obterConexao();
		
		String sql = "delete from cliente where id=?";
		
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
     public static boolean atualizar( String novoNome,String novoEmail, String novoTelefone,String novoCep, String novoLogradouro, String novoNumero, String novoBairro, String novoCidade, String novoEstado, String novoCpf, String novoComplemento,int id) {
		boolean ok = false;
		
		String url = "jdbc:mysql://localhost:3306/tecnoasus?useTimezone=true&serverTimezone=UTC";
		String driver = "com.mysql.cj.jdbc.Driver";
		String login = "root";
		String senha = "";
		Conexao conexao = new Conexao(url, driver, login, senha);
		
		Connection con = conexao.obterConexao();
		
		String sql = "update cliente set nome=?,email=?,telefone=?,cep=?,logradouro=?,numero=?,bairro=?,cidade=?,estado=?,cpf=?,complemento=?  where id = ?";
		
		try {
			PreparedStatement comando = con.prepareStatement(sql);
			comando.setString(1, novoNome);
                        comando.setString(2, novoEmail);
                        comando.setString(3, novoTelefone);
                        comando.setString(4, novoCep);
                        comando.setString(5, novoLogradouro);
                        comando.setString(6, novoNumero);
                        comando.setString(7, novoBairro);
                        comando.setString(8, novoCidade);
                        comando.setString(9, novoEstado);
                        comando.setString(10, novoCpf);
                        comando.setString(11, novoComplemento);
                        comando.setInt(12, id);
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
}
