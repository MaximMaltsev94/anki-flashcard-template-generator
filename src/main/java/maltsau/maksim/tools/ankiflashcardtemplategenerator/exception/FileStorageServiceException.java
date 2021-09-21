package maltsau.maksim.tools.ankiflashcardtemplategenerator.exception;

/**
 * Exception for
 * {@link maltsau.maksim.tools.ankiflashcardtemplategenerator.service.filestorage.FileStorageService} errors.
 * Date: 22.08.2021
 *
 * @author Maksim Maltsau
 */
public class FileStorageServiceException extends RuntimeException {
    public FileStorageServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileStorageServiceException(String message) {
        super(message);
    }
}
