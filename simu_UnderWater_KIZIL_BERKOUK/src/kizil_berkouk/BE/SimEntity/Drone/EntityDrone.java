package kizil_berkouk.BE.SimEntity.Drone;

import enstabretagne.base.logger.Logger;
import enstabretagne.base.logger.ToRecord;
import enstabretagne.monitor.interfaces.IMovable;
import enstabretagne.simulation.components.IEntity;
import enstabretagne.simulation.components.data.SimFeatures;
import enstabretagne.simulation.components.data.SimInitParameters;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import kizil_berkouk.BE.SimEntity.Drone.Representation3D.EntityDrone3DRepresentationInterface;
import kizil_berkouk.BE.SimEntity.MouvementSequenceur.EntityMouvementSequenceur;
import kizil_berkouk.BE.SimEntity.MouvementSequenceur.EntityMouvementSequenceur_Exemple;
import enstabretagne.simulation.components.implementation.SimEntity;


@ToRecord(name="Drone")
public class EntityDrone extends SimEntity implements IMovable,EntityDrone3DRepresentationInterface{
	
	private EntityMouvementSequenceur rmv;
	private EntityDroneInit DroneInit;
	private EntityDroneFeature NavireFeature;
	
	public EntityDrone(String name, SimFeatures features) {
		super(name, features);
		NavireFeature = (EntityDroneFeature) features;
	}

	@Override
	public void onParentSet() {
		
	}

	@Override
	protected void initializeSimEntity(SimInitParameters init) {
		DroneInit = (EntityDroneInit) getInitParameters();

		rmv = (EntityMouvementSequenceur_Exemple) createChild(EntityMouvementSequenceur_Exemple.class, "monSequenceur", ((EntityDroneFeature) getFeatures()).getSeqFeature());
		rmv.initialize(DroneInit.getMvtSeqInitial());
	
	}

	@Override
	protected void BeforeActivating(IEntity sender, boolean starting) {
		
	}

	@Override
	protected void AfterActivate(IEntity sender, boolean starting) {
		Logger.Detail(this, "AfterActivate", "Activation de Navire");
		rmv.activate();
	}
	

	@ToRecord(name="Position")
	@Override
	public Point3D getPosition() {
		return rmv.getPosition(getCurrentLogicalDate());
	}

	@ToRecord(name="Vitesse")
	@Override
	public Point3D getVitesse() {
		return rmv.getVitesse(getCurrentLogicalDate());
	}

	@ToRecord(name="Acceleration")
	@Override
	public Point3D getAcceleration() {
		return rmv.getAcceleration(getCurrentLogicalDate());
	}

	@ToRecord(name="VitesseRotation")
	@Override
	public Point3D getVitesseRotationXYZ() {
		return rmv.getVitesseRotationXYZ(getCurrentLogicalDate());
	}

	@ToRecord(name="AccelerationRotation")
	@Override
	public Point3D getAccelerationRotationXYZ() {
		return rmv.getAccelerationRotationXYZ(getCurrentLogicalDate());
	}

	@ToRecord(name="Rotation")
	@Override
	public Point3D getRotationXYZ() {
		return rmv.getRotationXYZ(getCurrentLogicalDate());
	}


	@Override
	protected void BeforeDeactivating(IEntity sender, boolean starting) {
		rmv.deactivate();
	}

	@Override
	protected void AfterDeactivated(IEntity sender, boolean starting) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void AfterTerminated(IEntity sender, boolean restart) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Color getColor() {
		return NavireFeature.getCouleur();
	}

	@Override
	public double getRayon() {
		return NavireFeature.getRayon();
	}

	@Override
	public double getLongueur() {
		return NavireFeature.getTaille();
	}

}
