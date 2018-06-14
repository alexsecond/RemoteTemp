package comm.remotetemp;

import java.net.URISyntaxException;

import io.socket.client.Socket;
import io.socket.client.IO;
import io.socket.emitter.Emitter;

/**
 * Created by Alexander on 11/06/2018.
 */

public class ControlUpdates {
    private Socket socket;

    private OnReceiveUpdates updateListener;

    public ControlUpdates(OnReceiveUpdates updateListener) {
        this.updateListener = updateListener;
        initSocket();
    }

    private void initSocket() {
        try {
            IO.Options options = new IO.Options();
            options.secure = true;
            options.forceNew = true;

            socket = IO.socket("https://control-remote-temperature.herokuapp.com/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                updateListener.onConnection();
            }
        }).on("get-temperature", new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                double temperature = (Double) args[0];
                updateListener.updateReceived(temperature);
            }
        });
    }


    public boolean isConnected() {
        return socket.connected();
    }

    public void connect() {
        socket.connect();
    }

    public void disconnect() {
        socket.disconnect();
    }
}
