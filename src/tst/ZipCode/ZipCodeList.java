package tst.ZipCode;

public class ZipCodeList {
	private String city;
	private String ZipCode;
	private ZipCodeList next;

	public ZipCodeList(String c, String z, ZipCodeList n) {
		setCity(c);
		setZipCode(z);
		setNext(n);
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return ZipCode;
	}

	public void setZipCode(String zipCode) {
		ZipCode = zipCode;
	}

	public ZipCodeList getNext() {
		return next;
	}

	public void setNext(ZipCodeList next) {
		this.next = next;
	}
}
