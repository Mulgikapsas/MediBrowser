package com.qa.dao;

public class Category {

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
    public Category setName(final String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                '}';
    }
}
