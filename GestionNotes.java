import java.util.Scanner;

public class GestionNotes {

    static final int MAX = 50;
    static final String[] MATIERES = {"Java", "Algorithmique", "Base de données", "Anglais"};
    static final int[] COEFS = {3, 3, 2, 1};

    static double lireNote(Scanner clavier, String matiere) {
        double note;
        do {
            System.out.print("Note de " + matiere + " : ");
            note = clavier.nextDouble();
        } while (note < 0 || note > 20);
        return note;
    }

    static double calculerMoyenne(double[] notes) {
        double somme = 0;
        int total = 0;
        for (int i = 0; i < notes.length; i++) {
            somme += notes[i] * COEFS[i];
            total += COEFS[i];
        }
        return somme / total;
    }

    static String mention(double moyenne) {
        if (moyenne < 10) return "Ajourné";
        if (moyenne < 12) return "Passable";
        if (moyenne < 14) return "Assez bien";
        if (moyenne < 16) return "Bien";
        return "Très bien";
    }

    static void statistiques(String[] noms, double[] moyennes, int nb) {
        double somme = 0;
        int admis = 0;
        int max = 0;
        int min = 0;
        for (int i = 0; i < nb; i++) {
            somme += moyennes[i];
            if (moyennes[i] >= 10) admis++;
            if (moyennes[i] > moyennes[max]) max = i;
            if (moyennes[i] < moyennes[min]) min = i;
        }
        System.out.printf("Moyenne de la classe : %.2f%n", somme / nb);
        System.out.printf("Meilleur : %s (%.2f)%n", noms[max], moyennes[max]);
        System.out.printf("Dernier : %s (%.2f)%n", noms[min], moyennes[min]);
        System.out.println("Admis : " + admis + " sur " + nb);
    }

    static void classement(String[] noms, double[] moyennes, int nb) {
        boolean[] dejaAffiche = new boolean[nb];
        for (int rang = 1; rang <= nb; rang++) {
            int meilleur = -1;
            for (int i = 0; i < nb; i++) {
                if (!dejaAffiche[i] && (meilleur == -1 || moyennes[i] > moyennes[meilleur])) {
                    meilleur = i;
                }
            }
            dejaAffiche[meilleur] = true;
            System.out.printf("%d. %s : %.2f%n", rang, noms[meilleur], moyennes[meilleur]);
        }
    }

    public static void main(String[] args) {
        Scanner clavier = new Scanner(System.in);
        String[] noms = new String[MAX];
        String[] matricules = new String[MAX];
        double[][] notes = new double[MAX][MATIERES.length];
        double[] moyennes = new double[MAX];
        int nb = 0;
        int choix;

        do {
            System.out.println("\n1. Ajouter  2. Classe  3. Relevé  4. Statistiques  5. Classement  0. Quitter");
            System.out.print("Choix : ");
            choix = clavier.nextInt();
            clavier.nextLine();

            if ((choix >= 2 && choix <= 5) && nb == 0) {
                System.out.println("Aucun étudiant.");
                continue;
            }

            switch (choix) {
                case 1:
                    if (nb == MAX) {
                        System.out.println("Classe pleine.");
                        break;
                    }
                    System.out.print("Nom : ");
                    noms[nb] = clavier.nextLine();
                    System.out.print("Matricule : ");
                    matricules[nb] = clavier.nextLine();
                    for (int m = 0; m < MATIERES.length; m++) {
                        notes[nb][m] = lireNote(clavier, MATIERES[m]);
                    }
                    moyennes[nb] = calculerMoyenne(notes[nb]);
                    nb++;
                    break;
                case 2:
                    for (int i = 0; i < nb; i++) {
                        System.out.printf("%s | %s | %.2f | %s%n", matricules[i], noms[i], moyennes[i], mention(moyennes[i]));
                    }
                    break;
                case 3:
                    System.out.print("Matricule : ");
                    String recherche = clavier.nextLine();
                    boolean trouve = false;
                    for (int i = 0; i < nb; i++) {
                        if (matricules[i].equals(recherche)) {
                            trouve = true;
                            System.out.println(noms[i]);
                            for (int m = 0; m < MATIERES.length; m++) {
                                System.out.printf("%s : %.2f%n", MATIERES[m], notes[i][m]);
                            }
                            System.out.printf("Moyenne : %.2f (%s)%n", moyennes[i], mention(moyennes[i]));
                        }
                    }
                    if (!trouve) System.out.println("Introuvable.");
                    break;
                case 4:
                    statistiques(noms, moyennes, nb);
                    break;
                case 5:
                    classement(noms, moyennes, nb);
                    break;
                case 0:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        } while (choix != 0);

        clavier.close();
    }
}