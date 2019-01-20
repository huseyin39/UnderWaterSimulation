package kizil_berkouk.BE.SimEntity.Artefact;

import enstabretagne.simulation.components.data.SimInitParameters;
import javafx.scene.paint.Color;
import kizil_berkouk.BE.SimEntity.MouvementSequenceur.EntityMouvementSequenceurInit;

public class ArtefactInit extends SimInitParameters {
	private String name;
	private Color couleur;
	
	private EntityMouvementSequenceurInit mvtSeqInit;
	
	
	public ArtefactInit(String nom,EntityMouvementSequenceurInit mvtSeqInit,Color c)
	{
		this.name = nom;
		this.couleur=c;
		this.mvtSeqInit = mvtSeqInit;
	}
	
	public EntityMouvementSequenceurInit getMvtSeqInit() {
		return mvtSeqInit;
	}
	
	public String getName() {
		return name;
	}
	
	public Color getColor() {
		return couleur;
	}
	
	

}
