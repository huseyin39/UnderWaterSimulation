package kizil_berkouk.BE.SimEntity.Drone;


import enstabretagne.simulation.components.data.SimFeatures;
import javafx.scene.paint.Color;
import kizil_berkouk.BE.Scenarios.ScenarioFeatures;
import kizil_berkouk.BE.SimEntity.MouvementSequenceur.EntityMouvementSequenceurFeature;

public class EntityDroneFeature extends SimFeatures {

	private double taille;
	private double rayon;
	private Color couleur;
	private ScenarioFeatures scenarioFeatures;
	private EntityMouvementSequenceurFeature seq;
	
	public EntityDroneFeature(String id,double taille,double rayon,Color couleur,EntityMouvementSequenceurFeature seq, ScenarioFeatures scenarioFeatures) {
		super(id);
		this.taille = taille;
		this.couleur = couleur;
		this.rayon=rayon;
		this.seq=seq;
		this.scenarioFeatures = scenarioFeatures;

	}

	public Color getCouleur() {
		return couleur;
	}
	
	//pour interroger l'environnement sur la possibilité de scanner les artefacts
	public ScenarioFeatures getScenarioFeatures() {
		return scenarioFeatures;
	}
	
	public double getRayon() {
		return rayon;
	}
	public double getTaille() {
		return taille;
	}

	public EntityMouvementSequenceurFeature getSeqFeature() {
		return seq;
	}

}
