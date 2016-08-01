/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threeD;

import physics.Space;

/**
 *
 * @author janne
 */
public class RunnableSpace implements Runnable{
    Space space;
    
    boolean pause = true;
    boolean pause2 = true;
    boolean stop = false;
    public RunnableSpace(Space space){
        this.space = space;
    }
    
    @Override
    public synchronized void run() {
        int times = 0;
        while (stop == false) {
            
            if (pause2 == true || pause == true || times >= 2000) {
                try {
                    wait();
                    times = 0;
                    if (stop == true) {
                        return;
                    }
                } catch (Exception ex) {

                }
            }
            if (!pause2) {
                space.step();
                times++;
            }
        }
    }

    public void pause() {
        pause = true;
    }

    public synchronized void play() {
        pause = false;
        notify();
    }
    public void playPause() {
        pause2 = !pause2;
    }

    public synchronized void stop() {
        
        stop = true;
        notify();
    }


}
