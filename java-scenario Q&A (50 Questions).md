# 📘 Java Tech Lead Interview – Scenario-Based Q&A (50 Questions)

A complete practice handbook for senior Java / Tech Lead roles.  
Covers **real-world scenarios**: coding, architecture, microservices, system design, leadership.



## 🔹 Core Java & Concurrency

### 1. Handling High Load in a Microservice
**Q:** Your Java microservice slows under high concurrent load. How do you identify bottlenecks and scale?  
**A:** Profile (JProfiler, VisualVM), optimize code (connection pooling, caching), async programming, horizontal scaling (K8s), Resilience4j circuit breakers.


### 2. Preventing Memory Leaks
**Q:** You see `OutOfMemoryError` in prod. How do you debug & prevent leaks?  
**A:** Heap dump analysis, weak references, clear listeners, avoid static big objects, tune GC.

---

### 3. Deadlock in Multithreading
**Q:** Service freezes due to deadlock. What’s your approach?  
**A:** Detect with `jstack`, enforce lock ordering, use `tryLock(timeout)`, prefer concurrent collections.

---

### 4. Thread Pool Misuse
**Q:** A junior dev uses `new Thread()` everywhere. How do you fix it?  
**A:** Use `ExecutorService`/`ForkJoinPool`. Size thread pools based on CPU cores and workload type (IO vs CPU bound).

---

### 5. Race Conditions
**Q:** Data inconsistency observed under load. How do you solve?  
**A:** Synchronize critical sections, use atomic classes (`AtomicInteger`), prefer immutability, transactional DB updates.

---

### 6. Blocking I/O Problem
**Q:** REST service slows when waiting on slow external APIs. Solution?  
**A:** Use async HTTP clients (WebClient, CompletableFuture), timeouts, bulkheads.

---

### 7. GC Pause Issues
**Q:** Latency spikes caused by GC. How do you tune it?  
**A:** Use G1GC/ZGC, tune heap sizes, avoid object churn, prefer primitive arrays over boxed objects.

---

### 8. Large Object Serialization
**Q:** Serialization is slow for large payloads. Fix?  
**A:** Use JSON-B/Jackson streaming API, Protobuf/Avro, compress payloads, avoid unnecessary fields.

---

### 9. Handling Huge Collections
**Q:** A list of 10M records is loaded into memory. Alternatives?  
**A:** Stream processing, pagination, database cursors, lazy evaluation.

---

### 10. Thread Safety in Caches
**Q:** Custom cache causing concurrency issues. What do you do?  
**A:** Use Caffeine/Guava cache, or `ConcurrentHashMap` with atomic compute methods.

---

## 🔹 Spring & Microservices

### 11. API Scalability
**Q:** Designing scalable REST API in Spring Boot?  
**A:** Stateless, rate limiting, async APIs, circuit breakers, distributed tracing.

---

### 12. Distributed Transactions
**Q:** Handle transactions across multiple services?  
**A:** Saga Pattern (choreography/orchestration), idempotency, compensating actions.

---

### 13. Service Discovery
**Q:** How to manage service-to-service calls in microservices?  
**A:** Use Eureka/Consul/Zookeeper, or Kubernetes DNS, client-side load balancing.

---

### 14. Retry Storms
**Q:** Retrying external API leads to overload. Fix?  
**A:** Exponential backoff with jitter, circuit breakers, bulkhead isolation.

---

### 15. Message Ordering
**Q:** Kafka consumers must process messages in order. Solution?  
**A:** Partition keying, single consumer per partition, ordered log compaction.

---

### 16. Event Duplication
**Q:** Kafka consumer sees duplicate events. How to handle?  
**A:** Idempotent processing, deduplication table, Kafka exactly-once semantics.

---

### 17. Config Management
**Q:** How to manage configs across 50 microservices?  
**A:** Spring Cloud Config, Vault, Kubernetes ConfigMaps/Secrets.

---

### 18. Service Startup Dependencies
**Q:** One microservice fails if another is down at startup. How to design better?  
**A:** Loose coupling, retries on startup, async initialization, health checks.

---

### 19. Circuit Breaker Usage
**Q:** How would you apply circuit breakers in Java?  
**A:** Resilience4j annotations or decorators, fallback strategies, monitoring trips.

---

### 20. Versioning APIs
**Q:** How do you handle breaking API changes?  
**A:** Versioned endpoints (`/v1/`, `/v2/`), backward compatibility, feature flags.

---

## 🔹 Database & Persistence

### 21. DB Bottleneck
**Q:** Java service times out due to DB slowness. What’s next?  
**A:** Optimize SQL, use indexes, batching, read replicas, caching layer.

---

### 22. N+1 Query Problem
**Q:** ORM fetching N+1 queries. Solution?  
**A:** Use `JOIN FETCH`, `EntityGraph`, batch fetching, second-level cache.

---

### 23. Connection Pool Exhaustion
**Q:** HikariCP shows “no available connections”. Fix?  
**A:** Tune pool size, fix unclosed connections, monitor with metrics.

