package org.teamAutomation.entity;

public class CasDeTest {
    private String nom;
    private String complexite;
    private double tempsParCas;

    public CasDeTest() {
    }

    public CasDeTest(String nom, String complexite) {
        this.nom = nom;
        this.complexite = complexite;
    }

    public String getNom() {
        return nom;
    }

    public String getComplexite() {
        return complexite;
    }

    public double getTempsParCas() {
        return tempsParCas;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setComplexite(String complexite) {
        this.complexite = complexite;
    }

    public void setTempsParCas(double tempsParCas) {
        this.tempsParCas = tempsParCas;
    }
}
