/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;
import modelo.Pais;
import modelo.Pelicula;
import modelo.Productora;
import service.PeliculaService;

/**
 *
 * @author Iñigo
 */
public class PeliculasForm extends JFrame {

    private Dimension dmVentana = new Dimension(800, 740);
    private JLabel lblTitulo = new JLabel("Título");
    private JTextField txtTitulo = new JTextField();
    private JLabel lblAño = new JLabel("Año");
    private JLabel lblProductora = new JLabel("Productora");
    private JComboBox cbxProductora = new JComboBox();
    private JSpinner spiAño = new JSpinner();
    private JLabel lblSinopsis = new JLabel("Sinopsis");
    private JTextArea txaSinopsis = new JTextArea();
    private JLabel lblValoracion = new JLabel("Valoración");
    private JSlider sldValoracion = new JSlider();
    private JLabel lblIdiomas = new JLabel("Idiomas");
    private JCheckBox cbkIngles = new JCheckBox("Inglés");
    private JCheckBox cbkCastellano = new JCheckBox("Castellano");
    private JCheckBox cbkFrances = new JCheckBox("Francés");
    private JLabel lblRating = new JLabel("Rating");
    private JRadioButton rbtG = new JRadioButton("G");
    private JRadioButton rbtPG = new JRadioButton("PG");
    private JRadioButton rbtPG13 = new JRadioButton("PG-13");
    private JRadioButton rbtR = new JRadioButton("R");
    private JRadioButton rbtNC17 = new JRadioButton("NC-17");
    private JLabel lblNacionalidad = new JLabel("Nacionalidad");
    private Pais[] paises = {
        new Pais("EEUU", "us.png"),
        new Pais("Inglaterra", "gb.png"),
        new Pais("Francia", "fr.png"),
        new Pais("España", "es.png"),
        new Pais("Alemania", "de.png"),
        new Pais("Italia", "it.png"),};
    private Productora[] productoras = {
        new Productora("Warner Bross", "warner.png"),
        new Productora("Paramount", "paramount.png"),
        new Productora("MGM", "mgm.jfif"),
        new Productora("Universal", "universal.png")
    };
    private String[] titulos = {
        "Título", "Productora", "Año", "País", "Valoración", "Rating"
    };
    private JList lstPais = new JList();
    private JLabel lblImagen = new JLabel();
    private JLabel lblInteresar = new JLabel("Películas Similares");
    private JTable tblInteresar = new JTable();
    private DefaultMutableTreeNode dtmRaiz
            = new DefaultMutableTreeNode("Géneros");
    private JTree treGeneroPeliculas = new JTree(dtmRaiz);
    private JButton btnVer = new JButton("Ver");
    private JButton btnSalir = new JButton("Salir");
    private JPanel pnlDatos = new JPanel();
    private JPanel pnlBotones = new JPanel(new FlowLayout());
    private GridBagLayout gbl = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();
    private JPanel pnlIdiomas = new JPanel();
    private JPanel pnlRating = new JPanel();
    private ButtonGroup bgrRating = new ButtonGroup();
    private PeliculaService service = new PeliculaService();
    private List<Pelicula> peliculas = service.getPeliculas();
    private Object[][] datosPeliculas;

    public PeliculasForm() {
        Dimension dmPantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dmPantalla.width - dmVentana.width) / 2;
        int y = (dmPantalla.height - dmVentana.height) / 2;
        this.setTitle("Películas");
        this.setSize(dmVentana);
        this.setLocation(x, y);

        pnlDatos.setLayout(gbl);
        pnlDatos.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        //gbc.ipadx = 10;
        //gbc.ipady = 10;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 10;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        treGeneroPeliculas.setMinimumSize(new Dimension(50, 100));
        gbc.weightx = 0.5;

