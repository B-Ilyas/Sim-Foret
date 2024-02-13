// GROUPE 8
// 21106878 SONG Michelle
// 21118889 BENYAHIA Ilyas

/**
 * Cette classe permet de gerer un bucheron.
 * @author Michelle Song et Ilyas Benyahia
 */
public class Bucheron extends Agent {
    
    /**
     * Constructeur standard à partir de 2 entiers.
     * @param x Abscisse.
     * @param y Ordonnee.
     */
	public Bucheron(int x, int y) {
		super("Bucheron", x, y);
	}
	
    /**
     * Clone un bucheron.
     * @return Clone un bucheron.
     */
	public Agent clone() {
		return new Bucheron(getX(), getY());
	} 
    
}
