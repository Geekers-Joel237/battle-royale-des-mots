# 🎮 Battle Royale des Mots

[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](https://opensource.org/licenses/MIT)
[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/technologies/downloads/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3%2B-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![JavaFX](https://img.shields.io/badge/JavaFX-21-blue.svg)](https://openjfx.io/)
[![Gradle](https://img.shields.io/badge/Gradle-8%2B-02303A.svg)](https://gradle.org/)

---

## 🇬🇧 English

### 📖 About the Project

**Battle Royale des Mots** is an **extremely fun** real-time multiplayer word game where players face off in a virtual arena, forming words from a continuous stream of letters.  
The longer and rarer the word, the more points you score—or the more damage you inflict.  
The last player standing or the one with the highest score takes the crown! 👑

Built from scratch with **professional software engineering practices**:
- **Domain-Driven Design (DDD)** for a clean, expressive domain model
- **Hexagonal Architecture** to keep business logic framework-independent
- **Test-Driven Development (TDD)** for robust and maintainable code

### ✨ Key Features
- **Real-Time Multiplayer** – Up to 50 players per match
- **Two Modes** – **Survival** & **Score Attack**
- **Dynamic Letter Flow** – Fast-paced, unpredictable matches
- **Advanced Scoring** – Rewards word length and letter rarity

### 🏛️ Architecture
We use **Hexagonal Architecture (Ports & Adapters)**:
- **Domain Layer** – Core business rules, pure Java, no dependencies
- **Application Layer** – Use cases, defines “Ports” (interfaces)
- **Infrastructure Layer** – Implements adapters: Spring Boot backend, JavaFX client, database, etc.

### 🛠️ Technology Stack

| Area          | Technology |
|---------------|------------|
| **Backend**   | Java 21, Spring Boot 3.3+, Spring WebSocket, JPA |
| **Frontend**  | JavaFX 21 |
| **Database**  | H2 (Dev), MySQL (Prod) |
| **Build Tool**| Gradle 8+ |
| **Testing**   | JUnit 5, AssertJ, Mockito |

### 🏗️ Project Structure
- `brm-domain` – Pure domain model (game rules & logic)
- `brm-contracts` – DTOs for client-server communication
- `brm-server` – Spring Boot backend
- `brm-client` – JavaFX frontend
- `brm-tools` – Utility scripts (e.g., dictionary importer)

---

## 🇫🇷 Français

### 📖 À propos du projet

**Battle Royale des Mots** est un jeu de mots multijoueur en temps réel **très fun** où les joueurs s’affrontent dans une arène virtuelle en formant des mots à partir d’un flux de lettres continu.  
Plus le mot est long et rare, plus vous marquez de points ou infligez de dégâts.  
Le dernier survivant ou celui avec le meilleur score remporte la partie ! 🏆

Construit avec des **pratiques professionnelles** :
- **Domain-Driven Design (DDD)** pour un domaine clair et expressif
- **Architecture Hexagonale** pour isoler la logique métier
- **Test-Driven Development (TDD)** pour un code fiable et maintenable

### ✨ Fonctionnalités clés
- **Multijoueur temps réel** – Jusqu’à 50 joueurs par partie
- **Deux modes** – **Survie** & **Score Attack**
- **Flux de lettres dynamique** – Parties rapides et imprévisibles
- **Système de score avancé** – Basé sur la longueur et la rareté des lettres

### 🏛️ Architecture
Basée sur une **Architecture Hexagonale (Ports & Adapters)** :
- **Couche Domaine** – Logique métier pure, indépendante des frameworks
- **Couche Application** – Cas d’utilisation, définit les “Ports” (interfaces)
- **Couche Infrastructure** – Implémente les adaptateurs : backend Spring Boot, client JavaFX, base de données, etc.

### 🛠️ Stack Technique

| Domaine       | Technologie |
|---------------|-------------|
| **Backend**   | Java 21, Spring Boot 3.3+, Spring WebSocket, JPA |
| **Frontend**  | JavaFX 21 |
| **Base de données** | H2 (Dev), MySQL (Prod) |
| **Outil de build**  | Gradle 8+ |
| **Tests**     | JUnit 5, AssertJ, Mockito |

### 🏗️ Structure du projet
- `brm-domain` – Modèle de domaine pur (règles et logique du jeu)
- `brm-contracts` – DTOs pour communication client-serveur
- `brm-server` – Backend Spring Boot
- `brm-client` – Frontend JavaFX
- `brm-tools` – Scripts utilitaires (ex: importateur de dictionnaire)

---

## 🚀 How to Start / Comment démarrer
*(Coming soon – À venir)*  
