package web.beens;

//import com.geser.entities.ClazzEntity;
//import com.geser.service.ClazzService;
//import com.geser.service.EventService;
//import com.geser.utils.DBLogger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

/**
 * Created by root on 30.01.17.
 */
@ManagedBean
@SessionScoped
public class TableData implements Serializable {

//    public static final ClazzEntity[] testClassTable = {
//            new ClazzEntity(new ClazzService().findByName("LogoDesignBuyingProcess").getClassId(), new ClazzService().findByName("LogoDesignBuyingProcess").getName()),
//            new ClazzEntity(new ClazzService().findByName("Email Hosting").getClassId(), new ClazzService().findByName("Email Hosting").getName())
//    };
//
//    public  ClazzEntity[] getClassEntity(){
//        return testClassTable;
//    }


    private static final Name[] testsData = {

            new Name("Dmitriy", "Frolov"),
            new Name("Ilia", "Zviagintsev"),
            new Name("Sergiy", "Konoplyaniy"),
            new Name("Valentyn", "Goroduk")
    };

    private static final Name[] names = {
            new Name("Dmitriy", "Frolov"),
            new Name("Ilia", "Zviagintsev"),
            new Name("Sergiy", "Konoplyaniy"),
            new Name("Valentyn", "Goroduk")
    };

    public Name[] getNames() {
        return names;
    }
}
