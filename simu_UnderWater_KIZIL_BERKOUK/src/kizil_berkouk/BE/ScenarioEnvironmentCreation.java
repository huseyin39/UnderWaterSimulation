package kizil_berkouk.BE;

import java.util.HashMap;

import enstabretagne.base.math.MoreRandom;
import enstabretagne.monitor.implementation.MovableState;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import kizil_berkouk.BE.Scenarios.ScenarioFeatures;
import kizil_berkouk.BE.SimEntity.Artefact.ArtefactFeatures;
import kizil_berkouk.BE.SimEntity.Artefact.ArtefactInit;
import kizil_berkouk.BE.SimEntity.Bateau.BateauFeatures;
import kizil_berkouk.BE.SimEntity.Bateau.BateauInit;
import kizil_berkouk.BE.SimEntity.MouvementSequenceur.EntityMouvementSequenceurInit;
import kizil_berkouk.BE.SimEntity.Ocean.EntityOceanFeature;
import kizil_berkouk.BE.SimEntity.Ocean.EntityOceanInit;

public class ScenarioEnvironmentCreation {
	public ScenarioFeatures kizil_berkouksf;
	public HashMap<String,Point3D> positionsCles = new HashMap<>();

	MoreRandom mRandom = new MoreRandom();
	
	public ScenarioEnvironmentCreation(ScenarioFeatures scenarioFeatures) {
		this.kizil_berkouksf = scenarioFeatures;
	}
	
	
	public void createEnvironment() {
		createArtefacts();
		createBateau();
		createCible();
		createOcean();
		}
	
	private void createBateau() {
		MovableState mstBateau;
		EntityMouvementSequenceurInit msiBateau;
		mstBateau= new MovableState(new Point3D(0,0,1), Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO);
		msiBateau= new EntityMouvementSequenceurInit("MSI", mstBateau, 0, 0,0,0, positionsCles);
		kizil_berkouksf.getBateau().put(new BateauFeatures("Bateau",0 , 0, 30, 20, 20), new BateauInit("Bateau", msiBateau, Color.RED));
	}
	
	private void createCible() {
		MovableState mstArtefact;
		EntityMouvementSequenceurInit msiArtefact;
		int taille = (int) Math.round( mRandom.nextUniform(2, 5) );
		mstArtefact = new MovableState(point3dArtefact(), Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO);
		msiArtefact= new EntityMouvementSequenceurInit("MSI", mstArtefact, 0, 0,0,0, positionsCles);
		kizil_berkouksf.getArtefacts().put(new ArtefactFeatures("Cible",taille, taille, taille), new ArtefactInit("Cible",msiArtefact, 0));
	
	}
	
	private void createOcean() {
		MovableState mstOcean = new MovableState(Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO); // création d'un objet movable
		EntityMouvementSequenceurInit msiOcean = new EntityMouvementSequenceurInit("MSIOCEAN", mstOcean, 0, 0, 0, 0, positionsCles); // initialisation
		kizil_berkouksf.getOcean().put(new EntityOceanFeature("O1"), new EntityOceanInit("Zone de recherche", msiOcean));
	}

	
	private void createArtefacts() {
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
	}
	
	
	public Point3D point3dArtefact() {
		double x = mRandom.nextUniform(-1000, 1000);
		double y = mRandom.nextUniform(-1000, 1000);
		double z = mRandom.nextUniform(-300, 0);
		return new Point3D(x, y, z);
	}
	
	public Point3D point3dArtefactCible() {
		double x = mRandom.nextUniform(-1000, 1000);// set the value here to be sure that it is under 1 km deep
		double y = mRandom.nextUniform(-1000, 1000);
		double z = mRandom.nextUniform(-300, -200);
		return new Point3D(x, y, z);
	}

}
