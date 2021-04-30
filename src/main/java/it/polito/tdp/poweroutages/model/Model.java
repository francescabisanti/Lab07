package it.polito.tdp.poweroutages.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	List <EventBlackOut> partenza;
	PowerOutageDAO podao;
	List<EventBlackOut> best;
	int maxClientiMigliore;
	public Model() {
		podao = new PowerOutageDAO();
		
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public List <EventBlackOut> calcolaSottoInsiemeEventi(String nerc, int maxAnni, float maxOre){
		List<EventBlackOut> parziale= new ArrayList<>();
		this.partenza=this.podao.getEventList(nerc);
		Collections.sort(partenza);
		best= new ArrayList<>();
		this.maxClientiMigliore=0;
		cerca(parziale, 0, maxAnni, maxOre );
		
		return best;
	}

	private void cerca(List<EventBlackOut> parziale, int L, int maxAnni, float maxOre) {
		float numeroOre= calcolaNumOre(parziale);
		if(numeroOre>maxOre) {
			return;
			
		}
		if(numeroOre<= maxOre) {
			int numeroClienti=calcolaNumeroClienti(parziale);
			if(numeroClienti >maxClientiMigliore) {
				maxClientiMigliore= numeroClienti;
				this.best= new ArrayList<>(parziale);
				return;
			}
		}
		
		
		if(L==partenza.size()) {
			return;
		}
		
		for(EventBlackOut e: partenza) {
			if(!parziale.contains(e)&&aggiuntaValida(partenza.get(L), parziale, maxAnni)) {
				parziale.add(e);
				if(aggiuntaValida(e, parziale, maxAnni)) {
					cerca(parziale, L+1, maxAnni, maxOre);
					}
				
				parziale.remove(e);
			}
		}
		/*parziale.add(partenza.get(L));
			if(aggiuntaValida(partenza.get(L), parziale, maxAnni)){
					cerca(parziale, L+1, maxAnni, maxOre);}
		parziale.remove(partenza.get(L));
		if(aggiuntaValida(partenza.get(L), parziale, maxAnni)){
			cerca(parziale, L+1, maxAnni, maxOre);}
			cerca(parziale, L+1, maxAnni, maxOre);*/
		
		
		
	}

	

	private boolean aggiuntaValida(EventBlackOut e, List<EventBlackOut> parziale, int maxAnni) {
		/*int min=10000;
		int max=0;
		for(EventBlackOut ee:parziale) {
			if(ee.getAnno()<min)
				min=ee.getAnno();
			if(ee.getAnno()>max)
				max=ee.getAnno();
		}
		if((max-min)<=maxAnni && e.getAnno()<=max &&e.getAnno()>=min)
			return true;
		return false;*/
		if(parziale.size()>=2) {
			int a1= parziale.get(0).getAnno();
			int a2=parziale.get(parziale.size()-1).getAnno();
			if((a2-a1+1)>maxAnni)
				return false;
		}
		return true;
	}

	public int calcolaNumeroClienti(List<EventBlackOut> parziale) {
		int numClienti=0;
		for(EventBlackOut e: parziale) {
			numClienti= numClienti+e.getCustomers_affected();
		}
		return numClienti;
	}

	public float calcolaNumOre(List<EventBlackOut> parziale) {
		float numeroOre=0;
		for(EventBlackOut e: parziale) {
			numeroOre =numeroOre+e.getOreDisservizio();
		}
		return numeroOre;
	}
	
}



