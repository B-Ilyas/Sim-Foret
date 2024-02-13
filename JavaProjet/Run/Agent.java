// GROUPE 8
// 21106878 SONG Michelle
// 21118889 BENYAHIA Ilyas

import java.util.ArrayList;

/**
 * Cette classe permet de gerer un agent.
 * @author Michelle Song et Ilyas Benyahia
 */
public abstract class Agent {
    /**
     * Nom de l'agent.
     */
    public final String nom;
    /**
     * Identifiant unique de l'agent.
     */
    public final int id;
    /**
     * Compteur statique.
     */
    private static int cpt = 0;
    /**
     * Abscisse x de la position de l'agent.
     */
    protected int x;
    /**
     * Abscisse y de la position de l'agent.
     */
    protected int y;
    /**
     * Liste d'agent déjà présent sur le terrain.
     */
    protected static ArrayList<Agent> la = new ArrayList<Agent>();
    
    /**
     * Constructeur standard a partir de 2 entiers.
     * @param nom Nom de l'agent.
     * @param x Abscisse.
     * @param y Ordonnee.
     */
    public Agent(String nom, int x, int y) {
        this.nom = nom;
    	this.x = x;
    	this.y = y;
        id = cpt;
        cpt++;
        la.add(this);
    }
    
    /**
     * Calcule la distance entre l'agent et les coordonnées x et y.
     * @param x Abscisse.
     * @param y Ordonnee.
     * @return La distance entre l'agent et les coordonnées x et y.
     */
    public double distance(int x, int y) {
        return Math.sqrt((this.x - x) * (this.x - x) + (this.y - y) * (this.y - y));
    }
    
    /**
     * Deplace l'agent.
     * @param xnew Abscisse.
     * @param ynew Ordonnee.
     * @return Deplace l'agent en position xnew et ynew si cela est possible, sinon on le deplace dans une autre position valide.
     * @throws HorsTerrainException si les coordonnees n'appartiennent pas a celles du terrain.
     */
    public boolean seDeplacer(int xnew, int ynew) throws HorsTerrainException {
        try { 
        	if (((xnew >= 0) && (xnew < Terrain.NBLIGNESMAX)) && ((ynew >= 0) && (ynew < Terrain.NBCOLONNESMAX))) {
            	for (Agent a : la) {
            	    if ((a.getX() == xnew) && (a.getY() == ynew)) {
            	        return false;
                    }
            	}
            	
            	x = xnew;
            	y = ynew;
        	} else { 
        		throw new HorsTerrainException();
            }
        	
    	} catch (HorsTerrainException e) {
    		int x = (int)(Math.random() * (Terrain.NBLIGNESMAX));
    		int y = (int)(Math.random() * (Terrain.NBCOLONNESMAX));
    		for (Agent ag : la) {
    			while ((ag.getX() == x) && (ag.getY() == y)) { // jusqu'à qu'on trouve des coordonnées valides (dans le terrain)
    				x = (int)(Math.random() * (Terrain.NBLIGNESMAX));
    				y = (int)(Math.random() * (Terrain.NBCOLONNESMAX));
                }
    		}
                	
    		seDeplacer(x, y);
    	}
    	
    	return true;
    }
    
    /**
     * Methode abstract qui clone un agent.
     */
    public abstract Agent clone(); 
    
    /**
     * Accesseur de X.
     * @return Accesseur de X.
     */
    public int getX() {
        return x;
    }
    
    /**
     * Accesseur de Y.
     * @return Accesseur de Y.
     */
    public int getY() {
        return y;
    }

    
    // test d'affichage
    
    /**
     * Affiche la liste de tous les agents crees.
     */
    public static void afficherListeAgent() {
        for (Agent a : la) {
            System.out.println(a.toString());
        }
    }
    
    /**
     * Informations sur l'agent.
     * @return Le nom, l'identifiant et la position sur le terrain.
     */
    public String toString() {
        return String.format("%s [id : %d] en position (%d, %d)", nom, id, x, y);
    }
    
}
