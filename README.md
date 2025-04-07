<div align="center">
    
  <img src="./app/src/main/resources/logo_images/blue-hashtag-design.png" alt="Caltrix Logo" style="width:32%;height:auto;"/>
  <h1>Caltrix</h1>
  <p>A minimal calculator built with Java.</p>

  <div style="display: flex; justify-content: center; gap: 10px;">
    <a href="https://www.java.com/en/" style="text-decoration: none;"><img src="https://img.shields.io/badge/Java-23%2B-purple" alt="Java"></a>
    <a href="https://github.com/gradle/gradle" style="text-decoration: none;"><img src="https://img.shields.io/badge/Gradle-8.1%2B-blue" alt="Gradle"></a>
  </div>

 [Features](#features) • [Themes](#themes) • [Design](#design) • [Credits](#credits)
</div>


## Overview

Caltrix is an application designed to provide an intuitive and powerful interface for mathematical expression parsing and evaluation. The app supports a variety of themes, customizable settings, and an organized multi-view design to enhance user experience.

---

## Architecture

Caltrix utilizes the **Model-View-Controller (MVC)** architecture, ensuring a clean separation of concerns between data management, user interface, and control logic.

---

## Global Settings

- **Degree/Radians Mode:**  
  Users can switch between degree and radian modes for trigonometric calculations.

---

## Themes

Caltrix comes with four pre-defined themes which can be easily found online:

    Note: upon finishing the application add images of each theme to each listed theme.

- **Everforest - Light**
- **Everforest - Dark**
- **Catppuccin - Latte**
- **Catppuccin - Frappe**

---

## Features

- **Expression Parsing:**  
  Capable of parsing complex expressions, e.g.,  
  `(3 * (3 + (3/2)) + (9^4)) + (cos(pi/3))`

- **Mathematical Functions:**  
  Supports trigonometric functions, roots, and squares.

- **Text Sizing Options:**  
  Two text size options available – *Big* and *Small*.

- **Theme Support:**  
  A total of 4 themes to choose from for a personalized user experience.

- **Undo/Redo Functionality:**  
  Implemented using stack and queue data structures to manage input operations.

- **Clear/Clear All Buttons:**  
  - **Clear:** Clears the active view.  
  - **Clear All:** Clears the output view.

- **Persistent User Data:**  
  Automatically stores user data upon closing the application, allowing users to resume where they left off.

- **Symbol Conversion:**  
  Converts characters to their natural symbols (e.g., pi, theta).

### Features TBD (To Be Determined)

- **Enhanced Expression Representation:**  
  Improve the accurate representation of division and exponentiation (e.g., ensuring that an expression like `3/4` is not misinterpreted).

---

## Design

Caltrix is structured into three primary views, each serving a unique purpose:

### Input View

- **Purpose:**  
  Contains buttons that allow users to click or press to input characters or numbers.

### Output View

- **Purpose:**  
  Utilizes a stack data structure to display the most recent submitted input operations. It also contains an **Active View** component.

### Active View

- **Purpose:**  
  Highlights the selected frame from which the input is currently being streamed.

---