---

### 24. DB Migration in Microservices
**Q:** How to safely update schema in prod?  
**A:** Use Flyway/Liquibase, backward-compatible migrations, deploy in phases.

---

### 25. Sharding & Partitioning
**Q:** DB struggling with scale. Options?  
**A:** Vertical partitioning, horizontal sharding, read-write splitting.

---

### 26. Caching Strategy
**Q:** How do you design caching for high read traffic?  
**A:** Use Redis/Caffeine, define TTL/eviction, cache invalidation strategy.

---

### 27. Data Consistency
**Q:** How to maintain consistency in distributed DB writes?  
**A:** Use eventual consistency, two-phase commit (rare), or Saga with compensations.

---

### 28. Large Batch Updates
**Q:** Batch updates locking the DB. How to optimize?  
**A:** Chunk updates, async processing, use partitioned jobs (Spring Batch).

---

### 29. Soft Deletes
**Q:** App needs “soft delete”. How to design?  
**A:** Add `is_deleted` flag, filter queries, archive old data.

---

### 30. Audit Logging
**Q:** How do you track DB changes?  
**A:** Use JPA entity listeners, Debezium (CDC), audit tables.

---

## 🔹 System Design & Architecture

### 31. Migrating Monolith to Microservices
**Q:** What’s your strategy?  
**A:** Identify bounded contexts, extract services gradually, split DB, event-driven comms.

---

### 32. Scaling File Uploads
**Q:** Millions of users uploading files. Design?  
**A:** Store in S3/GCS, async processing, pre-signed URLs, CDN delivery.

---

### 33. Rate Limiting
**Q:** How do you limit abusive clients?  
**A:** API Gateway (token bucket/leaky bucket), Redis counters, 429 responses.

---

### 34. Authentication
**Q:** How do you secure APIs?  
**A:** OAuth2.0, JWT, refresh tokens, API gateway auth layer.

---

### 35. Authorization
**Q:** Role-based vs Attribute-based access control?  
**A:** RBAC for roles, ABAC for fine-grained conditions.

---

### 36. High Availability
**Q:** How to design HA microservices?  
**A:** Multi-instance, load balancers, auto-healing (K8s), geo-redundancy.

---

### 37. Logging & Monitoring
**Q:** How do you ensure observability?  
**A:** Centralized logging (ELK), metrics (Prometheus), tracing (Jaeger).

---

### 38. Feature Rollouts
**Q:** How do you safely release new features?  
**A:** Feature flags, canary deployments, A/B testing.

---

### 39. Backward Compatibility
**Q:** A new service version must not break clients. How to ensure?  
**A:** Contract testing (Pact), versioned APIs, consumer-driven contracts.

---

### 40. Bulk Data Export
**Q:** How to design large data exports?  
**A:** Async job queues, chunked streaming, CSV/Parquet output.

---

## 🔹 Leadership & Team Management

### 41. Code Quality Issues
**Q:** Team pushes buggy code. Solution?  
**A:** Code reviews, CI tests, SonarQube, mentoring.

---

### 42. Handling Conflicts
**Q:** Two devs argue about design choice. What do you do?  
**A:** Facilitate discussion, data-driven decision, document trade-offs.

---

### 43. Mentoring Juniors
**Q:** How do you upskill junior devs?  
**A:** Pair programming, learning sessions, code walkthroughs.

---

### 44. Delivery Pressure
**Q:** Business wants faster delivery at cost of quality. How do you balance?  
**A:** Negotiate trade-offs, advocate for automation/testing, propose phased delivery.

---

### 45. Tech Debt
**Q:** How to manage tech debt in Java systems?  
**A:** Track in backlog, allocate sprint % for refactoring, prioritize critical debt.

---

### 46. Incident Management
**Q:** Prod outage at midnight. What’s your role?  
**A:** Coordinate triage, communicate with stakeholders, lead RCA, implement fixes.

---

### 47. Onboarding New Developers
**Q:** How do you onboard quickly?  
**A:** Documentation, buddy system, starter tasks, local dev setup scripts.

---

### 48. Distributed Team Collaboration
**Q:** Team across geographies. How do you ensure productivity?  
**A:** Async communication (Slack, Jira), clear ownership, overlapping hours.

---

### 49. Handling Underperformers
**Q:** A dev is consistently underperforming. What’s your approach?  
**A:** 1:1 feedback, mentoring, set goals, performance improvement plan.

---

### 50. Leading Architecture Decisions
**Q:** How do you make and document key decisions?  
**A:** Use ADRs (Architecture Decision Records), involve stakeholders, weigh trade-offs.

---

# ✅ Conclusion
This 50-scenario handbook covers:
- Core Java & Concurrency
- Spring & Microservices
- DB & Persistence
- System Design & Scalability
- Leadership & Team Management

Practice **storytelling answers** → *“In my last project, we solved this by …”*  
That makes your responses **credible and strong** in interviews.
