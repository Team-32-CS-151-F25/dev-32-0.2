package cs151.application;

public class Test {
    public static void main(String[] args) {
        System.out.println("java.version=" + System.getProperty("java.version"));
        System.out.println("java.vendor=" + System.getProperty("java.vendor"));
        System.out.println("java.runtime.name=" + System.getProperty("java.runtime.name"));
        System.out.println("java.runtime.version=" + System.getProperty("java.runtime.version"));
        System.out.println("os.name=" + System.getProperty("os.name"));
        System.out.println("os.version=" + System.getProperty("os.version"));
        System.out.println("os.arch=" + System.getProperty("os.arch"));
        System.out.println("javafx.version=" + System.getProperty("javafx.version")); // present when bundled

    }
}
