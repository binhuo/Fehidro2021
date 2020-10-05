package fehidro.api.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.google.common.net.HttpHeaders;

import fehidro.api.model.ItemRelatorio;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/relatorio")
public class GeradorRelatorioXLSX {
	
	public GeradorRelatorioXLSX() {
		
	}
	
	@ApiOperation(value = "Relatorio *.XLSX para relatorio final.")
	@GetMapping(value = "/xlsx/final/{itensRelatorio}")
	public ResponseEntity<StreamingResponseBody> gerarRelatorioFinal(ArrayList<ItemRelatorio> itensRelatorio) {
		
		//Filename
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
		LocalDateTime now = LocalDateTime.now();  
		String fileName = "Relatorio"+ dtf.format(now) +".xlsx";
		
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

      return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"" + fileName + "\"").body(workbook::write);
           
	}
	
	@ApiOperation(value = "Relatorio *.XLSX para relatorio por subPDC.")
	@GetMapping(value = "/xlsx/subPdc/{itensRelatorio}")
	public ResponseEntity<StreamingResponseBody> gerarRelatorioSubpdc(ArrayList<ItemRelatorio> itensRelatorio) {
		
		//Filename
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
		LocalDateTime now = LocalDateTime.now();  
		String fileName = "Relatorio"+ dtf.format(now) +".xlsx";
		
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

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"" + fileName + "\"").body(workbook::write);
	}
	
	
	
}