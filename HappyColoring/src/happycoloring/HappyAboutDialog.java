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

import java.awt.Desktop;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;

/**
 *
 * @author Tobiasz Stamborski <tstamborski@outlook.com>
 */
public class HappyAboutDialog extends AboutDialog implements HappyI18n {
    private final JScrollPane authorsScrollPane;
    private final JEditorPane authorsEditorPane;

    public HappyAboutDialog(JFrame parent) {
        super(parent);
        
        authorsScrollPane = new JScrollPane();
        authorsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        authorsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        authorsEditorPane = new JEditorPane();
        authorsEditorPane.setEditable(false);
        authorsEditorPane.setContentType("text/html");
        authorsEditorPane.addHyperlinkListener((e) -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED && Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().browse(e.getURL().toURI());
                } catch (IOException | URISyntaxException ex) {
                    //nie rob nic
                }
            }
        });
        
        authorsScrollPane.setViewportView(authorsEditorPane);
        tabs.insertTab("Authors", null, authorsScrollPane, "Authors", 1);
    }
    
    public void setApplicationAuthorsInfo(URL url) throws IOException {
        authorsEditorPane.setPage(url);
    }
    
    @Override
    public void loadi18n(ResourceBundle i18n) {
        setTitle(i18n.getString("AboutDialog"));
        tabs.setTitleAt(0, i18n.getString("AboutTab"));
        tabs.setTitleAt(1, i18n.getString("AuthorsTab"));
        tabs.setTitleAt(2, i18n.getString("LicenseTab"));
        okButton.setText(i18n.getString("OK"));
        
        setApplicationName(i18n.getString("ApplicationName"));
        setApplicationVersion(i18n.getString("ApplicationVersion"));
        setApplicationCopyright(i18n.getString("ApplicationCopyright"));
        setApplicationExtraInfo(i18n.getString("ApplicationExtraInfo"));
    }
}
