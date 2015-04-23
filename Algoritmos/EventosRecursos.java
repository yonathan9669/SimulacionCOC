void RecogerRecursos(Edificio edificio, Aldea aldea){
	if(edificio.generadorRecursos()){
		int cantidad = edificio.recogerRecursos();
		if(edificio.tipoRecurso() == 'oro'){
			int almacenado = aldea.oro;
			almacenado += cantidad;
			almacenado = (aldea.capacidadOro() < almacenado)? aldea.capacidadOro() : almacenado;
			aldea.almacenar(almacenado);
		}
		else{
			int almacenado = aldea.elixir;
			almacenado += cantidad;
			almacenado = (aldea.capacidadElixir() < almacenado)? aldea.capacidadElixir() : almacenado;
			aldea.almacenar(almacenado);
		}
	}
}

