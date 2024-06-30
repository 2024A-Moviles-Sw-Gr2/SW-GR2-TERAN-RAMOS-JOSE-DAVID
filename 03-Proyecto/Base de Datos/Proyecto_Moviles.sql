/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     29/6/2024 22:15:25                           */
/*==============================================================*/


drop index CLIENTE_PK;

drop table CLIENTE;

drop index RELATIONSHIP_6_FK;

drop index RELATIONSHIP_7_FK;

drop index CLIENTE_RESERVA_PK;

drop table CLIENTE_RESERVA;

drop index CONDUTOR_PK;

drop table CONDUTOR;

drop index RELATIONSHIP_1_FK;

drop index ENCOMIENDA_PK;

drop table ENCOMIENDA;

drop index RELATIONSHIP_7_FK2;

drop index PAGO_PK;

drop table PAGO;

drop index RELATIONSHIP_5_FK;

drop index RESERVA_PK;

drop table RESERVA;

drop index VEHICULO_PK;

drop table VEHICULO;

drop index RELATIONSHIP_3_FK;

drop index RELATIONSHIP_2_FK;

drop index VIAJE_PK;

drop table VIAJE;

/*==============================================================*/
/* Table: CLIENTE                                               */
/*==============================================================*/
create table CLIENTE (
   ID_CLIENTE           INT4                 not null,
   NOMBRE               TEXT                 null,
   APELLIDOS            TEXT                 null,
   FECHA_NACIMIENTO     DATE                 null,
   TELEFONO             NUMERIC(10)          null,
   EMAIL                TEXT                 null,
   constraint PK_CLIENTE primary key (ID_CLIENTE)
);

/*==============================================================*/
/* Index: CLIENTE_PK                                            */
/*==============================================================*/
create unique index CLIENTE_PK on CLIENTE (
ID_CLIENTE
);

/*==============================================================*/
/* Table: CLIENTE_RESERVA                                       */
/*==============================================================*/
create table CLIENTE_RESERVA (
   ID_CLIENTE           INT4                 not null,
   ID_RESERVA           INT4                 not null,
   constraint PK_CLIENTE_RESERVA primary key (ID_CLIENTE, ID_RESERVA)
);

/*==============================================================*/
/* Index: CLIENTE_RESERVA_PK                                    */
/*==============================================================*/
create unique index CLIENTE_RESERVA_PK on CLIENTE_RESERVA (
ID_CLIENTE,
ID_RESERVA
);

/*==============================================================*/
/* Index: RELATIONSHIP_7_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_7_FK on CLIENTE_RESERVA (
ID_RESERVA
);

/*==============================================================*/
/* Index: RELATIONSHIP_6_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_6_FK on CLIENTE_RESERVA (
ID_CLIENTE
);

/*==============================================================*/
/* Table: CONDUTOR                                              */
/*==============================================================*/
create table CONDUTOR (
   ID_CONDUCTOR         INT4                 not null,
   NOMBRE_CON           TEXT                 null,
   APELLIDOS_CON        TEXT                 null,
   TELEFONO             NUMERIC(10)          null,
   constraint PK_CONDUTOR primary key (ID_CONDUCTOR)
);

/*==============================================================*/
/* Index: CONDUTOR_PK                                           */
/*==============================================================*/
create unique index CONDUTOR_PK on CONDUTOR (
ID_CONDUCTOR
);

/*==============================================================*/
/* Table: ENCOMIENDA                                            */
/*==============================================================*/
create table ENCOMIENDA (
   ID_ENCOMIENDA        INT4                 not null,
   ID_VIAJE             INT4                 not null,
   NOMBRE_DESTINATARIO  TEXT                 null,
   APELLIDO_DESTINATARIO TEXT                 null,
   DIRECCION_ENTREGA    TEXT                 null,
   NUMERO_PRODUCTOS     INT4                 null,
   constraint PK_ENCOMIENDA primary key (ID_ENCOMIENDA)
);

/*==============================================================*/
/* Index: ENCOMIENDA_PK                                         */
/*==============================================================*/
create unique index ENCOMIENDA_PK on ENCOMIENDA (
ID_ENCOMIENDA
);

/*==============================================================*/
/* Index: RELATIONSHIP_1_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_1_FK on ENCOMIENDA (
ID_VIAJE
);

/*==============================================================*/
/* Table: PAGO                                                  */
/*==============================================================*/
create table PAGO (
   ID_PAGO              INT4                 not null,
   ID_RESERVA           INT4                 null,
   MONTO_TOTAL          DECIMAL              null,
   FECHA_PAGO           DATE                 null,
   NUM_TARJETA          TEXT                 null,
   TITULAR_TARJETA      TEXT                 null,
   FECHA_EXPIRACION     DATE                 null,
   CODIGO_SEGURIDAD     INT4                 null,
   constraint PK_PAGO primary key (ID_PAGO)
);

