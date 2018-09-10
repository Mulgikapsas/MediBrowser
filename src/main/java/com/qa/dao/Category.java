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
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Category category = (Category) o;

        return name != null ? name.equals(category.name) : category.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return name; //Return name so it is displayed properly in list view
    }
}
