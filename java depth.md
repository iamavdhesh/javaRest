Great! You chose **Q + detailed A**. I'll use **Markdown** format (easy to convert to PDF).

Let me start generating **Module 1: Core Java (75 Q&A)** — the foundation that everything else builds on.

---

# 📘 JAVA INTERVIEW HANDBOOK
## For Senior / Tech Lead Roles (40–45 LPA)
### Total: 700+ Q&A

---

# MODULE 1: CORE JAVA (75 Q&A)

---

## 1.1 JVM Architecture

### Q1: Explain the JVM Architecture in detail.

**Answer:**

The JVM (Java Virtual Machine) is the runtime engine that executes Java bytecode. It has the following major components:

```
┌─────────────────────────────────────────────┐
│              Java Source Code (.java)        │
└──────────────────┬──────────────────────────┘
                   │ javac (Compiler)
                   ▼
┌─────────────────────────────────────────────┐
│           Bytecode (.class files)           │
└──────────────────┬──────────────────────────┘
                   │
                   ▼
┌─────────────────────────────────────────────┐
│                CLASS LOADER                 │
│  ┌─────────────┐ ┌──────────┐ ┌──────────┐ │
│  │  Bootstrap  │ │ Extension│ │Application│ │
│  │  ClassLoader│ │ ClassLdr │ │ ClassLdr  │ │
│  └─────────────┘ └──────────┘ └──────────┘ │
└──────────────────┬──────────────────────────┘
                   │
                   ▼
┌─────────────────────────────────────────────┐
│            JVM MEMORY (Runtime Data Areas)  │
│                                             │
│  ┌─────────────────────────────────────┐    │
│  │         METHOD AREA (Metaspace)     │    │
│  │  (Class metadata, static vars,      │    │
│  │   constant pool)                    │    │
│  └─────────────────────────────────────┘    │
│                                             │
│  ┌──────────┐  ┌──────────────────────┐    │
│  │   HEAP   │  │    THREAD AREAS      │    │
│  │          │  │  ┌─────────────────┐ │    │
│  │  Young   │  │  │  Stack (per     │ │    │
│  │  Gen     │  │  │  thread)        │ │    │
│  │  ┌─────┐ │  │  │  ┌───────────┐  │ │    │
│  │  │ Eden│ │  │  │  │ StackFrame│  │ │    │
│  │  │S0   │ │  │  │  │ -Local    │  │ │    │
│  │  │S1   │ │  │  │  │  Variables│  │ │    │
│  │  └─────┘ │  │  │  │ -Operand  │  │ │    │
│  │  Old Gen │  │  │  │  Stack    │  │ │    │
│  │          │  │  │  │ -PC Reg   │  │ │    │
│  └──────────┘  │  │  └───────────┘  │ │    │
│                │  └─────────────────┘ │    │
│                │  ┌─────────────────┐ │    │
│                │  │  Native Method  │ │    │
│                │  │  Stack          │ │    │
│                │  └─────────────────┘ │    │
│                │  ┌─────────────────┐ │    │
│                │  │  PC Register    │ │    │
│                │  └─────────────────┘ │    │
│                └──────────────────────┘    │
└─────────────────────────────────────────────┘
                   │
                   ▼
┌─────────────────────────────────────────────┐
│          EXECUTION ENGINE                   │
│  ┌──────────┐ ┌──────────┐ ┌────────────┐  │
│  │Interpreter│ │   JIT    │ │  Garbage   │  │
│  │          │ │ Compiler │ │ Collector  │  │
│  └──────────┘ └──────────┘ └────────────┘  │
└─────────────────────────────────────────────┘
                   │
                   ▼
┌─────────────────────────────────────────────┐
│     NATIVE METHOD INTERFACE (JNI)           │
│     (Interface to C/C++ libraries)          │
└─────────────────────────────────────────────┘
```

**Key Components Explained:**

| Component | Role |
|-----------|------|
| **Class Loader** | Loads .class files into memory |
| **Method Area (Metaspace)** | Stores class metadata, static variables, constant pool |
| **Heap** | Stores all objects and instance variables (shared across threads) |
| **Stack** | Per-thread; stores stack frames (local variables, operand stack, PC) |
| **PC Register** | Holds address of current JVM instruction per thread |
| **Execution Engine** | Interprets bytecode + JIT compilation for hot code |
| **JNI** | Bridge to native (C/C++) code |

---

### Q2: What is the difference between JIT Compiler and Interpreter?

**Answer:**

| Aspect | Interpreter | JIT Compiler |
|--------|-------------|--------------|
| **How it works** | Reads bytecode line by line and executes | Compiles entire hot methods to native machine code |
| **Speed** | Slow for repeated execution | Very fast after compilation |
| **Startup** | Fast (no compilation overhead) | Slower (compilation takes time) |
| **Memory** | Low | Higher (stores compiled native code in CodeCache) |
| **Use case** | First few executions of code | Frequently called ("hot") methods |

**How JIT decides what to compile:**
- JVM tracks method invocation counts
- When a method exceeds the **compilation threshold** (~10,000 invocations for server mode), it is flagged as "hot"
- The JIT compiler then compiles it to native code
- The compiled code is stored in the **CodeCache**

```java
// This loop will trigger JIT compilation
// because the method is called thousands of times
for (int i = 0; i < 100_000; i++) {
    processTransaction(i);  // After ~10K calls, JIT kicks in
}
```

**Interview Tip:** Mention that JIT also performs optimizations like method inlining, loop unrolling, dead code elimination, and escape analysis.

---

### Q3: What are the different JVM Memory Areas? Explain each.

**Answer:**

```
JVM Memory
├── Heap (Shared across all threads)
│   ├── Young Generation
│   │   ├── Eden Space (new objects allocated here)
│   │   ├── Survivor Space S0 (From Space)
│   │   └── Survivor Space S1 (To Space)
│   └── Old Generation (Tenured Space)
│       (objects that survived multiple GC cycles)
│
├── Non-Heap (Metaspace since Java 8)
│   ├── Class Metadata
│   ├── Static Variables
│   └── Constant Pool
│
└── Thread-Specific (per thread)
    ├── Java Stack
    │   └── Stack Frames
    │       ├── Local Variable Array
    │       ├── Operand Stack
    │       └── Frame Data (return value, exception table)
    ├── Native Method Stack (for JNI calls)
    └── PC Register (Program Counter)
```

