OrderUP Architecture

OrderUp has been implemented following a 3-tier architecture.

Objects:
- FoodItem: This object represents a FoodItem in the menu of a Restaurant
- GiftCard: This object represents a GiftCard in the account of a User
- Restaurant: This object represents a restaurant in the database 
- User: This object represents a User of the application.

Data/Persistence Layer:
- PersistenceException: holds all the various exception messages we will be throwing from the persistence layer to the logic layer.
- RestaurantPersistenceHSQLDB & UserPersistenceHSQLDB: These classes implement interfaces called RestaurantPersistence and UserPersistence respectively. 
  - These SQL-based implementations store Restaurants, FoodItems, and Users data. 
  - Restaurant and FoodItems can be retrieved by their unique restaurant and food item IDs.
  - User information is added to the database as a User creates an account on the application.
  - The user database also holds the status of a user's cart containing their food items.

Presentation Layer:
- CheckoutActivity the page containing the UI for the checkout page after a user views their cart.
- LoginActivity the login page loads and asks the user to input the credentials to access the application further which leads it to calling the HomeFragment class.
- HomeFragment consists of the bottom navigation and the display of the itemView for the Restaurants. Clicking on the restaurant image will lead to the holder and    adapter for the food/item.
- MenuViewHolder/MyViewHolder is the part of holder which is stores the adapter to present the restaurant data and/or the food/item data.
- MenuAdapter is the class which is responsible to show the individual data set i.e the restaurant and the food items separately.
- CustomerSupportFragment is the smaller fragment that leads the user to the page where it can access the contact information of the service department.
- RegisterActivity is used to present the registration page for the user to create and account.
- RestaurantActivity is used to display the individual details of the restaurant data including it's menu.
- ErrorPopUp is used to take a message string and print it out to the application.
- MainActivity is the homepage of the entire application which consists of the fragments that user can access as shortcuts to his/her details/homepage/customersupportfragment page.
- MenuHolder holds the menu components together for each food item, +/- buttons,image, info, cart button etc.
- MyCartAdapter handles the menu items in the cart recycler view on the cart page.
- MyCartsActivity is the Order summary page which has the delivery and pick up option, totals and the cart items.
- RestaurantAdapter handles the restaurant items in the restaurant recycler view on the home page.
- RestaurantHolder holds the restaurant components together for each restaurant item, image, name and description.
- UserAccountFragment holds the entire user account page and all the buttons for membership, adding address, adding credit card, etc.

Logic Layer:
- MyException: holds all the various exception messages we will be throwing in the logic layer.
- RestaurantServices: accesses the List of Restaurants from the database in the Persistence Layer
- Search_algorithm: takes user input, searches restaurants by name, category and food type, and returns matching restaurants in our database.
- Services: User and Restaurant database is setup to be ready to use.
- UserServices: accesses User data from persistence to presentation: User First and Last name, Address,and Wallet Balance.
- UserVerification: verifies Information provided by User: name,email and password, address, credit card and gift card format before creating an Account for the User.
