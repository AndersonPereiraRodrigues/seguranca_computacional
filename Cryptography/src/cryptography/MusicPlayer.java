/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptography;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 *
 * @author Anderson
 */
public class MusicPlayer {
    private final String VIDEO_URL = getClass().getResource("/video03.mp4").toString();
 
    public MusicPlayer(){
    
}

public void start(Stage palco) throws Exception {

    Media media = new Media(VIDEO_URL);
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    MediaView mediaView = new MediaView(mediaPlayer);

    StackPane raiz = new StackPane();
    raiz.getChildren().add(mediaView);
    Scene cena = new Scene(raiz, 600, 400);
    palco.setTitle("Tocando Video");
    palco.setScene(cena);
    palco.show();

    mediaPlayer.play();
    }
}
