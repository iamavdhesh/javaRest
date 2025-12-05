Sure Avdhesh — here is the full MD (Markdown) file you can directly copy into a .md file.
No extra formatting, clean and ready to use.


---

Coforge Java Interview Questions & Answers (Experienced Developer)

1. Core Java (OOP, Language Basics)

1. Difference between interface and abstract class

Answer:

Interface: All methods abstract (till Java 7), supports default/static methods, supports multiple inheritance.

Abstract class: Can have abstract + concrete methods, fields, constructors.

Use interfaces for capabilities; abstract classes for shared behavior with state.



---

2. Why is String immutable in Java?

Answer:

Enables string pool usage (memory optimization).

Important for security (class loaders, network operations).

Thread-safe by design.

Allows cached hashcode → faster lookups.



---

3. What is a functional interface?

Answer:
Interface with only one abstract method (SAM).
Examples: Runnable, Callable, Comparator.
Used in lambda expressions.


---

4. Autoboxing vs Unboxing

Answer:

Autoboxing: primitive → wrapper

Unboxing: wrapper → primitive
Be cautious: unboxing null → NullPointerException.



---

5. Difference between == and equals()

Answer:

== → compares references for objects

equals() → compares content (if overridden)



---

6. final vs finally vs finalize()

Answer:

final → constant, non-override methods, non-extendable classes

finally → block that always executes

finalize() → called by GC before removal (deprecated)



---

7. What is a marker interface?

Answer:
Interface with no methods.
Examples: Serializable, Cloneable.


---

8. Explain this and super

Answer:

this → current object

super → parent class object



---

9. Overloading vs Overriding

Answer:

Overloading → same method name, different params

Overriding → same signature, runtime polymorphism



---

10. How to create an immutable class

Answer:

Declare class final

Make fields private final

No setters

Use defensive copies



---

2. Collections & Java 8

11. ArrayList vs LinkedList

Answer:

ArrayList: fast random access, slow inserts

LinkedList: slow access, fast insert/remove



---

12. Fail-fast vs Fail-safe

Answer:

Fail-fast: throws ConcurrentModificationException

Fail-safe: works on copy → no exception



---

13. HashMap vs ConcurrentHashMap

Answer:

HashMap → not thread-safe

ConcurrentHashMap → thread-safe, segmented locking, no null keys/values



---

14. How HashMap works internally

Answer:

Key → hash → bucket index

Collision → linked list or tree

Retrieval through equals() check



---

15. map() vs flatMap()

Answer:

map() → one input to one output

flatMap() → one input to multiple outputs (flattened)



---

16. Find highest salary per department (Streams)

Map<String, Optional<Employee>> result =
employees.stream()
         .collect(Collectors.groupingBy(Employee::getDept,
         Collectors.maxBy(Comparator.comparing(Employee::getSalary))));


---

17. Optional in Java

Used to avoid null checks; has methods like map, filter, orElse.


---

18. Stream vs Collection

Collection stores data

Stream processes data; is lazy, functional, may be infinite



---

19. Short-circuiting operations

Examples: findFirst, anyMatch, allMatch, limit


---

20. When to use parallelStream()

Use only for CPU-heavy, stateless, large datasets.


---

3. Concurrency & JVM

21. synchronized vs Lock

synchronized: simple, auto-release

Lock: tryLock, timeouts, more flexible, must manually unlock



---

22. Thread lifecycle

NEW → RUNNABLE → BLOCKED / WAITING / TIMED_WAITING → TERMINATED


---

23. volatile keyword

Ensures visibility + ordering; not atomic.


---

24. Race condition

Occurs when multiple threads modify shared state unsafely.
Fix via synchronization, locks, atomics.


---

25. Java Memory Model

Defines rules for visibility, ordering using happens-before.


---

26. Garbage Collection overview

Identifies unreachable objects

Uses generations: Young, Old

Collectors: G1, ZGC, Shenandoah



---

27. Heap vs Stack

Stack → method frames, primitives

Heap → objects, shared between threads



---

4. Spring & Spring Boot

28. What is Dependency Injection?

Spring injects object dependencies.
Types: constructor (best), setter, field.


---

29. Component vs Service vs Repository vs Controller

@Component: generic bean

@Service: business layer

@Repository: DAO + exception translation

@RestController: returns JSON



---

30. @Controller vs @RestController

@RestController = @Controller + @ResponseBody


---

31. How Spring Boot auto-configuration works

Uses classpath checks, conditional beans, and properties.


---

32. Exception handling in REST APIs

Use:

@ControllerAdvice
@ExceptionHandler(...)

Return meaningful ResponseEntity.


---

33. @Transactional behavior

Opens transaction before method

Commits/rolls back based on exception

Supports propagation & isolation levels



---

34. Bean scopes

singleton

prototype

Web scopes: request, session, application



---

35. Securing REST API

Use Spring Security + JWT + filters + CORS config.


---

5. JPA / Hibernate

36. findById vs getReferenceById

findById → DB hit immediately

getReferenceById → lazy proxy



---

37. Lazy vs Eager loading

Lazy → fetch when required

Eager → fetch all immediately



---

38. @OneToMany / @ManyToOne

ManyToOne → owning side

OneToMany → mappedBy side



---

39. N+1 problem

Fix using:

JOIN FETCH

EntityGraph

Batch fetching



---

40. Transactions in microservices

Use Saga Pattern, Outbox, event-driven design.


---

6. Microservices & System Design

41. Monolith vs Microservices

Monolith → single deployable, tightly coupled

Microservices → independent services, scalable, complex infra



---

42. Communication types

Sync: REST, gRPC

Async: Kafka, RabbitMQ, events



---

43. Service Discovery

Use Eureka, Consul, Zookeeper, or Kubernetes service registry.


---

44. Fault tolerance patterns

Circuit Breaker, Retry, Timeout, Bulkhead, Fallback.


---

45. Design URL Shortener (High-Level)

Write path:

Generate short code (Base62/Hash)

Store in DB
Read path:

Lookup code → redirect
Use Redis caching, replication, sharding.



---

If you want, I can also generate:

✅ A PDF version
✅ A cheat-sheet one-pager
✅ A full mock interview based on these questions

Just tell me!