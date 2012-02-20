package ru.diabalda.geocamrade.logic;

import java.util.ArrayList;

public class Reaction {
	
	private int idReaction;
	private String name;
	private ArrayList<ReactionType> listReactionTypes;
	
	
	
	
	public Reaction(String name, ArrayList<ReactionType> listReactionTypes) {
		super();
		this.name = name;
		this.listReactionTypes = listReactionTypes;
	}
	public ArrayList<ReactionType> getListReactionTypes() {
		return listReactionTypes;
	}
	public void setListReactionTypes(ArrayList<ReactionType> listReactionTypes) {
		this.listReactionTypes = listReactionTypes;
	}
	public int getIdReaction() {
		return idReaction;
	}
	public void setIdReaction(int idReaction) {
		this.idReaction = idReaction;
	}
	
	
	
}
