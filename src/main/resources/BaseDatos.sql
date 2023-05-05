-- DROP SCHEMA dbo;

CREATE SCHEMA dbo;
-- BaseDatos.dbo.Cliente definition

-- Drop table

-- DROP TABLE BaseDatos.dbo.Cliente;

CREATE TABLE BaseDatos.dbo.Cliente (
	id bigint NOT NULL,
	direccion varchar(255) COLLATE Modern_Spanish_CI_AS NULL,
	edad int NOT NULL,
	genero varchar(255) COLLATE Modern_Spanish_CI_AS NULL,
	identificacion varchar(255) COLLATE Modern_Spanish_CI_AS NULL,
	nombre varchar(255) COLLATE Modern_Spanish_CI_AS NULL,
	telefono varchar(255) COLLATE Modern_Spanish_CI_AS NULL,
	contrasena varchar(255) COLLATE Modern_Spanish_CI_AS NULL,
	estado bit NOT NULL,
	CONSTRAINT PK__Cliente__3213E83F99AFBB9C PRIMARY KEY (id)
);
 CREATE  UNIQUE NONCLUSTERED INDEX UK_4l7ftruxsjh5qf8hwftwt52rj ON dbo.Cliente (  identificacion ASC  )  
	 WHERE  ([identificacion] IS NOT NULL)
	 WITH (  PAD_INDEX = OFF ,FILLFACTOR = 100  ,SORT_IN_TEMPDB = OFF , IGNORE_DUP_KEY = OFF , STATISTICS_NORECOMPUTE = OFF , ONLINE = OFF , ALLOW_ROW_LOCKS = ON , ALLOW_PAGE_LOCKS = ON  )
	 ON [PRIMARY ] ;
ALTER TABLE BaseDatos.dbo.Cliente WITH NOCHECK ADD CONSTRAINT CK__Cliente__genero__37A5467C CHECK ([genero]='Masculino' OR [genero]='Femenino');


-- BaseDatos.dbo.hibernate_sequences definition

-- Drop table

-- DROP TABLE BaseDatos.dbo.hibernate_sequences;

CREATE TABLE BaseDatos.dbo.hibernate_sequences (
	sequence_name varchar(255) COLLATE Modern_Spanish_CI_AS NOT NULL,
	next_val bigint NULL,
	CONSTRAINT PK__hibernat__666199D5D0872E26 PRIMARY KEY (sequence_name)
);


-- BaseDatos.dbo.Cuenta definition

-- Drop table

-- DROP TABLE BaseDatos.dbo.Cuenta;

CREATE TABLE BaseDatos.dbo.Cuenta (
	id bigint IDENTITY(1,1) NOT NULL,
	estado bit NOT NULL,
	numeroCuenta varchar(20) COLLATE Modern_Spanish_CI_AS NULL,
	saldoInicial real NOT NULL,
	tipoCuenta varchar(255) COLLATE Modern_Spanish_CI_AS NULL,
	cliente_id bigint NULL,
	CONSTRAINT PK__Cuenta__3213E83F191480D1 PRIMARY KEY (id),
	CONSTRAINT cuenta_fk FOREIGN KEY (cliente_id) REFERENCES BaseDatos.dbo.Cliente(id)
);
 CREATE  UNIQUE NONCLUSTERED INDEX UK_m09koyl5cv8p27c5gmrk7xk8u ON dbo.Cuenta (  numeroCuenta ASC  )  
	 WHERE  ([numeroCuenta] IS NOT NULL)
	 WITH (  PAD_INDEX = OFF ,FILLFACTOR = 100  ,SORT_IN_TEMPDB = OFF , IGNORE_DUP_KEY = OFF , STATISTICS_NORECOMPUTE = OFF , ONLINE = OFF , ALLOW_ROW_LOCKS = ON , ALLOW_PAGE_LOCKS = ON  )
	 ON [PRIMARY ] ;


-- BaseDatos.dbo.Movimiento definition

-- Drop table

-- DROP TABLE BaseDatos.dbo.Movimiento;

CREATE TABLE BaseDatos.dbo.Movimiento (
	id int IDENTITY(1,1) NOT NULL,
	fecha date NULL,
	saldo real NOT NULL,
	tipoMovimiento varchar(255) COLLATE Modern_Spanish_CI_AS NULL,
	valor real NOT NULL,
	cuenta_id bigint NULL,
	CONSTRAINT PK__Movimien__3213E83F64B8ADD1 PRIMARY KEY (id),
	CONSTRAINT FK2h22h2mjjw88q0hrqeygjsf06 FOREIGN KEY (cuenta_id) REFERENCES BaseDatos.dbo.Cuenta(id)
);
ALTER TABLE BaseDatos.dbo.Movimiento WITH NOCHECK ADD CONSTRAINT CK__Movimient__tipoM__3E52440B CHECK ([tipoMovimiento]='Retiro' OR [tipoMovimiento]='Deposito');


