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

import java.io.File;
import java.util.ResourceBundle;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Tobiasz Stamborski <tstamborski@outlook.com>
 */
public class HappyLoadDialog extends JFileChooser implements HappyI18n {
    private final FileNameExtensionFilter imgFilter, pngFilter, jpgFilter, bmpFilter;

    public HappyLoadDialog(File currentDirectory) {
        super(currentDirectory);
        
        imgFilter = new FileNameExtensionFilter("Image Files", "png", "jpg", "jpeg", "bmp");
        pngFilter = new FileNameExtensionFilter("PNG Images", "png");
        jpgFilter = new FileNameExtensionFilter("JPG Images", "jpg", "jpeg");
        bmpFilter = new FileNameExtensionFilter("BMP Images", "bmp");
        
        addChoosableFileFilter(bmpFilter);
        addChoosableFileFilter(pngFilter);
        addChoosableFileFilter(jpgFilter);
        addChoosableFileFilter(imgFilter);
        setFileFilter(imgFilter);
        
        setMultiSelectionEnabled(true);
    }

    @Override
    public void loadi18n(ResourceBundle rb) {
        setDialogTitle(rb.getString("LoadDialog"));
    }
    
}
