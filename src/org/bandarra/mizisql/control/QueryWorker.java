/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bandarra.mizisql.control;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.SwingWorker;
import org.bandarra.mizisql.model.SQLProcessor;
import org.bandarra.mizisql.view.WorkerListener;

/**
 *
 * @author andreban
 */
public class QueryWorker extends SwingWorker<Object, Long> implements Observer {
    private String driver;
    private String url;
    private String user;
    private String pass;
    private String sql;
    private String file;
    private WorkerListener listener;
    
    public QueryWorker(String driver, String url, String user, String pass, 
            String sql, String file, WorkerListener listener){
        this.driver = driver;
        this.url = url;
        this.user = user.equals("")?null:user;
        this.pass = pass.equals("")?null:pass;
        this.sql = sql;
        this.file = file;        
        this.listener = listener;
    }
    
    @Override
    protected Object doInBackground() throws Exception {
        SQLProcessor processor = new SQLProcessor();
        processor.addObserver(this);
        processor.processSQL(driver, url, user, pass, sql, file);
        return null;
    }

    public void update(Observable o, Object arg) {        
        if (arg instanceof Long){
            publish((Long)arg);
        }
    }

    @Override
    protected void process(List<Long> chunks) {
        super.process(chunks);
        if (listener != null){
            listener.progress(chunks.get(chunks.size()-1));
        }
    }

    @Override
    protected void done() {
        super.done();
        listener.done();
    }
    
    
}
