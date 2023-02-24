/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package happycoloring;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFrame;

/**
 *
 * @author Tobiasz Stamborski <tstamborski@outlook.com>
 */
public class HappyColoring extends JFrame implements HappyI18n {

    private HappyMenuBar menu;
    private HappyStatusBar status;
    private AboutDialog aboutDialog;
    private HappyLoadDialog loadDialog;
    private final HappyScrollPane scrollPane;
    private HappyPaletteToolBar paletteToolbar;
    private HappyShortcutToolBar shortcutToolbar;
    private ColoringPageList pagesList;
    
    private DrawingTool currentDrawingTool;
    private BufferedImage circlePattern, squarePattern, diamondPattern, starPattern, softPattern;
    private Pencil pencil, softbrush;
    private Rubber rubber;
    private PaintBucket bucket;
    
    private ResourceBundle i18n;
    
    private void loadi18n(Locale locale) {
        i18n = ResourceBundle.getBundle("happycoloring.i18n.i18n", locale);
        loadi18n(i18n);
    }
    
    @Override
    public void loadi18n(ResourceBundle rb) {
        setTitle(rb.getString("ApplicationName"));
        menu.loadi18n(rb);
        aboutDialog.loadi18n(rb);
        loadDialog.loadi18n(rb);
        paletteToolbar.loadi18n(rb);
        shortcutToolbar.loadi18n(rb);
    }
    
    public HappyColoring() {
        createDialogs();
        createMenu();
        createToolbars();
        createStatusbar();
        scrollPane = new HappyScrollPane();
        add(scrollPane);
        
        pagesList = new ColoringPageList();
        createDrawingTools();
        
        setIconImage(new ImageIcon(getClass().getResource("icons/coloring64.png")).getImage());
        setSize(800, 600);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        enableEvents(WindowEvent.WINDOW_EVENT_MASK);
        
        loadi18n(Locale.getDefault());
        updateGUI();
    }
    
    protected void setCurrentColor(Color c) {
        currentDrawingTool.setColor(c);
    }
    
    protected void setCustomColor() {
        Color c = JColorChooser.showDialog(this, i18n.getString("ColorChooserDialog"), currentDrawingTool.getColor());
        if (c != null)
            setCurrentColor(c);
    }
    
    private void createDialogs() {
        aboutDialog = new AboutDialog(this);
        aboutDialog.setApplicationIcon(new ImageIcon(getClass().getResource("icons/coloring64.png")));
        try {
            aboutDialog.setApplicationLicense(getClass().getResourceAsStream("LICENSE"));
        } catch (IOException ex) {
            Logger.getLogger(HappyColoring.class.getName()).log(Level.SEVERE, ex.getMessage(), this);
        }
        
        loadDialog = new HappyLoadDialog(new File(System.getProperty("user.home")));
    }
    
