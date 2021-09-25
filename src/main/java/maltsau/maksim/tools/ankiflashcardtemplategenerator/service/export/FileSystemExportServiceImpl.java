package maltsau.maksim.tools.ankiflashcardtemplategenerator.service.export;

import maltsau.maksim.tools.ankiflashcardtemplategenerator.domain.CardType;
import maltsau.maksim.tools.ankiflashcardtemplategenerator.domain.WordContextHolder;
import maltsau.maksim.tools.ankiflashcardtemplategenerator.service.filestorage.FileStorageService;
import maltsau.maksim.tools.ankiflashcardtemplategenerator.service.template.CardTemplateRenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of {@link ExportService} which export data to file on file system.
 * Date: 20.09.2021
 *
 * @author Maksim Maltsau
 */
@Service
public class FileSystemExportServiceImpl implements ExportService {

    private static final String EXPORT_CONTENT_TEMPLATE_STRING = "%s | %s";

    private FileStorageService fileStorageService;

    private CardTemplateRenderService cardTemplateRenderService;

    @Autowired
    public FileSystemExportServiceImpl(FileStorageService fileStorageService,
                                       CardTemplateRenderService cardTemplateRenderService) {
        this.fileStorageService = fileStorageService;
        this.cardTemplateRenderService = cardTemplateRenderService;
    }

    @Override
    public Set<String> exportAllCardTypes(WordContextHolder wordContextHolder) {
        Set<String> affectedFileNames = new HashSet<>();
        affectedFileNames.add(exportBasicCardType(wordContextHolder));
        affectedFileNames.add(exportBasicReversedCardType(wordContextHolder));
        affectedFileNames.add(exportTypeInCardType(wordContextHolder));
        return affectedFileNames;
    }

    @Override
    public String exportBasicCardType(WordContextHolder wordContextHolder) {
        String basicFrontTemplate = cardTemplateRenderService.renderBasicFrontTemplate(wordContextHolder);
        String basicBackTemplate = cardTemplateRenderService.renderBasicBackTemplate(wordContextHolder);

        String content = generateDataForExport(basicFrontTemplate, basicBackTemplate);

        return fileStorageService.appendExportToFile(content, CardType.BASIC);
    }

    @Override
    public String exportBasicReversedCardType(WordContextHolder wordContextHolder) {
        String basicFrontTemplate = cardTemplateRenderService.renderBasicFrontTemplate(wordContextHolder);
        String basicBackTemplate = cardTemplateRenderService.renderBasicBackTemplate(wordContextHolder);

        String content = generateDataForExport(basicBackTemplate, basicFrontTemplate);

        return fileStorageService.appendExportToFile(content, CardType.BASIC_REVERSED);
    }

    @Override
    public String exportTypeInCardType(WordContextHolder wordContextHolder) {
        String typeInFrontTemplate = cardTemplateRenderService.renderTypeInFrontTemplate(wordContextHolder);
        String typeInBackTemplate = cardTemplateRenderService.renderTypeInBackTemplate(wordContextHolder);

        String content = generateDataForExport(typeInFrontTemplate, typeInBackTemplate);

        return fileStorageService.appendExportToFile(content, CardType.TYPE_IN);
    }

    private String generateDataForExport(String frontTemplate, String backTemplate) {
        return String.format(EXPORT_CONTENT_TEMPLATE_STRING,
                removeNewLineCharacters(frontTemplate),
                removeNewLineCharacters(backTemplate));
    }

    private String removeNewLineCharacters(String content) {
        return content.replaceAll("([\\n\\r])", "");
    }
}
