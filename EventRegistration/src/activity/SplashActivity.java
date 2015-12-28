package activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.cloudconcept.R;

import utilities.ActivitiesLauncher;

/**
 * Created by Abanoub Wagdy on 12/15/2015.
 */
public class SplashActivity extends Activity {

    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ActivitiesLauncher.openHomeActivity(getApplicationContext());
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
