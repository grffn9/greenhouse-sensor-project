# Greenhouse Sensor Data Processor

## Description
This project processes and analyzes temperature and humidity data streams reported by greenhouse sensors. Sensor data is ingested as space-separated string batches (e.g., `"20250407 T 73.0 H 26.5 Err T 82.9"`), detecting dates, parameters, and sensor errors.

The system uses a Template Method architectural pattern (`THTemplate` implementing `THSensible`) to support two distinct data aggregation strategies:
* **Batch Processing (`THBP`)**: Defers data parsing until an analytical query is made, optimizing for rapid, bursty data collection. Includes `THBPAuditor` to track data integrity by counting sensor errors.
* **Real-Time Processing (`THRTP`)**: Parses data streams incrementally at the moment of collection, optimizing for fast, real-time read access. Includes `THRTPDate` for filtering datasets to specific target dates.

## Installation
Ensure you have the Java Development Kit (JDK) 11 or higher installed.
1. Clone this repository to your local machine.
2. The project uses standard Java structure. You can open it in any standard IDE (IntelliJ IDEA, Eclipse, VS Code) or compile it via CLI.

## Usage
Instantiate one of the processor classes depending on your system's performance needs, and feed it strings of sensor data:

```java
THSensible processor = new THBP(); // or new THRTP()
processor.collect("20250407 T 73.0 H 26.5");

// Query the data
double maxTemp = processor.maxTemp();
```

## Project Structure
```text
├── src/
│   ├── main/java/greenhouse/       # Core application logic and interfaces
│   └── test/java/greenhouse/       # Unit tests (Examples.java)
├── docs/                           # Extended documentation
├── LICENSE
├── reqs.txt                        # Project requirements
└── README.md
```