package main;

import jason.environment.grid.GridWorldModel;
import jason.environment.grid.Location;

public class TaxiWorldModel extends GridWorldModel {
    private boolean hasCustomer = false;
    private boolean isAtTaxiRank = false;
    
    private static final int gridSize = 20;
    
    private static final int TAXIRANK = 16;
    private static final int CINEMA = 32;
    
    // Initial locations
    Location lTaxiRank = new Location(0,0);
    Location lCinema = new Location(gridSize-1,gridSize-1);
    
    protected TaxiWorldModel() {
        super(gridSize, gridSize, 3);
        
        //taxi pos
        setAgPos(0, gridSize/2, gridSize/2);
        
        add(TAXIRANK, lTaxiRank);
        add(CINEMA, lCinema);
    }
    
    public boolean getCustomer() {
        if (isAtTaxiRank) {
            hasCustomer = true;
            return true;
        }
        return false;
    }
    
    public boolean dropCustomer() {
        if (hasCustomer) {
            hasCustomer = false;
            return true;
        }
        return false;
    }
    
    public int getTAXIRANK() {
        return TAXIRANK;
    }
    
    public int getCINEMA() {
        return CINEMA;
    }

}
