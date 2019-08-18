/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable;

import algorithm.Individual;
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author acer
 */
public class AlgorithmAnalysis extends javax.swing.JDialog {

    /**
     * Creates new form AlgorithmAnalysis
     */
    ArrayList<Output> AlgorithmAnalysisop;
    ArrayList<Output> cmop;
    ArrayList<Output> cop;
    ArrayList<Output> mop;

    String[][] combination;
    String[][] crossoverMutationCombination;
    String[][] crossoverCombination;
    String[][] mutationCombination;

    public AlgorithmAnalysis(ArrayList<Output> aaop) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

        this.AlgorithmAnalysisop = aaop;
        cmop = new ArrayList<>();
        cop = new ArrayList<>();
        mop = new ArrayList<>();

        combination = new String[this.AlgorithmAnalysisop.size()][3];
        crossoverMutationCombination = new String[AlgorithmAnalysisParameter.crossoverRates.size() * AlgorithmAnalysisParameter.mutationRates.size()][3];
        crossoverCombination = new String[AlgorithmAnalysisParameter.crossoverRates.size()][3];
        mutationCombination = new String[AlgorithmAnalysisParameter.mutationRates.size()][3];

        Collections.sort(AlgorithmAnalysisop);

//        for (int ps : AlgorithmAnalysisParameter.populationSizes) {
//            for (double cr : AlgorithmAnalysisParameter.crossoverRates) {
//                for (double mr : AlgorithmAnalysisParameter.mutationRates) {
//                    input.add(new InputToAlgorithm(ps, AlgorithmAnalysisParameter.maxGenerationStandard, mr, cr,
//                            AlgorithmAnalysisParameter.eliticsmCountStandard, AlgorithmAnalysisParameter.tournamentSizeStandard));
//                }
//
//            }
//        }
        this.lblPs.setText("Population sizes used: ");
        for (int ps : AlgorithmAnalysisParameter.populationSizes) {
            this.lblPs.setText(lblPs.getText() + ps + ", ");
        }
        this.lblPs.setText(lblPs.getText().substring(0, lblPs.getText().length() - 2));

        this.lblCr.setText("Crossover rates used: ");
        for (double cr : AlgorithmAnalysisParameter.crossoverRates) {
            this.lblCr.setText(lblCr.getText() + cr + ", ");
        }
        this.lblCr.setText(lblCr.getText().substring(0, lblCr.getText().length() - 2));

        this.lblMr.setText("Mutation rates used: ");
        for (double mr : AlgorithmAnalysisParameter.mutationRates) {
            this.lblMr.setText(lblMr.getText() + mr + ", ");
        }
        this.lblMr.setText(lblMr.getText().substring(0, lblMr.getText().length() - 2));

        pnlcm.removeAll();
        pnlcm.repaint();
        pnlcm.revalidate();
        pnlcm.setLayout(new GridLayout(17, 1));

        int i = 0;
        int j = 0;

        JPanel pnl = null;
        JLabel lbl = null;

//        Collections.sort(AlgorithmAnalysisop);
        ArrayList<String> s = new ArrayList<>();
        ArrayList<String> co = new ArrayList<>();
        ArrayList<String> mu = new ArrayList<>();
        int k = 0;
        int l = 0;
        int m = 0;
        for (Output op : this.AlgorithmAnalysisop) {
            combination[j][0] = "(" + op.getPopulationSize() + "," + op.getCrossoverRate() + "," + op.getMutationRate() + ")";
            combination[j][1] = Integer.toString(op.getGenerationFitness().size());
            combination[j][2] = Double.toString(op.getGenerationFitness().get(op.getGenerationFitness().size() - 1));

            String c = "(" + op.getCrossoverRate() + "," + op.getMutationRate() + ")";
            if (!s.contains(new String(c))) {

                crossoverMutationCombination[k][0] = "(" + op.getCrossoverRate() + "," + op.getMutationRate() + ")";
                crossoverMutationCombination[k][1] = Integer.toString(op.getGenerationFitness().size());
                crossoverMutationCombination[k][2] = Double.toString(op.getGenerationFitness().get(op.getGenerationFitness().size() - 1));
                s.add("(" + op.getCrossoverRate() + "," + op.getMutationRate() + ")");
                k = k + 1;

                cmop.add(op);

            }

            c = Double.toString(op.getCrossoverRate());
            if (!co.contains(new String(c))) {
                crossoverCombination[l][0] = Double.toString(op.getCrossoverRate());
                crossoverCombination[l][1] = Integer.toString(op.getGenerationFitness().size());
                crossoverCombination[l][2] = Double.toString(op.getGenerationFitness().get(op.getGenerationFitness().size() - 1));
                co.add(Double.toString(op.getCrossoverRate()));
                l = l + 1;

                cop.add(op);
            }

            c = Double.toString(op.getMutationRate());
            if (!mu.contains(new String(c))) {
                mutationCombination[m][0] = Double.toString(op.getMutationRate());
                mutationCombination[m][1] = Integer.toString(op.getGenerationFitness().size());
                mutationCombination[m][2] = Double.toString(op.getGenerationFitness().get(op.getGenerationFitness().size() - 1));
                mu.add(Double.toString(op.getMutationRate()));
                m = m + 1;

                mop.add(op);

            }

            j += 1;
        }

