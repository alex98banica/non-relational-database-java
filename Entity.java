package tema2;

import java.util.LinkedList;

public class Entity {
	  private String nume;
	  private int factorReplicare;
	  private int nrAtribute;
	  public LinkedList<EntityNode> attributes=new LinkedList<EntityNode>();
	  public void createAtribute(String Atribut,String tipAtribut) {
			EntityNode e=new EntityNode(Atribut,tipAtribut);
			attributes.add(e);
	  }
	  public void setNume(String nume) {
		  this.nume=nume;
	  }
	  public void setFactorReplicare(int factorReplicare) {
		  this.factorReplicare=factorReplicare;
	  }
	  public void setNrAtribute(int nrAtribute) {
		  this.nrAtribute=nrAtribute;
	  }
	  public String retNume() {
		  return nume;
	  }
	  public int retFactorReplicare() {
		  return factorReplicare;
	  }
	  public int retNrAtribute() {
		  return nrAtribute;
	  }
	 
}
