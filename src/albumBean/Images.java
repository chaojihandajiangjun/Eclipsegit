package albumBean;

public class Images {
private int photoid;
private String name;
private String url;
private int albumid;
public int getPhotoid() {
	return photoid;
}
public void setPhotoid(int photoid) {
	this.photoid = photoid;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public int getAlbumid() {
	return albumid;
}
public void setAlbumid(int albumid) {
	this.albumid = albumid;
}
@Override
public String toString() {
	return "Images [photoid=" + photoid + ", name=" + name + ", url=" + url + ", albumid=" + albumid + "]";
}
public Images(int photoid, String name, String url, int albumid) {
	super();
	this.photoid = photoid;
	this.name = name;
	this.url = url;
	this.albumid = albumid;
}
public Images() {
	super();
}

}
