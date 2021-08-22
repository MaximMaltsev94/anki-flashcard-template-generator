package maltsau.maksim.tools.ankiflashcardtemplategenerator.service.filestorage;

import maltsau.maksim.tools.ankiflashcardtemplategenerator.exception.FileStorageServiceException;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Component
public class FileSystemFileStorageServiceImpl implements FileStorageService {

    private static final String FILE_SAVE_EXCEPTION = "error while writing file to disk";

    private final String saveFolderPath;

    public FileSystemFileStorageServiceImpl(@Value("${filestorage.filesystem.savepath}") String saveFolderPath) {
        this.saveFolderPath = saveFolderPath;
    }

    @Override
    public String saveFileAsMP3(InputStream is) {
        try {
            String randomFileName = String.format("%s%s.mp3", saveFolderPath, UUID.randomUUID());
            File file = new File(randomFileName);
            FileUtils.copyToFile(is, file);
            return file.getName();
        } catch (IOException e) {
            throw new FileStorageServiceException(FILE_SAVE_EXCEPTION, e);
        }
    }
}
