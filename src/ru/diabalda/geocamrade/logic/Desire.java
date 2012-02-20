package ru.diabalda.geocamrade.logic;

import java.util.ArrayList;

import com.google.android.maps.GeoPoint;

public class Desire {
	
	private int idDesire;
	private String name;
	private Region region;
	private String info;
	private DesireType desireType;
	private Reaction reaction;
	private DesireGroup desireGroup;
	private boolean isActive;
	
	public Desire(int idDesire, Region region, String info,
			DesireType desireType, Reaction reaction, DesireGroup desireGroup) {
		super();
		this.idDesire = idDesire;
		this.region = region;
		this.info = info;
		this.desireType = desireType;
		this.reaction = reaction;
		this.desireGroup = desireGroup;
	}

    public Desire(String name, boolean isActive) {
		this.name = name;
		this.isActive = isActive;
	}
    
    public Desire(String name, Region region,boolean isActive) {
		this.name = name;
		this.isActive = isActive;
		this.region = region;
		
		ArrayList<ReactionType> listReactions = new ArrayList<ReactionType>();
		listReactions.add(ReactionType.EMAIL);
		listReactions.add(ReactionType.ALERT);
		listReactions.add(ReactionType.TOAST);
		listReactions.add(ReactionType.SMS);
		listReactions.add(ReactionType.AUDIOTRACK);
		listReactions.add(ReactionType.TWITTER);
		listReactions.add(ReactionType.ALARM);
		
		this.reaction = new Reaction("TestReaction", listReactions);
	}

	public boolean isActual(GeoPoint currentPoint){
    	boolean isActual = false;
    	
    	if (!isActive){
    		
    		isActual = checkActuality(currentPoint);
    	}	
    	return isActual; 
    	
    }
    
    
    // Здесь сложная геометрическая проверка
	private boolean checkActuality(GeoPoint currentPoint) {
	
	return true;	
		
	}

	/**
	 * @return the idDesire
	 */
	public int getIdDesire() {
		return idDesire;
	}


	/**
	 * @param idDesire the idDesire to set
	 */
	public void setIdDesire(int idDesire) {
		this.idDesire = idDesire;
	}


	/**
	 * @return the region
	 */
	public Region getRegion() {
		return region;
	}


	/**
	 * @param region the region to set
	 */
	public void setRegion(Region region) {
		this.region = region;
	}


	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}


	/**
	 * @param info the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
	}


	/**
	 * @return the desireType
	 */
	public DesireType getDesireType() {
		return desireType;
	}


	/**
	 * @param desireType the desireType to set
	 */
	public void setDesireType(DesireType desireType) {
		this.desireType = desireType;
	}


	/**
	 * @return the reaction
	 */
	public Reaction getReaction() {
		return reaction;
	}


	/**
	 * @param reaction the reaction to set
	 */
	public void setReaction(Reaction reaction) {
		this.reaction = reaction;
	}


	/**
	 * @return the desireGroup
	 */
	public DesireGroup getDesireGroup() {
		return desireGroup;
	}


	/**
	 * @param desireGroup the desireGroup to set
	 */
	public void setDesireGroup(DesireGroup desireGroup) {
		this.desireGroup = desireGroup;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	

	
	

}
