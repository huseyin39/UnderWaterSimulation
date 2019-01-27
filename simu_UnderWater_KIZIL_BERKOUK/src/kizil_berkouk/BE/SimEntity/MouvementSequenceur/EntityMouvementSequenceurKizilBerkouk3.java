package kizil_berkouk.BE.SimEntity.MouvementSequenceur;

import enstabretagne.base.logger.Logger;
import enstabretagne.base.logger.ToRecord;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import enstabretagne.simulation.components.IEntity;
import enstabretagne.simulation.components.data.SimFeatures;
import enstabretagne.simulation.components.data.SimInitParameters;
import enstabretagne.simulation.core.implementation.SimEvent;

@ToRecord(name="MouvementSequenceur")
public class EntityMouvementSequenceurKizilBerkouk3 extends EntityMouvementSequenceur implements IMover{


	
	public EntityMouvementSequenceurKizilBerkouk3(String name, SimFeatures features) {
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
		
		if (ini.getPositionsCles().get("PointCible4") != null)
			Post(new FinStaticPhase3(), LogicalDuration.ofSeconds(1));
		
		else if (ini.getPositionsCles().get("PointCible2b") != null) 
			Post(new FinStaticPhase2(), LogicalDuration.ofMinutes(10));
		
		else
			Post(new FinStaticPhase1(),LogicalDuration.ofMinutes(20));
		
	}
	
	public class FinStaticPhase1 extends SimEvent {

		@Override
		public void Process() {
			Logger.Information(Owner(), "Process FinStaticPhase1", "Fin de la première phase statique");
			LogicalDateTime d = getCurrentLogicalDate();
			rectilinearMover = new RectilinearMover(d, mv.getPosition(d), ini.getPositionsCles().get("PointCible1"), ini.getMaxLinearSpeed());
			mv= rectilinearMover;
			Logger.Information(Owner(), "Process FinStaticPhase1", "Phase mouvement linéaire enclenché");
			Post(new FinStaticPhase1b(),mv.getDurationToReach());
		}
	}
	public class FinStaticPhase1b extends SimEvent {

		@Override
		public void Process() {
			Logger.Information(Owner(), "Process FinStaticPhase1", "Fin de la première phase statique");
			LogicalDateTime d = getCurrentLogicalDate();
			rectilinearMover = new RectilinearMover(d, mv.getPosition(d), ini.getPositionsCles().get("PointCible1b"), ini.getMaxLinearSpeed());
			mv= rectilinearMover;
			Logger.Information(Owner(), "Process FinStaticPhase1", "Phase mouvement linéaire enclenché");
			Post(new Arret(),mv.getDurationToReach());
		}
	}
	
	public class FinStaticPhase2 extends SimEvent {

		@Override
		public void Process() {
			Logger.Information(Owner(), "Process FinStaticPhase1", "Fin de la première phase statique");
			LogicalDateTime d = getCurrentLogicalDate();
			rectilinearMover = new RectilinearMover(d, mv.getPosition(d), ini.getPositionsCles().get("PointCible2b"), ini.getMaxLinearSpeed());
			mv= rectilinearMover;
			Logger.Information(Owner(), "Process FinStaticPhase1", "Phase mouvement linéaire enclenché");
			Post(new FinLinearPhase1(),mv.getDurationToReach());
		}
	}
	public class FinLinearPhase1 extends SimEvent {

		@Override
		public void Process() {
			Logger.Information(Owner(), "Process FinLinearPhase2", "Fin de la deuxième phase");
			LogicalDateTime d = getCurrentLogicalDate();
			circulrMover = new CircularMover(d, mv.getPosition(d), mv.getVitesse(d).normalize().multiply(ini.getMaxLinearSpeed()), ini.getPositionsCles().get("PointCible2"));
			mv= circulrMover;
			Logger.Information(Owner(), "Process FinStaticPhase1", "Phase mouvement circulaire enclenché");
			Post(new FinCircularPhase4(),mv.getDurationToReach());
		}
	}
	public class FinCircularPhase4 extends SimEvent {

		@Override
		public void Process() {
			Logger.Information(Owner(), "Process FinStaticPhase1", "Fin de la première phase statique");
			LogicalDateTime d = getCurrentLogicalDate();
			rectilinearMover = new RectilinearMover(d, mv.getPosition(d), ini.getPositionsCles().get("PointCible3"), ini.getMaxLinearSpeed());
			mv= rectilinearMover;
			Logger.Information(Owner(), "Process FinStaticPhase1", "Phase mouvement linéaire enclenché");
			Post(new Arret(),mv.getDurationToReach());
		}
	}
	/*Drone 3*/
	public class FinStaticPhase3 extends SimEvent {

		@Override
		public void Process() {
			Logger.Information(Owner(), "Process FinStaticPhase1", "Fin de la première phase statique");
			LogicalDateTime d = getCurrentLogicalDate();
			rectilinearMover = new RectilinearMover(d, mv.getPosition(d), ini.getPositionsCles().get("PointCible4"), ini.getMaxLinearSpeed());
			mv= rectilinearMover;
			Logger.Information(Owner(), "Process FinStaticPhase1", "Phase mouvement linéaire enclenché");
			Post(new FinLinearPhase5(),mv.getDurationToReach());
		}
	}
	public class FinLinearPhase5 extends SimEvent {

		@Override
		public void Process() {
			Logger.Information(Owner(), "Process FinLinearPhase2", "Fin de la deuxième phase");
			LogicalDateTime d = getCurrentLogicalDate();
			circulrMover = new CircularMover(d, mv.getPosition(d), mv.getVitesse(d).normalize().multiply(ini.getMaxLinearSpeed()), ini.getPositionsCles().get("PointCible5"));
			mv= circulrMover;
			Logger.Information(Owner(), "Process FinStaticPhase1", "Phase mouvement circulaire enclenché");
			Post(new FinCircularPhase6(),mv.getDurationToReach());
		}
	}
	public class FinCircularPhase6 extends SimEvent {

		@Override
		public void Process() {
			Logger.Information(Owner(), "Process FinStaticPhase1", "Fin de la première phase statique");
			LogicalDateTime d = getCurrentLogicalDate();
			rectilinearMover = new RectilinearMover(d, mv.getPosition(d), ini.getPositionsCles().get("PointCible6"), ini.getMaxLinearSpeed());
			mv= rectilinearMover;
			Logger.Information(Owner(), "Process FinStaticPhase1", "Phase mouvement linéaire enclenché");
			Post(new Arret(),mv.getDurationToReach());
		}
	}
	
	
	
//	public class FinSelfRotatePhase4 extends SimEvent {
//
//		@Override
//		public void Process() {
//			Logger.Information(Owner(), "Process FinStaticPhase1", "Fin de la première phase statique");
//			LogicalDateTime d = getCurrentLogicalDate();
//			rectilinearMover = new RectilinearMover(d, mv.getPosition(d), ini.getPositionsCles().get("PointCible3"), ini.getMaxLinearSpeed());
//			mv= rectilinearMover;
//			Logger.Information(Owner(), "Process FinStaticPhase1", "Phase mouvement linéaire enclenché");
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
			Logger.Information(Owner(), "Process Arret", "Mode arrêt : %s", mv.getPosition(d));
			mv = staticMover;
		}
		
	}

}
