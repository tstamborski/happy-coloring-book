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
import java.awt.Cursor;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Tobiasz Stamborski <tstamborski@outlook.com>
 */
public class PaintBucket implements DrawingTool {
    private static final int DEFAULT_TOLERANCE = 0x80; //do spr czy kolor jest "prawie" czarny
    
    private Color color;
    private int size; //tylko po to zeby ladnie dzialalo z reszta narzedzi/programu
    private int baseRGB, dstRGB;
    private final Queue<Coordinate> queue;
    private BufferedImage canvas, reference;
    private ChangeListener changeListener;
    
    public PaintBucket(Color c) {
        color = c;
        queue = new LinkedList<>();
    }
    
    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void apply(BufferedImage canvas, int x, int y, BufferedImage ref) {
        if (x < 0 || x >= canvas.getWidth())
            return;
        if (y < 0 || y >= canvas.getHeight())
            return;
        
        this.canvas = canvas;
        this.reference = ref;
        baseRGB = canvas.getRGB(x, y);
        if (canvas.getType() == BufferedImage.TYPE_INT_BGR)
            dstRGB = rgb2bgr(color.getRGB());
        else
            dstRGB = color.getRGB() | 0xff000000;
            
        queue.clear();
        queue.add(new Coordinate(x,y));
        
        while (!queue.isEmpty()) {
            Coordinate c = queue.poll();
            
            if (isInBound(c)) {
                canvas.setRGB(c.getX(), c.getY(), dstRGB);
                queue.add(new Coordinate(c.getX()+1, c.getY()));
                queue.add(new Coordinate(c.getX()-1, c.getY()));
                queue.add(new Coordinate(c.getX(), c.getY()+1));
                queue.add(new Coordinate(c.getX(), c.getY()-1));
            }
        }
    }

    @Override
    public void setColor(Color c) {
        color = c;
        fireChangeEvent();
    }

    @Override
    public Color getColor() {
        return color;
    }

    public ChangeListener getChangeListener() {
        return changeListener;
    }

    public void setChangeListener(ChangeListener changeListener) {
        this.changeListener = changeListener;
    }
    
    private void fireChangeEvent() {
        if (changeListener != null)
            changeListener.stateChanged(new ChangeEvent(this));
    }
    
    private static boolean isAlmostBlack(int rgb, int tolerance) {
        int r, g, b;
        
        r = (rgb >> 16) & 0xff;
        g = (rgb >> 8) & 0xff;
        b = rgb & 0xff;
        
        return (r < tolerance && g < tolerance && b < tolerance);
    }

    private boolean isInBound(Coordinate c) {
        if (c.getX() < 0 || c.getX() >= canvas.getWidth())
            return false;
        if (c.getY() < 0 || c.getY() >= canvas.getHeight())
            return false;
        if (isAlmostBlack(reference.getRGB(c.getX(), c.getY()), DEFAULT_TOLERANCE))
            return false;
        if (canvas.getRGB(c.getX(), c.getY()) != baseRGB)
            return false;
        
        return canvas.getRGB(c.getX(), c.getY()) != dstRGB;
    }

    private static int rgb2bgr(int rgb) {
        int r, g, b;
        int bgr;
        
        r = (rgb >> 16) & 0xff;
        g = (rgb >> 8) & 0xff;
        b = rgb & 0xff;
        
        bgr = 0;
        bgr = bgr | r;
        bgr = bgr | (g << 8);
        bgr = bgr | (b << 16);
        bgr = bgr | 0xff000000;
        
        return bgr;
    }

    @Override
    public void release() {
    }

    @Override
    public Cursor getCursor() {
        return HappyCursors.getBucketCursor();
    }
}

class Coordinate {
    private final int x, y;
    
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}