package br.com.pre.dojo.modelo;

public class Gun implements Comparable<Gun>{
	private String name;
	private int quantity;

	public int compareTo(Gun o) {
		return quantity - o.quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void add() {
		this.quantity++;
	}
	
	

}
