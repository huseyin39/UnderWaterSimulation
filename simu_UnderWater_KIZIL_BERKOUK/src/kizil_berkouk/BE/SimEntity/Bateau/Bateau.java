package kizil_berkouk.BE.SimEntity.Bateau;

import java.util.List;

import enstabretagne.base.logger.Logger;
import enstabretagne.base.logger.ToRecord;
import enstabretagne.monitor.interfaces.IMovable;
import enstabretagne.simulation.components.IEntity;
import enstabretagne.simulation.components.data.SimFeatures;
import enstabretagne.simulation.components.data.SimInitParameters;
import enstabretagne.simulation.components.implementation.SimEntity;
import kizil_berkouk.BE.Scenarios.Scenario;
import kizil_berkouk.BE.SimEntity.Artefact.ArtefactFeatures;
import kizil_berkouk.BE.SimEntity.Bateau.Representation3D.IBateauRepresentation3D;
import kizil_berkouk.BE.SimEntity.Drone.EntityDrone;
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
		Logger.Detail(this, "AfterActivate", "Activation de la bouée %s","test");
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
	
	//Ajouté ici les getters
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
    public void artefactFoundEvent(EntityDrone entityDrone, ArtefactFeatures artefactFeatures) {
        Logger.Detail(this, "artefactFoundEvent", "Le drone %s a fini d\'envoyer les données d'enregistrement de l'artefact %s.", entityDrone.getName(), artefactFeatures.getId());
    }

}
