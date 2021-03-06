package kizil_berkouk.BE.SimEntity.Ocean;

import enstabretagne.simulation.components.data.SimInitParameters;
import kizil_berkouk.BE.SimEntity.MouvementSequenceur.EntityMouvementSequenceurInit;

public class EntityOceanInit extends SimInitParameters {
	private EntityMouvementSequenceurInit mvtSeqIni;
	private String name;
	
	public EntityOceanInit(String nom, EntityMouvementSequenceurInit mvtSeqIni)
	{
		this.mvtSeqIni = mvtSeqIni;
		this.name = nom;
	}
	
	public String getName() {
		return name;
	}
	
	public EntityMouvementSequenceurInit getMvtSeqInitial() {
		return  mvtSeqIni;
	}

}
