package ru.diabalda.geocamrade.logic;

import java.util.ArrayList;

import com.google.android.maps.GeoPoint;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;



public class NavManager {
	private LocationManager mLocationManager;
	
	private Location mLastLocation ;
	
	private ArrayList<LocationListener> outerListeners = 
			new ArrayList<LocationListener>();

	private Context ctx;
	
	public NavManager(Context applicationContext) {
		ctx = applicationContext; 
	}

	public Location getLastLocation() {
		return mLastLocation;
	}
	
	public void addOuterListener(LocationListener listener){
		outerListeners.add(listener);
	}
	public void removeOuterListener(LocationListener listener){
		outerListeners.remove(listener);
	}

	
	public static String locationMessage(Location location){
		if (location==null){
			return "undefined";
		}
		return Location.convert(location.getLatitude(), Location.FORMAT_SECONDS) + 
				" " + Location.convert(location.getLongitude(), Location.FORMAT_SECONDS) + 
				" speed: " + location. getSpeed() +
				" got by " + location.getProvider();
	}
	
	private class NavManagerLocationListener implements LocationListener{
		public void onProviderDisabled(String provider) {
			for (LocationListener curListener: outerListeners)
			{
				curListener.onProviderDisabled(provider);
			}
		}

		public void onProviderEnabled(String provider) {
			for (LocationListener curListener: outerListeners)
			{
				curListener.onProviderEnabled(provider);
			}
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
			for (LocationListener curListener: outerListeners)
			{
				curListener.onStatusChanged(provider,status,extras);
			}
		}

		public void onLocationChanged(Location location) {
			mLastLocation = location;
			//Toast.makeText(	ctx, "ѕолучены координаты. ≈сть " + outerListeners.size() + "слушателей"	,	Toast.LENGTH_SHORT).show();
			/*тут можно вписать код дл€ того чтоб решить, нужна нам эта точка лил нет, и если не нужна -
			 *выйти из обработчика, не передава€ внешним слушател€м*/
			for (LocationListener curListener: outerListeners)
			{
				curListener.onLocationChanged(location); 
			}
	    }
	}
	    
	private NavManagerLocationListener locationListener = new NavManagerLocationListener();
	
	public void stopNavigation() {
		mLocationManager.removeUpdates(locationListener);
		//outerListeners.removeAll(outerListeners);
		Toast.makeText(	ctx, "Ќавигаци€ отключена"	,	Toast.LENGTH_SHORT).show();
	}

	public void startNavigation(Context ctxt) {
		if (mLocationManager == null) {
			mLocationManager  = (LocationManager)ctxt.getSystemService(Context.LOCATION_SERVICE);
			ctx = ctxt;
		}
		mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
		mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
		Toast.makeText(	ctxt, "Ќавигаци€ запушена"	,	Toast.LENGTH_SHORT).show();
		
	}
	
	public void startNavigation() {
		startNavigation(ctx);

	}
	
	public static abstract class SimpleLocationListener implements LocationListener {
		public void onLocationChanged(Location location) {
			GeoPoint point = new GeoPoint((int)(location.getLatitude()*1E6),(int)(location.getLongitude()*1E6));
			onLocationChanged(point);
		}
		public void onProviderDisabled(String provider) {
		}
		public void onProviderEnabled(String provider) {
		}
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
		public abstract void onLocationChanged(GeoPoint point);
	}

}
