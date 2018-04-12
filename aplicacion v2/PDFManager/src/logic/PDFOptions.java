package logic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.PDFMerger;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

public class PDFOptions {
	static String ruta1;
	
	public static void unirArchivos(ArrayList<File> lista, String path) throws IOException{
		PDFMergerUtility pm = new PDFMergerUtility();
		for (File file : lista) {
			ruta1 = file.getAbsolutePath();
			//PDDocument documento = PDDocument.load(file);
			pm.addSource(file);
			
		}
//		String userDir = System.getProperty("user.home");
//		pm.setDestinationFileName(userDir + "/Desktop/PDFunido.pdf");
		if(path.contains(".pdf"))
			pm.setDestinationFileName(path);
		else
			pm.setDestinationFileName(path+".pdf");
		pm.mergeDocuments(null);
		
		
		//guardarArchivos(listaDocs, ruta1);
	}
	
	public void guardarArchivos(ArrayList<PDDocument> listaDocs, String ruta) throws IOException{
		if(!listaDocs.isEmpty()){
			for (PDDocument pdDocument : listaDocs) {
				pdDocument.save(new File(ruta));
				pdDocument.close();
			}
		}
	}
	
	public static BufferedImage getImagenPDF(String pdf, int pagina) throws IOException{
		PDDocument document = PDDocument.load(new File(pdf));
		PDFRenderer pdfRenderer = new PDFRenderer(document);
//		for (int page = 0; page < document.getNumberOfPages(); ++page)
//		{ 
		    BufferedImage bim = pdfRenderer.renderImageWithDPI(pagina, 300, ImageType.RGB);
		    
		    // suffix in filename will be used as the file format
		    //ImageIOUtil.writeImage(bim, pdf + "-" + (page+1) + ".png", 300);
//		}
		document.close();
		return bim;
	}

	public static int getNumerOfPDFPages(String pdf){
		try {
			PDDocument documento = PDDocument.load(new File(pdf));
			int numPags = documento.getNumberOfPages();
			documento.close();
			return numPags;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 1;
	}

}
