package ru.diabalda.geocomrade;

import java.util.ArrayList;

import ru.diabalda.geocamrade.logic.Desire;
import ru.diabalda.geocamrade.logic.DesireManager;
import ru.diabalda.geocamrade.logic.NavManager;
import ru.diabalda.geocamrade.logic.Region;

import com.google.android.maps.GeoPoint;

import android.app.Application;
import android.widget.Toast;

public class GeoComradeApp extends Application{
    public static DesireManager desireManager;
    public static NavManager navManager;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		ArrayList<Desire> desires = new ArrayList<Desire>();
		desires.add(new Desire("Школа", new Region(new GeoPoint(55000000, 55000000), 10),
				true));
		desires.add(new Desire("Универ", new Region(new GeoPoint(55010000, 55010000), 10),
				true));
		desireManager = new DesireManager(desires, getApplicationContext());
		
		navManager = new NavManager(getApplicationContext());
		navManager.addOuterListener(new NavManager.SimpleLocationListener(){
			@Override
			public void onLocationChanged(GeoPoint point) {
				// обработка точки
				//Toast.makeText(	getApplicationContext(), "Твоя остановка!"	,	Toast.LENGTH_SHORT).show();
				desireManager.exciteReactions(point);						
			}
        });
		
	}
	

}
