# Graph Visualizer Desktop Application

A desktop application for visualizing graphs based on user input. It allows users to input graph data, filter vertices, and generate a diagram representation of the graph.

## Features

- **Graph Input**: Users can input graph data in the form of directed edges (e.g., `A -> B`).
- **Vertex Filter**: Vertices can be disabled, and the corresponding edges will not appear in the generated diagram.
- **Diagram Generation**: The graph data is processed, and a diagram is generated using PlantUML, an open-source tool for UML diagramming.
- **Graph Visualization**: The generated diagram is displayed as an image within the application.

## Requirements

- Kotlin 1.5 or higher
- Gradle
- Java 8 or higher

## Setup Instructions

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-username/graph-visualizer.git
   cd graph-visualizer
   ```

2. **Build the Project**:
   Use Gradle to build the project:
   ```bash
   ./gradlew build
   ```

3. **Run the Application**:
   To run the application, use:
   ```bash
   ./gradlew run
   ```

   This will launch the application and open a window where you can enter your graph data.

## How to Use

1. **Enter Graph Data**: In the main UI, you can input graph data in the format `A -> B`. Each line should represent an edge between two vertices.

2. **Disable Vertices**: You can disable specific vertices by unchecking the checkboxes next to them. Disabled vertices will not appear in the generated diagram.

3. **Generate Diagram**: The application will automatically generate and display a diagram based on the provided graph data, excluding any disabled vertices.

4. **View Diagram**: The diagram will be displayed as a PNG image within the application window.

## Code Structure

- `DiagramGenerator`: Responsible for generating the diagram based on the graph data and disabled vertices. It uses PlantUML to generate the PNG diagram.
- `GraphParser`: Parses the graph input and extracts vertices.
- `GraphViewModel`: Manages the application's state, including graph input, vertices, and diagram generation.
- `GraphUI`: The user interface implemented using Jetpack Compose. Displays the graph input, checkboxes for disabling vertices, and the generated diagram.

## Dependencies

- **Kotlin**: The main programming language used.
- **Jetpack Compose**: A modern UI toolkit for Kotlin that simplifies UI development.
- **PlantUML**: A tool to generate UML diagrams from text descriptions.
- **JUnit** Library used for unit testing