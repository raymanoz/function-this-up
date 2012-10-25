package example;

import org.junit.Test;

import static example.Sterotypes.manchester;
import static example.Sterotypes.newzealand;
import static junit.framework.Assert.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StereotypesTest {
    @Test
    public void valueForNameShouldReturnValidPortalWhenPassedDescription() {
        assertThat(Sterotypes.find("a person from new zealand"), is(newzealand));
    }

    @Test
    public void valueForNameShouldReturnValidPortalWhenPassedEnumValueItself() {
        assertThat(Sterotypes.find("manchester"), is(manchester));
    }

    @Test
    public void valueForNameShouldReturnValidPortalWhenPassedAlternativeName() {
        assertThat(Sterotypes.find("thief"), is(Sterotypes.liverpool));
    }

    @Test
    public void valueForNameShouldBeCaseInsensitive() {
        assertThat(Sterotypes.find("scally"), is(Sterotypes.preston));
        assertThat(Sterotypes.find("SCALLY"), is(Sterotypes.preston));
        assertThat(Sterotypes.find("ScAlLy"), is(Sterotypes.preston));
    }

    @Test
    public void valueForNameShouldThrowErrorWhenPassedInvalidString() {
        try {
            Sterotypes.find("Awesome");
            fail("Did not throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is(
                    "Invalid Portal [Awesome]. Must be one of [a person from australia, a person from liverpool, " +
                            "a person from manchester, a person from new zealand, a person from preston, aussie, " +
                            "fighter, kiwi, legend, manc, scally, scouser, thief]"));
        }
    }
}