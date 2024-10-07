# A2

Project for Assignment 2

A project template based on gradle and a gitlab pipeline.

[design.md](design.md) contains the prescribed architectural design of the application.

## Building
The build has passed in visual studio code but not in git lab.  
`./gradlew build`  
CodeQualityTests > codeQuality() STANDARD_OUT
    0 CheckStyle Issues in controller/App.java
    0 CheckStyle Issues in controller/ContractController.java
    0 CheckStyle Issues in controller/ItemController.java
    0 CheckStyle Issues in controller/MainController.java
    0 CheckStyle Issues in controller/MemberController.java
    0 CheckStyle Issues in model/Contract.java
    0 CheckStyle Issues in model/ContractRepository.java
    0 CheckStyle Issues in model/CreditSystem.java
    0 CheckStyle Issues in model/Item.java
    0 CheckStyle Issues in model/ItemRepository.java
    0 CheckStyle Issues in model/Member.java
    0 CheckStyle Issues in model/MemberRepository.java
    0 CheckStyle Issues in model/ModelExceptions.java
    0 CheckStyle Issues in view/ContractView.java
    0 CheckStyle Issues in view/ItemView.java
    0 CheckStyle Issues in view/MainView.java
    0 CheckStyle Issues in view/MemberView.java
    0 FindBugs Issues in model/ModelExceptions.java
    0 FindBugs Issues in model/Contract.java
    0 FindBugs Issues in view/ContractView.java
    0 FindBugs Issues in model/ContractRepository.java
    0 FindBugs Issues in model/ItemRepository.java
    0 FindBugs Issues in controller/MainController.java
    0 FindBugs Issues in model/CreditSystem.java
    0 FindBugs Issues in model/MemberRepository.java
    0 FindBugs Issues in controller/MemberController.java
    0 FindBugs Issues in model/Member.java
    0 FindBugs Issues in view/ItemView.java
    0 FindBugs Issues in view/MainView.java
    0 FindBugs Issues in controller/App.java
    0 FindBugs Issues in controller/ContractController.java
    0 FindBugs Issues in model/Item.java
    0 FindBugs Issues in controller/ItemController.java
    0 FindBugs Issues in view/MemberView.java

## Running
The application starts by running console command:  
`./gradlew run -q --console=plain`

## Application Description

This application is a management system designed for handling members, items, and contracts. It features:

- **Member Management**: Allows users to create, delete, and view members. Each member has attributes such as ID, name, email, phone, and credits.
- **Item Management**: Facilitates the creation, deletion, and viewing of items. Each item includes attributes like ID, owner ID, name, and cost.
- **Contract Management**: Manages contracts associated with items and members, including the ability to create, delete, and view contracts with details such as ID, item ID, borrower ID, start date, and end date.

The application is built using Java and follows a Model-View-Controller (MVC) architecture. The view classes handle user interactions, while the model classes represent the core data and business logic. The application integrates various functionalities to manage and track resources efficiently.

## Overview
stuff lending machine is a console-based application designed to manage members, items, and contracts in a library or rental system. It provides functionalities for creating, viewing, and deleting members, items, and contracts, as well as managing credits, costs, and contract durations.

## Features

- **Manage Members**: Create, delete, and view members with unique IDs, valid phone numbers, and non-negative credits.
- **Manage Items**: Create, delete, and view items with unique IDs and non-negative costs.
- **Manage Contracts**: Create, delete, and view contracts with unique IDs, valid dates, and handle contract conflicts.
- **Advance Time**: Simulate the passage of time for contract management.

## Business Rules

1. **Members**:
   - Each member must have a unique ID.
   - Members are identified by an ID, name, email, phone number, and credits.
   - Phone numbers must be numeric and credits cannot be negative.

2. **Items**:
   - Each item must have a unique ID and an associated owner ID.
   - Items are identified by an ID, owner ID, name, and cost.
   - Costs must be non-negative.

3. **Contracts**:
   - Each contract must have a unique ID.
   - Contracts link items with members (borrowers).
   - Contracts have a start date and an end date.
   - End date cannot be before the start date.

## Known Issues

### Pipeline Failure

The pipeline on GitLab did not pass, but the build process succeeded locally in Visual Studio Code. This issue is currently unresolved due to restrictions against changing the build file or GitLab pipeline settings. 

If you encounter problems related to the pipeline, please contact support or refer to the [GitLab pipeline documentation](https://docs.gitlab.com/ee/ci/) for potential troubleshooting steps.

# Installation Instructions

Follow these steps to set up and run the Library Management System:

```bash
# 1. Clone the Repository
git clone https://gitlab.lnu.se/1dv607/student/eg223di/a2.git

# 2. Navigate to the Project Directory
cd a2

# 3. Build the Project Using Gradle
./gradlew build

# 4. Run the Application
./gradlew run
