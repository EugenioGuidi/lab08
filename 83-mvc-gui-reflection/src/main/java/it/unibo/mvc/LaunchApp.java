package it.unibo.mvc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.controller.DrawNumberControllerImpl;
import it.unibo.mvc.model.DrawNumberImpl;
import it.unibo.mvc.view.DrawNumberStdoutView;
import it.unibo.mvc.view.DrawNumberSwingView;

/**
 * Application entry-point.
 */
public final class LaunchApp {

    private LaunchApp() { }

    /**
     * Runs the application.
     *
     * @param args ignored
     * @throws ClassNotFoundException if the fetches class does not exist
     * @throws SecurityException 
     * @throws NoSuchMethodException if the 0-ary constructor do not exist
     * @throws InvocationTargetException if the constructor throws exceptions
     * @throws InstantiationException if the constructor throws exceptions
     * @throws IllegalAccessException in case of reflection issues
     * @throws IllegalArgumentException in case of reflection issues
     */
    public static void main(final String... args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        final var model = new DrawNumberImpl();
        final DrawNumberController app = new DrawNumberControllerImpl(model);
        final Class<?> class1 = Class.forName("it.unibo.mvc.view.DrawNumberSwingView");
        final Class<?> class2 = Class.forName("it.unibo.mvc.view.DrawNumberStdoutView");
        var swingView = class1.getConstructor(null).newInstance();
        app.addView((DrawNumberView) swingView);
        swingView = class1.getConstructor(null).newInstance();
        app.addView((DrawNumberView) swingView);
        swingView = class1.getConstructor(null).newInstance();
        app.addView((DrawNumberView) swingView);

        var stdoutView = class2.getConstructor(null).newInstance();
        app.addView((DrawNumberView) stdoutView);
        stdoutView = class2.getConstructor(null).newInstance();
        app.addView((DrawNumberView) stdoutView);
        stdoutView = class2.getConstructor(null).newInstance();
        app.addView((DrawNumberView) stdoutView);
    }
}