        pnlcm.repaint();
        pnlcm.revalidate();
//        Collections.sort(combination);
        pnlOverall.setLayout(new GridLayout(65, 1));
        JTable tbl = new JTable(combination, new String[]{"(Population Size, Crossover Rate, Mutation Rate)", "generation", "Final Fitness"});
        JScrollPane scrollPane = new JScrollPane(tbl);
        scrollPane.setSize(550, 500);
        scrollPane.setPreferredSize(new Dimension(550, 500));
        scrollPane.setBorder(BorderFactory.createTitledBorder("Overall Combination"));
        pnlOverall.add(scrollPane);

        for (Output op : this.AlgorithmAnalysisop) {
            i = -1;
            pnl = new JPanel(new BorderLayout(200, 10));
            pnl.removeAll();
            pnl.repaint();
            pnl.revalidate();
            lbl = new JLabel("Population size = " + op.populationSize + " Crossover rate = " + op.crossoverRate + " , " + "Mutation Rate = " + op.mutationRate);
            lbl.setFont(new Font("Arial Black", Font.PLAIN, 30));
            pnl.add(lbl, BorderLayout.NORTH);
            pnl.repaint();
            pnl.revalidate();
            JFreeChart lineChart = ChartFactory.createLineChart(
                    "Population size Analysis",
                    "Fitness", "Generation",
                    createDataset(op),
                    PlotOrientation.VERTICAL,
                    true, true, false);

            ChartPanel chartPanel = new ChartPanel(lineChart);
            chartPanel.setPreferredSize(new java.awt.Dimension(1300, 800));
            pnl.add(chartPanel, BorderLayout.CENTER);

            String[] columnNames = {"Generation", "Fitness"};
            ArrayList<String> generations = new ArrayList<>();
            ArrayList<String> fn = new ArrayList<>();

            double prevFitness = -1.0;
            int prevGeneration = -1;
            for (double fitness : op.getGenerationFitness()) {
                if (prevFitness == -1.0) {
                    prevFitness = fitness;
                    prevGeneration = i + 1;
                } else {

                    if (fitness != prevFitness) {
                        if (prevGeneration == i) {
                            generations.add(i + "");
                        } else {
                            generations.add(prevGeneration + "-" + (i));
                        }
                        fn.add(prevFitness + "");
                        prevGeneration = i + 1;
                        prevFitness = fitness;
                    }
                }
                i = i + 1;

            }

            if (prevGeneration != i) {
                generations.add(prevGeneration + "-" + (i));
                fn.add(prevFitness + "");
            } else {
                generations.add(i + "");
                fn.add(prevFitness + "");
            }

            String[][] data = new String[fn.size()][2];

            for (int o = 0; o < fn.size(); o++) {
                data[o][0] = generations.get(o);
                data[o][1] = fn.get(o);
//                System.out.println(i);
            }
            JTable tbla = new JTable(data, new String[]{"Generation", "Fitness"});

            JScrollPane scrollPanea = new JScrollPane(tbla);
            scrollPanea.setSize(500, 500);
            scrollPanea.setPreferredSize(new Dimension(500, 500));
            scrollPanea.setBorder(BorderFactory.createTitledBorder("Fitness Growth"));
            pnl.add(scrollPanea, BorderLayout.SOUTH);

            pnl.repaint();
            pnl.revalidate();

            pnlOverall.add(pnl);

        }

        JTable tbl1 = new JTable(crossoverMutationCombination, new String[]{"(Crossover Rate, Mutation Rate)", "Minimum generation", "Final Fitness"});
        JScrollPane scrollPane1 = new JScrollPane(tbl1);
        scrollPane1.setSize(550, 500);
        scrollPane1.setPreferredSize(new Dimension(550, 500));
        scrollPane1.setBorder(BorderFactory.createTitledBorder("Crossover-Mutation"));
        pnlcm.add(scrollPane1);

