package springbook.user.domain;

public class User {

    // 사용자 정보를 저장할 때는 자바빈 규약을 따르는 Object를 이용하면 편리하다
    private String id;
    private String name;
    private String passwd;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    public String toString() {
        int passwdLength = passwd.length();
        StringBuilder passwdSecurity = new StringBuilder(passwd.substring(0, 1));
        for (int i = 1; i < passwdLength; i++) {
            passwdSecurity.append("*");
        }

        return "User [id=" + id + ", name=" + name + ", passwd=" + passwdSecurity + "]";
    }
}