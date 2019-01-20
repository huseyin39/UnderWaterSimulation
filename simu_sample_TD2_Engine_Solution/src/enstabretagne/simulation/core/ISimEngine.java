/*
 * 
 */
package enstabretagne.simulation.core;

import enstabretagne.simulation.components.IScenarioIdProvider;
import enstabretagne.simulation.components.data.SimScenarioInit;

// TODO: Auto-generated Javadoc
/**
 * The Interface ISimEngine. Cette interface est plut�t pour utilisation du framework de simulation.
 * Elle �tend l'interface IEngine. 
 */
public interface ISimEngine extends IEngine,ISimulationDateProvider,IScenarioIdProvider{



	/**
	 * Cette m�thode permet simplement au moteur de supprimer l'objet de sa liste d'objets sous sa gestion.
	 * Sans doute � renommer...
	 *
	 * @param simObject the sim object
	 */
	void OnSimObjectRemoved(ISimObject simObject);
	
	/**
	 * Cette m�thode permet simplement au moteur d'ajouter l'objet dans sa liste d'objet sous sa gestion.
	 * Sans doute � renommer...
	 *
	 * @param simObject the sim object
	 */
	void OnSimObjectAdded(ISimObject simObject);
	
	/**
	 * On event posted.
	 *
	 * @param ev the ev
	 */
	void OnEventPosted(ISimEvent ev);
	
	/**
	 * On event un posted.
	 *
	 * @param ev the ev
	 */
	void OnEventUnPosted(ISimEvent ev);
	
	/**
	 * Cette fonction va initialiser la simulation, provoquer l�instanciation des diff�rentes entit�s puis leur initialisation.
	 *
	 * � am�liorer...
	 * 
	 * @param currentScenario the current scenario
	 * @param initScenario the init scenario
	 */
	void init(IScenario currentScenario,SimScenarioInit initScenario);
	
	/**
	 * Apr�s l�initialisation il s�agit d�activer la simulation en propageant l�activation � toutes les entit�s de la simulation
	 */
	void activateSimulation();
	
	/**
	 * Cette fonction permet � un objet ext�rieur de jalonner des interruptions du moteur.
	 *
	 * @param bydate the bydate
	 */
	void interrupt(EngineInterruptReason bydate);
	
	/**
	 * La fonction ci-dessous est le c�ur du moteur. 
	 * Elle va boucler sur les �v�nements post�s. C�est par l�activation provoqu�e de chaque entit� que �v�nements initiaux vont �tre cr��s. Et ce sont ces �v�nements qui produisent une r�action en chaine jusqu�� �puisement des 
	 * �v�nements ou l�atteinte de la fin de la dur�e demand�e de la simulation...
	 * 
	 */
	void simulate();
	
	/**
	 * Deactivate simulation.
	 */
	void deactivateSimulation();	
	
	/**
	 * Terminate.
	 *
	 * @param restart the restart
	 */
	void terminate(boolean restart);

}
