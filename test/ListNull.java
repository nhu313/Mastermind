import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;


public class ListNull {

	
	@Test
	public void test(){
		List<Integer> list = new ArrayList<Integer>();
		list.add(5);
		list.add(null);
		list.add(4);
		
		Assert.assertEquals(3, list.size());
		
		list.removeAll(Collections.singleton(null));

		Assert.assertEquals(2, list.size());
	}
}
