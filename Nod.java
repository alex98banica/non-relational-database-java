package tema2;

import java.util.LinkedList;

public class Nod
{
	
	  LinkedList<Instanta> Inst=new LinkedList<Instanta>();
	  int maxCap;
	  int curCap;
	  public void AddInst(Instanta e) 
	  {
		  Inst.add(e);  
	  }
	  public void setMaxCap(int maxCap) {
		  this.maxCap=maxCap;
	  }
	  public void setCurCap(int curCap) {
		  this.curCap=curCap;
	  }
	  public int retMaxCap() {
		  return maxCap;
	  }
	  public int retCurCap() {
		  return curCap;
	  }
}
