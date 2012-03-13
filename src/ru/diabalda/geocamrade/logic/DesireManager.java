package ru.diabalda.geocamrade.logic;

import java.util.ArrayList;

import android.content.Context;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;

public class DesireManager {
 

	private ArrayList<Desire> desireList;
	private Context ctx;   
	
	
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

	public void addDesire(Desire desire) {
		desireList.add(desire);
	}

	public void deleteDesire(Desire desire) {
		desireList.remove(desire);
	}

	public void save(String filname) {

	}

	public void load(String filename) {

	}

	public void exciteReactions(GeoPoint currentPoint) {
		ArrayList<Desire> activeDesires = new ArrayList<Desire>();
        
		
		// Идем по списку Desires и те из них, что должны быть активированы помещаем в новый списке
		for (Desire oldDesire : desireList) {
			if (oldDesire.isActual(currentPoint)) {
				activeDesires.add(oldDesire);
			}

		}

		// Обработка пересекающихся Desires

		for (Desire newDesire : activeDesires) {
			exciteOneReaction(newDesire);
		}

	}

	private void exciteOneReaction(Desire desire) {

		if (desire.getReaction().getListReactionTypes() != null) {

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
				"Пора вставать!!! Твоя остановка",
				Toast.LENGTH_SHORT).show();
		
	}

	private void callAlarm() {
		// TODO Auto-generated method stub
		
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
