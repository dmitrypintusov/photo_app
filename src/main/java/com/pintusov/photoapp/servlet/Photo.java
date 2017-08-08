package com.pintusov.photoapp.servlet;

import java.util.Date;

/**
 * Project: photo_app
 * Created by: USER
 * Date: 04.08.17.
 */
public class Photo {
	private long id;
	private byte[] data;
	private String filename;
	private String name;
	private Date dateTaken;
	private boolean isPublic;

	public Photo(long id, byte[] data, String filename, String name, Date dateTaken, boolean isPublic) {
		this.id = id;
		this.data = data;
		this.filename = filename;
		this.name = name;
		this.dateTaken = dateTaken;
		this.isPublic = isPublic;
	}

	public Photo () {}

	public String getFilename() {
		return filename;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public byte[] getData() {
		return data;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public Date getDateTaken() {
		return dateTaken;
	}

	public String getViewUri() {
		return "DisplayPhotoServlet?photoid=" + this.id;
	}

	@Override
	public String toString() {
		return "Photo: " + this.name;
	}
}
