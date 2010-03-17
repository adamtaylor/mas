package main;
import java.util.logging.Logger;

import jason.environment.Environment;
import jason.environment.grid.Location;
import jason.asSyntax.*;

public class TaxiEnvironment extends Environment {
    private Logger logger = Logger.getLogger("MAS."+TaxiEnvironment.class.getName());
    private TaxiWorldModel model;
    
    //common literals
    public static final Literal gc  = Literal.parseLiteral("get(customer)");
    public static final Literal dc  = Literal.parseLiteral("drop(customer)");
    public static final Literal at = Literal.parseLiteral("at(taxi,taxirank)");
    public static final Literal ac = Literal.parseLiteral("at(taxi,cinema)");
    public static final Literal au = Literal.parseLiteral("at(taxi,university)");

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
        
        // manual hack to work(?) for all the taxi agents
        for(int i = 0;i<3;i++) {
            int j = i + 1;
            String name = "taxi" + j;
            
            clearPercepts(name);
            
            // get the taxi location
            Location lTaxi = model.getAgPos(i);
            
            logger.info("taxi - "+name+ " - is at: "+lTaxi.x+" "+lTaxi.y);

            
            // add agent location to its percepts
            if (lTaxi.equals(model.lTaxiRank)) {
                logger.info("taxi - "+name+ " - is at taxi rank");

                addPercept(name, at);
            }
            if (lTaxi.equals(model.lCinema)) {
                addPercept(name, ac);
            }
            if (lTaxi.equals(model.lUniversity)) {
                addPercept(name, au);
            } 
        }

        
    }
    
    @Override
    synchronized public boolean executeAction(String ag, Structure action) {
        logger.info("["+ag+"] doing: "+action);
        boolean result = false;
        
        int agInt = Integer.parseInt(ag.substring(4,5));
        agInt = agInt - 1;
        
        logger.info("agInt: "+agInt);

        
        if (action.getFunctor().equals("move_towards")) {
            String l = action.getTerm(0).toString();
            Location dest = null;
            if (l.equals("taxirank")) {
                dest = model.lTaxiRank;
            } else if (l.equals("cinema")) {
                dest = model.lCinema;
            } else if (l.equals("university")) {
                dest = model.lUniversity;
            }

            try {
                result = model.moveTowards(dest,agInt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (action.equals(gc)) {
            logger.info("about to getCustomer() "+action);
            result = model.getCustomer();
            
        } else if (action.equals(dc)) {
            result = model.dropCustomer();
        } else {
            logger.info("Failed to execute action "+action);
        }

        if (result) {
            updatePercepts();
            try { Thread.sleep(100); } catch (Exception e) {}
        }
        return result;
    }
    
}
