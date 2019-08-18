/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author acer
 */
public class AlgorithmAnalysisParameter {

    public static int populationSizeStandard = 50;
    public static double crossoverRateStandard = 0.9;
    public static double mutationRateStandard = 0.01;
    public static int tournamentSizeStandard = 5;
    public static int eliticsmCountStandard = 2;
    public static int maxGenerationStandard = 4000;

    public static ArrayList<Integer> populationSizes = new ArrayList<>();
    public static ArrayList<Double> crossoverRates = new ArrayList<>();
    public static ArrayList<Double> mutationRates = new ArrayList<>();

    public static void loadParameter() {
        try {
            populationSizes = new ArrayList<>();
            crossoverRates = new ArrayList<>();
            mutationRates = new ArrayList<>();
            
            
            
            DB_Connect dc = new DB_Connect();
            ResultSet rs = null;
            dc.connectDatabase();
            
            Statement s = dc.conn.createStatement();
            String query = "Select * from analysisparameter";
            rs = s.executeQuery(query);
            while(rs.next()){
               populationSizes.add(Integer.parseInt(rs.getString("population_size")));
               crossoverRates.add(Double.parseDouble(rs.getString("crossover_rate")));
               mutationRates.add(Double.parseDouble(rs.getString("mutation_rate")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AlgorithmAnalysisParameter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
