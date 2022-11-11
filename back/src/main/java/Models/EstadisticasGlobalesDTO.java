package Models;

import Models.Reportes.GraficoBarrasPartidas;
import Models.Reportes.TortaDer;
import Models.Reportes.TortaIzq;

public class EstadisticasGlobalesDTO {
    private TortaIzq tortaIzq;
    private TortaDer tortaDer;
    private GraficoBarrasPartidas grafico;

    public EstadisticasGlobalesDTO(TortaIzq tortaIzq, TortaDer tortaDer, GraficoBarrasPartidas grafico) {
        this.tortaIzq = tortaIzq;
        this.tortaDer = tortaDer;
        this.grafico = grafico;
    }

    public TortaIzq getTortaIzq() {
        return tortaIzq;
    }

    public void setTortaIzq(TortaIzq tortaIzq) {
        this.tortaIzq = tortaIzq;
    }

    public TortaDer getTortaDer() {
        return tortaDer;
    }

    public void setTortaDer(TortaDer tortaDer) {
        this.tortaDer = tortaDer;
    }

    public GraficoBarrasPartidas getGrafico() {
        return grafico;
    }

    public void setGrafico(GraficoBarrasPartidas grafico) {
        this.grafico = grafico;
    }
}
