package dao;

import exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;

public interface DaoFactory {

    Connection getConnection() throws SQLException;

    DaoGeneric getDao(Connection connection, Class daoClass) throws DaoException;
}
