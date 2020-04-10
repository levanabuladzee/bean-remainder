package validator;

import annotation.GEOCity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GEOCityValidator implements ConstraintValidator<GEOCity, String> {
    String[] cities = {"Tbilisi", "Batumi", "Kutaisi", "Rustavi", "Gori",
            "Zugdidi", "Poti", "Khashuri", "Samtredia", "Senaki",
            "Zestaponi", "Marneuli", "Telavi", "Akhaltsikhe", "Kobuleti",
            "Ozurgeti", "Kaspi", "Chiatura", "Tsqaltubo", "Sagarejo",
            "Gardabani", "Borjomi", "Tkibuli", "Khoni", "Bolnisi",
            "Akhalkalaki", "Gurjaani", "Mtskheta", "Kvareli", "Akhmeta",
            "Kareli", "Lanchkhuti", "Tsalenjikha", "Dusheti", "Sachkhere",
            "Dedoplistsqaro", "Lagodekhi", "Ninotsminda", "Abasha", "Tsnori",
            "Terjola", "Martvili", "Jvari", "Khobi", "Vani",
            "Baghdati", "Vale", "Tetritsqaro", "Tsalka", "Dmanisi",
            "Oni", "Ambrolauri", "Sighnaghi", "Tsageri", "Sukhumi",
            "Tkvarcheli", "Ochamchire", "Gali", "Gudauta", "Pitsunda",
            "Gulripshi", "Gagra", "New Athos", "Tskhinvali", "Lazica"};

    @Override
    public void initialize(GEOCity constraintAnnotation) {

    }

    @Override
    public boolean isValid(String city, ConstraintValidatorContext constraintValidatorContext) {
        if (city == null) {
            return true;
        }

        for (String i : cities) {
            if (i.equals(city.toString())){
                return true;
            }
        }

        return false;
    }
}
