package blackjack.tpi;

import java.util.ArrayList;
import java.sql.*;

import Models.*;
import Models.Reportes.GraficoBarrasJugadores;
import Models.Reportes.GraficoBarrasPartidas;
import Models.Reportes.Rachas;
import Models.Reportes.TortaDer;
import Models.Reportes.TortaIzq;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.jsonwebtoken.Claims;

import java.util.Calendar;
import java.util.List;
import java.util.Date;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.HashSet;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class BlackJackController {

    ArrayList<Carta> nuevoMazo;
    ArrayList<Carta> manoJugador;
    ArrayList<Carta> manoCompu;

    int puntosJugador = 0;
    int puntosCompu = 0;
    int puntosOcultosCompu = 0;
    int userId = 0;

    int partida_id;

    private Mazo mazo;
    private String A = "A";

    private Connection conn;

    public BlackJackController() {
        mazo = new Mazo();
        manoJugador = new ArrayList<Carta>();
        manoCompu = new ArrayList<Carta>();
        nuevoMazo = new ArrayList<Carta>();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public LoginDTO login(@RequestBody Usuario us) {

        userId = 0;
        LoginDTO dto = new LoginDTO("");
        String token = "";

        Usuario userTarget = new Usuario();
        BeanUtils.copyProperties(us, userTarget);

        //VERIFICAMOS QUE EXISTA EN LA BD
        try {
            abrirConexion();

            String sql = "SELECT id FROM usuario WHERE usuario = ? AND password = ?;";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, userTarget.getUsuario());
            st.setString(2, userTarget.getPassword());

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                userId = rs.getInt(1);
                if (userId != 0) {
                    //VERIFICAMOS QUE EXISTA EN LA BD
                    token = generateToken(us);
                    dto.setToken(token);
                }
            }

            rs.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return dto;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/mazo")
    public manosPartidaDTO getMazo(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        Usuario us = getUsuarioFromToken(token);
        boolean validacion = validarUsuario(us);

        if (!validacion) {
            return null;
        }

        //nueva partida
        puntosJugador = 0;
        puntosCompu = 0;
        puntosOcultosCompu = 0;
        partida_id = 0;

        manoJugador.clear();
        manoCompu.clear();
        nuevoMazo.clear();
        nuevoMazo = mazo.nuevoMazo();
        //primer carta jugador
        Carta carta = nuevoMazo.get(0);

        if (carta.getNumero().equals(this.A)) {
            puntosJugador = 10;
        }

        puntosJugador = puntosJugador + carta.getValor();
        manoJugador.add(carta);
        nuevoMazo.remove(0);

        //primer carta compu
        carta = nuevoMazo.get(0);
        if (carta.getNumero().equals(this.A)) {
            puntosCompu = 10;
        }
        manoCompu.add(carta);
        nuevoMazo.remove(0);

        puntosCompu = puntosCompu + carta.getValor();

        //segunda carta jugador
        carta = nuevoMazo.get(0);
        if (puntosJugador < 11 && carta.getNumero().equals(this.A)) {
            puntosJugador += 10;
        }
        manoJugador.add(carta);
        nuevoMazo.remove(0);

        puntosJugador = puntosJugador + carta.getValor();

        //segunda carta compu
        carta = nuevoMazo.get(0);
        if (puntosCompu < 11 && carta.getNumero().equals(this.A)) {
            puntosOcultosCompu += 11;
            carta.setValor(11);
        } else {
            puntosOcultosCompu = carta.getValor();
        }
        manoCompu.add(carta);
        nuevoMazo.remove(0);

        manosPartidaDTO dto = new manosPartidaDTO(nuevoMazo, manoJugador, manoCompu, puntosJugador, puntosCompu, puntosOcultosCompu);

        Gson gson = new Gson();
        String mazoStrJson = gson.toJson(nuevoMazo);
        String jugadorStrJson = gson.toJson(manoJugador);
        String compuStrJson = gson.toJson(manoCompu);

        String msj = "";
        try {
            abrirConexion();

            String sql = "INSERT INTO partidas(puntajeUsuario, puntajeCompu,puntajeOcultoCompu, mazo, manoUsuario, manoCompu, idUsuario, finalizada) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, puntosJugador);
            st.setInt(2, puntosCompu);
            st.setInt(3, puntosOcultosCompu);
            st.setString(4, mazoStrJson);
            st.setString(5, jugadorStrJson);
            st.setString(6, compuStrJson);
            st.setInt(7, userId);
            st.setBoolean(8, false);

            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.next();
            partida_id = rs.getInt(1);
            msj = "Partida registrado exitosamente";

            rs.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            msj = "No se pudo registrar su partida";
        } finally {
            cerrarConexion();
        }

        return dto;

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/pedir")
    public manosPartidaDTO pedirCarta(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        Usuario us = getUsuarioFromToken(token);
        boolean validacion = validarUsuario(us);
        boolean existeA = false;
        if (!validacion) {
            return null;
        }

        Carta carta = nuevoMazo.get(0);
        manoJugador.add(carta);
        nuevoMazo.remove(0);

        this.puntosJugador = 0;
        for (Carta cj : manoJugador) {
            puntosJugador += cj.getValor();
        }
        for (Carta cj : manoJugador) {

            if (carta.getNumero().equals(this.A)) {
                existeA = true;
            }
        }
        if (existeA == true && puntosJugador < 12) {
            puntosJugador += 10;
        }

        //UPDATEAMOS LA PARTIDA EN BD
        Gson gson = new Gson();
        String mazoStrJson = gson.toJson(nuevoMazo);
        String jugadorStrJson = gson.toJson(manoJugador);
        String compuStrJson = gson.toJson(manoCompu);
        String msj = "";

        try {
            abrirConexion();

            String sql = "UPDATE partidas SET puntajeUsuario = ?, puntajeCompu = ?, puntajeOcultoCompu = ?, mazo = ?, manoUsuario = ?, manoCompu = ?, idUsuario = ?, finalizada = ? WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setInt(1, puntosJugador);
            st.setInt(2, puntosCompu);
            st.setInt(3, puntosOcultosCompu);
            st.setString(4, mazoStrJson);
            st.setString(5, jugadorStrJson);
            st.setString(6, compuStrJson);
            st.setInt(7, userId);
            st.setBoolean(8, false);
            st.setInt(9, partida_id);

            st.executeUpdate();

            st.close();

            msj = "Partida registrado exitosamente";

        } catch (SQLException ex) {
            ex.printStackTrace();
            msj = "No se pudo registrar su partida";
        } finally {
            cerrarConexion();
        }

        manosPartidaDTO dto = new manosPartidaDTO(nuevoMazo, manoJugador, manoCompu, puntosJugador, puntosCompu, puntosOcultosCompu);
        return dto;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/pasar")
    public manosPartidaDTO pasar(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        Usuario us = getUsuarioFromToken(token);
        boolean validacion = validarUsuario(us);
        boolean existeA = false;
        if (!validacion) {
            return null;
        }

        puntosCompu += puntosOcultosCompu;

        while (puntosCompu < 17) {

            Carta carta = nuevoMazo.get(0);
            manoCompu.add(carta);
            nuevoMazo.remove(0);

            this.puntosCompu = 0;
            for (Carta cc : manoCompu) {
                puntosCompu += cc.getValor();
            }
            for (Carta cj : manoCompu) {

                if (carta.getNumero().equals(this.A)) {
                    existeA = true;
                }
            }
            if (existeA == true && puntosCompu < 12) {
                puntosCompu += 10;
            }

        }
        //UPDATEAMOS LA PARTIDA EN BD AL FINALIZAR
        Gson gson = new Gson();
        String mazoStrJson = gson.toJson(nuevoMazo);
        String jugadorStrJson = gson.toJson(manoJugador);
        String compuStrJson = gson.toJson(manoCompu);

        boolean win = false;
        boolean lose = false;
        boolean tie = false;
        String msj = "";

        if (puntosCompu > puntosJugador && puntosCompu <= 21) {
            win = false;
            lose = true;
            tie = false;
        } else if (puntosCompu < puntosJugador || puntosCompu > 21) {
            win = true;
            lose = false;
            tie = false;
        } else if (puntosCompu == puntosJugador) {
            win = false;
            lose = false;
            tie = true;
        }
        try {
            abrirConexion();

            String sql = "UPDATE partidas SET puntajeUsuario = ?, puntajeCompu = ?, puntajeOcultoCompu = ?, mazo = ?, manoUsuario = ?, manoCompu = ?, idUsuario = ?, win = ?, lose = ?, tie = ?, finalizada = ? WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setInt(1, puntosJugador);
            st.setInt(2, puntosCompu);
            st.setInt(3, puntosOcultosCompu);
            st.setString(4, mazoStrJson);
            st.setString(5, jugadorStrJson);
            st.setString(6, compuStrJson);
            st.setInt(7, userId);
            st.setBoolean(8, win);
            st.setBoolean(9, lose);
            st.setBoolean(10, tie);
            st.setBoolean(11, true);
            st.setInt(12, partida_id);

            st.executeUpdate();
            st.close();
            msj = "Partida registrado exitosamente";

        } catch (SQLException ex) {
            ex.printStackTrace();
            msj = "No se pudo registrar su partida";
        } finally {
            cerrarConexion();
        }

        manosPartidaDTO dto = new manosPartidaDTO(nuevoMazo, manoJugador, manoCompu, puntosJugador, puntosCompu, puntosOcultosCompu);
        return dto;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/partida-en-curso")
    public manosPartidaDTO getPartidaEnCurso(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        manosPartidaDTO mazo = new manosPartidaDTO();

        Usuario us = getUsuarioFromToken(token);

        try {
            abrirConexion();

            String sql = "SELECT id FROM usuario WHERE usuario = ? AND password = ?;";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, us.getUsuario());
            st.setString(2, us.getPassword());

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                userId = rs.getInt(1);
                if (userId != 0) {
                    try {
                        abrirConexion();

                        String sqlPartida = "SELECT id, puntajeUsuario, puntajeCompu,puntajeOcultoCompu, mazo, manoUsuario, manoCompu FROM partidas WHERE idUsuario = ? AND finalizada = false ORDER BY id DESC LIMIT 1;";
                        PreparedStatement stP = conn.prepareStatement(sqlPartida);

                        stP.setInt(1, userId);

                        ResultSet rsPartida = stP.executeQuery();
                        if (rsPartida.next()) {
                            Gson gson = new Gson();
                            String mazoStrJson = gson.toJson(nuevoMazo);
                            String jugadorStrJson = gson.toJson(manoJugador);
                            String compuStrJson = gson.toJson(manoCompu);

                            partida_id = rsPartida.getInt(1);
                            puntosJugador = rsPartida.getInt(2);
                            puntosCompu = rsPartida.getInt(3);
                            puntosOcultosCompu = rsPartida.getInt(4);
                            mazoStrJson = rsPartida.getString(5);
                            nuevoMazo = gson.fromJson(mazoStrJson, new TypeToken<List<Carta>>() {
                            }.getType());

                            jugadorStrJson = rsPartida.getString(6);
                            manoJugador = gson.fromJson(jugadorStrJson, new TypeToken<List<Carta>>() {
                            }.getType());
                            compuStrJson = rsPartida.getString(7);
                            manoCompu = gson.fromJson(compuStrJson, new TypeToken<List<Carta>>() {
                            }.getType());
                            mazo = new manosPartidaDTO(nuevoMazo, manoJugador, manoCompu, puntosJugador, puntosCompu, puntosOcultosCompu);
                            mazo.setMazo(nuevoMazo);
                            mazo.setManoCompu(manoCompu);
                            mazo.setManoJugador(manoJugador);
                            mazo.setPuntosOcultosCompu(puntosOcultosCompu);
                            mazo.setPuntosCompu(puntosCompu);
                            mazo.setPuntosJugador(puntosJugador);
                        }
                        stP.close();
                        rsPartida.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } finally {
                        cerrarConexion();

                    }
                }

            }
            st.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return mazo;
    }

    // Metodos privados 
    private String abrirConexion() {
        String msj = "";
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BlackJack", "root", "nikolas06");
            msj = "Conexion exitosa!";
        } catch (SQLException ex) {
            ex.printStackTrace();
            msj = "No se pudo conectar a la base de datos";
        }

        return msj;
    }

    private String cerrarConexion() {
        String msj = "";

        try {
            conn.close();
            msj = "Conexion Cerrada";
        } catch (SQLException ex) {
            ex.printStackTrace();
            msj = "No se pudo cerrar";
        }

        return msj;

    }

    private String generateToken(Usuario usuario) {
        String secretKey = "pepito";
        Date createdDate = new Date();
        return Jwts.builder()
                .claim("user", usuario.getUsuario())
                .claim("password", usuario.getPassword())
                .setSubject(usuario.getUsuario()).setSubject(usuario.getPassword())
                .setIssuedAt(createdDate)
                .signWith(SignatureAlgorithm.HS512, secretKey).compact();
    }

    private Usuario getUsuarioFromToken(String token) {
        String secretKey = "pepito";

        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        Usuario usuario = new Usuario(claims.get("user").toString(), claims.get("password").toString());
        return usuario;
    }

    private boolean validarUsuario(Usuario us) {
        try {
            abrirConexion();

            String sql = "SELECT id FROM usuario WHERE usuario = ? AND password = ?;";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, us.getUsuario());
            st.setString(2, us.getPassword());

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return true;
            }
            st.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            cerrarConexion();
        }
        return false;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "usuarioExists")
    private ResponseEntity<Boolean> usuarioExists(@RequestParam String username) {
        try {
            abrirConexion();

            String sql = "SELECT id FROM usuario WHERE usuario = ?;";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, username);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return ResponseEntity.status(200).body(true);
            }
            st.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            cerrarConexion();
        }
        return ResponseEntity.status(400).body(null);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/perdiste")
    public manosPartidaDTO perdiste(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {

        Usuario us = getUsuarioFromToken(token);
        boolean validacion = validarUsuario(us);

        if (!validacion) {
            return null;
        }

        puntosCompu += puntosOcultosCompu;
        puntosOcultosCompu = 0;

        //UPDATEAMOS LA PARTIDA EN BD AL FINALIZAR
        Gson gson = new Gson();
        String mazoStrJson = gson.toJson(nuevoMazo);
        String jugadorStrJson = gson.toJson(manoJugador);
        String compuStrJson = gson.toJson(manoCompu);
        String msj = "";
        boolean win = false;
        boolean lose = true;
        boolean tie = false;
        try {
            abrirConexion();

            String sql = "UPDATE partidas SET puntajeUsuario = ?, puntajeCompu = ?, puntajeOcultoCompu = ?, mazo = ?, manoUsuario = ?, manoCompu = ?, idUsuario = ?, win = ?, lose = ?, tie = ?, finalizada = ? WHERE id = ?";
            PreparedStatement stLose = conn.prepareStatement(sql);

            stLose.setInt(1, puntosJugador);
            stLose.setInt(2, puntosCompu);
            stLose.setInt(3, puntosOcultosCompu);
            stLose.setString(4, mazoStrJson);
            stLose.setString(5, jugadorStrJson);
            stLose.setString(6, compuStrJson);
            stLose.setInt(7, userId);
            stLose.setBoolean(8, win);
            stLose.setBoolean(9, lose);
            stLose.setBoolean(10, tie);
            stLose.setBoolean(11, true);
            stLose.setInt(12, partida_id);

            stLose.executeUpdate();
            stLose.close();

            msj = "Partida registrado exitosamente";
            stLose.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            msj = "No se pudo registrar su partida";
        } finally {

            cerrarConexion();
        }

        manosPartidaDTO dto = new manosPartidaDTO(nuevoMazo, manoJugador, manoCompu, puntosJugador, puntosCompu, puntosOcultosCompu);
        return dto;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/signup")
    public int signup(@RequestBody Usuario us) {
        LoginDTO dto = new LoginDTO("");
        int response = 0;
        Usuario userTarget = new Usuario();
        BeanUtils.copyProperties(us, userTarget);

        //VERIFICAMOS QUE EXISTA EN LA BD
        try {
            abrirConexion();

            String sql = "SELECT id FROM usuario WHERE usuario = ? AND password = ?;";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, userTarget.getUsuario());
            st.setString(2, userTarget.getPassword());

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                response = 400;

            } else {

                try {
                    abrirConexion();
                    String sqlInsert = "INSERT INTO usuario(usuario, password) VALUES(?,?);";
                    PreparedStatement stInsert = conn.prepareStatement(sqlInsert);
                    stInsert.setString(1, userTarget.getUsuario());
                    stInsert.setString(2, userTarget.getPassword());

                    stInsert.execute();
                    response = 200;

                    stInsert.close();


                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            st.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }

        return response;
    }
    //REPORTES

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/reportesIndividuales")
    public ResponseEntity<EstadisticasDTO> getResultadosIndividuales() {
        
        Connection conn = null;
Statement st = null;
ResultSet rs = null;
        try {
            
            
            System.out.println(userId);
            abrirConexion();
            Rachas rachaV = new Rachas(0, 0, 0);
            Rachas rachaD = new Rachas(0, 0, 0);
            ;
            Rachas bj = new Rachas(0, 0, 0);
            Rachas partidasJugadas = new Rachas(0, 0, 0);
            //Rachas de victorias
            String sql = "SELECT idUsuario\n"
                    + "\t,MAX(cnt) AS valor\n"
                    + "\t,CASE \n"
                    + "\t\tWHEN MAX(cnt) >= 0\n"
                    + "\t\t\tAND MAX(cnt) <= 5\n"
                    + "\t\t\tTHEN 1\n"
                    + "\t\tWHEN MAX(cnt) > 5\n"
                    + "\t\t\tAND MAX(cnt) <= 10\n"
                    + "\t\t\tTHEN 2\n"
                    + "\t\tWHEN MAX(cnt) > 10\n"
                    + "\n"
                    + "\t\t\tTHEN 3\n"
                    + "\t\tEND AS tier\n"
                    + "FROM (\n"
                    + "\tSELECT idUsuario\n"
                    + "\t\t,COUNT(*) AS cnt\n"
                    + "\tFROM (\n"
                    + "\t\tSELECT idUsuario\n"
                    + "\t\t\t,id\n"
                    + "\t\t\t,win\n"
                    + "\t\t\t,SUM(CASE \n"
                    + "\t\t\t\t\tWHEN win <> true\n"
                    + "\t\t\t\t\t\tTHEN 1\n"
                    + "\t\t\t\t\tEND) OVER (\n"
                    + "\t\t\t\tPARTITION BY idUsuario ORDER BY id ROWS UNBOUNDED PRECEDING\n"
                    + "\t\t\t\t) AS DUMMY\n"
                    + "\t\tFROM partidas\n"
                    + "\t\t) dt\n"
                    + "\tWHERE win = true\n"
                    + "\t\tAND idusuario = ?\n"
                    + "\tGROUP BY idUsuario\n"
                    + "\t\t,DUMMY\n"
                    + "\t) dt\n"
                    + "GROUP BY idUsuario";

            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, userId);

            ResultSet rs = st.executeQuery();


            while (rs.next()) {
                int valor1 = rs.getInt(2);
                int tier1 = rs.getInt(3);
                float porcentaje = 0f;
                if (tier1 == 1) {
                    porcentaje = ((float) valor1 / (float) 5) * (float) 100;
                } else if (tier1 == 2) {
                    porcentaje = ((float) valor1 / (float) 10) * (float) 100;
                } else if (tier1 == 3) {
                    porcentaje = 100f;
                }

                rachaV = new Rachas(valor1, tier1, porcentaje);
            }

            st.close();
            rs.close();
            //Rachas de derrotas
            String sql2 = "SELECT idUsuario\n"
                    + "\t,MAX(cnt) AS valor\n"
                    + "\t,CASE \n"
                    + "\t\tWHEN MAX(cnt) >= 0\n"
                    + "\t\t\tAND MAX(cnt) <= 5\n"
                    + "\t\t\tTHEN 1\n"
                    + "\t\tWHEN MAX(cnt) > 5\n"
                    + "\t\t\tAND MAX(cnt) <= 10\n"
                    + "\t\t\tTHEN 2\n"
                    + "\t\tWHEN MAX(cnt) > 10\n"
                    + "\t\t\t\n"
                    + "\t\t\tTHEN 3\n"
                    + "\n"
                    + "\t\tEND AS tier\n"
                    + "FROM (\n"
                    + "\tSELECT idUsuario\n"
                    + "\t\t,COUNT(*) AS cnt\n"
                    + "\tFROM (\n"
                    + "\t\tSELECT idUsuario\n"
                    + "\t\t\t,id\n"
                    + "\t\t\t,lose\n"
                    + "\t\t\t,SUM(CASE \n"
                    + "\t\t\t\t\tWHEN lose <> true\n"
                    + "\t\t\t\t\t\tTHEN 1\n"
                    + "\t\t\t\t\tEND) OVER (\n"
                    + "\t\t\t\tPARTITION BY idUsuario ORDER BY id ROWS UNBOUNDED PRECEDING\n"
                    + "\t\t\t\t) AS DUMMY\n"
                    + "\t\tFROM partidas\n"
                    + "\t\t) dt\n"
                    + "\tWHERE lose = true\n"
                    + "\t\tAND idusuario = ?\n"
                    + "\tGROUP BY idUsuario\n"
                    + "\t\t,DUMMY\n"
                    + "\t) dt\n"
                    + "GROUP BY idUsuario";

            PreparedStatement st2 = conn.prepareStatement(sql2);
            st2.setInt(1, userId);

            ResultSet rs2 = st.executeQuery();

            while (rs2.next()) {
                int valor = rs2.getInt(2);
                int tier = rs2.getInt(3);

                float porcentaje = 0f;
                if (tier == 1) {
                    porcentaje = ((float) valor / (float) 5) * (float) 100;
                } else if (tier == 2) {
                    porcentaje = ((float) valor / (float) 10) * (float) 100;
                } else if (tier == 3) {
                    porcentaje = 100f;
                }

                rachaD = new Rachas(valor, tier, porcentaje);
            }

            st2.close();
            rs2.close();
            //Cantidad de BlackJacks
            String sql3 = "SELECT count(*)\n"
                    + "\t,CASE \n"
                    + "\t\tWHEN count(*) >= 0\n"
                    + "\t\t\tAND count(*) <= 10\n"
                    + "\t\t\tTHEN 1\n"
                    + "\t\tWHEN count(*) > 10\n"
                    + "\t\t\tAND count(*) <= 25\n"
                    + "\t\t\tTHEN 2\n"
                    + "\t\tWHEN count(*) > 25\n"
                    + "\n"
                    + "\t\t\tTHEN 3\n"
                    + "\t\tEND AS tier\n"
                    + "\n"
                    + "FROM partidas p\n"
                    + "WHERE p.finalizada = true\n"
                    + "\tAND p.puntajeUsuario = 21\n"
                    + "\tAND p.idUsuario = ?";

            PreparedStatement st3 = conn.prepareStatement(sql3);
            st3.setInt(1, userId);

            ResultSet rs3 = st3.executeQuery();

            while (rs3.next()) {
                int valor = rs3.getInt(1);
                int tier = rs3.getInt(2);

                float porcentaje = 0f;
                if (tier == 1) {
                    porcentaje = ((float) valor / (float) 10) * (float) 100;
                } else if (tier == 2) {
                    porcentaje = ((float) valor / (float) 25) * (float) 100;
                } else if (tier == 3) {
                    porcentaje = 100f;
                }

                bj = new Rachas(valor, tier, porcentaje);
            }
            st3.close();
            rs3.close();
            //Cantidad de Partidas Jugadas
            String sql4 = "SELECT count(*)\n"
                    + "\t,CASE \n"
                    + "\t\tWHEN count(*) >= 0\n"
                    + "\t\t\tAND count(*) <= 10\n"
                    + "\t\t\tTHEN 1\n"
                    + "\t\tWHEN count(*) > 10\n"
                    + "\t\t\tAND count(*) <= 25\n"
                    + "\t\t\tTHEN 2\n"
                    + "\t\tWHEN count(*) > 25\n"
                    + "\t\t\t\n"
                    + "\t\t\tTHEN 3\n"
                    + "\n"
                    + "\t\tEND AS tier\n"
                    + "FROM partidas p\n"
                    + "WHERE p.finalizada = true\n"
                    + "\tAND p.idUsuario = ?";

            PreparedStatement st4 = conn.prepareStatement(sql4);
            st4.setInt(1, userId);

            ResultSet rs4 = st4.executeQuery();

            while (rs4.next()) {
                int valor = rs4.getInt(1);
                int tier = rs4.getInt(2);

                float porcentaje = 0f;
                if (tier == 1) {
                    porcentaje = ((float) valor / (float) 10) * (float) 100;
                } else if (tier == 2) {
                    porcentaje = ((float) valor / (float) 25) * (float) 100;
                } else if (tier == 3) {
                    porcentaje = 100f;
                }
                partidasJugadas = new Rachas(valor, tier, porcentaje);
            }
            st4.close();
            rs4.close();

            EstadisticasDTO dto = new EstadisticasDTO(rachaV, rachaD, bj, partidasJugadas);
            return ResponseEntity.status(200).body(dto);

        } catch (SQLException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(200).body(null);
        } finally {
               try { rs.close(); } catch (Exception errorRS) { errorRS.printStackTrace(); }
    try { st.close(); } catch (Exception errorST) { errorST.printStackTrace(); }
    try { conn.close(); } catch (Exception errorCONN) { errorCONN.printStackTrace(); }
        }

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/tortaIzq")
    public ResponseEntity<TortaIzq> getTortaIzq() {

        try {
            abrirConexion();

            TortaIzq tortaIzq = new TortaIzq(0f, 0f);

            //Torta Izquierda - Porcentaje de BlackJacks
            String sql = "\t\n"
                    + "SELECT round(SUM(CASE \n"
                    + "\t\t\tWHEN p.puntajeUsuario = 21\n"
                    + "\t\t\t\tTHEN 1\n"
                    + "\t\t\tEND) / count(*) * 100, 2) as porcentajeJugador\n"
                    + "\t,round(SUM(CASE \n"
                    + "\t\t\tWHEN p.puntajeCompu = 21\n"
                    + "\t\t\t\tTHEN 1\n"
                    + "\t\t\tEND) / count(*) * 100,2) as porcentajeCompu\n"
                    + "FROM partidas p\n"
                    + "INNER JOIN usuario u ON p.idUsuario = u.id\n"
                    + "WHERE p.finalizada = true\n"
                    + "\tAND (\n"
                    + "\t\tpuntajeUsuario = 21\n"
                    + "\t\tOR puntajeCompu = 21\n"
                    + "\t\t)";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            float porcentajeJugador = 0;
            float porcentajeCompu = 0;

            while (rs.next()) {
                porcentajeJugador = rs.getFloat(1);
                porcentajeCompu = rs.getFloat(2);

            }
            tortaIzq = new TortaIzq(porcentajeJugador, porcentajeCompu);
            rs.close();
            st.close();

            return ResponseEntity.status(200).body(tortaIzq);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(400).body(null);
        } finally {
            cerrarConexion();
        }

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/tortaDer")
    public ResponseEntity<TortaDer> getTortaDer() {

        try {
            abrirConexion();

            TortaDer tortaDer = new TortaDer(0f, 0f, 0f);

            //Torta Derecha - Resultados en porcentajes
            String sql2 = "SELECT round(avg(win) * 100,2) as win\n"
                    + "\t,round(avg(lose) * 100,2) as lose\n"
                    + "\t,round(avg(tie) * 100,2) as tie\n"
                    + "FROM partidas p\n"
                    + "INNER JOIN usuario u ON p.idUsuario = u.id\n"
                    + "WHERE p.finalizada = true";

            Statement st2 = conn.createStatement();
            ResultSet rs2 = st2.executeQuery(sql2);
            float porcentajeWin = 0f;
            float porcentajeLose = 0f;
            float porcentajeTie = 0f;

            while (rs2.next()) {
                porcentajeWin = rs2.getFloat(1);
                porcentajeLose = rs2.getFloat(2);
                porcentajeTie = rs2.getFloat(3);

            }
            tortaDer = new TortaDer(porcentajeWin, porcentajeLose, porcentajeTie);
            rs2.close();
            st2.close();

            return ResponseEntity.status(200).body(tortaDer);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(400).body(null);
        } finally {
            cerrarConexion();
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/grafico-partidas")
    public ResponseEntity<GraficoBarrasPartidas> getGraficoPartidas() {
        try {
            abrirConexion();

            ArrayList<String> fechas = new ArrayList<String>();
            ArrayList<Integer> partidas = new ArrayList<Integer>();

            //Graficos de barra - Consulta para array de partidas en los ultimos 30 dias
            String sql3 = "SELECT * FROM v_partidas_mes;";

            Statement st3 = conn.createStatement();
            ResultSet rs3 = st3.executeQuery(sql3);

            while (rs3.next()) {
                fechas.add(rs3.getString(1));
                partidas.add(rs3.getInt(3));
            }
            rs3.close();
            st3.close();

            GraficoBarrasPartidas grafico = new GraficoBarrasPartidas(fechas, partidas);

            return ResponseEntity.status(200).body(grafico);

        } catch (SQLException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(400).body(null);
        } finally {
            cerrarConexion();
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/grafico-jugadores")
    public ResponseEntity<GraficoBarrasJugadores> getGraficoJugadores() {
        try {
            abrirConexion();

            ArrayList<String> fechas = new ArrayList<String>();
            ArrayList<Integer> jugadores = new ArrayList<Integer>();

            //Graficos de barra - Consulta para array de jugadores en los ultimos 30 dias
            String sql4 = "SELECT * FROM v_jugadores_mes;";

            Statement st4 = conn.createStatement();
            ResultSet rs4 = st4.executeQuery(sql4);

            while (rs4.next()) {
                jugadores.add(rs4.getInt(3));
            }

            rs4.close();
            st4.close();
            GraficoBarrasJugadores grafico = new GraficoBarrasJugadores(fechas, jugadores);

            return ResponseEntity.status(200).body(grafico);

        } catch (SQLException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(400).body(null);
        } finally {
            cerrarConexion();
        }
    }
}
