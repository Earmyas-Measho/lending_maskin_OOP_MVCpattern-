# Boatclub OO-Design
This document describes the design according to the requirements presented in assignment 2.

## Architecture

The application is designed with a layered architecture, including:

1. **Model**: Contains core entities and business logic.
2. **View**: Manages user interactions and display.
3. **Controller**: Coordinates interactions (if applicable).

## Key Components

1. **Member**:
   - **Attributes**:
     - ID (String)
     - Name (String)
     - Email (String)
     - Phone (String)
     - Credits (int)
   - **Methods**:
     - Getters and Setters for attributes.

2. **Item**:
   - **Attributes**:
     - ID (String)
     - Owner ID (String)
     - Name (String)
     - Cost (int)
   - **Methods**:
     - Getters and Setters for attributes.

3. **Contract**:
   - **Attributes**:
     - ID (String)
     - Item ID (String)
     - Borrower ID (String)
     - Start Date (LocalDate)
     - End Date (LocalDate)
     - Active (boolean)
   - **Methods**:
     - Getters and Setters for attributes.

## Business Rules

1. **Members**:
   - Must have a unique ID.
   - Phone number must be digits-only.
   - Credits must be non-negative.

2. **Items**:
   - Must have a unique ID.
   - Costs must be non-negative.

3. **Contracts**:
   - Must have a unique ID.
   - End date must be after or the same as the start date.

## Diagrams

- **Sequence Diagram**: [View Sequence Diagram](https://gitlab.lnu.se/1dv607/student/eg223di/a2/-/blob/main/img/sequeence%20diagram.png?ref_type=heads)
- **Class Diagram**: [View Class Diagram](https://gitlab.lnu.se/1dv607/student/eg223di/a2/-/blob/main/img/uml_a2.drawio.png?ref_type=heads)

## Design Decisions

- The application uses a console interface for simplicity.
- Validation is performed within view classes to enforce business rules.

## Future Enhancements

- Implement a GUI for better user interaction.
- Add persistent storage to maintain data between application runs.
- Introduce a controller layer for improved separation of concerns.

## Contributor

- **Earmyas Measho** - [eg223di@student.lnu.se](mailto:eg223di@student.lnu.se)
