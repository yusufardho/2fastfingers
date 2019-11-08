package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.tools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

public class CallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                context.sendBroadcast(new Intent("INCOMING_CALL_DETECTED"));
            }
        } catch (Exception ignored) {}
    }
}