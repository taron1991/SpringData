package spring.app.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Operation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import spring.app.models.Car;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class SpringJdbcRepository {

    private JdbcTemplate jdbcTemplate;

    private static final String FIND_ALL = "select*from carDb";
    private static final String FIND_BY_ID = "select*from carDb where id = ?";

    private static final String DELETE_ID = "delete from carDb where id = ?";

    private static final String SAVE = "insert into carDb(name,color,price) values(?,?,?)";

    private static final String FIND_BY_NAME = "select * from carDb where name = ?";

    private static final String FIND_MORE = "select * from carDb where price > ?";

    private static final String FIND_LESS = "select * from carDb where price < ?";

    @Autowired
    public SpringJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Car> findAll() {
        List<Car> cars = new ArrayList<>();
        jdbcTemplate.query(FIND_ALL, new Object[]{}, (rs) -> {
            cars.add(new Car(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
        });
        return cars;
    }


    public Optional<Car> findById(int id) {
        Optional<Car> optionalCar;
        Stream<Car> carStream = jdbcTemplate.queryForObject(FIND_BY_ID, new Object[]{id}, (rs, row) -> Stream.of(new Car
                (rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4))));
        optionalCar = carStream.findAny();
        return optionalCar;
    }


    public void deleteById(int id) {
        jdbcTemplate.update(DELETE_ID, id);

    }


    public void save(Car car) {
        jdbcTemplate.update(SAVE, car.getName(), car.getColor(), car.getPrice());
    }


    public List<Car> findByName(String name) {
        List<Car> list = new ArrayList<>();

        jdbcTemplate.query(FIND_BY_NAME, new Object[]{name}, rs -> {
            list.add(new Car(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
        });

        return list;
    }


    public List<Car> moreThan(int price) {
        List<Car> list = new ArrayList<>();

        jdbcTemplate.query(FIND_MORE, new Object[]{price}, rs -> {
            list.add(new Car(rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getInt(4)));
        });
        return list;
    }

    public List<Car> lessThan(int price) {
        List<Car> carList = new ArrayList<>();
        jdbcTemplate.query(FIND_LESS, new Object[]{price}, rs -> {
            carList.add(new Car(rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getInt(4)));
        });

        return carList;
    }
}

