package co.com.sofka.questions.countryinformation;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class LanguageNameQuestion implements Question<String> {

    private final String systemValue;

    public LanguageNameQuestion(String systemValue) {
        this.systemValue = systemValue;
    }

    @Override
    public String answeredBy(Actor actor) {
        return systemValue;
    }
}
