package com.intrications.systemuihelper.sample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.intrications.systemuihelper.SystemUiHelper;

public class MainActivity extends AppCompatActivity {

    private SystemUiHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_github) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://github.com/intrications/SystemUiHelper"));
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onLowProfile(View view) {
        hide(SystemUiHelper.LEVEL_LOW_PROFILE, 0);
    }

    public void onHideStatusBar(View view) {
        hide(SystemUiHelper.LEVEL_HIDE_STATUS_BAR, 0);
    }

    public void onLeanBack(View view) {
        hide(SystemUiHelper.LEVEL_LEAN_BACK, 0);
    }

    public void onImmersive(View view) {
        hide(SystemUiHelper.LEVEL_IMMERSIVE, 0);
    }

    public void onImmersiveSticky(View view) {
        hide(SystemUiHelper.LEVEL_IMMERSIVE, SystemUiHelper.FLAG_IMMERSIVE_STICKY);
    }

    public void onReset(View view) {
        if (helper != null) {
            helper.show();
        }
    }

    private void hide(int level, int flags) {
        if (helper != null) {
            helper.show();
            helper = null;
        }

        helper = new SystemUiHelper(this, level, flags,
                new SystemUiHelper.OnVisibilityChangeListener() {

                    @Override
                    public void onVisibilityChange(boolean visible) {
                        Toast.makeText(MainActivity.this,
                                "Visible: " + visible, Toast.LENGTH_SHORT).show();
                    }
                });
        helper.hide();
    }
}
