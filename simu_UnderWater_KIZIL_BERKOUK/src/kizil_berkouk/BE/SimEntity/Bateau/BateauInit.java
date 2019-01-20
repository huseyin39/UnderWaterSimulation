package kizil_berkouk.BE.SimEntity.Bateau;

import enstabretagne.simulation.components.data.SimInitParameters;
import javafx.scene.paint.Color;
import kizil_berkouk.BE.SimEntity.MouvementSequenceur.EntityMouvementSequenceurInit;

public class BateauInit extends SimInitParameters {
	private String name;
	private Color couleur;
	
	private EntityMouvementSequenceurInit mvtSeqInit;
	
	
	public BateauInit(String nom,EntityMouvementSequenceurInit mvtSeqInit,Color c)
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
