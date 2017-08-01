package data;

import static helpers.Artist.DrawQuadTex;
import static helpers.Clock.Delta;

import org.newdawn.slick.opengl.Texture;

public class Entity {
	private int width, height, origYSpeed, origXSpeed;
	private double ySpeed, xSpeed;
	private float x, y;
	private Texture texture;
	private boolean first = true;
	
	public Entity(Texture texture, int x, int y, int width, int height, int origYSpeed, int origXSpeed, double xSpeed, double ySpeed) {
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.origXSpeed = origXSpeed;
		this.origYSpeed= origYSpeed;
	}
	
	public void Draw() {
		DrawQuadTex(texture, x, y, width, height);
	}
	
	public void Update() {
		if(first) {
			first = false;
		} else {
			x += Delta() * xSpeed;
			y += Delta() * ySpeed;
		}
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getOrigYSpeed() {
		return origYSpeed;
	}

	public void setOrigYSpeed(int origYSpeed) {
		this.origYSpeed = origYSpeed;
	}

	public int getOrigXSpeed() {
		return origXSpeed;
	}

	public void setOrigXSpeed(int origXSpeed) {
		this.origXSpeed = origXSpeed;
	}

	public double getySpeed() {
		return ySpeed;
	}

	public void setySpeed(double ySpeed) {
		this.ySpeed = ySpeed;
	}

	public double getxSpeed() {
		return xSpeed;
	}

	public void setxSpeed(double xSpeed) {
		this.xSpeed = xSpeed;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	
	
}
