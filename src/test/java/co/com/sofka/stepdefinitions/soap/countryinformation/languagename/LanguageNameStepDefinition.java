package co.com.sofka.stepdefinitions.soap.countryinformation.languagename;


import co.com.sofka.questions.countryinformation.LanguageNameQuestion;
import co.com.sofka.stepdefinitions.soap.countryinformation.setup.SetUp;
import co.com.sofka.task.countryinformation.LanguageNameTask;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static co.com.sofka.util.FilesUtilities.readFile;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;

public class LanguageNameStepDefinition extends SetUp {

    private static final String LANGUAGE_NAME_XML = System.getProperty("user.dir") + "\\src\\test\\resources\\countryinformation\\LanguageName.xml";
    private static final String S_ISO_CODE = "[sISOCode]";
    String MSJ_NOT_FOUND = "Language ISOS Code not found!";

    @Given("que el automatizador ingresa un código iso {string}")
    public void queElAutomatizadorIngresaUnCódigoIso(String isoCode) {
        setUp();
        bodyRequest = defineBodyRequest(isoCode);
        actor.attemptsTo();
    }

    @When("el automatizador ejecuta el servicio")
    public void elAutomatizadorEjecutaElServicio() {
        actor.attemptsTo(
                new LanguageNameTask()
                        .usingThe(RESOURCE)
                        .with(headers())
                        .and(bodyRequest)
        );
    }

    @Then("el servicio Language Name arroja el nombre del idioma de acuerdo al código iso {string}")
    public void elServicioLanguageNameArrojaElNombreDelIdiomaDeAcuerdoAlCodigoIso(String nameLanguage) {
        actor.should(
                seeThatResponse("El código de respuesta HTTP deber ser: " + SC_OK,
                        response -> response.statusCode(SC_OK)
                ),
                seeThat(
                        "El nombre del idioma debe ser: ",
                        new LanguageNameQuestion(fromLastResponseBy(actor)),
                        containsString("<m:LanguageNameResult>" + nameLanguage + "</m:LanguageNameResult>")
                )
        );
    }

    @Given("el automatizador ingresa el {string} del idioma")
    public void elAutomatizadorIngresaElDelIdioma(String string) {
        setUp();
        bodyRequest = defineBodyRequest(string);
        actor.attemptsTo();
    }

    @When("el automatizador ejecuta el servicio para obtener el nombre del idioma")
    public void elAutomatizadorEjecutaElServicioParaObtenerElNombreDelIdioma() {
        actor.attemptsTo(
                new LanguageNameTask()
                        .usingThe(RESOURCE)
                        .with(headers())
                        .and(bodyRequest)
        );
    }

    @Then("el servicio Language Name arroja el mensaje de código no encontrado {string}")
    public void elServicioLanguageNameArrojaElMensajeDeCodigoNoEncontrado(String msjFail) {
        actor.should(
                seeThatResponse("El código de respuesta HTTP deber ser: " + SC_OK,
                        response -> response.statusCode(SC_OK)
                ),
                seeThat(
                        "El mensaje del servicio no encontrado es: ",
                        new LanguageNameQuestion(fromLastResponseBy(actor)),
                        containsString("<m:LanguageNameResult>" + MSJ_NOT_FOUND + "</m:LanguageNameResult>")
                )
        );

    }

    private String defineBodyRequest(String sISOCode) {
        return readFile(LANGUAGE_NAME_XML)
                .replace(S_ISO_CODE, sISOCode);

    }
}
