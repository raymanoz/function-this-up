package example;

import com.googlecode.totallylazy.Function1;
import com.googlecode.totallylazy.Predicates;
import com.googlecode.totallylazy.comparators.Comparators;

import java.util.List;

import static com.googlecode.totallylazy.Predicates.exists;
import static com.googlecode.totallylazy.Sequences.cons;
import static com.googlecode.totallylazy.Sequences.sequence;
import static com.googlecode.totallylazy.Strings.equalIgnoringCase;

public enum FunctionalStereotype {
    australia("a person from australia", "aussie", "legend"),
    newzealand("a person from new zealand", "kiwi"),
    preston("a person from preston", "scally"),
    liverpool("a person from liverpool", "scouser", "thief"),
    manchester("a person from manchester", "manc", "mancunian", "fighter");

    private List<String> allNames;

    FunctionalStereotype(String description, String... allNames) {
        this.allNames = cons(name(), cons(description, sequence(allNames))).toList();
    }

    public static FunctionalStereotype find(String name) throws IllegalArgumentException {
        return sequence(values()).
                find(Predicates.where(allNames(), exists(equalIgnoringCase(name)))).
                getOrThrow(exception(name));
    }

    private static Exception exception(String name) {
        final String values = sequence(values()).flatMap(allNames()).sortBy(Comparators.<String>ascending()).toString(", ");
        return new IllegalArgumentException(("Invalid Stereotype [" + name + "]. Must be one of [" + values + "]"));
    }

    private static Function1<FunctionalStereotype, List<String>> allNames() {
        return new Function1<FunctionalStereotype, List<String>>() {
            @Override
            public List<String> call(FunctionalStereotype value) throws Exception {
                return value.allNames;
            }
        };
    }
}