    private void createMenu() {
        menu = new HappyMenuBar();
        
        menu.getAboutItem().addActionListener((ae)->{aboutDialog.setVisible(true);});
        
        menu.getSizeItems().forEach(i -> i.addActionListener(ae -> setCurrentToolSize(i.getIntValue())));
        menu.getSizeItems().get(3).setSelected(true);
        menu.getZoomItems().forEach(i -> i.addActionListener(ae -> setCurrentZoom(i.getDoubleValue())));
        menu.getZoomItems().get(1).setSelected(true);
        
        menu.getColorItems().forEach(cb -> cb.addActionListener(ae->setCurrentColor(cb.getColor())));
        menu.getCustomColorItem().addActionListener((ae)->setCustomColor());
        
        menu.getCirclePencilItem().addActionListener((ae)->{pencil.setShape(circlePattern); setCurrentTool(pencil);});
        menu.getSquarePencilItem().addActionListener((ae)->{pencil.setShape(squarePattern); setCurrentTool(pencil);});
        menu.getDiamondPencilItem().addActionListener((ae)->{pencil.setShape(diamondPattern); setCurrentTool(pencil);});
        menu.getStarPencilItem().addActionListener((ae)->{pencil.setShape(starPattern); setCurrentTool(pencil);});
        menu.getCircleRubberItem().addActionListener((ae)->{rubber.setShape(circlePattern); setCurrentTool(rubber);});
        menu.getSquareRubberItem().addActionListener((ae)->{rubber.setShape(squarePattern); setCurrentTool(rubber);});
        menu.getDiamondRubberItem().addActionListener((ae)->{rubber.setShape(diamondPattern); setCurrentTool(rubber);});
        menu.getStarRubberItem().addActionListener((ae)->{rubber.setShape(starPattern); setCurrentTool(rubber);});
        menu.getSoftbrushItem().addActionListener((ae)->setCurrentTool(softbrush));
        menu.getBucketItem().addActionListener(ae -> setCurrentTool(bucket));
        
        menu.getNextItem().addActionListener(ae->nextPage());
        menu.getPreviousItem().addActionListener(ae->previousPage());
        menu.getLoadItem().addActionListener((ae)->load());
        menu.getExitItem().addActionListener((ae)->{dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));});
        
        setJMenuBar(menu);
    }
    
    private void createToolbars() {
        paletteToolbar = new HappyPaletteToolBar();
        paletteToolbar.getColorButtons().forEach(cb -> cb.addActionListener(
                (ae)->{setCurrentColor(((ColorButton)ae.getSource()).getColor());}
        ));
        paletteToolbar.getPaletteButton().addActionListener(
                (ae)->{setCustomColor();}
        );
        
        shortcutToolbar = new HappyShortcutToolBar();
        shortcutToolbar.getNextButton().addActionListener(ae -> nextPage());
        shortcutToolbar.getPreviousButton().addActionListener(ae -> previousPage());
        shortcutToolbar.getPencilButton().addActionListener(ae -> setCurrentTool(pencil));
        shortcutToolbar.getRubberButton().addActionListener(ae -> setCurrentTool(rubber));
        shortcutToolbar.getSprayButton().addActionListener(ae -> setCurrentTool(null));
        shortcutToolbar.getSoftbrushButton().addActionListener(ae -> setCurrentTool(softbrush));
        shortcutToolbar.getBucketButton().addActionListener(ae -> setCurrentTool(bucket));
        shortcutToolbar.getSizeButtons().forEach(b -> b.addActionListener(ae -> setCurrentToolSize(b.getIntValue())));
        
        add(paletteToolbar, BorderLayout.EAST);
        add(shortcutToolbar, BorderLayout.WEST);
    }
    
    private void createStatusbar() {
        status = new HappyStatusBar();
        add(status, BorderLayout.SOUTH);
    }
    
    private void createDrawingTools() {
        try {
            circlePattern = ImageIO.read(getClass().getResourceAsStream("patterns/circle16.png"));
            squarePattern = ImageIO.read(getClass().getResourceAsStream("patterns/square16.png"));
            diamondPattern = ImageIO.read(getClass().getResourceAsStream("patterns/diamond16.png"));
            starPattern = ImageIO.read(getClass().getResourceAsStream("patterns/star16.png"));
            softPattern = ImageIO.read(getClass().getResourceAsStream("patterns/soft16.png"));
        } catch (IOException ex) {
            Logger.getLogger(HappyColoring.class.getName()).log(Level.SEVERE, ex.getMessage(), this);
        }
        
        pencil = new Pencil(circlePattern, HappyPalette.getInstance().get(0).getColor(), DrawingTool.SIZE_BIG);
        rubber = new Rubber(circlePattern, DrawingTool.SIZE_BIG);
        softbrush = new Pencil(softPattern, HappyPalette.getInstance().get(0).getColor(), DrawingTool.SIZE_BIG);
        bucket = new PaintBucket(HappyPalette.getInstance().get(0).getColor());
        
        pencil.setChangeListener(ce -> status.setDisplayedColor(pencil.getColor()));
        softbrush.setChangeListener(ce -> status.setDisplayedColor(softbrush.getColor()));
        bucket.setChangeListener(ce -> status.setDisplayedColor(bucket.getColor()));
        
        currentDrawingTool = pencil;
    }
    
    public static void main(String[] args) {
        HappyColoring hc = new HappyColoring();
        EventQueue.invokeLater(() -> {
            hc.setVisible(true);
        });
    }
    
    private void updateGUI() {
        boolean isFilesOpened = !pagesList.isEmpty();
        menu.getSaveAsItem().setEnabled(isFilesOpened);
        menu.getZoomMenu().setEnabled(isFilesOpened);
        menu.getClearAllItem().setEnabled(isFilesOpened);
        menu.getClearItem().setEnabled(isFilesOpened);
        
        menu.getUndoItem().setEnabled(false);
        shortcutToolbar.getUndoButton().setEnabled(false);
        menu.getRedoItem().setEnabled(false);
        shortcutToolbar.getRedoButton().setEnabled(false);
        
        menu.getNextItem().setEnabled(pagesList.isNext());
        shortcutToolbar.getNextButton().setEnabled(pagesList.isNext());
        menu.getPreviousItem().setEnabled(pagesList.isPrevious());
        shortcutToolbar.getPreviousButton().setEnabled(pagesList.isPrevious());

        if (currentDrawingTool == pencil)
            shortcutToolbar.getPencilButton().setSelected(true);
        else if (currentDrawingTool == rubber)
            shortcutToolbar.getRubberButton().setSelected(true);
        else if (currentDrawingTool == softbrush)
            shortcutToolbar.getSoftbrushButton().setSelected(true);
        else if (currentDrawingTool == bucket)
            shortcutToolbar.getBucketButton().setSelected(true);
        
        if (currentDrawingTool == bucket) {
            menu.getSizeMenu().setEnabled(false);
            shortcutToolbar.getSizeButtons().forEach(b -> b.setEnabled(false));
        }
        else {
            menu.getSizeMenu().setEnabled(true);
            shortcutToolbar.getSizeButtons().forEach(b -> b.setEnabled(true));
        }
        
        status.setDisplayedColor(currentDrawingTool.getColor());
        if (pagesList.isEmpty())
                status.setDisplayedCoord(0, 0);
        
        menu.getSizeItems().forEach(i -> i.setSelected(i.getIntValue() == currentDrawingTool.getSize()));
        shortcutToolbar.getSizeButtons().forEach(b -> b.setSelected(b.getIntValue() == currentDrawingTool.getSize()));
        
        if (isFilesOpened) {
            menu.getZoomItems().forEach(i -> i.setSelected(i.getDoubleValue() == pagesList.getCurrent().getZoom()));
            status.setDisplayedInfo(pagesList);
        }
    }

    protected void load() {
        if (loadDialog.showOpenDialog(this) == HappyLoadDialog.CANCEL_OPTION)
            return;
        
        try {
            pagesList = ColoringPageList.fromFileArray(loadDialog.getSelectedFiles());
        } catch (IOException ex) {
            Logger.getLogger(HappyColoring.class.getName()).log(Level.WARNING, ex.getMessage(), this);
            Util.showError(this, ex.getLocalizedMessage());
        }
        
        pagesList.forEach(cp -> cp.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                status.setDisplayedCoord((int)(e.getX()/cp.getZoom()), (int)(e.getY()/cp.getZoom()));
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                status.setDisplayedCoord((int)(e.getX()/cp.getZoom()), (int)(e.getY()/cp.getZoom()));
            }
        }));
        
        setCurrentTool(currentDrawingTool);
        setCurrentPage(pagesList.getCurrent());
    }
    
    protected void nextPage() {
        setCurrentPage(pagesList.next());
    }
    
    protected void previousPage() {
        setCurrentPage(pagesList.previous());
    }
    
    protected void setCurrentPage(ColoringPage page) {
        if (page != null)
            scrollPane.setViewportView(page);
        updateGUI();
    }
    
    protected void setCurrentTool(DrawingTool tool) {
        currentDrawingTool = tool;
        pagesList.forEach(i -> i.setDrawingTool(tool));
        updateGUI();
    }
    
    protected void setCurrentToolSize(int s) {
        currentDrawingTool.setSize(s);
        updateGUI();
    }

    protected void setCurrentZoom(double d) {
        if (!pagesList.isEmpty())
            pagesList.getCurrent().setZoom(d);
        updateGUI();
    }
}
