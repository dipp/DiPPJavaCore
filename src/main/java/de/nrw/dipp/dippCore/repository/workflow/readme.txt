workflow consists of activities, transitions, states,
process, items

items are typical complex objects
a simple object is a special type of a complex object
a complex object is defined by its content model

a process is a transaction
a transition is a change from one state to another state

a activity means a job or operation

example: 
an item is in state "a".
there is a job "x" do be done, means
the job "x" will be processed.
during the process there is state transition from "a" to "b".
after the job is completed, the item is in state "b" or there might
be an exception case.

What is the Repository-Workflow?
- the repository-workflow is a definition of
  - how a digital object can be created
  - how a persistent identifier can be assigned
  - wether and how content can be converted and transformed
  - what parts of a digital object can be published and accessed
  - management of digital object datastreams (add, remove, modify)
  
Activities (Klasse): - Zuweisung persistenter Identifier
              Parameter: - mehrteilig, versionieren
            - Konvertierung und Transformation von Inhalten
            - Hinzufügen, Ändern, Entfernen von Datenströmen und weiteren digitalen Objekten
            - Statusverwaltung
            
Prozesse (Methoden):   - 
Status (Felder, Variable): - ? status hängt am Workflow oder am Item ?

___

steps: persIdent, conversion, metadata, publish, version