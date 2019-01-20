package kizil_berkouk.BE.SimEntity.Artefact.Representation3D;


import enstabretagne.monitor.Contrat3D;
import enstabretagne.monitor.ObjTo3DMappingSettings;
import enstabretagne.monitor.implementation.Representation3D;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

@Contrat3D(contrat = IArtefactRepresentation3D.class)
public class Artefact3DRepresentation extends Representation3D {
	
	public Artefact3DRepresentation(ObjTo3DMappingSettings settings) {
		super(settings);
	}

	IArtefactRepresentation3D bouee3D;
	Group maBouee;
	@Override
	public void init(Group world, Object obj) {
		bouee3D = (IArtefactRepresentation3D) obj;
		maBouee = new Group();
	    
	    PhongMaterial material = new PhongMaterial(bouee3D.getColor());


	    Sphere s = new Sphere(bouee3D.getSize());
	    s.setMaterial(material);
	    maBouee.getChildren().add(s);
	    world.getChildren().add(maBouee);

	}
	
	@Override
	public void update() {
		Point3D p = bouee3D.getPosition();

		maBouee.setTranslateX(p.getX());
		maBouee.setTranslateY(p.getY());
		maBouee.setTranslateZ(p.getZ());
		

	}


}
	