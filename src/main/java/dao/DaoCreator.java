package dao;

import java.sql.Connection;

public interface DaoCreator {

    DaoGeneric create(Connection connection);
}
