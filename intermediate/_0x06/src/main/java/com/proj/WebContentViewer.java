package com.proj;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebContentViewer extends JFrame {
    private JTextField urlField;
    private JButton runButton;
    private JEditorPane editorPane;
    private JList<String> linkList;
    private DefaultListModel<String> listModel;

    public WebContentViewer() {
        setTitle("Web Content Viewer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        urlField = new JTextField();
        runButton = new JButton("Run");

        editorPane = new JEditorPane();
        editorPane.setEditable(false);
        editorPane.setContentType("text/html");

        editorPane.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    try {
                        editorPane.setPage(e.getURL());
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(WebContentViewer.this,
                                "Cannot load content from url.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        listModel = new DefaultListModel<>();
        linkList = new JList<>(listModel);

        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = urlField.getText();
                loadPage(url);
            }
        });

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(urlField, BorderLayout.CENTER);
        inputPanel.add(runButton, BorderLayout.EAST);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(editorPane), new JScrollPane(linkList));
        splitPane.setDividerLocation(600);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(inputPanel, BorderLayout.NORTH);
        getContentPane().add(splitPane, BorderLayout.CENTER);
    }

    private void loadPage(String url) {
        try {
            editorPane.setPage(new URL(url));

            Document doc = Jsoup.connect(url).get();
            Elements links = doc.select("a[href]");

            listModel.clear();
            for (Element link : links) {
                listModel.addElement(link.attr("abs:href"));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Cannot Load page.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WebContentViewer viewer = new WebContentViewer();
            viewer.setVisible(true);
        });
    }
}
