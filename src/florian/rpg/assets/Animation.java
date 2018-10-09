package florian.rpg.assets;

import java.awt.image.BufferedImage;

public class Animation {

	private BufferedImage[] frames;
	private int currentFrame;
	private int fps, delta, count;
	
	public Animation(BufferedImage[] frames, int fps, int count){
		this.frames = frames;
		this.fps = fps;
		this.count = count;
	}
	
	public BufferedImage getCurrentFrame(){
		return frames[currentFrame];
	}
	
	public BufferedImage getFrame(int ID){
		return frames[ID];
	}
	
	public void tick(){
		delta++;
		if(delta >= fps / count){
			delta -= fps / count;
			currentFrame++;
			if(currentFrame >= frames.length)
				currentFrame = 0;
		}
	}
	
	public void reset(){
		currentFrame = 0;
	}
	
}
