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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;

/**
 *
 * @author Tobiasz Stamborski <tstamborski@outlook.com>
 */
public class ColoringPage extends JComponent {

    private final BufferedImage compImage;
    private BufferedImage canvasImage;
    private final BufferedImage refImage;
    private Drawable drawingTool;
    private double zoom;
    private final ColoringUndoRedo history;
    private ActionListener actionListener;

    public ActionListener getActionListener() {
        return actionListener;
    }

    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public Drawable getDrawingTool() {
        return drawingTool;
    }

    public void setDrawingTool(Drawable drawingTool) {
        this.drawingTool = drawingTool;
    }

    public double getZoom() {
        return zoom;
    }

    public void setZoom(double zoom) {
        this.zoom = zoom;
        setPreferredSize(new Dimension((int)(refImage.getWidth()*zoom), (int)(refImage.getHeight()*zoom)));
        getParent().revalidate();
    }
    
    public boolean isUndo() {
        return history.isUndo();
    }
    
    public boolean isRedo() {
        return history.isRedo();
    }
    
    public void undo() {
        if (history.isUndo())
            canvasImage = history.undo();
        repaint();
    }
    
    public void redo() {
        if (history.isRedo())
            canvasImage = history.redo();
        repaint();
    }
    
    public ColoringPage(BufferedImage ref, String name) {
        if (name != null)
            setName(name);
        else
            setName("Unnamed");
        
        refImage = new BufferedImage(ref.getWidth(), ref.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = refImage.createGraphics();
        g2d.drawImage(ref, 0, 0, null);
        g2d.dispose();
        
        history = new ColoringUndoRedo();
        
        canvasImage = new BufferedImage(refImage.getWidth(), refImage.getHeight(), refImage.getType());
        compImage = new BufferedImage(refImage.getWidth(), refImage.getHeight(), refImage.getType());
        clear();
        
        setPreferredSize(new Dimension(refImage.getWidth(), refImage.getHeight()));
        zoom = 1.0;
        enableEvents(MouseEvent.MOUSE_MOTION_EVENT_MASK | MouseEvent.MOUSE_EVENT_MASK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = compImage.createGraphics();
        g2d.drawImage(canvasImage, 0, 0, this);
        g2d.setComposite(new ANDComposite());
        g2d.drawImage(refImage, 0, 0, this);
        g2d.dispose();
        
        g.drawImage(compImage, 0, 0, getWidth(), getHeight(), this);
    }

    @Override
    protected void processMouseMotionEvent(MouseEvent e) {
        super.processMouseMotionEvent(e);
        
        if ((e.getModifiersEx() & MouseEvent.BUTTON1_DOWN_MASK) != 0)
            draw(e.getX(), e.getY());
    }
    
    @Override
    protected void processMouseEvent(MouseEvent e) {
        super.processMouseEvent(e);
        
        if (e.getButton() == MouseEvent.BUTTON1)
            if (e.getID() == MouseEvent.MOUSE_PRESSED)
                draw(e.getX(), e.getY());
            else if (e.getID() == MouseEvent.MOUSE_RELEASED)
                noteAction();
    }
    
    public final void clear() {
        Graphics2D g = canvasImage.createGraphics();
        g.setBackground(Color.white);
        g.clearRect(0, 0, canvasImage.getWidth(), canvasImage.getHeight());
        g.dispose();
        
        noteAction();
        repaint();
    }

    private void draw(int x, int y) {
        if (drawingTool == null)
            return;
        
        drawingTool.apply(canvasImage, (int)(x/zoom), (int)(y/zoom), refImage);
        repaint();
    }
    
    private void noteAction() {
        history.push(canvasImage);
        fireActionEvent();
    }
    
    private void fireActionEvent() {
        if (actionListener != null)
            actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
    }

}
