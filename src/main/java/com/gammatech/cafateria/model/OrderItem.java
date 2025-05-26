package com.gammatech.cafateria.model;

public class OrderItem {
	  private Coffee coffee;
	    private Integer quantity;
	    private Double price;

	    public Coffee getCoffee() {
	        return coffee;
	    }

	    public void setCoffee(Coffee coffee) {
	        this.coffee = coffee;
	    }

	    public Integer getQuantity() {
	        return quantity;
	    }

	    public void setQuantity(Integer quantity) {
	        this.quantity = quantity;
	    }

	    public Double getPrice() {
	        return price;
	    }

	    public void setPrice(Double price) {
	        this.price = price;
	    }

	    public Double getSubtotal() {
	        return price * quantity;
	    }

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        OrderItem orderItem = (OrderItem) o;
	        return coffee != null && coffee.equals(orderItem.coffee);
	    }

	    @Override
	    public int hashCode() {
	        return coffee != null ? coffee.hashCode() : 0;
	    }
}
