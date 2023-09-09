package DAO_Modelos;

import java.util.LinkedList;

public interface DAO_CRUD_Generica<Objeto> {

	public int actualizar(Objeto elemento);
	public int eliminar(Objeto elemento);
	public int agregar(Objeto elemento);
	public Objeto obtener(int claveElemnto,Class C);
    public LinkedList<Objeto> obtenerTodos(Objeto elemnto);
	
}
