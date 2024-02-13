// GROUPE 8
// 21106878 SONG Michelle
// 21118889 BENYAHIA Ilyas

/**
 * Cette classe permet de tester la simulation.
 * @author Michelle Song et Ilyas Benyahia
 */
public class TestSimulation {
    /**
     * Test la simulation.
     * @param args -
     * @throws HorsTerrainException si les coordonnees n'appartiennent pas a celles du terrain.
     */
    public static void main(String[] args) throws HorsTerrainException {
        Simulation s = new Simulation(50, 200); // n agents, m ressources
        s.majNFois(50);
        
    }
}
