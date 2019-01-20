package kizil_berkouk.BE.SimEntity.Artefact;

import enstabretagne.simulation.components.data.SimInitParameters;
import javafx.scene.paint.Color;
import kizil_berkouk.BE.SimEntity.MouvementSequenceur.EntityMouvementSequenceurInit;

public class ArtefactInit extends SimInitParameters {
	private String name;
	private Color couleur;
	private int type;
	
	private EntityMouvementSequenceurInit mvtSeqInit;
	
	
	public ArtefactInit(String nom,EntityMouvementSequenceurInit mvtSeqInit,Color c, int type)
	{
		this.name = nom;
		this.couleur=c;
		this.mvtSeqInit = mvtSeqInit;
		this.type = type;
	}
	
	
	public int getType() {
		return type;
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
