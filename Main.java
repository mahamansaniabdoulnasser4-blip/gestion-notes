import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner clavier = new Scanner(System.in);

        final int COEF_JAVA = 3;
        final int COEF_ALGO = 3;
        final int COEF_BDD = 2;
        final int COEF_ANGLAIS = 1;

        System.out.print("Nom complet : ");
        String nom = clavier.nextLine();
        System.out.print("Matricule : ");
        String matricule = clavier.nextLine();

        System.out.print("Note de Java : ");
        double noteJava = clavier.nextDouble();
        System.out.print("Note d'Algorithmique : ");
        double noteAlgo = clavier.nextDouble();
        System.out.print("Note de Base de données : ");
        double noteBdd = clavier.nextDouble();
        System.out.print("Note d'Anglais : ");
        double noteAnglais = clavier.nextDouble();

        double moyenneSimple = (noteJava + noteAlgo + noteBdd + noteAnglais) / 4;
        double totalPoints = noteJava * COEF_JAVA + noteAlgo * COEF_ALGO + noteBdd * COEF_BDD + noteAnglais * COEF_ANGLAIS;
        int totalCoefs = COEF_JAVA + COEF_ALGO + COEF_BDD + COEF_ANGLAIS;
        double moyennePonderee = totalPoints / totalCoefs;

        System.out.println();
        System.out.println("===== RELEVÉ DE NOTES =====");
        System.out.println("Nom : " + nom);
        System.out.println("Matricule : " + matricule);
        System.out.println("---------------------------");
        System.out.printf("Java : %.2f (coef %d)%n", noteJava, COEF_JAVA);
        System.out.printf("Algorithmique : %.2f (coef %d)%n", noteAlgo, COEF_ALGO);
        System.out.printf("Base de données : %.2f (coef %d)%n", noteBdd, COEF_BDD);
        System.out.printf("Anglais : %.2f (coef %d)%n", noteAnglais, COEF_ANGLAIS);
        System.out.println("---------------------------");
        System.out.printf("Moyenne simple : %.2f%n", moyenneSimple);
        System.out.printf("Moyenne pondérée : %.2f%n", moyennePonderee);

        clavier.close();
    }
}
