package maltsau.maksim.tools.ankiflashcardtemplategenerator.service;

import maltsau.maksim.tools.ankiflashcardtemplategenerator.domain.WordContextHolder;
import maltsau.maksim.tools.ankiflashcardtemplategenerator.exception.WordContextServiceException;
import maltsau.maksim.tools.ankiflashcardtemplategenerator.service.filestorage.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.List;

@Component
public class WordContextServiceImpl implements WordContextService {

    private static final String MALFORMED_URI_MSG = "Error with parsing of provided URI";
    private static final String URI_RESOURCE_ERROR_MSG = "Unexpected error with provided URI resource";
    private static final String NEW_LINE_REGEX = "\\r?\\n";

    private final FileStorageService fileStorageService;

    @Autowired
    public WordContextServiceImpl(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @Override
    public WordContextHolder generateContext(String originalWord, String originalContext, String translatedWords, String translatedContext, String pronunciationUri) {
        String savedFileName = saveFileFromUri(pronunciationUri);
        List<String> translatedWordsList = splitByNewLine(translatedWords);


        return WordContextHolder.builder()
                .withOriginalWord(originalWord)
                .withOriginalContext(originalContext)
                .withTranslatedWords(translatedWordsList)
                .withTranslatedContext(translatedContext)
                .withPronunciationFileName(savedFileName)
                .build();
    }

    private String saveFileFromUri(String uri) {
        try {
            return fileStorageService.saveFileAsMP3(new UrlResource(uri).getInputStream());
        } catch (MalformedURLException e) {
            throw new WordContextServiceException(MALFORMED_URI_MSG, e);
        } catch (IOException e) {
            throw new WordContextServiceException(URI_RESOURCE_ERROR_MSG, e);
        }
    }

    private List<String> splitByNewLine(String source) {
        if(!StringUtils.hasText(source)) {
            return Collections.emptyList();
        }
        return List.of(source.split(NEW_LINE_REGEX));
    }
}
