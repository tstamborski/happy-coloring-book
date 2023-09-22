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
    private final JMenuItem zoominItem, zoomoutItem;
    private final JMenuItem aboutItem;
    
    public static int SIZE_MAX = DrawingTool.SIZE_HUGE;
    public static int SIZE_MIN = DrawingTool.SIZE_TINY;
    public static int ZOOM_MAX = 400;
    public static int ZOOM_MIN = 50;
    
    @Override
    public void loadi18n(ResourceBundle rb) {
        fileMenu.setText(rb.getString("FileMenu"));
        fileMenu.setMnemonic(rb.getString("FileMenuMnemonic").charAt(0));
        nextItem.setText(rb.getString("NextMenuItem"));
        nextItem.setMnemonic(rb.getString("NextMenuItemMnemonic").charAt(0));
        previousItem.setText(rb.getString("PreviousMenuItem"));
        previousItem.setMnemonic(rb.getString("PreviousMenuItemMnemonic").charAt(0));
        loadItem.setText(rb.getString("LoadMenuItem"));
        loadItem.setMnemonic(rb.getString("LoadMenuItemMnemonic").charAt(0));
        saveAsItem.setText(rb.getString("SaveAsMenuItem"));
        saveAsItem.setMnemonic(rb.getString("SaveAsMenuItemMnemonic").charAt(0));
        exitItem.setText(rb.getString("ExitMenuItem"));
        exitItem.setMnemonic(rb.getString("ExitMenuItemMnemonic").charAt(0));
        
        editMenu.setText(rb.getString("EditMenu"));
        editMenu.setMnemonic(rb.getString("EditMenuMnemonic").charAt(0));
        undoItem.setText(rb.getString("UndoMenuItem"));
        undoItem.setMnemonic(rb.getString("UndoMenuItemMnemonic").charAt(0));
        redoItem.setText(rb.getString("RedoMenuItem"));
        redoItem.setMnemonic(rb.getString("RedoMenuItemMnemonic").charAt(0));
        clearItem.setText(rb.getString("ClearMenuItem"));
        clearItem.setMnemonic(rb.getString("ClearMenuItemMnemonic").charAt(0));
        
        toolMenu.setText(rb.getString("ToolMenu"));
        toolMenu.setMnemonic(rb.getString("ToolMenuMnemonic").charAt(0));
        pencilsMenu.setText(rb.getString("PencilsMenu"));
        pencilsMenu.setMnemonic(rb.getString("PencilsMenuMnemonic").charAt(0));
        circlePencilItem.setText(rb.getString("CircleMenuItem"));
        circlePencilItem.setMnemonic(rb.getString("CircleMenuItemMnemonic").charAt(0));
        squarePencilItem.setText(rb.getString("SquareMenuItem"));
        squarePencilItem.setMnemonic(rb.getString("SquareMenuItemMnemonic").charAt(0));
        diamondPencilItem.setText(rb.getString("DiamondMenuItem"));
        diamondPencilItem.setMnemonic(rb.getString("DiamondMenuItemMnemonic").charAt(0));
        starPencilItem.setText(rb.getString("StarMenuItem"));
        starPencilItem.setMnemonic(rb.getString("StarMenuItemMnemonic").charAt(0));
        rubbersMenu.setText(rb.getString("RubbersMenu"));
        rubbersMenu.setMnemonic(rb.getString("RubbersMenuMnemonic").charAt(0));
        circleRubberItem.setText(rb.getString("CircleMenuItem"));
        circleRubberItem.setMnemonic(rb.getString("CircleMenuItemMnemonic").charAt(0));
        squareRubberItem.setText(rb.getString("SquareMenuItem"));
        squareRubberItem.setMnemonic(rb.getString("SquareMenuItemMnemonic").charAt(0));
        diamondRubberItem.setText(rb.getString("DiamondMenuItem"));
        diamondRubberItem.setMnemonic(rb.getString("DiamondMenuItemMnemonic").charAt(0));
        starRubberItem.setText(rb.getString("StarMenuItem"));
        starRubberItem.setMnemonic(rb.getString("StarMenuItemMnemonic").charAt(0));
        sprayItem.setText(rb.getString("SprayMenuItem"));
        sprayItem.setMnemonic(rb.getString("SprayMenuItemMnemonic").charAt(0));
        softbrushItem.setText(rb.getString("SoftbrushMenuItem"));
        softbrushItem.setMnemonic(rb.getString("SoftbrushMenuItemMnemonic").charAt(0));
        bucketItem.setText(rb.getString("BucketMenuItem"));
        bucketItem.setMnemonic(rb.getString("BucketMenuItemMnemonic").charAt(0));
        
        paletteMenu.setText(rb.getString("PaletteMenu"));
        paletteMenu.setMnemonic(rb.getString("PaletteMenuMnemonic").charAt(0));
        for (int i = 0; i < colorItems.size(); i++) {
            colorItems.get(i).setText(rb.getString(HappyPalette.getInstance().get(i).getName()));
            colorItems.get(i).setMnemonic(
                    rb.getString(HappyPalette.getInstance().get(i).getName()+"Mnemonic").charAt(0)
            );
        }
        customColorItem.setText(rb.getString("CustomColorMenuItem"));
        customColorItem.setMnemonic(rb.getString("CustomColorMenuItemMnemonic").charAt(0));
        
        optionsMenu.setText(rb.getString("OptionsMenu"));
        optionsMenu.setMnemonic(rb.getString("OptionsMenuMnemonic").charAt(0));
        languageMenu.setText(rb.getString("LanguageMenu"));
        languageMenu.setMnemonic(rb.getString("LanguageMenuMnemonic").charAt(0));
        englishItem.setText(rb.getString("EnglishMenuItem"));
        englishItem.setMnemonic(rb.getString("EnglishMenuItemMnemonic").charAt(0));
        polishItem.setText(rb.getString("PolishMenuItem"));
        polishItem.setMnemonic(rb.getString("PolishMenuItemMnemonic").charAt(0));
        zoomMenu.setText(rb.getString("ZoomMenu"));
        zoomMenu.setMnemonic(rb.getString("ZoomMenuMnemonic").charAt(0));
        zoomItems.forEach(i -> i.setMnemonic(i.getText().charAt(0)));
        zoominItem.setText(rb.getString("ZoomInMenuItem"));
        zoominItem.setMnemonic(rb.getString("ZoomInMenuItemMnemonic").charAt(0));
        zoomoutItem.setText(rb.getString("ZoomOutMenuItem"));
        zoomoutItem.setMnemonic(rb.getString("ZoomOutMenuItemMnemonic").charAt(0));
        sizeMenu.setText(rb.getString("SizeMenu"));
        sizeMenu.setMnemonic(rb.getString("SizeMenuMnemonic").charAt(0));
        sizeItems.get(0).setText(rb.getString("TinySizeMenuItem"));
        sizeItems.get(0).setMnemonic(rb.getString("TinySizeMenuItemMnemonic").charAt(0));
        sizeItems.get(1).setText(rb.getString("SmallSizeMenuItem"));
        sizeItems.get(1).setMnemonic(rb.getString("SmallSizeMenuItemMnemonic").charAt(0));
        sizeItems.get(2).setText(rb.getString("MediumSizeMenuItem"));
        sizeItems.get(2).setMnemonic(rb.getString("MediumSizeMenuItemMnemonic").charAt(0));
        sizeItems.get(3).setText(rb.getString("BigSizeMenuItem"));
        sizeItems.get(3).setMnemonic(rb.getString("BigSizeMenuItemMnemonic").charAt(0));
        sizeItems.get(4).setText(rb.getString("LargeSizeMenuItem"));
        sizeItems.get(4).setMnemonic(rb.getString("LargeSizeMenuItemMnemonic").charAt(0));
        sizeItems.get(5).setText(rb.getString("HugeSizeMenuItem"));
        sizeItems.get(5).setMnemonic(rb.getString("HugeSizeMenuItemMnemonic").charAt(0));
        clearAllItem.setText(rb.getString("ClearAllMenuItem"));
        clearAllItem.setMnemonic(rb.getString("ClearAllMenuItemMnemonic").charAt(0));
        
        helpMenu.setText(rb.getString("HelpMenu"));
        helpMenu.setMnemonic(rb.getString("HelpMenuMnemonic").charAt(0));
        aboutItem.setText(rb.getString("AboutMenuItem"));
        aboutItem.setMnemonic(rb.getString("AboutMenuItemMnemonic").charAt(0));
        
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
        circlePencilItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, 0));
        squarePencilItem = new JMenuItem("Square", new ImageIcon(getClass().getResource("icons/square16.png")));
        squarePencilItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0));
        diamondPencilItem = new JMenuItem("Diamond", new ImageIcon(getClass().getResource("icons/diamond16.png")));
        diamondPencilItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0));
        starPencilItem = new JMenuItem("Star", new ImageIcon(getClass().getResource("icons/star16.png")));
        starPencilItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0));
        circleRubberItem = new JMenuItem("Circle", new ImageIcon(getClass().getResource("icons/circle16.png")));
        circleRubberItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.SHIFT_DOWN_MASK));
        squareRubberItem = new JMenuItem("Square", new ImageIcon(getClass().getResource("icons/square16.png")));
        squareRubberItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.SHIFT_DOWN_MASK));
        diamondRubberItem = new JMenuItem("Diamond", new ImageIcon(getClass().getResource("icons/diamond16.png")));
        diamondRubberItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.SHIFT_DOWN_MASK));
        starRubberItem = new JMenuItem("Star", new ImageIcon(getClass().getResource("icons/star16.png")));
        starRubberItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.SHIFT_DOWN_MASK));
        sprayItem = new  JMenuItem("Spray", new ImageIcon(getClass().getResource("icons/spray16.png")));
        sprayItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0));
        softbrushItem = new  JMenuItem("Soft Brush", new ImageIcon(getClass().getResource("icons/flat-brush16.png")));
        softbrushItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, 0));
        bucketItem = new  JMenuItem("Intelligent Bucket", new ImageIcon(getClass().getResource("icons/paint-bucket16.png")));
        bucketItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, 0));
        
        colorItems = new ArrayList();
        HappyPalette.getInstance().forEach(e -> colorItems.add(new ColorMenuItem(e.getColor(), e.getName())));
        for (int i = 0; i < colorItems.size(); i++)
            if (i < 9)
                colorItems.get(i).setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1+i,
                        0));
            else
                colorItems.get(i).setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1+i-9,
                        KeyEvent.SHIFT_DOWN_MASK));
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
        for (int i = 0; i < sizeItems.size(); i++)
            sizeItems.get(i).setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2+i, 0));
        zoomItems = new ArrayList();
        for (int i = ZOOM_MIN; i <= ZOOM_MAX; i *= 2) {
            zoomItems.add(new NumberRadioButtonMenuItem(String.format("%d%%", i), i));
            if (i == 100)
                zoomItems.get(zoomItems.size()-1).setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0,
                        KeyEvent.CTRL_DOWN_MASK));
        }
        zoominItem = new JMenuItem("Zoom In", new ImageIcon(getClass().getResource("icons/zoomin16.png")));
        zoominItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, KeyEvent.CTRL_DOWN_MASK));
        zoomoutItem = new JMenuItem("Zoom Out", new ImageIcon(getClass().getResource("icons/zoomout16.png")));
        zoomoutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, KeyEvent.CTRL_DOWN_MASK));
        polishItem = new JMenuItem("Polish");
        polishItem.setIcon(new ImageIcon(getClass().getResource("icons/poland16.png")));
        englishItem = new JMenuItem("English");
        englishItem.setIcon(new ImageIcon(getClass().getResource("icons/united-kingdom16.png")));
        clearItem = new JMenuItem("Clear...", new ImageIcon(getClass().getResource("icons/cross-black16.png")));
        clearItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, KeyEvent.CTRL_DOWN_MASK));
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
        zoomMenu.addSeparator();
        zoomMenu.add(zoominItem);
        zoomMenu.add(zoomoutItem);
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
    
    public JMenuItem getZoomInItem() {
        return zoominItem;
    }
    
    public JMenuItem getZoomOutItem() {
        return zoomoutItem;
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

