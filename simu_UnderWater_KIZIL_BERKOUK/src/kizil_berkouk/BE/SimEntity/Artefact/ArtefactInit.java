package kizil_berkouk.BE.SimEntity.Artefact;

import enstabretagne.simulation.components.data.SimInitParameters;
import javafx.scene.paint.Color;
import kizil_berkouk.BE.SimEntity.MouvementSequenceur.EntityMouvementSequenceurInit;

public class ArtefactInit extends SimInitParameters {
	private String name;
	private Color couleur;
	private int type;
	
	private EntityMouvementSequenceurInit mvtSeqInit;
	
	
	public ArtefactInit(String nom,EntityMouvementSequenceurInit mvtSeqInit, int type)
	{
		this.name = nom;
		this.mvtSeqInit = mvtSeqInit;
		this.type = type;
		switch (type) {
		case 0:
			this.couleur = Color.BLACK;
			break;
		case 1:
			this.couleur = Color.RED;
			break;
		case 2:
			this.couleur = Color.YELLOW;
			break;
		case 3:
			this.couleur = Color.GREEN;
			break;
		default:
			throw new java.lang.RuntimeException("The type of artefact is incorrect; it must be 0,1,2 or 3");
		}
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
