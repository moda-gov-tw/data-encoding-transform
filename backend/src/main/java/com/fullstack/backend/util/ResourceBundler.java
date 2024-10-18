package com.fullstack.backend.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

public class ResourceBundler {

    public static String getProps(String filename, final String key) throws MissingResourceException {
        final ResourceBundle rb = ResourceBundle.getBundle(filename);
        return rb.getString(key);
    }

    public static Set<String> keySet(String filename) throws MissingResourceException {
        final ResourceBundle rb = ResourceBundle.getBundle(filename);
        return rb.keySet();
    }
}