| Area | Shared? | Stores | OutOfMemoryError? |
|------|---------|--------|--------------------|
| **Eden** | Yes | New objects | Yes (`Java heap space`) |
| **Survivor** | Yes | Objects surviving minor GC | Yes |
| **Old Gen** | Yes | Long-lived objects | Yes |
| **Metaspace** | Yes | Class metadata (since Java 8) | Yes (`Metaspace`) |
| **Java Stack** | No | Method calls, local variables | Yes (`StackOverflowError`) |
| **PC Register** | No | Current instruction address | No |
| **Native Stack** | No | Native method calls | Yes |

**Interview Tip:** Since Java 8, PermGen was replaced by **Metaspace** which lives in native memory (not heap) and grows dynamically by default.

---

## 1.2 Heap vs Stack

### Q4: What is the difference between Heap and Stack memory?

**Answer:**

```java
public class HeapVsStackDemo {
    
    // This reference variable lives in Stack
    // The actual object lives in Heap
    public void process() {
        int count = 10;                    // Stack (primitive)
        String name = "John";              // Stack (ref) -> Heap (String object)
        Order order = new Order();         // Stack (ref) -> Heap (Order object)
        calculate(count);                  // New stack frame created
    }
    
    private int calculate(int value) {
        int result = value * 2;            // Stack
        return result;                     // Stack frame destroyed on return
    }
}
```

| Aspect | Stack | Heap |
|--------|-------|------|
| **Stores** | Primitives, object references, method frames | Objects, instance variables |
| **Lifetime** | Method scope (auto-destroyed) | Until GC collects |
| **Size** | Small (default 512KB–1MB) | Large (default ~256MB+) |
| **Thread Safety** | Thread-safe (per-thread) | Shared, needs synchronization |
| **Access Speed** | Very fast (LIFO) | Slower |
| **Error** | `StackOverflowError` | `OutOfMemoryError` |
| **Growth** | Contiguous, LIFO | Dynamic allocation |

**Real-World Example:**

```java
public class PaymentService {
    
    public void processPayment() {
        // Stack: reference "request"
        // Heap: PaymentRequest object
        PaymentRequest request = new PaymentRequest();
        request.setAmount(1000);
        
        // Stack: reference "response"  
        // Heap: PaymentResponse object
        PaymentResponse response = validate(request);
        
        // After method returns:
        // - Stack frame is destroyed
        // - Objects in Heap remain until GC
    }
}
```

**Interview Tip:** Always mention that Stack memory is automatically managed (frame push/pop), while Heap memory requires Garbage Collection.

---

### Q5: Can a Stack have memory leak?

**Answer:**

Technically **no** — Stack memory is automatically reclaimed when the method returns. However:

1. **StackOverflowError** can occur with infinite recursion:
```java
// This will cause StackOverflowError, not memory leak
public void recursive() {
    recursive();  // Each call adds a frame to stack
}
```

2. **Memory leaks happen on Heap** — the Stack itself doesn't leak, but Stack-held references can prevent Heap objects from being GC'd:
```java
public class CacheService {
    private static final List<Object> cache = new ArrayList<>();
    
    public void addToCache(Object item) {
        cache.add(item);  // Heap memory grows, never freed
    }
    // This is a HEAP memory leak caused by a static reference
}
```

---

## 1.3 ClassLoader

### Q6: Explain the ClassLoader hierarchy in Java.

**Answer:**

```
┌─────────────────────────────┐
│  Bootstrap ClassLoader       │  ← Written in C/C++
│  (rt.jar / java.base module) │  ← Loads core Java classes
│  e.g., java.lang.String,    │     (String, Object, etc.)
│  java.util.HashMap           │
└──────────────┬──────────────┘
               │ Delegation (parent first)
               ▼
┌─────────────────────────────┐
│  Extension/Platform          │  ← Written in Java
│  ClassLoader                │  ← Loads extension classes
│  (jre/lib/ext or modules)   │     (javax.*, crypto, etc.)
└──────────────┬──────────────┘
               │ Delegation (parent first)
               ▼
┌─────────────────────────────┐
│  Application ClassLoader     │  ← Written in Java
│  (System ClassLoader)       │  ← Loads application classes
│  (classpath)                │     (your code + dependencies)
└──────────────┬──────────────┘
               │
               ▼
┌─────────────────────────────┐
│  Custom ClassLoaders        │  ← Your own (e.g., for plugins,
│  (Tomcat, OSGi, etc.)       │     hot-reload, modular apps)
└─────────────────────────────┘
```

### Q7: What is the Parent Delegation Model?

**Answer:**

When a class needs to be loaded:

```
1. ClassLoader receives request
2. Delegates to PARENT first
3. Parent delegates to its parent (all the way to Bootstrap)
4. If Bootstrap can load → loaded by Bootstrap
5. If Bootstrap can't → parent tries
6. If parent can't → original ClassLoader tries
7. If no one can → ClassNotFoundException
```

```java
public class ClassLoaderDemo {
    public static void main(String[] args) {
        // Bootstrap loads java.lang.String
        System.out.println(String.class.getClassLoader());
        // Output: null (Bootstrap is native, returns null)
        
        // Application ClassLoader loads our class
        System.out.println(ClassLoaderDemo.class.getClassLoader());
        // Output: sun.misc.Launcher$AppClassLoader@xxx
        
        // Parent of AppClassLoader
        System.out.println(ClassLoaderDemo.class.getClassLoader().getParent());
        // Output: sun.misc.Launcher$ExtClassLoader@xxx
    }
}
```

**Why Parent Delegation?**
1. **Security** — prevents loading malicious `java.lang.String`
2. **Consistency** — core classes loaded only once
3. **Avoids class collisions** — same class loaded by different loaders

### Q8: When would you write a Custom ClassLoader?

**Answer:**

| Use Case | Example |
|----------|---------|
| **Hot-reload** | Reload modified classes without restarting JVM |
| **Plugin systems** | Load plugins from different directories |
| **Application servers** | Tomcat isolates web apps using separate classloaders |
| **Encryption** | Load encrypted class files |
| **Module systems** | OSGi, JBoss Modules |