        for (Output op : this.cmop) {
            i = -1;
            pnl = new JPanel(new BorderLayout(200, 10));
            pnl.removeAll();
            pnl.repaint();
            pnl.revalidate();
            lbl = new JLabel("Population size = " + op.populationSize + " Crossover rate = " + op.crossoverRate + " , " + "Mutation Rate = " + op.mutationRate);
            lbl.setFont(new Font("Arial Black", Font.PLAIN, 30));
            pnl.add(lbl, BorderLayout.NORTH);
            pnl.repaint();
            pnl.revalidate();
            JFreeChart lineChart = ChartFactory.createLineChart(
                    "Population size Analysis",
                    "Fitness", "Generation",
                    createDataset(op),
                    PlotOrientation.VERTICAL,
                    true, true, false);

            ChartPanel chartPanel = new ChartPanel(lineChart);
            chartPanel.setPreferredSize(new java.awt.Dimension(1300, 800));
            pnl.add(chartPanel, BorderLayout.CENTER);

            String[] columnNames = {"Generation", "Fitness"};
            ArrayList<String> generations = new ArrayList<>();
            ArrayList<String> fn = new ArrayList<>();

            double prevFitness = -1.0;
            int prevGeneration = -1;
            for (double fitness : op.getGenerationFitness()) {
                if (prevFitness == -1.0) {
                    prevFitness = fitness;
                    prevGeneration = i + 1;
                } else {
                    if (fitness != prevFitness) {
                        if (prevGeneration == i) {
                            generations.add(i + "");
                        } else {
                            generations.add(prevGeneration + "-" + (i));
                        }
                        fn.add(prevFitness + "");
                        prevGeneration = i + 1;
                        prevFitness = fitness;
                    }
                }
                i = i + 1;

            }

            if (prevGeneration != i) {
                generations.add(prevGeneration + "-" + (i));
                fn.add(prevFitness + "");
            } else {
                generations.add(i + "");
                fn.add(prevFitness + "");
            }

            String[][] data = new String[fn.size()][2];

            for (int o = 0; o < fn.size(); o++) {
                data[o][0] = generations.get(o);
                data[o][1] = fn.get(o);
//                System.out.println(i);
            }
            JTable tbla = new JTable(data, new String[]{"Generation", "Fitness"});

            JScrollPane scrollPanea = new JScrollPane(tbla);
            scrollPanea.setSize(500, 500);
            scrollPanea.setPreferredSize(new Dimension(500, 500));
            scrollPanea.setBorder(BorderFactory.createTitledBorder("Fitness Growth"));
            pnl.add(scrollPanea, BorderLayout.SOUTH);

            pnl.repaint();
            pnl.revalidate();

            pnlcm.add(pnl);

        }
        pnlc.removeAll();
        pnlc.repaint();
        pnlc.revalidate();
        pnlc.setLayout(new GridLayout(5, 1));
        pnlc.repaint();
        pnlc.revalidate();

