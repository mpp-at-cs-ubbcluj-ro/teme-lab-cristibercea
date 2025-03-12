package ubb.scs.mpp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CarsDBRepository implements CarRepository{

    private final JdbcUtils dbUtils;



    private static final Logger logger= LogManager.getLogger();

    public CarsDBRepository(Properties props) {
        logger.info("Initializing CarsDBRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public List<Car> findByManufacturer(String manufacturerN) {
        logger.traceEntry("findByManufacturer()");
        List<Car> cars=new ArrayList<>();
        Connection conn=dbUtils.getConnection();
        try (PreparedStatement preparedStatement = conn.prepareStatement("select * from Masini where manufacturer=?")) {
            preparedStatement.setString(1, manufacturerN);
            cars = executeSelectComd(preparedStatement);
        } catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB: "+e);
        }
        return cars;
    }

    @Override
    public List<Car> findBetweenYears(int min, int max) {
        logger.traceEntry("findBetweenYears()");
        List<Car> cars=new ArrayList<>();
        Connection conn=dbUtils.getConnection();
        try (PreparedStatement preparedStatement = conn.prepareStatement("select * from Masini where year between ? and ?")) {
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);
            cars = executeSelectComd(preparedStatement);
        } catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB: "+e);
        }
        return cars;
    }

    private List<Car> executeSelectComd(PreparedStatement preparedStatement) throws SQLException {
        List<Car> cars=new ArrayList<>();
        try(ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id=resultSet.getInt("id");
                String manufacturer=resultSet.getString("manufacturer");
                String model=resultSet.getString("model");
                int year=resultSet.getInt("year");
                Car car = new Car(manufacturer, model, year);
                car.setId(id);
                cars.add(car);
            }
        }
        return cars;
    }

    @Override
    public void add(Car elem) {
        logger.traceEntry("saving task {}", elem);
        Connection conn=dbUtils.getConnection();
        try(PreparedStatement preparedStatement = conn.prepareStatement("insert into Masini (manufacturer,model,year) values (?,?,?)")){
            preparedStatement.setString(1,elem.getManufacturer());
            preparedStatement.setString(2,elem.getModel());
            preparedStatement.setInt(3,elem.getYear());
            int result = preparedStatement.executeUpdate();
            logger.trace("Saved {} instances.",result);
        }catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB: "+e);
        }
    }

    @Override
    public void update(Integer id, Car elem) {
        logger.traceEntry("update task {}", elem);
        Connection conn=dbUtils.getConnection();
        try(PreparedStatement preparedStatement = conn.prepareStatement("update Masini set manufacturer=?,model=?,year=? where id=?")){
            preparedStatement.setString(1,elem.getManufacturer());
            preparedStatement.setString(2,elem.getModel());
            preparedStatement.setInt(3,elem.getYear());
            preparedStatement.setInt(4,id);
            int result = preparedStatement.executeUpdate();
            logger.trace("Modified {} instances.",result);
        }catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB: "+e);
        }
    }

    @Override
    public Iterable<Car> findAll() {
        logger.traceEntry("findAll()");
        List<Car> cars=new ArrayList<>();
        Connection conn=dbUtils.getConnection();
        try (PreparedStatement preparedStatement = conn.prepareStatement("select * from Masini")){
            cars = executeSelectComd(preparedStatement);
        } catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB: "+e);
        }
        return cars;
    }
}
