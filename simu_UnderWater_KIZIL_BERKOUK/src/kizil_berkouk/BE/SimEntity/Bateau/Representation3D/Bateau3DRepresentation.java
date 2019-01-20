package kizil_berkouk.BE.SimEntity.Bateau.Representation3D;


import enstabretagne.monitor.Contrat3D;
import enstabretagne.monitor.ObjTo3DMappingSettings;
import enstabretagne.monitor.implementation.Representation3D;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

@Contrat3D(contrat = IBateauRepresentation3D.class)
public class Bateau3DRepresentation extends Representation3D {
	
	public Bateau3DRepresentation(ObjTo3DMappingSettings settings) {
		super(settings);
	}

	IBateauRepresentation3D bateau3D;
	Group monBateau;
	@Override
	public void init(Group world, Object obj) {
		bateau3D = (IBateauRepresentation3D) obj;
		monBateau = new Group();
	    
	    PhongMaterial material = new PhongMaterial(bateau3D.getColor());

	    Box s = new Box(bateau3D.getLongueur(), bateau3D.getLargeur(), bateau3D.getHauteur());
	    s.setMaterial(material);
	    monBateau.getChildren().add(s);
	    world.getChildren().add(monBateau);

	}
	
	@Override
	public void update() {
		Point3D p = bateau3D.getPosition();

		monBateau.setTranslateX(p.getX());
		monBateau.setTranslateY(p.getY());
		monBateau.setTranslateZ(p.getZ());
		

	}


}
	