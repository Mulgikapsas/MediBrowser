package com.qa.dao.wrapper;

public class FileValidationParameters {

    /**
     * Private Constructor.
     */
    private FileValidationParameters() {
        //Private Constructor.
    }

    /**
     * Get new instance
     *
     * @return {@link FileValidationParameters}
     */
    public static FileValidationParameters newInstance() {
        return new FileValidationParameters();
    }

    private boolean allowMP3;
    private boolean allowAAC;
    private boolean allowWAV;
    private boolean allowMP4;
    private boolean allowAVI;

    /**
     * Get allowMP3.
     **/
    public boolean isAllowMP3() {
        return allowMP3;
    }

    /**
     * Sets the allowMP3 and returns a reference to this for chaining.
     **/
    public FileValidationParameters setAllowMP3(final boolean allowMP3) {
        this.allowMP3 = allowMP3;
        return this;
    }

    /**
     * Get allowAAC.
     **/
    public boolean isAllowAAC() {
        return allowAAC;
    }

    /**
     * Sets the allowAAC and returns a reference to this for chaining.
     **/
    public FileValidationParameters setAllowAAC(final boolean allowAAC) {
        this.allowAAC = allowAAC;
        return this;
    }

    /**
     * Get allowWAV.
     **/
    public boolean isAllowWAV() {
        return allowWAV;
    }

    /**
     * Sets the allowWAV and returns a reference to this for chaining.
     **/
    public FileValidationParameters setAllowWAV(final boolean allowWAV) {
        this.allowWAV = allowWAV;
        return this;
    }

    /**
     * Get allowMP4.
     **/
    public boolean isAllowMP4() {
        return allowMP4;
    }

    /**
     * Sets the allowMP4 and returns a reference to this for chaining.
     **/
    public FileValidationParameters setAllowMP4(final boolean allowMP4) {
        this.allowMP4 = allowMP4;
        return this;
    }

    /**
     * Get allowAVI.
     **/
    public boolean isAllowAVI() {
        return allowAVI;
    }

    /**
     * Sets the allowAVI and returns a reference to this for chaining.
     **/
    public FileValidationParameters setAllowAVI(final boolean allowAVI) {
        this.allowAVI = allowAVI;
        return this;
    }
}
