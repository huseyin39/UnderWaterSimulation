package kizil_berkouk.BE.SimEntity.Artefact.Representation3D;


import java.awt.Shape;

import enstabretagne.monitor.Contrat3D;
import enstabretagne.monitor.ObjTo3DMappingSettings;
import enstabretagne.monitor.implementation.Representation3D;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;

@Contrat3D(contrat = IArtefactRepresentation3D.class)
public class Artefact3DRepresentation extends Representation3D {
	
	public Artefact3DRepresentation(ObjTo3DMappingSettings settings) {
		super(settings);
	}

	IArtefactRepresentation3D artefact3D;
	Group monArtefact;
	@Override
	public void init(Group world, Object obj) {
		artefact3D = (IArtefactRepresentation3D) obj;
		monArtefact = new Group();
		
		PhongMaterial material = new PhongMaterial(artefact3D.getColor());
		
		
		switch (artefact3D.getType()) {
		case 0:
			Box rectangle = new Box(artefact3D.getSize1(), artefact3D.getSize2(), artefact3D.getSize3());
			rectangle.setMaterial(material);
			monArtefact.getChildren().add(rectangle);
			break;
		case 1:
			Sphere sphere = new Sphere(artefact3D.getSize1());
			sphere.setMaterial(material);
			monArtefact.getChildren().add(sphere);
			break;
		case 2:
			Cylinder cylinder = new Cylinder(artefact3D.getSize1(), artefact3D.getSize2());
			cylinder.setMaterial(material);
			monArtefact.getChildren().add(cylinder);
			break;
		case 3:
			Box box = new Box(artefact3D.getSize1(), artefact3D.getSize2(), artefact3D.getSize3());
			box.setMaterial(material);
			monArtefact.getChildren().add(box);
			break;
		}
	   
	    world.getChildren().add(monArtefact);

	}
	
	@Override
	public void update() {
		Point3D p = artefact3D.getPosition();

		monArtefact.setTranslateX(p.getX());
		monArtefact.setTranslateY(p.getY());
		monArtefact.setTranslateZ(p.getZ());
		

	}


}
	