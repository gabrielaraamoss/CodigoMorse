package Estructura;
        
import java.io.File;
import java.io.IOException;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gabrielaramos
 */

import java.io.File;
import java.io.IOException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

//import javax.sound.sampled.AudioInputStream;
//import javax.sound.sampled.AudioSystem;
//import javax.sound.sampled.Clip;
//import javax.sound.sampled.DataLine;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gabrielaramos
 */
public class Sonido extends Thread {
    private String palabra;
    
    public Sonido(String palabra){
        this.palabra=palabra;
    }
    
    @Override
    public void run() {
        for(char l: palabra.toCharArray()){
             if(l =='.'){
                 playPunto();
                 try{
                     Thread.sleep(100);
                 }catch(InterruptedException e){
                     
                 }           
            
            }else if(l =='-'){
                playRaya();
                try{
                    Thread.sleep(100);
                }catch(InterruptedException e){
                    
                } 
            } else if(l ==' '){
                playPunto();
                try{
                    Thread.sleep(100);
                }catch(InterruptedException e){
                    
                }
            } 

        }
    
    }
    
    private void playPunto(){
//        File f = new File("/Users/gabrielaramos/Downloads/Sonidos/punto.wav");
        Media  media = new Media("/Users/gabrielaramos/Downloads/Sonidos/punto.wav");
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.play();        
//        AudioInputStream sound = AudioSystem.getAudioInputStream(f);
//
//        DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
//        Clip clip = (Clip) AudioSystem.getLine(info);
//        clip.open(sound);
//
//        clip.start();
//        clip.drain();
//        clip.close();    
    
    }

    
    private void playRaya(){
//        File f = new File("/Users/gabrielaramos/Downloads/Sonidos/raya.wav");

        Media  media = new Media("/Users/gabrielaramos/Downloads/Sonidos/raya.wav");
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.play();
//        AudioInputStream sound = AudioSystem.getAudioInputStream(f);
//
//        DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
//        Clip clip = (Clip) AudioSystem.getLine(info);
//        clip.open(sound);
//
//        clip.start();
//        clip.drain();
//        clip.close();    
    
    }
    
}


