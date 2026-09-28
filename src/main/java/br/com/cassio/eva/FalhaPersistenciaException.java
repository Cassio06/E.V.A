package br.com.cassio.eva;

public class FalhaPersistenciaException extends RuntimeException {
    public FalhaPersistenciaException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }
}
