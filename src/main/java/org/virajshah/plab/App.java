package org.virajshah.plab;

/**
 * Hello world!
 *
 */
public class App {
    static {
        System.loadLibrary("native");
    }

    public static void main(String[] args) {
        new App().helloWorld();
    }

    private native void helloWorld();
}
