*	Buy X quantity of item for the price of Y (3 kgs of oranges for 1.5 kgs)
	Solution – (100 - 100*Y/X)% reduce the price of item

*	50% off on price of X (Pay 75 for 1 kg of oranges instead of Rs 150) – 
	Solution - 50% of on item price
	
*	Buy 1 X & 1 Y for Z (Buy 1 kg of apple and 1 kg of orange for 100)
	Solution -  X - X((X+Y -Z)/(X+Y)) + Y - Y((X+Y -Z)/(X+Y)) = Z at the time of order calculation
	
	
Lets assume that promotions are like 
	1. Buy 5kg (X1) Oranges in price of 2Kg (X2)while orange is 120rs(orangePrice) per kg.
	2. Buy 1Kg Orange at the price of 120/2
	3. Buy 1Kg Orange and 1kg Apple at the price of 100. Where apple price is 180 rs/kg.
	
Assumption 1:
1. Assuming that all the promotions are stackable hence all can be applied.
2. Application is only for 3 number of promotions with fixed input data format as there is FileIO which is as per OrderData.txt and PromotionsData.txt.
3. So far there is a bit hard coded logic for all the possible promotions combinations from 3 promotions where I am assuming that 
   Promotion 1 :BuyQtyXInPriceOfQtyYPromotion promotion 
   Promotion 2 :PercentOffOnCategotryPromotion promotion
   Promotion 3 :SKUCombinationOrderPromotion promotion
   So there will be total 15 combinations (with order/priority) out of these 3 promotions {"123", "132", "213", "231", "312", "321"} 
   e.g Promotion order is 132 which means P1 --> P3 --> P2 is the promotion execution order.
