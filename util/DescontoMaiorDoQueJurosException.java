package util;

class DescontoMaiorDoQueJurosException extends Exception{
    public DescontoMaiorDoQueJurosException(String errorMessage){
        super(errorMessage);
    }
}