package sample;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

import javafx.util.Duration;

public class SpriteAnimation extends Transition {
    private final ImageView imageview;
    private final int count;
    private final int columns;
    private int offSetY;
    private int offSetX;
    private final int Width;
    private final int Height;

    public SpriteAnimation(ImageView imageview, Duration duration, int count, int columns, int offSetX, int offSetY, int Width, int Height) {

        this.imageview = imageview;
        this.count = count;
        this.columns = columns;
        this.offSetX = offSetX;
        this.offSetY = offSetY;
        this.Width = Width;
        this.Height = Height;

        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);

        this.imageview.setViewport(new Rectangle2D(offSetX, offSetY, Width, Height));

    }

    public int getOffSetY() {
        return offSetY;
    }

    public int getOffSetX() {
        return offSetX;
    }

    public void setOffSetY(int offSetY) {
        this.offSetY = offSetY;
    }

    public void setOffSetX(int offSetX) {
        this.offSetX = offSetX;
    }

    int lastIndex;

    protected void interpolate(double frac) {

        final int index = Math.min((int) Math.floor(count * frac), count - 1);
        if(index != lastIndex) {
            final int x = (index % columns) * Width + offSetX;
            final int y = (index / columns) * Height + offSetY;
            imageview.setViewport(new Rectangle2D(x, y, Width, Height));
            lastIndex = index;
        }
    }
}

