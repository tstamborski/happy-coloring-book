/*
 * The MIT License
 *
 * Copyright 2023 Tobiasz Stamborski <tstamborski@outlook.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package happycoloring;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 *
 * @author Tobiasz Stamborski <tstamborski@outlook.com>
 */
public class HappyMenuBar extends JMenuBar implements HappyI18n {
    private final JMenu fileMenu, editMenu, toolMenu, paletteMenu, optionsMenu, helpMenu;
    private final JMenu pencilsMenu, rubbersMenu;
    private final JMenu sizeMenu, zoomMenu, languageMenu;
    private final ButtonGroup sizeGroup, zoomGroup;
    private final JMenuItem loadItem, nextItem, previousItem, saveAsItem, exitItem;
    private final JMenuItem undoItem, redoItem;
    private final JMenuItem circlePencilItem, squarePencilItem, diamondPencilItem, starPencilItem;
    private final JMenuItem circleRubberItem, squareRubberItem, diamondRubberItem, starRubberItem;
    private final JMenuItem sprayItem, softbrushItem, bucketItem; 
    private final ArrayList<ColorMenuItem> colorItems;
    private final JMenuItem customColorItem;
    private final JMenuItem polishItem, englishItem;
    private final JMenuItem clearItem, clearAllItem;
    private final ArrayList<NumberRadioButtonMenuItem> sizeItems;
    private final ArrayList<NumberRadioButtonMenuItem> zoomItems;
    private final JMenuItem aboutItem;
    
    @Override
    public void loadi18n(ResourceBundle rb) {
        fileMenu.setText(rb.getString("FileMenu"));
        exitItem.setText(rb.getString("ExitMenuItem"));
        
        for (int i = 0; i < colorItems.size(); i++)
            colorItems.get(i).setText(rb.getString(HappyPalette.getInstance().get(i).getName()));
        
        helpMenu.setText(rb.getString("HelpMenu"));
        aboutItem.setText(rb.getString("AboutMenuItem"));
        
    }
    
