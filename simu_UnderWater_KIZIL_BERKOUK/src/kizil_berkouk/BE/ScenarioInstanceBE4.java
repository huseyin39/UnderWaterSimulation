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
import kizil_berkouk.BE.SimEntity.Drone.EntityDroneFeature;
import kizil_berkouk.BE.SimEntity.Drone.EntityDroneInit;
import kizil_berkouk.BE.SimEntity.MouvementSequenceur.EntityMouvementSequenceurFeature;
import kizil_berkouk.BE.SimEntity.MouvementSequenceur.EntityMouvementSequenceurInit;

public class ScenarioInstanceBE4 implements IScenarioInstance {
	@Override
	public IScenario getScenarioInstance() {
		ScenarioFeatures kizil_berkouksf = new ScenarioFeatures("kizil_berkoukSF");
		
		// Trajectory D1 -- 4D
		HashMap<String,Point3D> positionsCles41 = new HashMap<String, Point3D>();
		positionsCles41.put("start", new Point3D(0,0,0));
		positionsCles41.put("PointCible1", new Point3D(-600, 200, 0));
		positionsCles41.put("PointCible1b", new Point3D(-600,-600,0));
		
		// Trajectory D2 -- 4D
		HashMap<String,Point3D> positionsCles42 = new HashMap<String, Point3D>();
		positionsCles42.put("start", new Point3D(0, 0, 0));
		positionsCles42.put("PointCible2b", new Point3D(-600, 600, 0));
		positionsCles42.put("PointCible2", new Point3D(200, 600, 0));
		
		// Trajectory D3 -- 4D
		HashMap<String,Point3D> positionsCles43 = new HashMap<String, Point3D>();
		positionsCles43.put("start", new Point3D(0,0,0));
		positionsCles43.put("PointCible4b", new Point3D(200, -600, 0));
		positionsCles43.put("PointCible4", new Point3D(1000, -600, 0));
		
		// Trajectory D4 -- 4D
		HashMap<String,Point3D> positionsCles44 = new HashMap<String, Point3D>();
		positionsCles44.put("start", new Point3D(0, 0, 0));
		positionsCles44.put("PointCible5", new Point3D(1000, -200, 0));
		positionsCles44.put("PointCible5b", new Point3D(1000, 600, 0));

		
		// Création drone
		MovableState mstDrone;
		EntityMouvementSequenceurInit msiDrone;
		EntityMouvementSequenceurFeature msfDrone;
		
		// Drone 1
		mstDrone= new MovableState(new Point3D(0,0,0),new Point3D(Math.sqrt(2)*4, Math.sqrt(2)*4,0), Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO);
		msiDrone= new EntityMouvementSequenceurInit("MSI", mstDrone, 200, 0, 0, 0, positionsCles41);
		msfDrone = new EntityMouvementSequenceurFeature("MSF");
		kizil_berkouksf.getDrones().put(new EntityDroneFeature("Drone1", 10, 5, Color.BLACK, msfDrone, kizil_berkouksf), new EntityDroneInit("Drone1", msiDrone));
		
		//Drone 2
		mstDrone= new MovableState(new Point3D(0,0,0),new Point3D(Math.sqrt(2)*4, Math.sqrt(2)*4,0), Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO);
		msiDrone= new EntityMouvementSequenceurInit("MSI", mstDrone, 200, 0, 0, 0, positionsCles42);
		msfDrone = new EntityMouvementSequenceurFeature("MSF");
		kizil_berkouksf.getDrones().put(new EntityDroneFeature("Drone2", 10, 5, Color.GREEN, msfDrone, kizil_berkouksf), new EntityDroneInit("Drone2", msiDrone));
	
		// Drone 3
		mstDrone= new MovableState(new Point3D(0,0,0),new Point3D(Math.sqrt(2)*4, Math.sqrt(2)*4,0), Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO);
		msiDrone= new EntityMouvementSequenceurInit("MSI", mstDrone, 200, 0, 0, 0, positionsCles43);
		msfDrone = new EntityMouvementSequenceurFeature("MSF");
		kizil_berkouksf.getDrones().put(new EntityDroneFeature("Drone2", 10, 5, Color.BROWN, msfDrone, kizil_berkouksf), new EntityDroneInit("Drone3", msiDrone));
		
		//Drone 4
		mstDrone= new MovableState(new Point3D(0,0,0),new Point3D(Math.sqrt(2)*4, Math.sqrt(2)*4,0), Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO);
		msiDrone= new EntityMouvementSequenceurInit("MSI", mstDrone, 200, 0, 0, 0, positionsCles44);
		msfDrone = new EntityMouvementSequenceurFeature("MSF");
		kizil_berkouksf.getDrones().put(new EntityDroneFeature("Drone2", 10, 5, Color.RED, msfDrone, kizil_berkouksf), new EntityDroneInit("Drone4", msiDrone));
		
		
		ScenarioEnvironmentCreation scenarioEnvironmentCreation = new ScenarioEnvironmentCreation(kizil_berkouksf);
		scenarioEnvironmentCreation.createEnvironment();
		
		LogicalDateTime start = new LogicalDateTime("20/01/2018 06:00");
		LogicalDateTime end = start.add(LogicalDuration.ofMinutes(2));
		Scenario bms = new Scenario(new ScenarioId("Scenario4"), kizil_berkouksf, start, end);
		return bms;
		
	}
}
