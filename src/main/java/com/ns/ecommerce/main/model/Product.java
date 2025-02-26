package com.ns.ecommerce.main.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String description;
	private double price;
	private String img_url;

	public Product() {}

	public Product(String name, String description, int price, String img_url) 
	{
		this.name = name;
		this.description = description;
		this.price = price;
		this.img_url = img_url;
	}

	public Long getId() 
	{ 
		return id;
	}
	public void setId(Long id) 
	{
		this.id = id; 
	}

	public String getName()
	{
		return name; 
	}
	public void setName(String name)
	{ 
		this.name = name; 
	}

	public String getDescription() 
	{
		return description;
	}
	public void setDescription(String description) 
	{ 
		this.description = description;
	}

	public double getPrice()
	{ 
		return price; 
	}
	public void setPrice(double price2)
	{ 
		this.price = price2; 
	}

	public String getImageUrl() {
	    return "/uploads/" + this.img_url; 
	}

	public void setImageUrl(String img_url)
	{ 
		this.img_url = img_url;
	}
}
