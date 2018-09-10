package com.qa.dao;

public class FileName {

    private String name;

    /**
     * Get name.
     **/
    public String getName() {
        return name;
    }

    /**
     * Sets the name and returns a reference to this for chaining.
     **/
    public FileName setName(final String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "FileName{" +
                "name='" + name + '\'' +
                '}';
    }
}
