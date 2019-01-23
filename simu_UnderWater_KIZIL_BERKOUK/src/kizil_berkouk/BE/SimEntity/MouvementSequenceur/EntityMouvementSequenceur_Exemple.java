package kizil_berkouk.BE.SimEntity.MouvementSequenceur;

import enstabretagne.base.logger.Logger;
import enstabretagne.base.logger.ToRecord;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import enstabretagne.monitor.implementation.XYZRotator2;
import enstabretagne.simulation.components.IEntity;
import enstabretagne.simulation.components.data.SimFeatures;
import enstabretagne.simulation.components.data.SimInitParameters;
import enstabretagne.simulation.core.implementation.SimEvent;
import javafx.geometry.Point3D;
import javafx.scene.transform.Rotate;
import kizil_berkouk.BE.ScenarioInstanceBE;

@ToRecord(name="MouvementSequenceur")
public class EntityMouvementSequenceur_Exemple extends EntityMouvementSequenceur implements IMover{
	private static int NbDrone = ScenarioInstanceBE.nbDrone;

	
	public EntityMouvementSequenceur_Exemple(String name, SimFeatures features) {
		super(name, features);
		
		
		
	}

	@Override
	public void onParentSet() {
		
	}

	@Override
	protected void initializeSimEntity(SimInitParameters init) {
		super.initializeSimEntity(init);
	}


	@Override
	protected void AfterActivate(IEntity sender, boolean starting) {
		Logger.Detail(this, "AfterActivate", "Activation de MouvementSequenceur");
		//attente
		switch(NbDrone) {
		case 1:
			Post(new FinStaticPhase1(),LogicalDuration.ofSeconds(1));
		case 2:
			Post(new FinStaticPhase1_2DD1(), LogicalDuration.ofSeconds(1));
		}
		
	}
	
		
	
	public class FinStaticPhase1 extends SimEvent {

		@Override
		public void Process() {
			Logger.Information(Owner(), "Process FinStaticPhase1", "Fin de la premi�re phase statique");
			LogicalDateTime d = getCurrentLogicalDate();
			rectilinearMover = new RectilinearMover(d, mv.getPosition(d), ini.getPositionsCles().get("PointCible1"), ini.getMaxLinearSpeed());
			mv= rectilinearMover;
			Logger.Information(Owner(), "Process FinStaticPhase1", "Phase mouvement lin�aire enclench�");
			Post(new BugCorrection(),mv.getDurationToReach());
		}
	}
	public class FinStaticPhase1_2DD1 extends SimEvent {

		@Override
		public void Process() {
			Logger.Information(Owner(), "Process FinStaticPhase1", "Fin de la premi�re phase statique");
			LogicalDateTime d = getCurrentLogicalDate();
			rectilinearMover = new RectilinearMover(d, mv.getPosition(d), ini.getPositionsCles().get("PointCible1"), ini.getMaxLinearSpeed());
			mv= rectilinearMover;
			Logger.Information(Owner(), "Process FinStaticPhase1", "Phase mouvement lin�aire enclench�");
			Post(new BugCorrection(),mv.getDurationToReach());
		}
	}

	public class BugCorrection extends SimEvent {

		@Override
		public void Process() {
			Logger.Information(Owner(), "Process FinCircularPhase3", "Fin de la trois�me phase");
			LogicalDateTime d = getCurrentLogicalDate();
			Point3D dir = new Point3D(1,1,0);
			selfRotator = new SelfRotator(d, mv.getPosition(d), mv.getVitesse(d), mv.getPosition(d).add(dir),ini.getMaxSelfRotationSpeed());
			mv= selfRotator;
			Logger.Information(Owner(), "Process FinStaticPhase1", "Phase mouvement circulaire enclench�");
			Post(new FinLinearPhase2(),mv.getDurationToReach());
			
		}
	}

	public class FinLinearPhase2 extends SimEvent {

