package test1.domain;

/*
 * 地址类
 * */
public class Address {
	private String City;//城市
	private String Street;//街道
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getStreet() {
		return Street;
	}
	public void setStreet(String street) {
		Street = street;
	}
	@Override
	public String toString() {
		return "Address [City=" + City + ", Street=" + Street + "]";
	}
	
}
