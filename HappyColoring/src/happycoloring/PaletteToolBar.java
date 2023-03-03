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
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 *
 * @author Tobiasz Stamborski <tstamborski@outlook.com>
 */
public class PaletteToolBar extends JToolBar implements HappyI18n {
    ArrayList<ColorButton> colorButtons;
    private final JButton paletteButton;
    
    public PaletteToolBar() {
        colorButtons = new ArrayList<>();
        
        colorButtons.add(new ColorButton(new Color(0xe8,0x00,0x00)));
        colorButtons.add(new ColorButton(new Color(0x00,0xe8,0x00)));
        colorButtons.add(new ColorButton(new Color(0x00,0x00,0xe8)));
        
        colorButtons.add(new ColorButton(new Color(0xe8,0xe8,0x00)));
        colorButtons.add(new ColorButton(new Color(0xff,0xaa,0x00)));
        colorButtons.add(new ColorButton(new Color(0x80,0x00,0xe8)));
        
        colorButtons.add(new ColorButton(new Color(0xe8,0x80,0x80)));
        colorButtons.add(new ColorButton(new Color(0x66,0xff,0x66)));
        colorButtons.add(new ColorButton(new Color(0x80,0x80,0xff)));
        colorButtons.add(new ColorButton(new Color(0x80,0xff,0xff)));
        
        colorButtons.add(new ColorButton(new Color(0x44,0x22,0x00)));
        colorButtons.add(new ColorButton(new Color(0x22,0x66,0x00)));
        colorButtons.add(new ColorButton(new Color(0xaa,0xaa,0x00)));
        colorButtons.add(new ColorButton(new Color(0x88,0x88,0x88)));
        
        colorButtons.add(new ColorButton(Color.white));
        colorButtons.add(new ColorButton(Color.black));
        
        paletteButton = new JButton(new ImageIcon(getClass().getResource("icons/palette16.png")));
        paletteButton.setToolTipText("Custom color...");
        
        setOrientation(JToolBar.VERTICAL);
        colorButtons.forEach(cb -> {
            add(cb);
        });
        addSeparator();
        add(paletteButton);
    }

    public JButton getPaletteButton() {
        return paletteButton;
    }
    
    public ArrayList<ColorButton> getColorButtons() {
        return colorButtons;
    }

    @Override
    public void loadi18n(ResourceBundle rb) {
        paletteButton.setToolTipText(rb.getString("PaletteToolTip"));
    }
}
