package com.web.atvfirst.model.DAO;

import com.web.atvfirst.model.Entity.Produto;
import com.web.atvfirst.model.JDBC.MinhaConexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProdutoDAO {

    Connection con;

    public ProdutoDAO() {
        this.con = MinhaConexao.conexao();
    }

    public List<Produto> buscarProdutos() {
        try {
            String sql = "select * from produto";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Produto> produto = new ArrayList();
            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setDescricao(rs.getString("descricao"));
                p.setValor(rs.getDouble("valor"));
                produto.add(p);
            }
            return produto;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Produto buscarProduto(int id) {
        try {
            String sql = "select * from produto where id = "+id;
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Produto p = new Produto();
            if (rs.next()) {
                p.setId(id);
                p.setDescricao(rs.getString("descricao"));
                p.setValor(rs.getDouble("valor"));
            }
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean update(Produto p) {
        try {
            //comando sql
            String sql = "update produto set descricao = ?, valor = ? where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            //referênciar o parâmetro do método para a ?
            ps.setString(1, p.getDescricao());
            ps.setDouble(2, p.getValor());
            ps.setInt(3, p.getId());

            if (ps.executeUpdate()==1)
                return true;

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean remove(int id) {
        try {
            String sql = "delete from produto where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            if(ps.executeUpdate()==1)
                return true;

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean save(Produto produto) {
        try {
            String sql = "insert into produto (descricao, valor) values (?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, produto.getDescricao());
            ps.setDouble(2, produto.getValor());

            if(ps.executeUpdate()==1)
                return true;

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
