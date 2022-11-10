CREATE DATABASE blackjack;

USE blackjack;

CREATE TABLE usuario (
	id INT auto_increment PRIMARY KEY
	,usuario VARCHAR(50) NOT NULL
	,password VARCHAR(50) NOT NULL
	);

CREATE TABLE partidas (
	id INT auto_increment PRIMARY KEY
	,puntajeUsuario INT
	,puntajeCompu INT
	,puntajeOcultoCompu INT
	,mazo JSON NOT NULL
	,manoUsuario JSON NOT NULL
	,manoCompu JSON NOT NULL
	,idUsuario INT REFERENCES usuario(id)
	,fechaInicio TIMESTAMP DEFAULT current_timestamp
	,win bool
	,lose bool
	,tie bool
	,finalizada bool
	);

INSERT INTO usuario (
	id
	,usuario
	,password
	)
VALUES (
	1
	,"Joaquin"
	,"123"
	);

INSERT INTO usuario (
	id
	,usuario
	,password
	)
VALUES (
	2
	,"Juan"
	,"123"
	);

INSERT INTO usuario (
	id
	,usuario
	,password
	)
VALUES (
	3
	,"Nicolas"
	,"123"
	);

INSERT INTO partidas (
	puntajeUsuario
	,puntajeCompu
	,puntajeOcultoCompu
	,mazo
	,manoUsuario
	,manoCompu
	,idUsuario
	,fechaInicio
	,win
	,lose
	,tie
	,finalizada
	)
