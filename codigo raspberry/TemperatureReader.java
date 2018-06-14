/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remotetemperature;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexander
 */
public class TemperatureReader {

    private Thread thread;

    private Reader reader;

    public TemperatureReader(Reader r) {
        this.reader = r;

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Random random = new Random();
                    double dec = random.nextDouble() * 10;
                    double res = random.nextInt(40);

                    dec = Math.floor(dec);
                    res = res + dec / 10;

                    reader.OnReadTemperature(res);

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(TemperatureReader.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        });
        thread.start();
    }
}
