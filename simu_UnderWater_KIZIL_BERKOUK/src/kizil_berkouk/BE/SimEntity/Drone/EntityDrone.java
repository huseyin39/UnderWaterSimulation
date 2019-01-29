package kizil_berkouk.BE.SimEntity.Drone;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Map.Entry;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.logger.ToRecord;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
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
import kizil_berkouk.BE.SimEntity.MouvementSequenceur.EntityMouvementSequenceurKizilBerkouk1;
import kizil_berkouk.BE.SimEntity.MouvementSequenceur.EntityMouvementSequenceurKizilBerkouk2;
import kizil_berkouk.BE.SimEntity.MouvementSequenceur.EntityMouvementSequenceurKizilBerkouk3;
import kizil_berkouk.BE.SimEntity.MouvementSequenceur.EntityMouvementSequenceurKizilBerkouk4;
import kizil_berkouk.BE.SimEntity.MouvementSequenceur.EntityMouvementSequenceurKizilBerkouk5;
import enstabretagne.simulation.components.implementation.SimEntity;
import enstabretagne.simulation.core.implementation.SimEvent;


@ToRecord(name="Drone")
public class EntityDrone extends SimEntity implements IMovable,EntityDrone3DRepresentationInterface{
	Queue<LogicalDateTime> myQ = new LinkedList<LogicalDateTime>();//To handle the queue of messsages to send to boat
	private LogicalDateTime lastLogicalDateTime;
	
