void RealizarAtaque(Aldea aldea, Aldea enemigo, List<int> cantidad, List<int> tipos){
	if(aldea.poblacion > 0){
		List<Tropa> tropaAtaque = aldea.usarTropas(cantidad, tipos, enemigo);
		enemigo.atacar(aldea, tropaAtaque);
	}
}

void RecibirAtaque(Aldea aldea, List<Tropa> tropas){
	int tiempo = currentTime() + TIEMPO_ATAQUE;

	while((tiempo - currentTime() > 0) && !aldea.destruida() && !tropas.empty()){
		foreach(tropa in tropas){
			if(!tropa.atacando()){
				Edificio edif = null;

				switch(tropa.tipo){
					case "arquera":
					case "barbaro":
					edif = aldea.edifCercano(tropa.posicion);
					break;
					case "gigante":
					edif = aldea.torreCercana(tropa.posicion);
					break;
					case "duende":
					edif = aldea.recolectorCercano(tropa.posicion);
					break;
				}

				if(edif == null){
					edif = aldea.edifCercano(tropa.posicion);
				}

				tropa.atacar(edif);
			}
		}

		foreach(torre in aldea.torres()){
			if(!torre.atacando()){
				Tropa tropa = aldea.tropaCercana(torre, tropas);

				if (tropa != null)
					torre.atacar(tropa);
			}
		}
	}
}

List<Tropa> tropas = [tropa1,tropa2,tropa3,..., tropan];
aldea;
tiempo;