# How to build the project

Pre-requisites:
---
- Kotlin 2
- Maven 3
- GraalVM Community Edition 25.0.2

Quick Start
---
The easiest way to get started is to install [IntelliJ IDEA](https://www.jetbrains.com/idea/) which makes it super easy for you to handle all the above requisites:
- Comes pre-installed with Kotlin and Maven.
- Can easily download any JDK you would need, included GraalVM.
- You'll be able to run Maven commands with an easy-to-use interface.

So first, download and install IntelliJ IDEA, then git clone this project and open it with IDEA.

Then, Sync your Maven project by clicking on the "m" icon that is located on the right toolbar, then click the refresh icon that reads "Sync All Maven Projects".

To build all artifacts (Uber JAR, EXE), in this same Maven menu, open up the Lifecycle folder and run the task named "package". If build task succeeds, then you can find the artifacts in ./target
