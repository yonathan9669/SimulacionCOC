void CrearEdificio(int tipo, Aldea aldea){
	if(edificios[tipo].tipoCompra == 'oro'){
		if(aldea.oro >= edificios[tipo].precioCompraMejora()){
			if(aldea.contructoresLibres() > 0){
				int i = BuscarLEF(infitino);
				LEF[i] = aldea.construirEdif(tiempoActual, edificios[tipo]);
			}
		}
	}else{
		if(aldea.elixir >= edificios[tipo].precioCompraMejora()){
			if(aldea.contructoresLibres() > 0){
				int i = BuscarLEF(infitino);
				LEF[i] = aldea.construirEdif(tiempoActual, edificios[tipo]);
			}
		}
	}
}

void MejorarEdificio(Edificio edificio, Aldea aldea){
	int tipo = edificio.tipo;
	int nivel = edificio.nivel;

	if(edificio.tipoCompra == 'oro'){
		if(aldea.oro >= edificios[tipo].precioCompraMejora()){
			if(aldea.contructoresLibres() > 0){
				int i = BuscarLEF(infitino);
				LEF[i] = aldea.mejorarEdificio(tiempoActual, edificios[tipo].mejoras[nivel]);
				// Aqui puede ser mejor enviar como segundo parametro el edificio a mejorar, porque el edificio sabe en que nivel esta 
				// y sabe cual es su siguiente nivel
				// LEF[i] = aldea.mejorarEdificio(tiempoActual, edificio);
			}
		}
	}else{
		if(aldea.elixir >= edificios[tipo].precioCompraMejora()){
			if(aldea.contructoresLibres() > 0){
				int i = BuscarLEF(infitino);
				LEF[i] = aldea.mejorarEdificio(tiempoActual, edificios[tipo].mejoras[nivel]);
				// Aqui puede ser mejor enviar como segundo parametro el edificio a mejorar, porque el edificio sabe en que nivel esta 
				// y sabe cual es su siguiente nivel
				// LEF[i] = aldea.mejorarEdificio(tiempoActual, edificio);
			}
		}
	}
}

void CulminarEdificio(Edificio edificio, Aldea aldea){
	edificio.habilitar();
	aldea.liberarConstructor();
}

// Adentro de CulminarEdificio() puede que sea mejor poner: aldea.habilitarEdificio(edificio) en vez de edificio.habilitar()
