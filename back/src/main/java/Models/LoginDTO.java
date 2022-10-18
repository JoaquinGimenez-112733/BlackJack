/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author juanc
 */
public class LoginDTO {
    private boolean partida;
    private String token;
    private manosPartidaDTO manos;
    
    public LoginDTO() {
    }
    
    public LoginDTO(boolean partida, String token) {
        this.partida = partida;
        this.token = token;
    }
    public LoginDTO(boolean partida, String token, manosPartidaDTO manos) {
        this.partida = partida;
        this.token = token;
        this.manos = manos;
    }

    public boolean isPartida() {
        return partida;
    }

    public void setPartida(boolean partida) {
        this.partida = partida;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public manosPartidaDTO getManos() {
        return manos;
    }

    public void setManos(manosPartidaDTO manos) {
        this.manos = manos;
    }

}
