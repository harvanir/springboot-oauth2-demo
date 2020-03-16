package org.harvanir.oauth2.server.oauth2.support;

/**
 * @author Harvan Irsyadi
 */
public class ClientIdThreadContext {

    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    private ClientIdThreadContext() {
    }

    public static String get() {
        return THREAD_LOCAL.get();
    }

    public static void set(String clientDetails) {
        THREAD_LOCAL.set(clientDetails);
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }
}
