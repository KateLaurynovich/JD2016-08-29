package by.it.tsydzik.jd03_03.custom_dao;

import java.util.List;

/**
 * @author Eugene Tsydzik
 * @since 30.10.16.
 */
interface InterfaceDAO<TYPE> {
    //READ чтение отдельной сущности
    TYPE read(int id);
    //CREATE,UPDATE,DELETE обновление сущности
    boolean create(TYPE entity);
    boolean update(TYPE entity);
    boolean delete(TYPE entity);

    //READ - чтение всех элементов по условию
    List<TYPE> getAll(String WhereAndOrder);
    //и другие необходимые системе операции

}
