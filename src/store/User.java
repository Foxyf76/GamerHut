package store;

import java.util.ArrayList;

public class User {

	public String ID;
	public String fullname;
	public String dob;
	public String username;
	public String email;
	public String password;
	public String imageURL;
	public ArrayList<Game> games;
	
	public User(String iD, String fullname, String dob, String username, String email, String password, String imageURL,
			ArrayList<Game> games) {
		ID = iD;
		this.fullname = fullname;
		this.dob = dob;
		this.username = username;
		this.email = email;
		this.password = password;
		this.imageURL = imageURL;
		this.games = games;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public ArrayList<Game> getGames() {
		return games;
	}

	public void setGames(ArrayList<Game> games) {
		this.games = games;
	}

	@Override
	public String toString() {
		return "User [ID=" + ID + ", fullname=" + fullname + ", dob=" + dob + ", username=" + username + ", email="
				+ email + ", password=" + password + ", imageURL=" + imageURL + ", games=" + games.toString() + "]";
	}
}
