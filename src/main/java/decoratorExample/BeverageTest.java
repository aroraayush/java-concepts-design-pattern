package decoratorExample;
/** The example demonstrates how to use the Decorator pattern.
 *  The code is from Head First Design Pattern book.
 *
 */

public class BeverageTest {

	public boolean func(int n1, int n2) {
		if (n1 <= 0)
			if (n2 <= 0)
				return true;
			else
				return false;
		else
			if (n2 > 0)
				return true;
			else
				return false;
	}

	public static void main(String args[]) {


		Beverage beverage1 = new Espresso();
		System.out.println(beverage1.getDescription()
				+ " $" + beverage1.cost());

		beverage1 = new BeverageWithWhippedCream(new BeverageWithMilk(beverage1));
		System.out.println(beverage1.getDescription()
				+ " $" + beverage1.cost());

		System.out.println("-----------------------");
		Beverage beverage2 = new Decaf();
		System.out.println(beverage2.getDescription()
				+ " $" + beverage2.cost());
		beverage2 = new BeverageWithMilk(beverage2);
		beverage2 = new BeverageWithWhippedCream(beverage2);
		beverage2 = new BeverageWithSoyMilk(beverage2);
		System.out.println(beverage2.getDescription() 
				+ " $" + beverage2.cost());
	}
}
