# Compiladores
Interprete del lenguaje SQL utilizando un analizador sintactico acendente no recursivo.

## Gramatica utilizada

1. Q    -> select D from T
2. D    -> distinct P 
3. D	-> P
4. P    -> *
5. P	->A
6. A    -> A2 A1
7. A1	-> , A 
8. A1	->ε
9. A2	-> id A3
10. A3	-> . id
11. A3	->ε
12. T    -> T2 T1
13. T1	-> , T
14. T1	->ε
15. T2 	-> id T3
16. T3 	-> id
17. T3	->ε

## Automata



```mermaid
---
title: Automata LR(0)
---
%%{init: {
	'theme':'dark'
}}%%
stateDiagram-v2
direction LR

I0 --> I1 : Q
I0 --> I2 : select

I1 --> aceptar : $

I2 --> I3 : D
I2 --> I4 : distinct
I2 --> I25 : P
I2 --> I6 : *
I2 --> I7 : A
I2 --> I8 : A2
I2 --> I9 : id

I3 --> I10 : from

I4 --> I5 : P
I4 --> I7 : A
I4 --> I8 : A2
I4 --> I9 : id

I8 --> I11 : A1
I8 --> I12 : ,

I9 --> I13 : A3
I9 --> I14 : .

I10 --> I15 : T
I10 --> I16 : T2
I10 --> I17 : id

I16 --> I18 : T1
I16 --> I19 : ,

I19 --> I20 : T
I19 --> I16 : T2
I19 --> I17 : id

I17 --> I21 : T3
I17 --> I22 : id

I12 --> I23 : A
I12 --> I8 : A2
I12 --> I9 : id

I14 --> I24 : id



I0 : I0
I0 : Q' -> .Q
I0 : Q -> .select D from T

I1 : I1
I1 : Q' -> Q.

I2 : I2
I2 : Q -> select .D from T
I2 : D -> .distinct P
I2 : D -> .P
I2 : P -> .*
I2 : P -> .A
I2 : A -> .A2 A1
I2 : A2 -> .id A3

I3 : I3
I3 : Q -> select D .from T

I4 : I4
I4 : D -> distinct .P
I4 : P -> .A
I4 : A -> .A2 A1
I4 : A2 -> .id A3

I5 : I5
I5 : D -> distinct P.

I6 : I6
I6 : P -> *.

I7 : I7
I7 : P -> A.

I8 : I8
I8 : A -> A2 .A1
I8 : A1 -> . ,A
I8 : A1 -> .ε

I9 : I9
I9 : A2 -> id .A3
I9 : A3 -> ..id
I9 : A3 -> ε

I10 : I10
I10 : Q -> select D from .T
I10 : T -> .T2 T1
I10 : T2 -> .id T3

I11 : I11
I11 : A -> A2 A1.

I12 : I12
I12 : A1 -> , .A
I12 : A -> .A2 A1
I12 : A2 -> .id A3

I23 : I23
I23 : A1 -> ,A.

I13 : I13
I13 : A2 -> id A3.

I14 : I14
I14 : A3 -> . .id

I24 : I24 
I24 : A3 -> .id.

I15 : I15
I15 : Q -> select D from T.

I16 : I16
I16 : T -> T2 .T1
I16 : T1 -> .,T
I16 : T1 -> ε

I18 : I18
I18 : T -> T2 T1.

I19 : I19
I19 : T1 -> , .T
I19 : T -> .T2 T1
I19 : T2 -> .id T3

I20 : I20
I20 : T1 -> ,T.

I17 : I17
I17 : T2 -> id .T3
I17 : T3 -> .id
I17 : T3 -> ε

I21 : I21
I21 : T2 -> id T3.

I22 : I22
I22 : T3 -> id.

I25 : D -> P.
```
