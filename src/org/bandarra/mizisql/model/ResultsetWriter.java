/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bandarra.mizisql.model;

import java.sql.ResultSet;
import java.util.Observable;
import java.util.logging.Logger;

/**
 *
 * @author andreban
 */
public abstract class ResultsetWriter extends Observable{
    protected Logger log = Logger.getLogger("org.bandarra.mizisql.model");    
    public abstract void writeResultSet(String file, ResultSet resultSet)
            throws MiziSQLException;

}
