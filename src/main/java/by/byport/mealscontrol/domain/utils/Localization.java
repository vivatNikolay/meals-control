package by.byport.mealscontrol.domain.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class Localization {
    private static Localization instance = new Localization();
    private Locale locale;
    private ResourceBundle rb;
    private Localization() {
        locale = Locale.getDefault();
        rb = ResourceBundle.getBundle("messages", locale);
    }

    public static Localization getInstance() {
        return instance;
    }

    public String translate(String key) {
        return rb.getString(key);
    }
}
