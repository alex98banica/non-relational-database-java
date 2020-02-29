package tema2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.LinkedList;


public class Tema2 {
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		
		String comanda;
		String numeBD=null;
		String prod=null;
		int nrNoduri=0;
		int maxNoduri=0;
		int RF=0,nrAtribute=0;
		int i,j=0,k=0,t = 0,t2=0,u;
		String Atribut=null,tipAtribut=null;
		
		Entity m=new Entity();
		
		String fisier=args[0];
		
		File f=new File(fisier);
		

		PrintWriter writer = new PrintWriter(fisier+"_out","UTF-8");
		Scanner scan = new Scanner(f);

		
		DataBase DB=new DataBase();
		
		while(scan.hasNext()) {
		
			comanda=scan.next();
			if(comanda.equals("CREATEDB")) 
			{

				numeBD=scan.next();
				DB.setNume(numeBD);
			
				if(scan.hasNextInt()) 
				{
					maxNoduri=scan.nextInt();
				}
				DB.setNrNoduri(nrNoduri);
			
				if(scan.hasNextInt()) 
				{
					nrNoduri=scan.nextInt();
				}
				DB.setMaxNoduri(maxNoduri);
				DB.createArrNod(maxNoduri,nrNoduri);
			}
		
		
			if(comanda.equals("CREATE")) 
			{
			

			  	prod=scan.next();
			  	String nume;
			  	Entity e=new Entity();
			  	if(scan.hasNextInt())
			  	{
				  	RF=scan.nextInt();
			  	}
			  	if(scan.hasNextInt())
			  	{
				  	nrAtribute=scan.nextInt();
			  	}
			  
			  	e.setNume(prod);
			  	e.setFactorReplicare(RF);
			  	e.setNrAtribute(nrAtribute);
			  
			  	for(i=0;i<e.retNrAtribute();i++)
			  	{
				  	Atribut=scan.next();
				  	tipAtribut=scan.next();
				  	e.createAtribute(Atribut, tipAtribut);

			  	}
			  	DB.Ent.add(e);
			
			
			}
		
			if(comanda.equals("INSERT")) {

			  	String numeEntitate;
			  	numeEntitate=scan.next();
			  	Instanta e=new Instanta();
			  	e.setNume(numeEntitate);
			  	String val;
			  
			  	for(i=0;i<DB.Ent.size();i++) {
				  	if(DB.Ent.get(i).retNume().equals(numeEntitate))
				  	{
					  	RF=DB.Ent.get(i).retFactorReplicare();
					  	for(j=0;j<DB.Ent.get(i).attributes.size();j++)
					  	{
					  		val=scan.next(); 
					  		e.addAtribute(val,DB.Ent.get(i).attributes.get(j).retTipAtribut(),DB.Ent.get(i).attributes.get(j).retAtribut());
					  	}
				  	}

			  	}
			  	i=0;
			  	j=0;
			  	while(i<RF)
			  	{
				  	if(DB.ArrNod.get(j).retCurCap()<DB.ArrNod.get(j).retMaxCap())
				  	{
					  	DB.ArrNod.get(j).AddInst(e);
					  	u=DB.ArrNod.get(j).retCurCap();
					  	DB.ArrNod.get(j).setCurCap(u+1);
					  	j++;
				  	}
				  	else {
					  	while(DB.ArrNod.get(j).retCurCap()>=DB.ArrNod.get(j).retMaxCap()) {
						  	j++;
					  	}
					  	DB.ArrNod.get(j).AddInst(e);
					  	u=DB.ArrNod.get(j).retCurCap();
					  	DB.ArrNod.get(j).setCurCap(u+1);
					  	j++;
				  	}
				  	i++;

			  	}
			}
		
			t2=0;
			if(comanda.equals("SNAPSHOTDB"))
			{
				int z=0;
				int check=0;
				for(z=0;z<DB.ArrNod.size();z++)
					if(DB.ArrNod.get(z).retCurCap()>0)
						check=1;
			
				if(check==1)
					for(i=0;i<DB.ArrNod.size();i++)
					{
			  
						if(DB.ArrNod.get(i).retCurCap()>0) 
						{
							writer.println("Nod"+(i+1));
							for(j=DB.ArrNod.get(i).Inst.size()-1;j>=0;j--) 
							{
								writer.print(DB.ArrNod.get(i).Inst.get(j).getNume()+" ");
				  
								for(t=0;t<DB.Ent.size();t++) 
								{
									if(DB.Ent.get(t).retNume().equals(DB.ArrNod.get(i).Inst.get(j).getNume()))
									{
										t2=t;
									}
								}
				  
								for(k=0;k<DB.ArrNod.get(i).Inst.get(j).Atr.size();k++) 
								{
									writer.print(DB.Ent.get(t2).attributes.get(k).retAtribut()+":"+  DB.ArrNod.get(i).Inst.get(j).Atr.get(k).retAtribut());
									if(k!=DB.ArrNod.get(i).Inst.get(j).Atr.size()-1)
										writer.print(" ");
								}
								writer.println();
							}	
						}
					}
		  
				else 
					writer.println("EMPTY DB");
			}

			if(comanda.equals("GET"))
			{
				String numeEnt,id;
				numeEnt=scan.next();
				id=scan.next();
				int adv=0,adv2=0;
				for(i=0;i<DB.ArrNod.size();i++) 
				{
					adv=0;
					for(k=0;k<DB.ArrNod.get(i).Inst.size();k++)
						if(numeEnt.equals(DB.ArrNod.get(i).Inst.get(k).getNume()))
						{

							for(u=0;u<DB.ArrNod.get(i).Inst.get(k).Atr.size();u++)
								if(id.equals(DB.ArrNod.get(i).Inst.get(k).Atr.get(u).retAtribut())) {
									adv=1;
									j=i;
									t=k;
								}
						}
					if(adv==1) {
						writer.print("Nod"+(i+1)+" ");
						adv2=1;
					}
				}
				if(adv2==1) {
				for(i=0;i<DB.Ent.size();i++)
					if(DB.Ent.get(i).retNume().equals(numeEnt)) 
					{
						writer.print(DB.Ent.get(i).retNume()+" ");
						for(k=0;k<DB.Ent.get(i).attributes.size();k++) {
		
							writer.print(DB.Ent.get(i).attributes.get(k).retAtribut()+":"+DB.ArrNod.get(j).Inst.get(t).Atr.get(k).retAtribut());
						if(k!=DB.Ent.get(i).attributes.size()-1)
							writer.print(" ");}
					
					}
				writer.println();
				}
				else
					writer.println("NO INSTANCE FOUND");
			
			
			}
		
			if(comanda.equals("DELETE"))
			{
				String numeEnt,id;
				numeEnt=scan.next();
				id=scan.next();
				int adv=0,adv2=0;	
				for(i=0;i<DB.ArrNod.size();i++) 
				{
					adv=0;
					for(k=0;k<DB.ArrNod.get(i).Inst.size();k++)
						if(numeEnt.equals(DB.ArrNod.get(i).Inst.get(k).getNume()))
						{

							for(u=0;u<DB.ArrNod.get(i).Inst.get(k).Atr.size();u++)
								if(id.equals(DB.ArrNod.get(i).Inst.get(k).Atr.get(u).retAtribut())) {
									adv=1;
									j=i;
									t=k;
								}
						}
					if(adv==1) {
						DB.ArrNod.get(i).Inst.remove(t);
						u=DB.ArrNod.get(i).retCurCap()-1;
						DB.ArrNod.get(i).setCurCap(u);
						adv2=1;
					}
				}
				if(adv2==0)
					writer.println("NO INSTANCE TO DELETE");
			
			}
		
		}
			scan.close();		
			writer.close();

	}

}
