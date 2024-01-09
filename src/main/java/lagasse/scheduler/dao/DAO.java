package lagasse.scheduler.dao;

import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface DAO<T> {

    ObservableList<T> getAll() throws SQLException;

    int add(T t)throws SQLException;
    int delete(T t)throws SQLException;

}
