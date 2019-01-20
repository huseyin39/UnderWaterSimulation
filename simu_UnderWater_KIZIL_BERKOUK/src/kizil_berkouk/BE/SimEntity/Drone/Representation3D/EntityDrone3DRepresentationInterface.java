package kizil_berkouk.BE.SimEntity.Drone.Representation3D;

import enstabretagne.monitor.interfaces.IMovable;
import javafx.scene.paint.Color;

public interface EntityDrone3DRepresentationInterface extends IMovable{
	Color getColor();
	double getRayon();
	double getLongueur();

}