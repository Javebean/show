package com.novahome.data.model;

import java.util.List;


import com.novahome.data.pojo.Construction;
import com.novahome.data.pojo.DisplayItem;
import com.novahome.data.pojo.Exhibitors;
import com.novahome.data.pojo.SceneServ;
import com.novahome.data.pojo.Transportation;
import com.novahome.data.pojo.Visitor;

public class TotalExhibitInfo {

	private Exhibitors exhibitors;
	
	private List<Construction>construction;
	
	private List<Transportation>transportation;
	
	private List<SceneServ>sceneServ;
	
	private List<DisplayItem>displayItem;
	
	private List<Visitor>visitor;

	public Exhibitors getExhibitors() {
		return exhibitors;
	}

	public void setExhibitors(Exhibitors exhibitors) {
		this.exhibitors = exhibitors;
	}

	public List<Construction> getConstruction() {
		return construction;
	}

	public void setConstruction(List<Construction> construction) {
		this.construction = construction;
	}

	public List<Transportation> getTransportation() {
		return transportation;
	}

	public void setTransportation(List<Transportation> transportation) {
		this.transportation = transportation;
	}

	public List<SceneServ> getSceneServ() {
		return sceneServ;
	}

	public void setSceneServ(List<SceneServ> sceneServ) {
		this.sceneServ = sceneServ;
	}

	public List<DisplayItem> getDisplayItem() {
		return displayItem;
	}

	public void setDisplayItem(List<DisplayItem> displayItem) {
		this.displayItem = displayItem;
	}

	public List<Visitor> getVisitor() {
		return visitor;
	}

	public void setVisitor(List<Visitor> visitor) {
		this.visitor = visitor;
	}
	
	
	
	
}
