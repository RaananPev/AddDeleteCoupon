# Software Quality Engineering - System Testing
This is a repository for the system-testing assignment of the Software Quality Engineering course at the [Ben-Gurion University](https://in.bgu.ac.il/), Israel.

## Assignment Description
In this assignment, we tested an open-source software called [OpenCart]((https://www.opencart.com/)).

OpenCart is a popular open-source e-commerce platform that allows businesses to set up and manage their online stores. It provides a user-friendly interface and a wide range of features to help merchants create and customize their online stores. OpenCart offers various themes and extensions to enhance the functionality and appearance of the store. It supports multiple currencies and languages, making it suitable for businesses operating in different regions. OpenCart also includes features such as product management, order processing, payment gateways, and shipping options, making it a comprehensive solution for online retailers.

## Installation
In order to run you will need selenium and provengo

*Selenium:*
1. use git clone and clone this repository into your own computer.
2. downlaod your vertion of chromdriver and make sure to update the path of it in the project.
3. add to the project libs the following jar selenium-server-4.18.1.jar from .../rep/Selenium
4. run and make sure it works.

*Provengo:*
1. Download Provengo (jar + script) and remove the build number from the jar file name so it reads Provengo.uber.jar
2. Download Graphviz
3. Download Selenium Server (jar)
4. Download WebDrivers (We downloaded chrome). The vesion of the WebDriver must suit the version of your browser
5. unzip the zip files and put all the files in the same folder
6. open powershell in the same folder and write ./provengo to check if it works.


## What we tested
We tested the coupon modle and its functionalities. We chose to test the following user stories: 

*User story:* A customer uses a coupon that lowers the cost in the cart

*Preconditions:* There is a valid coupon, the user adds item to the cart first.

*Expected outcome:* The final price it lowerd by the coupon

-----------------------------------------------------------

*User story:* The admin cancels the coupon.

*Preconditions:* There is a valid coupon, the admin logs in

*Expected outcome:* The coupon no longer exists and cannot be used.


## How we tested
We used two different testing methods:
1. [Cucumber](https://cucumber.io/), a behavior-driven testing framework.
2. [Provengo](https://provengo.tech/), a story-based testing framework.

Each of the testing methods is elaborated in its own directory. 


## Detected Bugs
We did not detect any bugs in the OpenCarts system, the tests has passed.