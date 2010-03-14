package main;
import java.util.logging.Logger;

import jason.environment.Environment;
import jason.asSyntax.*;

public class TaxiEnvironment extends Environment {
    private Logger logger = Logger.getLogger("MAS."+TestEnv.class.getName());
    private TaxiWorldModel model;
    
    @Override
    public void init(String[] args) {
        model = new TaxiWorldModel();
        
        if (args.length == 1 && args[0].equals("gui")) {
            logger.info("attempting to create the GUI");
            TaxiWorldView view = new TaxiWorldView(model,"Taxi World",500);
            model.setView(view);
        }
    }
    
    @Override
    public void stop() {
        
    }
    
    @Override
    public boolean executeAction(String ag, Structure act) {
        return false;
    }
    
}
