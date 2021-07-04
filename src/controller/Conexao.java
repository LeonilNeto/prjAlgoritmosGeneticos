package controller;

import model.Livro;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 * @author Leonil
 */
public class Conexao {
    
    private static String usuario;
    private static String senha;
    private static String banco;
    private static String driver;
    private static Connection conn;
    
    /** 
     * Método responsável pela conexão do banco de dados local MySQL
     * @return Connection conn
     */    
    public static Connection conectar() {
        usuario = "root";
        senha = "";
        banco = "biblioteca";
        driver = "com.mysql.jdbc.Driver";
        conn = null;
        
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection("jdbc:mysql://localhost/"+banco, usuario, senha);
        } catch(ClassNotFoundException cnf) {
            System.out.println(cnf.getMessage());
        } catch(SQLException se) {
            JOptionPane.showMessageDialog(null, 
                    "Erro ao conectar a Base de Dados!\nO programa será finalizado...", 
                    "Erro de conexão",JOptionPane.ERROR_MESSAGE);
            System.out.println(se.getMessage());
            System.exit(1);
        } 
        return conn;
    }
    
    /** 
     * Método responsável por buscar todos os livros válidos da consulta no banco de dados
     * @return livros ArrayList de livros
     */
    public static ArrayList<Livro> buscar() {
        ArrayList<Livro> livros = null;
        try{         
            String sql = "select l.id, t.titulo, a.autor, baixa, disponivel, titulo_id, COUNT(el.livro_id) as "+
                    "EMPRESTIMO, buscas from livro l INNER JOIN emprestimo_livros el ON (l.id = el.livro_id) INNER JOIN "+ 
                    "titulo t ON (l.titulo_id = t.id) INNER JOIN autor a ON (a.id = t.autor_id) GROUP BY l.id";
            PreparedStatement ps = Conexao.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            rs.last();
            if(rs.getRow() > 0) {
                livros = new ArrayList<Livro>();
                rs.first();
                do {
                    Livro l = new Livro();
                    l.recuperar(rs.getInt("id"), rs.getString("titulo"), 
                            rs.getString("autor"), rs.getString("baixa"), 
                            rs.getInt("disponivel"), quantidadeDeTitulos(rs.getInt("titulo_id")),
                            rs.getInt("EMPRESTIMO"), rs.getInt("buscas"));
                    livros.add(l);
                }while(rs.next());
            }
            rs.close();
            ps.close();
            Conexao.desconectar();
        } catch(SQLException se) {
            System.out.println("Erro: "+se.getMessage());
        }
        return livros;
    }
    
    /** 
     * Método responsável por veritifcar a quantidade de títulos de cada livro
     * @param id int - id do livro no banco de dados
     * @return quantidade 
     */
    public static int quantidadeDeTitulos(int id) {
        int quantidade = 0;
        try {
            String sql = "SELECT COUNT(titulo_id)as quantidade FROM livro where titulo_id = ?";
            PreparedStatement ps = Conexao.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.last();
            quantidade = rs.getInt("quantidade");
            
            rs.close();
            ps.close();
            Conexao.desconectar();
        }  catch(SQLException se) {
            System.out.println("Erro: "+se.getMessage());
        }        
        return quantidade;
    }
    
    /** 
     * Método responsável por encerrar a conexão local 
     * @exception SQLException
     */
    public static void desconectar() {
        try {
            conn.close();
        } catch(SQLException se) {
            System.out.println(se.getMessage());
        }               
    }
}
