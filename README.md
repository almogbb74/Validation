# Java Validation Library 🛡️

A robust, extensible, and fluent validation library for Java applications. 
This library leverages modern Java functional programming to create clean, readable, and highly maintainable validation rules.

## ✨ Features

* Fluent Combinator API: Chain multiple validation rules together using logical operators (and, or, xor, all, none).

* Pre-Built Validations: Includes standard checks for emails, passwords, usernames, and age.

* Design Pattern Driven: Architected using industry-standard design patterns (Combinator, Factory Method, Template Method).

* Null-Safe Results: Validation results use java.util.Optional to handle error messages safely.

## 🏗️ Design Patterns Implemented

* Combinator Pattern: The core of the library. It allows simple UserValidation rules to be composed into complex validation trees using functional interfaces and lambdas.

* Factory Method: The UserFactory handles the instantiation logic for different user tiers (BasicUser, PremiumUser, PlatinumUser), abstracting object creation from the client code.

* Template Method: The UserUtils.sort method defines the skeleton of a sorting algorithm while deferring the specific comparison logic to an injected Comparator.
