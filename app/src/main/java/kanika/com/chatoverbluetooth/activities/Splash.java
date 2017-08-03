package kanika.com.chatoverbluetooth.activities;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import kanika.com.chatoverbluetooth.R;

public class Splash extends Activity {
    private Handler mHandler;
    private Runnable mRunnable;
    private static final long SPLASH_DURATION = 2000L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarTransparent();
        setContentView(R.layout.activity_splash);
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        adapter.enable();
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash.this, HomeActivity.class));
                finish();
            }
        }, SPLASH_DURATION);
//        mRunnable = new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(Splash.this, HomeActivity.class));
//                finish();
//            }
//        };
    }

    private void setStatusBarTransparent() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(Splash.this, HomeActivity.class));
//                finish();
//            }
//        }, SPLASH_DURATION);

        mHandler.postDelayed(mRunnable, SPLASH_DURATION);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mRunnable);
    }

}
