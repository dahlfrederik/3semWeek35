# WEEK 35 REVIEW  

## Regarding unit tests
All tests done regarding databases should have been done with a specific test database, this was not the case and would not happen in production. 

## Status on exercise completion? 
All exercises are completed to a full extend unless anything else is noted at the specific exercise. 

## Tuesday 
##### [Exercise manual 1 :](https://docs.google.com/document/d/1Uib8GtBXmQZJ9x5tqXXHt1UYkkRPo9zKwugWa87bzUI/edit)
##### [Exercise manual 2 : ](https://docs.google.com/document/d/131iZ7z3XKBjAGcB8qUqX9y0B2FxsoYu0zjRy6KxObPg/edit)

* Exercise 1 

Source code:	[Point](https://github.com/dahlfrederik/3semWeek35/tree/master/01-Tuesday/point)

* Exercise 2 

Source code:	[Customer](https://github.com/dahlfrederik/3semWeek35/tree/master/01-Tuesday/customer)
	
* Exercise 3

Source code:	[library](https://github.com/dahlfrederik/3semWeek35/tree/master/01-Tuesday/library)
	
* Exercise 4 

Source code:	[firstjpa1](https://github.com/dahlfrederik/3semWeek35/tree/master/01-Tuesday/firstjpa)	

## Wednesday
##### [Exercise manual :](https://docs.google.com/document/d/1gdtrSIb_RiEE3qv5hPwrzBrNaowHA-MPFXR8LP9CKJk/edit)

* Exercise 1

Source code:	[rest1](https://github.com/dahlfrederik/3semWeek35/tree/master/02-Wednesday/rest1)
	
## Thursday
##### [Exercise manual : ](https://docs.google.com/document/d/1c4uti7oLiipp1Sdny9Rwc1aOStfn9aasmWhhhzuTQS8/edit)

* Exercise 1

Source code:	[jpql-demo-for-day4](https://github.com/dahlfrederik/3semWeek35/tree/master/03-Thursday/jpql-demo-for-day4-2)


## Friday 

#### Exercise 1
##### Note: I experienced some down time on the servlet because docker shut down automaticly so if you cannot acces the droplet version that might be why (though it should be up and running). Also the Databases has a thing with emptying it self... i cannot figure out why.  
##### [Exercise manual : ](https://docs.google.com/document/d/1c4uti7oLiipp1Sdny9Rwc1aOStfn9aasmWhhhzuTQS8/edit)



Source code: [week1-simple-jpa-rest](https://github.com/dahlfrederik/3semWeek35/tree/master/03-Thursday/week1-simple-jpa-rest-2)
	
* Link for [droplet](http://157.230.31.58:8081/jpa_rest_startup-1.0/api/employee/all) 

The following is all the commands / methods that can be invoked on the droplet:

* Show all employees: http://157.230.31.58:8081/jpa_rest_startup-1.0/api/employee/all
* Show employee by ID:  Use http://157.230.31.58:8081/jpa_rest_startup-1.0/api/employee/byid/{id}
* Show highest salary: http://157.230.31.58:8081/jpa_rest_startup-1.0/api/employee/highestpaid
* Show employee by name: http://157.230.31.58:8081/jpa_rest_startup-1.0/api/employee/byname/{name}

#### Exercise 2
##### Note: I experienced some down time on the servlet because docker shut down automaticly so if you cannot acces the droplet version that might be why (though it should be up and running). Also the Databases has a thing with emptying it self... i cannot figure out why. 



##### [Exercise manual : ](https://docs.google.com/document/d/1HdHiORGNyteRpn7MoOixowxL10LQuUHt9XxAKtL9r0o/edit)

Source code: [bankcustomer](https://github.com/dahlfrederik/3semWeek35/tree/master/04-Friday/bankcustomer)

* Link for [droplet](http://157.230.31.58:8081/bankcustomer-1.0/api/bankcustomer/all)

The following is all the commands / methods that can be invoked on the droplet:

* Show all customers: http://157.230.31.58:8081/bankcustomer-1.0/api/bankcustomer/all
* Show customer by ID: http://157.230.31.58:8081/bankcustomer-1.0/api/bankcustomer/byid/1 



