package Teatrus.persistence.repository.jdbc;

import Teatrus.model.Rezervare;
import Teatrus.model.User;
import Teatrus.persistence.IRepositoryUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class RepositoryUser implements IRepositoryUser {
    private JDBCUtils dbUtils;
    private static final Logger logger= LogManager.getLogger();


    public RepositoryUser(Properties props){
        logger.info("Initializing RepositoryUser with properties: {} ",props);
        dbUtils=new JDBCUtils(props);
    }

    @Override
    public int size() {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select count(*) as [SIZE] from User")) {
            try(ResultSet result = preStmt.executeQuery()) {
                if (result.next()) {
                    logger.traceExit(result.getInt("SIZE"));
                    return result.getInt("SIZE");
                }
            }
        }catch(SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        return 0;
    }

    @Override
    public void save(User entity) {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into User(idUtilizator,nume,prenume,email,numeUtilizator,parola,tipUtilizator) values (?,?,?,?,?,?,?)")){
            preStmt.setInt(1,size()+1);
            preStmt.setString(2,entity.getNume());
            preStmt.setString(3, entity.getPrenume());
            preStmt.setString(4,entity.getEmail());
            preStmt.setString(5,entity.getNumeUtilizator());
            preStmt.setString(6,entity.getParola());
            preStmt.setInt(7,entity.getTipUtilizator());
            int result=preStmt.executeUpdate();
        }
        catch(SQLException exc){
            logger.error(exc);
            System.out.println(exc);
        }
        logger.traceExit();
    }

    @Override
    public void delete(Integer integer) {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from User where idUtilizator=?")){
            preStmt.setInt(1,integer);
            int result=preStmt.executeUpdate();
        }
        catch(SQLException exc){
            logger.error(exc);
            System.out.println(exc);
        }
        logger.traceExit();
    }

    @Override
    public void update(Integer integer, User entity) {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("update User set nume=?, prenume=?, email=?, numeUtilizator=?, parola=?, tipUtilizator=? where idUtilizator=?")){
            preStmt.setString(1,entity.getNume());
            preStmt.setString(2, entity.getPrenume());
            preStmt.setString(3,entity.getEmail());
            preStmt.setString(4,entity.getNumeUtilizator());
            preStmt.setString(5,entity.getParola());
            preStmt.setInt(6,entity.getTipUtilizator());
            preStmt.setInt(7,integer);
            int result=preStmt.executeUpdate();
        }
        catch(SQLException exc){
            logger.error(exc);
            System.out.println(exc);
        }
        logger.traceExit();
    }

    @Override
    public User findOne(Integer integer) {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select * from User where idUtilizator=?")){
            preStmt.setInt(1,integer);
            try(ResultSet result=preStmt.executeQuery()){
                if (result.next()){
                    int idUser=result.getInt("idUtilizator");
                    String nume=result.getString("nume");
                    String prenume=result.getString("prenume");
                    String email=result.getString("email");
                    String numeUtilizator=result.getString("numeUtilizator");
                    String parola=result.getString("parola");
                    int tipUtilizator=result.getInt("tipUtilizator");
                    User user=new User(idUser,nume,prenume,numeUtilizator,parola,tipUtilizator);
                    user.setEmail(email);
                    logger.traceExit(user);
                    return user;
                }
            }
        }
        catch (SQLException exc){
            logger.error(exc);
            System.out.println(exc);
        }
        logger.traceExit();
        return null;
    }

    @Override
    public ArrayList<User> findAll() {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        ArrayList<User> utilizatori=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from User ")){

            try(ResultSet result=preStmt.executeQuery()){
                while (result.next()){
                    int idUser=result.getInt("idUtilizator");
                    String nume=result.getString("nume");
                    String prenume=result.getString("prenume");
                    String email=result.getString("email");
                    String numeUtilizator=result.getString("numeUtilizator");
                    String parola=result.getString("parola");
                    int tipUtilizator=result.getInt("tipUtilizator");
                    User user=new User(idUser,nume,prenume,numeUtilizator,parola,tipUtilizator);
                    user.setEmail(email);
                    logger.traceExit(user);
                    utilizatori.add(user);
                }
            }
        }
        catch (SQLException exc){
            logger.error(exc);
            System.out.println(exc);
        }
        logger.traceExit();
        return utilizatori;
    }
}
