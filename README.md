# BlackJack - DABD
<a name="readme-top"></a>
## Integrantes:
* 113231 - Barrera Dominici, Juan Cruz 
* 112733 - Gimenez, Joaquin
* 112962 - Gonzalo, Nicolas



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Tabla de contenidos</summary>
  <ol>
    <li>
      <a href="#about-the-project">Acerca del proyecto</a>
      <ul>
        <li><a href="#built-with">Construido con</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Empecemos</a>
      <ul>
        <li><a href="#prerequisites">Pre-Requisitos</a></li>
        <li><a href="#installation">Instalación</a></li>
      </ul>
    </li>
    <li><a href="#contact">Contactos</a></li>

  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## Acerca del proyecto
<a name="about-the-project"></a>

Se trata de un simple blackjack, modalidad de un solo jugador, contra un NPC que hace las veces de crupier.
Este juego tiene como características, empezar una nueva partida, pedir cartas, pasar y resolver partidas, todo persistiendo los datos y posibilitando con esto, retomar partidas sin finalizar.

El proyecto forma parte de la materia DABD de la UTN-FRC (Cordoba - Argentina), y está a cargo del profesor **Serrano, Javier.**
<p align="right">(<a href="#readme-top">volver arriba</a>)</p>



### Construido con
<a name="built-with"></a>

* [![Angular][Angular.io]][Angular-url]
* [![Bootstrap][Bootstrap.com]][Bootstrap-url]
* [![CSS3][CSS3]][JQuery-url]
* [![JWT][JWT]][JWT-url]
* [![SPRING][SPRING]][SPRING-url]
* [![MYSQL][MYSQL]][MYSQL-url]

<p align="right">(<a href="#readme-top">volver arriba</a>)</p>



<!-- GETTING STARTED -->
## Empecemos
<a name="getting-started"></a>
A continuación detallaremos como inicializar el proyecto de manera local, en futuras entregas puede que lo hostiemos para evitarles molestias.

### Pre-Requisitos
<a name="prerequisites"></a>
Esta es una lista de frameworks que deberá tener instalados para inicializar el proyecto Angular.

**Windows**

1. Entrar en https://nodejs.org/es/download/ y descargar el instalador de Node.js en el sistema operativo deseado. Podemos elegir entre Windows, Mac y Linux.

2. Ejecutar el instalador que acabamos de descargar. Simplemente debemos avanzar en el proceso de instalación.

3. Una vez finalizado el proceso de instalación, podemos comprobar fácilmente si se nos ha instalado correctamente. Para ello, vamos al intérprete de comandos de nuestro ordenador (en Windows, por ejemplo, escribir “cmd” en la barra de búsqueda y abrir la aplicación de “Símbolo del sistema”).

4. En la ventana de comandos, escribir node -v y pulsar la tecla Enter. Nos debería aparecer la versión que tenemos instalada de Node.js. Para comprobar que se nos ha instalado también NPM, escribiremos npm -v y pulsaremos de nuevo Enter. Nos debería aparecer también en este caso la versión del Node Package Manager.

5. Instalamos Angular desde CMD:
   ```sh
   npm install @angular/cli
   ```

**Ubuntu**
1. Antes de nada vamos a tener que asegurarnos de que todos los paquetes de nuestro sistema estén actualizados. Para hacerlo solo habrá que abrir una terminal (Ctrl+Alt+T) y ejecutar la siguiente secuencia de comandos:
   ```sh
   sudo apt update; sudo apt upgrade
   ```
2. Antes de proceder a la instalación de Angular en Ubuntu 18.04, vamos a tener que instalar NodeJs y Node Package Manager (NPM). Para hacerlo en la misma terminal ejecutaremos las siguientes líneas:
    ```sh
   curl -sL https://deb.nodesource.com/setup_10.x | sudo -E bash -
   ```

   ```sh
   sudo apt-get install -y nodejs
   ```
3. Tras finalizar la instalación de NodeJs procedemos a la instalación de NPM:
   ```sh
   	
    sudo npm install npm@latest -g
   ```
4. Ahora instalaremos la CLI angular usando NPM:
   ```sh
   	
    sudo npm install -g @angular/cli    
   ```
### Instalacion
<a name="installation"></a>
1. Clona el repo
   ```sh
   git clone https://github.com/JoaquinGimenez-112733/BlackJack.git
   ```
2. Necesitas crear la base de datos en MySQL, para ello:
    2.1. Abrir algun editor de scripts-sql, como MySQL Workbench o DBeaver en donde tengas una conexion hecha al localhost:3306, y crear una base de datos:
    ```sh   	
    CREATE DATABASE BlackJack;   
   ```
   2.2. Selecciona la base de datos recien creada y ejecuta el script que está adjunto en nuestro repo: https://github.com/JoaquinGimenez-112733/BlackJack/blob/main/back/sql-scripts/tablas.sql
   2.3. En nuestro repo clonado, cambiaremos las credenciales de nuestro proyecto, por las tuyas en ```BlackJackController.java``` en el método ```abrirConexion```:
    ```sh   	
    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BlackJack", "claselab4", "123456");   
   ```
      En el segundo y tercer argumento, donde dice ```claselab4``` y ```123456``` insertaremos nuestro usuario y password de nuestra instancia de MySQL.

<p align="right">(<a href="#readme-top">volver arriba</a>)</p>


<!-- CONTACT -->
## Contactos
<a name="contacts"></a>

Gonzalo, Nicolas - 112962@tecnicatura.frc.utn.edu.ar
Barrera Dominici, Juan Cruz - 113231@tecnicatura.frc.utn.edu.ar
 Gimenez, Joaquin - 112733@tecnicatura.frc.utn.edu.ar


<p align="right">(<a href="#readme-top">volver arriba</a>)</p>




<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->

[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/linkedin_username

[Angular.io]: https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white
[Angular-url]: https://angular.io/

[Bootstrap.com]: https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white
[Bootstrap-url]: https://getbootstrap.com
[JQuery.com]: https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white
[JQuery-url]: https://jquery.com 
[CSS3]:https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white
[CSS3-url]: https://www.w3schools.com/css/
[JWT]:https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens
[JWT-url]:https://jwt.io/
[Spring]:https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white
[Spring-url]:https://spring.io/
[MySQL]:https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white
[MySQL-url]:https://www.mysql.com/


