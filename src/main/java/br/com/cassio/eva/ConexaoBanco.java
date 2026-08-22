package br.com.cassio.eva;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexaoBanco {
    private static final String URL = "jdbc:postgresql://localhost:5432/eva";
    private static final String USUARIO = "eva_app";
    public static Connection conectar() throws SQLException{
        String SENHA = System.getenv("EVA_DB_PASSWORD");

        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
