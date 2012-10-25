package example;

public class ImperativeStereotypesTest extends StereotypesContract {
    @Override
    protected ImperativeSterotypes find(String name) {
        return ImperativeSterotypes.find(name);
    }
}