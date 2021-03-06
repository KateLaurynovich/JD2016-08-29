package by.it.rudzko.jd03_03.DAO;


import by.it.rudzko.jd03_03.Beans.Audience;
import by.it.rudzko.jd03_03.CN;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AudienceDAO implements InterfaceDAO<Audience> {

    @Override
    public Audience create(Audience aud){
        aud.setID(0);
        String createAudience = String.format(
                "insert into Readership(Audience) value('%s');",
                aud.getGroup()
        );
        try (
                Connection connection = CN.getConnection();
                Statement statement = connection.createStatement()
        ) {
            if (statement.executeUpdate(createAudience, Statement.RETURN_GENERATED_KEYS) == 1) {
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next())
                    aud.setID(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aud;
    }

    @Override
    public Audience read(int id) {
        Audience audRes = null;
        try (
                Connection connection = CN.getConnection();
                Statement statement = connection.createStatement()
        ) {
            ResultSet rs = statement.executeQuery("SELECT * FROM Readership WHERE ID=" + id);
            if (rs.next()) {
                audRes = new Audience();
                audRes.setID(rs.getInt("ID"));
                audRes.setGroup(rs.getString("Audience"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return audRes;
    }

    @Override
    public Audience update(Audience aud) {
        Audience audRes = null;
        String updateAudience = String.format(
                "UPDATE Readership SET Audience= '%s' WHERE Readership.ID = %d",
                aud.getGroup(), aud.getID()
        );
        try (
                Connection connection = CN.getConnection();
                Statement statement = connection.createStatement()
        ) {
            if (statement.executeUpdate(updateAudience) == 1)
                audRes = aud;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return audRes;
    }

    @Override
    public boolean delete(Audience aud) {
        boolean res=false;
        String deleteAudience = String.format("DELETE FROM Readership WHERE Readership.ID = %d", aud.getID());
        try (
                Connection connection = CN.getConnection();
                Statement statement = connection.createStatement()
        ) {
            res= (statement.executeUpdate(deleteAudience) == 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public List<Audience> getAll(String condition) {
        List<Audience>readership =new ArrayList<>();
        String sql="SELECT * FROM Readership "+condition;
        try(Connection con= CN.getConnection();
            Statement st=con.createStatement()){
            ResultSet rs=st.executeQuery(sql);
            while (rs.next()){
                Audience aud=new Audience();
                aud.setID(rs.getInt("ID"));
                aud.setGroup(rs.getString("Audience"));
                readership.add(aud);
            }
        } catch (SQLException e) {
            System.out.println("No connection. Can't read table Readership.\n"+e.getMessage());
        }
        return readership;
    }

}
