/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable;

/**
 *
 * @author acer
 */
public class InputToAlgorithm {
    int populationSize;
    int maxGeneration;
    double mutationRate;
    double crossoverRate;
    int eliticsmCount;
    int tournamentSize;

    public InputToAlgorithm(int populationSize, int maxGeneration, double mutationRate, double crossoverRate, int eliticsmCount, int tournamentSize) {
        this.populationSize = populationSize;
        this.maxGeneration = maxGeneration;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.eliticsmCount = eliticsmCount;
        this.tournamentSize = tournamentSize;
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


    
    
}
