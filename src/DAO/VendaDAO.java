/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Venda;

/**
 *
 * @author GabrielFSampaio
 */
public class VendaDAO {
    public static Venda inserir(String metodo,String total,String troco,String valor,String data,String hora) {
		Venda venda=null;
		String url = "jdbc:mysql://localhost:3306/tecnoasus?useTimezone=true&serverTimezone=UTC";
		String driver = "com.mysql.cj.jdbc.Driver";
		String login = "root";
		String senha = "";
		Conexao conexao = new Conexao(url, driver, login, senha);
		
		Connection con = conexao.obterConexao();
		
		String sql = "insert into venda(metodo,total,troco,valor,data,hora) values(?,?,?,?,?,?)";
		try {
			PreparedStatement comando = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                        comando.setString(1, metodo);
                        comando.setString(2, total);
                        comando.setString(3, troco);
                        comando.setString(4, valor);
                        comando.setString(5, data);
                        comando.setString(6, hora);
                      
			if(comando.executeUpdate()>0) {
				ResultSet rs = comando.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					venda=new Venda(id, metodo, total, troco, valor, data, hora);
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
		return venda;	
    }
    public Double retornaRendimentos(){
                Double recebidos = 0.0;
                String url = "jdbc:mysql://localhost:3306/tecnoasus?useTimezone=true&serverTimezone=UTC";
		String driver = "com.mysql.cj.jdbc.Driver";
		String login = "root";
		String senha = "";
		Conexao conexao = new Conexao(url, driver, login, senha);
		
		Connection con = conexao.obterConexao();
		
		String sql = "SELECT SUM(total)-SUM(troco) as recebidos FROM `venda`";
		
		try {
			PreparedStatement comando = con.prepareStatement(sql);
			ResultSet rs = comando.executeQuery(sql);
			
			while(rs.next()) {
				recebidos=rs.getDouble("recebidos");
			}
			rs.close();
			comando.close();
			con.close();
			
		} catch (SQLException e) {
			System.out.println("Erro ao buscar no Banco de Dados");
			System.out.println("Verifique sua instrução SQL");
			System.out.println("Mensagem: "+e.getMessage());
		}
                return recebidos;
    }
}
