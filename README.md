# Redis-with-Kafka

Two Spring Boot microservices using **Kafka** and **Redis** for asynchronous communication, and **OpenFeign** for synchronous REST calls. This project demonstrates scalable and decoupled service interactions in a microservices architecture.

Inspired by the principles discussed in **"Cloud Native Java" by Josh Long & Kenny Bastani**, the implementation showcases real-world usage of messaging systems and REST clients to enable communication between independent services.

## 🔧 Overview

- **Microservice A**  
  - Publishes events to Kafka  
  - Calls Microservice B via OpenFeign (synchronous)

- **Microservice B**  
  - Consumes Kafka messages (asynchronous)  
  - Exposes REST APIs  
  - Uses Redis for caching or optional pub/sub

## 📚 Reference

> *Cloud Native Java* by Josh Long & Kenny Bastani  
> A comprehensive guide on building resilient, distributed systems using Spring Boot and modern cloud patterns.

## 🛠️ Tech Stack

- Java 17+
- Spring Boot
- Apache Kafka
- Redis
- OpenFeign
---

Feel free to clone, explore, and adapt this repo to learn about mixed communication strategies in microservices.