```java
public class CustomClassLoader extends ClassLoader {
    
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = loadClassData(name); // read from custom location
        if (classData == null) {
            throw new ClassNotFoundException(name);
        }
        return defineClass(name, classData, 0, classData.length);
    }
    
    private byte[] loadClassData(String className) {
        // Load from database, network, encrypted file, etc.
        String path = "/plugins/" + className.replace('.', '/') + ".class";
        // ... read bytes from path
        return bytes;
    }
}
```

---

## 1.4 HashMap Internals

### Q9: How does HashMap work internally in Java 8+?

**Answer:**

```
HashMap (default: capacity=16, loadFactor=0.75)
┌──────────────────────────────────────────────┐
│  Index:  0    1    2    3    4   ...   15    │
│          │    │    │    │    │         │      │
│         null [A] null [B] null      [C]     │
│               │         │              │     │
│              [D]→null  [E]→[F]→null  [G]→null│
│         (linked)  (linked)        (linked)   │
└──────────────────────────────────────────────┘

Key → hashCode() → index calculation → bucket → 
  if no collision → store as Node
  if collision → 
    if TREEIFY_THRESHOLD (8) reached → convert to Red-Black Tree
```

**Step-by-step put() operation:**

```java
// 1. Get hashCode
int hash = key.hashCode();

// 2. Apply hash扰动 (reduce collisions)
// XOR upper 16 bits with lower 16 bits
hash = hash ^ (hash >>> 16);

// 3. Calculate bucket index
int index = hash & (capacity - 1);  // Same as hash % capacity (when capacity is power of 2)

// 4. Place in bucket
// If bucket is empty → create new Node
// If key exists (equals() match) → update value
// If collision → add to linked list / tree
```

**Key Internal Details:**

| Aspect | Value |
|--------|-------|
| **Default Capacity** | 16 |
| **Load Factor** | 0.75 |
| **Resize Threshold** | capacity × loadFactor = 12 |
| **Resize Multiplier** | 2× (16 → 32 → 64 → ...) |
| **Treeify Threshold** | 8 (linked list → red-black tree) |
| **Untreeify Threshold** | 6 (tree → linked list) |
| **Minimum Treeify Capacity** | 64 |

### Q10: Why is HashMap capacity always a power of 2?

**Answer:**

```java
// Because index calculation uses bitwise AND instead of modulo
int index = hash & (capacity - 1);

// When capacity = 16 (10000 in binary):
// capacity - 1 = 15 (01111 in binary)
// hash & 01111 → gives index 0-15 (perfect distribution)

// If capacity were NOT power of 2 (e.g., 10):
// capacity - 1 = 9 (1001 in binary)
// hash & 1001 → only uses 2 bits → poor distribution!
```

### Q11: What happens during HashMap resize (rehashing)?

**Answer:**

```
Before Resize (capacity=4, 3 elements):
Bucket 0: [A]
Bucket 1: [B] → [C]
Bucket 2: null
Bucket 3: null

After Resize (capacity=8):
Bucket 0: [A]     (index 0 → 0 OR 4, depends on hash bit)
Bucket 1: null
Bucket 2: null
Bucket 3: null
Bucket 4: [B]     (re-indexed based on new bit)
Bucket 5: [C]     (re-indexed based on new bit)
Bucket 6: null
Bucket 7: null
```

**Java 8 Optimization:**
In Java 7, ALL nodes were re-indexed. In Java 8:
```java
// Only checks one additional bit
// If new bit == 0 → stays at same index
// If new bit == 1 → moves to index + oldCapacity
newIndex = oldIndex + oldCapacity  // or oldIndex (no rehash needed)
```

**Interview Tip:** HashMap is **NOT thread-safe**. Concurrent modification can cause infinite loops (Java 7) or data loss. Use `ConcurrentHashMap` for thread safety.

---

### Q12: Why String and Integer are good HashMap keys?

**Answer:**

1. **Immutable** — hashCode is cached and never changes
2. **Well-implemented hashCode()** — good distribution
3. **Proper equals()** — consistent comparison

```java
// String hashCode is cached
public int hashCode() {
    int h = hash;  // cached
    if (h == 0 && value.length > 0) {
        for (char c : value) {
            h = 31 * h + c;  // s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]
        }
        hash = h;
    }
    return h;
}
```

**Bad key example:**
```java
// Mutable key — DANGEROUS!
class MutableKey {
    int value;
    // After putting in HashMap, if you change value,
    // hashCode changes, and you can't find the entry!
}
```

---

## 1.5 ConcurrentHashMap

### Q13: How does ConcurrentHashMap work in Java 8+?

**Answer:**

```
Java 7: Segment-based locking (16 segments)
┌────────────────────────────────────┐
│  Segment 0  │  Segment 1  │  ...  │
│  (Lock 0)   │  (Lock 1)   │       │
│  HashEntry[]│  HashEntry[]│       │
└────────────────────────────────────┘
Concurrency = number of segments (default 16)

Java 8: CAS + Synchronized on individual bins
┌────────────────────────────────────────────┐
│  Node[] table (no segments)                │
│  ┌────┬────┬────┬────┬────┬────┬────┬────┐│
│  │ B0 │ B1 │ B2 │ B3 │ B4 │ B5 │ B6 │ B7 ││
│  └──┬─┴────┴──┬─┴────┴──┬─┴────┴────┴────┘│
│     │         │         │                  │
│   Node→Node  Node     TreeNode (if ≥8)    │
│   (linked)   (synchronized on bin)         │
└────────────────────────────────────────────┘
```

**put() operation in Java 8:**
```java
final V putVal(K key, V value) {
    int hash = spread(key.hashCode());
    
    for (Node<K,V>[] tab = table;;) {
        Node<K,V> f; int n, i, fh;
        
        if (tab == null || (n = tab.length) == 0)
            tab = initTable();  // CAS to initialize
        
        else if ((f = tabAt(tab, i = (n - 1) & hash)) == null) {
            // EMPTY BUCKET — use CAS (no lock needed)
            if (casTabAt(tab, i, null, new Node<>(hash, key, value)))
                break;
        }
        else {
            // COLLISION — synchronize on the first node of the bin
            synchronized (f) {
                // Add to linked list or tree
            }
        }
    }
}
```

### Q14: Difference between HashMap, Hashtable, and ConcurrentHashMap?

**Answer:**

