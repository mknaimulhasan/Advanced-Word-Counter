package TextAnalyzer;

import javax.swing.*;
import java.io.*;
import java.util.concurrent.TimeUnit;


public class design extends javax.swing.JFrame {

    
    public design() {
        initComponents();
    }

    private void initComponents() {

        mapTypeButtonGroup = new javax.swing.ButtonGroup();
        logPanel = new javax.swing.JPanel();
        logScrollPane = new javax.swing.JScrollPane();
        logTextArea = new javax.swing.JTextArea();
        mapTypePanel = new javax.swing.JPanel();
        useHashMapRadioButton = new javax.swing.JRadioButton();
        buttonPanel = new javax.swing.JPanel();
        analyzeTextButton = new javax.swing.JButton();
       
        showWordsByFrequencyButton = new javax.swing.JButton();
       

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        logPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(), "Advanced Word Counter"));

        logTextArea.setColumns(10);
        logTextArea.setRows(5);
        logScrollPane.setViewportView(logTextArea);

        javax.swing.GroupLayout logPanelLayout = new javax.swing.GroupLayout(logPanel);
        logPanel.setLayout(logPanelLayout);
        logPanelLayout.setHorizontalGroup(
                logPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(logScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );
        logPanelLayout.setVerticalGroup(
                logPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(logScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );


        mapTypeButtonGroup.add(useHashMapRadioButton);
        useHashMapRadioButton.setSelected(false);
        useHashMapRadioButton.setText("HashMap");


        javax.swing.GroupLayout mapTypePanelLayout = new javax.swing.GroupLayout(mapTypePanel);
        mapTypePanel.setLayout(mapTypePanelLayout);
        mapTypePanelLayout.setHorizontalGroup(
                mapTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mapTypePanelLayout.createSequentialGroup()
                                .addContainerGap(5, Short.MAX_VALUE)
                                .addGroup(mapTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                      
                                        .addComponent(useHashMapRadioButton))
                                .addGap(30, 30, 30))
        );
        mapTypePanelLayout.setVerticalGroup(
                mapTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(mapTypePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(useHashMapRadioButton)
                                .addGap(30, 30, 30)
                              
                                .addContainerGap(35, Short.MAX_VALUE))
        );

        buttonPanel.setLayout(new java.awt.GridLayout(2, 3));

        analyzeTextButton.setText("Choose File & Analyze");
        analyzeTextButton.addActionListener(this::analyzeTextButtonActionPerformed);
        buttonPanel.add(analyzeTextButton);

       

        showWordsByFrequencyButton.setText("Display Frequency");
        showWordsByFrequencyButton.addActionListener(this::showWordsByionButtonFrequencyButtonAct);
        buttonPanel.add(showWordsByFrequencyButton);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(logPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                               
                                                .addComponent(mapTypePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                
                                                .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                             )
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(logPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(1, 1, 1)
                                                .addComponent(mapTypePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(2, 2, 2)
                                                .addComponent(buttonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)))
                                .addContainerGap())
        );

        pack();
    }


    private void analyzeTextButtonActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser();
        javax.swing.filechooser.FileFilter textFilter = new javax.swing.filechooser.FileFilter() {

            @Override
            public String getDescription() {
                return null;
            }

            @Override
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith("txt") || f.isDirectory();
            }
        };
        fileChooser.setFileFilter(textFilter);
        int actionSelected = fileChooser.showOpenDialog(logTextArea);
        if (actionSelected == JFileChooser.APPROVE_OPTION) {
            if (useHashMapRadioButton.isSelected()) {
                textAnalyzer = new TextAnalyzer("hashmap");
            } 
            long startTime = System.nanoTime();
            long endTime = 0;
            try {
                textAnalyzer.analyzeText(fileChooser.getSelectedFile().getPath());
                endTime = System.nanoTime();
            } catch (FileNotFoundException e) {
            } catch (IOException e) {
            }
            logTextArea.append("DisPlay File name: " + fileChooser.getSelectedFile().getName() + "\n");
            logTextArea.append("Unique Words: " + textAnalyzer.getUniqueWordCount() + "\n");
            logTextArea.append("Total Words: " + textAnalyzer.getWordCount() + "\n");
            logTextArea.append("Analyze Time: " + TimeUnit.NANOSECONDS.toMillis(endTime - startTime) + " milliseconds\n");
        }

    }


    private void showWordsByionButtonFrequencyButtonAct(java.awt.event.ActionEvent evt) {
        if (textAnalyzer != null) {
            long startTime = System.nanoTime();
            for (IWordDatafile word : textAnalyzer.allWordsOrdedByFrequencyCount()) {
                logTextArea.append(word.getText() + " - Happens " + word.getFrequencyCount() + " times\n");
            }
            long endTime = System.nanoTime();
            logTextArea.append("Time shows" + TimeUnit.NANOSECONDS.toMillis(endTime - startTime) + " millisecond to Word analyze\n");
        }
    }
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new design().setVisible(true);
            }
        });
    }
    TextAnalyzer textAnalyzer;
    
    private javax.swing.JButton analyzeTextButton;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JPanel logPanel;
    private javax.swing.JScrollPane logScrollPane;
    private javax.swing.JTextArea logTextArea;
    private javax.swing.ButtonGroup mapTypeButtonGroup;
    private javax.swing.JPanel mapTypePanel;
    private javax.swing.JButton showWordsByFrequencyButton;
    private javax.swing.JRadioButton useHashMapRadioButton;

   

}
