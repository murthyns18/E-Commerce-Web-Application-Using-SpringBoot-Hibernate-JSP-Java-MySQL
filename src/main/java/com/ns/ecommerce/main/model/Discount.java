package com.ns.ecommerce.main.model;

import jakarta.persistence.*;

@Entity
@Table(name = "discount")
public class Discount 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String offerName;
	private int discountPercentage;
	@Column(name = "image_path")
	private String imagePath;
	private boolean available;

	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public String getOfferName() 
	{
		return offerName;
	}

	public void setOfferName(String offerName) 
	{
		this.offerName = offerName;
	}

	public int getDiscountPercentage() 
	{
		return discountPercentage;
	}

	public void setDiscountPercentage(int discountPercentage) 
	{
		this.discountPercentage = discountPercentage;
	}

	public String getImagePath() 
	{
		return "/uploads/" + imagePath;
	}
	
	public void setImagePath(String imagePath)
	{
		this.imagePath = imagePath;
	}
	public boolean isAvailable() 
	{
		return available;
	}

	public void setAvailable(boolean available) 
	{
		this.available = available;
	}
}