		@Override
		public void Process() {
			Logger.Information(Owner(), "Process FinLinearPhase2", "Fin de la deuxi�me phase");
			LogicalDateTime d = getCurrentLogicalDate();
			circulrMover = new CircularMover(d, mv.getPosition(d), mv.getVitesse(d).normalize().multiply(ini.getMaxLinearSpeed()), ini.getPositionsCles().get("PointCible2"));
			mv= circulrMover;
			Logger.Information(Owner(), "Process FinStaticPhase1", "Phase mouvement circulaire enclench�");
			Post(new FinCircularPhase3(),mv.getDurationToReach());
		}
	}
	public class FinCircularPhase3 extends SimEvent {

		@Override
		public void Process() {
			Logger.Information(Owner(), "Process FinCircularPhase3", "Fin de la trois�me phase");
			LogicalDateTime d = getCurrentLogicalDate();
			rectilinearMover = new RectilinearMover(d, mv.getPosition(d), ini.getPositionsCles().get("PointCible3"), ini.getMaxLinearSpeed());
			mv= rectilinearMover;
			Logger.Information(Owner(), "Process FinStaticPhase1", "Phase mouvement lin�aire enclench�");
			Post(new FinLinearPhase4(),mv.getDurationToReach());
			
		}
	}
	
	
//	public class BugCorrection2 extends SimEvent {
//
//		@Override
//		public void Process() {
//			Logger.Information(Owner(), "Process FinCircularPhase3", "Fin de la trois�me phase");
//			LogicalDateTime d = getCurrentLogicalDate();
//			Point3D dir = new Point3D(1,1,0);
//			selfRotator = new SelfRotator(d, mv.getPosition(d), mv.getVitesse(d), mv.getPosition(d).add(dir),ini.getMaxSelfRotationSpeed());
//			mv= selfRotator;
//			Logger.Information(Owner(), "Process FinStaticPhase1", "Phase mouvement circulaire enclench�");
//			Post(new FinLinearPhase4(),mv.getDurationToReach());
//			
//		}
//	}

	public class FinLinearPhase4 extends SimEvent {

		@Override
		public void Process() {
			Logger.Information(Owner(), "Process FinLinearPhase4", "Fin de la quatri�me phase");
			LogicalDateTime d = getCurrentLogicalDate();
			circulrMover = new CircularMover(d, mv.getPosition(d), mv.getVitesse(d).normalize().multiply(ini.getMaxLinearSpeed()), ini.getPositionsCles().get("PointCible4"));
			mv= circulrMover;
			Logger.Information(Owner(), "Process FinStaticPhase1", "Phase mouvement circulaire enclench�");
			Post(new FinCircularPhase5(),mv.getDurationToReach());
		}
	}
	
	public class FinCircularPhase5 extends SimEvent {

