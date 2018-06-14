package comm.remotetemp;

/**
 * Created by Alexander on 11/06/2018.
 */

public interface OnReceiveUpdates {

    public void onConnection();
    public void updateReceived(double temperature);
}
