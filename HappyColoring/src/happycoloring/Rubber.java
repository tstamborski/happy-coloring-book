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
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Tobiasz Stamborski <tstamborski@outlook.com>
 */
public class Rubber extends AbstractPencil {
    private BufferedImage shape;
    private ChangeListener changeListener;

    public Rubber(BufferedImage shape, int size) {
        this.size = size;
        this.shape = shape;
        createPattern();
    }

    public ChangeListener getChangeListener() {
        return changeListener;
    }

    public void setChangeListener(ChangeListener changeListener) {
        this.changeListener = changeListener;
    }
    
    public BufferedImage getShape() {
        return shape;
    }

    public void setShape(BufferedImage shape) {
        this.shape = shape;
        createPattern();
    }

    @Override
    public void setSize(int size) {
        this.size = size;
        createPattern();
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void setColor(Color c) {
        this.color = c;
        createPattern();
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    private void createPattern() {
        pattern = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = pattern.createGraphics();
        g2d.drawImage(shape, 0, 0, size, size, null);
        
        if (changeListener != null)
            changeListener.stateChanged(new ChangeEvent(this));
    }
}
