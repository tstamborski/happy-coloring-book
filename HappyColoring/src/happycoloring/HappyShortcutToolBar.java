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

import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

/**
 *
 * @author Tobiasz Stamborski <tstamborski@outlook.com>
 */
public class HappyShortcutToolBar extends JToolBar implements HappyI18n {
    private final JButton nextButton, previousButton;
    private final JButton undoButton, redoButton;
    private final JToggleButton pencilButton, rubberButton, sprayButton, softbrushButton, bucketButton;
    private final ArrayList<NumberToggleButton> sizeButtons;
    private final ButtonGroup toolGroup, sizeGroup;
    
    public HappyShortcutToolBar() {
        nextButton = new JButton(new ImageIcon(getClass().getResource("icons/arrow-right16.png")));
        previousButton = new JButton(new ImageIcon(getClass().getResource("icons/arrow-left16.png")));
        
        undoButton = new JButton(new ImageIcon(getClass().getResource("icons/undo16.png")));
        redoButton = new JButton(new ImageIcon(getClass().getResource("icons/redo16.png")));
        
        pencilButton = new JToggleButton(new ImageIcon(getClass().getResource("icons/pencil16.png")));
        rubberButton = new JToggleButton(new ImageIcon(getClass().getResource("icons/rubber16.png")));
        sprayButton = new JToggleButton(new ImageIcon(getClass().getResource("icons/spray16.png")));
        softbrushButton = new JToggleButton(new ImageIcon(getClass().getResource("icons/flat-brush16.png")));
        bucketButton = new JToggleButton(new ImageIcon(getClass().getResource("icons/paint-bucket16.png")));
        
        sizeButtons = new ArrayList();
        sizeButtons.add(new NumberToggleButton(new ImageIcon(getClass().getResource("icons/xs16.png")),
                DrawingTool.SIZE_TINY));
        sizeButtons.add(new NumberToggleButton(new ImageIcon(getClass().getResource("icons/s16.png")),
                DrawingTool.SIZE_SMALL));
        sizeButtons.add(new NumberToggleButton(new ImageIcon(getClass().getResource("icons/m16.png")),
                DrawingTool.SIZE_MEDIUM));
        sizeButtons.add(new NumberToggleButton(new ImageIcon(getClass().getResource("icons/l16.png")),
                DrawingTool.SIZE_BIG));
        sizeButtons.add(new NumberToggleButton(new ImageIcon(getClass().getResource("icons/xl16.png")),
                DrawingTool.SIZE_LARGE));
        sizeButtons.add(new NumberToggleButton(new ImageIcon(getClass().getResource("icons/xxl16.png")),
                DrawingTool.SIZE_HUGE));
        
        toolGroup = new ButtonGroup();
        toolGroup.add(pencilButton);
        toolGroup.add(rubberButton);
        toolGroup.add(sprayButton);
        toolGroup.add(softbrushButton);
        toolGroup.add(bucketButton);
        sizeGroup = new ButtonGroup();
        sizeButtons.forEach(b -> sizeGroup.add(b));
        
        add(nextButton);
        add(previousButton);
        addSeparator();
        add(undoButton);
        add(redoButton);
        addSeparator();
        add(pencilButton);
        add(rubberButton);
        add(sprayButton);
        add(softbrushButton);
        add(bucketButton);
        addSeparator();
        sizeButtons.forEach(b -> add(b));
        
        setOrientation(JToolBar.VERTICAL);
    }

    public JButton getUndoButton() {
        return undoButton;
    }

    public JButton getRedoButton() {
        return redoButton;
    }

    public JButton getNextButton() {
        return nextButton;
    }

    public JButton getPreviousButton() {
        return previousButton;
    }

    public JToggleButton getPencilButton() {
        return pencilButton;
    }

    public JToggleButton getRubberButton() {
        return rubberButton;
    }

    public JToggleButton getSprayButton() {
        return sprayButton;
    }

    public JToggleButton getSoftbrushButton() {
        return softbrushButton;
    }

    public JToggleButton getBucketButton() {
        return bucketButton;
    }

    public ArrayList<NumberToggleButton> getSizeButtons() {
        return sizeButtons;
    }

    @Override
    public void loadi18n(ResourceBundle rb) {
        nextButton.setToolTipText(rb.getString("NextButtonToolTip"));
        previousButton.setToolTipText(rb.getString("PreviousButtonToolTip"));
        
        undoButton.setToolTipText(rb.getString("UndoButtonToolTip"));
        redoButton.setToolTipText(rb.getString("RedoButtonToolTip"));
        
        pencilButton.setToolTipText(rb.getString("PencilButtonToolTip"));
        rubberButton.setToolTipText(rb.getString("RubberButtonToolTip"));
        sprayButton.setToolTipText(rb.getString("SprayButtonToolTip"));
        softbrushButton.setToolTipText(rb.getString("SoftBrushButtonToolTip"));
        bucketButton.setToolTipText(rb.getString("BucketButtonToolTip"));
        
        sizeButtons.get(0).setToolTipText(rb.getString("TinySizeButtonToolTip"));
        sizeButtons.get(1).setToolTipText(rb.getString("SmallSizeButtonToolTip"));
        sizeButtons.get(2).setToolTipText(rb.getString("MediumSizeButtonToolTip"));
        sizeButtons.get(3).setToolTipText(rb.getString("BigSizeButtonToolTip"));
        sizeButtons.get(4).setToolTipText(rb.getString("LargeSizeButtonToolTip"));
        sizeButtons.get(5).setToolTipText(rb.getString("HugeSizeButtonToolTip"));
    }
    
}
