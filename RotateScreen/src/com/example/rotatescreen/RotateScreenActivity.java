package com.example.rotatescreen;

import com.example.utils.RotateManager;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class RotateScreenActivity extends Activity {

	private static final String TAG = RotateScreenActivity.class.getSimpleName();
	private RotateManager mRotateMgr;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rotate_screen);
		init();
	}

    private TextView mTvInfo;
    
    
    private void init() {
    	mTvInfo = (TextView) findViewById(R.id.tv_info);
    	findViewById(R.id.btn_rotate).setOnClickListener(mBtnClickListener);
    	
    	mRotateMgr = new RotateManager(this);
    }

//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//    	super.onConfigurationChanged(newConfig);
//    }
    
    private OnClickListener mBtnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			int orientation = getRequestedOrientation();
			if (orientation != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE 
			 &&	orientation != ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE 
			 && orientation != ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE) {
				mRotateMgr.landscape();
			} else {
				mRotateMgr.portrait();
			}
		}
    };
    
    @Override
    protected void onDestroy() {
    	mRotateMgr.destroy();
    	mRotateMgr = null;
    	super.onDestroy();
    };
}
