package Models;

import Models.Reportes.GraficoBarras;
import Models.Reportes.TortaDer;
import Models.Reportes.TortaIzq;

public class EstadisticasGlobalesDTO {
    private TortaIzq tortaIzq;
    private TortaDer tortaDer;
    private GraficoBarras grafico;

    public EstadisticasGlobalesDTO(TortaIzq tortaIzq, TortaDer tortaDer, GraficoBarras grafico) {
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

    public GraficoBarras getGrafico() {
        return grafico;
    }

    public void setGrafico(GraficoBarras grafico) {
        this.grafico = grafico;
    }
}
