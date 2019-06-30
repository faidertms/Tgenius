import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

// classe SOM
public class Som {
	private Clip clip; // cria um Clip
	private AudioInputStream Audio; // Cria um AIS
		public void carregar(String name) throws LineUnavailableException, UnsupportedAudioFileException, IOException{
			URL urlClick = getClass().getResource(name); // Recebe e guarda o Caminho do Audio
			clip = AudioSystem.getClip();
			Audio = AudioSystem.getAudioInputStream(urlClick); // Salva o som, a partir do caminho
		}
		public void tocar() throws LineUnavailableException, IOException{
			clip.open(Audio); // Abre o som
			clip.loop(0);	// continua o Som ate o fim
		}
			
}
