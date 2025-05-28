Learning Management System (LMS) - Software Evolution and Maintenance

This Java-based Learning Management System (LMS) was enhanced through a collaborative software maintenance project. The team implemented bug fixes and feature enhancements to improve functionality, conducted static code analysis with SonarQube, and documented the system architecture using UML diagrams via reverse engineering.

Project Overview

The LMS supports educational processes such as course management, user roles, and content delivery. This project focused on maintaining and evolving the system by:





Fixing bugs to improve stability and performance.



Adding or enhancing features based on team-defined requirements.



Analyzing code quality with SonarQube to ensure maintainability.



Documenting the system with UML diagrams (pre- and post-changes).

The project was completed in two phases, following a structured software maintenance process with Git-based collaboration and pull request reviews.

Features





Bug Fixes: Addressed issues to enhance system reliability (e.g., fixed login errors, course loading bugs).



Feature Enhancements: Added or improved functionality (e.g., improved user dashboard, added quiz submission).



Static Code Analysis: Used SonarQube to generate reports identifying code quality issues.



UML Documentation: Created UML diagrams via reverse engineering to visualize system architecture, updated post-implementation.



Change Management: Tracked changes via an online ticketing system (e.g., Jira, Bugzilla).

Tech Stack





Java: Backend logic (Java 17; update as needed).



SonarQube: Static code analysis for code quality and maintainability.



Maven: Dependency management (adjust if using Gradle).



UML Tool: [PlantUML, Enterprise Architect, or specify your tool] for reverse-engineered UML diagrams.



Optional: [Spring Boot for APIs, Hibernate for database, if used].

Project Phases

Phase 1 (Completed by 8/5/2024)





Initialized Git repository with team contributors.



Documented LMS requirements and proposed changes (bug fixes and features).



Ran SonarQube for initial code quality analysis, generating reports to identify issues.



Registered change requests in [ticketing system, e.g., Jira, Bugzilla] and tracked progress via updates.

Phase 2 (Completed by 18/5/2024)





Generated UML diagrams via reverse engineering to document system architecture (e.g., class diagrams for subsystems like user management).



Implemented and validated changes (bug fixes and feature enhancements).



Re-ran SonarQube for impact analysis, generating updated code quality reports.



Validated changes through unit tests, integration tests, and manual testing to ensure functionality.



Updated UML diagrams to reflect post-implementation changes, stored in docs/uml/.

Validation and Verification





Unit Tests: Tested individual components (e.g., user authentication, course retrieval) to ensure bug fixes worked.



Integration Tests: Verified system-wide functionality after feature enhancements.



Manual Testing: Confirmed user-facing features (e.g., dashboard, quiz submission) via UI or API testing.



SonarQube Reports: Compared pre- and post-change reports to validate improved code quality (e.g., reduced code smells, improved coverage).



UML Updates: Ensured diagrams accurately reflected the updated system architecture.
