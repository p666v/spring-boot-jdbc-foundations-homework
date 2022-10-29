package ru.itsjava.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itsjava.domain.Pet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@SuppressWarnings("ALL")
@Repository
@RequiredArgsConstructor
public class PetDaoImpl implements PetDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public long create(Pet pet) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> params = Map.of("breed", pet.getBreed());
        jdbc.update("insert into pets(breed) values (:breed)", new MapSqlParameterSource(params), keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public void delete(Pet pet) {
        Map<String, Object> params = Map.of("id", pet.getId());
        jdbc.update("delete from pets where id = :id", params);
    }

    @Override
    public Pet findByBreed(String breed) {
        return jdbc.queryForObject("select id, breed from pets where breed = :breed",
                new MapSqlParameterSource(Map.of("breed", breed)), new PetsMapper());
    }

    @Override
    public List<Pet> printAllPets() {
        return jdbc.query("select id, breed from pets", new PetsMapper());
    }

    @Override
    public int count() {
        return jdbc.getJdbcOperations().queryForObject("select count(*) from pets", Integer.class);
    }


    private static class PetsMapper implements RowMapper<Pet> {

        @Override
        public Pet mapRow(ResultSet rs, int rowNum) throws SQLException {

            return new Pet(rs.getLong("id"), rs.getString("breed"));
        }
    }
}
