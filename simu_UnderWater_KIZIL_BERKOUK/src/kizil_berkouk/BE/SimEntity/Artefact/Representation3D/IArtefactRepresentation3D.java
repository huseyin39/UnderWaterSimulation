package kizil_berkouk.BE.SimEntity.Artefact.Representation3D;

import enstabretagne.monitor.interfaces.IMovable;
import javafx.scene.paint.Color;

public interface IArtefactRepresentation3D extends IMovable{
	double getSize1();
	double getSize2();
	double getSize3();
	Color getColor();
	int getType();
}
