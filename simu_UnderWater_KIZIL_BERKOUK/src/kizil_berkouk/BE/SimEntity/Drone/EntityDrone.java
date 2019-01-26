package kizil_berkouk.BE.SimEntity.Drone;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import enstabretagne.base.logger.Logger;
import enstabretagne.base.logger.ToRecord;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import enstabretagne.monitor.implementation.FX3DMonitor2;
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
import kizil_berkouk.BE.SimEntity.MouvementSequenceur.RectilinearMover;
import kizil_berkouk.BE.SimEntity.MouvementSequenceur.StaticMover;
import enstabretagne.simulation.components.implementation.SimEntity;
import enstabretagne.simulation.core.implementation.SimEvent;


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
		ScheduledThreadPoolExecutor scheduledPool = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
		Runnable runnabledelayedTask = new Runnable()
		{
			@Override
			public void run()
			{
				LogicalDateTime d = getCurrentLogicalDate();
				if (d.compareTo(new LogicalDateTime("20/01/2018 06:00")) > 0) {
					System.out.println("Scanning " + getName());
					if (scan())
						scheduledPool.shutdownNow();
					
				}
			}
		};
		
		scheduledPool.scheduleWithFixedDelay(runnabledelayedTask, 0, 1, TimeUnit.SECONDS);
	}
	
	public boolean scan() {
		HashMap<ArtefactFeatures, ArtefactInit> artefacts = DroneFeature.getScenarioFeatures().getArtefacts();
		
		if (artefacts != null) { // pour éviter de Throw un nullPointerException
			for (Map.Entry<ArtefactFeatures, ArtefactInit> entry : artefacts.entrySet()) {
				ArtefactFeatures artefactFeatures = entry.getKey();
				ArtefactInit artefactInit =entry.getValue();
				Point3D positionArtefact3d = artefactInit.getMvtSeqInit().getEtatInitial().getPosition(); //position artefact
				
				if (isDetectable(positionArtefact3d)) {
					//postInterrupt(new interuptPhase(), getCurrentLogicalDate().add(LogicalDuration.ofSeconds(1)));
					if (artefactInit.getType() == 0) {
						System.out.println("\n ------- Cible trouvé !! " + " Position :" + positionArtefact3d.toString());
						interruptEngineByDate();
						//FX3DMonitor2.finishIt();
						return true;
						
					}else {
						artefacts.remove(artefactFeatures); //On retire l'objet trouvé
						System.out.println("Artefact trouvé " + artefactFeatures.getId() + " position " + positionArtefact3d.toString());
						return false;
					}
				}
			}
		}
		return true;
	}
	
	
	
	
	public class interuptPhase extends SimEvent {

		@Override
		public void Process() {
			StaticMover staticMover = new StaticMover(getPosition(), getPosition());
			Logger.Information(Owner(), "Process FinStaticPhase1", "Phase mouvement linéaire enclenché");
		}
	}
	
	private boolean isDetectable(Point3D positionArtefact3d) {
		double distance = getPosition().distance(positionArtefact3d);
		if (distance < 5000)
			return true;
		return false;
		
	}

}
