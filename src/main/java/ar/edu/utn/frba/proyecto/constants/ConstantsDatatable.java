package ar.edu.utn.frba.proyecto.constants;

import java.util.HashMap;
import java.util.Map;

public class ConstantsDatatable {

	public static String PRODUCTO_ID = "idProducto";

	public static String ANALISIS_ID = "idAnalisis";

	public static String ESTADO_ID = "idEstado";
	
	public static String USUARIO_ID = "idUsuario";

	public static String PERFIL_ID = "idPerfil";

	public static String MAQUINARIA_ID = "idMaquinaria";

	public static String CRITERIO_ID = "idCriterio";

	public static String PASO_ID = "idPaso";

	public static String VERSION_ID = "idversion";

	public static String LOTE_ID = "idLote";

	public static String PERFIL_PRODUCCION = "Produccón";
	public static String PERFIL_CALIDAD = "Calidad";
	public static String PERFIL_DESARROLLO = "Desarrollo";
	public static String PERFIL_SUPERVISION = "Supervisión";

	public static String USUARIO_CONTRASEÑA = "contrasenia";

	public static String USUARIO_LEGAJO = "legajo";

	public static String USUARIO_ALIAS = "alias";
	
	public static String GENERAL_NOMBRE = "nombre";

	public static String GENERAL_APELLIDO = "apellido";

	public static String GENERAL_DESCRIPCION = "descripcion";

	public static String GENERAL_ESTADO = "estado";

	public static String VALOR_ESPERADO = "valorEsperado";

	public static String VALOR_OBTENIDO = "valorObtenido";
	
	public static String AUDIT_FECHA_CREACION = "fechaCreacion";

	public static String AUDIT_FECHA_ULTIMA_MOD = "fechaUltimaMod";

	public static String AUDIT_USUARIO_CREACION = "idUsuarioCreacion";

	public static String AUDIT_USUARIO_ULTIMA_MOD = "idUsuarioUltimaMod";

	public static String ULTIMA_VERSION = "ultimaVersion";
	
	/* Constantes para estados */
	
	public static Integer ESTADO_USUARIO_HABILITADO = 1;
	public static Integer ESTADO_USUARIO_DESHABILITADO = 2;

	public static Integer ESTADO_MAQUINARIA_DISPONIBLE = 3;
	public static Integer ESTADO_MAQUINARIA_NO_DISPONIBLE = 4;
	
	public static Integer ESTADO_GROUPID_USUARIO = 1;
	public static Integer ESTADO_GROUPID_MAQUINARIA = 2;
	public static Integer ESTADO_GROUPID_PRODUCTO = 3;
	public static Integer ESTADO_GROUPID_PROCESO_PRODUCCION = 4;
	public static Integer ESTADO_GROUPID_SOLICITUD_ANALISIS = 5;

	public static Integer ESTADO_PROCESO_EN_PROCESO = 7;
	public static Integer ESTADO_PROCESO_CANCELADO = 8;
	public static Integer ESTADO_PROCESO_FINALIZADO = 9;

	public static Integer ESTADO_SOLICITUD_ANALISIS_SOLICITADO = 10;
	public static Integer ESTADO_SOLICITUD_ANALISIS_RECIBIDO = 11;
	public static Integer ESTADO_SOLICITUD_ANALISIS_EN_PROCESO = 12;
	public static Integer ESTADO_SOLICITUD_ANALISIS_FINALIZADO = 13;
	public static Integer ESTADO_SOLICITUD_ANALISIS_CANCELADO = 14;
	
	/* Constantes para reportes */
	
	@SuppressWarnings("serial")
	public static Map<Integer,String> reportMap = new HashMap<Integer,String>(){{
		put(1,"rep_cantidad_analisis");
		put(2,"rep_estado_producto");
		put(3,"rep_lotes_producto");
		put(4,"rep_tiempo_promedio");
	}};
	
	
}
