Section 1: Architecture Summary

Write a short paragraph (3-5 sentences) summarizing the three-tier architecture in your own words. Mention the roles of the Presentation, Application, and Data layers, and technologies like Spring Boot, Thymeleaf, MySQL, and MongoDB.


Section 2: Numbered Flow

List the steps of the request/response cycle in a numbered format. Example:

User sends a request via HTML dashboard or REST API client.
Request is routed to an MVC or REST controller in the Application layer.
Controller processes logic and queries MySQL (via JPA) or MongoDB (via Spring Data MongoDB).
Database returns data to the controller.
Controller sends response (HTML view or JSON) to the frontend.
