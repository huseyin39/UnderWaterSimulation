package kizil_berkouk.BE.SimEntity.Artefact;

import enstabretagne.simulation.components.data.SimFeatures;
import kizil_berkouk.BE.SimEntity.MouvementSequenceur.EntityMouvementSequenceurFeature;

public class ArtefactFeatures extends SimFeatures {

	private double vitesseMax;
	private double accelerationMax;
	private double taille;
	
	public double getVitesseMax() {
		return vitesseMax;
	}
	
	public double getAccelerationMax() {
		return accelerationMax;
	}
	
	public ArtefactFeatures(String id,double vitesseMax,double accelerationMax,double taille) {
		super(id);
		this.vitesseMax = vitesseMax;
		this.accelerationMax = accelerationMax;
		this.taille = taille;
	}

	public double getTaille() {
		return taille;
	}

	public EntityMouvementSequenceurFeature getSeqFeature() {
		return null;
	}

}
