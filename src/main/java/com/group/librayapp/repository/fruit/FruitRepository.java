package com.group.librayapp.repository.fruit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.group.librayapp.dto.fruit.request.CereateFruitReqeust;
import com.group.librayapp.dto.fruit.request.UpdateFruitRequest;
import com.group.librayapp.dto.fruit.response.FruitResponse;

@Repository
public class FruitRepository {
    private final JdbcTemplate jdbcTemplate;

    public FruitRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    } 

    public void postFruitSet(CereateFruitReqeust request) {
        String sql = "INSERT INTO fruit (name, werehousing_date, price) VALUES (?, ?, ?)";

        jdbcTemplate.update(sql, request.getName(), request.getWerehousingDate(), request.getPrice());
    }

    public Boolean isFruitExist(long id) {
        String getId = "SELECT * FROM fruit WHERE id = ?";
        return jdbcTemplate.query(getId, (rs, rowNum) -> 0, id).isEmpty();
    }

    public void putFruitSet(UpdateFruitRequest request) {
        String sql = "UPDATE fruit SET is_sold = ? WHERE id = ?";
        jdbcTemplate.update(sql, true, request.getId());
    }

    public List<FruitResponse> getFruitStatus() {
        String sql = "SELECT SUM(CASE WHEN is_sold = 1 THEN price ELSE 0 END) AS seles_amount, SUM(CASE WHEN is_sold = 0 OR is_sold IS NULL THEN price ELSE 0 END) AS not_sales_amount FROM fruit";

        return jdbcTemplate.query(sql, new RowMapper<FruitResponse>() {
            @Override
            public FruitResponse mapRow(ResultSet rs, int rowNum) throws SQLException{ 
                long selesAmount = rs.getLong("seles_amount");
                long notSalesAmount = rs.getLong("not_sales_amount");
                return new FruitResponse(selesAmount, notSalesAmount);
            }
        });
    }

    

}
