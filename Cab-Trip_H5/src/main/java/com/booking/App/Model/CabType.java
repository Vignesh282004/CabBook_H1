package com.booking.App.Model;

public enum CabType {

	ECONOMY, COMPACT, NON_AC, LUXURY;
	
	Double price;
	
	 public void setPrice(double price) {
	        this.price = price;
	    }
	
	public int sittingCapacity() 
	{
		switch(this)
		{
		case ECONOMY : return 5;
		
		case COMPACT : return 4;
		
		case NON_AC : return 11;
		
		case LUXURY : return 2;
		
		default : return 0;
		}
		 //new type switch case
		/* 
		 return switch(this) {
		 	   case ECONOMY -> 5;
            case COMPACT -> 4;
            case NON_AC -> 11;
            case LUXURY -> 2;
            default -> 0;
		 }
		  */	
	}
		public 	double providePrice() 
		{
			double price = 2;
			
			  switch (this) {
	            case ECONOMY:
	                return price *= 5.25;
	            case COMPACT:
	                return price *= 4.0;
	            case NON_AC:
	                return price *= 2;
	            case LUXURY:
	                return price *= 8;
	            default:
	                return price;
	        }
		}
}



