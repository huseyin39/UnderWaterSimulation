package kizil_berkouk.BE;

import java.util.HashMap;

import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import enstabretagne.monitor.implementation.MovableState;
import enstabretagne.simulation.components.ScenarioId;
import enstabretagne.simulation.core.IScenario;
import enstabretagne.simulation.core.IScenarioInstance;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import kizil_berkouk.BE.Scenarios.Scenario;
import kizil_berkouk.BE.Scenarios.ScenarioFeatures;
import kizil_berkouk.BE.SimEntity.Bateau.BateauFeatures;
import kizil_berkouk.BE.SimEntity.Bateau.BateauInit;
import kizil_berkouk.BE.SimEntity.MouvementSequenceur.EntityMouvementSequenceurFeature;
import kizil_berkouk.BE.SimEntity.MouvementSequenceur.EntityMouvementSequenceurInit;
import kizil_berkouk.BE.SimEntity.Drone.EntityDroneFeature;
import kizil_berkouk.BE.SimEntity.Drone.EntityDroneInit;
import kizil_berkouk.BE.SimEntity.Artefact.ArtefactFeatures;
import kizil_berkouk.BE.SimEntity.Artefact.ArtefactInit;
import kizil_berkouk.BE.SimEntity.Ocean.EntityOceanFeature;
import kizil_berkouk.BE.SimEntity.Ocean.EntityOceanInit;

public class ScenarioInstanceBE implements IScenarioInstance {

	@Override
	public IScenario getScenarioInstance() {
		ScenarioFeatures kizil_berkouksf = new ScenarioFeatures("kizil_berkoukSF");
		
		HashMap<String,Point3D> positionsCles = new HashMap<String, Point3D>();

		//Création de l'ocean
		MovableState mstOcean = new MovableState(Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO); // création d'un objet movable
		EntityMouvementSequenceurInit msiOcean = new EntityMouvementSequenceurInit("MSIOCEAN", mstOcean, 0, 0, 0, 0, positionsCles); // initialisation
		kizil_berkouksf.getOcean().put(new EntityOceanFeature("O1"), new EntityOceanInit("Zone de recherche", msiOcean));
		
		//création du bateau
		MovableState mstBateau;
		EntityMouvementSequenceurInit msiBateau;
		MovableState mst; 

		mstBateau= new MovableState(new Point3D(0,0,1), Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO);
		msiBateau= new EntityMouvementSequenceurInit("MSI", mstBateau, 0, 0,0,0, positionsCles);
		kizil_berkouksf.getBateau().put(new BateauFeatures("Bateau", 0, 0, 3, 2, 2), new BateauInit("Bateau", msiBateau, Color.RED));

		

		/*//Navire1
		mst= new MovableState(new Point3D(0,0,0), new Point3D(1,1,0), Point3D.ZERO, new Point3D(0,0,45.0), new Point3D(10,5,0.0), Point3D.ZERO);
		msi= new EntityMouvementSequenceurInit("MSI", mst, 10, 100,2,8, positionsCles);
		feat = new EntityMouvementSequenceurFeature("MSF");
		bsf.getNavires().put(new EntityNavireFeature("NavireF",5,3,Color.BLACK,feat), new EntityNavireInit("Navire Observation 1", msi));

		//Création de bouees
		int i=0;
		int N=10;
		positionsCles = new HashMap<String, Point3D>();
		for(i=0;i<N;i++) 
		{
			MovableState mstBouee;
			EntityMouvementSequenceurInit msiBouee;
			
			
			mstBouee = new MovableState(new Point3D(10*i,0,0), Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO);
			msiBouee= new EntityMouvementSequenceurInit("MSI", mstBouee, 0, 0,0,0, positionsCles);
			bsf.getBouees().put(new BoueeFeatures("B1",5,1,3.0), new BoueeInit("B"+i,msiBouee,Color.RED));

			
		}
		
		//Création du navire et des points de passage
		positionsCles = new HashMap<String, Point3D>();
		positionsCles.put("start", new Point3D(0,0,0));
		positionsCles.put("PointCible1", new Point3D(10,10,0));
		positionsCles.put("PointCible2", new Point3D(30,-10,0));
		positionsCles.put("PointCible3", new Point3D(20,0,0));
		positionsCles.put("PointDirection", new Point3D(20,20,0));
		positionsCles.put("PointSousEau", new Point3D(0,0,-10));
		positionsCles.put("ObservationMine", new Point3D(20,20,-20));
		*/
		
		
		LogicalDateTime start = new LogicalDateTime("20/01/2018 06:00");
		LogicalDateTime end = start.add(LogicalDuration.ofMinutes(2)); //time here to modifiy?
		Scenario bms = new Scenario(new ScenarioId("Scenario"), kizil_berkouksf, start, end);
		
		return bms;
	}


}