    public HappyMenuBar() {
        nextItem = new JMenuItem("Next Page");
        nextItem.setIcon(new ImageIcon(getClass().getResource("icons/arrow-right16.png")));
        nextItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, KeyEvent.CTRL_DOWN_MASK));
        previousItem = new JMenuItem("Previous Page");
        previousItem.setIcon(new ImageIcon(getClass().getResource("icons/arrow-left16.png")));
        previousItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, KeyEvent.CTRL_DOWN_MASK));
        loadItem = new JMenuItem("Load Coloring Pages...");
        loadItem.setIcon(new ImageIcon(getClass().getResource("icons/openfile16.png")));
        loadItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        saveAsItem = new JMenuItem("Save As Image...");
        saveAsItem.setIcon(new ImageIcon(getClass().getResource("icons/savefile16.png")));
        saveAsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        exitItem = new JMenuItem("Exit");
        exitItem.setIcon(new ImageIcon(getClass().getResource("icons/exit16.png")));
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_DOWN_MASK));
        
        undoItem = new JMenuItem("Undo", new ImageIcon(getClass().getResource("icons/undo16.png")));
        undoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
        redoItem = new JMenuItem("Redo", new ImageIcon(getClass().getResource("icons/redo16.png")));
        redoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK));
        
        circlePencilItem = new JMenuItem("Circle", new ImageIcon(getClass().getResource("icons/circle16.png")));
        squarePencilItem = new JMenuItem("Square", new ImageIcon(getClass().getResource("icons/square16.png")));
        diamondPencilItem = new JMenuItem("Diamond", new ImageIcon(getClass().getResource("icons/diamond16.png")));
        starPencilItem = new JMenuItem("Star", new ImageIcon(getClass().getResource("icons/star16.png")));
        circleRubberItem = new JMenuItem("Circle", new ImageIcon(getClass().getResource("icons/circle16.png")));
        squareRubberItem = new JMenuItem("Square", new ImageIcon(getClass().getResource("icons/square16.png")));
        diamondRubberItem = new JMenuItem("Diamond", new ImageIcon(getClass().getResource("icons/diamond16.png")));
        starRubberItem = new JMenuItem("Star", new ImageIcon(getClass().getResource("icons/star16.png")));
        sprayItem = new  JMenuItem("Spray", new ImageIcon(getClass().getResource("icons/spray16.png")));
        softbrushItem = new  JMenuItem("Soft Brush", new ImageIcon(getClass().getResource("icons/flat-brush16.png")));
        bucketItem = new  JMenuItem("Intelligent Bucket", new ImageIcon(getClass().getResource("icons/paint-bucket16.png")));
        
        colorItems = new ArrayList();
        HappyPalette.getInstance().forEach(e -> colorItems.add(new ColorMenuItem(e.getColor(), e.getName())));
        customColorItem = new JMenuItem("Custom Color...");
        customColorItem.setIcon(new ImageIcon(getClass().getResource("icons/palette16.png")));
        customColorItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        
        sizeItems = new ArrayList();
        sizeItems.add(new NumberRadioButtonMenuItem("Tiny (2x2)", 2));
        sizeItems.add(new NumberRadioButtonMenuItem("Small (4x4)", 4));
        sizeItems.add(new NumberRadioButtonMenuItem("Medium (8x8)", 8));
        sizeItems.add(new NumberRadioButtonMenuItem("Big (16x16)", 16));
        sizeItems.add(new NumberRadioButtonMenuItem("Large (32x32)", 32));
        sizeItems.add(new NumberRadioButtonMenuItem("Huge (64x64)", 64));
        zoomItems = new ArrayList();
        zoomItems.add(new NumberRadioButtonMenuItem("50%", 0.5));
        zoomItems.add(new NumberRadioButtonMenuItem("100%", 1.0));
        zoomItems.add(new NumberRadioButtonMenuItem("200%", 2.0));
        zoomItems.add(new NumberRadioButtonMenuItem("400%", 4.0));
        polishItem = new JMenuItem("Polish");
        polishItem.setIcon(new ImageIcon(getClass().getResource("icons/poland16.png")));
        englishItem = new JMenuItem("English");
        englishItem.setIcon(new ImageIcon(getClass().getResource("icons/united-kingdom16.png")));
        clearItem = new JMenuItem("Clear...", new ImageIcon(getClass().getResource("icons/cross-black16.png")));
        clearAllItem = new JMenuItem("Clear All...", new ImageIcon(getClass().getResource("icons/bin16.png")));
        
        aboutItem = new JMenuItem("About...");
        aboutItem.setIcon(new ImageIcon(getClass().getResource("icons/info16.png")));
        
        pencilsMenu = new JMenu("Pencil");
        pencilsMenu.setIcon(new ImageIcon(getClass().getResource("icons/pencil16.png")));
        pencilsMenu.add(circlePencilItem);
        pencilsMenu.add(squarePencilItem);
        pencilsMenu.add(diamondPencilItem);
        pencilsMenu.add(starPencilItem);
        rubbersMenu = new JMenu("Rubber");
        rubbersMenu.setIcon(new ImageIcon(getClass().getResource("icons/rubber16.png")));
        rubbersMenu.add(circleRubberItem);
        rubbersMenu.add(squareRubberItem);
        rubbersMenu.add(diamondRubberItem);
        rubbersMenu.add(starRubberItem);
        
        sizeMenu = new JMenu("Tool Size");
        sizeMenu.setIcon(new ImageIcon(getClass().getResource("icons/ruler16.png")));
        sizeGroup = new ButtonGroup();
        sizeItems.forEach(i -> {sizeMenu.add(i);sizeGroup.add(i);});
        zoomMenu = new JMenu("Page Zoom");
        zoomMenu.setIcon(new ImageIcon(getClass().getResource("icons/find16.png")));
        zoomGroup = new ButtonGroup();
        zoomItems.forEach(i -> {zoomMenu.add(i);zoomGroup.add(i);});
        languageMenu = new JMenu("Language");
        languageMenu.setIcon(new ImageIcon(getClass().getResource("icons/rolling-stones16.png")));
        languageMenu.add(englishItem);
        languageMenu.add(polishItem);
        
        fileMenu = new JMenu("File");
        fileMenu.add(nextItem);
        fileMenu.add(previousItem);
        fileMenu.addSeparator();
        fileMenu.add(loadItem);
        fileMenu.add(saveAsItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        
        editMenu = new JMenu("Edit");
        editMenu.add(undoItem);
        editMenu.add(redoItem);
        editMenu.addSeparator();
        editMenu.add(clearItem);
        
        toolMenu = new JMenu("Tools");
        toolMenu.add(pencilsMenu);
        toolMenu.add(rubbersMenu);
        toolMenu.add(sprayItem);
        toolMenu.add(softbrushItem);
        toolMenu.add(bucketItem);
        
        paletteMenu = new JMenu("Palette");
        colorItems.forEach(mi -> paletteMenu.add(mi));
        paletteMenu.addSeparator();
        paletteMenu.add(customColorItem);
        
        optionsMenu = new JMenu("Options");
        optionsMenu.add(languageMenu);
        optionsMenu.addSeparator();
        optionsMenu.add(zoomMenu);
        optionsMenu.add(sizeMenu);
        optionsMenu.addSeparator();
        optionsMenu.add(clearAllItem);
        
        helpMenu = new JMenu("Help");
        helpMenu.add(aboutItem);
        
        add(fileMenu);
        add(editMenu);
        add(toolMenu);
        add(paletteMenu);
        add(optionsMenu);
        add(helpMenu);
    }

    public JMenu getSizeMenu() {
        return sizeMenu;
    }

    public JMenu getZoomMenu() {
        return zoomMenu;
    }

    public JMenuItem getLoadItem() {
        return loadItem;
    }

    public JMenuItem getNextItem() {
        return nextItem;
    }

    public JMenuItem getPreviousItem() {
        return previousItem;
    }

    public JMenuItem getSaveAsItem() {
        return saveAsItem;
    }

    public JMenuItem getUndoItem() {
        return undoItem;
    }

    public JMenuItem getRedoItem() {
        return redoItem;
    }

    public JMenuItem getCirclePencilItem() {
        return circlePencilItem;
    }

    public JMenuItem getSquarePencilItem() {
        return squarePencilItem;
    }

    public JMenuItem getDiamondPencilItem() {
        return diamondPencilItem;
    }

    public JMenuItem getStarPencilItem() {
        return starPencilItem;
    }

    public JMenuItem getCircleRubberItem() {
        return circleRubberItem;
    }

    public JMenuItem getSquareRubberItem() {
        return squareRubberItem;
    }

    public JMenuItem getDiamondRubberItem() {
        return diamondRubberItem;
    }

    public JMenuItem getStarRubberItem() {
        return starRubberItem;
    }

    public JMenuItem getSprayItem() {
        return sprayItem;
    }

    public JMenuItem getSoftbrushItem() {
        return softbrushItem;
    }

    public JMenuItem getBucketItem() {
        return bucketItem;
    }

    public JMenuItem getPolishItem() {
        return polishItem;
    }

    public JMenuItem getEnglishItem() {
        return englishItem;
    }

    public ArrayList<NumberRadioButtonMenuItem> getSizeItems() {
        return sizeItems;
    }

    public ArrayList<NumberRadioButtonMenuItem> getZoomItems() {
        return zoomItems;
    }

    public JMenuItem getClearItem() {
        return clearItem;
    }

    public JMenuItem getClearAllItem() {
        return clearAllItem;
    }

    public ArrayList<ColorMenuItem> getColorItems() {
        return colorItems;
    }

    public JMenuItem getCustomColorItem() {
        return customColorItem;
    }

    public JMenuItem getExitItem() {
        return exitItem;
    }

    public JMenuItem getAboutItem() {
        return aboutItem;
    }
}

