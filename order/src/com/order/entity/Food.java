package com.order.entity;

/**
 * 菜品
 * @author ApnLee
 *
 */
public class Food {
	
	private int id;
	private String foodName;
	private double price;
	private double mprice;
	private String remark;
	private String img;
	
	private int foodTypeId;//类型id
	private String typeName;//类型名称

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double prince) {
		this.price = prince;
	}

	public double getMprice() {
		return mprice;
	}

	public void setMprice(double mprince) {
		this.mprice = mprince;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getFoodTypeId() {
		return foodTypeId;
	}

	public void setFoodTypeId(int foodTypeId) {
		this.foodTypeId = foodTypeId;
	}

	@Override
	public int hashCode() {
		
		return this.id;
	}

	@Override
	public boolean equals(Object obj) {
		Food f=(Food)obj;
		return this.foodName.equals(f.getFoodName());
	}
	
	
	
}
