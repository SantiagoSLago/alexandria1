
package com.lumbersoft.alexandria.excepciones;


public class AlfaException extends Exception{

    //Usamos esta clase para diferenciar errores en la logica del negocio, de los bugs y errores propios de sistema
    public AlfaException(String msg) {
        super(msg);
    }

    public AlfaException() {
    }

   
    
    
    
}