	private EntityMouvementSequenceur rmv;
	private EntityDroneInit DroneInit;
	private EntityDroneFeature DroneFeature;
	private droneListener mListener = null;
	//private ScheduledThreadPoolExecutor scheduledPool;


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
		String scenario = DroneInit.getMvtSeqInitial().getName();
		switch (scenario) {
		case "MSI1Drone":
			rmv = (EntityMouvementSequenceurKizilBerkouk1) createChild(EntityMouvementSequenceurKizilBerkouk1.class, "monSequenceur", ((EntityDroneFeature) getFeatures()).getSeqFeature());
			break;
		case "MSI2Drones":
			rmv = (EntityMouvementSequenceurKizilBerkouk2) createChild(EntityMouvementSequenceurKizilBerkouk2.class, "monSequenceur", ((EntityDroneFeature) getFeatures()).getSeqFeature());
			break;
		case "MSI3Drones":
			rmv = (EntityMouvementSequenceurKizilBerkouk3) createChild(EntityMouvementSequenceurKizilBerkouk3.class, "monSequenceur", ((EntityDroneFeature) getFeatures()).getSeqFeature());
			break;
			
		case "MSI4Drones":
			rmv = (EntityMouvementSequenceurKizilBerkouk4) createChild(EntityMouvementSequenceurKizilBerkouk4.class, "monSequenceur", ((EntityDroneFeature) getFeatures()).getSeqFeature());
			break;
		
		case "MSI5Drones":
			rmv = (EntityMouvementSequenceurKizilBerkouk5) createChild(EntityMouvementSequenceurKizilBerkouk5.class, "monSequenceur", ((EntityDroneFeature) getFeatures()).getSeqFeature());
			break;	
			
		default:
			break;
		}
		rmv.initialize(DroneInit.getMvtSeqInitial());
	}
	

	@Override
	protected void BeforeActivating(IEntity sender, boolean starting) {
		
	}

	@Override
	protected void AfterActivate(IEntity sender, boolean starting) {
		Logger.Detail(this, "AfterActivate", "Activation du drone");
		rmv.activate();
		Post(new startScan(this), getCurrentLogicalDate().add(LogicalDuration.ofSeconds(1)));
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
		//scheduledPool.shutdownNow();
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


	public void setDroneListener(droneListener listener) {
        this.mListener = listener;
    }
	
	private class sendMessageToBoat extends SimEvent { //add here the timer to send
		private ArtefactFeatures artefactFeatures;
		private ArtefactInit artefactInit;
		private EntityDrone entityDrone;
		
		public sendMessageToBoat(ArtefactFeatures artefactFeatures, ArtefactInit artefactInit, EntityDrone entityDrone) {
			this.artefactFeatures = artefactFeatures;
			this.artefactInit = artefactInit;
			this.entityDrone = entityDrone;
		}
		
		@Override
		public void Process() {
			if (mListener != null) 
	            mListener.artefactFoundEvent(entityDrone, artefactFeatures, artefactInit);
		}
		
	}
	
	/**
	 * To allow to the Boat to send messages to drone
	 * @author HuseyinK
	 *
	 */
	public interface droneListener {
        public void artefactFoundEvent(EntityDrone entityDrone, ArtefactFeatures artefactFeatures, ArtefactInit artefactInit);
    }
	
	
	/**
	 * Function which handles the scan procedures given the frequency.
	 */
	public class startScan extends SimEvent{
		public EntityDrone entityDrone;
		
		public startScan(EntityDrone entityDrone) {
			this.entityDrone = entityDrone;
		}
		
		@Override
		public void Process() {
			Logger.Detail(Owner(), "startScan", "%s scanned ", entityDrone.DroneInit.getName());
				System.out.println("Scanning " + getName());
				scan();
				Post(new startScan(entityDrone), getCurrentLogicalDate().add(LogicalDuration.ofMinutes(1)));
		}	
	}
	
	
	public void scan() {
		HashMap<ArtefactFeatures, ArtefactInit> artefacts = DroneFeature.getScenarioFeatures().getArtefacts();
		
		if (artefacts != null) { // pour éviter de Throw un nullPointerException
			Iterator<Entry<ArtefactFeatures, ArtefactInit>> iterator =  artefacts.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<ArtefactFeatures,ArtefactInit> entry = (Entry<ArtefactFeatures, ArtefactInit>) iterator.next();
				ArtefactFeatures artefactFeatures = entry.getKey();
				ArtefactInit artefactInit =entry.getValue();
				Point3D positionArtefact3d = artefactInit.getMvtSeqInit().getEtatInitial().getPosition(); //position artefact
				
				if (isDetectable(positionArtefact3d)) {
					randomTimeMessage(artefactFeatures, artefactInit);
					iterator.remove(); // retrait de l'artefact de la liste des artefacts
					break;
					//postInterrupt(new interuptPhase(), getCurrentLogicalDate().add(LogicalDuration.ofSeconds(1)));
				}
			}
		}
	}
	
	
	private void randomTimeMessage(ArtefactFeatures artefactFeatures, ArtefactInit artefactInit) {
		int delay = (int)Math.round(RandomGenerator().nextUniform(33, 35)); // correspond à 30 minutes pour faire la plongée, la remontée et l'envoie du message
		if (myQ.size() == 0) {
			lastLogicalDateTime =  getCurrentLogicalDate().add(LogicalDuration.ofMinutes(delay));
			Post(new sendMessageToBoat(artefactFeatures, artefactInit, this), lastLogicalDateTime);
		}
		else {
			lastLogicalDateTime = lastLogicalDateTime.add(LogicalDuration.ofMinutes(delay));
			Post(new sendMessageToBoat(artefactFeatures, artefactInit, this), lastLogicalDateTime);
		}
		myQ.add(lastLogicalDateTime);
	}
	
	
	/**
	 * To stop the scans
	 */
	public void stopMission() {
		Logger.Detail(this, "stopMission", "%s stopped", DroneInit.getName());
		UnPostAllEvents();
		//scheduledPool.shutdownNow();
	}
	

	
	private boolean isDetectable(Point3D positionArtefact3d) {
		double distance = getPosition().distance(positionArtefact3d);
		if (distance < 5000)
			return true;
		return false;
	}
	
	/*
	public class interuptPhase extends SimEvent {
		@Override
		public void Process() {
			StaticMover staticMover = new StaticMover(getPosition(), getPosition());
			Logger.Information(Owner(), "Process FinStaticPhase1", "Phase mouvement linéaire enclenché");
		}
	}*/

}
