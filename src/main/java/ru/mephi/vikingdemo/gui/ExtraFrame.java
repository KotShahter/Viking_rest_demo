package ru.mephi.vikingdemo.gui;

import ru.mephi.vikingdemo.model.Viking;
import ru.mephi.vikingdemo.service.LambdaService;
import ru.mephi.vikingdemo.service.VikingService;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;


public class ExtraFrame extends JFrame {

    private final LambdaService lambdaService;
    private final List<VikingTableModel> models;

    public ExtraFrame(LambdaService lambdaService) {
        this.lambdaService = lambdaService;

        models = List.of(new VikingTableModel(), new VikingTableModel(), new VikingTableModel());

        setTitle("Viking Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1000, 700));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel header = new JLabel("Viking Demo", SwingConstants.CENTER);
        header.setFont(header.getFont().deriveFont(Font.BOLD, 18f));
        add(header, BorderLayout.NORTH);

        // Main panel to hold all three tables vertically
        JPanel tablesPanel = new JPanel();
        tablesPanel.setLayout(new BoxLayout(tablesPanel, BoxLayout.Y_AXIS));

        // FIRST TABLE - Tall Vikings
        JLabel tallLabel = new JLabel("Tall Vikings (Height > 180cm)");
        tallLabel.setFont(tallLabel.getFont().deriveFont(Font.BOLD, 14f));
        tallLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        tablesPanel.add(tallLabel);

        JTable tallVikingTable = new JTable(models.get(0));
        tallVikingTable.setRowHeight(28);
        tallVikingTable.setFillsViewportHeight(true);
        JScrollPane tallScrollPane = new JScrollPane(tallVikingTable);
        tallScrollPane.setPreferredSize(new Dimension(950, 180));
        tallScrollPane.setAlignmentX(JScrollPane.CENTER_ALIGNMENT);
        tablesPanel.add(tallScrollPane);

        // SECOND TABLE - Legendary Vikings
        JLabel legendaryLabel = new JLabel("Legendary Vikings (Power Level > 90)");
        legendaryLabel.setFont(legendaryLabel.getFont().deriveFont(Font.BOLD, 14f));
        legendaryLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        tablesPanel.add(legendaryLabel);

        JTable legendaryVikingTable = new JTable(models.get(1));
        legendaryVikingTable.setRowHeight(28);
        legendaryVikingTable.setFillsViewportHeight(true);
        JScrollPane legendaryScrollPane = new JScrollPane(legendaryVikingTable);
        legendaryScrollPane.setPreferredSize(new Dimension(950, 180));
        legendaryScrollPane.setAlignmentX(JScrollPane.CENTER_ALIGNMENT);
        tablesPanel.add(legendaryScrollPane);

        // THIRD TABLE - Ginger Vikings
        JLabel gingerLabel = new JLabel("Ginger Vikings");
        gingerLabel.setFont(gingerLabel.getFont().deriveFont(Font.BOLD, 14f));
        gingerLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        tablesPanel.add(gingerLabel);

        JTable gingerVikingTable = new JTable(models.get(2));
        gingerVikingTable.setRowHeight(28);
        gingerVikingTable.setFillsViewportHeight(true);
        JScrollPane gingerScrollPane = new JScrollPane(gingerVikingTable);
        gingerScrollPane.setPreferredSize(new Dimension(950, 180));
        gingerScrollPane.setAlignmentX(JScrollPane.CENTER_ALIGNMENT);
        tablesPanel.add(gingerScrollPane);

        // Wrap tablesPanel in a JScrollPane for vertical scrolling if needed
        JScrollPane mainScrollPane = new JScrollPane(tablesPanel);
        mainScrollPane.setBorder(null);
        add(mainScrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void refreshAllTables() {
        List<List<Viking>> freshData = List.of(
                lambdaService.heightFilter(),
                lambdaService.rareFilter(),
                lambdaService.gingerFilter()
        );

        for (int x = 0; x < freshData.size(); x++)
        {
            models.get(x).editVikings(freshData.get(x));
        }
    }
}