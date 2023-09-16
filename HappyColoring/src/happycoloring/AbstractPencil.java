/*
 * The MIT License
 *
 * Copyright 2023 tstam.
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

/**
 *
 * @author tstam
 */
public abstract class AbstractPencil implements DrawingTool {
    protected int size;
    protected Color color;
    
    protected BufferedImage pattern;
    protected int lastX;
    protected int lastY;
    protected boolean released;

    public AbstractPencil() {
        released = true;
    }

    @Override
    public void apply(BufferedImage canvas, int x, int y, BufferedImage ref) {
        Graphics2D g2d = canvas.createGraphics();
        if (released == true) {
            g2d.drawImage(getPattern(), x - size / 2, y - size / 2, null);
            released = false;
            lastX = x;
            lastY = y;
        } else {
            int stepx = (int)Math.signum(x - lastX);
            int stepy = (int)Math.signum(y - lastY);
            while (lastX != x || lastY != y) {
                g2d.drawImage(getPattern(), lastX - size / 2, lastY - size / 2, null);
                if (lastX != x)
                    lastX += stepx;
                if (lastY != y)
                    lastY += stepy;
            }
        }
    }
    
    protected BufferedImage getPattern() {
        return pattern;
    }

    @Override
    public void release() {
        released = true;
    }
    
}
