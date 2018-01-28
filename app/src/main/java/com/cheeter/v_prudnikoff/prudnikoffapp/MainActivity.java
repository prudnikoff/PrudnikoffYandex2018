package com.cheeter.v_prudnikoff.prudnikoffapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.UpdateManager;


public class MainActivity extends AppCompatActivity {

    private ImageView avatarImageView;
    private ImageHelper imageHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        imageHelper = new ImageHelper(this);
        avatarImageView = findViewById(R.id.avatarImageView);
        int size = imageHelper.getScreenWidth() / 2;
        avatarImageView.getLayoutParams().height = size;
        avatarImageView.getLayoutParams().width = size;
        avatarImageView.setImageBitmap(imageHelper.getRoundedCornerBitmap(imageHelper
                .getBitmapFromAssets(getString(R.string.avatar)), 600));
        checkForUpdates();
    }

    @Override
    public void onResume() {
        super.onResume();
        checkForCrashes();
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterManagers();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterManagers();
    }


    private void checkForCrashes() {
        CrashManager.register(this);
    }

    private void checkForUpdates() {
        // Remove this for store builds!
        UpdateManager.register(this);
    }

    private void unregisterManagers() {
        UpdateManager.unregister();
    }

}
