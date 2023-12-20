# YogaClass_Website

## Introduction

Welcome to the YogaClass_Website project! This GitHub repository contains the source code and documentation for building an admission form for monthly Yoga classes. As the Chief Technology Officer (CTO) of an outsourcing firm, you are tasked with creating a user-friendly platform that allows individuals to enroll in monthly Yoga classes, pay fees on a monthly basis, and select their preferred class timings.

## Problem Statement

The admission form for Yoga classes has the following requirements:

1. **Age Limit:** Only individuals within the age range of 18-65 can enroll for the monthly classes.

2. **Payment System:** Participants are required to pay the monthly fees of 500/- Rs INR. The payment is to be made every month, and individuals can pay at any time during the month.

3. **Batch Selection:** There are four batches available each day: 6-7AM, 7-8AM, 8-9AM, and 5-6PM. Participants can choose any batch for a month and have the flexibility to switch to a different batch in the following months. However, within the same month, they must remain in the selected batch.

## Implementation Details

The project includes the following features:

1. **User Input and Validation:**
   - Accepts user data for enrollment.
   - Performs basic validations to ensure the provided information is accurate and within the specified criteria.

2. **Database Management:**
   - Stores user enrollment data in a database.
   - Provides an Entity-Relationship (ER) diagram to illustrate the database design.

   ![ER Diagram]
   ![YogaClass_Website drawio](https://github.com/thakurRashmi/YogaClass_Website/assets/76115061/ada16028-f373-4ff8-bb38-c06aaec416bf)

4. **Payment Handling:**
   - Assumes the existence of a mock function named `CompletePayment()`.
   - `CompletePayment()` accepts user details and payment information, simulating the payment process. This function is a placeholder, and its actual implementation is not required.

5. **Response to Front-End:**
   - Returns a response to the front-end based on the payment response from the `CompletePayment()` function.

## Getting Started

To get started with the YogaClass_Website project, follow these steps:

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/your-username/YogaClass_Website.git
