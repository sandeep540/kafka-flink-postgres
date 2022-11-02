package com.acme.examples.model;

import java.util.Objects;
import java.util.UUID;

public class Product {

    public UUID id;
    public String name;
    public String brand;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(brand, product.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, brand);
    }

    public Product(UUID id, String name, String brand) {
        this.id = id;
        this.name = name;
        this.brand = brand;
    }

    public Product() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", brand='").append(brand).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
