package tst.ZipCodeR;

import util.*;

public interface ZipCodeRList extends Remote// extends YourRemote or whatever
{
	public String find(String city);

	public ZipCodeRList add(String city, String zipcode);

	public ZipCodeRList next();
}
