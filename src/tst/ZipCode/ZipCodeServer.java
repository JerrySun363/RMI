package tst.ZipCode;

public interface ZipCodeServer // extends YourRemote or whatever
{
	public void initialise(ZipCodeList newlist);

	public String find(String city);

	public ZipCodeList findAll();

	public void printAll();

	public int sum(int int1, int int2);

}
