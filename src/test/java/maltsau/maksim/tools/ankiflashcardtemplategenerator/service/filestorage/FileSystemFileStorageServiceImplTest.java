package maltsau.maksim.tools.ankiflashcardtemplategenerator.service.filestorage;

import maltsau.maksim.tools.ankiflashcardtemplategenerator.BaseTestNGMockSetup;
import maltsau.maksim.tools.ankiflashcardtemplategenerator.domain.CardType;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class FileSystemFileStorageServiceImplTest extends BaseTestNGMockSetup {

    private static final String TEST_DIR = System.getProperty("user.dir") + File.separator + "export" + File.separator;


    private static final String EXPORT_BASE_CARD_FILE_NAME = TEST_DIR + "basic.txt";
    private static final String EXPORT_TYPE_IN_CARD_FILE_NAME = TEST_DIR + "typeIn.txt";

    private FileSystemFileStorageServiceImpl fileStorageService = new FileSystemFileStorageServiceImpl(TEST_DIR);

    @AfterMethod
    public void removeFolder() {
        FileUtils.deleteQuietly(new File(TEST_DIR));
    }

    @Test
    public void testSaveFileAsMP3() throws IOException {
        //given
        ByteArrayInputStream is = new ByteArrayInputStream("testing content to be saved".getBytes(StandardCharsets.UTF_8));

        //when
        String savedFileName = TEST_DIR + fileStorageService.saveFileAsMP3(is);

        //then
        assertThat(savedFileName, is(notNullValue()));
        assertThat(Files.exists(Paths.get(savedFileName)), is(equalTo(true)));
        String savedContent = FileUtils.readFileToString(new File(savedFileName), StandardCharsets.UTF_8);
        assertThat(savedContent, is(equalTo("testing content to be saved")));
    }

    @DataProvider(name = "cardTypes")
    private Object[][] cardTypes() {
        return new Object[][]{
                {CardType.BASIC, EXPORT_BASE_CARD_FILE_NAME},
                {CardType.BASIC_REVERSED, EXPORT_BASE_CARD_FILE_NAME},
                {CardType.TYPE_IN, EXPORT_TYPE_IN_CARD_FILE_NAME}
        };
    }

    @Test(dataProvider = "cardTypes")
    public void testAppendExportToFile(CardType cardType, String expectedFileName) throws IOException {
        //given

        //when
        String savedFileName = fileStorageService.appendExportToFile("content to be exported", cardType);

        //then
        assertThat(savedFileName, is(notNullValue()));
        assertThat(savedFileName, is(equalTo(expectedFileName)));
        assertThat(Files.exists(Paths.get(savedFileName)), is(equalTo(true)));
        String savedContent = FileUtils.readFileToString(new File(savedFileName), StandardCharsets.UTF_8);
        assertThat(savedContent, is(equalTo("content to be exported" + System.lineSeparator())));
    }


    @Test
    public void testAppendExportToFileDontEraseFileContent() throws IOException {
        //given
        ByteArrayInputStream is = new ByteArrayInputStream("testing content to be saved".getBytes(StandardCharsets.UTF_8));

        //when
        fileStorageService.appendExportToFile("basic content to be exported", CardType.BASIC);
        String savedFileName = fileStorageService.appendExportToFile("basic reversed content to be exported", CardType.BASIC_REVERSED);

        //then
        assertThat(savedFileName, is(notNullValue()));
        assertThat(Files.exists(Paths.get(savedFileName)), is(equalTo(true)));
        List<String> savedContent = FileUtils.readLines(new File(savedFileName), StandardCharsets.UTF_8);
        assertThat(savedContent.get(0), is(equalTo("basic content to be exported")));
        assertThat(savedContent.get(1), is(equalTo("basic reversed content to be exported")));
    }
}