package blackjack.tpi;

import java.util.ArrayList;
import java.sql.*;
import Models.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import java.util.Set;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    public String abrirConexion() {
        String msj = "";
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BlackJack", "claselab4", "123456");
            msj = "Conexion exitosa!";
        } catch (SQLException ex) {
            ex.printStackTrace();
            msj = "No se pudo conectar a la base de datos";
        }

        return msj;
    }

    public String cerrarConexion() {
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

    public BlackJackController() {
        mazo = new Mazo();
        manoJugador = new ArrayList<Carta>();
        manoCompu = new ArrayList<Carta>();
        nuevoMazo = new ArrayList<Carta>();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/mazo")
    public manosPartidaDTO getMazo() {

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
            st.setInt(7, 1);
            st.setBoolean(8, false);

            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.next();
            partida_id = rs.getInt(1);
            msj = "Partida registrado exitosamente";
            System.out.println(partida_id);
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
    public manosPartidaDTO pedirCarta() {

        Carta carta = nuevoMazo.get(0);
        manoJugador.add(carta);
        nuevoMazo.remove(0);

        this.puntosJugador = 0;
        for (Carta cj : manoJugador) {
            puntosJugador += cj.getValor();
        }
        if (manoJugador.contains('A') && puntosJugador < 12) {
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
            st.setInt(7, 1);
            st.setBoolean(8, false);
            st.setInt(9, partida_id);

            st.executeUpdate();

            msj = "Partida registrado exitosamente";
            System.out.println(partida_id);
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
    public manosPartidaDTO pasar() {

        puntosCompu += puntosOcultosCompu;

        while (puntosCompu < 17) {

            Carta carta = nuevoMazo.get(0);
            manoCompu.add(carta);
            nuevoMazo.remove(0);

            this.puntosCompu = 0;
            for (Carta cc : manoCompu) {
                puntosCompu += cc.getValor();
            }
            if (manoCompu.contains('A') && puntosCompu < 12) {
                puntosCompu += 10;
            }

        }
        //UPDATEAMOS LA PARTIDA EN BD AL FINALIZAR
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
            st.setInt(7, 1);
            st.setBoolean(8, true);
            st.setInt(9, partida_id);

            st.executeUpdate();

            msj = "Partida registrado exitosamente";
            System.out.println(partida_id);
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
    @PostMapping("/login")
    public int login(@RequestBody Usuario us) {
        int resp = 0;
        userId = 0;

        Usuario userTarget = new Usuario();
        BeanUtils.copyProperties(us, userTarget);

        //VERIFICAMOS QUE EXISTA EN LA BD
        String msj = "";
        try {
            abrirConexion();

            String sql = "SELECT id FROM usuario WHERE usuario = ? AND password = ?;";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, userTarget.getUsuario());
            st.setString(2, userTarget.getPassword());

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                userId = rs.getInt(1);
                msj = "Loggin exitoso";
                System.out.println(msj);
            } else {
                msj = "Loggin falló";
                System.out.println(msj);
            }

            if (userId != 0) {
                //VERIFICAMOS QUE EXISTA EN LA BD

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

                        msj = "Se encontró una partida sin finalizar";
                        System.out.println(msj);
                        System.out.println(jugadorStrJson);
                        System.out.println(manoJugador.toString());
                    } else {
                        msj = "No tiene partidas pendientes de finalizacion";
                        System.out.println(msj);
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    msj = "No se pudo registrar su partida";
                } finally {
                    cerrarConexion();
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            msj = "No se pudo registrar su partida";
        } finally {
            cerrarConexion();
        }

        return resp;
    }
}
