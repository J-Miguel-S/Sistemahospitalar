package br.com.sistemahospitalar;

public class CargaHorariaInvalidaException extends Exception {
    //Construtor que recebe a mensagem de erro e repassa para a superclasse
    public CargaHorariaInvalidaException(String mensagem) {
        super(mensagem);
    }
}
