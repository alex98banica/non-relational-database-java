package tema2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


public class DataBase {
	
	  private String nume;
	  private int nrNoduri;
	  private int maxNoduri;
	  LinkedList<Nod> ArrNod=new LinkedList<Nod>();
	  LinkedList<Entity> Ent=new LinkedList<Entity>();
	  
	  public void setNume(String nume)  {
		  this.nume=nume;
	  }

	  public void setNrNoduri(int nrNoduri)  {
		  this.nrNoduri=nrNoduri;
	  }
	  
	  public void setMaxNoduri(int maxNoduri)  {
		  this.maxNoduri=maxNoduri;
	  }
	  
	  public void createArrNod(int maxNoduri,int nrNoduri) {
		  int i;
		  for(i=0;i<maxNoduri;i++)
		  {
			  Nod e=new Nod();
			  e.setCurCap(0);
			  e.setMaxCap(nrNoduri);
			  ArrNod.add(e);
		  }
	  }
	  
}
