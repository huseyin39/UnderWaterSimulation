/**
* Classe ICloneable.java
*@author Olivier VERRON
*@version 1.0.
*/
package enstabretagne.simulation.core;
// TODO: Auto-generated Javadoc
// R�sum�:
//     Prend en charge le clonage, qui cr�e une nouvelle instance d'une classe avec
/**
 * The Interface ICloneable.
 */
//     la m�me valeur qu'une instance existante.
public interface ICloneable extends Cloneable
{
    // R�sum�:
    //     Cr�e un nouvel objet qui est une copie de l'instance en cours.
    //
    // Retourne�:
    /**
     * Clone.
     *
     * @return the object
     */
    //     Nouvel objet qui est une copie de cette instance.
    Object clone();
}

