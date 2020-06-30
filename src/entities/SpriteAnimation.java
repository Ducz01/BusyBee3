package entities;

/**
 * Diese Klasse dient zum Erstellen der Animation von Sprites.
 * @author Mona Urban
 **/
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpriteAnimation extends Transition {
	private final ImageView imageView;
	private final int count;
	private final int columns;
	private int offsetX;
	private long offsetY;
	private final int width;
	private final int height;

	private int lastIndex;

	// Konstruktor zum Erzeugen der Transition
	public SpriteAnimation(ImageView imageView, Duration duration, int count, int columns, int offsetX, long offsetY,
			int width, int height) {
		this.imageView = imageView;
		this.count = count;
		this.columns = columns;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.width = width;
		this.height = height;
		setCycleDuration(duration);
		setCycleCount(Animation.INDEFINITE);
		setInterpolator(Interpolator.LINEAR);
		this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
	}

	// Das Offset von X setzen, um gew�nschtes Bild zu zeigen
	public void setOffsetX(int x) {
		this.offsetX = x;
	}

	// Das Offset von Y setzen, um gew�nschte Reihe anzuzeigen
	public void setOffsetY(long l) {
		this.offsetY = l;
	}

	// Interpolation
	@Override
	protected void interpolate(double k) {
		final int index = Math.min((int) Math.floor(k * count), count - 1);
		if (index != lastIndex) {
			final int x = (index % columns) * width + offsetX;
			final long y = (index / columns) * height + offsetY;
			imageView.setViewport(new Rectangle2D(x, y, width, height));
			lastIndex = index;
		}
	}

}
