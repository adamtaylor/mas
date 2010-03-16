package main;

import java.util.logging.Logger;

import jason.environment.grid.GridWorldModel;
import jason.environment.grid.Location;

public class TaxiWorldModel extends GridWorldModel {
    private Logger logger = Logger.getLogger("MAS."+TaxiWorldModel.class.getName());
    
    private boolean hasCustomer = false;
    private boolean isAtTaxiRank = false;
    private boolean isAtCinema = false;
    private boolean inTaxi = false;
    
    private static final int gridSize = 20;
    
    private static final int TAXIRANK = 16;
    private static final int CINEMA = 32;
    
    // Initial locations
    Location lTaxiRank = new Location(0,0);
    Location lCinema = new Location(gridSize-1,gridSize-1);
    
    protected TaxiWorldModel() {
        super(gridSize, gridSize, 3);
        
        //taxi positions for all the taxis
        setAgPos(0, 3, gridSize/2);
        setAgPos(1, 6, gridSize/2);
        setAgPos(2, 12, gridSize/2);

        
        add(TAXIRANK, lTaxiRank);
        add(CINEMA, lCinema);
    }
    
    public boolean getCustomer() {
        if (isAtTaxiRank) {
            hasCustomer = true;
            inTaxi = true;
            return true;
        }
        return false;
    }
    
    public boolean dropCustomer() {
        if (hasCustomer) {
            hasCustomer = false;
            inTaxi = false;
            return true;
        }
        return false;
    }
    
    public boolean hasCustomer() {
        return hasCustomer;
    }
    
    public boolean isAtTaxiRank() {
        return isAtTaxiRank;
    }
    
    public boolean inTaxi() {
        return inTaxi;
    }
    
    public int getTAXIRANK() {
        return TAXIRANK;
    }
    
    public int getCINEMA() {
        return CINEMA;
    }
    
    public boolean moveTowards(Location dest, int ag) {
        //logger.info("attempting to move the taxi");
        Location taxi = getAgPos(ag);
        //logger.info("taxi.x && taxi.yy == "+taxi.x+" "+taxi.y);
        if (taxi.x < dest.x)        taxi.x++;
        else if (taxi.x > dest.x)   taxi.x--;
        if (taxi.y < dest.y)        taxi.y++;
        else if (taxi.y > dest.y)   taxi.y--;
        setAgPos(ag, taxi); 
        //logger.info("taxi.x && taxi.yy == "+taxi.x+" "+taxi.y);

        if (view != null) {
            view.update(lTaxiRank.x,lTaxiRank.y);
            view.update(lCinema.x,lCinema.y);
        }
        
        if (taxi.x == 0 && taxi.y == 0) {
            isAtTaxiRank = true;
        }
        
        if (taxi.x == 20 && taxi.y == 20) {
            isAtCinema = true;
        }

        
        //logger.info("about to return true");
        return true;
    }

}
