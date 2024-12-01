package org.teamAutomation.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.teamAutomation.dao.ICSVDAO;
import org.teamAutomation.entity.CasDeTest;
import org.teamAutomation.entity.ChiffrageResultat;

import java.util.List;

public class CSVService implements ICSVService{
    private static final Logger logger = LogManager.getLogger(CSVService.class);
    private final ICSVDAO csvdao;

    public CSVService(ICSVDAO csvdao) {
        this.csvdao = csvdao;
    }


    @Override
    public ChiffrageResultat calculerEtRetournerResultat(String cheminCSV) {
        List<CasDeTest> casDeTests = csvdao.lireCSV(cheminCSV);

        for (CasDeTest cas : casDeTests) {
            switch (cas.getComplexite().toLowerCase()) {
                case "faible" -> cas.setTempsParCas(2);
                case "moyenne" -> cas.setTempsParCas(4);
                case "élevée" -> cas.setTempsParCas(8);
                default -> cas.setTempsParCas(0);
            }
        }

        double tempsTotal = casDeTests.stream().mapToDouble(CasDeTest::getTempsParCas).sum();
        logger.info("Calcul terminé : {} heures.", tempsTotal);
        return new ChiffrageResultat(casDeTests, tempsTotal);
    }
}
