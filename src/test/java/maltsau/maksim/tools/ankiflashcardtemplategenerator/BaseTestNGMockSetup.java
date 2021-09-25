package maltsau.maksim.tools.ankiflashcardtemplategenerator;

import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * Utility base class which allows setting mocks using Mockito annotations (@Mock, @Spy, @Captor).
 * Date: 23.09.2021
 *
 * @author Maksim Maltsau
 */
public abstract class BaseTestNGMockSetup {

    private AutoCloseable mocksClosable;

    @BeforeClass
    public void setUpMocks() {
        mocksClosable = MockitoAnnotations.openMocks(this);
    }

    @AfterClass
    public void releaseMocks() throws Exception {
        mocksClosable.close();
    }

}
