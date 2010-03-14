package main;
import java.util.logging.Logger;

import jason.environment.Environment;
import jason.environment.grid.Location;
import jason.asSyntax.*;

public class TaxiEnvironment extends Environment {
    private Logger logger = Logger.getLogger("MAS."+TaxiEnvironment.class.getName());
    private TaxiWorldModel model;
    
    @Override
    public void init(String[] args) {
        model = new TaxiWorldModel();
        
        if (args.length == 1 && args[0].equals("gui")) {
            logger.info("attempting to create the GUI");
            TaxiWorldView view = new TaxiWorldView(model,"Taxi World",500);
            model.setView(view);
        }
        
        updatePercepts();

    }
    
    @Override
    public void stop() {
        
    }
    
    public void updatePercepts() {
        
    }
    
    @Override
    public boolean executeAction(String ag, Structure action) {
        System.out.println("["+ag+"] doing: "+action);
        boolean result = false;
//        if (action.equals(of)) { // of = open(fridge)
//            result = model.openFridge();
//            
//        } else if (action.equals(clf)) { // clf = close(fridge)
//            result = model.closeFridge();
//            
        if (action.getFunctor().equals("move_towards")) {
            String l = action.getTerm(0).toString();
            Location dest = null;
            if (l.equals("taxirank")) {
                dest = model.lTaxiRank;
            } else if (l.equals("cinema")) {
                dest = model.lCinema;
            }

            try {
                result = model.moveTowards(dest);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
//        } else if (action.equals(gb)) {
//            result = model.getBeer();
//            
//        } else if (action.equals(hb)) {
//            result = model.handInBeer();
//            
//        } else if (action.equals(sb)) {
//            result = model.sipBeer();
//            
//        } else if (action.getFunctor().equals("deliver")) {
//            // wait 4 seconds to finish "deliver"
//            try {
//                Thread.sleep(4000); 
//                result = model.addBeer( (int)((NumberTerm)action.getTerm(1)).solve());
//            } catch (Exception e) {
//                logger.info("Failed to execute action deliver!"+e);
//            }
//            
//        } else {
//            logger.info("Failed to execute action "+action);
//        }
//
//        if (result) {
//            updatePercepts();
//            try { Thread.sleep(100); } catch (Exception e) {}
//        }
        }
        return result;
    }
    
}
