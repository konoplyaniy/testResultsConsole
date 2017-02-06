package web.beens;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.Locale;

/**
 * Created by root on 26.01.17.
 */

@ManagedBean
@SessionScoped
public class UserProfile {
    private String locale="en";

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String changeLanguage(String locale){
        this.locale=locale;
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(locale));
        return locale;
    }
}
