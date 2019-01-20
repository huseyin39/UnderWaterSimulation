/*
 * 
 */
package enstabretagne.simulation.core;

import java.util.List;

import enstabretagne.base.math.MoreRandom;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.simulation.core.notifications.SimObjectAddedListener;

// TODO: Auto-generated Javadoc
/**
 * The Interface IEngine. Cette interface est � usage des applications. 
 * Elle contient les services essentiels dont peut avoir besoin une entit�  
 */
public interface IEngine {
	
	/**
	 * Permet de s'abonner � la cr�ation d'un nouvel SimObject<br>
	 * <b>ATTENTION: l'objet renvoy� est "brut" � l'�tat BORN.</b>
	 * Ne pas utiliser l'objet "as is". Attendre son passage en �tat Activated.
	 * On peut utiliser � cet effet {@link IEntity#OnActivated()} pour s'abonner ensuite � l'activation 
	 *	
	 *Noter que {@link SimObjectAddedListener} est une interface fonctionnelle.<br>
	 *Peut s'utiliser ainsi: <code>AddSimObjectAddedListener(this::monlistener);</code> o� "<code>monlistener</code>" est une m�thode impl�mentant l'interface SimObjectAddedListener
	 *
	 * @param listener Noter que {@link SimObjectAddedListener} est une interface fonctionnelle.<br>
	 *Peut s'utiliser ainsi: <code>AddSimObjectAddedListener(this::monlistener);</c
	 */
	void AddSimObjectAddedListener(SimObjectAddedListener listener);
	
	/**
	 * Permet de se d�sabonner de l'�v�nement de cr�ation d'un nouveau SimObject
	 *
	 * @param listener the listener
	 */
	void RemoveSimObjectAddedListener(SimObjectAddedListener listener);
	
	/**
	 * Permet de requ�ter un objet connu du moteur.
	 * 
	 *
	 * @param objectToBeFound est une fonction dont la signature correspond � l'interface fonctionnelle SimObjectRequest
	 * @return the list
	 */
	List<ISimObject> requestSimObject(SimObjectRequest objectToBeFound);
	
	/**
	 * Gets the random generator.
	 *
	 * @return the random generator
	 */
	MoreRandom getRandomGenerator();
	
	/**
	 * Simulation date.
	 *
	 * @return the logical date time
	 */
	LogicalDateTime SimulationDate();	


}
