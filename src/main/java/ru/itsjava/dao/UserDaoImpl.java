package ru.itsjava.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itsjava.domain.Pet;
import ru.itsjava.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@SuppressWarnings("ALL")
@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
    private final NamedParameterJdbcOperations jdbc;

    @Override
    public long create(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> params =
                Map.of("name", user.getName(), "age", user.getAge(), "pet_id", user.getPet().getId());
        jdbc.update("insert into users(name, age, pet_id) values (:name, :age, :pet_id)",
                new MapSqlParameterSource(params), keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public void update(User user) {
        Map<String, Object> params =
                Map.of("id", user.getId(), "name", user.getName(), "age", user.getAge());
        jdbc.update("update users set name = :name where id = :id", params);
        jdbc.update("update users set age = :age where id = :id", params);
    }

    @Override
    public void delete(User user) {
        Map<String, Object> params = Map.of("id", user.getId());
        jdbc.update("delete from users where id = :id", params);
    }

    @Override
    public int count() {
        return jdbc.getJdbcOperations().queryForObject("select count(*) from users", Integer.class);
    }

    @Override
    public User findById(long id) {
        Map<String, Object> params = Map.of("id", id);
        return jdbc.queryForObject("select us.id, name, age, p.id, breed from users us, pets p where us.id = :id " +
                "and us.pet_id = p.id", params, new UsersMapper());
    }

    @Override
    public List<User> listUsers() {
        List<User> users = jdbc.getJdbcOperations().query("select id, name, age from users", new UsersMapper());
        return users;
    }

    private static class UsersMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {

            return new User(rs.getLong("id"), rs.getString("name"), rs.getInt("age"),
                    new Pet(rs.getLong("id"), rs.getString("breed")));
        }
    }


}
