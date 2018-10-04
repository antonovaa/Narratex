package ru.gameserver.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ShowInfoImpl implements ShowInfo {


    private final JdbcTemplate jdbcTemplate;

    public ShowInfoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }




}
