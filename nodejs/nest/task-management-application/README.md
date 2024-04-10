# Task Management Application in NestJS

This is a task management application built with NestJS.

## Description

This project is a practice repository for developing a task management application using NestJS. It provides a RESTful API for managing tasks, including creating, updating, deleting, and retrieving tasks.

## General Objectives

- NestJS Modules
- NestJS Controllers
- NestJS Services and Providers
- Controller-to-Service communication
- Validation using NestJS Pipes

## Objectives - Back-end & Architecture

- Develop production-ready REST APIs
- CRUD operations (Create, Read, Update, Delete)
- Error handling
- Data Transfer Objects (DTO)
- System modularity
- Back-end development best practices
- Configuration Management
- Logging
- Security best practices

## Objectives - Persistence

- Connecting the application to a database
- Working with relational databases
- Using TypeORM
- Writing simple and complex queries using QueryBuilder
- Performance when working with a database

## Objectives - Authorization / Authentication

- Signing up, signing in
- Authentication and Authorization
- Protected resources
- Ownership of tasks by users
- Using JWT tokens (JSON Web Tokens)
- Password hashing, salts and properly storing passwords

## Objectives - Deployment

- Polishing the application for Production use
- Deploying NestJS apps to AWS (Amazon Web Services)
- Deploying front-end applications to Amazon S3
- Wiring up the front-end and back-end

## API Endpoints - Tasks
| Method | Endpoint | Description |
| ------ | -------- | ----------- |
| GET    | /tasks   | Get all tasks (including filters) |
| GET    | /tasks/:id | Get a specific task |
| POST   | /tasks   | Create a new task |
| DELETE    | /tasks/:id | Delete a task |
| PATCH | /tasks/:id/status | Update a task |

## API Endpoints - Auth
| Method | Endpoint | Description |
| ------ | -------- | ----------- |
| POST    | /auth/signup/   | Sign up |
| POST    | /auth/signin | Sign in |