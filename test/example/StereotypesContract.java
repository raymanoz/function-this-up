package example;

import org.junit.Test;

import static example.ImperativeSterotypes.manchester;
import static example.ImperativeSterotypes.newzealand;
import static junit.framework.Assert.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public abstract class StereotypesContract {
    protected abstract ImperativeSterotypes find(String name);

    @Test
    public void valueForNameShouldReturnValidPortalWhenPassedDescription() {
        assertThat(find("a person from new zealand"), is(newzealand));
    }

    @Test
    public void valueForNameShouldReturnValidPortalWhenPassedEnumValueItself() {
        assertThat(find("manchester"), is(manchester));
    }

    @Test
    public void valueForNameShouldReturnValidPortalWhenPassedAlternativeName() {
        assertThat(find("thief"), is(ImperativeSterotypes.liverpool));
    }

    @Test
    public void valueForNameShouldBeCaseInsensitive() {
        assertThat(find("scally"), is(ImperativeSterotypes.preston));
        assertThat(find("SCALLY"), is(ImperativeSterotypes.preston));
        assertThat(find("ScAlLy"), is(ImperativeSterotypes.preston));
    }

    @Test
    public void valueForNameShouldThrowErrorWhenPassedInvalidString() {
        try {
            find("Awesome");
            fail("Did not throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is(
                    "Invalid Portal [Awesome]. Must be one of [a person from australia, a person from liverpool, " +
                            "a person from manchester, a person from new zealand, a person from preston, aussie, " +
                            "fighter, kiwi, legend, manc, scally, scouser, thief]"));
        }
    }
}