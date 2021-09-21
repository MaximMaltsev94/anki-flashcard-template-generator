package maltsau.maksim.tools.ankiflashcardtemplategenerator.service.filestorage;

import maltsau.maksim.tools.ankiflashcardtemplategenerator.domain.CardType;
import maltsau.maksim.tools.ankiflashcardtemplategenerator.exception.FileStorageServiceException;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;

/**
 * Implementation of {@link FileStorageService} which work with file system.
 * Date: 22.08.2021
 *
 * @author Maksim Maltsau
 */
@Service
public class FileSystemFileStorageServiceImpl implements FileStorageService {

    private static final String FILE_SAVE_EXCEPTION = "error while writing file to disk";
    private static final String FILE_EXPORT_EXCEPTION = "Error while writing data to file: `%s`";
    private static final String UNKNOWN_CARD_TYPE_EXCEPTION = "No export file mapping found for card type: `%s`";

    private static final String WORKING_DIRECTORY_PATH = System.getProperty("user.dir");
    private static final String EXPORT_DIR = WORKING_DIRECTORY_PATH + File.separator + "export";
    private static final String EXPORT_BASE_CARD_FILE_NAME = EXPORT_DIR + File.separator + "basic.txt";
    private static final String EXPORT_TYPE_IN_CARD_FILE_NAME = EXPORT_DIR + File.separator + "typeIn.txt";

    private static final Map<CardType, String> EXPORT_FILE_NAME_MAPPING = Map.of(
            CardType.BASIC, EXPORT_BASE_CARD_FILE_NAME,
            CardType.BASIC_REVERSED, EXPORT_BASE_CARD_FILE_NAME,
            CardType.TYPE_IN, EXPORT_TYPE_IN_CARD_FILE_NAME
    );

    private final String mediaSaveFolderPath;

    public FileSystemFileStorageServiceImpl(@Value("${filestorage.filesystem.savepath}") String saveFolderPath) {
        this.mediaSaveFolderPath = saveFolderPath;
    }

    @Override
    public String saveFileAsMP3(InputStream is) {
        try {
            String randomFileName = String.format("%s%s.mp3", mediaSaveFolderPath, UUID.randomUUID());
            File file = new File(randomFileName);
            FileUtils.copyToFile(is, file);
            return file.getName();
        } catch (IOException e) {
            throw new FileStorageServiceException(FILE_SAVE_EXCEPTION, e);
        }
    }

    @Override
    public String appendExportToFile(String content, CardType cardType) {
        String fileName = getExportFileNameByCardType(cardType);
        try {
            FileUtils.writeStringToFile(new File(fileName), content, StandardCharsets.UTF_8, true);
            return fileName;
        } catch (IOException e) {
            throw new FileStorageServiceException(String.format(FILE_EXPORT_EXCEPTION, fileName), e);
        }
    }

    private String getExportFileNameByCardType(CardType cardType) {
        if (cardType == null || !EXPORT_FILE_NAME_MAPPING.containsKey(cardType)) {
            throw new FileStorageServiceException(String.format(UNKNOWN_CARD_TYPE_EXCEPTION, cardType));
        }
        return EXPORT_FILE_NAME_MAPPING.get(cardType);
    }
}
