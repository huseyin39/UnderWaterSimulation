package kizil_berkouk.BE;

import java.util.HashMap;

import enstabretagne.base.math.MoreRandom;
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
	MoreRandom mRandom = new MoreRandom();
	public static int nbDrone = 2;

	@Override
	public IScenario getScenarioInstance() {
		ScenarioFeatures kizil_berkouksf = new ScenarioFeatures("kizil_berkoukSF");
		// Trajectoire du D1 -- 1D
		HashMap<String,Point3D> positionsCles = new HashMap<String, Point3D>();

			positionsCles.put("start", new Point3D(-600,-600,0));
			positionsCles.put("PointCible1", new Point3D(-600,600,0));
			positionsCles.put("PointCible2", new Point3D(200,600,0));
			positionsCles.put("PointCible3", new Point3D(200,-600,0));
			positionsCles.put("PointCible4", new Point3D(1000,-600,0));
			positionsCles.put("PointCible5", new Point3D(1000,600,0));

		// Trajectoire D1 -- 2D
		HashMap<String,Point3D> positionsCles21 = new HashMap<String, Point3D>();
			positionsCles21.put("start", new Point3D(-600,-600,0));
			positionsCles21.put("PointCible1", new Point3D(-600,600,0));
			positionsCles21.put("PointCible2", new Point3D(200,600,0));	
		// Trajectoire D2 -- 2D
		HashMap<String,Point3D> positionsCles22 = new HashMap<String, Point3D>();
			positionsCles22.put("start", new Point3D(200,600,0));
			positionsCles22.put("PointCible3", new Point3D(200,-600,0));
			positionsCles22.put("PointCible4", new Point3D(1000,-600,0));
			positionsCles22.put("PointCible5", new Point3D(1000,600,0));
			
		// Trajectoire D1 -- 3
			HashMap<String,Point3D> positionsCles31 = new HashMap<String, Point3D>();
			positionsCles31.put("start", new Point3D(-600,-600,0));
			positionsCles31.put("PointCible1", new Point3D(-600,600,0));
			positionsCles31.put("PointCible2", new Point3D(200,600,0));
		// Trajectoire D2 -- 3
			HashMap<String,Point3D> positionsCles32 = new HashMap<String, Point3D>();
			positionsCles32.put("start", new Point3D(200,200,0));
			positionsCles32.put("PointCible3", new Point3D(200,-600,0));
			positionsCles32.put("PointCible4", new Point3D(1000,-600,0));
		// Trajectoire D3 -- 3
			HashMap<String,Point3D> positionsCles33 = new HashMap<String, Point3D>();
			positionsCles33.put("start", new Point3D(600,-600,0));
			positionsCles33.put("PointCible5", new Point3D(1000,600,0));
			
			
		//Création de l'ocean
		MovableState mstOcean = new MovableState(Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO); // création d'un objet movable
		EntityMouvementSequenceurInit msiOcean = new EntityMouvementSequenceurInit("MSIOCEAN", mstOcean, 0, 0, 0, 0, positionsCles); // initialisation
		kizil_berkouksf.getOcean().put(new EntityOceanFeature("O1"), new EntityOceanInit("Zone de recherche", msiOcean));
		
		//Création du bateau
		MovableState mstBateau;
		EntityMouvementSequenceurInit msiBateau;

		mstBateau= new MovableState(new Point3D(0,0,1), Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO);
		msiBateau= new EntityMouvementSequenceurInit("MSI", mstBateau, 0, 0,0,0, positionsCles);
		kizil_berkouksf.getBateau().put(new BateauFeatures("Bateau",0 , 0, 30, 20, 20), new BateauInit("Bateau", msiBateau, Color.RED));

		// Création drone
		MovableState mstDrone;
		EntityMouvementSequenceurInit msiDrone;
		EntityMouvementSequenceurFeature msfDrone;
		switch(nbDrone)
		{
		case 1: 
			// Drone 1
			mstDrone= new MovableState(new Point3D(-600,-600,0),new Point3D(Math.sqrt(2)*4, Math.sqrt(2)*4,0), Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO);
			msiDrone= new EntityMouvementSequenceurInit("MSI", mstDrone, 200, 0, 0, 0, positionsCles);
			msfDrone = new EntityMouvementSequenceurFeature("MSF");
			kizil_berkouksf.getDrones().put(new EntityDroneFeature("Drone1", 10, 5, Color.BLACK, msfDrone, kizil_berkouksf), new EntityDroneInit("Drone1", msiDrone));
			break;
		case 2 : 
			//Drone 2
			mstDrone= new MovableState(new Point3D(200,600,0),new Point3D(Math.sqrt(2)*4, Math.sqrt(2)*4,0), Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO);
			msiDrone= new EntityMouvementSequenceurInit("MSI", mstDrone, 200, 0, 0, 2, positionsCles22);
			msfDrone = new EntityMouvementSequenceurFeature("MSF");
			kizil_berkouksf.getDrones().put(new EntityDroneFeature("Drone2", 10, 5, Color.GREEN, msfDrone, kizil_berkouksf), new EntityDroneInit("Drone2", msiDrone));
			// Drone 1
			mstDrone= new MovableState(new Point3D(-600,-600,0),new Point3D(Math.sqrt(2)*4, Math.sqrt(2)*4,0), Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO);
			msiDrone= new EntityMouvementSequenceurInit("MSI", mstDrone, 200, 0, 0, 0, positionsCles21);
			msfDrone = new EntityMouvementSequenceurFeature("MSF");
			kizil_berkouksf.getDrones().put(new EntityDroneFeature("Drone1", 10, 5, Color.BLACK, msfDrone, kizil_berkouksf), new EntityDroneInit("Drone1", msiDrone));
			
			break;
		case 3 : 
			// Drone 1
			mstDrone= new MovableState(new Point3D(-600,-600,0),new Point3D(Math.sqrt(2)*4, Math.sqrt(2)*4,0), Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO);
			msiDrone= new EntityMouvementSequenceurInit("MSI", mstDrone, 2000, 0, 0, 0, positionsCles31);
			msfDrone = new EntityMouvementSequenceurFeature("MSF");
			kizil_berkouksf.getDrones().put(new EntityDroneFeature("Drone1", 10, 5, Color.BLACK, msfDrone, kizil_berkouksf), new EntityDroneInit("Drone1", msiDrone));
			
			//Drone 2
			mstDrone= new MovableState(new Point3D(200,200,0),new Point3D(Math.sqrt(2)*4, Math.sqrt(2)*4,0), Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO);
			msiDrone= new EntityMouvementSequenceurInit("MSI", mstDrone, 2000, 0, 0, 0, positionsCles32);
			msfDrone = new EntityMouvementSequenceurFeature("MSF");
			kizil_berkouksf.getDrones().put(new EntityDroneFeature("Drone2", 10, 5, Color.GREEN, msfDrone, kizil_berkouksf), new EntityDroneInit("Drone2", msiDrone));
		
			// Drone 3
			mstDrone= new MovableState(new Point3D(600,-600,0),new Point3D(Math.sqrt(2)*4, Math.sqrt(2)*4,0), Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO);
			msiDrone= new EntityMouvementSequenceurInit("MSI", mstDrone, 2000, 0, 0, 0, positionsCles33);
			msfDrone = new EntityMouvementSequenceurFeature("MSF");
			kizil_berkouksf.getDrones().put(new EntityDroneFeature("Drone2", 10, 5, Color.BROWN, msfDrone, kizil_berkouksf), new EntityDroneInit("Drone3", msiDrone));
			
		break;	
//		case 4 : 
//			// Drone 1
//			mstDrone= new MovableState(new Point3D(-6000,-6000,0),new Point3D(Math.sqrt(2)*4, Math.sqrt(2)*4,0), Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO);
//			msiDrone= new EntityMouvementSequenceurInit("MSI", mstDrone, 2000, 0, 0, 0, positionsCles41);
//			msfDrone = new EntityMouvementSequenceurFeature("MSF");
//			kizil_berkouksf.getDrones().put(new EntityDroneFeature("Drone1", 10, 5, Color.BLACK, msfDrone, kizil_berkouksf), new EntityDroneInit("Drone1", msiDrone));
//			
//			//Drone 2
//			mstDrone= new MovableState(new Point3D(0,0,0),new Point3D(Math.sqrt(2)*4, Math.sqrt(2)*4,0), Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO);
//			msiDrone= new EntityMouvementSequenceurInit("MSI", mstDrone, 2000, 0, 0, 0, positionsCles42);
//			msfDrone = new EntityMouvementSequenceurFeature("MSF");
//			kizil_berkouksf.getDrones().put(new EntityDroneFeature("Drone2", 10, 5, Color.GREEN, msfDrone, kizil_berkouksf), new EntityDroneInit("Drone2", msiDrone));
//		
//			// Drone 3
//			mstDrone= new MovableState(new Point3D(0,0,0),new Point3D(Math.sqrt(2)*4, Math.sqrt(2)*4,0), Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO);
//			msiDrone= new EntityMouvementSequenceurInit("MSI", mstDrone, 2000, 0, 0, 0, positionsCles43);
//			msfDrone = new EntityMouvementSequenceurFeature("MSF");
//			kizil_berkouksf.getDrones().put(new EntityDroneFeature("Drone2", 10, 5, Color.BROWN, msfDrone, kizil_berkouksf), new EntityDroneInit("Drone3", msiDrone));
//			
//			//Drone 4
//			mstDrone= new MovableState(new Point3D(0,0,0),new Point3D(Math.sqrt(2)*4, Math.sqrt(2)*4,0), Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO);
//			msiDrone= new EntityMouvementSequenceurInit("MSI", mstDrone, 2000, 0, 0, 0, positionsCles44);
//			msfDrone = new EntityMouvementSequenceurFeature("MSF");
//			kizil_berkouksf.getDrones().put(new EntityDroneFeature("Drone2", 10, 5, Color.RED, msfDrone, kizil_berkouksf), new EntityDroneInit("Drone4", msiDrone));
//		break;
//		case 5 : 
//			// Drone 1
//			mstDrone= new MovableState(new Point3D(-6000,-6000,0),new Point3D(Math.sqrt(2)*4, Math.sqrt(2)*4,0), Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO);
//			msiDrone= new EntityMouvementSequenceurInit("MSI", mstDrone, 2000, 0, 0, 0, positionsCles51);
//			msfDrone = new EntityMouvementSequenceurFeature("MSF");
//			kizil_berkouksf.getDrones().put(new EntityDroneFeature("Drone1", 10, 5, Color.BLACK, msfDrone, kizil_berkouksf), new EntityDroneInit("Drone1", msiDrone));
//			
//			//Drone 2
//			mstDrone= new MovableState(new Point3D(0,0,0),new Point3D(Math.sqrt(2)*4, Math.sqrt(2)*4,0), Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO);
//			msiDrone= new EntityMouvementSequenceurInit("MSI", mstDrone, 2000, 0, 0, 0, positionsCles52);
//			msfDrone = new EntityMouvementSequenceurFeature("MSF");
//			kizil_berkouksf.getDrones().put(new EntityDroneFeature("Drone2", 10, 5, Color.GREEN, msfDrone, kizil_berkouksf), new EntityDroneInit("Drone2", msiDrone));
//		
//			// Drone 3
//			mstDrone= new MovableState(new Point3D(0,0,0),new Point3D(Math.sqrt(2)*4, Math.sqrt(2)*4,0), Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO);
//			msiDrone= new EntityMouvementSequenceurInit("MSI", mstDrone, 2000, 0, 0, 0, positionsCles53);
//			msfDrone = new EntityMouvementSequenceurFeature("MSF");
//			kizil_berkouksf.getDrones().put(new EntityDroneFeature("Drone2", 10, 5, Color.BROWN, msfDrone, kizil_berkouksf), new EntityDroneInit("Drone3", msiDrone));
//			
//			//Drone 4
//			mstDrone= new MovableState(new Point3D(0,0,0),new Point3D(Math.sqrt(2)*4, Math.sqrt(2)*4,0), Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO);
//			msiDrone= new EntityMouvementSequenceurInit("MSI", mstDrone, 2000, 0, 0, 0, positionsCles54);
//			msfDrone = new EntityMouvementSequenceurFeature("MSF");
//			kizil_berkouksf.getDrones().put(new EntityDroneFeature("Drone2", 10, 5, Color.RED, msfDrone, kizil_berkouksf), new EntityDroneInit("Drone4", msiDrone));
//			
//			//Drone 5
//			mstDrone= new MovableState(new Point3D(0,0,0),new Point3D(Math.sqrt(2)*4, Math.sqrt(2)*4,0), Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO);
//			msiDrone= new EntityMouvementSequenceurInit("MSI", mstDrone, 2000, 0, 0, 0, positionsCles55);
//			msfDrone = new EntityMouvementSequenceurFeature("MSF");
//			kizil_berkouksf.getDrones().put(new EntityDroneFeature("Drone2", 10, 5, Color.PURPLE, msfDrone, kizil_berkouksf), new EntityDroneInit("Drone5", msiDrone));
//			break;
//		
//		
	}
		
	
		
			
		
		
		
		
		

		//Création artefacts
		int i;
		//mRandom.setSeed(seed); to set a seed
		int N = (int)mRandom.nextUniform(30, 40);
		System.out.println(N);
		positionsCles = new HashMap<String, Point3D>();
		
		int N1 = (int) Math.round(N*0.6);
		int N2 = (int) Math.round(N*0.3);
		int N3 = N - N1 - N2;

		for(i=1; i<=N1; i++) //Artefact de type 1
		{
			MovableState mstArtefact;
			EntityMouvementSequenceurInit msiArtefact;
						
			mstArtefact = new MovableState(point3dArtefact(), Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO);
			msiArtefact= new EntityMouvementSequenceurInit("MSI", mstArtefact, 0, 0,0,0, positionsCles);
			kizil_berkouksf.getArtefacts().put(new ArtefactFeatures("ArtefactT1", 3.0), new ArtefactInit("Type1A"+i,msiArtefact, 1));
		}
		
		
		for(i=1; i<=N2; i++) //Artefact de type 2
		{
			MovableState mstArtefact;
			EntityMouvementSequenceurInit msiArtefact;
			
			mstArtefact = new MovableState(point3dArtefact(), Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO);
			msiArtefact= new EntityMouvementSequenceurInit("MSI", mstArtefact, 0, 0,0,0, positionsCles);
			kizil_berkouksf.getArtefacts().put(new ArtefactFeatures("ArtefactT2", 3.0, 5.0), new ArtefactInit("Type2A"+i,msiArtefact, 2));
		}
		
		for(i=1; i<=N3; i++) //Artefact de type 3
		{
			MovableState mstArtefact;
			EntityMouvementSequenceurInit msiArtefact;
						
			mstArtefact = new MovableState(point3dArtefact(), Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO);
			msiArtefact= new EntityMouvementSequenceurInit("MSI", mstArtefact, 0, 0,0,0, positionsCles);
			kizil_berkouksf.getArtefacts().put(new ArtefactFeatures("ArtefactT3",5.0, 5.0, 3.0), new ArtefactInit("Type3A"+i,msiArtefact, 3));

		}
		
		//Création de la cible
		MovableState mstArtefact;
		EntityMouvementSequenceurInit msiArtefact;
		int taille = (int) Math.round( mRandom.nextUniform(2, 5) );
		mstArtefact = new MovableState(point3dArtefact(), Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO);
		msiArtefact= new EntityMouvementSequenceurInit("MSI", mstArtefact, 0, 0,0,0, positionsCles);
		kizil_berkouksf.getArtefacts().put(new ArtefactFeatures("Cible",taille, taille, taille), new ArtefactInit("Cible",msiArtefact, 0));

		

		/*//Navire1
		mst= new MovableState(new Point3D(0,0,0), new Point3D(1,1,0), Point3D.ZERO, new Point3D(0,0,45.0), new Point3D(10,5,0.0), Point3D.ZERO);
		msi= new EntityMouvementSequenceurInit("MSI", mst, 10, 100,2,8, positionsCles);
		feat = new EntityMouvementSequenceurFeature("MSF");
		bsf.getNavires().put(new EntityNavireFeature("NavireF",5,3,Color.BLACK,feat), new EntityNavireInit("Navire Observation 1", msi));

		//Création de bouees
		
		
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
	
	private Point3D Point3D(double d, double e, int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public Point3D point3dArtefact() {
		double x = mRandom.nextUniform(-10000, 10000);
		double y = mRandom.nextUniform(-10000, 10000);
		double z = mRandom.nextUniform(-3000, 0);
		return new Point3D(x, y, z);
	}
	
	public Point3D point3dArtefactCible() {
		double x = mRandom.nextUniform(-10000, 10000);// set the value here to be sure that it is under 1 km deep
		double y = mRandom.nextUniform(-10000, 10000);
		double z = mRandom.nextUniform(-3000, -2000);
		return new Point3D(x, y, z);
	}
	


}
