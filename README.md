# Compiladores
Interprete del lenguaje SQL utilizando un analizador decendente no recursivo.

## Gramantica
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

## Conjuntos Primero/Siguiente
### Primeros
P(Q) = {select}  
P(D) = {distinct, *, id}  
P(P) = {*, id}  
P(A) = {id}  
P(A1) = {, , ε}  
P(A2) = {id}  
P(A3) = {., ε}  
P(T) = {id}  
P(T1) = {, , ε}  
P(T2) = {id}  
P(T3) = {id, ε}  

### Siguientes
S(Q) = {$}  
S(D) = {from}  
S(P) = {from}  
S(A) = {from}  
S(A1) = {from}  
S(A2) = {, , from}  
S(A3) = {, , from}  
S(T) = {$}  
S(T1) = {$}  
S(T2) = {, , $}  
S(T3) = {, , $}  

## Tabla de análisis sintáctico 

|  |select|from|distinct|\* | ,|Id|.|\$ |
|-|-------|----|--------|--|--|--|-|--|
|Q|select D from T| | | | | | | |
|D| | |distinct P|P| |P|| |
|P	|					|		|				|*	|		|A		|		|		|
|A	|					|		|				|	|		|A2A1	|		|		|
|A1	|					|ε		|				|	|,A		|		|		|		|
|A2	|					|		|				|	|		|IdA3	|		|		|
|A3	|					|ε		|				|	|ε		|		|.Id	|		|
|T	|					|		|				|	|		|T2T1	|		|		|
|T1	|					|		|				|	|,T		|		|		|ε		|
|T2	|					|		|				|	|		|IdT3	|		|		|
|T3	|					|		|				|	|ε		|Id		|		|ε		|


