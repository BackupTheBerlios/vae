/*
 * Created on Jul 25, 2004
 *
 * $Id: Messages.java,v 1.2 2004/08/17 22:25:15 mojo_jojo Exp $
 */
package org.va_labs.vae;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author mojo_jojo
 * Messages class handling vae strings for i18n..
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