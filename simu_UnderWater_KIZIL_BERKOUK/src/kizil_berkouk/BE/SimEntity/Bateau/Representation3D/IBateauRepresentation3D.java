package kizil_berkouk.BE.SimEntity.Bateau.Representation3D;

import enstabretagne.monitor.interfaces.IMovable;
import javafx.scene.paint.Color;

public interface IBateauRepresentation3D extends IMovable{
	Color getColor();
	//modifié ici les prototypes de l'interface
	double getLargeur();
	double getLongueur();
	double getHauteur();
}
