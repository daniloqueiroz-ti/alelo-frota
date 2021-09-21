package br.com.alelofrota.api.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.alelofrota.domain.model.Vehicle;
import br.com.alelofrota.domain.service.VehicleService;
import io.swagger.annotations.Api;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/report-vehicle")
@Api(value = "API REST Alelo Frota 2020")
public class ReportVehicleController {
	
	public List<Vehicle> listaVeiculos;
	
	@Autowired
	private VehicleService serviceVehicle;
	
	private void popularLista() {
		listaVeiculos = serviceVehicle.findAll();
	}

	@GetMapping(value = "/imprimir-jasper")
	public void imprimirPlacasJasper(HttpServletResponse response) throws JRException, IOException {
		
		popularLista();
		String empresa = "Queiroz Tecnologia";

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("copyright", empresa);

		ClassPathResource pathjasper = new ClassPathResource("/relatorios/relatorioVeiculosJasper.jasper");
		JRBeanCollectionDataSource jrds = new JRBeanCollectionDataSource(listaVeiculos);
		JasperPrint jasperPrint = JasperFillManager.fillReport(pathjasper.getInputStream(), parameters, jrds);

		JRPdfExporter pdfExporter = new JRPdfExporter();
		pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));

		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "attachment;filename=relatorioVeiculosJasper.pdf");

		pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
		pdfExporter.exportReport();
	}
	
	@GetMapping(value = "/imprimir-itext")
	public void imprimirPlacasItext(HttpServletResponse response) throws DocumentException, IOException {
		
		popularLista();
		String empresa = "Queiroz Tecnologia";

		Document doc = new Document();
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "inline; filename=relatorioVeiculosItext.pdf");				
		PdfWriter.getInstance(doc, response.getOutputStream());
		
		doc.open();
		doc.addAuthor("Danilo Queiroz");
		
		PdfPTable tbHeader = new PdfPTable(2);
		tbHeader.getDefaultCell().setBorder(0);
		tbHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		tbHeader.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		tbHeader.addCell(new Paragraph(empresa));
		tbHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		Image logo = Image.getInstance("src/main/resources/relatorios/logo_queiroz_tecnologia.PNG");
		logo.scalePercent(50);
		tbHeader.addCell(logo);
		doc.add(tbHeader);
		
		PdfPTable tbTitle = new PdfPTable(3);
//		tbTitle.setHorizontalAlignment(100);
		tbTitle.getDefaultCell().setBorder(0);
//		tbTitle.addCell("\n");
		tbTitle.addCell(new Paragraph(""));
		tbTitle.addCell(new Paragraph("Relatório de Veículos"));
		tbTitle.addCell(new Paragraph(""));
//		tbTitle.addCell("\n");
		doc.add(tbTitle);
		
		PdfPTable tbDados = new PdfPTable(2);
		tbDados.addCell(new PdfPCell(new Paragraph("Placa")));
		tbDados.addCell(new PdfPCell(new Paragraph("Modelo")));
		for (Vehicle vehicle : listaVeiculos) {
			tbDados.addCell(vehicle.getPlate());
			tbDados.addCell(vehicle.getModel());
		}
		doc.add(tbDados);
		
		doc.close();

	}

}
