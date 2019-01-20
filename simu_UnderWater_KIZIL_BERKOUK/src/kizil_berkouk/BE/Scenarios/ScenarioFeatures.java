package kizil_berkouk.BE.Scenarios;

import java.util.HashMap;

import enstabretagne.simulation.components.data.SimFeatures;
import kizil_berkouk.BE.SimEntity.Artefact.ArtefactFeatures;
import kizil_berkouk.BE.SimEntity.Artefact.ArtefactInit;
import kizil_berkouk.BE.SimEntity.Bateau.BateauFeatures;
import kizil_berkouk.BE.SimEntity.Bateau.BateauInit;
import kizil_berkouk.BE.SimEntity.Drone.EntityDroneFeature;
import kizil_berkouk.BE.SimEntity.Drone.EntityDroneInit;
import kizil_berkouk.BE.SimEntity.Ocean.EntityOceanFeature;
import kizil_berkouk.BE.SimEntity.Ocean.EntityOceanInit;

public class ScenarioFeatures extends SimFeatures {

	private HashMap<ArtefactFeatures, ArtefactInit> artefacts;
	private HashMap<BateauFeatures, BateauInit> bateau;
	private HashMap<EntityDroneFeature, EntityDroneInit> drones;
	private HashMap<EntityOceanFeature,EntityOceanInit> ocean;
	
	public HashMap<ArtefactFeatures, ArtefactInit> getArtefacts(){
		return artefacts;
	}
	
	public HashMap<EntityOceanFeature, EntityOceanInit> getOcean() {
		return ocean;
	}
	public HashMap<EntityDroneFeature, EntityDroneInit> getDrones() {
		return drones;
	}
	
	public HashMap<BateauFeatures, BateauInit> getBateau() {
		return bateau;
	}
	public ScenarioFeatures(String id) {
		super(id);
		artefacts = new HashMap<>();
		bateau = new HashMap<>();
		drones = new HashMap<>();
		ocean = new HashMap<>();
	}	

}
