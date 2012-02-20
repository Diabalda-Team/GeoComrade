package ru.diabalda.geocomrade;

import java.util.ArrayList;

import ru.diabalda.geocamrade.logic.Desire;
import ru.diabalda.geocamrade.logic.DesireManager;
import ru.diabalda.geocamrade.logic.Region;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.widget.TabHost;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;

public class GeoComradeActivity extends TabActivity {
	@Override
	protected void onDestroy() {
		super.onStop();
		GeoComradeApp app = (GeoComradeApp)getApplicationContext();
		app.navManager.stopNavigation();
	}
	private DesireManager desireManager;

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().setFormat(PixelFormat.RGBX_8888);
		setContentView(R.layout.geo_comrade);

		Resources res = getResources();
		TabHost tabHost = getTabHost();
		TabHost.TabSpec spec;
		Intent intent;

		

		intent = new Intent().setClass(this, ComradeMapActivity.class);

		spec = tabHost.newTabSpec("map")
				.setIndicator("Map", res.getDrawable(R.drawable.ic_compass))
				.setContent(intent);
		tabHost.addTab(spec);

		// Do the same for the other tabs
		intent = new Intent().setClass(this, DesiresActivity.class);
		spec = tabHost
				.newTabSpec("desires")
				.setIndicator("Desires", res.getDrawable(R.drawable.ic_desires))
				.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, SettingsActivity.class);
		spec = tabHost
				.newTabSpec("settings")
				.setIndicator("Settings",
						res.getDrawable(R.drawable.ic_settings))
				.setContent(intent);
		tabHost.addTab(spec);

		tabHost.setCurrentTab(1);
		
		GeoComradeApp app = (GeoComradeApp)getApplicationContext();
		app.navManager.startNavigation(this);
		
	}
	
	
}