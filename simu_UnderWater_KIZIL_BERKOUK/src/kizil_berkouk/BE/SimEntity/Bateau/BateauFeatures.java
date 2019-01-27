package kizil_berkouk.BE.SimEntity.Bateau;

import java.util.List;

import enstabretagne.simulation.components.data.SimFeatures;
import kizil_berkouk.BE.SimEntity.Drone.EntityDrone;
import kizil_berkouk.BE.SimEntity.MouvementSequenceur.EntityMouvementSequenceurFeature;

public class BateauFeatures extends SimFeatures {

	private double vitesseMax;
	private double accelerationMax;
	private double longueur;
	private double largeur;
	private double hauteur;
	
	public double getVitesseMax() {
		return vitesseMax;
	}
	
	public double getAccelerationMax() {
		return accelerationMax;
	}
	
	public BateauFeatures(String id,double vitesseMax,double accelerationMax,double longueur,double largeur,double hauteur) {
		super(id);
		this.vitesseMax = vitesseMax;
		this.accelerationMax = accelerationMax;
		this.longueur = longueur;
		this.largeur = largeur;
		this.hauteur = hauteur;
	}
	
	public double getHauteur() {
		return hauteur;
	}
	
	public double getLongueur() {
		return longueur;
	}

	public double getLargeur() {
		return largeur;
	}
	
	public EntityMouvementSequenceurFeature getSeqFeature() {
		return null;
	}

}
