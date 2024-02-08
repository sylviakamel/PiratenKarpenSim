# A1 - Piraten Karpen

  * Author: Sylvia Kamel
  * Email: kamels3@mcmaster.ca

## Build and Execution

  * To clean your working directory:
    * `mvn clean`
  * To compile the project:
    * `mvn compile`
  * To run the project in development mode:
    * `mvn -q exec:java` (here, `-q` tells maven to be _quiet_)
  * To package the project as a turn-key artefact:
    * `mvn package`
  * To run the packaged delivery:
    * `java -jar target/piraten-karpen-jar-with-dependencies.jar` 

Remark: **We are assuming here you are using a _real_ shell (e.g., anything but PowerShell on Windows)**

## Feature Backlog

 * Status: 
   * Pending (P), Started (S), Blocked (B), Done (D)
 * Definition of Done (DoD):
   * My feature is done when I know I have interacted with the user to complete the objective of the software. In other words, it should bring something to the user and benefit them in some way. 

### Backlog 

| MVP? | Id  | Feature  | Status  |  Started  | Delivered |
| :-:  |:-:  |---       | :-:     | :-:       | :-:       |
| yes  | F01 | Roll eight die |  D | 25/01/23 | 25/01/23 |
| yes  | F02 | Reroll die that are not skulls  |  D (F01) | 25/01/23 | 28/01/23
| yes  | F03 | Mark beginning of each game |  D  | 30/01/23 | 30/01/23
| yes  | F04 | Accomodate 2 players | D | 29/01/23 | 29/01/23
| yes  | F05 | Stop rerolling if they have 3 skulls | D (F02) | 28/01/23 | 28/01/23
| yes  | F06 | Calculate points from gold and diamonds | D (F04) | 28/01/23 | 29/01/23
| yes  | F07 | Run  42 games |  D | 31/01/23 | 01/02/23
| yes  | F08 | Create 2 different strategies |  D | 01/02/23 | 01/02/23
| ... | ... | ... |

