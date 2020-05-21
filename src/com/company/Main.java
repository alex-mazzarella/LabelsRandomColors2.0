/**
 * Write a GraphicsProgram that creates GLabels for each of the color names RED,
 * ORANGE, YELLOW, GREEN, CYAN, BLUE, and MAGENTA, and then puts those labels up on
 * the screen in a random position and in a random color. It turns out to be difficult to
 * identify the color of such a label if the name says one thing, but its color is different.
 * ********
 * in this 2.0 version,  pressing the mouse button on
 * top of one of the GLabels temporarily resets its color to the one that matches its
 * name. Releasing the mouse button should choose a new random color for the label.
 */

package com.company;

import acm.graphics.GLabel;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import java.awt.event.MouseEvent;
import java.awt.Color;


public class Main extends GraphicsProgram {

    public static void main(String[] args) {

        (new com.company.Main()).start();
    }

    public void run() {

        for (String s : colorLabel) {
            newLabel(s);
        }

        addMouseListeners();

    }

    /****Creates a new label,
     * randomly sets the color of the label and
     * adds the label in the canvas at a random position*/

    private void newLabel(String s) {
        GLabel label = new GLabel(s);
        label.setColor(rgen.nextColor());
        label.setFont("ARIAL-18");
        GPoint randomPoint = generateRandomPoint(label);
        add(label, randomPoint);
    }


    public void mousePressed(MouseEvent e) {

        /* The last mouse position */
        GPoint last = new GPoint(e.getX(), e.getY()); //last point on Canvas where mouse was clicked
        gobj = (GLabel) getElementAt(last);

        if (gobj != null) { //mouse has been pressed over one label
            int i = findRightColor(gobj);
            gobj.setColor(colors[i]);
        }
    }


    public void mouseReleased(MouseEvent e) {
        if (gobj != null) {
            gobj.setColor(rgen.nextColor());
        }
    }

    private GPoint generateRandomPoint(GLabel label) {
        return new GPoint(rgen.nextDouble(0, (getWidth() - label.getWidth())),
                rgen.nextDouble(label.getAscent(), getHeight() - label.getAscent()));
    }

    private int findRightColor(GLabel gobj) {
        String str = gobj.getLabel();
        int i = 0;
        while (!colorLabel[i].equals(str)) {
            i++;
        }
        return i;
    }

    /**Declaring instance variables*/

    private final RandomGenerator rgen = new RandomGenerator();
    private GLabel gobj; /* used to check if there is an existing object where the mouse is clicked */
    String[] colorLabel = new String[]{"RED", "ORANGE", "YELLOW", "GREEN", "CYAN", "BLUE", "MAGENTA"};
    Color[] colors = new Color[]{Color.RED, Color.ORANGE, Color.YELLOW,
            Color.GREEN, Color.CYAN, Color.BLUE, Color.MAGENTA};

}