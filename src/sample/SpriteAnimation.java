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
    private double offSetY;
    private double offSetX;
    private final double Width;
    private final double Height;

    public SpriteAnimation(ImageView imageview, Duration duration, int count, int columns, double offSetX, double offSetY, double Width, double Height) {

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

    public double getOffSetY() {
        return offSetY;
    }

    public double getOffSetX() {
        return offSetX;
    }

    public void setOffSetY(double offSetY) {
        this.offSetY = offSetY;
    }

    public void setOffSetX(double offSetX) {
        this.offSetX = offSetX;
    }

    int lastIndex;

    protected void interpolate(double frac) {

        final int index = Math.min((int) Math.floor(count * frac), count - 1);
        if(index != lastIndex) {
            final double x = (index % columns) * Width + offSetX;
            final double y = (index / columns) * Height + offSetY;
            imageview.setViewport(new Rectangle2D(x, y, Width, Height));
            lastIndex = index;
        }
    }
}