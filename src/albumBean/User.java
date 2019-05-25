package albumBean;

public class User {
private int userid;
private String username;
private String password;
private String eamil;
@Override
public String toString() {
	return "User [userid=" + userid + ", username=" + username + ", password=" + password + ", eamil=" + eamil + "]";
}
public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getEamil() {
	return eamil;
}
public void setEamil(String eamil) {
	this.eamil = eamil;
}
public User(int userid, String username, String password, String eamil) {
	super();
	this.userid = userid;
	this.username = username;
	this.password = password;
	this.eamil = eamil;
}
public User() {
	super();
}

}
