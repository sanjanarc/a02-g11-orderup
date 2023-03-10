OrderUP Architecture

OrderUp has been implemented following a 3-tier architecture.

Objects:
- FoodItem: This object represents a FoodItem in the menu of a Restaurant
- GiftCard: This object represents a GiftCard in the account of a User
- Restaurant: This object represents a restaurant in the database 
- User: This object represents a User of the application.

Data/Persistence Layer:
- RestaurantPersistenceHSQLDB and RestaurantPersistenceStub: These classes implement the interface called RestaurantPersistence. 
  - Both SQL-based implementation and stub databases store Restaurants, FoodItems, and Users data. 
  - Restaurant and FoodItems can be retrieved by their unique restaurant and food item IDs.
  - User information is added to the database as a User creates an account on the application.

Presentation Layer:
- 

Logic Layer:
- RestaurantServices: accesses the List of Restaurants from the database in the Persistence Layer
- Services: User and Restaurant database is setup to be ready to use.
- UserServices: accesses User data from persistence to presentation: User First and Last name, Address,and Wallet Balance.
- UserVerification: verifies Information provided by User: name,email and password, address, credit card and gift card format before creating an Account for the User.
