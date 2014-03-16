package faceclicker;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Stores the relevant information for one Salient Point. Contains x and y 
 * values for the point, a name, and the circle object associated with the 
 * point.
 *
 * @author Alex McKay
 */
public class SalientPoint {

    /* x and y values corresponding to this point's location */
    private double x, y;
    
    /* The name of the point */
    private String name;
    
    /* The circle object associated with the point (displayed on the image) */
    private Circle c;

    /**
     * Constructor method for an unspecified point. Sets x and y to 0 initially.
     *
     * @param name The name to be assigned to the salient point.
     */
    public SalientPoint(String name) {
        x = 0;
        y = 0;
        this.name = name;
        c = new Circle();
        c.setRadius(2);
        c.setFill(Color.RED);
    }
    
    /**
     * Constructor method for a known point.
     *
     * @param name The name to be assigned to the salient point.
     * @param x The x value of the point.
     * @param y The y value of the point.
     */
    public SalientPoint(String name, double x, double y) {
        this.x = x;
        this.y = y;
        this.name = name;
        c = new Circle();
        c.setRadius(2);
        c.setFill(Color.RED);
    }

    /**
     * Set a new x value for the salient point.
     *
     * @param x The desired new value.
     */
    public void setX(double x) {
        this.x = x;
        c.setCenterX(x);
    }

    /**
     * Set a new y value for the salient point.
     *
     * @param y The desired new value.
     */
    public void setY(double y) {
        this.y = y;
        c.setCenterY(y);
    }

    /**
     * Retrieve the x value of the salient point.
     *
     * @return The x value of the salient point.
     */
    public double getX() {
        return x;
    }

    /**
     * Retrieve the y value of the salient point.
     *
     * @return The y value of the salient point.
     */
    public double getY() {
        return y;
    }

    /**
     * Retrieve the name of the salient point.
     *
     * @return The name of the salient point.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieve the circle object associated with the salient point.
     *
     * @return The circle object associated with the salient point.
     */
    public Circle getCircle() {
        return c;
    }
    
    /**
     * Prints the name and x/y values of the point to terminal.
     *
     */
        public void printValues() {
        System.out.printf("%s: x = %.0f y = %.0f \n", name, x, y);
    }

    /**
     * Retrieves the name and x/y values of the point.
     *
     * @return String formatted "[name]: x = [x] y = [y]"
     */
    @Override
    public String toString() {
        return String.format("%s: x = %.0f y = %.0f", name, x, y);
    }
}
