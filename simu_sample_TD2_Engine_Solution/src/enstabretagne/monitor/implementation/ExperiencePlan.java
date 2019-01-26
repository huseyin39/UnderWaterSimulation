/*
 * 
 */
package enstabretagne.monitor.implementation;

import java.util.Arrays;
import java.util.List;

import enstabretagne.monitor.IExperiencePlan;
import enstabretagne.simulation.core.IScenario;

// TODO: Auto-generated Javadoc
/**
 * The Class ExperiencePlan.
 */
public class ExperiencePlan implements IExperiencePlan {

	/** The nombre repliques. */
	private long nombreRepliques;
	
	/** The liste scenarios. */
	private List<IScenario> listeScenarios;
	
	/** The initial seed. */
	private long initialSeed;
	
	/** The number of drones**/
	private int nombreDrones;
	
	/**
	 * Instantiates a new experience plan.
	 *
	 * @param nombreRepliques the nombre repliques
	 * @param initialSeed the initial seed
	 * @param listeScenarios the liste scenarios
	 */
	public ExperiencePlan(long nombreRepliques, long initialSeed, int nombreDrones ,IScenario... listeScenarios) {
		super();
		this.nombreRepliques = nombreRepliques;
		this.nombreDrones = nombreDrones;
		this.listeScenarios = Arrays.asList(listeScenarios);
		this.initialSeed = initialSeed;
	}

	/**
	 * Instantiates a new experience plan.
	 *
	 * @param nombreRepliques the nombre repliques
	 * @param initialSeed the initial seed
	 * @param listeScenarios the liste scenarios
	 */
	public ExperiencePlan(long nombreRepliques,long initialSeed, int nombreDrones, List<IScenario> listeScenarios) {
		super();
		this.nombreRepliques = nombreRepliques;
		this.listeScenarios = listeScenarios;
		this.initialSeed = initialSeed;
		this.nombreDrones = nombreDrones;
	}

	/* (non-Javadoc)
	 * @see enstabretagne.monitor.IExperiencePlan#getNombreRepliques()
	 */
	@Override
	public long getNombreRepliques() {
		return nombreRepliques;
	}

	/* (non-Javadoc)
	 * @see enstabretagne.monitor.IExperiencePlan#getListeScenarios()
	 */
	@Override
	public List<IScenario> getListeScenarios() {
		return listeScenarios;
	}

	/* (non-Javadoc)
	 * @see enstabretagne.monitor.IExperiencePlan#getInitialSeed()
	 */
	@Override
	public long getInitialSeed() {
		return initialSeed;
	}
	
	@Override
	public int getNombreDrones() {
		return nombreDrones;
	}
	
	

}
