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
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Tobiasz Stamborski <tstamborski@outlook.com>
 */
public class Spray extends AbstractPencil {
    private ChangeListener changeListener;

    public Spray(Color c, int size) {
        this.color = c;
        this.size = size;
    }

    public ChangeListener getChangeListener() {
        return changeListener;
    }

    public void setChangeListener(ChangeListener changeListener) {
        this.changeListener = changeListener;
    }
    
    @Override
    public void setSize(int size) {
        this.size = size;
        fireChangeEvent();
    }

    @Override
    public int getSize() {
        return size;
    }
    
    @Override
    public void setColor(Color c) {
        this.color = c;
        fireChangeEvent();
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    protected BufferedImage getPattern() {
        BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Random rnd = new Random();
            
        for (int i = 2; i <= size; i = i*2) {
            int radius;
            double angle;
                
            for (int j = 0; j < i; j++) {
                radius = rnd.nextInt(i/2);
                angle = (Math.PI / 180) * rnd.nextInt(360);
                    
                img.setRGB(size/2 + (int)(radius * Math.cos(angle)), size/2 + (int)(radius * Math.sin(angle)),
                        color.getRGB());
            }
        }
        
        return img;
    }
    
    private void fireChangeEvent() {
        if (changeListener != null)
            changeListener.stateChanged(new ChangeEvent(this));
    }
}
