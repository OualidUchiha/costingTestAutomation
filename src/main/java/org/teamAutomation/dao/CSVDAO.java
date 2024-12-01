package org.teamAutomation.dao;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.teamAutomation.entity.CasDeTest;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CSVDAO implements ICSVDAO{
    private static final Logger logger= LogManager.getLogger(CSVDAO.class);
    @Override
    public List<CasDeTest> lireCSV(String chemin) {
        logger.info("Lecture du fichier CSV: {}",chemin);
        List<CasDeTest> casDeTests=new ArrayList<>();

        try(CSVReader reader=new CSVReader(new FileReader(chemin))){
            String[] ligne;
            //reader.readNext();
            while ((ligne=reader.readNext())!=null){
                CasDeTest casDeTest=new CasDeTest();
                casDeTest.setNom(ligne[0]);
                casDeTest.setComplexite(ligne[1]);
                casDeTests.add(casDeTest);
                logger.debug("Cas de test ajouté : {}",casDeTest);
            }
        }catch (IOException | CsvValidationException e){
            logger.error("Erreur lors de la lecture du fichier CSV",e);
        }
        return casDeTests;
    }

    @Override
    public void ecrireCSV(String chemin, List<CasDeTest> casDeTests, double tempsTotal) {
        logger.info("Écriture des résultats dans le fichier CSV: {}",chemin);

        try(CSVWriter writer=new CSVWriter(new FileWriter(chemin))){
            writer.writeNext(new String[]{"Nom du cas de test","Complexité","Temps par cas (heures)"});

            for(CasDeTest cas : casDeTests){
                String[] ligne = {cas.getNom(),cas.getComplexite(),String.valueOf(cas.getTempsParCas())};
                writer.writeNext(ligne);
                logger.debug("Écrit dans CSV : {}", cas);
            }
            writer.writeNext(new String[]{"Temps total","",String.valueOf(tempsTotal)});
        }catch (Exception e){
            logger.error("Erreur lors de l'écriture du fichier CSV", e);
        }

    }

    @Override
    public void convertirCSVEnExcel(String cheminCsv, String cheminExcel) {
        logger.info("Conversion du fichier CSV en Excel : {}", cheminExcel);

        try(CSVReader csvReader=new CSVReader(new FileReader(cheminCsv));
        Workbook workbook=new XSSFWorkbook()){
            //créer une feuille Excel
            Sheet sheet=workbook.createSheet("Cas de Test");
            String[] ligne;
            int rowIndex=0;
            // lire le fichier CSV ligne par ligne
            while ((ligne=csvReader.readNext())!=null){
                Row row=sheet.createRow(rowIndex++);
                for(int colIndex=0;colIndex<ligne.length;colIndex++){
                    Cell cell=row.createCell(colIndex);
                    cell.setCellValue(ligne[colIndex]);
                }
            }
            // Sauvegarder le fichier Excel
            try(FileOutputStream fileOut=new FileOutputStream(cheminExcel)){
                workbook.write(fileOut);
                logger.info("Fichier Excel généré avec succès : " + cheminExcel);
            }
        }catch (IOException | CsvValidationException e){
            logger.error("Erreur lors de la conversion CSV vers Excel : " + e.getMessage());
        }
    }
}
