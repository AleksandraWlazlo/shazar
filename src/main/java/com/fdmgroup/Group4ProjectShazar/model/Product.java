package com.fdmgroup.Group4ProjectShazar.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.Transient;

@Entity
public class Product {
	
	static final String VEHICLES = "Vehicles";
	static final String ELECTRONICS = "Electronics";
	static final String HOUSE_GARDEN = "House & Garden";
	static final String FASHION = "Fashion";
	static final String SPORT_HOBBY = "Sport & Hobby";
	static final String HEALTH_BEAUTY = "Health & Beauty";
	
	@Id
	@GeneratedValue
	@Column(name="PRODUCT_ID")
	private int productId;
	
	private String name;
	private String description;
	
	private double rating;
	private int numberOfRatings;
	
	@Column(name="PICKUP_TIME")
	private String pickupTime;
	
	@Column(name="IS_AVAILABLE_NOW")
	private boolean isAvailableNow;
	
	@Column(name="PRICE_FOR_DAY")
	private double priceForDay;
	
	@Column(name="MAX_NUMBER_OF_DAYS")
	private int maxNumberOfDays;
	
	private String color;
	private String type;
	private String category;
	
	@Column(name = "main_image", length = 64)
	private String mainImage;
	
	@Column(name = "image2", length = 64)
	private String image2;
	
	@Column(name = "image3", length = 64)
	private String image3;
	
	@Column(name = "image4", length = 64)
	private String image4;
	
	@Column(name = "image5", length = 64)
	private String image5;
	
	@Column(name = "image6", length = 64)
	private String image6;
	
	@Column(name = "image7", length = 64)
	private String image7;
	
	@Column(name = "image8", length = 64)
	private String image8;
	
	@Column(name = "image9", length = 64)
	private String image9;
	
	@Column(name = "image10", length = 64)
	private String image10;
	
	@ManyToOne
	private Address pickupAdress;
	
	@ManyToOne
	private User owner;
	
	//Should come from the booking table or input from user?
	//Should be LocalDate or String?
	private LocalDate nextAvailableDate;
	

	public Product() {
		super();
	}

	public Product(String name, String description, String pickupTime, boolean isAvailableNow,
			double priceForDay, int maxNumberOfDays, String color, String type, String category, User user) {
		super();
		this.name = name;
		this.description = description;
		this.rating = 0.0;					//rating starts with 0?
		this.pickupTime = pickupTime;
		this.isAvailableNow = isAvailableNow;
		this.priceForDay = priceForDay;
		this.maxNumberOfDays = maxNumberOfDays;
		this.color = color;
		this.type = type;
		this.category = category;
		//this.pickupAdress = pickupAdress;
		this.owner = user;
		this.nextAvailableDate = LocalDate.now(); //takes today as a first available date
	}
	
	public Product(int productId, String name, String description, double rating, String pickupTime,
			boolean isAvailableNow, double priceForDay, int maxNumberOfDays, String color, String type, String category,
			String mainImage, String image2, String image3, String image4, String image5, String image6, String image7,
			String image8, String image9, String image10, Address pickupAdress, User user,
			LocalDate nextAvailableDate) {
		super();
		this.productId = productId;
		this.name = name;
		this.description = description;
		this.rating = 0.0; //rating starts with 0?
		this.pickupTime = pickupTime;
		this.isAvailableNow = isAvailableNow;
		this.priceForDay = priceForDay;
		this.maxNumberOfDays = maxNumberOfDays;
		this.color = color;
		this.type = type;
		this.category = category;
		this.mainImage = mainImage;
		this.image2 = image2;
		this.image3 = image3;
		this.image4 = image4;
		this.image5 = image5;
		this.image6 = image6;
		this.image7 = image7;
		this.image8 = image8;
		this.image9 = image9;
		this.image10 = image10;
	//	this.pickupAdress = pickupAdress;
		this.owner = user;
		this.nextAvailableDate = LocalDate.now(); //takes today as a first available date
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
	
	
	public int getNumberOfRatings() {
		return numberOfRatings;
	}

	public void setNumberOfRatings(int numberOfRatings) {
		this.numberOfRatings = numberOfRatings;
	}

	public String getMainImage() {
		return mainImage;
	}

	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}

	public String getImage4() {
		return image4;
	}

	public void setImage4(String image4) {
		this.image4 = image4;
	}

	public String getImage5() {
		return image5;
	}

	public void setImage5(String image5) {
		this.image5 = image5;
	}

	public String getImage6() {
		return image6;
	}

	public void setImage6(String image6) {
		this.image6 = image6;
	}

