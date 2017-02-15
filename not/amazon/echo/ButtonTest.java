package not.amazon.echo;

import static org.junit.Assert.assertTrue;

/**
 * Created by AliciaDaurignac on 15/02/2017.
 * This tests the Button, ButtonPressedEvent and ButtonPressedListener classes.
 */
public class ButtonTest {

    Button button;
    MockButtonPressedImpl listener;

    @org.junit.Before
    public void setUp() throws Exception {
        button = new Button();
        listener = new MockButtonPressedImpl();

    }


    /**
     * Tests that addListener and pressButton both work as expected.
     * listener.pressed can only be set to true if the event has been fired properly.
     *
     * @throws Exception
     */
    @org.junit.Test
    public void testButton() throws Exception {
        button.addListener(listener);
        button.pressButton();
        assertTrue(listener.pressed);

    }

    /**
     * Mock object to test the ButtonPressedEvent.
     */
    private class MockButtonPressedImpl implements ButtonPressedListener {
        public boolean pressed = false;

        /**
         * Sets pressed to true only if the source of the press is the button in this test class.
         *
         * @param event is the event
         */
        @Override
        public void buttonPressed(ButtonPressedEvent event) {
            if (event.getSource().equals(button)) {
                pressed = true;
            }


        }
    }
}