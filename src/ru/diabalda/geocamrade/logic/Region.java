package ru.diabalda.geocamrade.logic;

import com.google.android.maps.GeoPoint;

public class Region {
	private GeoPoint geoPoint;
	private int Radius;
	
    
	public Region(GeoPoint geoPoint, int radius) {
		this.geoPoint = geoPoint;
		Radius = radius;
	}
	/**
	 * @return the geoPoint
	 */
	public GeoPoint getGeoPoint() {
		return geoPoint;
	}
	/**
	 * @param geoPoint the geoPoint to set
	 */
	public void setGeoPoint(GeoPoint geoPoint) {
		this.geoPoint = geoPoint;
	}
	/**
	 * @return the radius
	 */
	public int getRadius() {
		return Radius;
	}
	/**
	 * @param radius the radius to set
	 */
	public void setRadius(int radius) {
		Radius = radius;
	}
	
	
}
