// GROUPE 8
// 21106878 SONG Michelle
// 21118889 BENYAHIA Ilyas

/**
 * Cette classe permet de gerer des animaux.
 * @author Michelle Song et Ilyas Benyahia
 */
public abstract class Animal extends Ressource {
    /**
     * Age des animaux.
     */
	private int age;
    /**
     * Age pour lequel les animaux mourront.
     */
	private final int ageDeces;
	
    /**
     * Constructeur standard a partir d'un nom et de 2 entiers.
     * @param nom Nom des animaux.
     * @param age Age des animaux.
     * @param quantite Quantite d'animaux.
     */
	public Animal(String nom, int age, int quantite) {
		super(nom, quantite);
        this.age = age;
        ageDeces = (int)(Math.random()*50);
	}
    
    /**
     * Fait vieillir les animaux.
     */
    public void vieillir() {
        if ((getX() != -1) && (getY() != -1)) {
        	age++;
        }
    }
    
    /**
     * Accesseur de X.
     * @return Accesseur de X.
     */
    public int getAge() {
    	return age;
    }
    
    /**
     * Accesseur de Y.
     * @return Accesseur de Y.
     */
    public int getAgeDeces() {
    	return ageDeces;
    }
    
    /**
     * Informations sur les animaux.
     * @return Le nom, l'identifiant, la quantite et la position sur le terrain.
     */
    public String toString() {
        return String.format("%s [id : %d quantite : %d age : %d] en position (%d, %d)", super.type, super.ident, getQuantite(), age, getX(), getY());
    }

}
