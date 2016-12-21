package springbook.user.dao;

import java.sql.*;
import springbook.user.domain.User;
import springbook.user.property.DBProperties;

// Dao :정보를 DB에 넣고 관리할 수 있는 클래스
public class UserDao {

    // 1. DB 연결을 위한 Connection을 가져온다
    // 2. SQL을 담은 Statment(or PreStatment)를 만든다
    // 3. 만들어진 Statment를 ㅣㄹ행한다
    // 4. 조흐의 경우 SQL Query 실행 결과를 ResultSet으로 받아서 정보를 저장할 Object
    // 5. 작업 중에 생성된 Connection, Statement, ResultSet과 같은 리소스는 작업을 마친 후 반드시 닫아준다.
    // 6. JDBC API가 만들어 내는 Exception을 잡아서 직접 처리하거나, 메소드에 Throws를
    // 선언해서 예외가 발생하면 메소드 밖으로 던지게 한다.

    public void add(User user) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection c = DriverManager.getConnection(DBProperties.DB_URL, DBProperties.DB_ID, DBProperties.DB_PASSWD);
        PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPasswd());

        ps.executeUpdate();
        ps.close();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection c = DriverManager.getConnection(DBProperties.DB_URL, DBProperties.DB_ID, DBProperties.DB_PASSWD);
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