package sound;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class PlaySound {

	private Clip clip;
	
	public void playSound(URL url) {
		try {
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
		clip = AudioSystem.getClip();
		clip.open(audioInputStream);
		clip.start();
		System.out.println("Dale papu esta sonando ese sonido");
		} catch(Exception ex) {
		System.out.println("Error con el sonido");
		ex.printStackTrace();
		}
		
		
	}
	
	public void endSound() {
		clip.stop();
		clip.flush();
		System.out.println("STOPPING MUSIC");
	}
}