        JTable tbl2 = new JTable(crossoverCombination, new String[]{"Crossover Rate", "Minimum generation", "Final Fitness"});
        JScrollPane scrollPane12 = new JScrollPane(tbl2);
        scrollPane12.setSize(550, 500);
        scrollPane12.setPreferredSize(new Dimension(550, 500));
        scrollPane12.setBorder(BorderFactory.createTitledBorder("Crossover"));
        pnlc.add(scrollPane12);
        for (Output op : this.cop) {
            i = -1;
            pnl = new JPanel(new BorderLayout(200, 10));
            pnl.removeAll();
            pnl.repaint();
            pnl.revalidate();
            lbl = new JLabel("Population size = " + op.populationSize + " Crossover rate = " + op.crossoverRate + " , " + "Mutation Rate = " + op.mutationRate);
            lbl.setFont(new Font("Arial Black", Font.PLAIN, 30));
            pnl.add(lbl, BorderLayout.NORTH);
            pnl.repaint();
            pnl.revalidate();
            JFreeChart lineChart = ChartFactory.createLineChart(
                    "Population size Analysis",
                    "Fitness", "Generation",
                    createDataset(op),
                    PlotOrientation.VERTICAL,
                    true, true, false);

            ChartPanel chartPanel = new ChartPanel(lineChart);
            chartPanel.setPreferredSize(new java.awt.Dimension(1300, 800));
            pnl.add(chartPanel, BorderLayout.CENTER);

            String[] columnNames = {"Generation", "Fitness"};
            ArrayList<String> generations = new ArrayList<>();
            ArrayList<String> fn = new ArrayList<>();

            double prevFitness = -1.0;
            int prevGeneration = -1;
            for (double fitness : op.getGenerationFitness()) {
                if (prevFitness == -1.0) {
                    prevFitness = fitness;
                    prevGeneration = i + 1;
                } else {
                    if (fitness != prevFitness) {
                        if (prevGeneration == i) {
                            generations.add(i + "");
                        } else {
                            generations.add(prevGeneration + "-" + (i));
                        }
                        fn.add(prevFitness + "");
                        prevGeneration = i + 1;
                        prevFitness = fitness;
                    }
                }
                i = i + 1;

            }

            if (prevGeneration != i) {
                generations.add(prevGeneration + "-" + (i));
                fn.add(prevFitness + "");
            } else {
                generations.add(i + "");
                fn.add(prevFitness + "");
            }

            String[][] data = new String[fn.size()][2];

            for (int o = 0; o < fn.size(); o++) {
                data[o][0] = generations.get(o);
                data[o][1] = fn.get(o);
//                System.out.println(i);
            }
            JTable tbla = new JTable(data, new String[]{"Generation", "Fitness"});

            JScrollPane scrollPanea = new JScrollPane(tbla);
            scrollPanea.setSize(500, 500);
            scrollPanea.setPreferredSize(new Dimension(500, 500));
            scrollPanea.setBorder(BorderFactory.createTitledBorder("Fitness Growth"));
            pnl.add(scrollPanea, BorderLayout.SOUTH);

            pnl.repaint();
            pnl.revalidate();

            pnlc.add(pnl);

        }
        pnlc.repaint();
        pnlc.revalidate();

        pnlm.removeAll();
        pnlm.repaint();
        pnlm.revalidate();
        pnlm.setLayout(new GridLayout(5, 1));
        pnlm.repaint();
        pnlm.revalidate();

        JTable tbl3 = new JTable(mutationCombination, new String[]{"Mutation Rate", "Minimum generation", "Final Fitness"});
        JScrollPane scrollPane3 = new JScrollPane(tbl3);
        scrollPane3.setSize(550, 500);
        scrollPane3.setPreferredSize(new Dimension(550, 500));
        scrollPane3.setBorder(BorderFactory.createTitledBorder("Mutation"));
        pnlm.add(scrollPane3);

        for (Output op : this.mop) {
            i = -1;
            pnl = new JPanel(new BorderLayout(200, 10));
            pnl.removeAll();
            pnl.repaint();
            pnl.revalidate();
            lbl = new JLabel("Population size = " + op.populationSize + " Crossover rate = " + op.crossoverRate + " , " + "Mutation Rate = " + op.mutationRate);
            lbl.setFont(new Font("Arial Black", Font.PLAIN, 30));
            pnl.add(lbl, BorderLayout.NORTH);
            pnl.repaint();
            pnl.revalidate();
            JFreeChart lineChart = ChartFactory.createLineChart(
                    "Population size Analysis",
                    "Fitness", "Generation",
                    createDataset(op),
                    PlotOrientation.VERTICAL,
                    true, true, false);

            ChartPanel chartPanel = new ChartPanel(lineChart);
            chartPanel.setPreferredSize(new java.awt.Dimension(1300, 800));
            pnl.add(chartPanel, BorderLayout.CENTER);

            String[] columnNames = {"Generation", "Fitness"};
            ArrayList<String> generations = new ArrayList<>();
            ArrayList<String> fn = new ArrayList<>();

            double prevFitness = -1.0;
            int prevGeneration = -1;
            for (double fitness : op.getGenerationFitness()) {
                if (prevFitness == -1.0) {
                    prevFitness = fitness;
                    prevGeneration = i + 1;
                } else {
                    if (fitness != prevFitness) {
                        if (prevGeneration == i) {
                            generations.add(i + "");
                        } else {
                            generations.add(prevGeneration + "-" + (i));
                        }
                        fn.add(prevFitness + "");
                        prevGeneration = i + 1;
                        prevFitness = fitness;
                    }
                }
                i = i + 1;

            }

            if (prevGeneration != i) {
                generations.add(prevGeneration + "-" + (i));
                fn.add(prevFitness + "");
            } else {
                generations.add(i + "");
                fn.add(prevFitness + "");
            }

            String[][] data = new String[fn.size()][2];

            for (int o = 0; o < fn.size(); o++) {
                data[o][0] = generations.get(o);
                data[o][1] = fn.get(o);
//                System.out.println(i);
            }
            JTable tbla = new JTable(data, new String[]{"Generation", "Fitness"});

            JScrollPane scrollPanea = new JScrollPane(tbla);
            scrollPanea.setSize(500, 500);
            scrollPanea.setPreferredSize(new Dimension(500, 500));
            scrollPanea.setBorder(BorderFactory.createTitledBorder("Fitness Growth"));
            pnl.add(scrollPanea, BorderLayout.SOUTH);

            pnl.repaint();
            pnl.revalidate();

            pnlm.add(pnl);

        }