	public String getImage7() {
		return image7;
	}

	public void setImage7(String image7) {
		this.image7 = image7;
	}

	public String getImage8() {
		return image8;
	}

	public void setImage8(String image8) {
		this.image8 = image8;
	}

	public String getImage9() {
		return image9;
	}

	public void setImage9(String image9) {
		this.image9 = image9;
	}

	public String getImage10() {
		return image10;
	}

	public void setImage10(String image10) {
		this.image10 = image10;
	}

	public String getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(String pickupTime) {
		this.pickupTime = pickupTime;
	}

	public boolean isAvailableNow() {
		return isAvailableNow;
	}

	public void setIsAvailableNow(boolean isAvailableNow) {
		this.isAvailableNow = isAvailableNow;
	}

	public double getPriceForDay() {
		return priceForDay;
	}

	public void setPriceForDay(double priceForDay) {
		this.priceForDay = priceForDay;
	}

	public int getMaxNumberOfDays() {
		return maxNumberOfDays;
	}

	public void setMaxNumberOfDays(int maxNumberOfDays) {
		this.maxNumberOfDays = maxNumberOfDays;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Address getPickupAdress() {
		return pickupAdress;
	}

	public void setPickupAdress(Address pickupAdress) {
		this.pickupAdress = pickupAdress;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public LocalDate getNextAvailableDate() {
		return nextAvailableDate;
	}

	public void setNextAvailableDate(LocalDate nextAvailableDate) {
		this.nextAvailableDate = nextAvailableDate;
	}

	public int getProductId() {
		return productId;
	}

//	@Override
//	public String toString() {
//		return "Product [productId=" + productId + ", name=" + name + ", description=" + description + ", rating="
//				+ rating + ", pickupTime=" + pickupTime + ", isAvailableNow=" + isAvailableNow + ", priceForDay="
//				+ priceForDay + ", maxNumberOfDays=" + maxNumberOfDays + ", color=" + color + ", type=" + type
//				+ ", category=" + category + ", pickupAdress=" + pickupAdress + ", owner=" + owner
//				+ ", nextAvailableDate=" + nextAvailableDate + "]";
//	}
	
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", description=" + description + ", rating="
				+ rating + ", pickupTime=" + pickupTime + ", isAvailableNow=" + isAvailableNow + ", priceForDay="
				+ priceForDay + ", maxNumberOfDays=" + maxNumberOfDays + ", color=" + color + ", type=" + type
				+ ", category=" + category + ", mainImage=" + mainImage + ", image2=" + image2 + ", image3=" + image3
				+ ", image4=" + image4 + ", image5=" + image5 + ", image6=" + image6 + ", image7=" + image7
				+ ", image8=" + image8 + ", image9=" + image9 + ", image10=" + image10 + ", pickupAdress="
				+ pickupAdress + ", owner=" + owner + ", nextAvailableDate=" + nextAvailableDate + "]";
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return
			productId ==other.getProductId();
	}

	@Transient
    public String getMainProductImagePath() {
        if (mainImage == null) return null;
         
        return "/product-pictures/" + productId + "/" + mainImage;
    }
	
	@Transient
    public String getProductImageTwoPath() {
        if (image2 == null) return null;
         
        return "/product-pictures/" + productId + "/" + image2;
    }
	
	@Transient
    public String getProductImageThreePath() {
        if (image3 == null) return null;
         
        return "/product-pictures/" + productId + "/" + image3;
    }
	
	@Transient
    public String getProductImageFourPath() {
        if (image4 == null) return null;
         
        return "/product-pictures/" + productId + "/" + image4;
    }
	
	@Transient
    public String getProductImageFivePath() {
        if (image5 == null) return null;
         
        return "/product-pictures/" + productId + "/" + image5;
    }
	
	@Transient
    public String getProductImageSixPath() {
        if (image6 == null) return null;
         
        return "/product-pictures/" + productId + "/" + image6;
    }
	
	@Transient
    public String getProductImageSevenPath() {
        if (image7 == null) return null;
         
        return "/product-pictures/" + productId + "/" + image7;
    }
	
	@Transient
    public String getProductImageEightPath() {
        if (image8 == null) return null;
         
        return "/product-pictures/" + productId + "/" + image8;
    }
	
	@Transient
    public String getProductImageNinePath() {
        if (image9 == null) return null;
         
        return "/product-pictures/" + productId + "/" + image9;
    }
	
	@Transient
    public String getProductImageTenPath() {
        if (image10 == null) return null;
         
        return "/product-pictures/" + productId + "/" + image10;
    }
	
}
