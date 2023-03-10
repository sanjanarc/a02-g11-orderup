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
- LoginActivity the login page loads and asks the user to input the credentials to access the application further which leads it to calling the HomeFragment class.
- HomeFragment consists of the bottom navigation and the display of the itemView for the Restaurants. Clicking on the restaurant image will lead to the holder and adapter for the food/item.
- MenuViewHolder/MyViewHolder is the part of holder which is stores the adapter to present the restaurant data and/or the food/item data.
- MenuAdapter/MyAdapter is the class which is responsible to show the individual data set i.e the restaurant and the food items separately.
- CustomerSupportFragment is the smaller fragment that leads the user to the page where it can access the contact information of the service department.
- RegistrationActivity is used to present the registration page for the user to create and account.
- RestaurantActivity is used to display the individual details of the restaurant data.
- ErrorPopUp is used to take a message string and print it out to the application.
- MainActivity is the homepage of the entire application which consists of the fragments that user can access as shortcuts to his/her details/homepage/customersupportfragment page.

Logic Layer:
- RestaurantServices: accesses the List of Restaurants from the database in the Persistence Layer
- Services: User and Restaurant database is setup to be ready to use.
- UserServices: accesses User data from persistence to presentation: User First and Last name, Address,and Wallet Balance.
- UserVerification: verifies Information provided by User: name,email and password, address, credit card and gift card format before creating an Account for the User.
