package fehidro.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import fehidro.control.ItemRelatorio;


public class GeradorRelatorioXLSX {
	
	public GeradorRelatorioXLSX() {
		
	}
	
	public static void gerarRelatorioFinal(ArrayList<ItemRelatorio> itensRelatorio) throws IOException {
		
		//Filename
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
		LocalDateTime now = LocalDateTime.now();  
		String fileName = "relatorio"+ dtf.format(now) +".xlsx";
		
		HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheetRelatorio = workbook.createSheet("Relatorio Final");

        int rownum = 0;
        int cellnum = 0;
        ////LABELS
        Row row = sheetRelatorio.createRow(rownum++);
        //Nome do proj
        Cell cellLabelTitulo = row.createCell(cellnum++);
        cellLabelTitulo.setCellValue("Titulo");
        //Classificacao geral (rank)
        Cell cellLabelClassificacaoGeral = row.createCell(cellnum++);
        cellLabelClassificacaoGeral.setCellValue("Classificacao Geral");
        //Subpdc
        Cell cellLabelSubpdc = row.createCell(cellnum++);
        cellLabelSubpdc.setCellValue("Subpdc");
        //Classificacao por Subpdc (rank)
        Cell cellLabelClassificacaoSubpdc = row.createCell(cellnum++);
        cellLabelClassificacaoSubpdc.setCellValue("Classificacao Subpdc");
        //Nota
        Cell cellLabelNota = row.createCell(cellnum++);
        cellLabelNota.setCellValue("Nota");
      //Nota
        Cell cellLabelStatus = row.createCell(cellnum++);
        cellLabelStatus.setCellValue("Status");
        
        ////VALORES
        cellnum = 0;
        for (ItemRelatorio item : itensRelatorio) {
            row = sheetRelatorio.createRow(rownum++);
            //Nome do proj
            Cell cellTitulo = row.createCell(cellnum++);
            cellTitulo.setCellValue(item.getProposta().getNomeProjeto());
            //Classificacao geral (rank)
            Cell cellClassificacaoGeral = row.createCell(cellnum++);
            cellClassificacaoGeral.setCellValue(item.getClassificacao());
            //Subpdc
            Cell cellSubpdc = row.createCell(cellnum++);
            cellSubpdc.setCellValue(item.getProposta().getSubPDC().getTitulo());
            //Classificacao por Subpdc (rank)
            Cell cellClassificacaoSubpdc = row.createCell(cellnum++);
            cellClassificacaoSubpdc.setCellValue(item.getClassificacaoSubpdc());
            //Nota
            Cell cellNota = row.createCell(cellnum++);
            cellNota.setCellValue(item.getSoma());
            //Status
            Cell cellStatus = row.createCell(cellnum++);
            cellStatus.setCellValue(item.getStringDesclassificado());
        }

      //Grava o arquivo
        try {
            FileOutputStream out = new FileOutputStream(new File(fileName));
            workbook.write(out);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
               System.out.println("Arquivo nao encontrado!");
        } catch (IOException e) {
            e.printStackTrace();
               System.out.println("Erro na edicao do arquivo!");
        }
           
	}
	
	public static void gerarRelatorioSubpdc(ArrayList<ItemRelatorio> itensRelatorio) throws IOException {
		
		//Filename
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
		LocalDateTime now = LocalDateTime.now();  
		String fileName = "relatorio"+ dtf.format(now) +".xlsx";
		
		HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheetRelatorio = workbook.createSheet("Relatorio por subpdc");
        
        //TODO: Adicionar em algum lugar qual subpdc que este relatorio eh referente.

        int rownum = 0;
        int cellnum = 0;
        ////LABELS
        Row row = sheetRelatorio.createRow(rownum++);
        //Nome do proj
        Cell cellLabelTitulo = row.createCell(cellnum++);
        cellLabelTitulo.setCellValue("Titulo");
        //Classificacao geral (rank)
        Cell cellLabelClassificacaoGeral = row.createCell(cellnum++);
        cellLabelClassificacaoGeral.setCellValue("Classificacao Geral");
        //Subpdc
        Cell cellLabelSubpdc = row.createCell(cellnum++);
        cellLabelSubpdc.setCellValue("Subpdc");
        //Nota
        Cell cellLabelNota = row.createCell(cellnum++);
        cellLabelNota.setCellValue("Nota");
        //Status
        Cell cellLabelStatus = row.createCell(cellnum++);
        cellLabelStatus.setCellValue("Status");
        
        ////VALORES
        cellnum = 0;
        for (ItemRelatorio item : itensRelatorio) {
            row = sheetRelatorio.createRow(rownum++);
            //Nome do proj
            Cell cellTitulo = row.createCell(cellnum++);
            cellTitulo.setCellValue(item.getProposta().getNomeProjeto());
            //Classificacao geral (rank)
            Cell cellClassificacaoGeral = row.createCell(cellnum++);
            cellClassificacaoGeral.setCellValue(item.getClassificacao());
            //Subpdc
            Cell cellSubpdc = row.createCell(cellnum++);
            cellSubpdc.setCellValue(item.getProposta().getSubPDC().getTitulo());
            //Nota
            Cell cellNota = row.createCell(cellnum++);
            cellNota.setCellValue(item.getSoma());
            //Status
            Cell cellStatus = row.createCell(cellnum++);
            cellStatus.setCellValue(item.getStringDesclassificado());
        }

      //Grava o arquivo
        try {
            FileOutputStream out = new FileOutputStream(new File(fileName));
            workbook.write(out);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
               System.out.println("Arquivo nao encontrado!");
        } catch (IOException e) {
            e.printStackTrace();
               System.out.println("Erro na edicao do arquivo!");
        }
	}
	
	
	
}