        cargarGeneros();
        treGeneroPeliculas.expandRow(0);
        treGeneroPeliculas.expandRow(1);
        treGeneroPeliculas.setSelectionRow(2);
        treGeneroPeliculas.setCellRenderer(new TreeCellRenderer() {
            @Override
            public Component getTreeCellRendererComponent(
                    JTree tree, 
                    Object value, 
                    boolean selected, 
                    boolean expanded, 
                    boolean leaf, 
                    int row, 
                    boolean hasFocus) {
                JLabel lbl = new JLabel();
                lbl.setText(value.toString());
                if (row == 0){
                    Icon icon = IconFontSwing.buildIcon(FontAwesome.LIST_UL, 14);
                    lbl.setIcon(icon);
                }
                DefaultMutableTreeNode dtm = (DefaultMutableTreeNode) value;
                if (dtm.getUserObject() instanceof Pelicula){
                    Pelicula p = (Pelicula) dtm.getUserObject();
                    ImageIcon im = new ImageIcon("imagenes/" + p.getImagen());
                    Image imag = im.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH);
                    im.setImage(imag);
                    lbl.setIcon(im);
                }
                lbl.setOpaque(true);
                if (selected){
                    lbl.setBackground(lstPais.getSelectionBackground());
                } else {
                    lbl.setBackground(tree.getBackground());
                }
                return lbl;
            }
        });
        treGeneroPeliculas.getSelectionModel().addTreeSelectionListener(
                new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                TreePath tp = e.getPath();
                if (tp.getPathCount() == 3){
                    DefaultMutableTreeNode dtm = (DefaultMutableTreeNode) tp.getLastPathComponent();
                    Pelicula p = (Pelicula) dtm.getUserObject();
                    mostrar(p);
                }
            }
        }
        );

        JScrollPane jspGeneros = new JScrollPane(treGeneroPeliculas);
        jspGeneros.setMinimumSize(new Dimension(200, 100));
        pnlDatos.add(jspGeneros, gbc);
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0;

        gbc.gridx = 1;
        gbc.gridy = 0;
        pnlDatos.add(lblTitulo, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        //txtTitulo.setMinimumSize(new Dimension(500,100));
        gbc.gridwidth = 3;
        pnlDatos.add(txtTitulo, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 1;
        gbc.gridy = 1;
        pnlDatos.add(lblAño, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 0.5;

        /*GregorianCalendar gc = new GregorianCalendar();
        Date hoy = gc.getTime();
        gc.add(Calendar.YEAR, -25);
        Date inicial = gc.getTime();
        gc.add(Calendar.YEAR, 50);
        Date ffinal = gc.getTime();
        SpinnerModel spiModel = new SpinnerDateModel(hoy,inicial,ffinal,Calendar.DATE);
        spiAño.setModel(spiModel);
        spiAño.setEditor(new JSpinner.DateEditor(spiAño,"dd/MM/yyyy"));*/
 /*String[] colores = {"Rojo", "Verde", "Azul", "Amarillo", "Cyan"};
        SpinnerModel spiModel = new SpinnerListModel(colores);*/
        int actual = new GregorianCalendar().get(Calendar.YEAR);
        SpinnerModel spiModel = new SpinnerNumberModel(actual,
                actual - 100,
                actual,
                1);
        spiAño.setModel(spiModel);
        pnlDatos.add(spiAño, gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.weightx = 0;
        //gbc.anchor = GridBagConstraints.SOUTHWEST;
        pnlDatos.add(lblProductora, gbc);
        //gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.weightx = 1;

        for (Productora p : productoras) {
            cbxProductora.addItem(p);
        }
        //cbxProductora.setSelectedItem(new Productora("universal",""));
        //cbxProductora.setSelectedIndex(3);
        //cbxProductora.setSelectedItem(productoras[3]);
        /*cbxProductora.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                JOptionPane.showMessageDialog(PeliculasForm.this, 
                    "Estado : " + e.getStateChange() +
                    "\nObjeto : " + e.getItem());
            }
        });*/
        cbxProductora.setRenderer(new ProductoraRenderer());
        /*cbxProductora.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Productora p = (Productora) cbxProductora.getSelectedItem();
                JOptionPane.showMessageDialog(null, 
                    p.getNombre() + " : " + p.getImagen());
            }
        });*/
        pnlDatos.add(cbxProductora, gbc);
        gbc.weightx = 0;

        gbc.gridx = 1;
        gbc.gridy = 2;
        pnlDatos.add(lblSinopsis, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        gbc.weighty = 0.25;
        gbc.fill = GridBagConstraints.BOTH;
        txaSinopsis.setLineWrap(true);
        JScrollPane jspSinopsis = new JScrollPane(txaSinopsis);
        pnlDatos.add(jspSinopsis, gbc);
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;

        gbc.gridx = 1;
        gbc.gridy = 4;
        pnlDatos.add(lblValoracion, gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        //sldValoracion.setPaintTrack(false);
        sldValoracion.setPaintLabels(true);
        /*sldValoracion.setPaintTicks(true);
        sldValoracion.setMajorTickSpacing(25);
        sldValoracion.setMinorTickSpacing(5);
        sldValoracion.setSnapToTicks(true);*/
        sldValoracion.setMajorTickSpacing(1);
        sldValoracion.setPaintTicks(true);
        sldValoracion.setMinimum(1);
        sldValoracion.setMaximum(5);
        sldValoracion.setValue(3);
        Hashtable<Integer, JLabel> etiquetas = new Hashtable<Integer, JLabel>();
        Icon icon = IconFontSwing.buildIcon(FontAwesome.STAR, 18, Color.YELLOW);
        etiquetas.put(1, new JLabel(icon));
        etiquetas.put(2, new JLabel(icon));
        etiquetas.put(3, new JLabel(icon));
        etiquetas.put(4, new JLabel(icon));
        etiquetas.put(5, new JLabel(icon));
        /*sldValoracion.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (!sldValoracion.getValueIsAdjusting()){
                    JOptionPane.showMessageDialog(null, 
                        sldValoracion.getValue());
                }
            }
        });*/
        sldValoracion.setLabelTable(etiquetas);
        pnlDatos.add(sldValoracion, gbc);
        gbc.gridwidth = 0;

        /*gbc.gridx = 1;
        gbc.gridy = 5;
        pnlDatos.add(lblIdiomas,gbc);*/
        pnlIdiomas.add(cbkIngles);
        pnlIdiomas.add(cbkCastellano);
        pnlIdiomas.add(cbkFrances);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        pnlIdiomas.setBorder(BorderFactory.createTitledBorder("Idiomas"));
        pnlDatos.add(pnlIdiomas, gbc);
        gbc.gridwidth = 0;
        /*pnlDatos.add(cbkIngles, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 5;
        pnlDatos.add(cbkCastellano, gbc);
        
        gbc.gridx = 4;
        gbc.gridy = 5;
        pnlDatos.add(cbkFrances, gbc);*/

 /*gbc.gridx = 1;
        gbc.gridy = 6;
        pnlDatos.add(lblRating, gbc);*/
 /*rbtG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Action");
            }
        });*/
        bgrRating.add(rbtG);
        bgrRating.add(rbtNC17);
        bgrRating.add(rbtPG);
        bgrRating.add(rbtPG13);
        bgrRating.add(rbtR);

        pnlRating.add(rbtG);
        pnlRating.add(rbtPG13);
        rbtPG13.setSelected(true);
        pnlRating.add(rbtPG);
        pnlRating.add(rbtNC17);
        pnlRating.add(rbtR);

        gbc.gridx = 3;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        pnlRating.setBorder(BorderFactory.createTitledBorder("Rating"));
        pnlDatos.add(pnlRating, gbc);
        gbc.gridwidth = 0;
        /*pnlDatos.add(rbtG, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 6;
        pnlDatos.add(rbtPG13, gbc);
        
        gbc.gridx = 4;
        gbc.gridy = 6;
        pnlDatos.add(rbtPG, gbc);
        
        gbc.gridx = 5;
        gbc.gridy = 6;
        pnlDatos.add(rbtNC17, gbc);
        
        gbc.gridx = 6;
        gbc.gridy = 6;
        pnlDatos.add(rbtR, gbc);
        gbc.weightx = 0;*/

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        pnlDatos.add(lblNacionalidad, gbc);
        //lstPais.setMinimumSize(new Dimension(50,50));

        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.BOTH;
        //lstPais.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        //lstPais.setVisibleRowCount(-1);
        lstPais.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        DefaultListModel dlm = new DefaultListModel();
        for (Pais p : paises) {
            dlm.add(0, p);
        }
        lstPais.setModel(dlm);
        lstPais.setCellRenderer(new ListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList list,
                    Object value,
                    int index,
                    boolean isSelected,
                    boolean cellHasFocus) {
                Pais p = (Pais) value;
                ImageIcon imIcon = new ImageIcon("imagenes/" + p.getImagen());
                Image im = imIcon.getImage().getScaledInstance(30, 20, Image.SCALE_SMOOTH);
                imIcon.setImage(im);
                JLabel lbl = new JLabel(
                        p.getNombre(),
                        imIcon,
                        JLabel.LEFT);
                lbl.setOpaque(true);
                if (isSelected) {
                    lbl.setBackground(list.getSelectionBackground());
                }
                lbl.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
                return lbl;
            }
        });
        //lstPais.setSelectedIndices(new int[] {0,2,3});
        /*lstPais.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                JOptionPane.showMessageDialog(null, e.getFirstIndex()
                 + "-" + e.getLastIndex());
            }
        });*/

        JScrollPane jspPais = new JScrollPane(lstPais);
        //jspPais.setPreferredSize(new Dimension(60,100));
        gbc.weighty = 0.5;
        pnlDatos.add(jspPais, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;

        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.anchor = GridBagConstraints.NORTH;
        pnlDatos.add(lblImagen, gbc);
        gbc.gridheight = 1;

        gbc.gridx = 1;
        gbc.gridy = 8;
        pnlDatos.add(lblInteresar, gbc);

        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.gridwidth = 5;
        //tblInteresar.setMinimumSize(new Dimension(100,50));
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.5;
        //tblInteresar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        /*tblInteresar.getModel().addTableModelListener(new TableModelListener(){
            @Override
            public void tableChanged(TableModelEvent e) {              
            }
        });*/
 /*tblInteresar.getSelectionModel().addListSelectionListener(
            new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()){
                    JOptionPane.showMessageDialog(null, 
                        "Seleccionada : " + e.getFirstIndex());
                }
            }
        });*/

        JScrollPane jspInteresar = new JScrollPane(tblInteresar);
        //jspInteresar.setPreferredSize(new Dimension(60,100));
        pnlDatos.add(jspInteresar, gbc);
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;

        btnVer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ver();
            }
        });
        pnlBotones.add(btnVer);
        pnlBotones.add(btnSalir);

        this.add(pnlDatos, BorderLayout.CENTER);
        this.add(pnlBotones, BorderLayout.SOUTH);

        mostrar(peliculas.get(0));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void ver(){
        String listado = "";
        // Árbol
        TreePath[] paths = treGeneroPeliculas.getSelectionPaths();
        if (paths == null){
            listado = "Árbol : Sin selección";
        } else {
            listado += "Árbol : Selecciones";
            for(TreePath tp : paths){
                listado +="\n\t";
                for(int i=0; i<tp.getPathCount(); i++){
                    DefaultMutableTreeNode dtm = (DefaultMutableTreeNode) tp.getPathComponent(i);
                    listado += dtm.getUserObject().toString() + " - ";
                }
            }
        }
        // TextField
        listado += "\nTítulo : " + txtTitulo.getText();
        
        // Spinner Numérico
        Integer año = (Integer) spiAño.getValue();
        listado += "\nAño : " + año;
        
        // JComboBox
        Productora p = (Productora) cbxProductora.getSelectedItem();
        //p = productoras[cbxProductora.getSelectedIndex()];
        listado += "\nProductora : " + p.getNombre();
        
        //JTextArea
        listado += "\nSinopsis : " + txaSinopsis.getText().substring(0, 30) + "...";
                
        //Slider
        listado += "\nValoración : " + sldValoracion.getValue();
        
        //CheckBoxes. Mirar uno a uno
        String idiomas = "";
        if (cbkCastellano.isSelected()){
            idiomas += "Castellano, ";
        }
        if (cbkFrances.isSelected()){
            idiomas += "Francés, ";
        }
        if (cbkIngles.isSelected()){
            idiomas += "Inglés, ";
        }
        if (idiomas.isEmpty()){
            idiomas = "Ninguno";
        } else {
            idiomas = idiomas.substring(0, idiomas.length()-2);
        }
        listado += "\nIdiomas : " + idiomas;
        
        // RadioButton
        String rating = "";
        if (rbtG.isSelected()){
            rating = "G";
        } else if (rbtNC17.isSelected()){
            rating = "NC17";
        } else if (rbtPG.isSelected()){
            rating = "PG";
        } else if (rbtPG13.isSelected()){
            rating = "PG13";
        } else {
            rating = "R"; 
        }
        listado += "\nRating : " + rating;
        
        // Lista
        /*int[] sel = lstPais.getSelectedIndices();
        String paiss = "\nPaises :";
        for(int i=0; i<sel.length; i++){
            paiss += "    " + paises[i].getNombre() + ", ";
        }*/
        List<Pais> sel = lstPais.getSelectedValuesList();
        String paiss = "\nPaises :";
        for(int i=0; i<sel.size(); i++){
            paiss += "    " + sel.get(i).getNombre() + ", ";
        }
        if (sel.size() == 0){
            paiss = "\nPaises : Ninguno";
        }
        listado += paiss;
        
        //Table
        int[] seleccionadas = tblInteresar.getSelectedRows();
        if (seleccionadas.length == 0){
            listado += "\nPeliculas Recomendadas : Sin selección";
        } else {
            listado += "\nPeliculas Recomendadas Seleccionadas : ";
            for(int i=0; i<seleccionadas.length; i++){
                listado += "\n   " + datosPeliculas[seleccionadas[i]][0] ;
            }
        }
        
        JOptionPane.showMessageDialog(this, listado);
    }
    
    public static void main(String[] args) {
        IconFontSwing.register(FontAwesome.getIconFont());
        new PeliculasForm();
    }

    private void cargarGeneros() {
        for (String genero : service.getGeneros()) {
            DefaultMutableTreeNode dtmGenero
                    = new DefaultMutableTreeNode(genero);
            for (Pelicula p : service.getPeliculasGenero(genero)) {
                DefaultMutableTreeNode dtmPeli
                        = new DefaultMutableTreeNode(p);
                dtmGenero.add(dtmPeli);
            }
            dtmRaiz.add(dtmGenero);
        }
    }

    class ProductoraRenderer implements ListCellRenderer<Productora> {

        @Override
        public Component getListCellRendererComponent(
                JList<? extends Productora> list,
                Productora value,
                int index,
                boolean isSelected,
                boolean cellHasFocus) {
            JLabel lbl = new JLabel();
            lbl.setOpaque(true);
            lbl.setText(value.getNombre());
            ImageIcon imIc = new ImageIcon("imagenes/" + value.getImagen());
            Image im = imIc.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            imIc.setImage(im);
            lbl.setIcon(imIc);
            if (isSelected) {
                lbl.setBackground(list.getSelectionBackground());
            }
            return lbl;
        }

    }

    private void mostrar(Pelicula p) {
        txtTitulo.setText(p.getTitulo());
        spiAño.setValue(p.getAño());
        Productora prod = new Productora();
        prod.setNombre(p.getProductora());
        txaSinopsis.setText(p.getSinopsis());
        cbxProductora.setSelectedItem(prod);
        ImageIcon im = new ImageIcon("imagenes/" + p.getImagen());
        Image imag = im.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        im.setImage(imag);
        lblImagen.setIcon(im);
        /*int i;
        boolean  salir = false;
        for(i=0;i<cbxProductora.getItemCount() && !salir;i++){
            Productora pr = (Productora) cbxProductora.getItemAt(i);
            if (pr.getNombre().equalsIgnoreCase(p.getProductora())){
                salir = true;
            }
        }
        int i;
        for(i=0; i<productoras.length && 
            !p.getProductora().equalsIgnoreCase(
                productoras[i].getNombre());
            i++);
        cbxProductora.setSelectedIndex(i);*/
        sldValoracion.setValue(p.getValoracion());
        for (String id : p.getIdiomas()) {
            switch (id.toLowerCase()) {
                case "castellano":
                    cbkCastellano.setSelected(true);
                    break;
                case "inglés":
                    cbkIngles.setSelected(true);
                    break;
                case "francés":
                    cbkFrances.setSelected(true);
                    break;
            }
        }
        switch (p.getRating()) {
            case "G":
                rbtG.setSelected(true);
                break;
            case "PG-13":
                rbtPG13.setSelected(true);
                break;
            case "PG":
                rbtPG.setSelected(true);
                break;
            case "NC-17":
                rbtNC17.setSelected(true);
                break;
            case "R":
                rbtR.setSelected(true);
                break;
        }
        Pais pais = new Pais();
        pais.setNombre(p.getPais());
        lstPais.setSelectedValue(pais, true);

        // Películas Recomendadas
        datosPeliculas = new Object[p.getPuedeInteresar().size()][6];
        for (int i = 0; i < p.getPuedeInteresar().size(); i++) {
            Pelicula peli = p.getPuedeInteresar().get(i);
            datosPeliculas[i][0] = peli.getTitulo();
            datosPeliculas[i][1] = peli.getProductora();
            datosPeliculas[i][2] = peli.getAño();
            datosPeliculas[i][3] = peli.getPais();
            datosPeliculas[i][4] = peli.getValoracion();
            datosPeliculas[i][5] = peli.getRating();
        }
        //tblInteresar = new JTable(datosPeliculas, titulos)
        DefaultTableModel dtm = new DefaultTableModel(datosPeliculas, titulos);
        tblInteresar.setModel(dtm);
        tblInteresar.getColumnModel().getColumn(1)
                .setCellRenderer(new TableCellRenderer() {
                    @Override
                    public Component getTableCellRendererComponent(
                            JTable table,
                            Object value,
                            boolean isSelected,
                            boolean hasFocus,
                            int row,
                            int column) {
                        String pr = (String) value;
                        int i;
                        for (i = 0; i < productoras.length
                                && !pr.equalsIgnoreCase(productoras[i].getNombre());
                                i++);
                        return new JLabel(
                                new ImageIcon("imagenes/" + productoras[i].getImagen()));
                    }
                });
    }

}
