package wej01.book;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import wej01.bookmanager.BookManager;

public class BookManagerTest {
	
	private BookManager managerToTest = new BookManager();
	
	@Test
	public void addTest() {
		Car testCar = new Car("Ford", "Multipla");
		managerToTest.add(testCar);
		
		Assert.assertEquals(testCar, managerToTest.allCars.getFirst());
	}
	@Test
	public void getAllTest() {
		Car testCar = new Car("Ford", "Multipla");
		Car testCar2 = new Car("Ford", "T");
		
		managerToTest.allCars.add(testCar);
		managerToTest.allCars.add(testCar2);
		
		List<Car> cars = managerToTest.getAll();
		
		Assert.assertEquals(testCar, cars.get(0));
		Assert.assertEquals(testCar2, cars.get(1));
		
		//assertThat(cars.get(0), testCar);
		
		
	}
}
