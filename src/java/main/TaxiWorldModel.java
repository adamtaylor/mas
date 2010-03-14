package main;

import java.util.logging.Logger;

import jason.environment.grid.GridWorldModel;
import jason.environment.grid.Location;

public class TaxiWorldModel extends GridWorldModel {
    private Logger logger = Logger.getLogger("MAS."+TaxiWorldModel.class.getName());
    
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
    
    public boolean hasCustomer() {
        return hasCustomer;
    }
    
    public boolean isAtTaxiRank() {
        return isAtTaxiRank;
    }
    
    public int getTAXIRANK() {
        return TAXIRANK;
    }
    
    public int getCINEMA() {
        return CINEMA;
    }
    
    public boolean moveTowards(Location dest) {
        logger.info("attempting to move the taxi");
        Location taxi = getAgPos(0);
        logger.info("taxi.x && taxi.yy == "+taxi.x+" "+taxi.y);
        if (taxi.x < dest.x)        taxi.x++;
        else if (taxi.x > dest.x)   taxi.x--;
        if (taxi.y < dest.y)        taxi.y++;
        else if (taxi.y > dest.y)   taxi.y--;
        setAgPos(0, taxi); 
        logger.info("taxi.x && taxi.yy == "+taxi.x+" "+taxi.y);

        if (view != null) {
            view.update(lTaxiRank.x,lTaxiRank.y);
            view.update(lCinema.x,lCinema.y);
        }
        return true;
    }

}