		@Override
		public void Process() {
			Logger.Information(Owner(), "Process FinCircularPhase5", "Fin de la cinqui�me phase");
			LogicalDateTime d = getCurrentLogicalDate();
			rectilinearMover = new RectilinearMover(d, mv.getPosition(d), ini.getPositionsCles().get("PointCible5"), ini.getMaxLinearSpeed());
			mv= rectilinearMover;
			Logger.Information(Owner(), "Process FinStaticPhase1", "Phase mouvement lin�aire enclench�");
			Post(new Arret(),mv.getDurationToReach());
			
		}
	}
//	public class FinSelfRotatePhase4 extends SimEvent {
//
//		@Override
//		public void Process() {
//			Logger.Information(Owner(), "Process FinStaticPhase1", "Fin de la premi�re phase statique");
//			LogicalDateTime d = getCurrentLogicalDate();
//			rectilinearMover = new RectilinearMover(d, mv.getPosition(d), ini.getPositionsCles().get("PointCible3"), ini.getMaxLinearSpeed());
//			mv= rectilinearMover;
//			Logger.Information(Owner(), "Process FinStaticPhase1", "Phase mouvement lin�aire enclench�");
//			Post(new FinLinearPhase5(),mv.getDurationToReach());
//		}
//	}
	
//	public class FinLinearPhase5 extends SimEvent {
//
//		@Override
//		public void Process() {
//			Logger.Information(Owner(), "Process FinLinearPhase5", "FinLinearPhase5");
//			LogicalDateTime d = getCurrentLogicalDate();
//			selfRotator = new SelfRotator(d, mv.getPosition(d), mv.getVitesse(d), ini.getPositionsCles().get("PointDirection"),ini.getMaxSelfRotationSpeed());
//			mv= selfRotator;
//			Logger.Information(Owner(), "Process FinLinearPhase5", "FinLinearPhase5");
//			Post(new FinRotatorPhase6(),mv.getDurationToReach());
//			
//		}
//	}
//	
//	public class FinRotatorPhase6 extends SimEvent {
//
//		@Override
//		public void Process() {
//			Logger.Information(Owner(), "Process FinRotatorPhase6", "FinRotatorPhase6");
//			LogicalDateTime d = getCurrentLogicalDate();
//			circulrMover = new CircularMover(d, mv.getPosition(d), mv.getVitesse(d).normalize().multiply(ini.getMaxLinearSpeed()), ini.getPositionsCles().get("start"));
//			mv= circulrMover;
//			Logger.Information(Owner(), "Process FinRotatorPhase6", "FinRotatorPhase6");
//			Post(new FinCircularPhase7(),mv.getDurationToReach());
//		}
//	}
//
//	public class FinCircularPhase7 extends SimEvent {
//
//		@Override
//		public void Process() {
//			Logger.Information(Owner(), "Process FinCircularPhase7", "FinCircularPhase7");
//			LogicalDateTime d = getCurrentLogicalDate();
//			rectilinearMover = new RectilinearMover(d, mv.getPosition(d), mv.getVitesse(d), ini.getPositionsCles().get("PointSousEau"), ini.getMaxPlongeeSpeed());
//			mv= rectilinearMover;
//			Logger.Information(Owner(), "Process FinCircularPhase7", "FinCircularPhase7");
//			Post(new FinPlongee(),mv.getDurationToReach());
//		}
//	}
//
//	public class FinPlongee extends SimEvent {
//
//		@Override
//		public void Process() {
//			Logger.Information(Owner(), "Process FinPlongee", "FinPlongee");
//			LogicalDateTime d = getCurrentLogicalDate();
//			
//			Point3D dir = XYZRotator2.getTransformByAngle(mv.getRotationXYZ(d)).transform(Rotate.X_AXIS);; 
//			selfRotator = new SelfRotator(d, mv.getPosition(d), dir, ini.getPositionsCles().get("ObservationMine"),ini.getMaxSelfRotationSpeed());
//			mv= selfRotator;
//			Post(new AtteinteCible(),mv.getDurationToReach());
//			
//		}
//	}
//
//	public class AtteinteCible extends SimEvent {
//
//		@Override
//		public void Process() {
//			Logger.Information(Owner(), "Process AtteinteCible", "AtteinteCible");
//			LogicalDateTime d = getCurrentLogicalDate();
//			rectilinearMover = new RectilinearMover(d, mv.getPosition(d), new Point3D(20,20,-20), 10);
//			mv= rectilinearMover;
//			Logger.Information(Owner(), "Process AtteinteCible", "AtteinteCible");
//			Post(new Arret(),mv.getDurationToReach());
//		}
//	}

	public class Arret extends SimEvent {

		@Override
		public void Process() {
			Logger.Information(Owner(), "Process Arret", "Fin de la phase");
			LogicalDateTime d = getCurrentLogicalDate();
			staticMover =new StaticMover(mv.getPosition(d), mv.getVitesse(d));			
			Logger.Information(Owner(), "Process Arret", "Mode arr�t : %s", mv.getPosition(d));
			mv = staticMover;
		}
		
	}

}
