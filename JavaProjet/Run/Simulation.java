// GROUPE 8
// 21106878 SONG Michelle
// 21118889 BENYAHIA Ilyas


import java.util.*;


/**
 * Cette classe permet de simuler le terrain.
 * @author Michelle Song et Ilyas Benyahia
 */
public class Simulation {
    /**
     * Foret.
     */
	private Terrain foret;
    /**
     * Liste de tous les agents qui sont et ont ete presents dans la foret.
     */
	private ArrayList<Agent> la;
    /**
     * Liste de toutes les ressources qui sont et ont ete presentes dans la foret.
     */
	private ArrayList<Ressource> lr;
	
    /**
     * Constructeur prenant en parametre 2 entiers.
     * @param n Nombre d'agents.
     * @param m Nombre de ressources.
     * @throws HorsTerrainException si les coordonnees n'appartiennent pas a celles du terrain.
     */
	public Simulation(int n, int m) throws HorsTerrainException {
		foret = new Terrain(); // creation d'une foret
        lr = new ArrayList<Ressource>();
        la = new ArrayList<Agent>();
        
        int x = (int)(Math.random() * (Terrain.NBLIGNESMAX));;
        int y = (int)(Math.random() * (Terrain.NBCOLONNESMAX));;
        
        for (int i = 0; i < m; i++) { // place m ressources aléatoirement
            while (foret.caseEstVide(x, y) == false) {
                x = (int)(Math.random() * (Terrain.NBLIGNESMAX));
                y = (int)(Math.random() * (Terrain.NBCOLONNESMAX));
            }
            int quantite = (int)(Math.random() * 101);
            
            if (Math.random() < 0.3) {
                Kangourou k = new Kangourou(0, quantite);
                foret.setCase(x, y, k);
                lr.add(k);
            } else {
  				if (Math.random() < 0.6) {
                    Arbre a = new Arbre(quantite);
                    foret.setCase(x, y, a);
                    lr.add(a);
            	} else {
                    Buisson b = new Buisson(quantite);
                    foret.setCase(x, y, b);
                    lr.add(b);
            	}
            }
            
        }
        
        
		for (int j = 0; j < n; j++) {
            for (Agent ag : la) {
                while ((ag.getX() == x) && (ag.getY() == y)) { // genere n agents aléatoirement
                    x = (int)(Math.random() * (Terrain.NBLIGNESMAX));
                    y = (int)(Math.random() * (Terrain.NBCOLONNESMAX));
                }
            }
            
            Bucheron bu = new Bucheron(x, y);
            la.add(bu);
            
		}
	
    
    	// test de la methode clone
        
    	// System.out.println("Clonage de " + (la.get(0)).nom + " en cours ");
   		// System.out.println(la.get(0).toString());
    	la.remove(n - 1);
    	Bucheron bubu = (Bucheron)((Bucheron)la.get(0)).clone();
    	// System.out.println(bubu.toString());
    	x = (int)(Math.random() * (Terrain.NBLIGNESMAX + 40));
    	y = (int)(Math.random() * (Terrain.NBCOLONNESMAX + 40));
    	
    	bubu.seDeplacer(x, y);
    	// System.out.println(bubu.toString());
    	la.add(bubu);
    
    }

    /**
     * Fait vieillir tous les animaux d'une liste de ressources.
     */
    public void tousVieillir() {
        for (int i = 0; i < lr.size(); i++) {
            if (lr.get(i) instanceof Animal) {
                ((Animal)(lr.get(i))).vieillir();
            
            }
            
        }
    }
    
    /**
     * Permet de faire la mise a jour de la foret nbEtapes de fois : a chaque etape, on fait vieillir les animaux, puis les bucherons se deplacent et coupent les arbres s'ils sont presents a leur nouvelle position. On affiche le terrain a chaque nouvelle mise a jour.
     * @param nbEtapes Nombre de fois qu'on met a jour le terrain.
     * @exception HorsTerrainException si les coordonnees n'appartiennent pas a celles du terrain.
     *
     */
    public void majNFois(int nbEtapes) throws HorsTerrainException {
        System.out.println("Informations sur le terrain :\n" + foret);
        foret.affiche(4);
        
        int i = 0;
        while (i < nbEtapes) {
        	System.out.println(">>>>>>> NOUVELLE ETAPE " + (i + 1));
        	
            tousVieillir();
            
            for (int k = 0; k < lr.size(); k++) {
            	if (lr.get(k) instanceof Animal) {
            		if (((Animal)(lr.get(k))).getAge() > (((Animal)(lr.get(k))).getAgeDeces())) { // si l'animal meurt
            			if ((((Animal)(lr.get(k))).getX() != -1) && ((Animal)(lr.get(k))).getY() != -1) { // pour afficher que les animaux morts maintenant
            				System.out.println(lr.get(k).toString() + " a trépassé");
            				foret.videCase((lr.get(k)).getX(), (lr.get(k)).getY());
            				(lr.get(k)).initialisePosition(); // met les positions à (-1, -1)
                        }
            		}
            	}
            }
            
            for (Agent a : la) { // parcourt la liste d'agents
                int x = (int)(Math.random() * (Terrain.NBLIGNESMAX + 1));
                int y = (int)(Math.random() * (Terrain.NBCOLONNESMAX + 1));
                
                a.seDeplacer(x, y);
                
                for (int j = 0; j < lr.size(); j++) { // parcourt la liste de ressources
                    if ((a.getX() == (lr.get(j)).getX()) && (a.getY() == (lr.get(j)).getY()) && (lr.get(j) instanceof Tronconnable)) {
                    	if (Math.random() < 0.6) { // les bucherons coupent les arbres présents sur leur position à une probabilité de 0.6
                    		foret.videCase((lr.get(j)).getX(), (lr.get(j)).getY());
                        	(lr.get(j)).initialisePosition(); 
                        	(lr.get(j)).setQuantite(0);
                        	System.out.println(a.toString() + " a coupé " + (lr.get(j)).type);
                        	System.out.println((lr.get(j)).toString());
                        }
                    }
                }
            }
            
            i++;
            
            System.out.println("Informations sur le terrain :\n" + foret);
            foret.affiche(4);
            
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                
            }
            
        }
    }
    
}
