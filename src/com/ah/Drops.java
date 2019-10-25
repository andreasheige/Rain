package com.ah;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Drops {
    private Random random;
    private int x,y, ySpeed;

    public Drops() {
        random = new Random();
        x = random.nextInt(Main.width);
        y = random.nextInt(200);
        ySpeed = random.nextInt(9) + 1;
    }
    public void update() {
        y += ySpeed;
        if(y > Main.height){
            y = - random.nextInt(300);
        }
    }
    public void draw(Graphics g){
        g.setColor(Color.CYAN);
        g.fillRect(x,y,5,20);
    }
}
