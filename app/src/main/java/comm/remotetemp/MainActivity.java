package comm.remotetemp;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import io.socket.client.Socket;

public class MainActivity extends AppCompatActivity implements OnReceiveUpdates {

    private TextView stateConnection;
    private TextView temperature;
    private Button connectDisconnect;

    private Handler handler;
    private ControlUpdates control;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();

        control = new ControlUpdates(this);


        stateConnection = (TextView) findViewById(R.id.state_connection);
        temperature = (TextView) findViewById(R.id.current_temperature);
        connectDisconnect = (Button) findViewById(R.id.btn_connect);

        connectDisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(control.isConnected()) {
                    connectDisconnect.setText("Conectar");
                    control.disconnect();

                    stateConnection.setText("Estado: Desconectado");
                    temperature.setText("00.0 °C");

                    Toast.makeText(getApplicationContext(), "Desconectando", Toast.LENGTH_SHORT).show();
                } else {
                    stateConnection.setText("Estado: Conectando...");
                    connectDisconnect.setText("Desconectar");

                    control.connect();
                    Toast.makeText(getApplicationContext(), "Conectando", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onConnection() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                stateConnection.setText("Estado: Conectado");
                Toast.makeText(getApplicationContext(), "Se ha conectado", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void updateReceived(final double temperature) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                String temp = String.valueOf(temperature) + " °C";
                stateConnection.setText(temp);
                Toast.makeText(getApplicationContext(), "temp", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
