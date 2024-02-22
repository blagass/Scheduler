package lagasse.scheduler.helper;

import java.util.Locale;
import java.util.ResourceBundle;

public class TimeZone {
    public static void main(String[] args) {
        ResourceBundle rb = ResourceBundle.getBundle("lagasse/c195/Lang", Locale.getDefault());

        if(Locale.getDefault().getLanguage().equals("fr"))
            System.out.println(rb.getString("Hello"));
        else
            System.out.println("Hello");

    }
}
