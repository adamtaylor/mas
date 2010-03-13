package main;

import jason.environment.grid.GridWorldModel;

public class TaxiWorldModel extends GridWorldModel {
    private boolean hasCustomer = false;
    private boolean isAtTaxiRank = false;
    
    protected TaxiWorldModel(int arg0, int arg1, int arg2) {
        super(arg0, arg1, arg2);
        // TODO Auto-generated constructor stub
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

}
