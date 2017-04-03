# _Hair Salon_

#### _An application for the manager of a salon to track stylists and clients._

#### By _**Nathaniel Meyer**_

## Description

_This is a simple, lightweight web application to allow the manager of a salon to track stylists and their clients._

_Project specifications / BDD_

_User Stories_

* As a salon employee, I need to be able to see a list of all our stylists.
* As an employee, I need to be able to select a stylist, see their details, and see a list of all clients that belong to that stylist.
* As an employee, I need to add new stylists to our system when they are hired.
* As an employee, I need to be able to add new clients to a specific stylist.
* As an employee, I need to be able to update a stylist's details.
* As an employee, I need to be able to update a client's details.
* As an employee, I need to be able to delete a stylist if they're no longer employed here. (What happens to the clients?)
* As an employee, I need to be able to delete a client if they no longer visit our salon.

## Setup/Installation Requirements

* _In any folder with git installed, run "git clone https://github.com/nathanielimeyer/hair-salon.git"._
* _THIS PROJECT DEPENDS ON A DUMMY ROW IN THE STYLISTS TABLE. PLEASE IMPORT SAMPLE DATA BY DOING THE FOLLOWING:_
* _Connect to psql and run: CREATE DATABASE hair_salon;_
* _In terminal run "psql hair_salon < hair_salon.sql"_
* _type "cd ./hair-salon" and enter_
* _type "gradle run" and enter_
* _In a browser load localhost:4567_

* _TEST CASES WERE ALSO WRITTEN WITH THE EXPECTATION OF A DUMMY ROW IN THE STYLISTS TABLE. THEY WILL FAIL UNLESS YOU DO THE FOLLOWING:_
* _Connect to psql and run: CREATE DATABASE hair_salon_test;_
* _In terminal run "psql hair_salon_test < hair_salon_test.sql"_


## Known Bugs

## Support and contact details

_nathanielimeyer@gmail.com_

## Technologies Used

Java, JUnit, Gradle, Spark, Velocity

### License

*All rights reserved*

Copyright (c) 2017 **_Nathaniel Meyer_**
