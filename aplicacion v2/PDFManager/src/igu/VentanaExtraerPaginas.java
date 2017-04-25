package igu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.PDFOptions;

import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Toolkit;

/**
 * 
 * @author Miguel
 *
 *	Falta que el spinner tenga máximo el número de paginas del PDF seleccionado
 */
public class VentanaExtraerPaginas extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JRadioButton rdbtnExtraerTodasLas;
	private JPanel pnExtraerUna;
	JSpinner spinner;
	JRadioButton rdbtnExtraerUnaPagina;
	JButton btnImagenMiniatura;
	String documento;
	JPanel panelMiniatura;
	
	private ButtonGroup bg = new ButtonGroup();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VentanaExtraerPaginas dialog = new VentanaExtraerPaginas("C:\\Users\\migue\\Desktop\\HERENCIA GENETICA.pdf");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws IOException 
	 */
	public VentanaExtraerPaginas(String documento) throws IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaExtraerPaginas.class.getResource("/img/logo_peq2.png")));

		setMinimumSize(new Dimension(500, 400));
		this.documento = documento;
		setModal(true);
		
		setTitle("PDF Manager: Extraer paginas");
		setBounds(100, 100, 509, 301);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(VentanaPrincipal.vp);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		{
			JPanel pnTop = new JPanel();
			contentPanel.add(pnTop, BorderLayout.NORTH);
			{
				JLabel lbSeleccionarPaginas = new JLabel("Seleccionar p\u00E1ginas a extraer del PDF");
				pnTop.add(lbSeleccionarPaginas);
			}
		}
		{
			JPanel pnCentro = new JPanel();
			contentPanel.add(pnCentro, BorderLayout.CENTER);
			pnCentro.setLayout(new GridLayout(1, 0, 0, 0));
			{
				JPanel pnIzquierda = new JPanel();
				pnCentro.add(pnIzquierda);
				{
					rdbtnExtraerTodasLas = new JRadioButton("Extraer todas las paginas");
					rdbtnExtraerTodasLas.setSelected(true);
					rdbtnExtraerTodasLas.setBounds(6, 20, 147, 23);
					rdbtnExtraerTodasLas.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(rdbtnExtraerUnaPagina.isSelected())
								spinner.setEnabled(true);
							else
								spinner.setEnabled(false);
						}
					});
				}
				{
					pnExtraerUna = new JPanel();
					pnExtraerUna.setBounds(6, 50, 212, 53);
					pnExtraerUna.setLayout(new BorderLayout(0, 0));
					{
						rdbtnExtraerUnaPagina = new JRadioButton("Extraer una pagina");
						rdbtnExtraerUnaPagina.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if(rdbtnExtraerUnaPagina.isSelected())
									spinner.setEnabled(true);
								else
									spinner.setEnabled(false);
							}
						});
						pnExtraerUna.add(rdbtnExtraerUnaPagina, BorderLayout.NORTH);
						bg.add(rdbtnExtraerUnaPagina);
					}
					{
						JPanel pnExtraerUnaOpciones = new JPanel();
						pnExtraerUna.add(pnExtraerUnaOpciones, BorderLayout.CENTER);
						{
							JLabel lblSeleccionaPagina = new JLabel("Selecciona pagina:");
							pnExtraerUnaOpciones.add(lblSeleccionaPagina);
						}
						{
							spinner = new JSpinner();
							spinner.addChangeListener(new ChangeListener() {
								public void stateChanged(ChangeEvent e) {
									if((int)spinner.getValue()<0){
										System.out.println("Valor menor que 0");
									}else{
										try {
											if((int)spinner.getValue() >= 0)
												actualizarMiniatura((int) spinner.getValue());
										} catch (IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									}
								}
							});
							spinner.setPreferredSize(new Dimension(40, 20));
							spinner.setEnabled(false);
							pnExtraerUnaOpciones.add(spinner);
						}
					}
				}
				pnIzquierda.setLayout(null);
				pnIzquierda.add(rdbtnExtraerTodasLas);
				pnIzquierda.add(pnExtraerUna);
			}
			{
				panelMiniatura = new JPanel();
				pnCentro.add(panelMiniatura);
				panelMiniatura.setLayout(new GridLayout(0, 1, 0, 0));
				{
					btnImagenMiniatura = new JButton("");
					btnImagenMiniatura.setEnabled(false);
					panelMiniatura.add(btnImagenMiniatura);
				}
			}
			bg.add(rdbtnExtraerTodasLas);
			
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		//setVisible(true);
		pack();
		
		actualizarMiniatura(0);
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				try {
					actualizarMiniatura((int)spinner.getValue());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	private void actualizarMiniatura(int pagina) throws IOException{
		setImagenAdaptada(btnImagenMiniatura, documento,pagina);
	}

	private void setImagenAdaptada(JButton boton, String rutaImagen, int pagina) throws IOException{
		Image imgOriginal = new ImageIcon(PDFOptions.getImagenPDF(documento,pagina)).getImage();
		Image imgEscalada = imgOriginal.getScaledInstance((int)(boton.getWidth()), (int)(boton.getHeight()), Image.SCALE_FAST);
		boton.setIcon(new ImageIcon(imgEscalada));
		boton.setDisabledIcon(new ImageIcon(imgEscalada));
	}
}