| Aspect | HashMap | Hashtable | ConcurrentHashMap |
|--------|---------|-----------|-------------------|
| **Thread Safe** | ❌ No | ✅ Yes (full lock) | ✅ Yes (fine-grained) |
| **Null Keys** | 1 null key | ❌ No nulls | ❌ No nulls |
| **Null Values** | Multiple | ❌ No | ❌ No |
| **Performance** | Fastest (single-thread) | Slowest (full lock) | Fast (concurrent) |
| **Locking** | None | Entire map (synchronized) | CAS + bin-level sync |
| **Iterator** | Fail-fast | Fail-fast | Weakly consistent |
| **Introduced** | Java 1.2 | Java 1.0 | Java 1.5 |
| **Legacy** | No | Yes (don't use) | Modern standard |

**Interview Tip:** Never use Hashtable. It locks the entire map. ConcurrentHashMap only locks the specific bucket (bin), allowing concurrent reads and high-throughput writes.

---

### Q15: Can ConcurrentHashMap have null keys or values?

**Answer:**

**No.** Unlike HashMap, ConcurrentHashMap explicitly disallows null keys and values.

```java
// ConcurrentHashMap.put() source code
public V put(K key, V value) {
    if (value == null) throw new NullPointerException();
    // ...
}
```

**Why?** In concurrent scenarios, if `get()` returns null, you can't tell if:
- The key doesn't exist, OR
- The value is actually null

This ambiguity is dangerous in concurrent code where you can't do a follow-up `containsKey()` atomically.

---

## 1.6 equals() and hashCode()

### Q16: What is the contract between equals() and hashCode()?

**Answer:**

```
The Contract:
┌─────────────────────────────────────────────────┐
│ 1. If a.equals(b) == true                       │
│    THEN a.hashCode() == b.hashCode() MUST be true│
│                                                  │
│ 2. If a.hashCode() == b.hashCode()               │
│    THEN a.equals(b) may be true or false (OK)    │
│                                                  │
│ 3. If a.equals(b) == false                       │
│    THEN a.hashCode() can be same or different    │
│    (but different is better for performance)     │
└─────────────────────────────────────────────────┘
```

**What breaks if you violate the contract:**

```java
class Employee {
    String id;
    String name;
    
    @Override
    public boolean equals(Object o) {
        Employee e = (Employee) o;
        return this.id.equals(e.id);  // Compares by ID
    }
    
    // FORGOT to override hashCode()!
    // Default hashCode() = memory address-based
    // Two equal employees will have DIFFERENT hashCodes
}

// This will FAIL:
Map<Employee, String> map = new HashMap<>();
Employee e1 = new Employee("101", "John");
Employee e2 = new Employee("101", "John");

map.put(e1, "Manager");
map.get(e2);  // Returns NULL! (because hashCode differs → different bucket)
// e1.equals(e2) is true, but they're in different buckets!
```

### Q17: How to properly implement equals() and hashCode()?

**Answer:**

```java
class Employee {
    private String id;
    private String name;
    private String department;
    
    @Override
    public boolean equals(Object o) {
        // 1. Reflexive check
        if (this == o) return true;
        
        // 2. Null and type check
        if (o == null || getClass() != o.getClass()) return false;
        
        // 3. Cast and compare fields
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id);
    }
    
    @Override
    public int hashCode() {
        // Use the SAME fields as equals()
        return Objects.hash(id);
    }
}
```

**Interview Tip:** IDEs generate these. But understanding WHY is what matters in interviews.

---

## 1.7 String Pool

### Q18: What is String Pool and how does it work?

**Answer:**

```
┌─────────────────────────────────────────────────┐
│                    HEAP                          │
│                                                  │
│  ┌──────────────────────────────────────────┐   │
│  │        STRING POOL (String Table)        │   │
│  │  (Special area in Heap, managed by JVM)  │   │
│  │                                          │   │
│  │  "Hello" → [char array: H,e,l,l,o]      │   │
│  │  "World" → [char array: W,o,r,l,d]      │   │
│  │  "Java"  → [char array: J,a,v,a]        │   │
│  │                                          │   │
│  └──────────────────────────────────────────┘   │
│                                                  │
│  ┌──────────────┐    ┌──────────────┐           │
│  │ String s3 =  │    │ String s4 =  │           │
│  │ new String() │    │ new String() │           │
│  │ "Hello"      │    │ "Hello"      │           │
│  └──────┬───────┘    └──────┬───────┘           │
│         │                   │                    │
│         └───── both point to ──────┘             │
│         same char[] in String Pool               │
└─────────────────────────────────────────────────┘
```

```java
String s1 = "Hello";                    // Created in String Pool
String s2 = "Hello";                    // Reuses from Pool
String s3 = new String("Hello");        // New object in Heap, but shares char[] from Pool

s1 == s2;  // TRUE  (same reference in pool)
s1 == s3;  // FALSE (s3 is a new object on heap)
s1.equals(s3);  // TRUE (same content)

// Force into pool
String s4 = s3.intern();  // Returns reference from pool
s1 == s4;  // TRUE
```

### Q19: Why is String immutable in Java?

**Answer:**

| Reason | Explanation |
|--------|-------------|
| **String Pool** | Multiple variables can share same String object. If mutable, changing one would affect all. |
| **Security** | Strings used in class loading, network connections, file paths. Mutable strings = security holes. |
| **Thread Safety** | Immutable objects are inherently thread-safe. No synchronization needed. |
| **HashCode caching** | `hash` is computed once and cached. Safe because content never changes. |
| **Performance** | JVM can optimize (interning, substring sharing). |

```java
// Internal structure (Java 9+)
public final class String {
    private final byte[] value;   // final array
    private final byte coder;     // LATIN1 or UTF16
    private int hash;             // cached hashCode
    
    // No setter methods
    // Class is final — can't be subclassed
}
```

---

## 1.8 Serialization

### Q20: Explain Serialization and Deserialization in Java.

**Answer:**

```
Serialization: Object → Byte Stream (for storage/network)
Deserialization: Byte Stream → Object

┌──────────┐  serialize()  ┌─────────────┐  deserialize()  ┌──────────┐
│  Object  │ ────────────→ │ Byte Stream │ ──────────────→ │  Object  │
│  (Heap)  │               │ (File/Net)  │                 │  (Heap)  │
└──────────┘               └─────────────┘                 └──────────┘
```

```java
// Step 1: Implement Serializable
public class Payment implements Serializable {
    private static final long serialVersionUID = 1L;  // Version control
    
    private String id;
    private double amount;
    private transient String securityToken;  // NOT serialized
    private static String bankName;          // NOT serialized (static)
    
    // Getters/Setters
}

// Step 2: Serialize
Payment payment = new Payment("PAY001", 1000.0, "secret");
try (ObjectOutputStream oos = new ObjectOutputStream(
        new FileOutputStream("payment.ser"))) {
    oos.writeObject(payment);
}

// Step 3: Deserialize
try (ObjectInputStream ois = new ObjectInputStream(
        new FileInputStream("payment.ser"))) {
    Payment p = (Payment) ois.readObject();
    System.out.println(p.getId());      // PAY001
    System.out.println(p.getSecurityToken()); // null (transient)
}
```

### Q21: What is serialVersionUID and why is it important?

**Answer:**

```
serialVersionUID = Version control for serialization

Scenario:
1. V1: Employee { name, age }     → serialVersionUID = 1L
   Serialize 1000 employees to file

2. V2: Employee { name, age, email }  → serialVersionUID = 2L (auto-generated)
   Try to deserialize V1 file → InvalidClassException!

3. V2: Employee { name, age, email }  → serialVersionUID = 1L (explicitly set)
   Deserialize V1 file → WORKS! (email will be null)
```

**Best Practice:**
```java
// ALWAYS declare explicitly
private static final long serialVersionUID = 1L;

// If you change the class significantly, bump it
private static final long serialVersionUID = 2L;
```

### Q22: What is the difference between Serializable and Externalizable?

**Answer:**

| Aspect | Serializable | Externalizable |
|--------|-------------|----------------|
| **Control** | JVM handles (automatic) | You control (custom) |
| **Performance** | Slower (uses reflection) | Faster (custom logic) |
| **Methods** | No methods to implement | Must implement `writeExternal()` and `readExternal()` |
| **Default constructor** | Not required | Required (for deserialization) |
| **transient** | Use `transient` to skip fields | You decide what to serialize |

```java
public class Payment implements Externalizable {
    
    public Payment() {}  // REQUIRED for Externalizable
    
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(id);
        out.writeDouble(amount);
        // Custom format — more compact
    }
    
    @Override
    public void readExternal(ObjectInput in) throws IOException {
        this.id = in.readUTF();
        this.amount = in.readDouble();
    }
}
```

---

## 1.9 Reflection

### Q23: What is Reflection in Java and when would you use it?

**Answer:**

Reflection allows you to inspect and manipulate classes, methods, fields at runtime — even private ones.

```java
// 1. Get Class object
Class<?> clazz = Class.forName("com.example.Payment");

// 2. Create instance
Object obj = clazz.getDeclaredConstructor().newInstance();

// 3. Access private field
Field amountField = clazz.getDeclaredField("amount");
amountField.setAccessible(true);  // Bypass private access
amountField.setDouble(obj, 1000.0);

// 4. Invoke private method
Method processMethod = clazz.getDeclaredMethod("process");
processMethod.setAccessible(true);
processMethod.invoke(obj);

// 5. Get all annotations
Annotation[] annotations = clazz.getAnnotations();
```

**Where Reflection is Used:**

| Framework | How it uses Reflection |
|-----------|----------------------|
| **Spring** | `@Autowired` — finds and injects beans via reflection |
| **JUnit** | `@Test` — finds and invokes test methods |
| **Jackson** | Reads object fields for JSON serialization |
| **Hibernate** | Maps object fields to database columns |
| **Servlet** | Maps URLs to controller methods |

**Drawbacks:**

| Issue | Impact |
|-------|--------|
| **Performance** | 10-50x slower than direct calls |
| **Type Safety** | Errors at runtime, not compile time |
| **Security** | Can break encapsulation |
| **Refactoring** | Changes in code won't break reflective calls |

---

## 1.10 Memory Leaks

### Q24: What causes memory leaks in Java and how do you find them?

**Answer:**

Even with GC, memory leaks occur when objects are **unintentionally kept alive**.

```
Root Causes of Memory Leaks:
┌─────────────────────────────────────────────┐
│ 1. Static Collections                       │
│    static List<Object> cache = new ArrayList│
│    → Never GC'd (root reference)            │
│                                             │
│ 2. Unclosed Resources                       │
│    Connection, Stream not closed            │
│    → Native memory leak                     │
│                                             │
│ 3. Inner Class References                   │
│    Non-static inner class holds ref to outer│
│    → Outer can't be GC'd                    │
│                                             │
│ 4. Listener/Callback not removed            │
│    Registered listener never unregistered   │
│    → Publisher holds subscriber alive       │
│                                             │
│ 5. ThreadLocal not cleaned                  │
│    ThreadLocal values persist in Thread pool│
│    → Values accumulate across requests      │
│                                             │
│ 6. String.intern() overuse                  │
│    Too many interned strings                │
│    → Pool grows indefinitely                │
└─────────────────────────────────────────────┘
```

**Example: Real Production Memory Leak**
```java
// BAD: ThreadLocal leak in web application
public class UserContext {
    private static final ThreadLocal<User> currentUser = new ThreadLocal<>();
    
    public static void setUser(User user) {
        currentUser.set(user);
    }
    
    // PROBLEM: If you don't call remove() after request,
    // the User object stays in ThreadLocal
    // In Tomcat (thread pool), the same thread is reused
    // → User from previous request leaks!
}

// FIX: Always clean up in finally block
public class UserContext {
    private static final ThreadLocal<User> currentUser = new ThreadLocal<>();
    
    public static void setUser(User user) {
        currentUser.set(user);
    }
    
    public static void clear() {
        currentUser.remove();  // CRITICAL!
    }
}

// In filter/interceptor:
try {
    UserContext.setUser(currentUser);
    filterChain.doFilter(request, response);
} finally {
    UserContext.clear();  // Always clean up
}
```

**How to Detect Memory Leaks:**

| Tool | What it does |
|------|-------------|
| **jmap** | `jmap -dump:format=b,file=heap.bin <pid>` — dumps heap |
| **jhat / Eclipse MAT** | Analyzes heap dump, finds largest objects |
| **VisualVM** | Real-time monitoring of heap, GC, threads |
| **JConsole** | Built-in JMX monitoring |
| **Prometheus + Grafana** | Production monitoring |
| **-XX:+HeapDumpOnOutOfMemoryError** | Auto-dump when OOM |

---

## 1.11 Garbage Collection Algorithms

### Q25: Explain the major GC algorithms in Java.

**Answer:**

```
GC Evolution:
Serial → Parallel → CMS → G1 → ZGC → Shenandoah

┌──────────────────────────────────────────────────────────────┐
│  Young Generation                │  Old Generation           │
│                                  │                           │
│  ┌──────┐ ┌──────┐ ┌──────┐    │  ┌───────────────────┐    │
│  │ Eden │ │  S0  │ │  S1  │    │  │    Old Gen        │    │
│  │      │ │From  │ │  To  │    │  │                   │    │
│  └──────┘ └──────┘ └──────┘    │  └───────────────────┘    │
│                                  │                           │
│  Minor GC: Eden + S0/S1 → S1/S0 │  Major GC: Old Gen       │
│  (fast, stop-the-world)         │  (slow, stop-the-world)  │
└──────────────────────────────────────────────────────────────┘
```

| Algorithm | Type | Best For | STW Pause | Java Version |
|-----------|------|----------|-----------|--------------|
| **Serial** | Single-threaded | Small apps, <100MB heap | Long | All |
| **Parallel** | Multi-threaded | Throughput-focused apps | Medium | All (default <Java 9) |
| **CMS** | Concurrent | Low-latency apps | Short | Deprecated in Java 9 |
| **G1** | Region-based | Balanced (latency + throughput) | Short, predictable | Java 9+ default |
| **ZGC** | Concurrent, NUMA-aware | Ultra-low latency (<10ms) | <1ms | Java 15+ (prod ready) |
| **Shenandoah** | Concurrent | Ultra-low latency | <1ms | OpenJDK |

### Q26: How does G1 Garbage Collector work?

**Answer:**

```
G1 divides heap into equal-sized REGIONS (1MB–32MB):
┌────┬────┬────┬────┬────┬────┬────┬────┐
│  E │  E │  S │  O │  O │  H │    │  E │
│Young│Young│Surv│Old │Old │Hum-│Free│Young│
│    │    │    │    │    │onous│    │    │
├────┼────┼────┼────┼────┼────┼────┼────┤
│  O │  E │    │  O │  S │  O │  E │    │
│Old │Young│Free│Old │Surv│Old │Young│Free│
└────┴────┴────┴────┴────┴────┴────┴────┘

E = Eden   S = Survivor   O = Old   H = Humongous (>50% region size)
```

**Key Concepts:**
1. **Regions** — heap split into ~2000 regions
2. **Mixed GC** — collects young + some old regions (not all old)
3. **Pause Prediction** — uses `-XX:MaxGCPauseMillis=200` (default)
4. **Humongous Objects** — objects >50% of region size go to special regions

**G1 GC Phases:**
```
1. Young GC → Evacuate young regions
2. Concurrent Mark → Identify garbage in old regions
3. Mixed GC → Evacuate young + high-garbage old regions
4. (If needed) Full GC → Last resort, evacuate all
```

### Q27: How to tune GC for a high-throughput payment system?

**Answer:**

```bash
# For Java 11/17 — G1GC (default, good for most cases)
java -XX:+UseG1GC \
     -XX:MaxGCPauseMillis=200 \
     -XX:G1HeapRegionSize=16m \
     -XX:InitiatingHeapOccupancyPercent=45 \
     -XX:ParallelGCThreads=8 \
     -XX:ConcGCThreads=4 \
     -Xms4g -Xmx4g \
     -XX:+HeapDumpOnOutOfMemoryError \
     -XX:HeapDumpPath=/var/log/app/heapdump.hprof \
     -Xlog:gc*:file=/var/log/app/gc.log:time,uptime:filecount=5,filesize=10M \
     -jar app.jar

# For Java 17+ with ultra-low latency requirements
java -XX:+UseZGC \
     -Xms8g -Xmx8g \
     -XX:+ZGenerational \
     -jar app.jar
```

| Scenario | Recommendation |
|----------|---------------|
| **High throughput, batch processing** | Parallel GC |
| **Balanced web application** | G1GC |
| **Ultra-low latency (<10ms pauses)** | ZGC (Java 15+) |
| **Large heap (>8GB)** | ZGC or Shenandoah |
| **Small heap (<256MB)** | Serial GC |

---

## 1.12 Design Patterns

### Q28: What are the most important Design Patterns for Java interviews?

**Answer:**

```
Design Patterns (Gang of Four)
├── Creational
│   ├── Singleton ✓ (Most Asked)
│   ├── Factory Method ✓
│   ├── Abstract Factory
│   ├── Builder ✓
│   └── Prototype
├── Structural
│   ├── Adapter ✓
│   ├── Decorator ✓
│   ├── Facade
│   ├── Proxy ✓
│   └── Composite
└── Behavioral
    ├── Strategy ✓
    ├── Observer ✓
    ├── Template Method ✓
    ├── Chain of Responsibility ✓
    └── Iterator
```

### Q29: Explain Singleton Pattern — thread-safe versions.

**Answer:**

```java
// ❌ NOT Thread-Safe
public class Singleton {
    private static Singleton instance;
    
    public static Singleton getInstance() {
        if (instance == null) {  // Race condition!
            instance = new Singleton();
        }
        return instance;
    }
}

// ✅ Thread-Safe: Double-Checked Locking
public class Singleton {
    private static volatile Singleton instance;  // volatile is CRITICAL
    
    private Singleton() {}
    
    public static Singleton getInstance() {
        if (instance == null) {               // First check (no lock)
            synchronized (Singleton.class) {
                if (instance == null) {        // Second check (with lock)
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}

// Why volatile?
// Without volatile, JVM can reorder instructions:
// 1. Allocate memory
// 2. Assign reference to instance  ← Another thread sees partially constructed object!
// 3. Call constructor
// volatile prevents this reordering.

// ✅ BEST: Enum Singleton (recommended by Joshua Bloch)
public enum Singleton {
    INSTANCE;
    
    public void doSomething() {
        // ...
    }
}
// Guarantees: Thread-safe, serialization-safe, reflection-safe
```

### Q30: Explain the Strategy Pattern with a real-world example.

**Answer:**

```java
// Strategy Interface
public interface PaymentStrategy {
    void pay(double amount);
}

// Concrete Strategies
public class CreditCardPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via Credit Card");
    }
}

public class UPIPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via UPI");
    }
}

public class WalletPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via Wallet");
    }
}

// Context
public class PaymentProcessor {
    private PaymentStrategy strategy;
    
    public PaymentProcessor(PaymentStrategy strategy) {
        this.strategy = strategy;
    }
    
    public void processPayment(double amount) {
        strategy.pay(amount);
    }
}

// Usage
PaymentProcessor processor = new PaymentProcessor(new UPIPayment());
processor.processPayment(500);  // Paid 500.0 via UPI

// In Spring, you'd use:
// @Autowired Map<String, PaymentStrategy> strategies;
// strategies.get("UPI").pay(amount);
```

### Q31: Explain the Builder Pattern.

**Answer:**

```java
// Problem: Too many constructor parameters (telescoping constructor)
// new Payment("id", 100, "INR", "UPI", "REF", "note", true, null, ...);

// Solution: Builder Pattern
public class Payment {
    private final String id;          // required
    private final double amount;      // required
    private final String currency;    // required
    private final String method;      // optional
    private final String reference;   // optional
    private final String note;        // optional
    private final boolean recurring;  // optional
    
    private Payment(Builder builder) {
        this.id = builder.id;
        this.amount = builder.amount;
        this.currency = builder.currency;
        this.method = builder.method;
        this.reference = builder.reference;
        this.note = builder.note;
        this.recurring = builder.recurring;
    }
    
    public static class Builder {
        // Required
        private final String id;
        private final double amount;
        private final String currency;
        
        // Optional
        private String method = "UPI";
        private String reference;
        private String note;
        private boolean recurring = false;
        
        public Builder(String id, double amount, String currency) {
            this.id = id;
            this.amount = amount;
            this.currency = currency;
        }
        
        public Builder method(String method) {
            this.method = method;
            return this;
        }
        
        public Builder reference(String reference) {
            this.reference = reference;
            return this;
        }
        
        public Builder note(String note) {
            this.note = note;
            return this;
        }
        
        public Builder recurring(boolean recurring) {
            this.recurring = recurring;
            return this;
        }
        
        public Payment build() {
            return new Payment(this);
        }
    }
}

// Clean usage:
Payment payment = new Payment.Builder("PAY001", 1000, "INR")
    .method("CreditCard")
    .reference("REF123")
    .note("Monthly subscription")
    .recurring(true)
    .build();
```

### Q32: Explain the Observer Pattern.

**Answer:**

```java
// Used in: Event systems, Spring Events, Kafka, UI frameworks

// Observer Interface
public interface PaymentEventListener {
    void onPaymentSuccess(PaymentEvent event);
    void onPaymentFailure(PaymentEvent event);
}

// Concrete Observers
public class NotificationService implements PaymentEventListener {
    public void onPaymentSuccess(PaymentEvent event) {
        sendEmail("Payment of " + event.getAmount() + " successful");
    }
    public void onPaymentFailure(PaymentEvent event) {
        sendSMS("Payment failed for " + event.getAmount());
    }
}

public class AuditService implements PaymentEventListener {
    public void onPaymentSuccess(PaymentEvent event) {
        logAudit("PAYMENT_SUCCESS", event);
    }
    public void onPaymentFailure(PaymentEvent event) {
        logAudit("PAYMENT_FAILURE", event);
    }
}

// Subject (Publisher)
public class PaymentService {
    private List<PaymentEventListener> listeners = new ArrayList<>();
    
    public void addListener(PaymentEventListener listener) {
        listeners.add(listener);
    }
    
    public void processPayment(Payment payment) {
        // Process...
        PaymentEvent event = new PaymentEvent(payment);
        
        // Notify all observers
        for (PaymentEventListener listener : listeners) {
            listener.onPaymentSuccess(event);
        }
    }
}

// Spring way (much cleaner):
@Service
public class PaymentService {
    @Autowired
    private ApplicationEventPublisher publisher;
    
    public void processPayment(Payment payment) {
        // Process...
        publisher.publishEvent(new PaymentSuccessEvent(payment));
    }
}

@Component
public class NotificationListener {
    @EventListener
    public void handlePaymentSuccess(PaymentSuccessEvent event) {
        // Send notification
    }
}
```

### Q33: Explain the Chain of Responsibility Pattern.

**Answer:**

```java
// Used in: Servlet Filters, Spring Security, Logging, Validation

// Handler Interface
public abstract class PaymentValidator {
    protected PaymentValidator next;
    
    public PaymentValidator setNext(PaymentValidator next) {
        this.next = next;
        return next;
    }
    
    public void validate(Payment payment) {
        doValidate(payment);
        if (next != null) {
            next.validate(payment);
        }
    }
    
    protected abstract void doValidate(Payment payment);
}

// Concrete Handlers
public class AmountValidator extends PaymentValidator {
    protected void doValidate(Payment payment) {
        if (payment.getAmount() <= 0) {
            throw new ValidationException("Amount must be positive");
        }
    }
}

public class FraudCheckValidator extends PaymentValidator {
    protected void doValidate(Payment payment) {
        if (isFraudulent(payment)) {
            throw new ValidationException("Fraud detected");
        }
    }
}

public class BalanceValidator extends PaymentValidator {
    protected void doValidate(Payment payment) {
        if (getBalance() < payment.getAmount()) {
            throw new ValidationException("Insufficient balance");
        }
    }
}

// Build the chain
PaymentValidator chain = new AmountValidator();
chain.setNext(new FraudCheckValidator())
     .setNext(new BalanceValidator());

// Process
chain.validate(payment);
```

### Q34: Explain the Proxy Pattern.

**Answer:**

```java
// Types: Static Proxy, Dynamic Proxy, CGLIB Proxy

// 1. Static Proxy
public interface PaymentService {
    PaymentResponse process(PaymentRequest request);
}

public class PaymentServiceImpl implements PaymentService {
    public PaymentResponse process(PaymentRequest request) {
        // Actual payment logic
        return gateway.charge(request);
    }
}

public class PaymentServiceProxy implements PaymentService {
    private PaymentService realService;
    
    public PaymentResponse process(PaymentRequest request) {
        // Pre-processing (logging, security, caching)
        log.info("Processing payment: {}", request.getId());
        long start = System.currentTimeMillis();
        
        // Delegate to real service
        PaymentResponse response = realService.process(request);
        
        // Post-processing
        long duration = System.currentTimeMillis() - start;
        metrics.recordLatency("payment", duration);
        log.info("Payment completed in {}ms", duration);
        
        return response;
    }
}

// 2. Spring uses CGLIB proxies for @Transactional, @Cacheable, @Async
@Service
public class OrderService {
    @Transactional  // Spring creates a CGLIB proxy that wraps this method
    public void placeOrder(Order order) {
        // Transaction starts before method
        orderRepo.save(order);
        // Transaction commits after method (or rollback on exception)
    }
}
```

### Q35: What is the Template Method Pattern?

**Answer:**

```java
// Defines the skeleton of an algorithm, subclasses fill in the steps

public abstract class DataProcessor {
    
    // Template method (final — can't be overridden)
    public final void process(String file) {
        readData(file);
        processData();
        writeResult();
    }
    
    // Abstract steps — subclasses must implement
    protected abstract void readData(String file);
    protected abstract void processData();
    
    // Hook — subclasses CAN override
    protected void writeResult() {
        System.out.println("Writing to default output");
    }
}

public class CSVProcessor extends DataProcessor {
    protected void readData(String file) {
        System.out.println("Reading CSV: " + file);
    }
    protected void processData() {
        System.out.println("Parsing CSV rows");
    }
}

public class JSONProcessor extends DataProcessor {
    protected void readData(String file) {
        System.out.println("Reading JSON: " + file);
    }
    protected void processData() {
        System.out.println("Parsing JSON objects");
    }
    @Override
    protected void writeResult() {
        System.out.println("Writing to MongoDB");
    }
}
```

---

### Q36: Explain the Factory Method Pattern.

**Answer:**

```java
// Creates objects without specifying exact class
// Spring uses this extensively (BeanFactory, ApplicationContext)

public interface NotificationFactory {
    Notification create(NotificationType type);
}

public class Notification {
    private String message;
    private String channel;  // EMAIL, SMS, PUSH
}

// Factory
public class NotificationService {
    
    public Notification createNotification(NotificationType type, String message) {
        switch (type) {
            case EMAIL:
                return new EmailNotification(message, getSmtpConfig());
            case SMS:
                return new SMSNotification(message, getTwilioConfig());
            case PUSH:
                return new PushNotification(message, getFirebaseConfig());
            default:
                throw new IllegalArgumentException("Unknown type: " + type);
        }
    }
}

// Spring way (better):
@Component
public interface PaymentGateway {
    PaymentResponse charge(PaymentRequest request);
}

@Component("razorpay")
public class RazorpayGateway implements PaymentGateway { ... }

@Component("stripe")
public class StripeGateway implements PaymentGateway { ... }

@Service
public class PaymentService {
    @Autowired
    private Map<String, PaymentGateway> gateways;
    // Spring auto-injects: {"razorpay": RazorpayGateway, "stripe": StripeGateway}
    
    public PaymentResponse process(String gatewayName, PaymentRequest request) {
        return gateways.get(gatewayName).charge(request);
    }
}
```

---

### Q37: Explain the Decorator Pattern.

**Answer:**

```java
// Dynamically adds behavior to objects without modifying the class
// Java I/O streams use this pattern extensively

// Base Interface
public interface DataSource {
    String readData();
    void writeData(String data);
}

// Concrete Component
public class FileDataSource implements DataSource {
    private String filename;
    
    public String readData() { /* read from file */ }
    public void writeData(String data) { /* write to file */ }
}

// Base Decorator
public abstract class DataSourceDecorator implements DataSource {
    protected DataSource wrapped;
    
    public DataSourceDecorator(DataSource source) {
        this.wrapped = source;
    }
}

// Concrete Decorators
public class EncryptionDecorator extends DataSourceDecorator {
    public EncryptionDecorator(DataSource source) { super(source); }
    
    public String readData() {
        return decrypt(wrapped.readData());  // Decrypt after reading
    }
    
    public void writeData(String data) {
        wrapped.writeData(encrypt(data));    // Encrypt before writing
    }
}

public class CompressionDecorator extends DataSourceDecorator {
    public CompressionDecorator(DataSource source) { super(source); }
    
    public String readData() {
        return decompress(wrapped.readData());
    }
    
    public void writeData(String data) {
        wrapped.writeData(compress(data));
    }
}

// Stack decorators:
DataSource source = new CompressionDecorator(
                        new EncryptionDecorator(
                            new FileDataSource("data.txt")));

source.writeData("secret data");
// Flow: compress → encrypt → write to file

// Java I/O uses this pattern:
BufferedReader br = new BufferedReader(      // Decorator 2
                        new InputStreamReader(  // Decorator 1
                            new FileInputStream("file.txt")));  // Base
```

---

### Q38: Explain the Adapter Pattern.

**Answer:**

```java
// Converts one interface to another expected by the client
// Real-world: Integrating a new payment gateway with existing code

// Existing interface (what your code expects)
public interface PaymentGateway {
    PaymentResponse charge(double amount, String currency);
}

// New third-party SDK (different interface)
public class RazorpaySDK {
    public RazorpayResult createOrder(RazorpayOrderRequest req) {
        // ... different API
    }
}

// Adapter (bridges the gap)
public class RazorpayAdapter implements PaymentGateway {
    private RazorpaySDK razorpaySDK;
    
    @Override
    public PaymentResponse charge(double amount, String currency) {
        // Convert to Razorpay format
        RazorpayOrderRequest req = new RazorpayOrderRequest();
        req.setOrderAmount(amount);
        req.setCurrencyCode(currency);
        
        // Call the third-party SDK
        RazorpayResult result = razorpaySDK.createOrder(req);
        
        // Convert back to our format
        PaymentResponse response = new PaymentResponse();
        response.setTransactionId(result.getOrderId());
        response.setStatus(result.isSuccess() ? "SUCCESS" : "FAILED");
        
        return response;
    }
}

// Client code doesn't change:
PaymentGateway gateway = new RazorpayAdapter();
gateway.charge(1000, "INR");  // Works seamlessly
```

---

That covers **Module 1: Core Java (75 Q&A)** — Questions 1 through 38 so far. This is already a comprehensive deep-dive.

**Shall I continue with the remaining 37 questions of Module 1** (covering topics like `final` vs `finally` vs `finalize`, `==` vs `.equals()`, immutability, autoboxing, enums, var-args, exception hierarchy, try-with-resources, generics, etc.)?