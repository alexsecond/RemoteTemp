/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remotetemperature;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexander
 */
public class RemoteConnection {
    private Socket socket;
    
    private Reader reader = new Reader() {
        @Override
        public void OnReadTemperature(double temperature) {
            socket.emit("temperature-value", temperature);
            System.out.println("Temperatura actual: " + temperature );
        }
    };
    
    
    public RemoteConnection() {
        
        try {
            
            socket = IO.socket("https://control-remote-temperature.herokuapp.com/");
            //socket = IO.socket("http://localhost:8080/");
            
        } catch (URISyntaxException ex) {
            Logger.getLogger(RemoteConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                
            }
        });
        socket.connect();
    }
    
    public Reader getReader() {
        return reader;
    }
}
