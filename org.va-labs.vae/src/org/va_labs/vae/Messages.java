/*
 * Created on Jul 25, 2004
 *
 * $Id: Messages.java,v 1.1 2004/08/07 10:15:17 mojo_jojo Exp $
 */
package org.va_labs.vae;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author mojo_jojo
 * TODO Comment the Messages class.
 */
public class Messages {
    private static final String BUNDLE_NAME = "org.va_labs.vae.messages";//$NON-NLS-1$

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
            .getBundle(BUNDLE_NAME);

    private Messages() {
    }

    public static String getString(String key) {
        // TODO Auto-generated method stub
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
}