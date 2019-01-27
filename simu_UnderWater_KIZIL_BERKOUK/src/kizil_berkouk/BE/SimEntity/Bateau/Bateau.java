package kizil_berkouk.BE.SimEntity.Bateau;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import enstabretagne.base.logger.Logger;
import enstabretagne.base.logger.ToRecord;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import enstabretagne.monitor.interfaces.IMovable;
import enstabretagne.simulation.components.IEntity;
import enstabretagne.simulation.components.data.SimFeatures;
import enstabretagne.simulation.components.data.SimInitParameters;
import enstabretagne.simulation.components.implementation.SimEntity;
import enstabretagne.simulation.core.implementation.SimEvent;
import kizil_berkouk.BE.Scenarios.Scenario;
import kizil_berkouk.BE.SimEntity.Artefact.ArtefactFeatures;
import kizil_berkouk.BE.SimEntity.Artefact.ArtefactInit;
import kizil_berkouk.BE.SimEntity.Bateau.Representation3D.IBateauRepresentation3D;
import kizil_berkouk.BE.SimEntity.Drone.EntityDrone;
import kizil_berkouk.BE.SimEntity.Drone.EntityDrone.startScan;
import kizil_berkouk.BE.SimEntity.MouvementSequenceur.EntityMouvementSequenceur;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;

@ToRecord(name="Bateau")
public class Bateau extends SimEntity implements IMovable, IBateauRepresentation3D, EntityDrone.droneListener{
	
	private List<EntityDrone> entityDrones;
	private EntityMouvementSequenceur rmv;

	public Bateau(String name, SimFeatures features) {
		super(name, features);
	}

	@Override
	public void onParentSet() {
		
	}

	@Override
	protected void initializeSimEntity(SimInitParameters init) {
		BateauInit bi = (BateauInit) getInitParameters();
		
		rmv = (EntityMouvementSequenceur) createChild(EntityMouvementSequenceur.class, "monSequenceur", ((BateauFeatures) getFeatures()).getSeqFeature());
		rmv.initialize(bi.getMvtSeqInit());
		entityDrones = Scenario.getListEntityDrones();
		for (EntityDrone eDrone : entityDrones) {
			eDrone.setDroneListener(this);
		 }
	}

	@Override
	protected void BeforeActivating(IEntity sender, boolean starting) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void AfterActivate(IEntity sender, boolean starting) {
		Logger.Detail(this, "AfterActivate", "Activation de la bou�e %s","test");
		rmv.activate();
//		Logger.Data(this);
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

	@Override
	public Color getColor() {
		return ((BateauInit) getInitParameters()).getColor();
	}
	
	//Ajout� ici les getters
	@Override
	public double getHauteur() {
		return ((BateauFeatures) getFeatures()).getLargeur();
	}

	@Override
	public double getLongueur() {
		return ((BateauFeatures) getFeatures()).getLargeur();
	}
	
	@Override
	public double getLargeur() {
		return ((BateauFeatures) getFeatures()).getLargeur();
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
	public Point3D getVitesseRotationXYZ() {
		return rmv.getVitesseRotationXYZ(getCurrentLogicalDate());
	}

	@Override
	public Point3D getAccelerationRotationXYZ() {
		return rmv.getAccelerationRotationXYZ(getCurrentLogicalDate());
	}

	@Override
	public Point3D getRotationXYZ() {
		return rmv.getRotationXYZ(getCurrentLogicalDate());
	}
	
	@Override
    public void artefactFoundEvent(EntityDrone entityDrone, ArtefactFeatures artefactFeatures, ArtefactInit artefactInit) {
        Logger.Detail(this, "artefactFoundEvent", "Le drone %s a fini d\'envoyer les donn�es d'enregistrement de l'artefact %s.", entityDrone.getName(), artefactFeatures.getId());
        Logger.Detail(this, "artefactFoundEvent", "Scanning de l\'artefact  %s par l\'equipage", artefactFeatures.getId());
		
		int delay = (int)Math.round(RandomGenerator().nextUniform(20, 40));
		Post(new analyzeArtefact(artefactInit, artefactFeatures), getCurrentLogicalDate().add(LogicalDuration.ofMinutes(delay)));
	}
	
	public class analyzeArtefact extends SimEvent {
		private ArtefactInit artefactInit;
		private ArtefactFeatures artefactFeatures;
		
		public analyzeArtefact(ArtefactInit artefactInit, ArtefactFeatures artefactFeatures) {
			this.artefactFeatures = artefactFeatures;
			this.artefactInit = artefactInit;
		}
		
		@Override
		public void Process() {
			Point3D positionArtefact3d = artefactInit.getMvtSeqInit().getEtatInitial().getPosition(); //position artefact
			if (artefactInit.getType() == 0) {
				LogicalDuration d = getCurrentLogicalDate().soustract(new LogicalDateTime("20/01/2018 06:00"));
				Logger.Detail(this, "analyzeArtefact", " ------ CIBLE TROUVEEEEE ------ Position : %s ---- Temps �coul� depuis le d�but : %s", positionArtefact3d.toString(), d.toString());
				interruptMission();
				//FX3DMonitor2.finishIt();
				
			}else {
				String message = "Artefact trouv� " + artefactFeatures.getId() + " position " + positionArtefact3d.toString();
				Logger.Detail(this, "analyzeArtefact", message);
			}
		}
		
	}
	
	private void interruptMission() { // send message to drones
		interruptEngineByDate();
		for(EntityDrone entityDrone: entityDrones)
			entityDrone.stopMission();
	}
	

}
