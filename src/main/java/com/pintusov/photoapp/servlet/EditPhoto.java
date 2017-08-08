package com.pintusov.photoapp.servlet;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * Project: photo_app
 * Created by: USER
 * Date: 04.08.17.
 */
@Named("editPhotoBean")
@SessionScoped
public class EditPhoto implements Serializable {
	@Inject
	PhotoAlbum photoAlbum;
	private long id = -1;
	private boolean isPublic = false;
	private String filename;
	private String photoName;
	private Date dateTaken = new Date();
	private byte[] photoData = null;
	private String previewUrl;

	public EditPhoto() {}

	public boolean isNew () {
		return this.id == -1L;
	}

	public boolean isPublic () {
		return isPublic;
	}

	public void setPublic (boolean isPublic) {
		this.isPublic = isPublic;
	}

	public Date getDateTaken () {
		return dateTaken;
	}

	public void setDateTaken (Date dateTaken) {
		this.dateTaken = dateTaken;
	}

	public String getPhotoName () {
		return this.photoName;
	}

	public void setPhotoName (String photoName) {
		this.photoName = photoName;
	}

	public String getFilename () {
		return this.filename;
	}

	public void reset() {
		this.id = -1L;
		this.dateTaken = new Date();
		this.filename = null;
		this.photoData = null;
		this.photoName = null;
	}

	public void commit () {
		this.photoAlbum.addPhoto(this.getPhoto());
		this.reset();
	}

	private void collectData (Part uploadedPart) {
		if (uploadedPart.getSize() == 0) {return;}
		try {
			ByteArrayOutputStream baos;
			try (InputStream is = uploadedPart.getInputStream()){
				baos = new ByteArrayOutputStream();
				int i = 0;
				while ((i=is.read()) != -1) {
					baos.write(i);
				}
				photoData = baos.toByteArray();
			}
			baos.close();
		} catch (Exception e) {
			photoData = null;
			throw new RuntimeException(e.getMessage());
		}
		this.filename = uploadedPart.getSubmittedFileName();
		this.photoName = this.filename.substring(0, this.filename.indexOf("."));
	}

	public byte[] getPhotoData () {
		return this.photoData;
	}

	public void setUploadedPart (Part p) {
		this.collectData(p);
	}

	public Part getUploadedPart () {
		return null;
	}

	public String getPreviewUrL () {
		if (this.hasPhoto()) {
			return this.previewUrl = "DisplayPhotoServlet";
		} else {
			return this.previewUrl = "nopreview.png";
		}
	}

	public void setPhoto (Photo p) {
		this.id = p.getId();
		this.isPublic = p.isPublic();
		this.filename = p.getFilename();
		this.photoName = p.getName();
		this.dateTaken = p.getDateTaken();
		this.photoData = p.getData();
	}

	public boolean hasPhoto () {
		return (this.photoData != null);
	}

	public Photo getPhoto() {
		if (this.hasPhoto()) {
			Photo p = new Photo (this.photoAlbum.getPhotoCount()+1, this.getPhotoData(),
					this.getFilename(), this.photoName, this.dateTaken, this.isPublic);
			return p;
		} else {
			return null;
		}
	}
}
