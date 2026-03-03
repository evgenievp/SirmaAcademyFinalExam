Sirma Final Exam

This is a Spring Boot application designed to solve an SQL query task about a pair of football players from EURO 2024 who played together in the same matches for the longest time.
The main SQL query is executed through a GET request. The application also includes CRUD operations for matches, players, teams, and records. 

Technologies used:
  Java 21
  MSSQL
  Spring Boot
  JPA

Backend setup:
  A working IntelliJ IDEA environment with Microsoft SQL Server installed.

Database configuration is public because these credentials are dummy (no need to hide them):
  database user: springUser
  database password: newPass123
  SQL Server port: 59161

Used Spring dependencies:
  SQL Server Driver
  Spring Security (basic security filter chain configured, CSRF disabled and all requests allowed)
  Validation
  Spring Web
  Lombok

API Endpoints
  POST:  
    /api/initAll - reads the CSV files and writes their data into the database
    /api/deleteAll - deletes all data from the database
    /api/players/add
    /api/players/delete/{id}
    /api/players/deleteAll
    /api/matches/add
    /api/matches/deleteAll
    /api/matches/saveAll
    /api/matches/delete/{id}
    /api/records/saveAll
    /api/records/deleteAll
    /api/teams/add
    /api/teams/saveAll
    /api/teams/deleteAll
    /api/teams/delete/{id}


  GET:
    /api/pair-played-most-time-info - returns the pair of players who played together for the longest time according to the implemented algorithm. With the provided CSV files the result is a table containing 7 matches with a total of 630 minutes played together by Declan Rice and Bukayo Saka, including match IDs and minutes played together.
    /api/just-pair - this endpoint return names of players with total minutes together.

Explanation of the approach used in the project:
    The provided CSV files are read manually without using external CSV libraries. This is done through the endpoint /api/initAll. Please note that CSVReaders works with relative paths. They are located in: src/main/resources/csv_files/...
    The parsed data is stored in lists of model objects (LinkedList is used due to the expected larger amount of data).
    These model lists are then persisted as SQL tables in the database (the same /api/initAll endpoint saves them into the database).
    REST controllers allow operations over the stored data such as adding, removing, or editing records.
    The main goal is to find the pair of players who have played together for the longest total time. This is calculated using a native SQL query executed through JPA.
    The query returns the pair of players, the matches in which they played together, and the calculated minutes played together.

Additional GET endpoints:
    /api/players/get/{id}
    /api/teams/get/{id}
    /api/teams/getAll
    /api/matches/getAll
    /api/matches/get/{id}
    /api/players/get
    /api/records/getAll

PATCH endpoints:
    /api/players/edit/{id}
    /api/teams/edit/{id}
    /api/matches/edit/{id}


Run the Application:
  Run SQL Server.
  Create a database that will be used by the application (or change the credentials in application.properties).
  Note: If you use a database different from MSSQL, some functionality may not work due to native SQL queries specifics.
  Open the project in IntelliJ IDEA.
  Build the project and run the Spring Boot application.
  Use Postman or another REST client to call the endpoints.
  If you want to use the existing CSV files:
  Call POST /api/initAll to load the data from the CSV files into the database.
  
  Call GET /api/pair-played-most-time-info to execute the main task.
  
  Example JSON bodies for CRUD endpoints:
  
  Note: When adding players, matches or teams the JSON must not contain an id field.

For players:

  {
    "teamNumber": 1,
    "position": "GK",
    "fullName": "Manuel Neuer",
    "teamId": 1
  }

For matches:

  {
    "ATeamId": 1,
    "BTeamId": 2,
    "date": "2024-06-14",
    "homeScore": 5,
    "awayScore": 1
  }

For teams:

  {
    "managerFullName": "Kasper Hjulmand",
    "name": "Denmark"
  }
