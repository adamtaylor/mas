package main;
import jason.environment.Environment;
import jason.asSyntax.*;

public class TaxiEnvironment extends Environment {
    TaxiWorldModel model;
    
    @Override
    public void init(String[] args) {
        model = new TaxiWorldModel(1,2,3);
        
        if (args.length == 1 && args[0].equals("gui")) {
            TaxiWorldView view = new TaxiWorldView(model,"Taxi World",800);
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
