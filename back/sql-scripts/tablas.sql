

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

