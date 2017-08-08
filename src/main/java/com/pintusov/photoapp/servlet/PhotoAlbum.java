package com.pintusov.photoapp.servlet;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Project: EETestApp
 * Created by: USER
 * Date: 17.07.17.
 */
@Named("photoAlbum")
@SessionScoped
public class PhotoAlbum implements Serializable {
	private List<Photo> photos = new ArrayList<>();
	private Photo currentPhoto = null;

	public PhotoAlbum() {}

	public List<Photo> getPhotos() {
		return this.photos;
	}

	public Photo getCurrentPhoto() {
		return this.currentPhoto;
	}

	public void setCurrentPhoto(Photo photo) {
		this.currentPhoto = photo;
	}

	public void addPhoto (Photo photo) {
		if(this.containsId(photo.getId())){
			this.photos.remove(this.getPhotoById(photo.getId()));
		}
		this.photos.add(photo);
	}

	private Photo getPhotoById (long id) {
		for (Photo photo: this.photos) {
			if (photo.getId() == id) {
				return photo;
			}
		}
		return null;
	}

	public boolean containsId (long id) {
		return this.getPhotoById(id) != null;
	}

	public void removePhoto (Photo photo) {
		this.photos.remove(photo);
	}

	public Photo getPhoto (long id) {
		for (Photo photo: this.photos) {
			if (photo.getId() == id) {
				return photo;
			}
		}
		return null;
	}

	public List getPhotoNames () {
		List<String> names = new ArrayList<>();
		for (Photo photo: this.photos) {
			names.add(photo.getName());
		}
		return names;
	}

	public List getPhotoFilenames () {
		List<String> filenames = new ArrayList<>();
		for (Photo photo: this.photos) {
			filenames.add(photo.getFilename());
		}
		return filenames;
	}

	public int getIndexOf (String photoName) {
		for (Photo photo: this.photos) {
			if (photo.getFilename().equals(photoName)) {
				return photos.indexOf(photo);
			}
		}
		return -1;
	}

	public byte[] getPhotoData (int i) {
		Photo photo = this.photos.get(i);
		return photo.getData();
	}

	public byte[] getPhotoDataByName (String name) {
		for (Photo photo: this.photos) {
			if (photo.getFilename().equals(name)) {
				return photo.getData();
			}
		}
		return null;
	}

	public String getPhotoName (int i) {
		Photo photo = this.photos.get(i);
		return photo.getFilename();
	}

	public int getPhotoCount () {
		return this.photos.size();
	}
}
