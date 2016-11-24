package logic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.tools.PDFMerger;

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
}