        pnlm.repaint();
        pnlm.revalidate();
        
        pnlOverall.repaint();
        pnlOverall.revalidate();

    }

    private DefaultCategoryDataset createDataset(Output op) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int i = 0;
        for (double fitness : op.getGenerationFitness()) {
            dataset.addValue(i, "Fitness A/C to Generation", String.format("%.2f", fitness));
            ++i;
        }

        return dataset;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblPs = new javax.swing.JLabel();
        lblCr = new javax.swing.JLabel();
        lblMr = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        SPOverall = new javax.swing.JScrollPane();
        pnlOverall = new javax.swing.JPanel();
        SPCrossoverMutation = new javax.swing.JScrollPane();
        pnlcm = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnlc = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        pnlm = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1600, 1000));
        setMinimumSize(new java.awt.Dimension(1600, 1000));
        setPreferredSize(new java.awt.Dimension(1600, 1000));

        jPanel6.setBackground(new java.awt.Color(0, 204, 204));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/logo.png"))); // NOI18N

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Elephant", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Algorithm-Analysis");

        lblPs.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPs.setText("jLabel2");

        lblCr.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCr.setText("jLabel4");

        lblMr.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblMr.setText("jLabel5");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblCr, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                            .addComponent(lblMr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblPs, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(46, 46, 46))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(lblPs, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(lblCr, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblMr, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlOverallLayout = new javax.swing.GroupLayout(pnlOverall);
        pnlOverall.setLayout(pnlOverallLayout);
        pnlOverallLayout.setHorizontalGroup(
            pnlOverallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1593, Short.MAX_VALUE)
        );
        pnlOverallLayout.setVerticalGroup(
            pnlOverallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 852, Short.MAX_VALUE)
        );

        SPOverall.setViewportView(pnlOverall);

        jTabbedPane1.addTab("Overall", SPOverall);

        javax.swing.GroupLayout pnlcmLayout = new javax.swing.GroupLayout(pnlcm);
        pnlcm.setLayout(pnlcmLayout);
        pnlcmLayout.setHorizontalGroup(
            pnlcmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1593, Short.MAX_VALUE)
        );
        pnlcmLayout.setVerticalGroup(
            pnlcmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 852, Short.MAX_VALUE)
        );

        SPCrossoverMutation.setViewportView(pnlcm);

        jTabbedPane1.addTab("Crossover-Mutation", SPCrossoverMutation);

        javax.swing.GroupLayout pnlcLayout = new javax.swing.GroupLayout(pnlc);
        pnlc.setLayout(pnlcLayout);
        pnlcLayout.setHorizontalGroup(
            pnlcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1593, Short.MAX_VALUE)
        );
        pnlcLayout.setVerticalGroup(
            pnlcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 852, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(pnlc);

        jTabbedPane1.addTab("Crossover", jScrollPane1);

        javax.swing.GroupLayout pnlmLayout = new javax.swing.GroupLayout(pnlm);
        pnlm.setLayout(pnlmLayout);
        pnlmLayout.setHorizontalGroup(
            pnlmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1593, Short.MAX_VALUE)
        );
        pnlmLayout.setVerticalGroup(
            pnlmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 852, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(pnlm);

        jTabbedPane1.addTab("Mutation", jScrollPane2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 884, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(AlgorithmAnalysis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(AlgorithmAnalysis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(AlgorithmAnalysis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(AlgorithmAnalysis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new AlgorithmAnalysis().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane SPCrossoverMutation;
    private javax.swing.JScrollPane SPOverall;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblCr;
    private javax.swing.JLabel lblMr;
    private javax.swing.JLabel lblPs;
    private javax.swing.JPanel pnlOverall;
    private javax.swing.JPanel pnlc;
    private javax.swing.JPanel pnlcm;
    private javax.swing.JPanel pnlm;
    // End of variables declaration//GEN-END:variables
}
