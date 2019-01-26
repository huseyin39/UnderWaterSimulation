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
import kizil_berkouk.BE.ScenarioInstanceBE1;



@ToRecord(name="MouvementSequenceur")
public class EntityMouvementSequenceurKizilBerkouk2 extends EntityMouvementSequenceur implements IMover{
	
	
	public EntityMouvementSequenceurKizilBerkouk2(String name, SimFeatures features) {
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
		Post(new FinStaticPhase1_2DD1(), LogicalDuration.ofSeconds(1));
<<<<<<< HEAD
		Post(new FinStaticPhase1_2DD2(), LogicalDuration.ofMinutes(10));
=======
		Post(new FinStaticPhase1_2DD2(), LogicalDuration.ofSeconds(1));
>>>>>>> refs/remotes/origin/master
	}
	
	/*Drone 1*/
		public class FinStaticPhase1_2DD1 extends SimEvent {

			@Override
			public void Process() {
				Logger.Information(Owner(), "Process FinStaticPhase1", "Fin de la première phase statique");
				LogicalDateTime d = getCurrentLogicalDate();
				rectilinearMover = new RectilinearMover(d, mv.getPosition(d), ini.getPositionsCles().get("PointCible1"), ini.getMaxLinearSpeed());
				mv= rectilinearMover;
				Logger.Information(Owner(), "Process FinStaticPhase1", "Phase mouvement linéaire enclenché");
				Post(new FinLinearPhase2_2DD1() ,mv.getDurationToReach());
			}
		}


			public class FinLinearPhase2_2DD1 extends SimEvent {

				@Override
				public void Process() {
					Logger.Information(Owner(), "Process FinLinearPhase2", "Fin de la deuxième phase");
					LogicalDateTime d = getCurrentLogicalDate();
					circulrMover = new CircularMover(d, mv.getPosition(d), mv.getVitesse(d).normalize().multiply(ini.getMaxLinearSpeed()), ini.getPositionsCles().get("PointCible2"));
					mv= circulrMover;
					Logger.Information(Owner(), "Process FinStaticPhase1", "Phase mouvement circulaire enclenché");
					Post(new FinCircularPhase3_2DD2(),mv.getDurationToReach());
					
				}
			}
		
			public class FinCircularPhase3_2DD2 extends SimEvent {

				@Override
				public void Process() {
					Logger.Information(Owner(), "Process FinCircularPhase3", "Fin de la troisème phase");
					LogicalDateTime d = getCurrentLogicalDate();
					rectilinearMover = new RectilinearMover(d, mv.getPosition(d), ini.getPositionsCles().get("PointCible3"), ini.getMaxLinearSpeed());
					mv= rectilinearMover;
					Logger.Information(Owner(), "Process FinStaticPhase1", "Phase mouvement linéaire enclenché");
					Post(new Arret(),mv.getDurationToReach());
					
				}
			}
			
			/*Drone 2*/
			public class FinStaticPhase1_2DD2 extends SimEvent {

				@Override
				public void Process() {
					Logger.Information(Owner(), "Process FinStaticPhase1", "Fin de la première phase statique");
					LogicalDateTime d = getCurrentLogicalDate();
					rectilinearMover = new RectilinearMover(d, mv.getPosition(d), ini.getPositionsCles().get("PointCible4"), ini.getMaxLinearSpeed());
					mv= rectilinearMover;
					Logger.Information(Owner(), "Process FinStaticPhase1", "Phase mouvement linéaire enclenché");
					Post(new FinLinearPhase2_2DD2(),mv.getDurationToReach());
				}
			}
			public class FinLinearPhase2_2DD2 extends SimEvent {

				@Override
				public void Process() {
					Logger.Information(Owner(), "Process FinLinearPhase4", "Fin de la quatrième phase");
					LogicalDateTime d = getCurrentLogicalDate();
					circulrMover = new CircularMover(d, mv.getPosition(d), mv.getVitesse(d).normalize().multiply(ini.getMaxLinearSpeed()), ini.getPositionsCles().get("PointCible5"));
					mv= circulrMover;
					Logger.Information(Owner(), "Process FinStaticPhase1", "Phase mouvement circulaire enclenché");
					Post(new FinCircularPhase_2DD2(),mv.getDurationToReach());
				}
			}
			public class FinCircularPhase_2DD2 extends SimEvent {

				@Override
				public void Process() {
					Logger.Information(Owner(), "Process FinCircularPhase5", "Fin de la cinquième phase");
					LogicalDateTime d = getCurrentLogicalDate();
					rectilinearMover = new RectilinearMover(d, mv.getPosition(d), ini.getPositionsCles().get("PointCible6"), ini.getMaxLinearSpeed());
					mv= rectilinearMover;
					Logger.Information(Owner(), "Process FinStaticPhase1", "Phase mouvement linéaire enclenché");
					Post(new Arret(),mv.getDurationToReach());
					
				}
			}
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
