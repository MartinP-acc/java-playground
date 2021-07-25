import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    public void tests() {
        assertEquals( "camel Casing", Solution.camelCase("camelCasing"));
        assertEquals( "camel Casing Test", Solution.camelCase("camelCasingTest"));
        assertEquals( "camelcasingtest", Solution.camelCase("camelcasingtest"));
    }

}