# Event Registration System API

## Overview
A Spring Boot REST API for creating events, registering users, viewing available seats, and cancelling registrations.

## Tech Stack
- Java
- Spring Boot
- Spring Data JPA
- H2 Database
- Maven

## Features
- Create events with unique event names
- Register users for events
- Prevent duplicate registration
- Prevent registration when event is full
- Cancel registration
- View events with available seats and total registrations
- Filter upcoming events

## API Endpoints
| Method | Endpoint | Description |
|---|---|---|
| POST | /api/events | Create event |
| GET | /api/events | View all events |
| GET | /api/events?upcomingOnly=true | View upcoming events |
| POST | /api/events/{eventId}/registrations | Register user |
| PATCH | /api/events/{eventId}/registrations/cancel | Cancel registration |

## Database
This project uses H2 file-based database. Data remains saved between application restarts.

## How to Run
1. Clone the repository
2. Open the project in IntelliJ IDEA
3. Run the main Spring Boot application class
4. Use Postman to test the APIs

## H2 Console
URL: /h2-console  
JDBC URL: jdbc:h2:file:./data/eventregistration_db
Username: sa  
Password: empty

## Race Condition Handling
Registration is handled inside a transactional service method. The event row is locked before checking available seats and saving a registration, preventing overbooking in concurrent requests.