VALUES (
	16
	,17
	,4
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "diamonds", "orden": 1, "valor": 3, "numero": "3"}, {"palo": "diamonds", "orden": 3, "valor": 6, "numero": "6"}, {"palo": "spades", "orden": 5, "valor": 7, "numero": "7"}]'
	,'[{"palo": "spades", "orden": 2, "valor": 6, "numero": "6"}, {"palo": "clubs", "orden": 4, "valor": 4, "numero": "4"}, {"palo": "diamonds", "orden": 6, "valor": 7, "numero": "7"}]'
	,1
	,'2022-10-09'
	,0
	,1
	,0
	,1
	)
	,(
	17
	,20
	,10
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "spades", "orden": 1, "valor": 3, "numero": "3"}, {"palo": "diamonds", "orden": 3, "valor": 10, "numero": "Q"}, {"palo": "hearts", "orden": 5, "valor": 4, "numero": "4"}]'
	,'[{"palo": "hearts", "orden": 2, "valor": 10, "numero": "Q"}, {"palo": "clubs", "orden": 4, "valor": 10, "numero": "J"}]'
	,2
	,'2022-10-09'
	,0
	,1
	,0
	,1
	)
	,(
	21
	,20
	,10
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "spades", "orden": 1, "valor": 4, "numero": "4"}, {"palo": "diamonds", "orden": 3, "valor": 3, "numero": "3"}, {"palo": "spades", "orden": 5, "valor": 6, "numero": "6"}, {"palo": "hearts", "orden": 6, "valor": 8, "numero": "8"}]'
	,'[{"palo": "spades", "orden": 2, "valor": 10, "numero": "10"}, {"palo": "clubs", "orden": 4, "valor": 10, "numero": "10"}]'
	,3
	,'2022-10-09'
	,1
	,0
	,0
	,1
	)
	,(
	20
	,17
	,5
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "spades", "orden": 1, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 3, "valor": 3, "numero": "3"}, {"palo": "spades", "orden": 5, "valor": 1, "numero": "A"}, {"palo": "spades", "orden": 6, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 7, "valor": 8, "numero": "8"}]'
	,'[{"palo": "spades", "orden": 2, "valor": 2, "numero": "2"}, {"palo": "clubs", "orden": 4, "valor": 5, "numero": "5"}, {"palo": "hearts", "orden": 8, "valor": 10, "numero": "10"}]'
	,1
	,'2022-10-10'
	,1
	,0
	,0
	,1
	)
	,(
	23
	,19
	,0
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "spades", "orden": 1, "valor": 10, "numero": "K"}, {"palo": "hearts", "orden": 3, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 5, "valor": 10, "numero": "10"}]'
	,'[{"palo": "spades", "orden": 2, "valor": 10, "numero": "Q"}, {"palo": "spades", "orden": 4, "valor": 9, "numero": "9"}]'
	,1
	,'2022-10-11'
	,0
	,1
	,0
	,1
	)
	,(
	20
	,19
	,10
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "hearts", "orden": 1, "valor": 10, "numero": "K"}, {"palo": "spades", "orden": 3, "valor": 10, "numero": "J"}]'
	,'[{"palo": "diamonds", "orden": 2, "valor": 9, "numero": "9"}, {"palo": "diamonds", "orden": 4, "valor": 10, "numero": "10"}]'
	,1
	,'2022-10-12'
	,1
	,0
	,0
	,1
	)
	,(
	20
	,20
	,4
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "hearts", "orden": 1, "valor": 8, "numero": "8"}, {"palo": "diamonds", "orden": 3, "valor": 2, "numero": "2"}, {"palo": "clubs", "orden": 5, "valor": 10, "numero": "K"}]'
	,'[{"palo": "hearts", "orden": 2, "valor": 10, "numero": "Q"}, {"palo": "spades", "orden": 4, "valor": 4, "numero": "4"}, {"palo": "clubs", "orden": 6, "valor": 6, "numero": "6"}]'
	,1
	,'2022-10-13'
	,0
	,0
	,1
	,1
	)
	,(
	25
	,12
	,0
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "clubs", "orden": 1, "valor": 5, "numero": "5"}, {"palo": "hearts", "orden": 3, "valor": 10, "numero": "K"}, {"palo": "clubs", "orden": 5, "valor": 10, "numero": "J"}]'
	,'[{"palo": "clubs", "orden": 2, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 4, "valor": 9, "numero": "9"}]'
	,2
	,'2022-10-13'
	,0
	,1
	,0
	,1
	)
	,(
	19
	,19
	,9
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "spades", "orden": 1, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 3, "valor": 4, "numero": "4"}, {"palo": "clubs", "orden": 5, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 6, "valor": 7, "numero": "7"}]'
	,'[{"palo": "clubs", "orden": 2, "valor": 10, "numero": "K"}, {"palo": "spades", "orden": 4, "valor": 9, "numero": "9"}]'
	,1
	,'2022-10-13'
	,0
	,0
	,1
	,1
	)
	,(
	17
	,21
	,11
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "spades", "orden": 1, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 3, "valor": 2, "numero": "2"}, {"palo": "hearts", "orden": 5, "valor": 10, "numero": "10"}]'
	,'[{"palo": "diamonds", "orden": 2, "valor": 10, "numero": "10"}, {"palo": "diamonds", "orden": 4, "valor": 11, "numero": "A"}]'
	,1
	,'2022-10-14'
	,0
	,1
	,0
	,1
	)
	,(
	28
	,21
	,0
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "hearts", "orden": 1, "valor": 10, "numero": "Q"}, {"palo": "hearts", "orden": 3, "valor": 8, "numero": "8"}, {"palo": "diamonds", "orden": 5, "valor": 10, "numero": "J"}]'
	,'[{"palo": "diamonds", "orden": 2, "valor": 1, "numero": "A"}, {"palo": "diamonds", "orden": 4, "valor": 10, "numero": "K"}]'
	,1
	,'2022-10-17'
	,0
	,1
	,0
	,1
	)
	,(
	20
	,19
	,10
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "hearts", "orden": 1, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 3, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 5, "valor": 10, "numero": "K"}]'
	,'[{"palo": "spades", "orden": 2, "valor": 2, "numero": "2"}, {"palo": "clubs", "orden": 4, "valor": 10, "numero": "J"}, {"palo": "diamonds", "orden": 6, "valor": 7, "numero": "7"}]'
	,1
	,'2022-10-18'
	,1
	,0
	,0
	,1
	)
	,(
	25
	,18
	,0
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "clubs", "orden": 1, "valor": 2, "numero": "2"}, {"palo": "hearts", "orden": 3, "valor": 10, "numero": "Q"}, {"palo": "hearts", "orden": 5, "valor": 3, "numero": "3"}, {"palo": "spades", "orden": 6, "valor": 10, "numero": "J"}]'
	,'[{"palo": "diamonds", "orden": 2, "valor": 1, "numero": "A"}, {"palo": "clubs", "orden": 4, "valor": 7, "numero": "7"}]'
	,2
	,'2022-10-18'
	,0
	,1
	,0
	,1
	)
	,(
	18
	,21
	,11
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "diamonds", "orden": 1, "valor": 10, "numero": "10"}, {"palo": "clubs", "orden": 3, "valor": 8, "numero": "8"}]'
	,'[{"palo": "hearts", "orden": 2, "valor": 10, "numero": "Q"}, {"palo": "diamonds", "orden": 4, "valor": 11, "numero": "A"}]'
	,3
	,'2022-10-18'
	,0
	,1
	,0
	,1
	)
	,(
	20
	,26
	,6
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "clubs", "orden": 1, "valor": 10, "numero": "J"}, {"palo": "clubs", "orden": 3, "valor": 10, "numero": "K"}]'
	,'[{"palo": "spades", "orden": 2, "valor": 10, "numero": "Q"}, {"palo": "clubs", "orden": 4, "valor": 6, "numero": "6"}, {"palo": "spades", "orden": 5, "valor": 10, "numero": "K"}]'
	,3
	,'2022-10-18'
	,1
	,0
	,0
	,1
	)
	,(
	25
	,9
	,0
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "diamonds", "orden": 1, "valor": 5, "numero": "5"}, {"palo": "hearts", "orden": 3, "valor": 2, "numero": "2"}, {"palo": "clubs", "orden": 5, "valor": 8, "numero": "8"}, {"palo": "spades", "orden": 6, "valor": 10, "numero": "J"}]'
	,'[{"palo": "clubs", "orden": 2, "valor": 3, "numero": "3"}, {"palo": "diamonds", "orden": 4, "valor": 6, "numero": "6"}]'
	,1
	,'2022-10-18'
	,0
	,1
	,0
	,1
	)
	,(
	20
	,17
	,10
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "diamonds", "orden": 1, "valor": 10, "numero": "J"}, {"palo": "clubs", "orden": 3, "valor": 10, "numero": "10"}]'
	,'[{"palo": "clubs", "orden": 2, "valor": 4, "numero": "4"}, {"palo": "clubs", "orden": 4, "valor": 10, "numero": "K"}, {"palo": "clubs", "orden": 5, "valor": 3, "numero": "3"}]'
	,1
	,'2022-10-19'
	,1
	,0
	,0
	,1
	)
	,(
	20
	,21
	,11
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "spades", "orden": 1, "valor": 6, "numero": "6"}, {"palo": "hearts", "orden": 3, "valor": 4, "numero": "4"}, {"palo": "hearts", "orden": 5, "valor": 10, "numero": "10"}]'
	,'[{"palo": "spades", "orden": 2, "valor": 10, "numero": "10"}, {"palo": "spades", "orden": 4, "valor": 11, "numero": "A"}]'
	,3
	,'2022-10-19'
	,0
	,1
	,0
	,1
	)
	,(
	24
	,19
	,0
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "hearts", "orden": 1, "valor": 6, "numero": "6"}, {"palo": "clubs", "orden": 3, "valor": 8, "numero": "8"}, {"palo": "diamonds", "orden": 5, "valor": 10, "numero": "10"}]'
	,'[{"palo": "clubs", "orden": 2, "valor": 10, "numero": "K"}, {"palo": "diamonds", "orden": 4, "valor": 9, "numero": "9"}]'
	,1
	,'2022-10-20'
	,0
	,1
	,0
	,1
	)
	,(
	20
	,21
	,10
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "clubs", "orden": 1, "valor": 10, "numero": "Q"}, {"palo": "diamonds", "orden": 3, "valor": 10, "numero": "J"}]'
	,'[{"palo": "hearts", "orden": 2, "valor": 1, "numero": "A"}, {"palo": "hearts", "orden": 4, "valor": 10, "numero": "Q"}]'
	,1
	,'2022-10-21'
	,0
	,1
	,0
	,1
	)
	,(
	20
	,18
	,10
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "hearts", "orden": 1, "valor": 10, "numero": "K"}, {"palo": "diamonds", "orden": 3, "valor": 10, "numero": "Q"}]'
	,'[{"palo": "hearts", "orden": 2, "valor": 8, "numero": "8"}, {"palo": "clubs", "orden": 4, "valor": 10, "numero": "10"}]'
	,1
	,'2022-10-21'
	,1
	,0
	,0
	,1
	)
	,(
	19
	,18
	,10
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "diamonds", "orden": 1, "valor": 6, "numero": "6"}, {"palo": "spades", "orden": 3, "valor": 8, "numero": "8"}, {"palo": "hearts", "orden": 5, "valor": 5, "numero": "5"}]'
	,'[{"palo": "diamonds", "orden": 2, "valor": 4, "numero": "4"}, {"palo": "diamonds", "orden": 4, "valor": 10, "numero": "10"}, {"palo": "hearts", "orden": 6, "valor": 2, "numero": "2"}, {"palo": "clubs", "orden": 7, "valor": 2, "numero": "2"}]'
	,2
	,'2022-10-21'
	,1
	,0
	,0
	,1
	)
	,(
	27
	,16
	,0
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "diamonds", "orden": 1, "valor": 10, "numero": "10"}, {"palo": "hearts", "orden": 3, "valor": 4, "numero": "4"}, {"palo": "clubs", "orden": 5, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 6, "valor": 10, "numero": "Q"}]'
	,'[{"palo": "diamonds", "orden": 2, "valor": 6, "numero": "6"}, {"palo": "diamonds", "orden": 4, "valor": 10, "numero": "J"}]'
	,1
	,'2022-10-22'
	,0
	,1
	,0
	,1
	)
	,(
	10
	,19
	,10
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "hearts", "orden": 1, "valor": 3, "numero": "3"}, {"palo": "spades", "orden": 3, "valor": 2, "numero": "2"}, {"palo": "hearts", "orden": 5, "valor": 5, "numero": "5"}]'
	,'[{"palo": "clubs", "orden": 2, "valor": 9, "numero": "9"}, {"palo": "diamonds", "orden": 4, "valor": 10, "numero": "K"}]'
	,2
	,'2022-10-23'
	,0
	,1
	,0
	,1
	)
	,(
	21
	,20
	,10
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "hearts", "orden": 1, "valor": 3, "numero": "3"}, {"palo": "diamonds", "orden": 3, "valor": 10, "numero": "10"}, {"palo": "spades", "orden": 5, "valor": 1, "numero": "A"}, {"palo": "clubs", "orden": 6, "valor": 7, "numero": "7"}]'
	,'[{"palo": "clubs", "orden": 2, "valor": 10, "numero": "J"}, {"palo": "clubs", "orden": 4, "valor": 10, "numero": "Q"}]'
	,3
	,'2022-10-24'
	,1
	,0
	,0
	,1
	)
	,(
	17
	,17
	,4
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "spades", "orden": 1, "valor": 7, "numero": "7"}, {"palo": "clubs", "orden": 3, "valor": 10, "numero": "Q"}]'
	,'[{"palo": "clubs", "orden": 2, "valor": 10, "numero": "K"}, {"palo": "clubs", "orden": 4, "valor": 4, "numero": "4"}, {"palo": "hearts", "orden": 5, "valor": 3, "numero": "3"}]'
	,1
	,'2022-10-25'
	,0
	,0
	,1
	,1
	)
	,(
	20
	,18
	,7
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "hearts", "orden": 1, "valor": 10, "numero": "K"}, {"palo": "spades", "orden": 3, "valor": 10, "numero": "10"}]'
	,'[{"palo": "spades", "orden": 2, "valor": 1, "numero": "A"}, {"palo": "diamonds", "orden": 4, "valor": 7, "numero": "7"}]'
	,1
	,'2022-10-26'
	,1
	,0
	,0
	,1
	)
	,(
	17
	,20
	,8
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "diamonds", "orden": 1, "valor": 10, "numero": "K"}, {"palo": "spades", "orden": 3, "valor": 7, "numero": "7"}]'
	,'[{"palo": "clubs", "orden": 2, "valor": 7, "numero": "7"}, {"palo": "spades", "orden": 4, "valor": 8, "numero": "8"}, {"palo": "clubs", "orden": 5, "valor": 5, "numero": "5"}]'
	,2
	,'2022-10-27'
	,0
	,1
	,0
	,1
	)
	,(
	24
	,20
	,0
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "clubs", "orden": 1, "valor": 9, "numero": "9"}, {"palo": "clubs", "orden": 3, "valor": 5, "numero": "5"}, {"palo": "spades", "orden": 5, "valor": 10, "numero": "K"}]'
	,'[{"palo": "hearts", "orden": 2, "valor": 10, "numero": "K"}, {"palo": "diamonds", "orden": 4, "valor": 10, "numero": "K"}]'
	,3
	,'2022-10-27'
	,0
	,1
	,0
	,1
	)
	,(
	22
	,14
	,0
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "spades", "orden": 1, "valor": 2, "numero": "2"}, {"palo": "clubs", "orden": 3, "valor": 6, "numero": "6"}, {"palo": "clubs", "orden": 5, "valor": 4, "numero": "4"}, {"palo": "diamonds", "orden": 6, "valor": 10, "numero": "Q"}]'
	,'[{"palo": "diamonds", "orden": 2, "valor": 7, "numero": "7"}, {"palo": "spades", "orden": 4, "valor": 7, "numero": "7"}]'
	,1
	,'2022-10-29'
	,0
	,1
	,0
	,1
	)
	,(
	19
	,22
	,5
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "diamonds", "orden": 1, "valor": 10, "numero": "Q"}, {"palo": "hearts", "orden": 3, "valor": 9, "numero": "9"}]'
	,'[{"palo": "hearts", "orden": 2, "valor": 10, "numero": "10"}, {"palo": "hearts", "orden": 4, "valor": 5, "numero": "5"}, {"palo": "diamonds", "orden": 5, "valor": 7, "numero": "7"}]'
	,1
	,'2022-10-30'
	,1
	,0
	,0
	,1
	)
	,(
	16
	,20
	,10
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "spades", "orden": 1, "valor": 2, "numero": "2"}, {"palo": "spades", "orden": 3, "valor": 9, "numero": "9"}, {"palo": "spades", "orden": 5, "valor": 5, "numero": "5"}]'
	,'[{"palo": "spades", "orden": 2, "valor": 4, "numero": "4"}, {"palo": "spades", "orden": 4, "valor": 10, "numero": "K"}, {"palo": "spades", "orden": 6, "valor": 6, "numero": "6"}]'
	,1
	,'2022-10-31'
	,0
	,1
	,0
	,1
	)
	,(
	22
	,12
	,0
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "diamonds", "orden": 1, "valor": 7, "numero": "7"}, {"palo": "spades", "orden": 3, "valor": 4, "numero": "4"}, {"palo": "spades", "orden": 5, "valor": 2, "numero": "2"}, {"palo": "diamonds", "orden": 6, "valor": 9, "numero": "9"}]'
	,'[{"palo": "clubs", "orden": 2, "valor": 10, "numero": "Q"}, {"palo": "diamonds", "orden": 4, "valor": 2, "numero": "2"}]'
	,1
	,'2022-11-01'
	,0
	,1
	,0
	,1
	)
	,(
	18
	,20
	,10
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "clubs", "orden": 1, "valor": 7, "numero": "7"}, {"palo": "hearts", "orden": 3, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 5, "valor": 6, "numero": "6"}]'
	,'[{"palo": "diamonds", "orden": 2, "valor": 10, "numero": "J"}, {"palo": "clubs", "orden": 4, "valor": 10, "numero": "J"}]'
	,2
	,'2022-11-01'
	,0
	,1
	,0
	,1
	)
	,(
	28
	,12
	,0
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "spades", "orden": 1, "valor": 10, "numero": "K"}, {"palo": "hearts", "orden": 3, "valor": 8, "numero": "8"}, {"palo": "clubs", "orden": 5, "valor": 10, "numero": "K"}]'
	,'[{"palo": "clubs", "orden": 2, "valor": 10, "numero": "J"}, {"palo": "spades", "orden": 4, "valor": 2, "numero": "2"}]'
	,3
	,'2022-11-01'
	,0
	,1
	,0
	,1
	)
	,(
	22
	,11
	,0
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "spades", "orden": 1, "valor": 2, "numero": "2"}, {"palo": "hearts", "orden": 3, "valor": 10, "numero": "10"}, {"palo": "hearts", "orden": 5, "valor": 10, "numero": "K"}]'
	,'[{"palo": "spades", "orden": 2, "valor": 5, "numero": "5"}, {"palo": "spades", "orden": 4, "valor": 6, "numero": "6"}]'
	,1
	,'2022-11-01'
	,0
	,1
	,0
	,1
	)
	,(
	21
	,18
	,3
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "hearts", "orden": 1, "valor": 10, "numero": "Q"}, {"palo": "hearts", "orden": 3, "valor": 3, "numero": "3"}, {"palo": "spades", "orden": 5, "valor": 8, "numero": "8"}]'
	,'[{"palo": "spades", "orden": 2, "valor": 1, "numero": "A"}, {"palo": "spades", "orden": 4, "valor": 3, "numero": "3"}, {"palo": "diamonds", "orden": 6, "valor": 10, "numero": "Q"}, {"palo": "diamonds", "orden": 7, "valor": 4, "numero": "4"}]'
	,1
	,'2022-11-02'
	,1
	,0
	,0
	,1
	)
	,(
	17
	,19
	,5
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "spades", "orden": 1, "valor": 10, "numero": "Q"}, {"palo": "clubs", "orden": 3, "valor": 7, "numero": "7"}]'
	,'[{"palo": "spades", "orden": 2, "valor": 8, "numero": "8"}, {"palo": "spades", "orden": 4, "valor": 5, "numero": "5"}, {"palo": "spades", "orden": 5, "valor": 6, "numero": "6"}]'
	,3
	,'2022-11-03'
	,0
	,1
	,0
	,1
	)
	,(
	17
	,21
	,2
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "hearts", "orden": 1, "valor": 4, "numero": "4"}, {"palo": "diamonds", "orden": 3, "valor": 10, "numero": "Q"}, {"palo": "diamonds", "orden": 5, "valor": 3, "numero": "3"}]'
	,'[{"palo": "clubs", "orden": 2, "valor": 3, "numero": "3"}, {"palo": "clubs", "orden": 4, "valor": 2, "numero": "2"}, {"palo": "hearts", "orden": 6, "valor": 6, "numero": "6"}, {"palo": "clubs", "orden": 7, "valor": 1, "numero": "A"}, {"palo": "spades", "orden": 8, "valor": 9, "numero": "9"}]'
	,3
	,'2022-11-04'
	,0
	,1
	,0
	,1
	)
	,(
	16
	,17
	,6
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "spades", "orden": 1, "valor": 3, "numero": "3"}, {"palo": "clubs", "orden": 3, "valor": 1, "numero": "A"}, {"palo": "spades", "orden": 5, "valor": 10, "numero": "10"}, {"palo": "spades", "orden": 6, "valor": 2, "numero": "2"}]'
	,'[{"palo": "hearts", "orden": 2, "valor": 7, "numero": "7"}, {"palo": "clubs", "orden": 4, "valor": 6, "numero": "6"}, {"palo": "clubs", "orden": 7, "valor": 4, "numero": "4"}]'
	,1
	,'2022-11-05'
	,0
	,1
	,0
	,1
	)
	,(
	19
	,17
	,8
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "diamonds", "orden": 1, "valor": 2, "numero": "2"}, {"palo": "diamonds", "orden": 3, "valor": 10, "numero": "J"}, {"palo": "diamonds", "orden": 5, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 6, "valor": 4, "numero": "4"}]'
	,'[{"palo": "spades", "orden": 2, "valor": 9, "numero": "9"}, {"palo": "diamonds", "orden": 4, "valor": 8, "numero": "8"}]'
	,2
	,'2022-11-05'
	,1
	,0
	,0
	,1
	)
	,(
	17
	,23
	,5
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "hearts", "orden": 1, "valor": 3, "numero": "3"}, {"palo": "spades", "orden": 3, "valor": 10, "numero": "K"}, {"palo": "spades", "orden": 5, "valor": 4, "numero": "4"}]'
	,'[{"palo": "spades", "orden": 2, "valor": 10, "numero": "10"}, {"palo": "clubs", "orden": 4, "valor": 5, "numero": "5"}, {"palo": "spades", "orden": 6, "valor": 8, "numero": "8"}]'
	,3
	,'2022-11-05'
	,1
	,0
	,0
	,1
	)
	,(
	24
	,11
	,0
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "spades", "orden": 1, "valor": 8, "numero": "8"}, {"palo": "diamonds", "orden": 3, "valor": 10, "numero": "J"}, {"palo": "clubs", "orden": 5, "valor": 6, "numero": "6"}]'
	,'[{"palo": "clubs", "orden": 2, "valor": 2, "numero": "2"}, {"palo": "spades", "orden": 4, "valor": 9, "numero": "9"}]'
	,3
	,'2022-11-05'
	,0
	,1
	,0
	,1
	)
	,(
	28
	,14
	,0
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "hearts", "orden": 1, "valor": 8, "numero": "8"}, {"palo": "diamonds", "orden": 3, "valor": 10, "numero": "10"}, {"palo": "clubs", "orden": 5, "valor": 10, "numero": "Q"}]'
	,'[{"palo": "hearts", "orden": 2, "valor": 10, "numero": "K"}, {"palo": "clubs", "orden": 4, "valor": 4, "numero": "4"}]'
	,2
	,'2022-11-06'
	,0
	,1
	,0
	,1
	)
	,(
	18
	,17
	,8
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "clubs", "orden": 1, "valor": 8, "numero": "8"}, {"palo": "spades", "orden": 3, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 5, "valor": 5, "numero": "5"}]'
	,'[{"palo": "diamonds", "orden": 2, "valor": 9, "numero": "9"}, {"palo": "spades", "orden": 4, "valor": 8, "numero": "8"}]'
	,3
	,'2022-11-06'
	,1
	,0
	,0
	,1
	)
	,(
	19
	,21
	,2
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "spades", "orden": 1, "valor": 9, "numero": "9"}, {"palo": "spades", "orden": 3, "valor": 10, "numero": "10"}]'
	,'[{"palo": "spades", "orden": 2, "valor": 10, "numero": "K"}, {"palo": "spades", "orden": 4, "valor": 2, "numero": "2"}, {"palo": "diamonds", "orden": 5, "valor": 9, "numero": "9"}]'
	,1
	,'2022-11-06'
	,0
	,1
	,0
	,1
	)
	,(
	25
	,14
	,0
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "hearts", "orden": 1, "valor": 5, "numero": "5"}, {"palo": "diamonds", "orden": 3, "valor": 10, "numero": "J"}, {"palo": "spades", "orden": 5, "valor": 10, "numero": "Q"}]'
	,'[{"palo": "diamonds", "orden": 2, "valor": 10, "numero": "K"}, {"palo": "spades", "orden": 4, "valor": 4, "numero": "4"}]'
	,3
	,'2022-11-06'
	,0
	,1
	,0
	,1
	)
	,(
	22
	,21
	,0
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "hearts", "orden": 1, "valor": 5, "numero": "5"}, {"palo": "diamonds", "orden": 3, "valor": 7, "numero": "7"}, {"palo": "spades", "orden": 5, "valor": 10, "numero": "10"}]'
	,'[{"palo": "clubs", "orden": 2, "valor": 10, "numero": "J"}, {"palo": "diamonds", "orden": 4, "valor": 11, "numero": "A"}]'
	,3
	,'2022-11-07'
	,0
	,1
	,0
	,1
	)
	,(
	16
	,19
	,9
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "diamonds", "orden": 1, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 3, "valor": 3, "numero": "3"}, {"palo": "diamonds", "orden": 5, "valor": 10, "numero": "Q"}]'
	,'[{"palo": "diamonds", "orden": 2, "valor": 10, "numero": "10"}, {"palo": "clubs", "orden": 4, "valor": 9, "numero": "9"}]'
	,3
	,'2022-11-07'
	,0
	,1
	,0
	,1
	)
	,(
	15
	,19
	,8
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "hearts", "orden": 1, "valor": 10, "numero": "Q"}, {"palo": "hearts", "orden": 3, "valor": 2, "numero": "2"}, {"palo": "clubs", "orden": 5, "valor": 3, "numero": "3"}]'
	,'[{"palo": "diamonds", "orden": 2, "valor": 5, "numero": "5"}, {"palo": "spades", "orden": 4, "valor": 8, "numero": "8"}, {"palo": "spades", "orden": 6, "valor": 6, "numero": "6"}]'
	,2
	,'2022-11-07'
	,0
	,1
	,0
	,1
	)
	,(
	21
	,19
	,8
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "spades", "orden": 1, "valor": 1, "numero": "A"}, {"palo": "hearts", "orden": 3, "valor": 10, "numero": "Q"}]'
	,'[{"palo": "diamonds", "orden": 2, "valor": 2, "numero": "2"}, {"palo": "hearts", "orden": 4, "valor": 8, "numero": "8"}, {"palo": "hearts", "orden": 5, "valor": 4, "numero": "4"}, {"palo": "diamonds", "orden": 6, "valor": 5, "numero": "5"}]'
	,3
	,'2022-11-08'
	,1
	,0
	,0
	,1
	)
	,(
	19
	,20
	,10
	,'[{"palo": "hearts", "orden": 7, "valor": 3, "numero": "3"}, {"palo": "hearts", "orden": 8, "valor": 5, "numero": "5"}, {"palo": "clubs", "orden": 9, "valor": 7, "numero": "7"}, {"palo": "diamonds", "orden": 10, "valor": 8, "numero": "8"}]'
	,'[{"palo": "clubs", "orden": 1, "valor": 8, "numero": "8"}, {"palo": "clubs", "orden": 3, "valor": 1, "numero": "A"}]'
	,'[{"palo": "spades", "orden": 2, "valor": 10, "numero": "J"}, {"palo": "clubs", "orden": 4, "valor": 10, "numero": "Q"}]'
	,3
	,'2022-11-09'
	,0
	,1
	,0
	,1
	)