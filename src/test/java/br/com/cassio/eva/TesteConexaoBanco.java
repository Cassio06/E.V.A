package br.com.cassio.eva;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLOutput;

public class TesteConexaoBanco {

    public static void main(String[] args){
        try(Connection conexao = ConexaoBanco.conectar()) {
            System.out.println("Conexão realizada com sucesso.");

        }catch (SQLException e){
            System.out.println("Erro ao conectar o banco.");
            e.printStackTrace();
        }
    }

}
