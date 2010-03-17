package main;

import java.awt.Color;
import java.awt.Graphics;

import jason.environment.grid.GridWorldModel;
import jason.environment.grid.GridWorldView;
import jason.environment.grid.Location;

public class TaxiWorldView extends GridWorldView {
    private TaxiWorldModel taxiModel;
    private static final int TAXIRANK = 16;
    private static final int CINEMA = 32;
    private static final int UNIVERSITY = 64;
    
    
    public TaxiWorldView(TaxiWorldModel model, String title, int windowSize) {
        super(model, title, windowSize);
        taxiModel = model;
        setVisible(true);
        repaint();
    }

    /** draw application objects */
    @Override
    public void draw(Graphics g, int x, int y, int object) {
        Location lTaxi = taxiModel.getAgPos(0);
        super.drawAgent(g, x, y, Color.lightGray, -1);
        
        
        
        switch (object) {
            case TAXIRANK: 
                if (lTaxi.equals(taxiModel.lTaxiRank)) {
                    super.drawAgent(g, x, y, Color.yellow, -1);
                }
                g.setColor(Color.black);
                drawString(g, x, y, defaultFont, "Taxi Rank");  
                break;
            case CINEMA:
                if (lTaxi.equals(taxiModel.lCinema)) {
                    super.drawAgent(g, x, y, Color.yellow, -1);
                }
                g.setColor(Color.black);
                drawString(g, x, y, defaultFont, "Cinema");  
                break;
            case UNIVERSITY:
                if (lTaxi.equals(taxiModel.lUniversity)) {
                    super.drawAgent(g, x, y, Color.yellow, -1);
                }
                g.setColor(Color.black);
                drawString(g, x, y, defaultFont, "University");  
                break;
        }
    }
    
    @Override
    public void drawAgent(Graphics g, int x, int y, Color c, int id) {
        Location lTaxi = taxiModel.getAgPos(0);
        if (!lTaxi.equals(taxiModel.lTaxiRank) && !lTaxi.equals(taxiModel.lCinema)) {
            c = Color.green;
            if (taxiModel.hasCustomer()) c = Color.red;
            super.drawAgent(g, x, y, c, -1);
            g.setColor(Color.black);
            super.drawString(g, x, y, defaultFont, "Taxi");
        }
    }
    
}
