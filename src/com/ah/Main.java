package com.ah;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Main extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;
    public static final int width = 1280, height = width / 17 * 8, dropnumber = 1000;
    public boolean running = false;
    public Thread thread;
    public Drops[] drops = new Drops[dropnumber];

    public Main(){
        for (int i = 0 ; i < dropnumber ; i++){
            drops[i] = new Drops();
        }
    }
    public synchronized void start(){
        if(running)
            return;
        thread = new Thread(this, "RainThread");
        thread.start();
        running = true;
    }
    public void run(){
        while(running){
            update();
            render();
        }
        stop();
    }
    private void render(){
        BufferStrategy bs = getBufferStrategy();
        if (bs == null){
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0,width,height); // BG

        for (int i=0; i<dropnumber; i++){
            drops[i].draw(g);
        }

        g.dispose();
        bs.show();

    }

    public void update(){
        for(int i = 0; i < dropnumber; i++){
            drops[i].update();
        }
    }

    public synchronized void stop(){
        if(running)
            return;
        running = false;
    }


    public static void main(String[] args) {
	Main main = new Main();
	JFrame frame = new JFrame("Rain");
	frame.add(main);
	frame.setSize(width, height);
	frame.setResizable(false);
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
        System.out.println("Initialized frame with: With " + width + " Height: " + height);
    main.start();
    }
}
