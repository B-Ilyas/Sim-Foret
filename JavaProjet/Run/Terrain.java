import java.util.ArrayList;

public final class Terrain {
   public static final int NBLIGNESMAX = 20;
   public static final int NBCOLONNESMAX = 20;
   public final int nbLignes;
   public final int nbColonnes;
   private Ressource[][] terrain;

   public Terrain() {
      this(20, 20);
   }

   public Terrain(int var1, int var2) {
      if (var1 > 20) {
         this.nbLignes = 20;
      } else if (var1 <= 0) {
         this.nbLignes = 1;
      } else {
         this.nbLignes = var1;
      }

      if (var2 > 20) {
         this.nbColonnes = 20;
      } else if (var2 <= 0) {
         this.nbColonnes = 1;
      } else {
         this.nbColonnes = var2;
      }

      this.terrain = new Ressource[this.nbLignes][this.nbColonnes];
   }

   public Ressource getCase(int var1, int var2) {
      return this.sontValides(var1, var2) ? this.terrain[var1][var2] : null;
   }

   public Ressource videCase(int var1, int var2) {
      if (this.sontValides(var1, var2) && this.terrain[var1][var2] != null) {
         Ressource var3 = this.terrain[var1][var2];
         var3.initialisePosition();
         this.terrain[var1][var2] = null;
         return var3;
      } else {
         return null;
      }
   }

   public boolean setCase(int var1, int var2, Ressource var3) {
      if (this.sontValides(var1, var2)) {
         if (this.terrain[var1][var2] != null) {
            this.terrain[var1][var2].initialisePosition();
         }

         this.terrain[var1][var2] = var3;
         var3.setPosition(var1, var2);
         return true;
      } else {
         return false;
      }
   }

   public boolean caseEstVide(int var1, int var2) {
      if (this.sontValides(var1, var2)) {
         return this.terrain[var1][var2] == null;
      } else {
         return true;
      }
   }

   public ArrayList<Ressource> lesRessources() {
      ArrayList var1 = new ArrayList();

      for(int var2 = 0; var2 < this.nbLignes; ++var2) {
         for(int var3 = 0; var3 < this.nbColonnes; ++var3) {
            if (this.terrain[var2][var3] != null) {
               var1.add(this.terrain[var2][var3]);
            }
         }
      }

      return var1;
   }

   public boolean sontValides(int var1, int var2) {
      return var1 >= 0 && var1 < this.nbLignes && var2 >= 0 && var2 < this.nbColonnes;
   }

   public void affiche(int var1) {
      String var2 = "";
      String var3 = ":";
      String var4 = "";
      int var5 = Math.max(var1, 1);

      int var6;
      for(var6 = 0; var6 < var5; ++var6) {
         var4 = var4 + "-";
      }

      for(var6 = 0; var6 < this.nbColonnes; ++var6) {
         var3 = var3 + var4 + ":";
      }

      var3 = var3 + "\n";
      var2 = var3;

      for(var6 = 0; var6 < this.nbLignes; ++var6) {
         for(int var7 = 0; var7 < this.nbColonnes; ++var7) {
            if (this.terrain[var6][var7] == null) {
               var2 = var2 + "|" + String.format("%-" + var5 + "s", " ");
            } else {
               var2 = var2 + "|" + this.premiersCar(this.terrain[var6][var7].type, var5);
            }
         }

         var2 = var2 + "|\n" + var3;
      }

      System.out.println(var2);
   }

   public String toString() {
      int var1 = 0;

      for(int var2 = 0; var2 < this.nbLignes; ++var2) {
         for(int var3 = 0; var3 < this.nbColonnes; ++var3) {
            if (this.terrain[var2][var3] != null) {
               ++var1;
            }
         }
      }

      String var4 = "Terrain de " + this.nbLignes + "x" + this.nbColonnes + " cases: ";
      if (var1 == 0) {
         var4 = var4 + "toutes les cases sont libres.";
      } else if (var1 == 1) {
         var4 = var4 + "il y a une case occupée.";
      } else {
         var4 = var4 + "il y a " + var1 + " cases occupées.";
      }

      return var4;
   }

   private String premiersCar(String var1, int var2) {
      String var3 = String.format("%-" + var2 + "s", var1);
      return var3.substring(0, var2);
   }
}
