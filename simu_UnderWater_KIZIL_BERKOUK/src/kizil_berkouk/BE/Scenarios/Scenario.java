package kizil_berkouk.BE.Scenarios;

import java.util.ArrayList;
import java.util.Map;

import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.simulation.components.IEntity;
import enstabretagne.simulation.components.ScenarioId;
import enstabretagne.simulation.components.data.SimFeatures;
import enstabretagne.simulation.components.data.SimInitParameters;
import enstabretagne.simulation.components.implementation.SimEntity;
import enstabretagne.simulation.components.implementation.SimScenario;
import enstabretagne.simulation.core.implementation.SimEvent;
import kizil_berkouk.BE.SimEntity.Artefact.Artefact;
import kizil_berkouk.BE.SimEntity.Artefact.ArtefactFeatures;
import kizil_berkouk.BE.SimEntity.Artefact.ArtefactInit;
import kizil_berkouk.BE.SimEntity.Bateau.Bateau;
import kizil_berkouk.BE.SimEntity.Bateau.BateauFeatures;
import kizil_berkouk.BE.SimEntity.Bateau.BateauInit;
import kizil_berkouk.BE.SimEntity.Drone.EntityDrone;
import kizil_berkouk.BE.SimEntity.Drone.EntityDroneFeature;
import kizil_berkouk.BE.SimEntity.Drone.EntityDroneInit;
import kizil_berkouk.BE.SimEntity.Ocean.EntityOcean;
import kizil_berkouk.BE.SimEntity.Ocean.EntityOceanFeature;
import kizil_berkouk.BE.SimEntity.Ocean.EntityOceanInit;

public class Scenario extends SimScenario{
	
	protected static ArrayList<EntityDrone> listEntityDrones = new ArrayList<>();
	

	public Scenario(ScenarioId id, SimFeatures features, LogicalDateTime start, LogicalDateTime end) {
		super(id, features, start, end);
		
	}
	
	@Override
	protected void initializeSimEntity(SimInitParameters init) {
		super.initializeSimEntity(init);
	}
	
	@Override
	protected void AfterActivate(IEntity sender, boolean starting) {
		super.AfterActivate(sender, starting);
		
		ScenarioFeatures feature = (ScenarioFeatures) getFeatures();
		Logger.Detail(this, "afteractivate", "taille liste artefacts = %s", feature.getArtefacts().size());

		for(Map.Entry<ArtefactFeatures, ArtefactInit> e : feature.getArtefacts().entrySet())
		{
			Logger.Detail(this, "afteractivate", "artefact à créer = %s , %s", e.getValue(),e.getKey());
			Post(new ArtefactArrival(e.getValue(),e.getKey()));
		}
		for(Map.Entry<BateauFeatures, BateauInit> e : feature.getBateau().entrySet())
		{
			Logger.Detail(this, "afteractivate", "bateau à créer = %s , %s", e.getValue(),e.getKey());
			Post(new BateauArrival(e.getValue(),e.getKey()));
		}
		for(Map.Entry<EntityDroneFeature, EntityDroneInit> e : feature.getDrones().entrySet())
		{
			Logger.Detail(this, "afteractivate", "drone à créer = %s , %s", e.getValue(),e.getKey());
			Post(new DroneArrival(e.getValue(),e.getKey()));

		}
		for(Map.Entry<EntityOceanFeature, EntityOceanInit> e : feature.getOcean().entrySet())
		{
			Logger.Detail(this, "afteractivate", "océan à créer = %s , %s", e.getValue(),e.getKey());
			Post(new OceanArrival(e.getValue(),e.getKey()));
		}
		
	}
	
	class BateauArrival extends SimEvent
	{
		private BateauInit i;
		private BateauFeatures f;
		public BateauInit getI() {
			return i;
		}
		
		public BateauFeatures getF() {
			return f;
		}
		
		
		public BateauArrival(BateauInit i, BateauFeatures f) {
			this.i=i;
			this.f=f;
		}

		@Override
		public void Process() {
			Logger.Detail(this, "BateauArrival.Process", "Création du bateau " + i);
			SimEntity b = createChild(Bateau.class, i.getName() , f);
			b.initialize(getI());
			b.activate();
		}
		
	}

	class DroneArrival extends SimEvent
	{
		private EntityDroneInit i;
		private EntityDroneFeature f;
		
		public EntityDroneInit getI() {
			return i;
		}
		
		public EntityDroneFeature getF() {
			return f;
		}
		
		
		public DroneArrival(EntityDroneInit i, EntityDroneFeature f) {
			this.i=i;
			this.f=f;
		}

		@Override
		public void Process() {
			Logger.Detail(this, "DroneArrival.Process", "Création du drone " + i.getName());
			SimEntity b = createChild(EntityDrone.class, i.getName() , f);
			EntityDrone drone = (EntityDrone) b;
			listEntityDrones.add(drone);
			b.initialize(getI());
			b.activate();
		}
		
	}
	
	class ArtefactArrival extends SimEvent
	{
		private ArtefactInit i;
		private ArtefactFeatures f;
		
		public ArtefactInit getI() {
			return i;
		}
		
		public ArtefactFeatures getF() {
			return f;
		}
		
		
		public ArtefactArrival(ArtefactInit i, ArtefactFeatures f) {
			this.i=i;
			this.f=f;
		}

		@Override
		public void Process() {
			Logger.Detail(this, "ArtefactArrival.Process", "Création de l'artefact" + i.getName());
			SimEntity b = createChild(Artefact.class, i.getName() , f);
			b.initialize(getI());
			b.activate();
		}
		
	}

	class OceanArrival extends SimEvent
	{
		private EntityOceanInit i;
		private EntityOceanFeature f;
		
		public EntityOceanInit getI() {
			return i;
		}
		
		public EntityOceanFeature getF() {
			return f;
		}
		
		
		public OceanArrival(EntityOceanInit i, EntityOceanFeature f) {
			this.i=i;
			this.f=f;
		}

		@Override
		public void Process() {
			Logger.Detail(this, "OceanArrival.Process", "Création de l'océan" + i.getName());
			SimEntity b = createChild(EntityOcean.class, i.getName() , f);
			b.initialize(getI());
			b.activate();
		}
		
	}
}
