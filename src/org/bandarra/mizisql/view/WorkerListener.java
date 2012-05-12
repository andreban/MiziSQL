/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bandarra.mizisql.view;

/**
 *
 * @author andre.bandarra
 */
public interface WorkerListener {
    public void progress(Long l);
    public void done();

}
