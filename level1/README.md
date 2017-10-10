# Level 1

We are building an e-commerce website. Our customers can:
  - add articles to a virtual cart
  - checkout the cart contents
  - get it delivered the next day

The customer is charged the sum of the prices of each article in their cart.

Prices are expressed in cents.

Write code that generates `output.json` from `data.json`

# Solution

Java project using maven

`data.json` is embedded in `./src/main/resources`

DataStore class has a main method wich generate `output.json` at the root of the project

