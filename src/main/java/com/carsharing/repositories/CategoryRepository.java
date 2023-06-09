package com.carsharing.repositories;

import com.carsharing.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class CategoryRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public CategoryRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Category> findAll() {
        return jdbcTemplate.query(
            "SELECT * FROM `category`",
            new BeanPropertyRowMapper<>(Category.class)
        );
    }

    public Optional<Category> findById(long id) {
        return jdbcTemplate.query(
                "SELECT * FROM `category` WHERE id=:id",
                Map.ofEntries(Map.entry("id", id)),
                new BeanPropertyRowMapper<>(Category.class)
            )
            .stream().findAny();
    }

    public void save(Category category) {
        jdbcTemplate.update(
            "INSERT INTO `category` (name) VALUES (:name)",
            Map.ofEntries(Map.entry("name", category.getName()))
        );
    }

    public void update(long id, Category category) {
        jdbcTemplate.update(
            "UPDATE `category` SET name=:name WHERE id=:id",
            Map.ofEntries(
                Map.entry("name", category.getName()),
                Map.entry("id", id)
            )
        );
    }

    public void delete(long id) {
        jdbcTemplate.update(
            "DELETE FROM `category` WHERE id=:id",
            Map.ofEntries(Map.entry("id", id))
        );
    }

    public Optional<Category> findByName(String name) {
        return jdbcTemplate.query(
                "SELECT * FROM `category` WHERE name=:name",
                Map.ofEntries(Map.entry("name", name)),
                new BeanPropertyRowMapper<>(Category.class)
            )
            .stream().findAny();
    }
}
