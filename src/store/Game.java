package store;

public class Game {
	
	public String ID;
	public String name;
	public int year;
	public String description;
	public String platform;
	public String genre;
	public double rating;
	public String image;
	
	public Game(String ID, String name, int year, String description, String platform, String genre, double rating,
			String image) {
		
		this.ID = ID;
		this.name = name;
		this.year = year;
		this.description = description;
		this.platform = platform;
		this.genre = genre;
		this.rating = rating;
		this.image = image;
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Game [ID=" + ID + ", name=" + name + ", year=" + year + ", description=" + description + ", platform="
				+ platform + ", genre=" + genre + ", rating=" + rating + ", image=" + image + "]";
	}
	
	
}
