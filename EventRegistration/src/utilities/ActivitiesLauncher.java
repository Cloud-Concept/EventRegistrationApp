package utilities;

import android.content.Context;
import android.content.Intent;

import activity.EventDetailsActivity;
import activity.HomeActivity;
import com.google.gson.Gson;

import model.Event;

/**
 * Created by Bibo on 12/27/15.
 */
public class ActivitiesLauncher {

    private static Intent intent;

    public static void openHomeActivity(Context applicationContext) {

        intent = new Intent(applicationContext, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        applicationContext.startActivity(intent);

    }

    public static void openEventDetailsActivity(Context applicationContext, Event event) {
        Gson gson = new Gson();
        String obj = gson.toJson(event);
        intent = new Intent(applicationContext, EventDetailsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("obj",obj);
        applicationContext.startActivity(intent);
    }
}