/*==============================================================*/
/* Index: PAGO_PK                                               */
/*==============================================================*/
create unique index PAGO_PK on PAGO (
ID_PAGO
);

/*==============================================================*/
/* Index: RELATIONSHIP_7_FK2                                    */
/*==============================================================*/
create  index RELATIONSHIP_7_FK2 on PAGO (
ID_RESERVA
);

/*==============================================================*/
/* Table: RESERVA                                               */
/*==============================================================*/
create table RESERVA (
   ID_RESERVA           INT4                 not null,
   ID_VIAJE             INT4                 not null,
   DIRECCION_ORIGEN     TEXT                 null,
   DIRECCION_DESTINO    TEXT                 null,
   NUMERO_PUESTOS       INT4                 null,
   COSTO_RESERVA        DECIMAL              null,
   constraint PK_RESERVA primary key (ID_RESERVA)
);

/*==============================================================*/
/* Index: RESERVA_PK                                            */
/*==============================================================*/
create unique index RESERVA_PK on RESERVA (
ID_RESERVA
);

/*==============================================================*/
/* Index: RELATIONSHIP_5_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_5_FK on RESERVA (
ID_VIAJE
);

/*==============================================================*/
/* Table: VEHICULO                                              */
/*==============================================================*/
create table VEHICULO (
   ID_VEHICULO          INT4                 not null,
   PLACA                TEXT                 null,
   MODELO               TEXT                 null,
   MARCA                TEXT                 null,
   COLOR                TEXT                 null,
   CAPACIDAD            INT4                 null,
   constraint PK_VEHICULO primary key (ID_VEHICULO)
);

/*==============================================================*/
/* Index: VEHICULO_PK                                           */
/*==============================================================*/
create unique index VEHICULO_PK on VEHICULO (
ID_VEHICULO
);

/*==============================================================*/
/* Table: VIAJE                                                 */
/*==============================================================*/
create table VIAJE (
   ID_VIAJE             INT4                 not null,
   ID_CONDUCTOR         INT4                 not null,
   ID_VEHICULO          INT4                 not null,
   HORA_VIAJE           TIME                 null,
   PROVINCIA_ORIGEN     TEXT                 null,
   PROVINCIA_DESTINO    TEXT                 null,
   DIA_VIAJE            DATE                 null,
   PUESTOS_OCUPADOS     INT4                 null,
   constraint PK_VIAJE primary key (ID_VIAJE)
);

/*==============================================================*/
/* Index: VIAJE_PK                                              */
/*==============================================================*/
create unique index VIAJE_PK on VIAJE (
ID_VIAJE
);

/*==============================================================*/
/* Index: RELATIONSHIP_2_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_2_FK on VIAJE (
ID_CONDUCTOR
);

/*==============================================================*/
/* Index: RELATIONSHIP_3_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_3_FK on VIAJE (
ID_VEHICULO
);

alter table CLIENTE_RESERVA
   add constraint FK_CLIENTE__RELATIONS_CLIENTE foreign key (ID_CLIENTE)
      references CLIENTE (ID_CLIENTE)
      on delete restrict on update restrict;

alter table CLIENTE_RESERVA
   add constraint FK_CLIENTE__RELATIONS_RESERVA foreign key (ID_RESERVA)
      references RESERVA (ID_RESERVA)
      on delete restrict on update restrict;

alter table ENCOMIENDA
   add constraint FK_ENCOMIEN_RELATIONS_VIAJE foreign key (ID_VIAJE)
      references VIAJE (ID_VIAJE)
      on delete restrict on update restrict;

alter table PAGO
   add constraint FK_PAGO_RELATIONS_RESERVA foreign key (ID_RESERVA)
      references RESERVA (ID_RESERVA)
      on delete restrict on update restrict;

alter table RESERVA
   add constraint FK_RESERVA_RELATIONS_VIAJE foreign key (ID_VIAJE)
      references VIAJE (ID_VIAJE)
      on delete restrict on update restrict;

alter table VIAJE
   add constraint FK_VIAJE_RELATIONS_CONDUTOR foreign key (ID_CONDUCTOR)
      references CONDUTOR (ID_CONDUCTOR)
      on delete restrict on update restrict;

alter table VIAJE
   add constraint FK_VIAJE_RELATIONS_VEHICULO foreign key (ID_VEHICULO)
      references VEHICULO (ID_VEHICULO)
      on delete restrict on update restrict;

