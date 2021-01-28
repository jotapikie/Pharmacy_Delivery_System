# Project Report #

### Index

- Abstract

- Introduction

- Solution

- Conclusion

- Technical Component


---

# Abstract

As the last assignment of LAPR3, we have developed a project in java to support the management of a ride-sharing business. The software allows the pharmacy's to delivery orders by scooter or drone. It is possible for the administrators to create, update and remove parks, vehicles, geographical points and paths taking into account the knowledge of physics. It is possible to check the available slots in a park and the status of every vehicle in a park. .

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

In this report we will make a simple explanation about what's the project’s main goal, followed by the description of the stated problem and the developed solution. In the solution section there is a technical analysis available regarding the most relevant cases, supported by the necessary documentation.


## State of the art

A sofware capable of performing similar tasks already exists in the market. For example the "Farmacia Entrega" software ( https://farmaciaentrega.pt/ ) offers similar features but in this case it is impossible to deliver "SARS-CoV-2" vaccines to clients. However, the project that we developed can provide more specific and technical information to improve the user's experience and simplify the management of the business, while also allowing for more customizations (for example, in delivery methods) and scalability of the business.



## Project Goal

The goal of this project was to develop an efficient and reliable software to manage the operations of a ride-sharing company. The software quality and sustainability are guaranteed by following good OO practices and a TDD approach and also applying good knowledges about Physics, Database and Information Structures.

---

# Solution

## Resources

In order to offer a optimal solution to the problem statement, we used the tools and resources learned and seen in various curricular units of the semester. The list below shows this mencioned units and the acquired knowledge:

- **ESINF** ( _Information Structures_ ): We learned how to efficiently use graphs and binary search trees in the programming language ``Java`` so that we have a more efficient access to the information stored in the system in a more geographical approach to the problem.
- **BDDAD** ( _Databases_ ): We learned how to model data and develop a database as well as how to properly create functions, procedures and triggers with ``SQL`` and ``PLSQL``, in order to efficiently manipulate the data stored.
- **FSIAP** ( _Applied Physics_ ): We learned the behavior of electricity, which was useful for the team when implementing formulas related to the calculation of energy in some processes.
- **ARQCP** ( _Assembly code_ ): We learned programing in assembly and calling this function in C for the purpose of simulating the parking slots' charging system.
- **LAPR3** ( _Scrum Module_ ): We learned the best practices for the Scrum agile project development and how to use ``Jira``, a software used in project management that was helpful, to keep track of the work's progresses, during the development of the project in several different forms.


The development of the project was divided into 3 sprints and a scrum master was early on nominated for the entire project, in order to better guide and organize the team's efforts. The tasks for each sprint were allocated in each sprint planning meeting and there were 10 minutes daily meetings everyday, to know what every team members did thoughtout the past hours, what they planned to do after and their blocks at the moment.

``Jira`` allowed us to segment issues into tasks and sub-tasks for each sprint and check various reports of the work that the team had made. Every User Stories created in Jira was all documented and divided into two parts, analysis and design, ``Visual Paradigm`` was used to design the diagramas of each functionality with the knowledge acquired on previous curricular units. The database tables and the required triggers, functions and procedures were created using ``SQLDeveloper``.

To be able to check for code analysis and mutation testing locally, we frequently used ``Jenkins`` and ``SonarQube`` that allows us to garantee the quality of the new code and make sure that all the requirements were fulfilled by testing different scenarios for each functionality existing in the project.

The entire code and documentation was stored in a repository in ``Bitbucket`` that in conjunction with ``Git`` provided all the necessary version control tools to allow simultaneous file creations and alterations between the team member's working platform.

In our application there are 4 types of actors. The (super) administrator, who creates new pharmacies, registers new products, adds new geographic points and add path. The (pharmacy) administrator, who can add, remove or update a vehicle (can be a drone or scooter). This actor also has the ability to update the stock, prepare orders, notify prepared orders, add couriers and parks and finally create a delivery. Another actor is the courier. This one can only know the orders and their location in order to start delivery. Finally, the client, that can add and remove from the shopping cart, and place the order, being able to use the credit system.

Our application has a menu (shown and controlled by console) that allows the user to simulate the developed system, as well as all User Stories.

## Project Documentation

* [Domain Model](domain_model/DomainModel.md)
* [Relational Model](relational_model/RelationalModel.md)
* [Use Cases](use_cases/UseCases.md)


---

[Back](../Readme.md)