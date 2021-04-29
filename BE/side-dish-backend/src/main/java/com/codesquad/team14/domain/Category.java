package com.codesquad.team14.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.HashMap;
import java.util.Map;

public class Category {

    @Id
    private Long id;
    private String name;
    private boolean best;

    @MappedCollection(idColumn = "category", keyColumn = "id")
    private Map<Long, Item> items = new HashMap<>();

    public Category(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isBest() {
        return best;
    }

    public Map<Long, Item> getItems() {
        return items;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBest(boolean best) {
        this.best = best;
    }

    public void addItem(Item item) {
        items.put(item.getId(), item);
    }

    public void removeItem(Item item) {
        items.remove(item.getId());
    }

    public void updateItem(Item item) {
        items.replace(item.getId(), item);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
