# java-orders

A student that completes this project shows that they can:
* perform CRUD operations on an RDBMS using JPA and Hibernate.

## Introduction

This is a basic database scheme with customers, orders, and sales agents.

## Instructions

Create a REST api server to store and read data from an in memory H2 database. 

The table layouts are as follows

* AGENTS
  * agentcode primary key, not null Long
  * agentname string
  * workingarea string
  * commission double
  * phone string
  * country string

* CUSTOMERS
  * custcode primary key, not null Long
  * CUSTNAME String, not null
  * custcity String
  * workingarea String
  * custcountry String
  * grade String
  * openingamt double
  * receiveamt double
  * paymentamt double
  * outstandingamt double
  * phone String
  * agentcode Long foreign key (one agent to many customers) not null

* ORDERS
  * ordnum primary key, not null Long
  * ordamount double
  * advanceamount double
  * custcode Long foreign key (one customer to many orders) not null
  * orddescription String

* Customers has a foreign key to Agents (agentcode) this means:
  * Customers has a Many to One relationship to Agents and
  * Agents has a One to Many relationship to Customers

* Orders has a foreign key to Customers (custcode) 
  * Orders has a Many to One relationship to Customers and
  * Customers has a One to Many relationship to Orders

* Create the entities needed to store this data
* A Java class called SeedData has been provided with seed data. You can use this class directly or modify it to fit your models. However, the data found in the class is the seed data to use!
 
Expose the following end points

* GET /customer/order - Returns all customers with their orders
* GET /customer/name/{custname} - Returns all orders for a particular customer based on name

* POST /data/customer/new - Adds a new customer
  * You can use the following as test data
  
Coming soon!!!

* PUT /data/customer/update/{custcode} - Updates the customer based off of custcode
* DELETE /data/customer/delete/{custcode} - Deletes the customer based off of custcode
  * this should also delete the orders of that customer

Stretch goals
* /agent/{agentcode} - Deletes an agent if they are not assigned to a customer or order
