/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable;

import java.util.ArrayList;

public class Output implements Comparable< Output> {

    ArrayList<Double> generationFitness;
    long timeTaken;
    public int populationSize;
    public int maxGeneration;
    public double mutationRate;
    public double crossoverRate;
    public int eliticsmCount;
    public int tournamentSize;

    public Output(ArrayList<Double> generationFitness, int maxGeneration, long timeTaken) {
        this.populationSize = 100;
        this.mutationRate = 0.01;
        this.crossoverRate = 0.9;
        this.eliticsmCount = 2;
        this.tournamentSize = 5;
        this.generationFitness = generationFitness;
        this.maxGeneration = maxGeneration;
        this.timeTaken = timeTaken;
    }

    public Output(ArrayList<Double> generationFitness, long timeTaken, int populationSize, int maxGeneration, double mutationRate, double crossoverRate, int eliticsmCount, int tournamentSize) {
        this.generationFitness = generationFitness;
        this.timeTaken = timeTaken;
        this.populationSize = populationSize;
        this.maxGeneration = maxGeneration;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.eliticsmCount = eliticsmCount;
        this.tournamentSize = tournamentSize;
    }

    public long getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(long timeTaken) {
        this.timeTaken = timeTaken;
    }

    public ArrayList<Double> getGenerationFitness() {
        return generationFitness;
    }

    public void setGenerationFitness(ArrayList<Double> generationFitness) {
        this.generationFitness = generationFitness;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public int getMaxGeneration() {
        return maxGeneration;
    }

    public void setMaxGeneration(int maxGeneration) {
        this.maxGeneration = maxGeneration;
    }

    public double getMutationRate() {
        return mutationRate;
    }

    public void setMutationRate(double mutationRate) {
        this.mutationRate = mutationRate;
    }

    public double getCrossoverRate() {
        return crossoverRate;
    }

    public void setCrossoverRate(double crossoverRate) {
        this.crossoverRate = crossoverRate;
    }

    public int getEliticsmCount() {
        return eliticsmCount;
    }

    public void setEliticsmCount(int eliticsmCount) {
        this.eliticsmCount = eliticsmCount;
    }

    public int getTournamentSize() {
        return tournamentSize;
    }

    public void setTournamentSize(int tournamentSize) {
        this.tournamentSize = tournamentSize;
    }

//    @Override
//    public int compareTo(Output o) {
//        int a;
//        a= new Integer(this.getPopulationSize()).compareTo(new Integer(o.getPopulationSize()));
//        if(a==0){
//          a= new Double(this.getCrossoverRate()).compareTo(new Double(o.getCrossoverRate()));  
//          if(a==0){
//              a= new Double(this.getMutationRate()).compareTo(new Double(o.getMutationRate()));
//          }
//        }
//        return a;
//
//    }
    
    @Override
    public int compareTo(Output o) {
        int a;
        a= new Integer(this.getGenerationFitness().size()).compareTo(new Integer(o.getGenerationFitness().size()));
 
        return a;

    }

}
