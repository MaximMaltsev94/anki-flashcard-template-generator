package maltsau.maksim.tools.ankiflashcardtemplategenerator.service.linkgenerator;

import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class SimpleTemplateLinkGeneratorImplTest {

    private static final String TEST_TEMPLATE = "test_%s_test";

    private SimpleTemplateLinkGeneratorImpl simpleTemplateLinkGenerator =
            new SimpleTemplateLinkGeneratorImpl(TEST_TEMPLATE);

    @Test
    public void testGenerateLink() {
        //given

        //when
        String generatedLink = simpleTemplateLinkGenerator.generateLink("word for search");

        //then

        assertThat(generatedLink, is(containsString("word+for+search")));
    }

    @Test
    public void testGenerateLinkNotFailOnNull() {
        //given

        //when
        String generatedLink = simpleTemplateLinkGenerator.generateLink(null);

        //then

        assertThat(generatedLink, is(containsString("test__test")));
    }
}