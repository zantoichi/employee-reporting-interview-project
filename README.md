How to run:
./gradlew clean build bootRun

How to test:
./gradlew test

API documentation after starting the app on:
http://localhost:8080/swagger-ui.html

H2 is used as a db as an easy way to have an in-memory storage for a simple task. For running in
production though, any tried and tested relational db would make sense, I would consider Postgres
for the ability to persist also json documents if needed. Nonetheless, if read speeds are a
relevant business requirement, any nosql solution should be considered, even maybe using cqrs
with nosql for reads and sql for writes.

In terms of data modeling I considered Employee and Reports to be different aggregate roots, in ddd terms,
and avoided a direct oneToMany relationship between them, and any problems that come with it (like n+1).
I made it so that Employee just holds a reference to the ids of the reports.
This can be beneficial if a need to extract the reports to another module arises, and is consistent, I feel,
with the ddd mentality about aggregates, but it can become costly, and an overhead if there is no such need.
In this scenario, if the reports are closely tied/coupled with the employee, I would consider them reports
again, in ddd terms, an entity of the aggregate root and would model it as so (@Embeddable in jpa).