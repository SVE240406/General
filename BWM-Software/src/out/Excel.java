package out;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import software.Buchung;
import software.Konto;
import software.KontoTyp;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class Excel {
    private Konto[] konten;
    private int offsetX, offsetY, offsetY1, offsetY2;
    private boolean x1;

    private ArrayList<XSSFRow> rows = new ArrayList<>();
    XSSFColor[] colors = new XSSFColor[5];

    private XSSFWorkbook workbook;
    private XSSFSheet sh;
    private XSSFFont nameFont, solutionFont;
    private XSSFCellStyle nameStyle, fieldStyle, solutionStyle;

    public void setKonten(Konto[] konten){
        this.konten = konten;
    }

    public void createFile(String name){
        createColors();
        try {
            workbook = new XSSFWorkbook();
            sh = workbook.createSheet(name);
            createCellStyles();

            for(int i = 0; i < konten.length; i++){
                if(i%2==0) {
                    offsetX = 0;
                    x1 = true;
                    offsetY = Math.max(offsetY1, offsetY2)+1;
                    offsetY2 = offsetY;
                }
                else {
                    offsetX = 5;
                    x1 = false;
                    offsetY = offsetY2;
                }
                doOneKonto(konten[i]);
                if(x1)
                    offsetY1 = offsetY;
                else
                    offsetY2 = offsetY;
            }

            for(int i=0;i< rows.size();i++)
                sh.autoSizeColumn(i);
            FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.home") + "/Documents/" + name + ".xlsx");
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            System.out.println("Completed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createColors(){
        int[][] rgbcolors = {{255, 192, 0}, {0, 176, 240}, {255, 0, 0}, {102, 176, 80}, {109, 109, 109}};
        byte[][] rgbs = new byte[rgbcolors.length][rgbcolors[0].length];
        for (int i = 0; i < rgbs.length; i++) {
            for (int j = 0; j < rgbs[i].length; j++) {
                rgbs[i][j] = (byte)rgbcolors[i][j];
            }
        }
        for(int i = 0; i < colors.length; i++)
            colors[i] = new XSSFColor(rgbs[i]);
    }

    private void createCellStyles(){
        fieldStyle = workbook.createCellStyle();
        fieldStyle.setFont(workbook.createFont());
        fieldStyle.setBorderBottom(BorderStyle.THIN);
        fieldStyle.setBorderLeft(BorderStyle.THIN);
        fieldStyle.setBorderRight(BorderStyle.THIN);
        fieldStyle.setBorderTop(BorderStyle.THIN);
        fieldStyle.setDataFormat(8);

        solutionFont = workbook.createFont();
        solutionFont.setBold(true);
        solutionStyle = workbook.createCellStyle();
        solutionStyle.setFont(solutionFont);
        solutionStyle.setBorderBottom(BorderStyle.DOUBLE);
        solutionStyle.setBorderLeft(BorderStyle.THIN);
        solutionStyle.setBorderRight(BorderStyle.THIN);
        solutionStyle.setBorderTop(BorderStyle.THIN);
        solutionStyle.setDataFormat(8);
    }

    private void doOneKonto(Konto konto){
        createNameStyle(konto.getKontoTyp());
        int neededRows = konto.numOfBuchungen() + 3;
        while(rows.size() < offsetY + neededRows)
            rows.add(sh.createRow(rows.size()));
        XSSFCell namecell = rows.get(offsetY).createCell(offsetX);
        namecell.setCellValue(konto.toString());
        namecell.setCellStyle(nameStyle);
        offsetY++;
        XSSFCell[] cells = new XSSFCell[4];
        for(int i = 0; i < 4; i++)
            cells[i] = rows.get(offsetY).createCell(offsetX + i);
        cells[0].setCellValue("Datum");
        cells[0].setCellStyle(fieldStyle);
        cells[1].setCellValue("Gegenkonto");
        cells[1].setCellStyle(fieldStyle);
        cells[2].setCellValue("Soll");
        cells[2].setCellStyle(fieldStyle);
        cells[3].setCellValue("Haben");
        cells[3].setCellStyle(fieldStyle);
        offsetY++;

        int startRow = offsetY;

        for(int i = 0; i < konto.numOfBuchungen(); i++, offsetY++)
            createBuchung(konto.getBuchung(i));

        createSumms(startRow, offsetY);
    }

    private void createNameStyle(KontoTyp typ){
        nameFont = workbook.createFont();
        nameFont.setBold(true);
        switch (typ){
            case AKTIVES_BK -> nameFont.setColor(colors[0]);
            case PASSIVES_BK -> nameFont.setColor(colors[1]);
            case AUFWAND -> nameFont.setColor(colors[2]);
            case ERLOES -> nameFont.setColor(colors[3]);
            case GRAU -> nameFont.setColor(colors[4]);
        }
        nameStyle = workbook.createCellStyle();
        nameStyle.setFont(nameFont);
    }

    private void createBuchung(Buchung buchung){
        XSSFCell[] cells = new XSSFCell[4];
        for(int i = 0; i < 4; i++)
            cells[i] = rows.get(offsetY).createCell(offsetX + i);
        cells[0].setCellValue(buchung.getDateString());
        cells[0].setCellStyle(fieldStyle);
        cells[1].setCellValue(buchung.getGegenk().toString());
        cells[1].setCellStyle(fieldStyle);
        if(buchung.getSoll() == 0f)
            cells[3].setCellValue(buchung.getHaben());
        else
            cells[2].setCellValue(buchung.getSoll());
        cells[2].setCellStyle(fieldStyle);
        cells[3].setCellStyle(fieldStyle);
    }

    private void createSumms(int startRow, int endRow) {
        Cell summeTxt = rows.get(offsetY).createCell(offsetX+1);
        summeTxt.setCellValue("Summe");
        summeTxt.setCellStyle(solutionStyle);

        Cell summS = rows.get(offsetY).createCell(offsetX+2);
        summS.setCellFormula("SUM(" + ((offsetX == 0)?"C":"H")+(startRow+1) + ":" + ((offsetX == 0)?"C":"H")+(endRow) + ")");
        summS.setCellStyle(solutionStyle);

        Cell summH = rows.get(offsetY).createCell(offsetX+3);
        summH.setCellFormula("SUM(" + ((offsetX == 0)?"D":"I")+(startRow+1) + ":" + ((offsetX == 0)?"D":"I")+(endRow) + ")");
        summH.setCellStyle(solutionStyle);
        offsetY++;
    }
}
