package ru.diabalda.geocamrade.logic;

import java.util.ArrayList;

import android.content.Context;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;

public class DesireManager {
 

	private ArrayList<Desire> desireList;
	private Context ctx;   
	private double minDistance;
	
	public DesireManager(ArrayList<Desire> desireList, Context ctx) {
		super();
		this.desireList = desireList;
		this.ctx = ctx;
		
	}

	/**
	 * @return the desireList
	 */
	public ArrayList<Desire> getDesireList() {
		return desireList;
	}

	/**
	 * @param desireList the desireList to set
	 */
	public void setDesireList(ArrayList<Desire> desireList) {
		this.desireList = desireList;
	}

	public double getMinDistance() {
		return minDistance;
	}

	public void setMinDistance(double minDistance) {
		this.minDistance = minDistance;
	}

	public void addDesire(Desire desire) {
		desireList.add(desire);
	}

	public void deleteDesire(Desire desire) {
		desireList.remove(desire);
	}
    
	public double minDistanceFromNearestDesireBorder(GeoPoint currentPoint){
		double minDistance = 1000000; //�������� �� ����� ������� ������ �������� ����� ���� double
		for (Desire desire : desireList) {
			double newMinDistance = desire.distanceFromCurrentPointToDesireBorder(currentPoint);
			if (minDistance > newMinDistance){
				minDistance = newMinDistance;
			}
		}
			
		return minDistance;
	}
	
	
	public void save(String filname) {

	}

	public void load(String filename) {

	}

	public void exciteReactions(GeoPoint currentPoint) {
		
		minDistance = minDistanceFromNearestDesireBorder(currentPoint); 
		
		ArrayList<Desire> activeDesires = new ArrayList<Desire>();
        
		
		// ���� �� ������ Desires � �� �� ���, ��� ������ ���� ������������ �������� � ����� ������
		for (Desire oldDesire : desireList) {
			if (oldDesire.isActual(currentPoint)) {
				
				
				activeDesires.add(oldDesire);
			}
           
		}

		// ��������� �������������� Desires

		for (Desire newDesire : activeDesires) {
			exciteOneReaction(newDesire);
		}

	}

	private void exciteOneReaction(Desire desire) {

		if ((desire.getReaction().getListReactionTypes() != null) && desire.getNumberOfCalls() < 2 )  {

			ArrayList<ReactionType> listReactionTypes = desire.getReaction()
					.getListReactionTypes();
			for (ReactionType type : listReactionTypes) {
                switch (type.ordinal()){
               //0 - SMS,1 - EMAIL,2 - NOTIFICATION, 3 - ALARM, 4 - TWITTER, 5 - ALERT, 6 - TOAST,7 - AUDIOTRACK
                	case 0:
                		sendSMS();
                		break;
                	case 1:
                		sendEMAIL();
                		break;
                	case 2:
                		createNotification();
                		break;
                	case 3:
                		callAlarm();
                		break;
                	case 6:
                		createToast();
                		break;
                }	
			}

		}

	}

	private void createToast() {
		Toast.makeText(
				ctx,
				"���� ��������!!! ���� ���������",
				Toast.LENGTH_SHORT).show();
		
	}

	private void callAlarm() {
		//����� ����������� ���� � ������� ����������, � �������� ����� �������� � ���� ����� �������
		
	}

	private void createNotification() {
		// TODO Auto-generated method stub
		
	}

	private void sendEMAIL() {
		// TODO Auto-generated method stub
		
	}

	private void sendSMS() {
		// TODO Auto-generated method stub
		
	}
}
