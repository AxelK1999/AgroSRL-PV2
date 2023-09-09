create table campo(
	id int not null,
	direccion varchar(35),
	fk_id_estado int not null,
	primary key(pk_nro_campo),
	foreign key(fk_id_estado) references estadocampo(pk_id_estado)
);

create table cultivo(
	id int not null,
	nombre varchar(30),
	caracteristicas varchar(100),
	fk_nro_suelo int not null,
	primary key(pk_nro_cultivo),
	foreign key(fk_nro_suelo) references suelo(pk_nro_suelo) 
); 

create table detalleProyecto(
	id int not null,
	fechaInicio date,
	fechaFin date,
	fk_nro_proyecto int not null,
	fk_nro_laboreo int not null,
	primary key(pk_nro_detalle),
	foreign key(fk_nro_proyecto) references proyecto(pk_nro_proyecto),
	foreign key(fk_nro_laboreo) references Laboreo(pk_nro_laboreo)
);

create table estadocampo(
	id int not null,
	nombreestado varchar(45),
	primary key(pk_id_estado)
);

create table Laboreo(
 	id int not null,
	estapa varchar(30),
	dierccion varchar(50),
	fk_nro_cultivo int not null,
	primary key(pk_nro_laboreo),
	foreign key(fk_nro_cultivo) references cultivo(pk_nro_cultivo)
);

create table lotes(
	id int not null,
	superficie float,
	fk_nro_campo int not null,
	fk_nro_suelo int not null,
	primary key(pk_nro_lote),
	foreign key(fk_nro_campo) references campo(pk_nro_campo),
	foreign key(fk_nro_suelo) references suelo(pk_nro_suelo)
);

create table proyecto(
	id int not null,
	estado varchar(30),
	motivo_cancelar varchar(100),
	fk_nro_lote int not null,
	fk_nro_cultivo int not null,
	primary key(pk_nro_proyecto),
	foreign key(fk_nro_lote) references lotes(pk_nro_lote),
	foreign key(fk_nro_cultivo) references cultivo(pk_nro_cultivo)
);

create table situacionanormal(
	id int not null,
	nombre varchar(35),
	primary key(pk_nro_incidencia)
);

create table suelo(
	id int not null,
	caracteristicas varchar(100),
	primary key(id)
);
