package kizil_berkouk.BE.SimEntity.Drone;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import enstabretagne.base.logger.Logger;
import enstabretagne.base.logger.ToRecord;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.monitor.interfaces.IMovable;
import enstabretagne.simulation.components.IEntity;
import enstabretagne.simulation.components.data.SimFeatures;
import enstabretagne.simulation.components.data.SimInitParameters;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import kizil_berkouk.BE.SimEntity.Artefact.ArtefactFeatures;
import kizil_berkouk.BE.SimEntity.Artefact.ArtefactInit;
import kizil_berkouk.BE.SimEntity.Drone.Representation3D.EntityDrone3DRepresentationInterface;
import kizil_berkouk.BE.SimEntity.MouvementSequenceur.EntityMouvementSequenceur;
import kizil_berkouk.BE.SimEntity.MouvementSequenceur.EntityMouvementSequenceur_Exemple;
import enstabretagne.simulation.components.implementation.SimEntity;


@ToRecord(name="Drone")
public class EntityDrone extends SimEntity implements IMovable,EntityDrone3DRepresentationInterface{
	
	private EntityMouvementSequenceur rmv;
	private EntityDroneInit DroneInit;
	private EntityDroneFeature DroneFeature;
	
	public EntityDrone(String name, SimFeatures features) {
		super(name, features);
		DroneFeature = (EntityDroneFeature) features;
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
		Logger.Detail(this, "AfterActivate", "Activation du drone");
		rmv.activate();
		startScan();
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
	}

	@Override
	protected void AfterTerminated(IEntity sender, boolean restart) {		
	}

	@Override
	public Color getColor() {
		return DroneFeature.getCouleur();
	}

	@Override
	public double getRayon() {
		return DroneFeature.getRayon();
	}

	@Override
	public double getLongueur() {
		return DroneFeature.getTaille();
	}
	
	public void startScan() {
		Runnable runnabledelayedTask = new Runnable()
		{
			@Override
			public void run()
			{
				LogicalDateTime d = getCurrentLogicalDate();
				if (d.compareTo(new LogicalDateTime("20/01/2018 06:00")) > 0)
					scan();
			}
		};
		ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(1);
		scheduledPool.scheduleWithFixedDelay(runnabledelayedTask, 0, 2, TimeUnit.SECONDS);
	}
	
	public void scan() {
		System.out.println("Scanning " + getName());
		HashMap<ArtefactFeatures, ArtefactInit> artefacts = DroneFeature.getScenarioFeatures().getArtefacts();
		if (artefacts != null) { // pour éviter de Throw un nullPointerException
			for (Map.Entry<ArtefactFeatures, ArtefactInit> entry : artefacts.entrySet()) {
				Point3D positionArtefact3d = entry.getValue().getMvtSeqInit().getEtatInitial().getPosition(); //position artefact
				if (isDetectable(positionArtefact3d)) {
					// stoppez le drone ici et analyzer la cible
					System.out.println("Artefact trouvé " + entry.getKey().getId() + " position " + positionArtefact3d.toString()); 
					artefacts.remove(entry.getKey()); //On retire l'objet trouvé
					break;
				}
				
			}
		}
		
	}
	
	private boolean isDetectable(Point3D positionArtefact3d) {
		double distance = getPosition().distance(positionArtefact3d);
		if (distance < 100)
			return true;
		return false;
		
	}

}
