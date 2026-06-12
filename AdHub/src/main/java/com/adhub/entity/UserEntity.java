package com.adhub.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="`tblUser`")
public class UserEntity {

	@Id
	@GeneratedValue
	@Column(name = "`Id`")
	private Long id;
	
	@Column(name="`FullName`")
	private String name;
	
	
	@OneToMany(mappedBy = "seller",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<ProductEntity> products;
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setProducts(List<ProductEntity> products) {
		
		this.products = products;
	}
	
	public List<ProductEntity> getProducts(){
		return this.products;
	}
}
