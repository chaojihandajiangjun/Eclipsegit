package albumBean;

public class Album {
private int albumid;
private int userid;
private String albumname;
public int getAlbumid() {
	return albumid;
}
public void setAlbumid(int albumid) {
	this.albumid = albumid;
}
public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}
public String getAlbumname() {
	return albumname;
}
public void setAlbumname(String albumname) {
	this.albumname = albumname;
}
public Album(int albumid, int userid, String albumname) {
	super();
	this.albumid = albumid;
	this.userid = userid;
	this.albumname = albumname;
}
@Override
public String toString() {
	return "Album [albumid=" + albumid + ", userid=" + userid + ", albumname=" + albumname + "]";
}
public Album() {
	super();
}

}
