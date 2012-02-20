package ru.diabalda.geocomrade;

import com.google.android.maps.GeoPoint;

import ru.diabalda.geocamrade.logic.Desire;
import ru.diabalda.geocamrade.logic.Region;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class AddDesire extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.create_desire);
		
		lat = getIntent().getIntExtra("lat", 0);
		lon = getIntent().getIntExtra("lon", 0);
		radius = 100;
		
		
		initViews();
	}
	
	private void initViews(){
		final EditText nameEditText = (EditText) findViewById(R.id.create_desire_name_edittext);
		final CheckBox activeCheckBox = (CheckBox) findViewById(R.id.create_desire_active_checkbox);
		
		Button mAddButton = (Button) findViewById(R.id.create_desire_add_button);
		
		mAddButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				GeoComradeApp app = (GeoComradeApp)getApplicationContext();
				app.desireManager.addDesire(new Desire (nameEditText.getText().toString(), 
						new Region(new GeoPoint(lat,lon), radius), activeCheckBox.isChecked()));
				
				Toast.makeText(
						AddDesire.this, "" + app.desireManager.getDesireList().get(1).getRegion().getGeoPoint().getLatitudeE6(), 
						
						Toast.LENGTH_SHORT).show();
				
				finish();
			}
		});
	}
	
	private int lat;
	private int lon;
    private int radius;
}
