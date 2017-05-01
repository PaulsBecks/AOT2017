# Aufgabe 1 - Ameisen

Das ist ein Maven Projekt.

## Problem beschreibung

In dieser Aufgabe sollen Agenten in einem Graphen nach Nahrung suchen, 
kürzesten weg finden und die Nahrung zu ihrem Nest zurück bringen. 

## Visualisierung

Mit [GraphStream](http://graphstream-project.org/) lassen sich Graphen plotten. Phreomone werden als Label an den Kanten dargestellt. 
Die Nahrung auf einem Feld als Label an einem Knoten.
Es ist noch zu überlegen wie die einzelnen Agenten dargestellt werden.

## Features

Ameisen können:
* Herumlaufen 
* Essen zurück bringen
* Pheromone legen
* Der stärksten Pheromonspur folgen
* visualisiert werden
* abbrechen wenn das gesammte Essen eingesammelt wurde

## TODO

Ameisen müssen noch lernen
* nicht immer der stärksten Pheromonspur zu folgen
* unterschiedliche Pheromone legen für Nahrung gefunden/gesucht
* ihre pheromone erst dann zu verstreuen wenn alle Ameisen gelaufen sind.
