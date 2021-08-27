package maltsau.maksim.tools.ankiflashcardtemplategenerator.service.filestorage;

import java.io.InputStream;

/**
 * Service interface for access to file storage.
 * Date: 22.08.2021
 *
 * @author Maksim Maltsau
 */
public interface FileStorageService {
    /**
     * Saves inputStream data as MP3 file to some file storage.
     * @param is InputStream with file data.
     * @return name of saved file.
     */
    String saveFileAsMP3(InputStream is);
}
