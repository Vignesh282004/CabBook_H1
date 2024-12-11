<img src= "Github Images/cabtrip1.png">
# 🚖 TripLyft

This system is built with **Spring Boot** and **Hibernate**, supporting **Admin**, **User**, and **Driver** roles.

---

## 👨‍💻 **Admin Role**

- **🔧 Booking Management**:  
  View, confirm, and cancel bookings.

- **🚖 Cab Management**:  
  Add, remove, or update taxis.  
  Categorize taxis (e.g., sedan, SUV).

- **👨‍✈️ Driver Management**:  
  Manage driver registration and details.

- **💸 Pricing Management**:  
  Set and update pricing for rides.

- **🛠️ Availability Management**:  
  Track and manage taxi availability and status.

- **👥 User Management**:  
  Oversee user registrations and profiles.

---

## 🧑‍💻 **User Role**

- **📝 Registration**:  
  Register with personal details.

- **🔍 Taxi Search & Booking**:  
  Search taxis by source and destination.  
  Book taxis based on availability.

- **💵 View Charges**:  
  View pricing for rides before booking.

---

## 🚗 **Driver Role**

- **📝 Driver Registration**:  
  Register as a driver with personal information.

- **📋 Booking Information**:  
  View booking details, including the user’s name, source, and destination,Generating Bills for Trips of Users.

  ##  **Functionalities**
  1. Cabs will be Booked on the basis of Availability.
  2. Users can give the Ratings to Drivers .
  3. Implemnted a feature of User books a Cab , the drvier with the highest rating will be allocated accordingly.
  4. Drivers are Associated with cabs
  5. ss

![Endpoints](https://github.com/Vignesh282004/CabBook_H1/blob/main/Cab-Trip_H5/src/main/resources/static/image/cab.png)
<img src= "Github Images/ERDaigram.png">

# Contributing to TripLyft: Adding JWT Authentication

If you're interested in contributing to **TripLyft** by adding **JWT Authentication** functionality, follow these steps to fork the repository, make your changes, and submit a pull request.

---

### **🛠️ How to Contribute:**

#### 1. **Fork the Repository:**
   - Click the **Fork** button at the top right of the [TripLyft GitHub Repository](https://github.com/your-username/TripLyft).
   - This will create a **copy** of the repository in your GitHub account.

#### 2. **Clone Your Forked Repository:**
   - After forking, clone the repository to your local machine to work on it:
   
   ```bash
   git clone https://github.com/your-username/TripLyft.git
   cd TripLyft

#### 3. **Create a New Branch berfore doing it:**
   
   ```bash
  git checkout -b add-jwt-authentication

4. Implement JWT Functionality:
Add JWT Authentication functionality:
Generate and validate JWT tokens.
Secure Spring REST API endpoints using JWT.
Update Spring Security to use JWT.
Make sure to test the functionality properly after implementing JWT.

#### 5. ** Commit  Your Changes:
   
   ```bash
 git add .
git commit -m "Added JWT Authentication functionality"

#### 5. **  Push Your Changes:
   
   ```bash
git push origin add-jwt-authentication


6. Create a Pull Request:
Once you've pushed your changes, go to your forked repository on GitHub.
Click "Compare & Pull Request" to open a pull request.
Provide a detailed description of your changes and how you implemented JWT.


🙌 Thank You for Contributing!
Your contributions will make TripLyft more secure and scalabe.


