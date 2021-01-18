//package Estructura;
//
//
//import java.io.File;
//import java.io.IOException;
//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
///**
// *
// * @author gabrielaramos
// */
//public class Sonido extends Thread{
//    String palabra;
//    
//    public Sonido(){}
//    
//    public Sonido(String palabra){
//        this.palabra=palabra;
//    }
//    
//    public void run(){
//        for(char l: palabra.toCharArray()){
//            if(l =='-'){
//                try{
//                    playRaya();
//                }catch(IOException e){
//                    e.printStackTrace();
//                }
//                
//            
//            
//            }
//        }
//
//    }
//    
//    
//    
//    private void playPunto(){
//        File f = new File(ruta);
//        Media media = new Media(f);
//        AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
//
//        DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
//        Clip clip = (Clip) AudioSystem.getLine(info);
//        clip.open(sound);
//
//        clip.start();
//        clip.drain();
//        clip.close();    
//    
//    }
//}
