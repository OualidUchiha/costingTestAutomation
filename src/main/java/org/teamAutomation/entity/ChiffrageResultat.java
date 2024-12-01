package org.teamAutomation.entity;

import java.util.List;

public class ChiffrageResultat {
    private final List<CasDeTest> casDeTests;
    private final double tempsTotal;

    public ChiffrageResultat(List<CasDeTest> casDeTests, double tempsTotal) {
        this.casDeTests = casDeTests;
        this.tempsTotal = tempsTotal;
    }

    public List<CasDeTest> getCasDeTests() {
        return casDeTests;
    }

    public double getTempsTotal() {
        return tempsTotal;
    }
}
