package logic;

import com.qa.dao.wrapper.FileValidationParameters;
import com.qa.helper.FileHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"HardCodedStringLiteral", "DuplicateStringLiteralInspection"})
class FileHelperTest {

    @Test
    void isValidFilePath() {
        final FileValidationParameters fileValidationParameters = FileValidationParameters.newInstance()
                .setAllowMP3(true)
                .setAllowAAC(true)
                .setAllowWAV(true)
                .setAllowMP4(true)
                .setAllowAVI(true);

        //Test path validator
        Assertions.assertTrue(FileHelper.isValidFilePath(fileValidationParameters, "C:/storage.mp3"));
        Assertions.assertTrue(FileHelper.isValidFilePath(fileValidationParameters, "C:/file/storage.aac"));
        Assertions.assertTrue(FileHelper.isValidFilePath(fileValidationParameters, "C:/file/storage.wav"));
        Assertions.assertTrue(FileHelper.isValidFilePath(fileValidationParameters, "C:/file/storage.mp4"));
        Assertions.assertTrue(FileHelper.isValidFilePath(fileValidationParameters, "C:/file/storage.avi"));
        Assertions.assertFalse(FileHelper.isValidFilePath(fileValidationParameters, "C:/file/storage.invalid"));

        //Test path validator with filters
        fileValidationParameters.setAllowMP3(false)
                .setAllowAAC(false)
                .setAllowWAV(false)
                .setAllowMP4(false)
                .setAllowAVI(false);
        Assertions.assertFalse(FileHelper.isValidFilePath(fileValidationParameters, "C:/storage.mp3"));
        Assertions.assertFalse(FileHelper.isValidFilePath(fileValidationParameters, "C:/file/storage.aac"));
        Assertions.assertFalse(FileHelper.isValidFilePath(fileValidationParameters, "C:/file/storage.wav"));
        Assertions.assertFalse(FileHelper.isValidFilePath(fileValidationParameters, "C:/file/storage.mp4"));
        Assertions.assertFalse(FileHelper.isValidFilePath(fileValidationParameters, "C:/file/storage.avi"));
        Assertions.assertFalse(FileHelper.isValidFilePath(fileValidationParameters, null));
    }
}
