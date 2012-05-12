/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bandarra.mizisql.model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;

/**
 *
 * @author andreban
 */
public class CsvWriter extends ResultsetWriter {

    @Override
    public void writeResultSet(String file, ResultSet resultSet) throws MiziSQLException {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(file);
            ResultSetMetaData metadata = resultSet.getMetaData();
            int columnCount = metadata.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                pw.print("\"" + resultSet.getMetaData().getColumnLabel(i + 1) + "\"");
                if (i + 1 < columnCount) {
                    pw.print(",");
                }
            }
            pw.println();
            
            long count = 0;
            while (resultSet.next()) {
                count++;
                notifyObservers(new Long(count));
                setChanged();
                for (int i = 0; i < columnCount; i++) {
                    resultSet.getObject(i + 1);
                    if (!resultSet.wasNull()) {
                        switch (metadata.getColumnType(i + 1)) {
                            case Types.VARCHAR:
                            case Types.CHAR:
                                pw.print("\"" + resultSet.getString(i + 1) + "\"");
                                break;
                            default:
                                pw.print(resultSet.getObject(i + 1));
                        }
                    }

                    if (i + 1 < columnCount) {
                        pw.print(",");
                    }
                }
                pw.println();
            }
        } catch (FileNotFoundException ex) {
            log.log(Level.SEVERE, "Error creating output file", ex);
            throw new MiziSQLException(ex);
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "SQL Error", ex);
            throw new MiziSQLException(ex);            
        } finally {
            if (pw != null){
                pw.close();
            }
        }
    }

}
