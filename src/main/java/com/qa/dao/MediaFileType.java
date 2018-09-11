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
        VIDEO, AUDIO;
    }

    private final String extension;

    private final Pattern extensionPattern;
    private final ContentType contentType;

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

    /**
     * This method returns {@link MediaFileType} based on file {@param path}
     *
     * @param path - {@link String}
     * @return {@link MediaFileType}
     */
    public static MediaFileType getByPath(final String path) {
        if (MediaFileType.MP3.matches(path)) {
            return MP3;
        }
        if (MediaFileType.AAC.matches(path)) {
            return AAC;
        }
        if (MediaFileType.WAV.matches(path)) {
            return WAV;
        }
        if (MediaFileType.MP4.matches(path)) {
            return MP4;
        }
        if (MediaFileType.AVI.matches(path)) {
            return AVI;
        }
        return null;
    }

}
