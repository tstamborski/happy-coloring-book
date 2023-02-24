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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ResourceBundle;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.*;

/**
 *
 * @author Tobiasz Stamborski <tstamborski@outlook.com>
 */
public class HappyStatusBar extends JPanel implements HappyI18n{
    private final JLabel colorLabel, pageLabel, mouseLabel;
    String pageFormatString;
    
    public HappyStatusBar() {
        pageFormatString = "Page %d of %d: %s";
        
        colorLabel = new JLabel();
        colorLabel.setBorder(new EtchedBorder());
        colorLabel.setPreferredSize(new Dimension(20,20));
        pageLabel = new JLabel();
        pageLabel.setBorder(new EtchedBorder());
        mouseLabel = new JLabel();
        mouseLabel.setBorder(new EtchedBorder());
        mouseLabel.setPreferredSize(new Dimension(100,20));
        
        setLayout(new BorderLayout());
        add(colorLabel, BorderLayout.WEST);
        add(pageLabel, BorderLayout.CENTER);
        add(mouseLabel, BorderLayout.EAST);
    }
    
    public void setDisplayedColor(Color c) {
        colorLabel.setIcon(new ColorIcon(c, 16));
    }
    
    public void setDisplayedInfo(ColoringPageList list) {
        if (!list.isEmpty())
            pageLabel.setText(String.format(pageFormatString, list.getCurrentIndex()+1, list.size(),
                list.getCurrent().getName()));
        else
            pageLabel.setText("");
    }
    
    public void setDisplayedCoord(int x, int y) {
        mouseLabel.setText(String.format("x: %4d y: %4d", x, y));
    }

    @Override
    public void loadi18n(ResourceBundle rb) {
        
    }
}
