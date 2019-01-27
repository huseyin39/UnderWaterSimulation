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

public class ScenarioInstanceBE3 implements IScenarioInstance{
	@Override
	public IScenario getScenarioInstance() {
		ScenarioFeatures kizil_berkouksf = new ScenarioFeatures("kizil_berkoukSF");
		
		// Trajectory D1 -- 3
		HashMap<String,Point3D> positionsCles31 = new HashMap<String, Point3D>();
		positionsCles31.put("start", new Point3D(0,0,0));
		positionsCles31.put("PointCible1", new Point3D(-600, 200, 0));
		positionsCles31.put("PointCible1b", new Point3D(-600,-600,0));
		
		// Trajectory D2 -- 3
		HashMap<String,Point3D> positionsCles32 = new HashMap<String, Point3D>();
		positionsCles32.put("start", new Point3D(0, 0, 0));
		positionsCles32.put("PointCible2b", new Point3D(-600, 600, 0));
		positionsCles32.put("PointCible2", new Point3D(200, 600, 0));
		positionsCles32.put("PointCible3", new Point3D(200, -200, 0));
		// Trajectory D3 -- 3
		HashMap<String,Point3D> positionsCles33 = new HashMap<String, Point3D>();
		positionsCles33.put("start", new Point3D(0, 0, 0));
		positionsCles33.put("PointCible4", new Point3D(600,-600,0));
		positionsCles33.put("PointCible5", new Point3D(1000,-600,0));
		positionsCles33.put("PointCible6", new Point3D(1000,600,0));
		
		// Creation drones
		MovableState mstDrone;
		EntityMouvementSequenceurInit msiDrone;
		EntityMouvementSequenceurFeature msfDrone;
		
		// Drone 1
		mstDrone= new MovableState(new Point3D(0,0,0),new Point3D(Math.sqrt(2)*4, Math.sqrt(2)*4,0), Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO);
		msiDrone= new EntityMouvementSequenceurInit("MSI3Drones", mstDrone, 200, 0, 0, 0, positionsCles31);
		msfDrone = new EntityMouvementSequenceurFeature("MSF");
		kizil_berkouksf.getDrones().put(new EntityDroneFeature("Drone1", 10, 5, Color.BLACK, msfDrone, kizil_berkouksf), new EntityDroneInit("Drone1", msiDrone));
		
		//Drone 2
		mstDrone= new MovableState(new Point3D(0,0,0),new Point3D(Math.sqrt(2)*4, Math.sqrt(2)*4,0), Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO);
		msiDrone= new EntityMouvementSequenceurInit("MSI3Drones", mstDrone, 200, 0, 0, 0, positionsCles32);
		msfDrone = new EntityMouvementSequenceurFeature("MSF");
		kizil_berkouksf.getDrones().put(new EntityDroneFeature("Drone2", 10, 5, Color.GREEN, msfDrone, kizil_berkouksf), new EntityDroneInit("Drone2", msiDrone));
	
		// Drone 3
		mstDrone= new MovableState(new Point3D(0,0,0),new Point3D(Math.sqrt(2)*4, Math.sqrt(2)*4,0), Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO);
		msiDrone= new EntityMouvementSequenceurInit("MSI3Drones", mstDrone, 200, 0, 0, 0, positionsCles33);
		msfDrone = new EntityMouvementSequenceurFeature("MSF");
		kizil_berkouksf.getDrones().put(new EntityDroneFeature("Drone2", 10, 5, Color.BROWN, msfDrone, kizil_berkouksf), new EntityDroneInit("Drone3", msiDrone));
		
		
		
		
		ScenarioEnvironmentCreation scenarioEnvironmentCreation = new ScenarioEnvironmentCreation(kizil_berkouksf);
		scenarioEnvironmentCreation.createEnvironment();
		
		LogicalDateTime start = new LogicalDateTime("20/01/2018 06:00");
		LogicalDateTime end = start.add(LogicalDuration.ofMinutes(2));
		Scenario bms = new Scenario(new ScenarioId("Scenario3"), kizil_berkouksf, start, end);
		
		return bms;
	}

}
