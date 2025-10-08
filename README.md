# **JetCo Library**

Welcome to the **JetCo** library documentation!
JetCo is a ready-to-go Compose library building awesome UI components for both **Android** and **KMP** (Kotlin Multiplatform). 💡

Your ultimate **Open-Source** partner for crafting visually stunning, interactive, and efficient UI components across multiple platforms including **Android**, **iOS**, **JVM**, **JS**, and **WASM**. Designed specifically for Jetpack Compose enthusiasts, JetCo is here to make your life easier, your apps prettier, and your users happier. 😊

View the full Documentation of <a href="https://jetco.developerstring.com" target="_blank" rel="noopener noreferrer">JetCo Docs</a>

<a href="https://github.com/DeveloperChunk/JetCo" target="_blank" rel="noopener noreferrer">![GitHub release (latest by date)](https://img.shields.io/github/v/release/developerchunk/jetco?label=GitHub)</a> <a href="https://central.sonatype.com/artifact/com.developerstring.jetco/ui" target="_blank" rel="noopener noreferrer">![Maven Central](https://img.shields.io/maven-central/v/com.developerstring.jetco/ui.svg?label=Maven%20Central)</a>

---

## 🎨 **JetCo in Action**

Check out the examples below to see JetCo's awesome UI components!

<table>
  <tr>
    <td>
      <img src="https://github.com/developerchunk/JetCo/blob/main/assets/images/1.jpg?raw=true" alt="Description 1" width="750"/>
      <p>Pie Chart</p>
    </td>
    <td>
      <img src="https://github.com/developerchunk/JetCo/blob/main/assets/images/2.jpg?raw=true" alt="Description 2" width="750"/>
      <p>Column Bar Chart</p>
    </td>
    <td>
      <img src="https://github.com/developerchunk/JetCo/blob/main/assets/images/3.jpg?raw=true" alt="Description 3" width="750"/>
      <p>Extended Column Bar Chart</p>
    </td>
    <td>
      <img src="https://github.com/developerchunk/JetCo/blob/main/assets/images/4.jpg?raw=true" alt="Description 4" width="750"/>
      <p>Group Column Bar Chart</p>
    </td>
  </tr>
  <tr>
    <td>
      <img src="https://github.com/developerchunk/JetCo/blob/main/assets/images/ticket_card.png?raw=true" alt="Description 5" width="750"/>
      <p>Ticket Card</p>
    </td>
    <td>
      <img src="https://github.com/developerchunk/JetCo/blob/main/assets/images/vertical-stepper.png?raw=true" alt="Description 6" width="750"/>
      <p>Vertical Stepper</p>
    </td>
    <td>
      <img src="https://github.com/developerchunk/JetCo/blob/main/assets/images/horizontal-stepper.png?raw=true" alt="Description 7" width="750"/>
      <p>Horizontal Stepper & CompactHorizontalStepper</p>
    </td>
    <td>
      <img src="https://github.com/developerchunk/JetCo/blob/main/assets/images/switch_button.gif?raw=true" alt="Description 8" width="150"/>
      <p>SwitchButton</p>
    </td>
  </tr>
</table>

---

## 📱 **Platform Support**

JetCo now supports multiple platforms through Kotlin Multiplatform:

| Platform | Support | Description |
|----------|---------|-------------|
| 🤖 **Android** | ✅ | Full support with Android-specific optimizations |
| 🍎 **iOS** | ✅ | Native iOS support through Compose Multiplatform |
| 💻 **JVM** | ✅ | Desktop applications (Windows, macOS, Linux) |
| 🌐 **JS** | ✅ | Web applications with Compose for Web |
| ⚡ **WASM** | ✅ | WebAssembly support for high-performance web apps |

---

## 🚀 **Installation**

### **For Android Projects (Legacy)**
Add this to your module's `build.gradle.kts`:

```kotlin
dependencies {
    implementation("com.developerstring.jetco:ui:1.0.0-beta.6")
}
```

### **For Kotlin Multiplatform Projects**
Add this to your `commonMain` dependencies in `build.gradle.kts`:

```kotlin
commonMain.dependencies {
    implementation("com.developerstring.jetco-kmp:ui:1.0.0-beta.7")
}
```

### **Complete KMP Setup Example**

```kotlin
kotlin {
    androidTarget()
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    jvm("desktop")
    
    js(IR) {
        moduleName = "composeApp"
        browser {
            commonWebpackConfig {
                outputFileName = "composeApp.js"
            }
        }
        binaries.executable()
    }
    
    wasmJs {
        moduleName = "composeApp"
        browser {
            commonWebpackConfig {
                outputFileName = "composeApp.js"
            }
        }
        binaries.executable()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            
            // Add JetCo KMP
            implementation("com.developerstring.jetco-kmp:ui:1.0.0-beta.7")
        }
    }
}
```

---

## 📊 **Supported UI Components**

JetCo offers a growing lineup of UI components that make your apps stand out:

| 🧁 Component                  | 🍭 Description                                                                                 |
| ----------------------------- | -------------------------------------------------------------------------------------------- |
| **Pie Chart**                 | Slice and dice your data into a tasty pie chart format. 🥧                                   |
| **Column Bar Chart**          | Perfect for showing data as bars with full customization. 📊                                 |
| **Extended Column Bar Chart** | Take your bar charts to the next level with advanced features. 🔥                            |
| **Group Column Bar Chart**    | Compare multiple data sets side by side. 🤓                                                  |
| **TicketCard**                | Custom ticket-style card with cutout arcs, dashed dividers, and flexible slots. 🎟️          |
| **VerticalStepper**           | A vertical timeline/stepper with titles, descriptions, and optional images in each node. ⬇️ |
| **HorizontalStepper**         | A clean and simple horizontal stepper for progress or timeline representation. ➡️           |
| **CompactHorizontalStepper**  | A minimal, icon-only horizontal stepper for compact UIs (great for mobile). ⚡               |
| **SwitchButton**              | A customizable and animated switch button composable. 🔘                                      |
  
---

## 🚀 **Getting Started**

Want to get started right away? We've got your back! 🏃‍♂️

### **Quick Start with KMP**
```kotlin
@Composable
fun MyAwesomeScreen() {
    // Use any JetCo component across all platforms!
    PieChart(
        data = listOf(
            PieChartData("Android", 40f, Color.Green),
            PieChartData("iOS", 30f, Color.Blue), 
            PieChartData("Web", 20f, Color.Red),
            PieChartData("Desktop", 10f, Color.Yellow)
        )
    )
}
```

### **Import Components**
```kotlin
// For KMP projects
import com.developerstring.jetco_kmp.charts.piechart.PieChart
import com.developerstring.jetco_kmp.cards.ticket.TicketCard
import com.developerstring.jetco_kmp.components.stepper.VerticalStepper

// For Android-only projects  
import com.developerstring.jetco.ui.charts.piechart.PieChart
import com.developerstring.jetco.ui.cards.ticket.TicketCard
import com.developerstring.jetco.ui.components.stepper.VerticalStepper
```

Head over to the <a href="https://jetco.developerstring.com" target="_blank" rel="noopener noreferrer">JetCo Docs</a> for detailed installation guides, sample code, and customization options for all platforms. ⏱

---

## 😎 **Why Choose JetCo?**

* **Easy to use**. No steep learning curve.
* **Fast & lightweight**. Your app stays smooth. 🧈
* **Beautiful & customizable** components.

Whether it's a hobby project or the next unicorn 🦄 — JetCo helps you craft jaw-dropping UIs that work everywhere.

---

## 🚀 **What Makes JetCo Special?**

* 🌐 **Kotlin Multiplatform Support:** One codebase for Android, iOS, Web, Desktop, and WASM.
* 🖌 **Modern UI Components:** Charts, ticket cards, steppers, and more to bring your app to life.
* ⚡️ **Seamless Compose Integration:** Works flawlessly with Jetpack Compose and Compose Multiplatform.
* 🛠 **Customization Galore:** Tune every detail to fit your design across all platforms.
* 🚀 **Performance Optimized:** Lightweight and blazing fast on every platform.
* 📱 **Platform-Specific Optimizations:** Tailored experiences for each target platform.

---

## 🤝 **Contribute to JetCo**

Join the community and help shape JetCo! 👩‍💻👨‍💻
Check out the [Community](https://jetco.developerstring.com/community) section for contribution details.

---


