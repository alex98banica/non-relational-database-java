package tema2;

public class EntityNode {
	private String Atribut;
	private String tipAtribut;
	
	public EntityNode(String Atribut,String tipAtribut) {
		this.Atribut=Atribut;
		this.tipAtribut=tipAtribut;
	}
	  public String retAtribut() {
		  return Atribut;
	  }
	  public String retTipAtribut() {
		  return tipAtribut;
	  }
}
