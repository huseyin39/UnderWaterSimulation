package kizil_berkouk.BE.SimEntity.Artefact;

import enstabretagne.simulation.components.data.SimFeatures;
import kizil_berkouk.BE.SimEntity.MouvementSequenceur.EntityMouvementSequenceurFeature;

public class ArtefactFeatures extends SimFeatures {

	private double taille1;
	private double taille2 = 0;
	private double taille3 = 0;
	
	
	public ArtefactFeatures(String id, double taille1) {
		super(id);
		this.taille1 = taille1;
	}
	
	public ArtefactFeatures(String id, double taille1, double taille2) {
		super(id);
		this.taille1 = taille1;
		this.taille2 = taille2;
	}
	
	public ArtefactFeatures(String id, double taille1, double taille2, double taille3) {
		super(id);
		this.taille1 = taille1;
		this.taille2 = taille2;
		this.taille3 = taille3;
	}


	public double getTaille1() {
		return taille1;
	}
	
	public double getTaille2() {
		return taille2;
	}
	
	public double getTaille3() {
		return taille3;
	}

	public EntityMouvementSequenceurFeature getSeqFeature() {
		return null;
	}

}
