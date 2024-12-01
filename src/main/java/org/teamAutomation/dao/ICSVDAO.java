package org.teamAutomation.dao;

import org.teamAutomation.entity.CasDeTest;

import java.util.List;

public interface ICSVDAO {
    List<CasDeTest> lireCSV(String chemin);
    void ecrireCSV(String chemin, List<CasDeTest> casDeTests, double tempsTotal);
    void convertirCSVEnExcel(String cheminCsv,String cheminExcel);
}
