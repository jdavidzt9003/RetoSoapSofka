package co.com.sofka.runner.soap.countryinformation.languagename;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberSerenityRunner;
import org.junit.runner.RunWith;

@RunWith(CucumberSerenityRunner.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/features/soap/countryinformation/languagename/LanguageName.feature"},
        glue = {"co.com.sofka.stepdefinitions.soap.countryinformation.languagename"}
)
public class LanguageNameTest {
}
