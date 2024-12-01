package org.teamAutomation.presentation;

import org.teamAutomation.dao.CSVDAO;
import org.teamAutomation.dao.ICSVDAO;
import org.teamAutomation.entity.ChiffrageResultat;
import org.teamAutomation.service.CSVService;
import org.teamAutomation.service.ICSVService;

import java.util.Scanner;

public class App {
    // second commit
    public static void main(String[] args) {
        ICSVDAO csvDAO = new CSVDAO();
        ICSVService service = new CSVService(csvDAO);
        Scanner scanner=new Scanner(System.in);

        System.out.println("Veuillez entrer le chemin du fichier CSV d'entrée : ");
        String cheminCSV = scanner.nextLine();


        ChiffrageResultat resultat = service.calculerEtRetournerResultat(cheminCSV);

        System.out.print("Veuillez entrer le chemin pour enregistrer le fichier CSV de sortie : ");
        String cheminCSVSortie = scanner.nextLine();

        csvDAO.ecrireCSV(cheminCSVSortie, resultat.getCasDeTests(), resultat.getTempsTotal());

        System.out.print("Veuillez entrer le chemin pour enregistrer le fichier Excel de sortie : ");
        String cheminExcelSortie = scanner.nextLine();

        csvDAO.convertirCSVEnExcel(cheminCSVSortie, cheminExcelSortie);
        System.out.println("Les fichiers CSV et Excel ont été générés avec succès.");
        scanner.close();
    }
}
