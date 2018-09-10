package com.qa.dao;

public class FilePath {

    private String path;

    /**
     * Get path.
     **/
    public String getPath() {
        return path;
    }

    /**
     * Sets the path and returns a reference to this for chaining.
     **/
    public FilePath setPath(final String path) {
        this.path = path;
        return this;
    }

    @Override
    public String toString() {
        return "FilePath{" +
                "path='" + path + '\'' +
                '}';
    }
}
