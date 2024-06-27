package com.web.atvfirst.model.JDBC;

import java.sql.Connection;

public class MinhaConexao {
    public static Connection conexao(){
        return new ConexaoHBD().criarConexao();
    }
}
