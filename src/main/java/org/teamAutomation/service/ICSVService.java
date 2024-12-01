package org.teamAutomation.service;

import org.teamAutomation.entity.CasDeTest;
import org.teamAutomation.entity.ChiffrageResultat;

import java.util.List;

public interface ICSVService {
    ChiffrageResultat calculerEtRetournerResultat(String cheminCSV);
}
