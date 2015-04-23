void CrearTropa(int tipoTropa, Edificio edificio, Aldea aldea){
	if(edificio.generaTropas()){
		if(edificio.disponible(tropas[tipoTropa])){
			if(aldea.elixir >= tropas[tipoTropa].precio){ // Este faltaba
				if(aldea.capacidadTropas() - aldea.poblacion >= tropas[tipoTropa].peso){
					if(edificio.capacidadCola() >= tropas[tipoTropa].peso){
						int i = BuscarLEF(infinito);
						LEF[i] = edificio.construirTropa(tiempoActual, tropas[tipoTropa]);
						aldea.poblacion += tropas[tipoTropa].peso;
					}
				}
			}
		}
	}
}

void CulminarTropa(Edificio edificio, Aldea aldea){
	aldea.habilitarTropa(edificio.liberarCola()); //antes: edificio.liberarCola();
}

// Viendo aldea.poblacion pareciera que hubiesemos considerado no poner campamentos