/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remotetemperature;

/**
 *
 * @author Alexander
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RemoteConnection connection = new RemoteConnection();
        TemperatureReader temperatureReader = 
                new TemperatureReader(connection.getReader());
        
    }
    
}
