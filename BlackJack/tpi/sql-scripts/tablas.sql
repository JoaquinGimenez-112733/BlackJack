

CREATE TABLE usuario (
	id INT auto_increment PRIMARY KEY
	,usuario VARCHAR(50) NOT NULL
	,password VARCHAR(50) NOT NULL
	)

CREATE TABLE partidas (
	id INT auto_increment PRIMARY KEY
	,puntajeUsuario INT
	,puntajeCompu INT
	,puntajeOcultoCompu INT
	,mazo JSON NOT NULL
	,manoUsuario JSON NOT NULL
	,manoCompu JSON NOT NULL
	,idUsuario INT REFERENCES usuarios(id)
	,finalizada bool
	)

INSERT INTO partidas (
	puntajeUsuario
	,puntajeCompu
	,mazo
	,manoUsuario
	,manoCompu
	,idUsuario
	)
VALUES (
	13
	,19
	,'[{"numero":"7","palo":"spades","valor":"7","orden":"5"},{"numero":"J","palo":"hearts","valor":"10","orden":"6"}]'
	,'[{"numero":"7","palo":"spades","valor":"7","orden":"5"},{"numero":"J","palo":"hearts","valor":"10","orden":"6"}]'
	,'[{"numero":"7","palo":"spades","valor":"7","orden":"5"},{"numero":"J","palo":"hearts","valor":"10","orden":"6"}]'
	,1
	)

