# Smart Greenhouse Sensor Processing

## Overview

This project implements a Java library for processing temperature and humidity data from smart greenhouse IoT sensors using the Template Method Design Pattern. It supports both Batch Processing and Real-Time Processing strategies, as well as specialized variants for date-restricted and error-auditing deployments. The architecture emphasizes code reuse through inheritance, with THTemplate handling most shared logic and subclasses customizing behavior.

## Features

* Multiple Processing Strategies – Optimized for different query and data collection frequencies.
* Data Cleaning – Removes invalid entries, dates, and sensor errors.
* Data Parsing – Converts string-based sensor data into sorted temperature and humidity lists.
* Extensible Design – New processing strategies can be added easily via subclassing.
* Unit Tests – Examples.java verifies correctness, performance, and edge cases.

## Class Structure

* THSensible – Interface defining public methods for sensor data operations.
* THTemplate – Abstract class implementing most logic (collecting, cleaning, parsing, querying).
* THBP – Batch processing implementation.
* THRTP – Real-time processing implementation.
* THBPAuditor – Batch processor with error auditing.
* THRTPDate – Real-time processor restricted to a specific date.
* Examples.java – JUnit test cases for all classes.

## Data Format

Sensor data strings follow the format:
yyyymmdd value1 value2 ...
Where:

* value can be:

  * Temperature in Fahrenheit (100.0 – 200.0)
  * Humidity percentage (0.0% – 100.0%)
  * "error" to indicate sensor malfunction

Example:
20250409 101.5 78.3% error

## Requirements

* Java JDK 17 or later
* Recommended IDE: IntelliJ IDEA (Eclipse and VSCode also supported)

## Installation & Usage

1. Clone the repository:
   git clone [https://github.com/your-username/greenhouse-sensor-project.git](https://github.com/your-username/greenhouse-sensor-project.git)
   cd greenhouse-sensor-project
2. Open the project in your preferred Java IDE.
3. Run Examples.java to verify functionality.

## Testing

Run all unit tests in Examples.java to ensure:

* All processing strategies function correctly.
* Data cleaning and parsing logic works as intended.
* Performance differences between batch and real-time modes are measurable.

## License

This project is licensed under the MIT License (LICENSE).
