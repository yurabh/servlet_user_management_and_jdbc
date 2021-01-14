package user_test;

import dao.DaoGeneric;
import dao.dao_factory.MySqlDaoFactory;
import domain.User;
import domain.UserState;
import exception.DaoException;
import dao.DaoFactory;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

public class UserTest {

    @Test
    public void createUserTest() throws IOException, SQLException, DaoException {
        User user = new User();
        user.setLogin("hhhhh");
        user.setFirstName("hhhh");
        user.setLastName("hhhh");
        user.setConfirmPassword("1111");
        user.setPassword("1111");
        user.setEmail("yura@1111");
        user.setStatus(UserState.NEW);
        DaoFactory factory = new MySqlDaoFactory();
        DaoGeneric daoGeneric = factory.getDao(factory.getConnection(), User.class);
        user = (User) daoGeneric.create(user);
        Assert.assertEquals(user, daoGeneric.read("hhhhh"));
    }

    @Test
    public void readUserTest() throws IOException, SQLException, DaoException {
        DaoFactory factory = new MySqlDaoFactory();
        DaoGeneric daoGeneric = factory.getDao(factory.getConnection(), User.class);
        User user = new User();
        user = (User) daoGeneric.read("yura");
        Assert.assertEquals(user, daoGeneric.read("yura"));
    }

    @Test
    public void updateUserTest() throws IOException, SQLException, DaoException {
        DaoFactory factory = new MySqlDaoFactory();
        DaoGeneric daoGeneric = factory.getDao(factory.getConnection(), User.class);
        User user = new User();
        user = (User) daoGeneric.read("yura");
        user.setLastName("ppp");
        daoGeneric.update(user);
    }

    @Test
    public void deleteUserTest() throws SQLException, DaoException, IOException {
        DaoFactory factory = new MySqlDaoFactory();
        DaoGeneric daoGeneric = factory.getDao(factory.getConnection(), User.class);
        User user = new User();
        user = (User) daoGeneric.read("22");
        daoGeneric.delete(user);
    }
}
