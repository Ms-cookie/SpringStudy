package springbook.user.dao;

import java.sql.*;
import springbook.user.domain.User;
import springbook.user.property.DBProperties;

// 리스트 1-4 getConnection() 메소드를 추출해서 중복을 제거한 UserDao
public class UserDao {
   
    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection c = getConnection();
        PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPasswd());

        ps.executeUpdate();
        ps.close();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection c = getConnection();
        PreparedStatement ps = c.prepareStatement("select * from users where id=?");
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setName(rs.getString("name"));
        user.setId(rs.getString("id"));
        user.setPasswd(rs.getString("password"));

        ps.close();
        c.close();
        return user;
    }

    /**DB 연결이 필요하면 getConnection() 메소드를 이용하게 한다.
     * 중복된 코드를 독립적인 메소드로 만들어서 중복을 제거했다.*/
    
    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection c = DriverManager.getConnection(DBProperties.DB_URL, DBProperties.DB_ID, DBProperties.DB_PASSWD);
        return c;
    }

    public static void main(String[] agrs) throws ClassNotFoundException, SQLException {
        UserDao dao = new UserDao();

        User user = new User();
        user.setId("cookie4");
        user.setName("koominjung");
        user.setPasswd("password");

        dao.add(user);

        System.out.println(user.getId() + " 등록 성공!");

        User user2 = dao.get("cookie");
        System.out.println(user2);
        System.out.println("조회 성공!");
    }
}