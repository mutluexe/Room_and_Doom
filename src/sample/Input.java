package sample;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.BitSet;

public class Input {

    /**
     * Bitset which registers if any {@link KeyCode} keeps being pressed or if it is released.
     */
    private BitSet keyboardBitSet = new BitSet();

    // -------------------------------------------------
    // default key codes
    // will vary when you let the user customize the key codes or when you add support for a 2nd player
    // -------------------------------------------------
    private KeyCode attackKey = KeyCode.SPACE;
    private KeyCode upKey = KeyCode.W;
    private KeyCode downKey = KeyCode.S;
    private KeyCode leftKey = KeyCode.A;
    private KeyCode rightKey = KeyCode.D;
    private KeyCode interactKey = KeyCode.E;
    private static boolean isAttacking = false;

    Scene scene;

    public Input(Scene scene) {
        this.scene = scene;
    }

    public void addListeners() {

        scene.addEventFilter(KeyEvent.KEY_PRESSED, keyPressedEventHandler);
        scene.addEventFilter(KeyEvent.KEY_RELEASED, keyReleasedEventHandler);

    }

    public void removeListeners() {

        scene.removeEventFilter(KeyEvent.KEY_PRESSED, keyPressedEventHandler);
        scene.removeEventFilter(KeyEvent.KEY_RELEASED, keyReleasedEventHandler);

    }

    /**
     * "Key Pressed" handler for all input events: register pressed key in the bitset
     */
    private EventHandler<KeyEvent> keyPressedEventHandler = event -> {

            // register key down
            keyboardBitSet.set(event.getCode().ordinal(), true);

        };


    /**
     * "Key Released" handler for all input events: unregister released key in the bitset
     */
    private EventHandler<KeyEvent> keyReleasedEventHandler = event ->  {

            // register key up
            keyboardBitSet.set(event.getCode().ordinal(), false);
            Player.animation.stop();
        };

    public static boolean getIsAttacking() {
        return isAttacking;
    }

    public static void setIsAttacking(boolean isAttacking) {
        Input.isAttacking = isAttacking;
    }
    // -------------------------------------------------
    // Evaluate bitset of pressed keys and return the player input.
    // If direction and its opposite direction are pressed simultaneously, then the direction isn't handled.
    // -------------------------------------------------

    public boolean isMoveUp() {
        return keyboardBitSet.get(upKey.ordinal()) && !keyboardBitSet.get(downKey.ordinal());
    }

    public boolean isMoveDown() {
        return keyboardBitSet.get(downKey.ordinal()) && !keyboardBitSet.get(upKey.ordinal());
    }

    public boolean isMoveLeft() {
        return keyboardBitSet.get(leftKey.ordinal()) && !keyboardBitSet.get(rightKey.ordinal());
    }

    public boolean isMoveRight() {
        return keyboardBitSet.get(rightKey.ordinal()) && !keyboardBitSet.get(leftKey.ordinal());
    }

    public boolean isAttack() {
        return keyboardBitSet.get(attackKey.ordinal());
    }

    public boolean isInteract() {
        return keyboardBitSet.get(interactKey.ordinal());
    }

}