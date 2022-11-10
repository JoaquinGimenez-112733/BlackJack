export interface RespuestaReportesIndividuales {
  rachaVictorias: {
    valor: number;
    tier: number;
  };
  rachaDerrotas: {
    valor: number;
    tier: number;
  };
  cantBJ: {
    valor: number;
    tier: number;
  };
  cantPartidas: {
    valor: number;
    tier: number;
  };
}

export interface RespuestaReportesGlobales {
  tortaIzq: {
    jugadorP: number;
    compuP: number;
  };
  tortaDer: {
    win: number;
    lose: number;
    tie: number;
  };
  grafico: {
    fecha: string[];
    partidas: number[];
    jugadores: number[];
  };
}
