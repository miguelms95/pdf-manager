package igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.ListModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
//import java.awt.List;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.tools.PDFBox;
import org.apache.pdfbox.tools.PDFBox.*;
import org.apache.pdfbox.tools.PDFMerger;

import logic.*;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Dimension;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;
import java.io.File;

import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.GridLayout;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnArchivos;
	private JMenu mnAyuda;
	private JMenuItem mntmSalir;
	private JSeparator separator;
	private JMenuItem mntmReiniciar;
	private JMenuItem mntmAcercaDe;
	private JPanel pnArriba;
	private JPanel pnCentro;
	private JPanel pnAbajo;
	private JPanel panel_3;
	private JLabel lblNewLabel;
	private JLabel lblPdfManager;
	private JScrollPane scrollPane;
	private JList listaImportados;
	private JButton btnUnirPdfs;
	private JButton btnLimpiarListaSeleccionados;
	private JButton btnExtraerPaginas;
	private JPanel pnTopCentro;
	private JButton btnSeleccionarPdfs;
	
	private JLabel lblImportaArchivosPdf;
	private JPanel panel_1;
	
	private DefaultListModel<String> listaArchivosImportados;
	private DefaultListModel<String> listaArchivosSeleccionados;
	private ArrayList<File> archivos = new ArrayList<>();
	private List<File> droppedFiles = null;
	
	private JPanel panel_2;
	private JButton btnNewButton;
	private JPanel panel_4;
	private JPanel pnDerecha;
	private JPanel panel_5;
	private JComboBox comboBox;
	private JLabel lblSeleccionaOpcin;
	private JPanel pnImportar;
	private JPanel panel_8;
	private JButton btnAadir;
	private JButton btnEliminarImportados;
	private JPanel panel_9;
	private JButton btSubir;
	private JButton btBajar;
	private JButton btnProcesar;
	private JList listaSeleccionados;
	private JButton btnEliminarSeleccionados;
	private JButton btLimpiarListaImportados;
	private JButton btnSeleccionarTodo;
	
	static VentanaPrincipal vp;
	private JMenuItem mntmImportarPdfs;
	private JScrollPane scrollPane_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					vp = new VentanaPrincipal();
					vp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setMinimumSize(new Dimension(758, 500));
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/logo_peq2.png")));
		setTitle("PDF Manager - Gestor de PDFs");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 758, 502);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPnArriba(), BorderLayout.NORTH);
		contentPane.add(getPnCentro(), BorderLayout.CENTER);
		//contentPane.add(getPnAbajo(), BorderLayout.SOUTH);
		setLocationRelativeTo(null);
	}

	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnArchivos());
			menuBar.add(getMnAyuda());
		}
		return menuBar;
	}
	private JMenu getMnArchivos() {
		if (mnArchivos == null) {
			mnArchivos = new JMenu("Archivo");
			mnArchivos.setMnemonic('a');
			mnArchivos.add(getMntmImportarPdfs());
			mnArchivos.add(getMntmReiniciar());
			mnArchivos.add(getSeparator());
			mnArchivos.add(getMntmSalir());
		}
		return mnArchivos;
	}
	private JMenu getMnAyuda() {
		if (mnAyuda == null) {
			mnAyuda = new JMenu("Ayuda");
			mnAyuda.setMnemonic('y');
			mnAyuda.add(getMntmAcercaDe());
		}
		return mnAyuda;
	}
	private JMenuItem getMntmSalir() {
		if (mntmSalir == null) {
			mntmSalir = new JMenuItem("Salir");
			mntmSalir.setMnemonic('s');
			mntmSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return mntmSalir;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}
	private JMenuItem getMntmReiniciar() {
		if (mntmReiniciar == null) {
			mntmReiniciar = new JMenuItem("Reiniciar");
			mntmReiniciar.setMnemonic('r');
			mntmReiniciar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					limpiarListaImportados();
					limpiarListaSeleccionados();
					resetearArchivos();
				}
			});
		}
		return mntmReiniciar;
	}
	private JMenuItem getMntmAcercaDe() {
		if (mntmAcercaDe == null) {
			mntmAcercaDe = new JMenuItem("Acerca de");
			mntmAcercaDe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(rootPane, "PDf Manager 2016\nDesarrollado por Miguel Martínez Serrano.\nVersión 1.0");
				}
			});
			mntmAcercaDe.setMnemonic('d');
		}
		return mntmAcercaDe;
	}
	private JPanel getPnArriba() {
		if (pnArriba == null) {
			pnArriba = new JPanel();
			pnArriba.setLayout(new BorderLayout(0, 0));
			pnArriba.add(getPanel_3());
			pnArriba.add(getLblNewLabel(), BorderLayout.EAST);
		}
		return pnArriba;
	}
	private JPanel getPnCentro() {
		if (pnCentro == null) {
			pnCentro = new JPanel();
			pnCentro.setLayout(new BorderLayout(0, 0));
			pnCentro.add(getPnTopCentro(), BorderLayout.NORTH);
			pnCentro.add(getPanel_4(), BorderLayout.CENTER);
		}
		return pnCentro;
	}
	private JPanel getPnAbajo() {
		if (pnAbajo == null) {
			pnAbajo = new JPanel();
			pnAbajo.add(getBtnExtraerPaginas());
			pnAbajo.add(getBtnUnirPdfs());
		}
		return pnAbajo;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setLayout(new BorderLayout(0, 0));
			panel_3.add(getPanel_2(), BorderLayout.CENTER);
		}
		return panel_3;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("version 1.0");
		}
		return lblNewLabel;
	}
	private JLabel getLblPdfManager() {
		if (lblPdfManager == null) {
			lblPdfManager = new JLabel("Manager");
			lblPdfManager.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblPdfManager.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblPdfManager;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					if(e.getKeyCode() == e.VK_DELETE)
						eliminarElementos(listaImportados);
				}
			});
			scrollPane.setBorder(new LineBorder(Color.LIGHT_GRAY));
			scrollPane.setViewportView(getLista());
		}
		return scrollPane;
	}
	private JList getLista() {
		if (listaImportados == null) {
			listaImportados = new JList();
			listaArchivosImportados = new DefaultListModel<>();
			listaImportados.setModel(listaArchivosImportados);
		}
		return listaImportados;
	}
	
	private JButton getBtnUnirPdfs() {
		if (btnUnirPdfs == null) {
			btnUnirPdfs = new JButton("Unir PDFs");
			btnUnirPdfs.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						unirPDFs();
						listaSeleccionados.clearSelection();
						listaImportados.clearSelection();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			btnUnirPdfs.setMnemonic('u');
		}
		return btnUnirPdfs;
	}

	
	private JButton getBtnLimpiarListaSeleccionados() {
		if (btnLimpiarListaSeleccionados == null) {
			btnLimpiarListaSeleccionados = new JButton("Limpiar Lista");
			btnLimpiarListaSeleccionados.setMnemonic('l');
			btnLimpiarListaSeleccionados.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					limpiarListaSeleccionados();
				}
			});
		}
		return btnLimpiarListaSeleccionados;
	}
	private void limpiarListaSeleccionados(){
		listaArchivosSeleccionados.clear();
	}
	
	private void limpiarListaImportados(){
		listaArchivosImportados.clear();
	}
	
	private JButton getBtnExtraerPaginas() {
		if (btnExtraerPaginas == null) {
			btnExtraerPaginas = new JButton("Extraer Paginas");
			btnExtraerPaginas.setMnemonic('e');
		}
		return btnExtraerPaginas;
	}
	private JPanel getPnTopCentro() {
		if (pnTopCentro == null) {
			pnTopCentro = new JPanel();
			pnTopCentro.setLayout(new BorderLayout(0, 0));
			pnTopCentro.add(getPanel_5(), BorderLayout.CENTER);
			pnTopCentro.add(getPanel_1(), BorderLayout.WEST);
		}
		return pnTopCentro;
	}
	private JButton getBtnSeleccionarPdfs() {
		if (btnSeleccionarPdfs == null) {
			btnSeleccionarPdfs = new JButton("Seleccionar Archivos...");
			btnSeleccionarPdfs.setMnemonic('s');
			btnSeleccionarPdfs.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					importarArchivos();
				}
			});
		}
		return btnSeleccionarPdfs;
	}
	private void importarArchivos(){
		String userDir = System.getProperty("user.home");
		JFileChooser chooser = new JFileChooser(userDir+"/Desktop");
		chooser.setMultiSelectionEnabled(true);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos PDF (*.pdf)", "pdf");
		chooser.setFileFilter(filter);
		chooser.setDialogTitle("Importar archivos PDF");
		chooser.showOpenDialog(vp);
		File[] archivosSeleccionados = chooser.getSelectedFiles();
		for (File file : archivosSeleccionados) {
			archivos.add(file);
		}
		imprimirLista();
	}
	private void imprimirLista() {
		if(!archivos.isEmpty()){
			for (File file : archivos) {
				if(!listaArchivosImportados.contains(file.getName()))
					listaArchivosImportados.addElement(file.getName());
			}
		}
	}
	private JLabel getLblImportaArchivosPdf() {
		if (lblImportaArchivosPdf == null) {
			lblImportaArchivosPdf = new JLabel("Importa archivos PDF:");
		}
		return lblImportaArchivosPdf;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			panel_1.add(getLblImportaArchivosPdf());
			panel_1.add(getBtnSeleccionarPdfs());
		}
		return panel_1;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.add(getBtnNewButton());
			panel_2.add(getLblPdfManager());
		}
		return panel_2;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("");
			btnNewButton.setBorder(null);
			btnNewButton.setEnabled(false);
			btnNewButton.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/logo_peq2.png")));
			btnNewButton.setDisabledIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/logo_peq2.png")));
		}
		return btnNewButton;
	}
	private JPanel getPanel_4() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.setLayout(new GridLayout(0, 2, 0, 0));
			panel_4.add(getPnImportar());
			panel_4.add(getPnDerecha());
		}
		return panel_4;
	}
	private JPanel getPnDerecha() {
		if (pnDerecha == null) {
			pnDerecha = new JPanel();
			pnDerecha.setLayout(new BorderLayout(0, 0));
			pnDerecha.add(getScrollPane_1(), BorderLayout.CENTER);
			//pnDerecha.add(getListAdded(), BorderLayout.SOUTH);
			pnDerecha.add(getPanel_9(), BorderLayout.SOUTH);
		}
		return pnDerecha;
	}
	private JPanel getPanel_5() {
		if (panel_5 == null) {
			panel_5 = new JPanel();
			panel_5.add(getLblSeleccionaOpcin());
			panel_5.add(getComboBox());
			panel_5.add(getBtnProcesar());
		}
		return panel_5;
	}
	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Combinar archivos", "Extraer paginas"}));
		}
		return comboBox;
	}
	private JLabel getLblSeleccionaOpcin() {
		if (lblSeleccionaOpcin == null) {
			lblSeleccionaOpcin = new JLabel("Selecciona opci\u00F3n:");
		}
		return lblSeleccionaOpcin;
	}
	private JPanel getPnImportar() {
		if (pnImportar == null) {
			pnImportar = new JPanel();
			DropTarget dt = new DropTarget(){
				public void drop(DropTargetDropEvent e){
					e.acceptDrop(DnDConstants.ACTION_COPY);
					//droppedFIles = e.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
					try {
						droppedFiles = (List<File>) e.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
						importaDropArchivos(droppedFiles);
					} catch (UnsupportedFlavorException | IOException e1) {
						e1.printStackTrace();
					}
				}
			};
			pnImportar.setDropTarget(dt);
			pnImportar.setLayout(new BorderLayout(0, 0));
			pnImportar.add(getScrollPane());
			pnImportar.add(getPanel_8(), BorderLayout.SOUTH);
		}
		return pnImportar;
	}
	
	private void importaDropArchivos(List<File> droppedFiles) {
		for (File file : droppedFiles) {
			if(!archivos.contains(file))	//compruebo que no exista ya el archivo
				archivos.add(file);			
		}
		imprimirLista();
		
	}
	
	private JPanel getPanel_8() {
		if (panel_8 == null) {
			panel_8 = new JPanel();
			panel_8.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			panel_8.add(getBtLimpiarListaImportados());
			panel_8.add(getBtnEliminarImportados());
			panel_8.add(getBtnSeleccionarTodo());
			panel_8.add(getBtnAadir());
		}
		return panel_8;
	}
	private JButton getBtnAadir() {
		if (btnAadir == null) {
			btnAadir = new JButton("A\u00F1adir");
			btnAadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int[] seleccionados = listaImportados.getSelectedIndices();
					if(seleccionados.length>0){
						for (int i=0; i< seleccionados.length;i++) {
							String elemento = archivos.get(seleccionados[i]).getName();
							if(!listaArchivosSeleccionados.contains(elemento))
								listaArchivosSeleccionados.addElement(elemento);
						}
						
					}
					listaImportados.clearSelection();
				}
			});
			btnAadir.setToolTipText("A\u00F1adir a la lista");
		}
		return btnAadir;
	}
	private JButton getBtnEliminarImportados() {
		if (btnEliminarImportados == null) {
			btnEliminarImportados = new JButton("Eliminar");
			btnEliminarImportados.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					eliminarElementos(listaImportados);
				}
			});
			btnEliminarImportados.setToolTipText("Eliminar de la lista");
		}
		return btnEliminarImportados;
	}
	private JPanel getPanel_9() {
		if (panel_9 == null) {
			panel_9 = new JPanel();
			panel_9.add(getBtSubir());
			panel_9.add(getBtBajar());
			panel_9.add(getBtnEliminarSeleccionados());
			panel_9.add(getBtnLimpiarListaSeleccionados());
		}
		return panel_9;
	}
	private JButton getBtSubir() {
		if (btSubir == null) {
			btSubir = new JButton("Subir");
			btSubir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int index, ant;
					index = listaSeleccionados.getSelectedIndex();
					ant = index-1;
					
					if(ant >=0){
						String aux = listaArchivosSeleccionados.getElementAt(ant);
						listaArchivosSeleccionados.setElementAt(listaArchivosSeleccionados.getElementAt(index), ant);
						listaArchivosSeleccionados.setElementAt(aux, index);
						listaSeleccionados.setSelectedIndex(ant);
					}
				}
			});
		}
		return btSubir;
	}
	private JButton getBtBajar() {
		if (btBajar == null) {
			btBajar = new JButton("Bajar");
			btBajar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int index, next;
					index = listaSeleccionados.getSelectedIndex();
					next = index+1;
					
					if(next < listaArchivosImportados.getSize()){
						String aux = listaArchivosSeleccionados.getElementAt(next);
						listaArchivosSeleccionados.setElementAt(listaArchivosSeleccionados.getElementAt(index), next);
						listaArchivosSeleccionados.setElementAt(aux, index);
						listaSeleccionados.setSelectedIndex(next);
					}
				}
			});
		}
		return btBajar;
	}
	private JButton getBtnProcesar() {
		if (btnProcesar == null) {
			btnProcesar = new JButton("Procesar");
			btnProcesar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					switch ((String)comboBox.getSelectedItem()) {
					case "Combinar archivos":
						//JOptionPane.showMessageDialog(vp, "Unir!");
						try {
							
							unirPDFs();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							//e1.printStackTrace();
							JOptionPane.showMessageDialog(vp, "ERROR:" + e1.getMessage());
						}
						break;
					case "Extraer paginas":
						JOptionPane.showConfirmDialog(vp, "Extraer paginas. En construcción.");
						
						break;
					default:
						break;
					}
				}
			});
		}
		return btnProcesar;
	}
	private JList getListAdded() {
		if (listaSeleccionados == null) {
			listaSeleccionados = new JList();
			listaArchivosSeleccionados = new DefaultListModel<>();
			listaSeleccionados.setModel(listaArchivosSeleccionados);
			listaSeleccionados.setBorder(null);
		}
		return listaSeleccionados;
	}
	private JButton getBtnEliminarSeleccionados() {
		if (btnEliminarSeleccionados == null) {
			btnEliminarSeleccionados = new JButton("Eliminar");
			btnEliminarSeleccionados.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					eliminarElementos(listaSeleccionados);
				}
			});
		}
		return btnEliminarSeleccionados;
	}
	private JButton getBtLimpiarListaImportados() {
		if (btLimpiarListaImportados == null) {
			btLimpiarListaImportados = new JButton("Limpiar Lista");
			btLimpiarListaImportados.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					limpiarListaImportados();
					resetearArchivos();
				}
			});
		}
		return btLimpiarListaImportados;
	}
	private void resetearArchivos(){
		archivos.clear();
	}
	
	private void eliminarElementos(JList j_list){
		ListModel milista = (DefaultListModel<String>) j_list.getModel();
		int[] valores = j_list.getSelectedIndices();
		while(valores.length > 0){
			if(j_list.equals(listaImportados)){
				Object obj = milista.getElementAt(valores[0]);
				//System.out.println(obj);
				int indexElementoBorrar = find(obj);
				listaArchivosSeleccionados.remove(indexElementoBorrar);
				archivos.remove(valores[0]);
			}
			((DefaultListModel<String>) milista).removeElement(milista.getElementAt(valores[0]));
			valores = j_list.getSelectedIndices();
		}
	}

	private int find(Object element) {
		return listaArchivosSeleccionados.indexOf(element);
	}
	
	private int findIndexArchivo(Object element) {
		for(int i=0; i<archivos.size();i++){
			if((archivos.get(i).getName()).equals(element))
				return i;
		}
		return -1;
	}
	
	private JButton getBtnSeleccionarTodo() {
		if (btnSeleccionarTodo == null) {
			btnSeleccionarTodo = new JButton("Seleccionar todo");
			btnSeleccionarTodo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					seleccionarTodos(listaImportados);
				}
			});
		}
		return btnSeleccionarTodo;
	}
	private void seleccionarTodos(JList lista){
		int tam = ((DefaultListModel<String>) lista.getModel()).size();
		int[] indices = new int[tam];
		for(int i=0; i<tam;i++)
			indices[i]=i;
		lista.setSelectedIndices(indices);
	}
	
	private void unirPDFs() throws IOException{
		int[] indicesSeleccionados = listaSeleccionados.getSelectedIndices();
		if(indicesSeleccionados.length == 0){
			seleccionarTodos(listaSeleccionados);
		}else
			seleccionarTodos(listaSeleccionados);// en todo caso se seleccionan todos
		indicesSeleccionados = listaSeleccionados.getSelectedIndices();
			
		ArrayList<File> listaArchivos = new ArrayList<>();
		
		if(indicesSeleccionados.length > 1){
			for(int i=0; i <indicesSeleccionados.length;i++){
				listaArchivos.add(archivos.get(findIndexArchivo(listaArchivosSeleccionados.get(indicesSeleccionados[i]))));
			}
			
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo PDF (*.pdf)", "pdf");
			chooser.setFileFilter(filter);
			
			String userDir = System.getProperty("user.home");
			String cadena = listaArchivosSeleccionados.firstElement();//.get(listaSeleccionados.getSelectedIndex()).getName();
			cadena = cadena.substring(0, cadena.length()-4);
			File file = new File(userDir + "/Desktop/" + cadena +"_unido.pdf");
			chooser.setSelectedFile(file);
			
			int r = chooser.showSaveDialog(vp);
			//chooser.showOpenDialog(vp);
			if(r == JFileChooser.APPROVE_OPTION){
				PDFOptions.unirArchivos(listaArchivos, chooser.getSelectedFile().getPath());
				JOptionPane.showMessageDialog(vp, "PDF generado con éxito","¡Bien!",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else
			JOptionPane.showMessageDialog(this, "Tienes que seleccionar más de un pdf para unir.");
	}
	private JMenuItem getMntmImportarPdfs() {
		if (mntmImportarPdfs == null) {
			mntmImportarPdfs = new JMenuItem("Importar...");
			mntmImportarPdfs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
			mntmImportarPdfs.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					importarArchivos();
				}
			});
			mntmImportarPdfs.setMnemonic('i');
		}
		return mntmImportarPdfs;
	}
	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setBorder(new LineBorder(Color.LIGHT_GRAY));
			scrollPane_1.setViewportView(getListAdded());
		}
		return scrollPane_1;
	}
}
