# Currency Conversion Service

Simple REST API built with Spring Boot for currency conversion using configurable exchange rates, environment variables, active profiles, and request validation.

---

## Features

* Currency Conversion API
* Custom Currency Converter Component
* Service Layer Architecture
* Configuration using `@ConfigurationProperties`
* Environment Variable Support
* Spring Profiles (`dev`, `prod`)
* Request Validation
* Global Exception Handling
* Exchange Rate Listing Endpoint

---

## Project Structure

```text
com.indivaragroup.jdt17.currency.conversion
├── controller
│   └── ConverterController
│
├── service
│   └── ConverterService
│
├── converter
│   └── CurrencyConverter
│
├── config
│   └── RateProperties
│
├── data
│   ├── ConversionRequest
│   ├── ConversionResponse
│   └── AppInfoResponse
|   └── ProfileResponse
│
├── exception
│   └── GlobalExceptionHandler
│
└── CurrencyConversionApplication
```

---

## Configuration

### application.yml

```yaml
app:
  name: ${APP_NAME:Unknown Application}

currency:
  rates:
    USD_IDR: 16000
    IDR_USD: 0.0000625
    SGD_IDR: 12500
    IDR_SGD: 0.00008
```

### application-dev.yml

```yaml
app:
  message: Running in Development
```

### application-prod.yml

```yaml
app:
  message: Running in Production
```

---

## Running the Application

### Development Profile

```bash
export APP_NAME="Currency Conversion Service"

mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### Production Profile

```bash
export APP_NAME="Currency Conversion Service"

mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

Application will run at:

```text
http://localhost:8080
```

---

# API Documentation

## 1. Currency Conversion

### Request

```http
GET /api/converter?amount=100&from=USD&to=IDR
```

### Example

```text
http://localhost:8080/api/converter?amount=100&from=USD&to=IDR
```

### Response

```json
{
  "amount": 100,
  "from": "USD",
  "to": "IDR",
  "rate": 16000,
  "result": 1600000
}
```

---

## Supported Exchange Rates

| From | To  |      Rate |
| ---- | --- | --------: |
| USD  | IDR |     16000 |
| IDR  | USD | 0.0000625 |
| SGD  | IDR |     12500 |
| IDR  | SGD |   0.00008 |

---

## 2. Application Information

### Request

```http
GET /api/info
```

### Example

```text
http://localhost:8080/api/info
```

### Response

```json
{
  "applicationName": "Currency Conversion Service"
}
```

If environment variable is not provided:

```json
{
  "applicationName": "Unknown Application"
}
```

---

## 3. Active Profile Information

### Request

```http
GET /api/profile
```

### Example

```text
http://localhost:8080/api/profile
```

### DEV Response

```json
{
  "message": "Running in Development"
}
```

### PROD Response

```json
{
  "message": "Running in Production"
}
```

---

## 4. Exchange Rate List

### Request

```http
GET /api/rates
```

### Example

```text
http://localhost:8080/api/rates
```

### Response

```json
[
  {
    "from": "USD",
    "to": "IDR",
    "rate": 16000
  },
  {
    "from": "IDR",
    "to": "USD",
    "rate": 0.0000625
  },
  {
    "from": "SGD",
    "to": "IDR",
    "rate": 12500
  },
  {
    "from": "IDR",
    "to": "SGD",
    "rate": 0.00008
  }
]
```

---

# Validation

## Amount Must Be Greater Than Zero

### Request

```text
http://localhost:8080/api/converter?amount=0&from=USD&to=IDR
```

### Response

```json
{
  "message": "Amount must be greater than zero"
}
```

---

## From Currency Cannot Be Empty

### Request

```text
http://localhost:8080/api/converter?amount=100&from=&to=IDR
```

### Response

```json
{
  "message": "From currency cannot be empty"
}
```

---

## To Currency Cannot Be Empty

### Request

```text
http://localhost:8080/api/converter?amount=100&from=USD&to=
```

### Response

```json
{
  "message": "To currency cannot be empty"
}
```

---

## Unsupported Currency Pair

### Request

```text
http://localhost:8080/api/converter?amount=100&from=EUR&to=IDR
```

### Response

```json
{
  "message": "Rate not found"
}
```

---

# Design Principles

This project demonstrates:

* REST API Development
* Dependency Injection
* Service Layer Pattern
* Custom Spring Component
* Configuration Properties
* Environment Variables
* Spring Profiles
* Request Validation
* Exception Handling
* Clean Project Structure

---

# Author

Verry Kurniawan
