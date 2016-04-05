package pizza.domain.discountprovider;

import java.util.ArrayList;
import java.util.List;

import pizza.domain.discountprovider.discounts.AccumulativeCardDiscount;
import pizza.domain.discountprovider.discounts.FourthPizzaDiscount;

public class DiscountProvider {
	
	private List<Discount> discountList;
	
	{
		discountList = new ArrayList<Discount>();
		discountList.add(new AccumulativeCardDiscount());
		discountList.add(new FourthPizzaDiscount());
	}

	public List<Discount> getDiscountList() {
		return discountList;
	}

	public void setDiscountList(List<Discount> discountList) {
		this.discountList = discountList;
	}

}
