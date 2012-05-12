/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bandarra.mizisql.model;

import java.sql.*;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andreban
 */
public class SQLProcessor extends Observable implements Observer{
    
    private Logger log = Logger.getLogger("org.bandarra.mizisql.model");
    
    public void processSQL(String className, String url, String userName,
            String password, String sql, String fileName) throws MiziSQLException{
        try {
            Class.forName(className);
            Connection con = DriverManager.getConnection(url, userName, password);
            PreparedStatement ps = con.prepareStatement(sql); 
            ResultSet result = ps.executeQuery();
            ResultsetWriter writer = new CsvWriter();
            writer.addObserver(this);
            writer.writeResultSet(fileName, result);
        } catch (SQLException ex) {
            log.log(Level.SEVERE, ex.getMessage(), ex);
            throw new MiziSQLException(ex);            
        } catch (ClassNotFoundException ex) {
            log.log(Level.SEVERE, "Invalid Class Name", ex);
            throw new MiziSQLException(ex);
        }
        
    }

    public void update(Observable o, Object arg) {        
        notifyObservers(arg);
        setChanged();
    }

}
