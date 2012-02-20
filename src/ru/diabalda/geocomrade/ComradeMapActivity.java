package ru.diabalda.geocomrade;

import java.util.List;

import ru.diabalda.geocamrade.logic.NavManager;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ComradeMapActivity extends MapActivity {
	/** Called when the activity is first created. */
	private LocationManager locationManager;
	private Location currentLocation;
	private GeoPoint experimentalPointFormarker;
	private MapController mapController;

	// using fileds
	private GeoPoint lastTouchPoint;
    private GeoPoint lastKnownLocation;
	class MapOverlay extends com.google.android.maps.Overlay {
		@Override
		public boolean draw(Canvas canvas, MapView mapView, boolean shadow,
				long when) {
			super.draw(canvas, mapView, shadow);
            
			if (lastKnownLocation != null) {
				
				// ---translate the GeoPoint to screen pixels---
				Point screenPts = new Point();
				mapView.getProjection().toPixels(lastKnownLocation, screenPts);
				// ---add the marker---
				Bitmap bmp = BitmapFactory.decodeResource(getResources(),
						R.drawable.ic_compass);
				canvas.drawBitmap(bmp, screenPts.x, screenPts.y - 50, null);
			}
			

			
			if (lastTouchPoint != null) {
				
				// ---translate the GeoPoint to screen pixels---
				Point screenPts = new Point();
				mapView.getProjection().toPixels(lastTouchPoint, screenPts);
				// ---add the marker---
				Bitmap bmp = BitmapFactory.decodeResource(getResources(),
						R.drawable.ic_launcher);
				canvas.drawBitmap(bmp, screenPts.x, screenPts.y - 50, null);
			}
			
			
			return true;
		}

		@Override
		public boolean onTouchEvent(MotionEvent event, MapView mapView) {
			// ---when user lifts his finger---
			if (event.getAction() == 2) {
			 lastTouchPoint = mapView.getProjection().fromPixels(
						(int) event.getX(), (int) event.getY());
				
			    //Draw marker by user's touch
				mapView.invalidate();
				
				Toast.makeText(
						getBaseContext(),
						lastTouchPoint.getLatitudeE6() / 1E6 + ","
								+ lastTouchPoint.getLongitudeE6() / 1E6,
						Toast.LENGTH_SHORT).show();
				
				
				Intent i = new Intent(ComradeMapActivity.this, AddDesire.class);
				i.putExtra("lat", lastTouchPoint.getLatitudeE6());
				i.putExtra("lon", lastTouchPoint.getLongitudeE6());
				
				startActivity(i);
				
			}
			return false;
		}

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);

		// Interfaces

		final MapView mapView = (MapView) findViewById(R.id.mapView);
		final ToggleButton streetViewBtn = (ToggleButton) findViewById(R.id.StreetsOrSatellite);

		// Logic
		mapView.setBuiltInZoomControls(true);
	    mapController = mapView.getController();
		// mapController.setCenter(new GeoPoint(55, 37));		
//		String coordinates[] = { "1.352566007", "103.78921587" };
//		double lat = Double.parseDouble(coordinates[0]);
//		double lng = Double.parseDouble(coordinates[1]);
//
//		experimentalPointFormarker = new GeoPoint((int) (lat * 1E6), (int) (lng * 1E6));
//		mapController.animateTo(experimentalPointFormarker);
//		mapController.setZoom(17);

		// ---Add a location marker---
		MapOverlay mapOverlay = new MapOverlay();
		List<Overlay> listOfOverlays = mapView.getOverlays();
		listOfOverlays.clear();
		listOfOverlays.add(mapOverlay);

		mapView.invalidate();

		// Listeners
		streetViewBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				if (streetViewBtn.isChecked()) {

					// Criteria criteria = new Criteria();
					// criteria.setAccuracy(Criteria.ACCURACY_FINE);
					// criteria.setPowerRequirement(Criteria.NO_REQUIREMENT);
					//
					// LocationManager locationManager =
					// (LocationManager)getSystemService(Context.LOCATION_SERVICE);
					// String provider =
					// locationManager.getBestProvider(criteria, true);
					// Location location =
					// locationManager.getLastKnownLocation(provider);
					// String locInfo =
					// String.format("Initial loc = (%f, %f) @ (%f meters up)",
					// location.getLatitude(), location.getLongitude(),
					// location.getAltitude() );
					// Toast.makeText(GeoComradeActivity.this, locInfo,
					// Toast.LENGTH_SHORT).show();
					// Log.e("DEBUGINFO",locInfo);

					mapView.setSatellite(false);
					mapView.setStreetView(true);
				} else {   
					mapView.setSatellite(true);
					mapView.setStreetView(false);
				}
			}
		});
		
		GeoComradeApp app = (GeoComradeApp)getApplicationContext();
		app.navManager.addOuterListener(new NavManager.SimpleLocationListener() {
			public void onLocationChanged(GeoPoint point){
				lastKnownLocation = point;
				ComradeMapActivity.this.mapController.animateTo(point);
				
			}
		});
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	// public void getLastLocation(){
	// String provider = getBestProvider();
	// currentLocation = locationManager.getLastKnownLocation(provider);
	// if(currentLocation != null){
	// setCurrentLocation(currentLocation);
	// }
	// else
	// {
	// Toast.makeText(this, "Location not yet acquired",
	// Toast.LENGTH_LONG).show();
	// }
	// }
	//
	// public void animateToCurrentLocation(){
	// if(currentPoint!=null){
	// mapController.animateTo(currentPoint);
	// }
	// }
	//
	// public String getBestProvider(){
	// locationManager = (LocationManager)
	// getSystemService(Context.LOCATION_SERVICE);
	// Criteria criteria = new Criteria();
	// criteria.setPowerRequirement(Criteria.NO_REQUIREMENT);
	// criteria.setAccuracy(Criteria.NO_REQUIREMENT);
	// String bestProvider = locationManager.getBestProvider(criteria, true);
	// return bestProvider;
	// }
	//
	// public void setCurrentLocation(Location location){
	// int currLatitude = (int) (location.getLatitude()*1E6);
	// int currLongitude = (int) (location.getLongitude()*1E6);
	// currentPoint = new GeoPoint(currLatitude,currLongitude);
	//
	// currentLocation = new Location("");
	// currentLocation.setLatitude(currentPoint.getLatitudeE6() / 1e6);
	// currentLocation.setLongitude(currentPoint.getLongitudeE6() / 1e6);
	// }
	//

}