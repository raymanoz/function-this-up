package example;

import org.junit.Test;

import static example.ImperativeSterotype.manchester;
import static example.ImperativeSterotype.newzealand;
import static junit.framework.Assert.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StereotypesTest {
    @Test
    public void valueForNameShouldReturnValidPortalWhenPassedDescription() {
        assertThat(ImperativeSterotype.find("a person from new zealand"), is(newzealand));
    }

    @Test
    public void valueForNameShouldReturnValidPortalWhenPassedEnumValueItself() {
        assertThat(ImperativeSterotype.find("manchester"), is(manchester));
    }

    @Test
    public void valueForNameShouldReturnValidPortalWhenPassedAlternativeName() {
        assertThat(ImperativeSterotype.find("thief"), is(ImperativeSterotype.liverpool));
    }

    @Test
    public void valueForNameShouldBeCaseInsensitive() {
        assertThat(ImperativeSterotype.find("scally"), is(ImperativeSterotype.preston));
        assertThat(ImperativeSterotype.find("SCALLY"), is(ImperativeSterotype.preston));
        assertThat(ImperativeSterotype.find("ScAlLy"), is(ImperativeSterotype.preston));
    }

    @Test
    public void valueForNameShouldThrowErrorWhenPassedInvalidString() {
        try {
            ImperativeSterotype.find("Awesome");
            fail("Did not throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is(
                    "Invalid Portal [Awesome]. Must be one of [a person from australia, a person from liverpool, " +
                            "a person from manchester, a person from new zealand, a person from preston, aussie, australia, " +
                            "fighter, kiwi, legend, liverpool, manc, manchester, newzealand, preston, scally, scouser, thief]"));
        }
    }
}