package com.qa.helper;

import com.qa.dao.MediaFileType;
import com.qa.dao.wrapper.FileValidationParameters;

/**
 * FileHelper
 * Utility class for handling files
 *
 * @author Juri Duval
 */
public enum FileHelper {
    ;

    /**
     * This method checks if {@param path} is valid.
     *
     *
     * @param fileValidationParameters - {@link FileValidationParameters} as filter
     * @param path                     - {@link String} as file path
     * @return boolean
     */
    public static boolean isValidFilePath(final FileValidationParameters fileValidationParameters, final String path) {
        return fileValidationParameters.isAllowMP3() && MediaFileType.MP3.matches(path)
                || fileValidationParameters.isAllowAAC() && MediaFileType.AAC.matches(path)
                || fileValidationParameters.isAllowWAV() && MediaFileType.WAV.matches(path)
                || fileValidationParameters.isAllowMP4() && MediaFileType.MP4.matches(path)
                || fileValidationParameters.isAllowAVI() && MediaFileType.AVI.matches(path);
    }
}
