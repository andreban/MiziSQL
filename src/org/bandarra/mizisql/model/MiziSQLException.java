/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bandarra.mizisql.model;

/**
 *
 * @author andreban
 */
public class MiziSQLException extends Exception {

    /**
     * Creates a new instance of <code>MiziSQLException</code> without detail message.
     */
    public MiziSQLException() {
    }


    /**
     * Constructs an instance of <code>MiziSQLException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public MiziSQLException(String msg) {
        super(msg);
    }
    
    public MiziSQLException(Exception cause){
        super(cause);
    }
}
