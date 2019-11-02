package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.tools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

public class IncomingBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("IncomingBroadcastReceiver: onReceive: ","lol");

        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        Log.d("IncomingBroadcastReceiver: onReceive: ", state);
        if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
//            Intent i = new Intent(context, IncomingCallActivity.class);
//            i.putExtras(intent);
//            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//            context.startActivity(i);
            Log.d("LOL","DAPET");
        }

    }

}
