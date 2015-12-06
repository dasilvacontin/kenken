User
====
username: String
password: String

Board
=====
size: Natural
matrix: Array[Array[Natural]]
regions: Array[Array[Natural]]

Region
=====
cells: List[(Natural, Natural]
op: RegionOperator

enum RegionOperator
===================
addition
substraction
multiplication
division
