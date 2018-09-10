package com.qa.dao;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

import static com.qa.dao.MediaFileType.ContentType.AUDIO;
import static com.qa.dao.MediaFileType.ContentType.VIDEO;

public enum MediaFileType {
    MP3(".mp3", AUDIO),
    AAC(".aac", AUDIO),
    WAV(".wav", AUDIO),
    MP4(".mp4", VIDEO),
    AVI(".avi", VIDEO);

    public enum ContentType {
        VIDEO, AUDIO
    }

    private String extension;
    private Pattern extensionPattern;
    private ContentType contentType;

    MediaFileType(final String extension, final ContentType contentType) {
        this.extension = extension;
        this.contentType = contentType;
        extensionPattern = Pattern.compile(".*" + extension);
    }

    /**
     * Get extension.
     **/
    public String getExtension() {
        return extension;
    }

    /**
     * Get contentType.
     **/
    public ContentType getContentType() {
        return contentType;
    }

    /**
     * This method matches {@code extensionPattern} against {@param fileName}
     *
     * @param fileName - {@link String} which represents {@link MediaFile} name
     * @return true if matches, false if not
     */
    public boolean matches(final String fileName) {
        if (StringUtils.isBlank(fileName)) {
            return false;
        }
        return extensionPattern.matcher(fileName).matches();
    }

}
