# Project Report #

### Index

- Abstract

- Introduction

- Solution

- Conclusion

- Technical Component


---

# Abstract

As the last assignment of LAPR3, we have developed a project in java to support the management of a ride-sharing business. The software allows the pharmacy's to delivery orders by scooter or drone. It is possible for the administrators to create, update and remove parks, vehicles,geographical points and paths taking into account the knowledge of physics. it is possible to check the available slots in a park and the vehicles that charging status of every scooter in a park.

## Keywords

- Ride-sharing
- Drone
- Scooter
- Park
- Geographical Point
- Pathway
- Order
- Courier
- Product
- Pharmacy


---

# Introduction

## Structure

In this report we will make a simple explanation of the project’s main goal, followed by the description of the presented problem and the developed solution. In the solution section there is a technical analysis for the most relevant cases, supported by the necessary documentation.


## State of the art

Sofware capable of performing similar tasks already exists in the market. Software like "Farmacia Entrega ( https://farmaciaentrega.pt/ ) offer similar features but in this case it is impossivel do delivery "SARS-CoV-2" vaccines to clients. However, the project that we developed can provide more specific and technical information to improve the user's experience and simplify the management of the business, while also allowing for more customizations (for example, in delivery methods) and scalability of the business.



## Project Goal

The goal of this project was to develop an efficient and reliable software to manage the operations of a ride-sharing company. The software quality and sustainability are guaranteed by following good OO practices and a TDD approach and also applying good knowledges about Physics, Database and Information Structures.

---

# Solution

## Resources

In order to offer a solution to the problem statement, we used the tools and resources learned in various curricular units of the semester:

- **ESINF** ( _Information Structures_ ): We learned how to efficiently use graphs and binary search trees in the programming language ``Java``.
- **BDDAD** ( _Databases_ ): We learned how to model data and develop a database as well as how to properly create, functions, procedures and triggers with ``SQL`` and ``PLSQL``, so that we can efficiently manipulate the data stored.
- **FSIAP** ( _Applied Physics_ ): We learned the behavior of electricity.
- **ARQCP** ( _Assembly code_ ): We learned programing in assembly and calling this function in C.
- **LAPR3** ( _Scrum Module_ ): We learned the best practices for the Scrum agile project development and how to use ``Jira``, a software used in project management.


The development of the project was divided into 3 sprints and a scrum master was appointed for the entire project, in order to better orient and organize the team's efforts. The tasks for each sprint were allocated in each sprint planning meeting and there were 10 minutes daily meetings everyday, to know what every team members did, what they will do and their blocks.

``Jira`` allowed us to segment issues into tasks and sub-tasks for each sprint and check various reports of the work that the team. Every User Stories created in Jira was all documented and divided into two parts, analysis and design, ``Visual Paradigm`` was used to design the diagramas of each functionality.The database and the required triggers, functions and procedures were created using ``SQLDeveloper``.

To be able to check for code analysis and mutation testing locally, we frequently used ``Jenkins`` and ``SonarQube`` that allows us to garantee the quality of the new code and make sure that the requirements were met.

The entire code and documentation was stored in a repository in ``Bitbucket`` that in conjunction with ``Git`` provided all the necessary version control tools to allow for simultaneous file creations and alterations.

In our application there are 4 types of actors. The (super) administrator, who creates new pharmacies, registers new products, adds new geographic points and add path. The (pharmacy) administrator, can add, remove or update a vehicle (can be a drone or scooter), also has the ability to update the stock, prepare orders, notify prepared orders, add couriers and parks and finally create a delivery. The courier can only know the orders and their location in order to start delivery. Finally, the client can add and remove from the shopping cart, and place the order, being able to use the credit system.

Our application has a menu (by console) that allows you to simulate the developed system, as well as all User Stories

## Project Documentation

* [Domain Model](domain_model/DomainModel.md)
* [Relational Model](relational_model/RelationalModel.md)
* [Use Cases](use_cases/UseCases.md)


---

[Back](../Readme.md)